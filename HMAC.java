import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HMAC {

    public static void main(String[] args) {
        String secretKey = "my_secret_key";
        String message = "Hello, world!";
        
        try {
            String hmac = calculateHMAC(message, secretKey);
            System.out.println("HMAC: " + hmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static String calculateHMAC(String message, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKeySpec);
        byte[] hmacBytes = hmacSha256.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(hmacBytes);
    }
}