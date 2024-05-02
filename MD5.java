import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static void main(String[] args) {
        String input = "Hello, World!";
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Update the digest with the input bytes
            md.update(input.getBytes());
            // Get the hash bytes
            byte[] hashBytes = md.digest();
            // Convert the bytes to a hexadecimal string 
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            // Print the hash value
            System.out.println("MD5 Hash: " + sb.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
