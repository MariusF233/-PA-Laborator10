/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * @author me
 */
public class ClientThread extends Thread {

    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // Get the request from the input stream: client → server

            while (true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                // Send the response to the oputput stream: server → client

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                if (request.equals("exit")) {
                    String raspuns = "server stopped";
                    out.println(raspuns);
                    out.flush();
                    socket.close();
                    break;
                } else {
                    String raspuns = "Server recieved the request " + request;
                    out.println(raspuns);
                    out.flush();
                }
            }
        } catch (IOException e) {

            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
