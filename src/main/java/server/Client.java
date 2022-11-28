package server;
import java.net.*;
import java.io.*;
import java.util.Objects;

public class Client {
    private DataOutputStream outputStream;

    public Client(String address, int port) {
        try {
            Socket socket = new Socket(address, port);
            System.out.println("Connected");

            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException error) {
            error.printStackTrace();
        }

        String line = "";

        while (!Objects.equals(line, "quit")) {
            try {
                line = System.console().readLine();
                outputStream.writeUTF(line);
            }
            catch (IOException error) {
                error.printStackTrace();
            }
        }
    }
}
