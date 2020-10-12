package com.xn.common.util;

import com.aliyun.oss.OSSClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author df
 * @Description:服务器本地文件上传到OSS
 * @create 2018-09-03 16:43
 **/
public class OssUploadUtil {

    public static String bucketName = "fruit-file";

    public static String endpoint = "img/";

    /**
     * @Description: TODO
     * @param bucketName
     * @param objectName
     * @param localFile - 文件的路径
     * @return
     * @author yoko
     * @date 2020/9/27 18:37
     */
    public static String ossUploadFile(String bucketName, String objectName, String localFile) throws Exception{
        String fileAddress = "";
        try{
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = "LTAI4GBhpufFHev3nK9gCDdp";
            String accessKeySecret = "zKgpyn1WFeCzCnYpW4sHkxE45WCDyo";
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



    /**
     * @Description: 使用file上传
     * @param bucketName
     * @param objectName
     * @param file - 文件的流
     * @return
     * @author yoko
     * @date 2020/9/27 18:37
     */
    public static String ossUploadFile(String bucketName, String objectName, File file) throws Exception{
        String fileAddress = "";
        try{
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
            String accessKeyId = "LTAI4GBhpufFHev3nK9gCDdp";
            String accessKeySecret = "zKgpyn1WFeCzCnYpW4sHkxE45WCDyo";
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
//            String bucketName = "zqtemp";
//            String objectName = "test1/myfile13.html";
//            String localFile = "C:/Users/THINK/temp/13.html";
            ossClient.putObject(bucketName, objectName, file);
            // 关闭OSSClient。
            ossClient.shutdown();
            fileAddress = assembleFileAddress(bucketName, endpoint, objectName);
        }catch (Exception e){
            throw e;
        }
        return fileAddress;
    }




    /**
     * @Description: MultipartFile 转 File
     * @param file
     * @return
     * @author yoko
     * @date 2020/9/28 10:45
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * @Description: 处理文件流
     * @param ins
     * @return
     * @author yoko
     * @date 2020/9/28 10:46
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description: 本地调用上传文件到阿里云的方法
     * <p>
     *     本地调用封装的方法：返回文件地址
     * </p>
     * @param files
     * @return
     * @author yoko
     * @date 2020/9/28 10:57
     */
    public static String localMethod(MultipartFile files) throws Exception{
        File file = OssUploadUtil.multipartFileToFile(files);
        String suffix = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf(".") + 1);
        String imageName = UUID.randomUUID().toString().replaceAll("\\-", "") + "." + suffix;
        String objectName = endpoint + imageName;
        String str = OssUploadUtil.ossUploadFile(bucketName, objectName, file);
        return str;
    }

}
