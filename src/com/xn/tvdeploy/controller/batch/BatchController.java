package com.xn.tvdeploy.controller.batch;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountDpModel;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.BatchModel;
import com.xn.tvdeploy.model.ChannelModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.BatchService;
import com.xn.tvdeploy.service.ChannelService;
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
import java.util.List;

/**
 * @author df
 * @Description:批次号的Controller层
 * @create 2018-09-13 16:04
 **/
@Controller
@RequestMapping("/batch")
public class BatchController extends BaseController {
    private static Logger log = Logger.getLogger(BatchController.class);

    @Autowired
    private BatchService<BatchModel> batchService;

    @Autowired
    private ChannelService<ChannelModel> channelService;//渠道号

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;//渠道账号


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/batch/batchIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, BatchModel model) throws Exception {
        AccountDpModel queryDpModel = new AccountDpModel();
        queryDpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        List<BatchModel> dataList = new ArrayList<BatchModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setTpId(account.getId());
            }
            dataList = batchService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }

    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, BatchModel model) throws Exception {
        List<BatchModel> dataList = new ArrayList<BatchModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setTpId(account.getId());
            }
            model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
            dataList = batchService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<AccountTpModel> tpList = new ArrayList<AccountTpModel>();
        AccountTpModel queryTpModel = new AccountTpModel();
        queryTpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        List<ChannelModel> chList = new ArrayList<ChannelModel>();
        ChannelModel queryChannelModel = new ChannelModel();
        queryChannelModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                queryTpModel.setId(account.getId());
                queryChannelModel.setTpId(account.getId());
            }
            tpList = accountTpService.queryAllList(queryTpModel);
            chList = channelService.queryAllList(queryChannelModel);
        }
        model.addAttribute("tpList", tpList);
        model.addAttribute("chList", chList);
        return "manager/batch/batchAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, BatchModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setCreateUser(account.getId());
            bean.setCreateRole(account.getRoleId());
            bean.setBatchType(ManagerConstant.PUBLIC_CONSTANT.BATCH_TYPE_SDLR);
            batchService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(HttpServletRequest request, HttpServletResponse response, Model model, long id) {
        List<AccountTpModel> tpList = new ArrayList<AccountTpModel>();
        AccountTpModel queryTpModel = new AccountTpModel();
        queryTpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        ChannelModel chModel = new ChannelModel();
        chModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                queryTpModel.setId(account.getId());
            }
            tpList = accountTpService.queryAllList(queryTpModel);//渠道账号
            model.addAttribute("tpList", tpList);
            BatchModel dataModel = new BatchModel();
            dataModel.setId(id);
            dataModel = batchService.queryById(dataModel);
            model.addAttribute("batchModel", dataModel);
            List<ChannelModel> chList = new ArrayList<ChannelModel>();
            chModel.setTpId(dataModel.getTpId());
            chList = channelService.queryAllList(chModel);
            model.addAttribute("chList", chList);
        }
        return "manager/batch/batchEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,BatchModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            batchService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, BatchModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            batchService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, BatchModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            batchService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
