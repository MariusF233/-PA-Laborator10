/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author me
 */
public class GameClient {

    //private string request;
    //int i = sc.next();
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        String serverAddress = "127.0.0.1"; // The server's IP addresScanner sc=new Scanner(system.in);s
        int PORT = 8100; // The server's port
        try (
                 Socket socket = new Socket(serverAddress, PORT); 
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);  
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) 
        {

            while (true) {

                System.out.println("Request=");
                String request = sc.nextLine();
                out.println(request);
                if (request.equals("exit")) {
                    break;
                }

                // Wait the response from the server ("Hello World!")
                String response = in.readLine();
                System.out.println(response);
                if (response.equals("server stopped")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
