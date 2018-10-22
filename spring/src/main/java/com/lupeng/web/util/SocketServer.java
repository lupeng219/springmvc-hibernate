package com.lupeng.web.util;

import cn.hutool.core.map.MapUtil;
import com.lupeng.web.data.EmployeeData;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;

/**
 * Created by lupeng on 2018/8/20.
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            System.out.println("start..");
            Socket socket=serverSocket.accept();//侦听并接受到此套接字的连接,返回一个Socket对象
            //根据输入输出流和客户端连接
            InputStream inp =socket.getInputStream();//得到一个输入流，接收客户端传递的信息
            //把字节流转换成字符流
            InputStreamReader isr = new InputStreamReader(inp);
            //为字符流增加缓冲区
            BufferedReader bfr = new BufferedReader(isr);
            String info = null;
            while((info=bfr.readLine())!=null){//循环读取数据
                    System.out.println("read："+info);
                }
            socket.shutdownInput();//关闭输入流
            OutputStream ots = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(ots);
            pw.write("END");
            pw.flush();
            //关闭资源
//             pw.close();
//             ots.close();
//             bfr.close();
//             isr.close();
//            inp.close();
//             socket.close();
//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
