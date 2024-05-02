import java.security.*;

public class SHA512 {
    public static void main(String[] args) {
        String originalMessage = "Hello, World!";
        System.out.println("Original message: " + originalMessage);

        try {
            // Create a MessageDigest instance for SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            // Compute the hash value of the message
            byte[] hashBytes = digest.digest(originalMessage.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Print the SHA-512 hash value
            System.out.println("SHA-512 Hash: " + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
