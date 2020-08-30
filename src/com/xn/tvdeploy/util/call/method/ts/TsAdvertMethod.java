package com.xn.tvdeploy.util.call.method.ts;

import com.alibaba.fastjson.JSON;
import com.xn.tvdeploy.util.call.model.ts.req.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author df
 * @Description:探索的所有处理的方法
 * @create 2018-10-15 11:11
 **/
public class TsAdvertMethod {

    /**
     * @Description: TODO(组装请求探索API的参数字段)
     * @author df
     * @create 11:46 2018/10/15
     **/
    public static String assembleReqApiData(){
        String str = "";
        SspModel sspModel = assembleSsp();
        str = JSON.toJSONString(sspModel);
        return str;
    }


    public static SspModel assembleSsp(){
        String uuid = assembleUUID();
        SspModel sspModel = new SspModel();
        sspModel.setId(uuid);

        AppModel app = new AppModel();
        app.setId("59386");
        app.setName("test");
//		app.setDomain("");
//		app.setCat("20410");
//		app.setVer("1.1.1");
        app.setBundle("test.package");
//		app.setPaid(0);
        sspModel.setApp(app);

        DeviceModel device = new DeviceModel();
        device.setIp("123.125.71.38");
//		device.setAndt(0);
        device.setDid("ac:35:ee:5d:ef:3f");
        device.setMac("ac:35:ee:5d:ef:3f");
//		device.setCarrier("00000");
//		device.setMake("小米");
//		device.setModel("小米电视4S");
        device.setOs("Android");
//		device.setOsv("4.4.4");
        device.setConnectiontype(0);
        device.setDevicetype(6);
//		device.setS_density(240);
        device.setSw(1080);
        device.setSh(1920);
        device.setOrientation(1);
//		device.setUa("Mozilla/5.0 (Windows NT 6.2; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
        sspModel.setDevice(device);

        List<ImpModel> imList = new ArrayList<ImpModel>();
        String uuid1 = assembleUUID();
        ImpModel imp = new ImpModel();
        imp.setId(uuid1);
        imp.setBidfloorcur("CNY");
        imp.setInstl("4");
//		imp.setTpl("1");
        imp.setBidfloor(1);

        BannerModel banner = new BannerModel();
        banner.setW(1280);
        banner.setH(720);
        imp.setBanner(banner);

        imList.add(imp);

        sspModel.setImp(imList);


        return sspModel;
    }

    /**
     * @Description: TODO(生成UUID)
     * @author df
     * @create 16:56 2018/9/18
     **/
    public static String assembleUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
