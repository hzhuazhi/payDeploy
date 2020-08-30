package com.xn.tvdeploy.controller.ch;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountDpModel;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.ChannelModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.ChannelService;
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
 * @Description:渠道的Controller层
 * @create 2018-09-13 13:35
 **/
@Controller
@RequestMapping("/ch")
public class ChannelController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelController.class);

    @Autowired
    private ChannelService<ChannelModel> channelService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;//渠道账号


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/ch/channelIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelModel model) throws Exception {
        AccountDpModel queryDpModel = new AccountDpModel();
        queryDpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        List<ChannelModel> dataList = new ArrayList<ChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setTpId(account.getId());
            }
            dataList = channelService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }

    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelModel model) throws Exception {
        List<ChannelModel> dataList = new ArrayList<ChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setTpId(account.getId());
            }
            model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
            dataList = channelService.queryAllList(model);
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
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                queryTpModel.setId(account.getId());
            }
            tpList = accountTpService.queryAllList(queryTpModel);
        }
        model.addAttribute("tpList", tpList);
        return "manager/ch/channelAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setCreateUser(account.getId());
            bean.setCreateRole(account.getRoleId());
            channelService.add(bean);
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
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                queryTpModel.setId(account.getId());
            }
            tpList = accountTpService.queryAllList(queryTpModel);
            ChannelModel dataModel = new ChannelModel();
            dataModel.setId(id);
            model.addAttribute("tpList", tpList);
            model.addAttribute("channelModel", channelService.queryById(dataModel));
        }
        return "manager/ch/channelEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            channelService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, ChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            channelService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            channelService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
