import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ansDes {

    public static void main(String[] args) {
        try {
            // Step 1: Leena generates her RSA key pair and sends her public key to Lila
            KeyPair leenaKeyPair = SessionKeyGenerator.generateRSAKeyPair(1024);
            PublicKey leenaPublicKey = leenaKeyPair.getPublic();

            // Step 2: Lila receives Leena's public key and generates a session key
            SecretKey sessionKey = SessionKeyGenerator.generateSessionKey("DES", 56);

            // Step 3: Lila encrypts the session key with Leena's public key and sends it to Leena
            byte[] encryptedSessionKey = encryptWithRSA(sessionKey.getEncoded(), leenaPublicKey);

            // Step 4: Leena decrypts the session key with her private key
            byte[] decryptedSessionKey = decryptWithRSA(encryptedSessionKey, leenaKeyPair.getPrivate());
            SecretKey decryptedKey = new SecretKeySpec(decryptedSessionKey, 0, decryptedSessionKey.length, "DES");

            // Step 5: Leena encrypts her order using the session key and sends it to Lila
            String order = "Brownies";
            byte[] encryptedOrder = encryptWithDES(order, decryptedKey);

            // Step 6: Lila decrypts the order using the session key
            String decryptedOrder = decryptWithDES(encryptedOrder, sessionKey);

            // Output
            System.out.println("Leena's order: " + decryptedOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] encryptWithRSA(byte[] plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext);
    }

    public static byte[] decryptWithRSA(byte[] ciphertext, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(ciphertext);
    }

    public static byte[] encryptWithDES(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decryptWithDES(byte[] ciphertext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }
}
