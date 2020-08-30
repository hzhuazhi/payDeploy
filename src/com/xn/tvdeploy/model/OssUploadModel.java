package com.xn.tvdeploy.model;

/**
 * @author df
 * @Description:OSS上传的实体属性Bean
 * @create 2018-09-03 16:59
 **/
public class OssUploadModel {
    /**
     * 阿里云的bucketName的值
     */
    private String bucketName;

    /**
     * 需要把文件名称修改成某某/以及要存放到那个文件夹下面
     */
    private String objectName;

    /**
     * 文件存放在服务器上的路径地址
     */
    private String localFile;

    /**
     * 最终上传到OSS上的文件的下载URL地址
     */
    private String fileAddress;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getLocalFile() {
        return localFile;
    }

    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }
}
