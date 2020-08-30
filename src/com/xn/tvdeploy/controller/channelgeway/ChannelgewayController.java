package com.xn.tvdeploy.controller.channelgeway;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.gewaytradetype.GewaytradetypeController;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.ChannelgewayModel;
import com.xn.tvdeploy.model.GewayModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.ChannelgewayService;
import com.xn.tvdeploy.service.GewayService;
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
 * @Description 渠道与通道的关联关系的Controller层
 * @Author yoko
 * @Date 2020/4/1 16:00
 * @Version 1.0
 */
@Controller
@RequestMapping("/channelgeway")
public class ChannelgewayController extends BaseController {

    private static Logger log = Logger.getLogger(ChannelgewayController.class);

    @Autowired
    private ChannelgewayService<ChannelgewayModel> channelgewayService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;

    @Autowired
    private GewayService<GewayModel> gewayService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelgeway/channelgewayIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelgewayModel model) throws Exception {
        List<ChannelgewayModel> dataList = new ArrayList<ChannelgewayModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，不能操作
//                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = channelgewayService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelgewayModel model) throws Exception {
        List<ChannelgewayModel> dataList = new ArrayList<ChannelgewayModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，不能操作
//                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
                HtmlUtil.writerJson(response, dataList);
            }
            dataList = channelgewayService.queryAllList(model);
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
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
            }else {
                model.addAttribute("tp", accountTpService.queryAllList(new AccountTpModel()));
                model.addAttribute("geway", gewayService.queryAllList(new GewayModel()));
            }
//            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/channelgeway/channelgewayAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelgewayModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelgewayService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        ChannelgewayModel atModel = new ChannelgewayModel();
        atModel.setId(id);
        model.addAttribute("account", channelgewayService.queryById(atModel));
        model.addAttribute("tp", accountTpService.queryAllList());
        model.addAttribute("geway", gewayService.queryAllList());
        return "manager/channelgeway/channelgewayEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ChannelgewayModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelgewayService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, ChannelgewayModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelgewayService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelgewayModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelgewayService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
