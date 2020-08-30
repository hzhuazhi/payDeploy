package com.xn.tvdeploy.controller;

import com.xn.common.controller.BaseController;
import com.xn.system.util.ResourceUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

/**
 * @author df
 * @Description:测试
 * @create 2018-08-31 11:39
 **/
@Controller
@RequestMapping("/test1")
public class TestController extends BaseController {

    private static Logger log=Logger.getLogger(LandingPageController.class);


    //生成文件路径
//    private static String path = "\\temp\\";
    private static String path = "/opt/app/tomcat03/apache-tomcat-6.0.44/webapps/html/";
    //文件路径+名称
    private static String filenameTemp;

    @RequestMapping("/getData")
    public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            String paths = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+paths;
            String urlData = request.getRequestURI();
            String url = basePath+"/ldPage/getData.do";
            String sb = sendGet(url,"UTF-8",null,null);
            UUID uuid = UUID.randomUUID();
            createFile("myfile1", sb);
        }catch (Exception e){
            //#记录错误日志
//            ModelAndView vw = new ModelAndView();
//            String sendJsp = "landing_page/" + "h5";
//            vw.setViewName(sendJsp);
//            vw.addObject("dto","error");
            throw new RuntimeException();
        }
    }

    @RequestMapping("/test1")
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{



            String url = "http://118.31.70.81:8086/tvSdkServer/durian/getData?channelId=1&batchNum=batch_1&appId=1&sgyId=13";
//            String url = "http://localhost:8080/durian/getData?channelId=1&batchNum=batch_1&appId=1&sgyId=13";
//            String url = "http://localhost:8080/ldPage/getData.do?channelId=1&batchNum=batch_1&appId=1&sgyId=12";
            String sb = sendGet(url,"UTF-8",null,null);
            UUID uuid = UUID.randomUUID();
            createFile("myfile1", sb);
        }catch (Exception e){
            //#记录错误日志
//            ModelAndView vw = new ModelAndView();
//            String sendJsp = "landing_page/" + "h5";
//            vw.setViewName(sendJsp);
//            vw.addObject("dto","error");
            throw new RuntimeException();
        }
    }

    @RequestMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            String sb1 = ResourceUtil.getData("sdk.deploy.value");
//            String sb2 = ResourceUtil.getSessionInfoName();
            log.info("----------------------sb1:"+sb1);
//            log.info("----------------------sb2:"+sb2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try{
            String url = "http://localhost:8080/durian/getData?channelId=1&batchNum=batch_1&appId=1&sgyId=13";
//            String url = "http://localhost:8080/ldPage/getData.do?channelId=1&batchNum=batch_1&appId=1&sgyId=12";
            String sb = sendGet(url,"UTF-8",null,null);
            UUID uuid = UUID.randomUUID();
            createFile("myfile1", sb);
            String sb1 ="sb";
            String sb2 ="sb2";
            String sb3 = "sb3";
            String sb4 ="sb4";
            String sb5 = "sb5";
            String sb6 = "sb6";
        }catch (Exception e){

        }
    }




    /**
     * 创建文件
     * @param fileName  文件名称
     * @param filecontent   文件内容
     * @return  是否创建成功，成功则返回true
     */
    public static boolean createFile(String fileName,String filecontent){
        Boolean bool = false;
//        String fl = System.getProperty("user.home");
        filenameTemp = path+fileName+".html";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is "+filenameTemp);
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp, filecontent);
            }else{
                //如果文件存在，覆盖文件
                file.delete();
                file.createNewFile();
                bool = true;
                System.out.println("1success create file,the file is "+filenameTemp);
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,String newstr) throws IOException{
        Boolean bool = false;
        String filein = newstr+"\r\n";//新写入的行，换行
        String temp  = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 删除文件
     * @param fileName 文件名称
     * @return
     */
    public static boolean delFile(String fileName){
        Boolean bool = false;
        filenameTemp = path+fileName+".txt";
        File file  = new File(filenameTemp);
        try {
            if(file.exists()){
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bool;
    }

    public static String sendGet(String httpUrl,String encoding, Map<String, String> parameter , Map<String, String> headerMap){
        if (httpUrl == null) {
            System.out.println("");
            return null;
        }
        String urlStr = null;
        StringBuilder sb = null;
        if(parameter != null){
            sb = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = parameter.entrySet().iterator();
            while (iterator.hasNext()) {
                if (sb.length() > 0) {
                    sb.append('&');
                }
                Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value;
                try {
                    value = URLEncoder.encode(entry.getValue(), encoding);
                } catch (UnsupportedEncodingException e) {
                    value = "";
                }
                sb.append(key).append('=').append(value);
            }
        }

        if(sb != null){
            if (httpUrl.lastIndexOf('?') != -1) {
                urlStr = httpUrl + '&' + sb.toString();
            } else {
                urlStr = httpUrl + '?' + sb.toString();
            }
        }else{
            urlStr = httpUrl;
        }
        HttpURLConnection httpCon = null;
        String responseBody = null;
        try {
            URL url = new URL(urlStr);
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);
            httpCon.setRequestMethod("GET");

            if(headerMap != null ){//
                Iterator<Map.Entry<String, String>> headerIterator = headerMap.entrySet().iterator();
                while (headerIterator.hasNext()) {
                    Entry<String, String> entry = headerIterator.next();
                    //System.out.println(entry.getKey()+"--" + entry.getValue());
                    //httpCon.setRequestProperty("Authorization", "Basic ZG9vcmNhY0Bkb29ybW9iaS5jb206ZG9vcjEyMw==");
                    httpCon.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // 开始读取返回的内容
            InputStream in = httpCon.getInputStream();
            byte[] readByte = new byte[1024];
            // 读取返回的内容
            int readCount = in.read(readByte, 0, 1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (readCount != -1) {
                baos.write(readByte, 0, readCount);
                readCount = in.read(readByte, 0, 1024);
            }
            responseBody = new String(baos.toByteArray(), encoding);
            baos.close();
        } catch (Exception e) {
            System.out.println(String.format("-->Exception:%s", httpUrl));
            e.printStackTrace();
        } finally {
            if (httpCon != null)
                httpCon.disconnect();
        }
        return responseBody;
    }

}
