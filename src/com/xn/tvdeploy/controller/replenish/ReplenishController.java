package com.xn.tvdeploy.controller.replenish;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.tpdatainfo.TpDataInfoController;
import com.xn.tvdeploy.model.GewayModel;
import com.xn.tvdeploy.model.TpDataInfoModel;
import com.xn.tvdeploy.service.TpDataInfoService;
import com.xn.tvdeploy.util.PublicMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 补单的Controller层
 * @Author yoko
 * @Date 2020/8/6 10:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/replenish")
public class ReplenishController extends BaseController {

    private static Logger log = Logger.getLogger(ReplenishController.class);

    @Autowired
    private TpDataInfoService<TpDataInfoModel> tpDataInfoService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/replenish/replenishIndex";
    }



    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, TpDataInfoModel model) throws Exception {
        List<TpDataInfoModel> dataList = new ArrayList<TpDataInfoModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setChannelId(account.getId());
            }
            TpDataInfoModel dataModel = new TpDataInfoModel();
            dataModel = tpDataInfoService.getChannelData(model);
            dataList.add(dataModel);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        TpDataInfoModel atModel = new TpDataInfoModel();
        atModel.setId(id);
        model.addAttribute("account", tpDataInfoService.getChannelData(atModel));
        return "manager/replenish/replenishEdit";
    }


    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, TpDataInfoModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            if (bean.getDataType() == 1){
                // 补下发
                TpDataInfoModel dataCoreModel = PublicMethod.assembleDataCore(bean, "");
                tpDataInfoService.addDataCore(dataCoreModel);
            }else {
                // 补全套
                TpDataInfoModel channelDataModel = PublicMethod.assembleChannelData(bean);
                TpDataInfoModel dataCoreModel = PublicMethod.assembleDataCore(bean, channelDataModel.getMyTradeNo());
                tpDataInfoService.add(dataCoreModel);
                tpDataInfoService.addDataCore(dataCoreModel);
            }
            sendSuccessMessage(response, "补单成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }





}
