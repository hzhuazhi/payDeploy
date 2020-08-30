package com.xn.tvdeploy.controller.withdraw;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.SendEmail;
import com.xn.common.util.SendSms;
import com.xn.common.util.StringUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.bank.BankController;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.AgentModel;
import com.xn.tvdeploy.model.BankModel;
import com.xn.tvdeploy.model.WithdrawModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.AgentService;
import com.xn.tvdeploy.service.BankService;
import com.xn.tvdeploy.service.WithdrawService;
import org.apache.commons.lang.StringUtils;
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
import java.util.UUID;

/**
 * @Description 提现-渠道
 * @Author yoko
 * @Date 2020/3/26 21:35
 * @Version 1.0
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController {

    private static Logger log = Logger.getLogger(WithdrawController.class);

    @Autowired
    private WithdrawService<WithdrawModel> withdrawService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;

    @Autowired
    private AgentService<AgentModel> agentService;

    @Autowired
    private BankService<BankModel> bankService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/withdraw/withdrawIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, WithdrawModel model) throws Exception {
        List<WithdrawModel> dataList = new ArrayList<WithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setLinkId(account.getId());
                model.setRoleId(account.getRoleId());
            }else{
                model.setRoleId(ManagerConstant.PUBLIC_CONSTANT.ROLE_TP);
            }

            dataList = withdrawService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, WithdrawModel model) throws Exception {
        List<WithdrawModel> dataList = new ArrayList<WithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setLinkId(account.getId());
                model.setRoleId(account.getRoleId());
            }else{
                model.setRoleId(ManagerConstant.PUBLIC_CONSTANT.ROLE_TP);
            }
            dataList = withdrawService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() < ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                sendFailureMessage(response,"管理员无法提现!");
            }else {
                if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                    AccountTpModel accountTpModel = new AccountTpModel();
                    accountTpModel.setId(account.getId());
                    model.addAttribute("tp", accountTpService.queryById(accountTpModel));
                }

                BankModel bankModel = new BankModel();
                bankModel.setLinkId(account.getId());
                bankModel.setRoleId(account.getRoleId());
                model.addAttribute("bank", bankService.queryAllList(bankModel));
            }
//            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/withdraw/withdrawAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, WithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() < ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                sendFailureMessage(response,"管理员无法提现!");
            }
            if (StringUtils.isBlank(bean.getMoney()) || StringUtils.isBlank(bean.getServiceCharge())){
                sendFailureMessage(response,"请填写提现金额!");
            }
            if (bean.getMoney().indexOf("-") > -1 || bean.getServiceCharge().indexOf("-") > -1){
                sendFailureMessage(response,"错误,请重试!");
            }
            boolean flag = false;
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                AccountTpModel accountTpModel = new AccountTpModel();
                accountTpModel.setId(account.getId());
                accountTpModel = accountTpService.queryById(accountTpModel);
                String totalMoney = StringUtil.getBigDecimalAdd(bean.getMoney(), bean.getServiceCharge());
                flag = StringUtil.getBigDecimalSubtract(accountTpModel.getBalance(), totalMoney);
            }
            if (flag){
                bean.setLinkId(account.getId());
                bean.setRoleId(account.getRoleId());
                withdrawService.add(bean);
//                SendSms.aliSendSms("15967171415", "8888");
                String  random = UUID.randomUUID().toString().replaceAll("\\-", "");
                String content = random + "渠道：" + account.getAccountNum() + "，嘿嘿：" +  bean.getMoney();
                SendEmail.sendEmail("提现审核", content);
                sendSuccessMessage(response, "保存成功~");
            }else {
                sendFailureMessage(response,"提现金额超出余额!");
            }

        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        WithdrawModel atModel = new WithdrawModel();
        atModel.setId(id);
        model.addAttribute("account", withdrawService.queryById(atModel));
        return "manager/withdraw/withdrawEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,WithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendSuccessMessage(response, "保存失败~");
            }else{
                withdrawService.update(bean);
                sendSuccessMessage(response, "保存成功~");
            }

        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, WithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            withdrawService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, WithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            withdrawService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
