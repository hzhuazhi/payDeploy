package com.xn.tvdeploy.controller.util;

import com.xn.common.controller.BaseController;
import com.xn.common.util.OssUploadUtil;
import com.xn.common.util.QiniuCloudUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * @Description 文件上传的Controller层
 * @Author yoko
 * @Date 2020/9/27 16:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

    private static Logger log = Logger.getLogger(UploadController.class);

    public static String qiniuUrl = "http://gtpqn.tiaocheng-tech.com/";

    public static String bucketName = "fruit-file";

    public static String endpoint = "img/";

    /**
     * @Description: 七牛云图片上传
     * <p>
     *     上传图片成功之后，返回图片地址
     * </p>
     * @param files
     * @author yoko
     * @date 2020/9/27 16:06
    */
    @RequestMapping("/qiniuUpload")
    public void qiniuUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files) throws Exception {
        try{
            if (files == null){
                return;
            }
            String suffix = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf(".") + 1);
            byte[] bytes = files.getBytes();
            String imageName = UUID.randomUUID().toString().replaceAll("\\-", "") + "." + suffix;
            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            String resStr = qiniuUtil.put64image(bytes, imageName);
            if (StringUtils.isBlank(resStr)){
                return;
            }
            String str = qiniuUrl + resStr;
            // 返回数据给客户端
            PrintWriter out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
            log.error(String.format("this UploadController.qiniuUpload() is error !"));
        }

        return;
    }


    /**
     * @Description: 阿里云图片上传
     * <p>
     *     上传成功之后，返回图片地址
     * </p>
     * @return
     * @author yoko
     * @date 2020/9/28 10:51
    */
    @RequestMapping("/ossUpload")
    public void ossUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files) throws Exception{
        try{
            if (files == null){
                return;
            }
            File file = OssUploadUtil.multipartFileToFile(files);
            String suffix = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf(".") + 1);
//            byte[] bytes = file.getBytes();
            String imageName = UUID.randomUUID().toString().replaceAll("\\-", "") + "." + suffix;
            String objectName = endpoint + imageName;
            String str = OssUploadUtil.ossUploadFile(bucketName, objectName, file);
            // 返回数据给客户端
            PrintWriter out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("this UploadController.ossUpload() is error !"));
        }
    }
}
