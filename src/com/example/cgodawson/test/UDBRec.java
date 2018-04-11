package com.example.cgodawson.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class UDBRec implements Runnable{

	@Override
	public void run() {

		 System.out.println("rec server start ok!");
		while(true)
		{
			try{
				byte[] buf = new byte[1024];
		         final DatagramPacket packet = new DatagramPacket(buf, buf.length);
		         DatagramSocket responseSocket = new DatagramSocket(12597);
		         responseSocket.receive(packet);
		         byte[] recData = packet.getData();
		         final String recS = new String(recData,0,packet.getLength());
		         System.out.println("send file "+recS+" to "+packet.getAddress().getHostAddress());
		         
	                //ip:192.168.18.255
	                new Thread(new Runnable() {
	                    @Override
	                    public void run() {

	                      try {
	                    	  final String ip = packet.getAddress().getHostAddress();
	                    	    Socket socket = new Socket(ip,12599);
	        	                final OutputStream outputStream = socket.getOutputStream();
	                          File file = new File("./files/"+recS);
	                          FileInputStream fileInputStream = new FileInputStream(file);
	                          byte[] buff = new byte[4096];
	                          int len = 0;
	                          while((len=fileInputStream.read(buff))>0)
	                          {
	                              outputStream.write(buff,0,len);
	                              outputStream.flush();
	                          }
	                          outputStream.close();
	                          fileInputStream.close();
	                          socket.close();
	                          System.out.println("      send list file ok:"+ip);
	                      }catch (Exception e)
	                      {
	                          e.printStackTrace();
	                          
	                      }

	                    }
	                }).start();
		         
		         
		         responseSocket.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		 
	}

}
