package com.lupeng.web.socket.impl;
import com.lupeng.web.data.EmployeeData;
import com.lupeng.web.util.DateUtil;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by lupeng on 2018/8/21.
 */
public class Test {

    /**
     * @param args
     * @throws Exception
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, Exception {
        MessageHead head = new  MessageHead();
        head.setCommand_Id(0x00000008);
        head.setSequence_Id(1000);
        head.setTotal_Length(head.getLength());
//        MessageBody body = new MessageBody();
//        body.setVersion(0x10);
//        body.setUser_id(8087);
//        String d=  DateUtil.format(new Date(),"MMddHHmmss");
//        body.setTimestamp(Integer.valueOf(d));
//        body.setAuthenticator_Md5("5fa2c2f5426689c8");
//        head.setTotal_Length(head.getLength() + body.getLength());

        MessagePackage packagee = new MessagePackage(head);
//        packagee.setT1(head);
//        packagee.setT2(body);
        System.out.println(new String(packagee.pack("GBK"), "GBK"));
        //创建Socket对象
        byte[] res = null;
        InputStream is = null;
        OutputStream os = null;


        Socket socket=new Socket("101.231.114.246",18086);
        //根据输入输出流和服务端连接
        os =socket.getOutputStream();//获取一个输出流，向服务端发送信息
        os.write(packagee.pack("GBK"));
        os.flush();

        is = socket.getInputStream();
        int a = is.available();
//        for (int i = 0; i>=a; i++) {
//            is = socket.getInputStream();
//            a = is.available();
//        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count = 0;
        do {
            count = is.read(buffer);
            bos.write(buffer, 0, count);
        } while (is.available() != 0);
        res = bos.toByteArray();
//        os.close();
//        is.close();
//        socket.close();
//        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
//        DataOutputStream out = new DataOutputStream(outputStream);
//        out.writeUTF(new String(packagee.pack("UTF-8"),"UTF-8"));
//        out.flush();
//        objectOutputStream.writeObject(new String(packagee.pack("UTF-8"), "UTF-8"));
//        objectOutputStream.flush();
        InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        DataInputStream in = new DataInputStream(inputStream);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int count = 0;
//        byte[] res = null;
//        do {
//            count = inputStream.read(buffer);
//            bos.write(buffer, 0, count);
//        } while (inputStream.available() != 0);
//        res = bos.toByteArray();
//        outputStream.close();
//        inputStream.close();
//        socket.close();
        System.out.println("hserver:" + res);
//        try {
//            Object o = objectInputStream.readObject();
//        } catch (Exception e) {
//
//        }
//        socket.shutdownOutput();//关闭输出流
//        MessagePackage packagee2 = new MessagePackage();
//        String str = "111122203333cont";
//        packagee2.unPack(str.getBytes("GBK"), "GBK");
//        System.out.println(packagee2.getT1().getCommand_Id());
    }

}

