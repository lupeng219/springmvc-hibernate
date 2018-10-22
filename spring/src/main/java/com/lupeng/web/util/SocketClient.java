package com.lupeng.web.util;

import com.lupeng.web.data.EmployeeData;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by lupeng on 2018/8/20.
 */
public class SocketClient {
    private static final int SERVER_PORT = 8888;
    private static final String IP = "10.168.15.81";
    /**
     * Socket客户端
     */
    public static void main(String[] args) {

    }
    public void  send(){
        try {
            //创建Socket对象
            Socket socket=new Socket(IP,SERVER_PORT);
            //根据输入输出流和服务端连接
            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
            EmployeeData employeeData = new EmployeeData();
            employeeData.setCustNo("lu");
            objectOutputStream.writeObject(employeeData);
            objectOutputStream.flush();

            socket.shutdownOutput();//关闭输出流



            InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            try {
                employeeData = (EmployeeData)objectInputStream.readObject();
            } catch (Exception e) {

            }
            //关闭相对应的资源
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void  over() {
//        //关闭相对应的资源
//        inputStream.close();
//        outputStream.close();
//        socket.close();
//    }
}
