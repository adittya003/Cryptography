import javax.net.ssl.*;
import java.io.*;

public class SSLServer {
    public static void main(String[] args) {
        try {
            SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(9999);

            System.out.println("Server started. Waiting for client...");

            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(sslSocket.getOutputStream(), true);

            String message = reader.readLine();
            System.out.println("Received from client: " + message);

            writer.println("Hello from server!");

            sslSocket.close();
            sslServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

