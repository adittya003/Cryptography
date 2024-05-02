import javax.net.ssl.*;
import java.io.*;

public class SSLClient {
    public static void main(String[] args) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 9999);

            BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(sslSocket.getOutputStream(), true);

            writer.println("Hello from client!");

            String response = reader.readLine();
            System.out.println("Received from server: " + response);

            sslSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

