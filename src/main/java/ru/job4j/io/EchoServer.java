package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 Ready\r\n\r\n".getBytes());
                    String requestLine = in.readLine();
                    if (requestLine != null && requestLine.contains("msg=Hello")) {
                        out.write("HTTP/1.1 200 Hello\r\n\r\n".getBytes());
                    } else if (requestLine != null && requestLine.contains("msg=Exit")) {
                        server.close();
                    } else {
                        out.write(Integer.parseInt("HTTP/1.1 200 " + requestLine + "\r\n\r\n".getBytes()));
                        for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                            System.out.println(str);
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
