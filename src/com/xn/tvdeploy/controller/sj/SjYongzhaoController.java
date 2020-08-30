package com.xn.tvdeploy.controller.sj;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.app.AppController;
import com.xn.tvdeploy.model.AccountDpModel;
import com.xn.tvdeploy.model.AppModel;
import com.xn.tvdeploy.model.sj.SjYongZhaoModel;
import com.xn.tvdeploy.service.AppService;
import com.xn.tvdeploy.service.SjYongzhaoService;
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
 * @author df
 * @Description:审计-yongzhao
 * @create 2018-09-22 12:25
 **/
@Controller
@RequestMapping("/sj-yzhao")
public class SjYongzhaoController extends BaseController {
    private static Logger log = Logger.getLogger(AppController.class);

    @Autowired
    private SjYongzhaoService<SjYongZhaoModel> sjYongzhaoService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/sj/yzhao";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, SjYongZhaoModel model) throws Exception {
        SjYongZhaoModel queryDpModel = new SjYongZhaoModel();
        List<SjYongZhaoModel> dataList = new ArrayList<SjYongZhaoModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
//                model.setDpId(account.getId());
            }
            model.setCurday(DateUtil.getIntYesterday());
            dataList = sjYongzhaoService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


}
