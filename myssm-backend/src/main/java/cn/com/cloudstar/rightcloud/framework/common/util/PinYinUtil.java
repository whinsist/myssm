package cn.com.cloudstar.rightcloud.framework.common.util;

import com.google.common.base.Strings;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * The type PinYinUtil.
 * <p>
 *
 * @author Xiaolu.Liu
 * @date 2018/6/6
 */
public class PinYinUtil {
    /**
     * 简称最长为6
     */
    private static Integer MAX_EN_LENGTH = 6;

    /**
     * 获取汉字字符串首字母
     *
     * @param chinese
     * @return
     */
    public static String getFirstSpell(String chinese) {
        if (Strings.isNullOrEmpty(chinese)) {
            return "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置格式为大写
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 没有音调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length && stringBuffer.length() < MAX_EN_LENGTH; i++) {
            // 如果汉字取首字母， 否则直接添加
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        stringBuffer.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else if (Character.isLetterOrDigit(arr[i])){
                stringBuffer.append(arr[i]);
            }
        }
        return stringBuffer.toString().replaceAll("\\W", "").trim();
    }

}
