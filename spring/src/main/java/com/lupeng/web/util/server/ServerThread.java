package com.lupeng.web.util.server;

import java.net.Socket;
import java.io.*;
public class ServerThread implements Runnable{
    //定义当前处理的Socket
    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;
    public ServerThread(Socket socket) throws IOException{
        this.socket = socket;
    }
    public void run(){
        try{
            //初始化该socket对应的输入流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            String line = null;
            //循环读取客户端发送的数据
            while((line=br.readLine().trim())!=null){
                //读到的是用户登录的用户名
                if(line.startsWith(CrazyitProtocol.USER_ROUND)&&line.endsWith(CrazyitProtocol.USER_ROUND)){
                    String userName = getRealMsg(line).trim();
                    if(Server.clients.map.containsKey(userName)){
                        System.out.println("该用户名已存在");
                        ps.println(CrazyitProtocol.NAME_REP);
                    }else{
                        if(Server.clients.size()<Server.MaxPoolNum){
                            System.out.println(userName+"登录成功！"+" 共登录"+(Server.clients.size()+1)+"人");
                            ps.println(CrazyitProtocol.LOGIN_SUCCESS);
                            Server.clients.put(userName, ps);
                        }else{
                            System.out.println("超出最大人数！");
                            ps.println(CrazyitProtocol.MAXPOOL);
                        }

                    }
                }else if(line.startsWith(CrazyitProtocol.PRIVATE_ROUND)&&line.endsWith(CrazyitProtocol.PRIVATE_ROUND)){
                    //私聊信息
                    String userAndMsg = getRealMsg(line);
                    String num = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[0];
                    String user = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[1];
                    String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[2];
                    if(Server.clients.map.containsKey(user)){
                        ps.println(CrazyitProtocol.SEND_ROUND+num+CrazyitProtocol.SEND_ROUND);
                        Server.clients.map.get(user).println(Server.clients.getKeyByValue(ps)+"悄悄对你说： "+msg);
                    }else{
                        ps.println(user+"用户不存在！");
                    }

                }else{
                    //公聊信息
                    String msg = getRealMsg(line);
                    String num = msg.split(CrazyitProtocol.SPLIT_SIGN)[0];
                    String message = msg.split(CrazyitProtocol.SPLIT_SIGN)[1];
                    ps.println(CrazyitProtocol.SEND_ROUND+num+CrazyitProtocol.SEND_ROUND);
                    for(PrintStream psClient : Server.clients.valueSet()){
                        psClient.println("【公共】"+Server.clients.getKeyByValue(ps)+"说： "+message);
                    }
                }
				/*System.out.println("receive message!");
				for(int i=0;i<Server.socketList.size();i++){
					PrintStream ps = new PrintStream(socket.getOutputStream());
					ps.println(content);
				}
				System.out.println(content);*/
            }
        }catch(IOException ex){
            System.out.println(Server.clients.getKeyByValue(ps)+"下线！");
            Server.clients.removeByValue(ps);
            System.out.println("上线人数："+Server.clients.map.size());
            try{
                if(br!=null){
                    br.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(socket!=null){
                    socket.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    private String getRealMsg(String line){
        return line.substring(CrazyitProtocol.PROTOCOL_LEN,line.length()-CrazyitProtocol.PROTOCOL_LEN);
    }
    //定义读取客户端数据的方法
	/*private String readFromClient(){
		try{
			return br.readLine();
		}catch(Exception ex){
			Server.socketList.remove(socket);
		}
		return null;
	}*/
}
