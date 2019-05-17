/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LoadingVerificationCodeUtil {
    public static final char[] CHARS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static String strCode = null;
    public static Random random = new Random();

    //返回某颜色的反色
    public static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }

    //创建图片
    public static BufferedImage createImage() {
        String randomString = getRandomString();//获取随机字符串

        int width = 80;//设置图片的宽度
        int height = 30;//高度

        Color color = new Color(246, 246, 246);//获取随机颜色，作为背景色
        Color reverse = new Color(255, 69, 0);//获取反色，用于前景色
        //创建一个彩色图片
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g = image.createGraphics();
        g.setFont(new Font("SansSerif", 1, 23));//设置字体

        g.setColor(color);//设置颜色

        g.fillRect(0, 0, width, height);//绘制背景
        g.setColor(reverse);//设置颜色
        g.drawString(randomString, 5, 23);
        //最多绘制100个噪音点
        int i = 0;
        for (int n = random.nextInt(10); i < n; i++) {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }
        //返回验证码图片的流格式
//		ByteArrayInputStream bais = convertImageToStream(image);

        return image;
    }

    //获取四位随机数
    public static String getRandomString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        strCode = buffer.toString();
        //System.out.println("4位随机验证码：" + strCode);
        return strCode;
    }

    public static BufferedImage createImage(String randomStr) {
        String randomString = randomStr;//获取随机字符串

        int width = 80;//设置图片的宽度
        int height = 30;//高度

        Color color = getRandomColor();//获取随机颜色，作为背景色
        Color reverse = new Color(246, 246, 246);//获取反色，用于前景色
        while (color.getRGB() == reverse.getRGB()) {
            color = getRandomColor();
        }
        //创建一个彩色图片
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics2D g = image.createGraphics();
        g.setFont(new Font("SansSerif", 1, 23));//设置字体

        g.setColor(reverse);//设置颜色

        g.fillRect(0, 0, width, height);//绘制背景
        g.setColor(color);//设置颜色
        g.drawString(randomString, 5, 23);
        //最多绘制100个噪音点
        int i = 0;
        for (int n = random.nextInt(10); i < n; i++) {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }
        //返回验证码图片的流格式
//		ByteArrayInputStream bais = convertImageToStream(image);

        return image;
    }

    //获取随机颜色
    public static Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    //将BufferedImage转换成ByteArrayInputStream
    private static void convertImageToStream(BufferedImage image, HttpServletResponse response) {

        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
