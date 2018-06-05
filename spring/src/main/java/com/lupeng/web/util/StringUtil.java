package com.lupeng.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
    public final static String dateTimeFormater = "yyyy-MM-dd HH:mm:ss";
    public final static String benginTime = " 00:00:00";
    public final static String endTime = " 23:59:59";

    /**
     * 格式化图片路径 格式为1,2,3,4,5
     * 
     * @param pic
     * @return
     */
    public static String formatPicUrl(String pic) {
        if (StringUtil.isEmpty(pic)) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        String[] str = pic.split(",");
        for (String s : str) {
            if (s != null || s != "") {
                buffer.append(s).append(",");
            }
        }
        return buffer.toString().substring(0, buffer.length() - 1);

    }

    /**
     * 去除空格
     * 
     * @param string
     * @return
     */
    public static String trim(String string) {
        return string.trim();
    }

    /**
     * 替换身份证
     * 
     * @param idCard
     * @return
     */
    public static String replaceIdCard(String idCard) {
        return idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1**********$2");
    }

    /**
     * 替换姓名
     * 
     * @param realName
     * @return
     */
    public static String replaceRealName(String realName) {
        char[] chars = new char[realName.length() - 1];
        Arrays.fill(chars, '*');
        return realName.substring(0, 1) + new String(chars);
    }

    /**
     * 替换姓名
     * 
     * @param realName
     * @return
     */
    public static String replaceBankNoName(String bankNo) {
        char[] chars = new char[bankNo.length() - 8];
        Arrays.fill(chars, '*');
        return bankNo.substring(0, 4) + new String(chars) + bankNo.substring(bankNo.length() - 4);
    }

    public static int nullToNumber(String str, int defalut) {
        if (isEmpty(str)) {
            return defalut;
        }
        return Integer.parseInt(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static String nullToBlank(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public static int nullToZero(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    /**
     * ��map��ȡֵ��null�򷵻�""
     * 
     * @param map
     * @param mapKey
     * @return
     */
    public String getMapValue(Map map, String mapKey) {
        return map.get(mapKey) == null ? "" : map.get(mapKey).toString();
    }

    /**
     * @param �����ַ�
     * @return ���ַ���ֽڳ��ȶ����ַ��
     */
    public static int getWordCount(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;

        }
        return length;
    }

    public static String getFilePath(String fileName) {
        String date = getFormatTime(new Date(), "yyyyMMdd-hhmmss");
        // String name =
        // fileName.substring(fileName.lastIndexOf("\\")+1,fileName.lastIndexOf("."));
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        return name + date;
    }

    public static String getFilePath(String dir, String fileName) {
        String d = dir;
        if (!fileName.endsWith("\\") && !fileName.endsWith("/")) {
            d += File.separator;
        }

        return d + fileName;
    }

    public static String getFileName(String fileName) {
        if (fileName.indexOf("\\") > -1)
            return fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
        else if (fileName.indexOf("/") > -1) {
            return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
        } else {
            return fileName;
        }
    }

    public static String getFileEXTName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
    }

    public static String getFileEXTName(String fileName, String newFilename) {
        return newFilename + '.' + getFileEXTName(fileName);
    }

    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (date == null) {
            return df.format(new Date());
        } else {
            return df.format(date);
        }
    }

    public static String[] split(String s, String separator) {
        if (s == null || s.trim().length() == 0)
            return null;
        String result[] = (String[]) null;
        String tmp[] = new String[500];
        String parseStr = s;
        int k = 0;
        int j = 0;
        for (int pos = 0; pos != -1;) {
            pos = parseStr.indexOf(separator);
            if (pos != -1) {
                tmp[k++] = parseStr.substring(j, pos);
                parseStr = parseStr.substring(pos + separator.length(), parseStr.length());
            }
        }

        tmp[k++] = parseStr.substring(j, parseStr.length());
        if (k == 0)
            return result;
        result = new String[k];
        for (int i = 0; i < k; i++)
            result[i] = tmp[i];

        return result;
    }

    public static String getShowTime(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (date == null) {
            return "";
        } else {
            return df.format(date);
        }
    }

    public static String roundDoule(double value) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(value);
    }

    public static String convertName(String name, boolean columnFlag) {
        String result = null;

        if (name != null && name.length() > 0) {
            StringBuffer tmpString = new StringBuffer();

            if (columnFlag) {
                String[] strs = split(name, "_");
                tmpString.append(strs[0].toLowerCase());
                if (strs.length > 1) {
                    for (int i = 1; i < strs.length; i++) {
                        tmpString.append(strs[i].substring(0, 1).toUpperCase())
                                .append(strs[i].substring(1));
                    }
                }
            } else {
                char[] chars = name.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (Character.isLowerCase(chars[i])) {
                        tmpString.append(Character.toUpperCase(chars[i]));
                    } else if (Character.isUpperCase(chars[i])) {
                        tmpString.append('_').append(chars[i]);
                    } else {
                        tmpString.append(chars[i]);
                    }
                }
            }

            result = tmpString.toString();
        }

        return result;
    }

    public static void main(String[] args) {

    }

    public static boolean isBlank(String principal) {
        return (principal == null) || (principal.trim().equals(""));
    }

    /**
     * 将clob转换成string
     * 
     * @param request
     * @return
     */
    public static String ClobToString(HttpServletRequest request) {
        Clob clob = null;
        clob = (Clob) request.getAttribute("articleContents");
        String reString = null;
        BufferedReader br = null;
        Reader is = null;// 得到流
        if (clob != null) {
            try {
                is = clob.getCharacterStream();
                br = new BufferedReader(is);
                String s;
                try {
                    s = br.readLine();
                    StringBuffer sb = new StringBuffer();
                    while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
                        sb.append(s);
                        s = br.readLine();
                    }
                    reString = sb.toString();
                } catch (IOException e) {
                    logger.error("ClobToString", e);
                }

            } catch (SerialException e) {
                logger.error("ClobToString", e);
            } catch (SQLException e) {
                logger.error("ClobToString", e);
            }
        } else {
            reString = null;
        }

        return reString;
    }

    public static String getTimestampString(String strts, String pattern) {
        if (StringUtils.isBlank(strts)) {
            return "";
        }
        SimpleDateFormat s = new SimpleDateFormat(pattern);
        Date ae;
        String time = null;
        try {
            ae = s.parse(strts);
            Long timel = ae.getTime();
            time = timel.toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return time;

    }

    
    public static int string2int(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }
    
    public static int string2int(String str, int defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Integer string2Int(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Integer string2Int(String str, int defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return Integer.valueOf(defaultValue);
        }
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return Integer.valueOf(defaultValue);
        }
    }
    
    public static long string2long(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0l;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0l;
        }
    }
    
    public static long string2long(String str, long defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Long string2Long(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        try {
            return Long.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Long string2Long(String str, long defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return Long.valueOf(defaultValue);
        }
        try {
            return Long.valueOf(str);
        } catch (Exception e) {
            return Long.valueOf(defaultValue);
        }
    }
    
    public static float string2float(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return 0;
        }
    }
    
    public static float string2float(String str, float defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static Float string2Float(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        try {
            return Float.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Float string2Float(String str, float defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return Float.valueOf(defaultValue);
        }
        try {
            return Float.valueOf(str);
        } catch (Exception e) {
            return Float.valueOf(defaultValue);
        }
    }

    public static String lowercaseFirstLetter(String data) {
        char firstLetter = Character.toLowerCase(data.substring(0, 1).charAt(0));
        String restLetters = data.substring(1);
        return firstLetter + restLetters;
    }


    /*
     * public static void yiHuanLixi(HttpServletRequest request){
     * List<RepaymentPerformance> rpf=(List<RepaymentPerformance>)
     * request.getAttribute("repaymentPerformance"); PaymentDetails
     * pay=(PaymentDetails)request.getAttribute("temp_pay"); BigDecimal
     * allAlsoInteres=new BigDecimal(0); for(RepaymentPerformance temp:rpf){
     * allAlsoInteres=allAlsoInteres.add(temp.getAlsoInteres()); }
     * request.setAttribute("result", allAlsoInteres.subtract(new
     * BigDecimal(pay.getTotalAmount()))); }
     */
}
