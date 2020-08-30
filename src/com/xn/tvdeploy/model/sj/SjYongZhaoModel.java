package com.xn.tvdeploy.model.sj;

import com.xn.common.page.BasePage;

/**
 * @author df
 * @Description:审计-yongzhao
 * @create 2018-09-22 13:43
 **/
public class SjYongZhaoModel extends BasePage {
    private long id; //账号ID
    private String corporateName;
    private String productName;
    private String unitPrice;
    private int activationNumber;
    private String profit;
    private String dataDay;
    private int curday;
    private int startCurday;
    private int endCurday;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getActivationNumber() {
        return activationNumber;
    }

    public void setActivationNumber(int activationNumber) {
        this.activationNumber = activationNumber;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getDataDay() {
        return dataDay;
    }

    public void setDataDay(String dataDay) {
        this.dataDay = dataDay;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public int getStartCurday() {
        return startCurday;
    }

    public void setStartCurday(int startCurday) {
        this.startCurday = startCurday;
    }

    public int getEndCurday() {
        return endCurday;
    }

    public void setEndCurday(int endCurday) {
        this.endCurday = endCurday;
    }
}
