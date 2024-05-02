import javax.crypto.*;
import java.util.*;
import java.security.*;



public class DES {
    public static void main(String[] args) throws Exception {
        String original = "Hello World";
        System.out.println("Original: " + original);

        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey key = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encVal = cipher.doFinal(original.getBytes());

        System.out.println("Encrypted message: " + new String(encVal));

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decVal = cipher.doFinal(encVal);
        System.out.println("Decrypted message: " + new String(decVal));
    }
}
