package com.xn.tvdeploy.util.call.model.ts.res;

import java.util.List;

/**
 * @author df
 * @Description:探索返回广告的原生广告素材
 * @create 2018-10-15 14:22
 **/
public class NativeModel {
    /**
     * 模板编号
     */
    private String templateNumber;

    /**
     * 广告跳转地址
     */
    private String link;

    /**
     * 标题
     */
    private List<Object> titleBean;

    /**
     *文本
     */
    private String text;

    /**
     * 字体大小
     */
    private float textSize;

    /**
     * 字体颜色
     */
    private Integer textColor;

    /**
     * 描述
     */
    private List<Object> descriptionBean;

    /**
     * 图标
     */
    private List<Object> iconBean;

    /**
     * 单张大图
     */
    private List<Object> imageBean;

    /**
     * 多张小图
     */
    private List<Object> listImagesBean;

    /**
     * 标题元素
     */
    private String title;

    /**
     * 图片元素
     */
    private List<Object> listImages;

    /**
     * 6.返回动作类型（1 打开连接 2拨打电话 ）
     */
    private Integer actionType;

    /**
     * 图标元素
     */
    private String iconUrl;

    /**
     * 来源
     */
    private String author;

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Object> getTitleBean() {
        return titleBean;
    }

    public void setTitleBean(List<Object> titleBean) {
        this.titleBean = titleBean;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public Integer getTextColor() {
        return textColor;
    }

    public void setTextColor(Integer textColor) {
        this.textColor = textColor;
    }

    public List<Object> getDescriptionBean() {
        return descriptionBean;
    }

    public void setDescriptionBean(List<Object> descriptionBean) {
        this.descriptionBean = descriptionBean;
    }

    public List<Object> getIconBean() {
        return iconBean;
    }

    public void setIconBean(List<Object> iconBean) {
        this.iconBean = iconBean;
    }

    public List<Object> getImageBean() {
        return imageBean;
    }

    public void setImageBean(List<Object> imageBean) {
        this.imageBean = imageBean;
    }

    public List<Object> getListImagesBean() {
        return listImagesBean;
    }

    public void setListImagesBean(List<Object> listImagesBean) {
        this.listImagesBean = listImagesBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getListImages() {
        return listImages;
    }

    public void setListImages(List<Object> listImages) {
        this.listImages = listImages;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
