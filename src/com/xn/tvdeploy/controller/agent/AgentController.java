package com.xn.tvdeploy.controller.agent;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.accounttp.TpController;
import com.xn.tvdeploy.model.AgentDataModel;
import com.xn.tvdeploy.model.AgentModel;
import com.xn.tvdeploy.model.AgentModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.AgentDataService;
import com.xn.tvdeploy.service.AgentService;
import org.apache.commons.lang.StringUtils;
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

/**
 * @Description 代理的Controller层
 * @Author yoko
 * @Date 2020/4/30 15:29
 * @Version 1.0
 */
@Controller
@RequestMapping("/agent")
public class AgentController extends BaseController {

    private static Logger log = Logger.getLogger(AgentController.class);

    @Autowired
    private AgentService<AgentModel> agentService;

    @Autowired
    private AgentDataService<AgentDataModel> agentDataService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/agent/agentIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentModel model) throws Exception {
        List<AgentModel> dataList = new ArrayList<AgentModel>();
        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setId(account.getId());
                log.info("");
                //不是管理员，只能查询自己的数据
            }
            List<AgentModel> resList = new ArrayList<AgentModel>();
            resList = agentService.queryByList(model);
            if (resList != null && resList.size() > 0){
                for (AgentModel agentModel : resList){
                    // 查询今日收益
                    AgentDataModel agentDataQuery = new AgentDataModel();
                    agentDataQuery.setCurdayStart(DateUtil.getDayNumber(new Date()));
                    agentDataQuery.setCurdayEnd(DateUtil.getDayNumber(new Date()));
                    agentDataQuery.setAgentId(agentModel.getId());
                    AgentDataModel agentDataModel = agentDataService.getTotalData(agentDataQuery);
                    if (agentDataModel != null){
                        if (!StringUtils.isBlank(agentDataModel.getTotalProfit())){
                            agentModel.setTodayProfit(agentDataModel.getTotalProfit());
                        }
                    }
                    dataList.add(agentModel);
                }

            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }
}
