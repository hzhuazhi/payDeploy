package com.xn.tvdeploy.controller;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Description TODO
 * @Author yoko
 * @Date 2020/8/18 19:30
 * @Version 1.0
 */
public class TempController {

    public static String decode(String qrcodePicfilePath) {
        System.out.println("开始解析二维码！！");
        /* 读取二维码图像数据 */
        File imageFile = new File(qrcodePicfilePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("读取二维码图片失败： " + e.getMessage());
            return null;
        }
        /* 解析二维码 */
        QRCodeDecoder decoder = new QRCodeDecoder();
        String decodedData = new String(
                decoder.decode(new J2SEImageGucas(image)));
        System.out.println("解析内容如下：" + decodedData);
        return decodedData;
    }

    public static void main(String[] args) {

            decode("D:/test/mp_.png");
    }

    public static class J2SEImageGucas implements QRCodeImage {
        BufferedImage image;

        public J2SEImageGucas(BufferedImage image) {
            this.image = image;
        }

        public int getWidth() {
            return image.getWidth();
        }

        public int getHeight() {
            return image.getHeight();
        }

        public int getPixel(int x, int y) {
            return image.getRGB(x, y);
        }
    }

}
