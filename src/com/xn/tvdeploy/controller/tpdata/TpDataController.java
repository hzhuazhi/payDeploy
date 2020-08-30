package com.xn.tvdeploy.controller.tpdata;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.enums.ManagerEnum;
import com.xn.common.util.*;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.TpDataModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.TpDataService;
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
 * @Description 渠道订单信息
 * @Author yoko
 * @Date 2020/3/26 15:42
 * @Version 1.0
 */
@Controller
@RequestMapping("/tpdata")
public class TpDataController extends BaseController {

    private static Logger log = Logger.getLogger(TpDataController.class);

    @Autowired
    private TpDataService<TpDataModel> tpDataService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/tpdata/tpdataIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, TpDataModel model) throws Exception {
        List<TpDataModel> dataList = new ArrayList<TpDataModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
//            bean.addAttribute("total", tpDataService.getTotalData(model));
            dataList = tpDataService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }





    /**
     * 重发
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, TpDataModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                bean.setChannelId(account.getId());
            }
            tpDataService.manyOperation(bean);
            sendSuccessMessage(response, "更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, TpDataModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, TpDataModel model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            List<TpDataModel> dataList = new ArrayList<TpDataModel>();
            dataList = tpDataService.queryAllList(model);
            // 导出数据
            String[] titles = new String[9];
            String[] titleCode = new String[9];
            String filename = "订单信息";
            titles = new String[]{"平台订单", "商家订单", "订单金额", "手续费", "实际金额", "交易状态", "交易时间","回传参数","同步状态"};
            titleCode = new String[]{"myTradeNo", "outTradeNo", "totalAmount", "serviceCharge", "actualMoney", "tradeStatusStr", "tradeTime", "xyExtraReturnParam", "runStatusStr"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(TpDataModel paramO : dataList){
                if (paramO.getTradeStatus() == 1){
                    paramO.setTradeStatusStr("成功");
                }else if (paramO.getTradeStatus() == 2){
                    paramO.setTradeStatusStr("失败");
                }else if (paramO.getTradeStatus() == 3){
                    paramO.setTradeStatusStr("其它");
                }
                if (paramO.getRunStatus() == 0){
                    paramO.setRunStatusStr("初始化");
                }else if (paramO.getRunStatus() == 1){
                    paramO.setRunStatusStr("锁定");
                }else if (paramO.getRunStatus() == 2){
                    paramO.setRunStatusStr("失败");
                }else if (paramO.getRunStatus() == 3){
                    paramO.setRunStatusStr("成功");
                }
                Map<String,Object> map = BeanUtils.transBeanToMap(paramO);
                paramList.add(map);
            }
            ExportData.exportExcel(paramList, titles, titleCode, filename, response);
        }
    }

    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, TpDataModel model) throws Exception {
        TpDataModel data = new TpDataModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
//            bean.addAttribute("total", tpDataService.getTotalData(model));
//            dataList = tpDataService.queryByList(model);
            data = tpDataService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }
}
