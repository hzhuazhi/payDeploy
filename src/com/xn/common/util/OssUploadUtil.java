package com.xn.common.util;

import com.aliyun.oss.OSSClient;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * @author df
 * @Description:服务器本地文件上传到OSS
 * @create 2018-09-03 16:43
 **/
public class OssUploadUtil {

    public String ossUploadFile(String bucketName, String objectName, String localFile) throws Exception{
        String fileAddress = "";
        try{
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = "LTAIEMzvMhhaBuHE";
            String accessKeySecret = "W2LdnHbyoqDZQDD3v2rlNMREiy2cEM";
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
//            String bucketName = "zqtemp";
//            String objectName = "test1/myfile13.html";
//            String localFile = "C:/Users/THINK/temp/13.html";
            ossClient.putObject(bucketName, objectName, new File(localFile));
            // 关闭OSSClient。
            ossClient.shutdown();
            fileAddress = assembleFileAddress(bucketName, endpoint, objectName);
        }catch (Exception e){
            throw e;
        }
        return fileAddress;
    }

    public static String assembleFileAddress(String bucketName, String endpoint, String objectName){
        String str = "";
        if (!StringUtils.isBlank(bucketName) && !StringUtils.isBlank(endpoint) && !StringUtils.isBlank(objectName)){
            if (endpoint.indexOf("https://") > -1){
                String [] fg_endpoint = endpoint.split("https://");
                fg_endpoint[0] = "https://";
                str = fg_endpoint[0] + bucketName + "." + fg_endpoint[1] + "/" + objectName;
            }
        }
        return str;
    }

}
