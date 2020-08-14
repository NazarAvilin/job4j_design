package ru.job4j.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean go = true;
            while (go) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String answer = "";
                    while (!str.isEmpty()) {
                        if (str.contains("Exit")) {
                            go = false;
                        }
                        if (answer.equals("")) {
                            answer = str.split("=")[1];
                            answer = answer.split("HTTP")[0];
                        }
                        str = in.readLine();
                    }
                    System.out.println(answer);
                    out.write("\nHTTP/1.1 200 OK\r\n\r".getBytes());
                    out.write(answer.getBytes());
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log form", e);
        }
    }
}
