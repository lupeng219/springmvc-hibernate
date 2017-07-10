package com.crdloo.loanloop.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {

    /**
     * 获得拼音
     * 
     * @param value
     * @return
     */
    public static String getPinYin(String value) {
        String pinyinStr = "";
        char[] newChar = value.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : newChar) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += c;
            }
        }
        return pinyinStr;
    }

    public static void main(String[] args) {
        // System.out.println(PinYinUtils.getPinYin("汉字转换为拼音"));
        System.err.println(Timestamp.valueOf("2016-10-29 14:18:57"));

        // String timeStamp2Date = timeStamp2Date("1477721937");
        // System.err.println(timeStamp2Date);

    }

}
