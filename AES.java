import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class AES {

    public static void main(String[] args) throws Exception {
        String originalMessage = "Hello, World!";
        System.out.println("Original message: " + originalMessage);

        // Generate a random AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // AES key size is 128 bits
        SecretKey secretKey = keyGenerator.generateKey();

        // Encrypt the message
        byte[] encryptedMessage = encrypt(originalMessage, secretKey);
        System.out.println("Encrypted message: " + new String(encryptedMessage));

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, secretKey);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    // Encrypt a message using AES
    public static byte[] encrypt(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message.getBytes());
    }

    // Decrypt a message using AES
    public static String decrypt(byte[] encryptedMessage, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }
}

