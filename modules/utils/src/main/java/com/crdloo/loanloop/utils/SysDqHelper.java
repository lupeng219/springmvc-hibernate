package com.crdloo.loanloop.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class SysDqHelper {

    private static Log log = LogFactory.getLog(SysDqHelper.class);

    private static final Map<String, Dq> INDEX2CODE = new HashMap<String, Dq>();
    private static final Map<String, List<String>> INDEX_PARENT2SONS = new LinkedHashMap<String, List<String>>();

    static {
        InputStream is = SysDqHelper.class.getClassLoader()
                .getResourceAsStream("statcode/sys_dq.dic");
        try {
            LineIterator it = IOUtils.lineIterator(is, "UTF-8");
            while (it.hasNext()) {
                String line = it.nextLine();
                if (line.startsWith("﻿#")) {
                    continue;
                }
                String[] arr = line.split(",");
                if (arr.length != 5) {
                    continue;
                }
                Dq dq = new Dq(arr);
                INDEX2CODE.put(dq.code, dq);

                List<String> sons = INDEX_PARENT2SONS
                        .get((0 == dq.relation) ? dq.code : dq.parentCode);
                if (sons == null) {
                    sons = new LinkedList<String>();
                    INDEX_PARENT2SONS.put((0 == dq.relation) ? dq.code : dq.parentCode, sons);
                }
                if (1 == dq.relation) {
                    sons.add(dq.code);
                }
            }
        } catch (Exception e) {
            log.error("sys dq loading error ...");
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * 根据地区id查找中文名
     */
    public static String lookupDQName(String code) {
        if (code == null || code.length() == 0) {
            return "";
        }
        Dq dq = INDEX2CODE.get(code);
        if (code.length() == 6) {
            if (dq == null) {
                return "";
            }
            Dq parentDq = INDEX2CODE.get(dq.parentCode);
            return dq.chName;
        }
        return (dq == null) ? "" : dq.chName;
    }

    /**
     * 根据地区id查找子类地区
     * 
     * @param String
     *            code
     * @return List<String[0]code,[1]地区中文>
     */
    public static List<String[]> getSubDqList(String code) {
        if (code == null || code.length() == 0) {
            return null;
        }
        List<String[]> result = null;
        List<String> sons = INDEX_PARENT2SONS.get(code);
        if (sons != null && sons.size() > 0) {
            result = new ArrayList<String[]>();
            for (String son : sons) {
                Dq dq = INDEX2CODE.get(son);
                result.add(new String[] { dq.code, dq.chName });
            }
        }
        return result;
    }

    /**
     * 查找地区父类code码
     */
    public static String lookupParentCode(String code) {
        if (code == null || code.length() == 0) {
            return "";
        }
        Dq dq = INDEX2CODE.get(code);
        return (dq == null) ? "" : dq.parentCode;
    }

    /**
     * 查找全部地区信息
     * 
     * @return List<String[0]code,[1]地区中文,[2]父类子类标识,[3]父类code>
     */
    public static List<Dq> lookupDqAll() {
        List<Dq> result = new LinkedList<Dq>();
        for (Dq dq : INDEX2CODE.values()) {
            result.add(dq);
        }
        return result;
    }

    public static Dq getLookupDq(String code) {
        return INDEX2CODE.get(code);
    }

    public static class Dq {

        public String code;
        public String chName;
        public String enName;
        public int relation;
        public String parentCode;

        public Dq(String[] arr) {
            code = arr[0].substring(1, arr[0].length() - 1);
            chName = arr[1].substring(1, arr[1].length() - 1);
            enName = arr[2].substring(1, arr[2].length() - 1);
            relation = Integer.parseInt(arr[3].substring(1, arr[3].length() - 1));
            parentCode = arr[4].substring(1, arr[4].length() - 1);
        }
    }

}
