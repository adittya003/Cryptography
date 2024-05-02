import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class DES {
    public static String encrypted(String plaintext, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(plaintext.getBytes());
        return new String(encryptedBytes);
    }

    public static String decrypted(String ciphertext, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = ciphertext.getBytes();
        Cipher decryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        // Key generation
        String plaintext = "im leena pls add my brownies";
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String enc_msg = encrypted(plaintext, publicKey);
        System.out.println("Encrypted message: " + enc_msg);

        String dec_msg = decrypted(enc_msg, privateKey);
        System.out.println("Decrypted message: " + dec_msg);
    }
}
