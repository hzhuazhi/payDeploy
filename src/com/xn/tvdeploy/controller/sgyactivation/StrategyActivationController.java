package com.xn.tvdeploy.controller.sgyactivation;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.*;
import com.xn.tvdeploy.service.*;
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
 * @Description:策略-激活-下次联网时间的Controller层
 * @create 2018-09-13 22:57
 **/
@Controller
@RequestMapping("/sgyactivation")
public class StrategyActivationController extends BaseController {
    private static Logger log = Logger.getLogger(StrategyActivationController.class);

    @Autowired
    private StrategyActivationService<StrategyActivationModel> strategyActivationService;

    @Autowired
    private ChannelService<ChannelModel> channelService;//渠道号

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;//渠道账号

    @Autowired
    private BatchService<BatchModel> batchService;//批次号

    @Autowired
    private AccountDpService<AccountDpModel> accountDpService;//渠道账号

    @Autowired
    private AppService<AppModel> appService;//应用


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/sgyactivation/strategyActivationIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, StrategyActivationModel model) throws Exception {
        AccountDpModel queryDpModel = new AccountDpModel();
        queryDpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        List<StrategyActivationModel> dataList = new ArrayList<StrategyActivationModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setCreateUser(account.getId());
            }
            dataList = strategyActivationService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
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

        List<BatchModel> bchList = new ArrayList<BatchModel>();
        BatchModel queryBatchModel = new BatchModel();
        queryBatchModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);

        List<AccountDpModel> dpList = new ArrayList<AccountDpModel>();
        AccountDpModel queryDpModel = new AccountDpModel();
        queryDpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);

        List<AppModel> appList = new ArrayList<AppModel>();
        AppModel queryAppModell = new AppModel();
        queryAppModell.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);

        tpList = accountTpService.queryAllList(queryTpModel);
        chList = channelService.queryAllList(queryChannelModel);
        bchList = batchService.queryAllList(queryBatchModel);
        dpList = accountDpService.queryAllList(queryDpModel);
        appList = appService.queryAllList(queryAppModell);

        model.addAttribute("tpList", tpList);
        model.addAttribute("chList", chList);
        model.addAttribute("bchList", bchList);
        model.addAttribute("dpList", dpList);
        model.addAttribute("appList", appList);
        return "manager/sgyactivation/strategyActivationAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, StrategyActivationModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setCreateUser(account.getId());
            bean.setCreateRole(account.getRoleId());
            strategyActivationService.add(bean);
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

        List<ChannelModel> chList = new ArrayList<ChannelModel>();
        ChannelModel queryChannelModel = new ChannelModel();
        queryChannelModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);

        List<BatchModel> bchList = new ArrayList<BatchModel>();
        BatchModel queryBatchModel = new BatchModel();
        queryBatchModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);

        List<AccountDpModel> dpList = new ArrayList<AccountDpModel>();
        AccountDpModel queryDpModel = new AccountDpModel();
        queryDpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);

        List<AppModel> appList = new ArrayList<AppModel>();
        AppModel queryAppModell = new AppModel();
        queryAppModell.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);

        StrategyActivationModel dataModel = new StrategyActivationModel();
        dataModel.setId(id);

        tpList = accountTpService.queryAllList(queryTpModel);
        chList = channelService.queryAllList(queryChannelModel);
        bchList = batchService.queryAllList(queryBatchModel);
        dpList = accountDpService.queryAllList(queryDpModel);
        appList = appService.queryAllList(queryAppModell);
        dataModel = strategyActivationService.queryById(dataModel);

        model.addAttribute("tpList", tpList);
        model.addAttribute("chList", chList);
        model.addAttribute("bchList", bchList);
        model.addAttribute("dpList", dpList);
        model.addAttribute("appList", appList);
        model.addAttribute("dataModel", dataModel);

        return "manager/sgyactivation/strategyActivationEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,StrategyActivationModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            strategyActivationService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, StrategyActivationModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            strategyActivationService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, StrategyActivationModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            strategyActivationService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
