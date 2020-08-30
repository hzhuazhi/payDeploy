package com.xn.tvdeploy.task;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HttpSendUtils;
import com.xn.tvdeploy.dao.OfferDDao;
import com.xn.tvdeploy.dao.OfferMDao;
import com.xn.tvdeploy.model.OfferDModel;
import com.xn.tvdeploy.model.OfferMModel;
import com.xn.tvdeploy.util.PublicMethod;
import com.xn.tvdeploy.util.call.method.ts.TsAdvertMethod;
import com.xn.tvdeploy.util.call.model.ts.res.AdsModel;
import com.xn.tvdeploy.util.call.model.ts.res.TsModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @Description:调用探索API广告接口获取广告数据
 * @create 2018-10-18 11:04
 **/
@Service
public class TsApiTask {

    private final static Logger log =Logger.getLogger(TsApiTask.class);

    @Autowired
    private OfferMDao<OfferMModel> offerMDao;

    @Autowired
    private OfferDDao<OfferDModel> offerDDao;


    /**
     *
     *<p>Title:调用探索API接口-获取广告数据</p>
     * @Description: TODO(用一句话描述该文件做什么)
     * @author yoko
     * @date 2018-5-4下午04:24:29
     */
    public void callTsApi() throws Exception{
        log.info("----------------------callTsApi----------------进来了!-startTime:"+ DateUtil.getNowPlusTimeMill());
        String adData = "";
        String url = "http://ssp.rtb.tansuotv.com/bid";
        String data = TsAdvertMethod.assembleReqApiData();
        adData = HttpSendUtils.sentPost(url, data, "UTF-8");
        if (!StringUtils.isBlank(adData)){
            TsModel tsModel = new TsModel();
            tsModel = JSON.parseObject(adData, TsModel.class);
            //根据源，以及广告ID判断是否存在广告库中
            for (AdsModel adsModel : tsModel.getAds()){
                OfferMModel offerMModel = PublicMethod.assembleQueryOfferMM(adsModel.getCid(), "SRE_TS");
                offerMModel = offerMDao.queryByCondition(offerMModel);
                if (offerMModel == null || offerMModel.getId() <= ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                    //表示不存在源广告库

                    //第三方的广告不在广告主档表中，需要把广告进行添加入库
                    OfferMModel offModel = PublicMethod.assembleAddOfferMByTs(0, adsModel, "SRE_TS", ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE);
                    //添加广告主档信息
                    offerMDao.add(offModel);
                    //添加广告详情信息
                    OfferDModel offDModel = PublicMethod.assembleOfferD(offModel.getId(), offModel.getOfferDModel());
                    offerDDao.add(offDModel);
                }else{
                    //存在
                    log.info("---------存在广告ID:"+adsModel.getCid());
                }
            }
        }
        log.info("-----------------------callTsApi---------------结束了!-endTime:"+DateUtil.getNowPlusTimeMill());

    }

}
