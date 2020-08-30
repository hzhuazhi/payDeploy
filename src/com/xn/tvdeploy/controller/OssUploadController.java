package com.xn.tvdeploy.controller;

import com.aliyun.oss.OSSClient;
import com.xn.common.controller.BaseController;
import com.xn.common.util.OssUploadUtil;
import com.xn.tvdeploy.model.OssUploadModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author df
 * @Description:服务器文件上传到OSS
 * @create 2018-09-03 15:56
 **/
@Controller
@RequestMapping("/ossUp")
public class OssUploadController extends BaseController {

    private static Logger log=Logger.getLogger(OssUploadController.class);


    @RequestMapping("/uploadFile")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, OssUploadModel model) throws Exception{
        try{
            OssUploadUtil oss = new OssUploadUtil();
            String fileAddress = oss.ossUploadFile(model.getBucketName(), model.getObjectName(), model.getLocalFile());
            sendSuccessMessage(response, "保存成功~", fileAddress);
        }catch(Exception e){
            sendFailureMessage(response, "请重新登录~");
        }
    }

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response, OssUploadModel model) throws Exception{
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
            ossClient.putObject(model.getBucketName(), model.getObjectName(), new File(model.getLocalFile()));
            // 关闭OSSClient。
            ossClient.shutdown();
            fileAddress = OssUploadUtil.assembleFileAddress(model.getBucketName(), endpoint, model.getObjectName());
            sendSuccessMessage(response, "保存成功~", fileAddress);
        }catch(Exception e){
            sendFailureMessage(response, "请重新登录~");
        }
    }

    public static void main(String[] args) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIEMzvMhhaBuHE";
        String accessKeySecret = "W2LdnHbyoqDZQDD3v2rlNMREiy2cEM";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        String bucketName = "zqtemp";
        String objectName = "test1/myfile14.html";
        String localFile = "C:/Users/THINK/temp/13.html";
        ossClient.putObject(bucketName, objectName, new File(localFile));
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
