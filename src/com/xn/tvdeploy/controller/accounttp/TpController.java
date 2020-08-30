package com.xn.tvdeploy.controller.accounttp;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.service.AccountTpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 渠道基本信息
 * @Author yoko
 * @Date 2020/3/26 19:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/tp")
public class TpController extends BaseController {

    private static Logger log = Logger.getLogger(TpController.class);

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/tp/tpIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AccountTpModel model) throws Exception {
        List<AccountTpModel> dataList = new ArrayList<AccountTpModel>();
        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setId(account.getId());
                log.info("");
                //不是管理员，只能查询自己的数据
            }
            dataList = accountTpService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }
}
