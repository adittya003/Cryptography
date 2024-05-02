import java.security.*;

public class SHA256 {
    public static void main(String[] args) {
        try {
            String originalString = "Hello World";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(originalString.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("SHA-512 Hash: " + hexString.toString());
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}