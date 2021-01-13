package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable{
    final String targetIP;
    final int receivePort;

    public Receive(String targetIP, int receivePort) {
        this.targetIP = targetIP;
        this.receivePort = receivePort;
    }

    public void receivingMessage() throws IOException {
        BufferedReader receivedMessage;
        Socket receiveSocket;
        receiveSocket = new Socket(targetIP, receivePort);
        receivedMessage = new BufferedReader(
                new InputStreamReader(
                        receiveSocket.getInputStream()
                )
        );
        System.out.println(receivedMessage.readLine());
        receiveSocket.close();
    }

    @Override
    public void run() {

        System.out.println("==============================");
        System.out.println("Receive Thread 연결을 시작합니다.");
        System.out.println("==============================");


        while (true){
            try {
                receivingMessage();
            } catch (IOException e) {
                System.out.println("상대방을 기다리는 중입니다...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }

    }
}
