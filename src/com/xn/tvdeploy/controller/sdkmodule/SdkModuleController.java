package com.xn.tvdeploy.controller.sdkmodule;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.SdkModuleModel;
import com.xn.tvdeploy.service.SdkModuleService;
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
 * @Description:sdk模块的Controller层
 * @create 2018-09-14 15:34
 **/
@Controller
@RequestMapping("/sdkmodule")
public class SdkModuleController extends BaseController {
    private static Logger log = Logger.getLogger(SdkModuleController.class);

    @Autowired
    private SdkModuleService<SdkModuleModel> sdkModuleService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/sdkmodule/sdkmoduleIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, SdkModuleModel model) throws Exception {
        List<SdkModuleModel> dataList = new ArrayList<SdkModuleModel>();
        dataList = sdkModuleService.queryByList(model);
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }

    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, SdkModuleModel model) throws Exception {
        List<SdkModuleModel> dataList = new ArrayList<SdkModuleModel>();
        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        dataList = sdkModuleService.queryAllList(model);
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "manager/sdkmodule/sdkmoduleAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, SdkModuleModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setCreateUser(account.getId());
            bean.setCreateRole(account.getRoleId());
            bean.setOnlyValue(PublicMethod.assembleUUID());
            sdkModuleService.add(bean);
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
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            SdkModuleModel dataModel = new SdkModuleModel();
            dataModel.setId(id);
            model.addAttribute("dataModel", sdkModuleService.queryById(dataModel));
        }
        return "manager/sdkmodule/sdkmoduleEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,SdkModuleModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            bean.setOnlyValue(PublicMethod.assembleUUID());
            sdkModuleService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, SdkModuleModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            sdkModuleService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, SdkModuleModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUser(account.getId());
            bean.setUpdateRole(account.getRoleId());
            sdkModuleService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
