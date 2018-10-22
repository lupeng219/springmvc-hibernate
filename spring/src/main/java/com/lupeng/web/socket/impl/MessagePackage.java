package com.lupeng.web.socket.impl;

import com.lupeng.web.socket.MsgPackage;

/**
 * 消息包
 * Created by lupeng on 2018/8/21.
 */
public class MessagePackage extends MsgPackage {

    public MessagePackage() {

        super("t1", "t2");
    }
    public MessagePackage(MessageHead t1) {
        super("t1");
        this.t1 = t1;
    }

    private MessageHead t1;

    private MessageBody t2;

    public MessageHead getT1() {
        return t1;
    }

    public void setT1(MessageHead t1) {
        this.t1 = t1;
    }

    public MessageBody getT2() {
        return t2;
    }

    public void setT2(MessageBody t2) {
        this.t2 = t2;
    }
}
