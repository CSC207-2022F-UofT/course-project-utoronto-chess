package server;
import java.net.*;
import java.io.*;

public class Server {
    public Server() {
        try {
//            ServerSocket class waits for requests over the network
//            Port number of 0 will allow the system to choose a port number
            ServerSocket server = new ServerSocket(0);
            System.out.println("Started");
//            Port number stored as a local variable
            int port = server.getLocalPort();

//            Accept blocks execution until a connection is made
            System.out.println("Waiting for a client on port " + port);
            Socket socket = server.accept();
            System.out.println("Client accepted on port " + port);
//            Reads input from the client socket
            DataInputStream input = new DataInputStream(new BufferedInputStream((socket.getInputStream())));
            String line = "";
//            Quits the connection when the message "quit" is sent
            while (!line.equals("quit")) {
                try {
                    line = input.readUTF();
                    System.out.println(line);
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }

            System.out.println("Closing");

            socket.close();
            input.close();

        } catch (IOException error) {
            error.printStackTrace();
        }

    }
}
