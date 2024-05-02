import java.net.*;
import java.io.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream inputStream;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            clientSocket = serverSocket.accept();
            System.out.println("Client accepted");

            inputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Over")) {
                try {
                    line = inputStream.readUTF();
                    System.out.println("Client: " + line);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            System.out.println("Closing connection");

            // Close connection
            clientSocket.close();
            inputStream.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}
