package com.crdloo.loanloop.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdWorker {

    private final long workerId;
    private final static long twepoch = 1458039873853L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 9L;
    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;
    private long lastTimestamp = -1L;

    private long datacenterIdBits = 5L;
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long datacenterId;
    private long datacenterIdShift = sequenceBits + workerIdBits;

    static IdWorker worker;
    static {
        String workid = "0";
        long workid_;
        if (workid != null && !"".equals(workid)) {
            workid_ = Long.parseLong("0");
        } else {
            workid_ = 0;
        }
        worker = new IdWorker(workid_, 0);
    }

    public IdWorker(final long workerId, long datacenterId) {
        // super();
        // if (workerId > maxWorkerId || workerId < 0)
        // {
        // throw new IllegalArgumentException(String.format("worker Id can't be
        // greater than %d or less than 0",maxWorkerId));
        // }
        // this.workerId = workerId;
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String
                    .format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format(
                    "datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        // long nextId = ((timestamp - twepoch << timestampLeftShift)) |
        // (this.workerId << workerIdShift) | (this.sequence);
        // System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
        // + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
        // + workerId + ",sequence:" + sequence);

        long nextId = ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static String getNextId() {
        long value = worker.nextId();
        String value_ = Long.toString(value);
        return value_;
    }

    /*
     * 返回长度为strLength的随机数，不足则在前面补0
     */
    private static String getFixLenthStringContinChar(int length) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    /*
     * 返回长度为strLength的随机数，不足则在前面补0
     */
    private static String getFixLenthStringNotContinChar(int length) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    public static void main(String[] args) {
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        // System.out.println(worker2.nextId());
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < 500000; i++) {
            long aaa = worker.nextId();
            System.out.println(aaa);
            if (list.contains(aaa)) {
                System.out.println("重复。。。" + aaa);
                break;
            }
            list.add(aaa);
        }
        System.out.println("list=====" + list.size());
        System.out.println(System.currentTimeMillis());

    }

}
