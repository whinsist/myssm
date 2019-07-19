package cn.com.cloudstar.rightcloud.system.activiti.image;

import org.activiti.image.util.ReflectUtil;

import java.awt.Font;
import java.io.File;
import java.net.URL;

/**
 * 动态字体（用于linux和docker容器里面没有中文字体）
 * @author yuz
 * @date 2018/8/21
 */
public class DynamicFonts {
    private Font ACT_FONT = null;
    private Font LABEL_FONT = null;
    private Font ANNOTATION_FONT = null;

    public Font getACT_FONT() {
        return ACT_FONT;
    }

    public Font getLABEL_FONT() {
        return LABEL_FONT;
    }

    public Font getANNOTATION_FONT() {
        return ANNOTATION_FONT;
    }

    private static DynamicFonts instance;

    private DynamicFonts() {}

    public static DynamicFonts getInstance() {
        if (instance == null) {
            // 初始化
            instance = new DynamicFonts();

            try {
                URL resource = ReflectUtil.getResource("fonts/msyh.ttf");
                Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, new File(resource.getFile()));

                instance.ACT_FONT = dynamicFont.deriveFont(12.0f).deriveFont(Font.BOLD);
                instance.ANNOTATION_FONT = dynamicFont.deriveFont(12.0f).deriveFont(Font.BOLD);
                instance.LABEL_FONT = dynamicFont.deriveFont(12.0f).deriveFont(Font.BOLD);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


}
