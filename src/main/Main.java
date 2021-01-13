package main;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하세요. >> ");
        String name = sc.nextLine();

        System.out.print("상대방 IP를 입력하세요. >>");
        String targetIP = sc.nextLine();

        System.out.print("상대방 SEND 포트를 입력하세요. >>");
        int sendPort = sc.nextInt();

        System.out.print("상대방 RECEIVE 포트를 입력하세요. >>");
        int receivePort = sc.nextInt();

        Thread sendThread = new Thread(new Send(targetIP, sendPort, name));
        Thread receiveThread = new Thread(new Receive(targetIP, receivePort));
        sendThread.start();
        receiveThread.start();

        sendThread.join();
        receiveThread.join();
        System.out.println("빠이빠이~");
    }

}
