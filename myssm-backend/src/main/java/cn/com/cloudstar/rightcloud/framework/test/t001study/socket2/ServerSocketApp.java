package cn.com.cloudstar.rightcloud.framework.test.t001study.socket2;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketApp {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("服务已启动");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户" + socket.getInetAddress().getHostAddress() + "来访，");
                new Thread(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        PrintStream printStream = new PrintStream(socket.getOutputStream());
                        Scanner in = new Scanner(inputStream);
                        in.useDelimiter("\n");
                        while (in.hasNext()) {
                            String message = in.next().trim();
                            if (message.equalsIgnoreCase("bye")) {
                                String bye = new String("客户端已退出，拜拜".getBytes("UTF-8"));
                                System.out.println(bye);
                                printStream.println(bye);
                            } else {
                                printStream.println("ECHO>" + message);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}