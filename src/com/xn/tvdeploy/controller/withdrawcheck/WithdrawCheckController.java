package com.xn.tvdeploy.controller.withdrawcheck;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.StringUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.bank.BankController;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.BankModel;
import com.xn.tvdeploy.model.WithdrawModel;
import com.xn.tvdeploy.service.AccountTpService;
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

/**
 * @Description 提现审核-渠道
 * @Author yoko
 * @Date 2020/3/27 14:07
 * @Version 1.0
 */
@Controller
@RequestMapping("/withdrawcheck")
public class WithdrawCheckController extends BaseController {

    private static Logger log = Logger.getLogger(WithdrawCheckController.class);

    @Autowired
    private WithdrawService<WithdrawModel> withdrawService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;

    @Autowired
    private BankService<BankModel> bankService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/withdrawcheck/withdrawcheckIndex";
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
                sendFailureMessage(response,"不是管理员,无法查看!");
            }
            model.setRoleId(ManagerConstant.PUBLIC_CONSTANT.ROLE_TP);
            model.setRunStatus(3);
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
                sendFailureMessage(response,"不是管理员,无法查看!");
            }
            model.setRoleId(ManagerConstant.PUBLIC_CONSTANT.ROLE_TP);
            dataList = withdrawService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }





    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        WithdrawModel atModel = new WithdrawModel();
        atModel.setId(id);
        model.addAttribute("account", withdrawService.queryById(atModel));
        return "manager/withdrawcheck/withdrawcheckEdit";
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
                WithdrawModel model = new WithdrawModel();
                model.setId(bean.getId());
                model.setWithdrawStatus(bean.getWithdrawStatus());
                if (!StringUtils.isBlank(bean.getWithdrawExplain())){
                    model.setWithdrawExplain(bean.getWithdrawExplain());
                }
                withdrawService.update(model);
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
