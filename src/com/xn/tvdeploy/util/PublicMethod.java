package com.xn.tvdeploy.util;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.MD5;
import com.xn.common.util.StringUtil;
import com.xn.tvdeploy.model.ChannelModel;
import com.xn.tvdeploy.model.OfferDModel;
import com.xn.tvdeploy.model.OfferMModel;
import com.xn.tvdeploy.model.TpDataInfoModel;
import com.xn.tvdeploy.util.call.model.ts.res.AdsModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author df
 * @Description: TODO(公共方法类)
 * @create 10:12 2018/9/12
 **/
public class PublicMethod{
    private static Logger log = Logger.getLogger(PublicMethod.class);

    /**
     * @Description: TODO(生成AppKey的值)
     * @author df
     * @param dpId-开发者ID
     * @create 10:30 2018/9/12
     **/
    public static String assembleAppKey(long dpId) throws Exception {
        String str = "";
        String dp = dpId+"";
        String timeMill = DateUtil.getNowPlusTimeMill();
        str = MD5.parseMD5(dp+timeMill);
        return str;
    }

    /**
     * @Description: TODO(组装出渠道号集合的所有主键ID)
     * @author df
     * @param list-渠道号数据集合
     * @create 23:33 2018/9/13
     **/
    public static List<Long> assembleChannelId(List<ChannelModel> list){
        List<Long> idList = list.stream().map(ChannelModel::getId).collect(Collectors.toList());
        return idList;
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

    /**
     * @Description: TODO(组装查询广告主档的查询条件)
     * @author df
     * @param sourceId-第三方广告的ID
     * @param sourceType-源标识类型
     * @create 14:52 2018/10/15
     **/
    public static OfferMModel assembleQueryOfferMM(String sourceId, String sourceType){
        OfferMModel resBean = new OfferMModel();
        resBean.setSourceId(sourceId);
        resBean.setSourceType(sourceType);
        return resBean;
    }

    /**
     * @Description: TODO(组装探索的广告信息，组装成我方想要的字段，进行入库)
     * @author df
     * @param apiId-Api的主键ID
     * @param adsModel-探索的广告信息
     * @param sourceType-探索的源类型：探索=SRE_TS
     * @param warehousingType-入库类型：0初始化，1work调用接口，2用户实时调用，3手动录入
     * @create 15:28 2018/10/15
     **/
    public static OfferMModel assembleAddOfferMByTs(long apiId, AdsModel adsModel, String sourceType, int warehousingType){
        OfferMModel resBean = new OfferMModel();
        resBean.setApiId(apiId);
        resBean.setSourceId(adsModel.getCid());
        resBean.setSourceType(sourceType);
        resBean.setSpreadUrl(adsModel.getAdurl());
        resBean.setPayout(adsModel.getPrice());

        if (!StringUtils.isBlank(adsModel.getAdmt())){
            if (adsModel.getAdmt().equals(1)){
                resBean.setOfferType(2);
            }else if (adsModel.getAdmt().equals(2)){
                resBean.setOfferType(3);
            }else if (adsModel.getAdmt().equals(3)){
                resBean.setOfferType(4);
            }else if (adsModel.getAdmt().equals(4)){
                resBean.setOfferType(5);
            }else if (adsModel.getAdmt().equals(5)){
                resBean.setOfferType(6);
            }else if (adsModel.getAdmt().equals(6)){
                resBean.setOfferType(7);
            }else if (adsModel.getAdmt().equals(7)){
                resBean.setOfferType(8);
            }else if (adsModel.getAdmt().equals(8)){
                resBean.setOfferType(9);
            }else{
                resBean.setOfferType(1);
            }
        }

        if (!StringUtils.isBlank(adsModel.getAdct())){
            if(adsModel.getAdct().equals(1)){
                resBean.setOfferClickType(2);
            }else if(adsModel.getAdct().equals(2)){
                resBean.setOfferClickType(3);
            }else if(adsModel.getAdct().equals(4)){
                resBean.setOfferClickType(4);
            }else if(adsModel.getAdct().equals(8)){
                resBean.setOfferClickType(5);
            }else if(adsModel.getAdct().equals(16)){
                resBean.setOfferClickType(6);
            }else if(adsModel.getAdct().equals(32)){
                resBean.setOfferClickType(7);
            }else if(adsModel.getAdct().equals(64)){
                resBean.setOfferClickType(8);
            }else{
                resBean.setOfferClickType(1);
            }
        }

        resBean.setWarehousingType(warehousingType);
        resBean.setIsHandle(ManagerConstant.PUBLIC_CONSTANT.IS_HANDLE_XYCL);
        resBean.setHandleStatus(ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE);

        OfferDModel offerDModel = new OfferDModel();
        offerDModel.setResUrl(adsModel.getAdi());
        offerDModel.setOfferJson(JSON.toJSONString(adsModel));

        resBean.setOfferDModel(offerDModel);
        return resBean;
    }


    /**
     * @Description: TODO(组装广告详情数据)
     * @author df
     * @param offerMId-广告主档的主键ID
     * @create 15:40 2018/10/15
     **/
    public static OfferDModel assembleOfferD(long offerMId, OfferDModel model){
        OfferDModel resBean = new OfferDModel();
        resBean = model;
        resBean.setId(ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO);
        resBean.setOfferMId(offerMId);
        return resBean;
    }

    /**
     * @Description: 组装渠道上报的数据-补单
     * @param tpDataInfoModel
     * @return
     * @author yoko
     * @date 2020/8/6 16:15
    */
    public static TpDataInfoModel assembleChannelData(TpDataInfoModel tpDataInfoModel) throws Exception{
        TpDataInfoModel resBean = new TpDataInfoModel();
        resBean = tpDataInfoModel;

        resBean.setId(0);
        String orderNo = DateUtil.getNowLongTime();
        resBean.setMyTradeNo(orderNo);
        if (StringUtils.isBlank(tpDataInfoModel.getOutTradeNo())){
            resBean.setOutTradeNo(orderNo);
        }
        resBean.setSubTime(DateUtil.getNowPlusTime());

        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;

    }

    public static TpDataInfoModel assembleDataCore(TpDataInfoModel tpDataInfoModel, String myTradeNo) throws Exception{
        TpDataInfoModel resBean = new TpDataInfoModel();
        resBean = tpDataInfoModel;
        resBean.setId(0);

        if (!StringUtils.isBlank(myTradeNo)){
            resBean.setMyTradeNo(myTradeNo);
        }
        resBean.setTradeNo(DateUtil.getNowLongTime());
        if (StringUtils.isBlank(resBean.getPayAmount())){
            // 表示页面没填写，说明支付金额一致
            resBean.setPayAmount(resBean.getTotalAmount());
            resBean.setPayActualMoney(resBean.getActualMoney());
            resBean.setMoneyFitType(4);
        }else {
            // 表示页面填写了实际金额：如果填写了实际金额则表示实际支付金额与订单金额不一致
            String payActualMoney = "";
            payActualMoney = StringUtil.getMultiply(resBean.getPayAmount(), resBean.getServiceCharge());
            payActualMoney = StringUtil.getBigDecimalSubtractStr(resBean.getPayAmount(), payActualMoney);
            resBean.setPayActualMoney(payActualMoney);
        }

        resBean.setTradeStatus(1);
//        resBean.setExtraReturnParam("");
        resBean.setTradeTime(DateUtil.getNowPlusTime());
        if (!StringUtils.isBlank(resBean.getExtraReturnParam())){
            resBean.setXyExtraReturnParam(resBean.getExtraReturnParam());
        }
        resBean.setReplenishType(2);

        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;
    }



    public static void main(String[] args) throws Exception {
        String str = assembleAppKey(3);
        log.info("str:"+str);
    }
}
