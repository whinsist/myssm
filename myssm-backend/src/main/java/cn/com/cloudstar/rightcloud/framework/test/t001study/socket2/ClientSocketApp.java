package cn.com.cloudstar.rightcloud.framework.test.t001study.socket2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketApp {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        Scanner in = new Scanner(System.in);
        Scanner inputStream = new Scanner(socket.getInputStream());
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        in.useDelimiter("\n");
        inputStream.useDelimiter("\n");
        boolean b = true;
        System.out.println("请输入：");
        while (b) {
            if (in.hasNext()) {
                String message = in.next().trim();
                printStream.println(message);
                if (inputStream.hasNext()) {
                    String serverMessage = new String(inputStream.next().trim().getBytes("UTF-8"));
                    System.out.println(serverMessage);
                }
                if (message.equalsIgnoreCase("bye")) {
                    b = false;
                }
            }
        }
        socket.close();
    }
}