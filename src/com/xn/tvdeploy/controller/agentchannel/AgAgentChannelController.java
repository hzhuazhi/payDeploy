package com.xn.tvdeploy.controller.agentchannel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.accounttp.TpController;
import com.xn.tvdeploy.model.AgentChannelModel;
import com.xn.tvdeploy.model.AgentChannelModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.AgentChannelService;
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
 * @Description 代理角色查看旗下的渠道的Controller层
 * @Author yoko
 * @Date 2020/4/30 15:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/agagentchannel")
public class AgAgentChannelController extends BaseController {

    private static Logger log = Logger.getLogger(AgAgentChannelController.class);

    @Autowired
    private AgentChannelService<AgentChannelModel> agentChannelService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/agagentchannel/agagentchannelIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentChannelModel model) throws Exception {
        List<AgentChannelModel> dataList = new ArrayList<AgentChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setAgentId(account.getId());
                log.info("");
                //不是管理员，只能查询自己的数据
            }
            dataList = agentChannelService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }
}
