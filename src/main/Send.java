package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Send implements Runnable {
    final String targetIP;
    final int sendPort;
    final String name;

    public Send(String targetIP, int sendPort, String name) {
        this.targetIP = targetIP;
        this.sendPort = sendPort;
        this.name = name;
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        PrintWriter sendingMessage;
        BufferedReader typeBuffer = null;
        Socket sendSocket;
        OutputStream stream;
        System.out.println("==============================");
        System.out.println("  Send Thread 연결을 시작합니다.");
        System.out.println("==============================");

        try {

            while (true) {
                serverSocket = new ServerSocket(sendPort);
                sendSocket = serverSocket.accept();
                sendingMessage = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        sendSocket.getOutputStream()
                                )
                        )
                );

                try{
                    typeBuffer = new BufferedReader(new InputStreamReader(System.in));
                    sendingMessage.println(name + " : " + typeBuffer.readLine());
                    sendingMessage.flush();
                }
                catch (NullPointerException e){
                    System.out.println(e);
                }

                serverSocket.close();

            }
        }
        catch (IOException e) {
            System.out.println("Send 스레드 오류 발생" + e);
        }
    }
}