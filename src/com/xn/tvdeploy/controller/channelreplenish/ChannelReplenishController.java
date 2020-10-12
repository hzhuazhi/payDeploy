package com.xn.tvdeploy.controller.channelreplenish;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.HttpSendUtils;
import com.xn.common.util.OssUploadUtil;
import com.xn.common.util.StringUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.ChannelReplenishModel;
import com.xn.tvdeploy.model.TpDataInfoModel;
import com.xn.tvdeploy.service.ChannelReplenishService;
import com.xn.tvdeploy.service.TpDataInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 渠道补单申请的Controller层
 * @Author yoko
 * @Date 2020/10/12 14:53
 * @Version 1.0
 */
@Controller
@RequestMapping("/channelreplenish")
public class ChannelReplenishController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelReplenishController.class);

    public static String fruitUrl = "http://localhost:8002/fruitDeploy/merchantreplenish/actionAdd.do?";

    @Autowired
    private ChannelReplenishService<ChannelReplenishModel> channelReplenishService;

    @Autowired
    private TpDataInfoService<TpDataInfoModel> tpDataInfoService;



    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelreplenish/channelreplenishIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelReplenishModel model) throws Exception {
        List<ChannelReplenishModel> dataList = new ArrayList<ChannelReplenishModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setChannelId(account.getId());
            }
            dataList = channelReplenishService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelReplenishModel model) throws Exception {
        List<ChannelReplenishModel> dataList = new ArrayList<ChannelReplenishModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setChannelId(account.getId());
            }
            dataList = channelReplenishService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/channelreplenish/channelreplenishAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files, ChannelReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account != null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            //check是否有此请求的订单信息
            TpDataInfoModel queryBean = new TpDataInfoModel();
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                queryBean.setChannelId(account.getId());
            }
            queryBean.setOutTradeNo(bean.getOutTradeNo());
            TpDataInfoModel tpDataInfoModel = tpDataInfoService.queryByCondition(queryBean);
            if (tpDataInfoModel == null || tpDataInfoModel.getId() <= ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                sendFailureMessage(response,"没有此订单的请求纪录,请您核实!");
                return;
            }
            if (tpDataInfoModel.getTradeStatus() > 0){
                sendFailureMessage(response,"此订单已属于交易成功,无需进行补单申请!");
                return;
            }
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (tpDataInfoModel.getChannelId() != account.getId()){
                    // 这里等于查询出来的请求纪录不属于登录后台的渠道的订单
                    sendFailureMessage(response,"错误,请联系商务人员!");
                    return;
                }
            }
            String pictureAds = OssUploadUtil.localMethod(files);
            if (StringUtils.isBlank(pictureAds)){
                sendFailureMessage(response, "图片上传失败,请重试!");
                return;
            }
            bean.setChannelId(tpDataInfoModel.getChannelId());
            bean.setMyTradeNo(tpDataInfoModel.getMyTradeNo());
            bean.setTotalAmount(tpDataInfoModel.getTotalAmount());
            bean.setPictureAds(pictureAds);
            channelReplenishService.add(bean);

            // 发送给水果平台
            String sendUrl = fruitUrl;
            String sendData = "linkId=" + bean.getId() + "&outTradeNo=" + bean.getMyTradeNo() + "&pictureAds=" + pictureAds;
            String resp = HttpSendUtils.sendGet(sendUrl + sendData, null, null);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        ChannelReplenishModel atModel = new ChannelReplenishModel();
        atModel.setId(id);
        model.addAttribute("account", channelReplenishService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/channelreplenish/channelreplenishEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ChannelReplenishModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            // 只允许修改备注
            ChannelReplenishModel updateModel = new ChannelReplenishModel();
            updateModel.setId(bean.getId());
            if (!StringUtils.isBlank(bean.getRemark())){
                updateModel.setRemark(bean.getRemark());
            }
            channelReplenishService.update(updateModel);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, ChannelReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelReplenishService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelReplenishService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取数据的详情
     */
    @RequestMapping("/jumpInfo")
    public String jumpInfo(Model model, long id) {
        ChannelReplenishModel atModel = new ChannelReplenishModel();
        atModel.setId(id);
        model.addAttribute("account", channelReplenishService.queryById(atModel));
        return "manager/channelreplenish/channelreplenishInfo";
    }

    /**
     *
     * 更新审核-对外接口
     */
    @RequestMapping(value = "/actionUpdateCheck", method = {RequestMethod.GET})
    public void updateCheck(HttpServletRequest request, HttpServletResponse response,ChannelReplenishModel channelReplenishModel) throws Exception{
        if (channelReplenishModel != null && channelReplenishModel.getId() > 0){
            if (!StringUtils.isBlank(channelReplenishModel.getCheckInfo())){
                String checkInfo = StringUtil.decoderBase64(channelReplenishModel.getCheckInfo());
                channelReplenishModel.setCheckInfo(checkInfo);
            }
            channelReplenishService.updateCheck(channelReplenishModel);
            // 返回数据给客户端
            PrintWriter out = response.getWriter();
            out.print("ok");
            out.flush();
            out.close();
            return;
        }else {
            // 返回数据给客户端
            PrintWriter out = response.getWriter();
            out.print("on");
            out.flush();
            out.close();
            return;
        }

    }



}
