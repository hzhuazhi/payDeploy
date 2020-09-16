package com.xn.tvdeploy.controller.tpdatainfo;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.TpDataInfoModel;
import com.xn.tvdeploy.service.TpDataInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 渠道订单信息-详情
 * @Author yoko
 * @Date 2020/4/7 17:45
 * @Version 1.0
 */
@Controller
@RequestMapping("/tpDataInfo")
public class TpDataInfoController extends BaseController {

    private static Logger log = Logger.getLogger(TpDataInfoController.class);

    @Autowired
    private TpDataInfoService<TpDataInfoModel> tpDataInfoService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/tpDataInfo/tpDataInfoIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, TpDataInfoModel model) throws Exception {

//        if (model.getTradeStatus() >= 0 || model.getRunStatus() > 0 || model.getReplenishType() > 0){
//            model.setOtherStatus(1);
//        }
//        if (model.getTradeStatus() == -1){
//            model.setTradeStatus(0);
//        }
        List<TpDataInfoModel> dataList = new ArrayList<TpDataInfoModel>();
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
//            bean.addAttribute("total", tpDataInfoService.getTotalData(model));
            dataList = tpDataInfoService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }





    /**
     * 重发
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, TpDataInfoModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                bean.setChannelId(account.getId());
            }
            tpDataInfoService.manyOperation(bean);
            sendSuccessMessage(response, "更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, TpDataInfoModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, TpDataInfoModel model) {
        if (model.getTradeStatus() == 0){
            model.setOtherStatus(1);
        }
        if (model.getTradeStatus() == -1){
            model.setTradeStatus(0);
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                model.setChannelId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            List<TpDataInfoModel> dataList = new ArrayList<TpDataInfoModel>();
            dataList = tpDataInfoService.queryAllList(model);
            // 导出数据
            String[] titles = new String[10];
            String[] titleCode = new String[10];
            String filename = "订单信息";
            titles = new String[]{"平台订单", "商家订单", "订单金额", "手续费", "实际金额", "实际支付金额", "请求状态", "交易状态", "交易时间","回传参数","补单类型","同步状态"};
            titleCode = new String[]{"myTradeNo", "outTradeNo", "totalAmount", "serviceCharge", "actualMoney", "payAmount", "sendOkStr", "tradeStatusStr", "tradeTime", "extraReturnParam", "replenishTypeStr","runStatusStr"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(TpDataInfoModel paramO : dataList){

                if (paramO.getSendOk() == 1){
                    paramO.setSendOkStr("成功");
                }else if (paramO.getSendOk() == 2){
                    paramO.setSendOkStr("失败");
                }
                if (paramO.getTradeStatus() == 0){
                    paramO.setTradeStatusStr("初始化");
                }else if (paramO.getTradeStatus() == 1){
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
                if (paramO.getReplenishType() == 0){
                    paramO.setReplenishTypeStr("不是补单");
                }else if (paramO.getReplenishType() == 1){
                    paramO.setReplenishTypeStr("不是补单");
                }else if (paramO.getReplenishType() == 2){
                    paramO.setReplenishTypeStr("是补单");
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
    public void totalData(HttpServletRequest request, HttpServletResponse response, TpDataInfoModel model) throws Exception {
        TpDataInfoModel data = new TpDataInfoModel();
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
//            bean.addAttribute("total", tpDataInfoService.getTotalData(model));
//            dataList = tpDataInfoService.queryByList(model);
            data = tpDataInfoService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }
}
