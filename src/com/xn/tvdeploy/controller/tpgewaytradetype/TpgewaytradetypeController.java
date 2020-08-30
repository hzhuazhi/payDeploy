package com.xn.tvdeploy.controller.tpgewaytradetype;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.gewaytradetype.GewaytradetypeController;
import com.xn.tvdeploy.model.ChannelgewayModel;
import com.xn.tvdeploy.model.GewayModel;
import com.xn.tvdeploy.model.GewaytradetypeModel;
import com.xn.tvdeploy.service.ChannelgewayService;
import com.xn.tvdeploy.service.GewayService;
import com.xn.tvdeploy.service.GewaytradetypeService;
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
 * @Description 渠道所对应的支付类型的Controller层
 * @Author yoko
 * @Date 2020/4/1 15:53
 * @Version 1.0
 */
@Controller
@RequestMapping("/tpgewaytradetype")
public class TpgewaytradetypeController extends BaseController {

    private static Logger log = Logger.getLogger(GewaytradetypeController.class);

    @Autowired
    private GewaytradetypeService<GewaytradetypeModel> gewaytradetypeService;

    @Autowired
    private ChannelgewayService<ChannelgewayModel> channelgewayService;



    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/tpgewaytradetype/tpgewaytradetypeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, GewaytradetypeModel model) throws Exception {
        List<GewaytradetypeModel> dataList = new ArrayList<GewaytradetypeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        String serviceCharge = "";
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                //不是管理员，只能查看自己的数据
                ChannelgewayModel channelgewayModel = new ChannelgewayModel();
                channelgewayModel.setChannelId(account.getId());
                ChannelgewayModel channelgewayData = channelgewayService.queryByCondition(channelgewayModel);
                if (channelgewayData == null){
                    sendFailureMessage(response,"错误,请重试!");
                }
                if (!StringUtils.isBlank(channelgewayData.getServiceCharge())){
                    serviceCharge = channelgewayData.getServiceCharge();
                }
                model.setGewayId(channelgewayData.getGewayId());
                List<GewaytradetypeModel> list = new ArrayList<>();
                list = gewaytradetypeService.queryByList(model);
                if (list != null && !StringUtils.isBlank(serviceCharge)){
                    for (GewaytradetypeModel dataModel : list){
                        dataModel.setMyServiceCharge(serviceCharge);
                        dataList.add(dataModel);
                    }
                }
            }

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



}
