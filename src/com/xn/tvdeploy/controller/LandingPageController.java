package com.xn.tvdeploy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xn.common.controller.BaseController;
import com.xn.system.entity.Module;
import com.xn.system.model.ModuleModel;
import com.xn.system.service.ModuleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author df
 * @Description:落地页的Controller层
 * @create 2018-08-31 11:18
 **/
@Controller
@RequestMapping("/ldPage")
public class LandingPageController extends BaseController {


    private static Logger log=Logger.getLogger(LandingPageController.class);

    @Autowired
    private ModuleService<Module> moduleService;

    @RequestMapping("/getData")
    public ModelAndView getData(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            ModelAndView vw = new ModelAndView();
            String sendJsp = "landing_page/" +"h5";
            vw.setViewName(sendJsp);
            ModuleModel model = new ModuleModel();
            model.setLevel(2);
            model.setType(0);
            List<Module> modules = moduleService.getParentModule(model);
            String data = JSON.toJSONString(modules, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
            vw.addObject("dto",data);
            return vw;
        }catch (Exception e){
            //#记录错误日志
//            ModelAndView vw = new ModelAndView();
//            String sendJsp = "landing_page/" + "h5";
//            vw.setViewName(sendJsp);
//            vw.addObject("dto","error");
            throw new RuntimeException();
        }
    }
}
