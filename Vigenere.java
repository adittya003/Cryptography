import java.util.*;

public class Vigenere {
    public static String vigenere(String s) {
        String key = "deceptive";
        int len = s.length();
        int lenKey = key.length();
        StringBuilder realKey = new StringBuilder(len);
        StringBuilder enc = new StringBuilder(len);

        // Generating the real key based on the plaintext and the given key
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i % lenKey);
            realKey.append(c);
        }

        for (int i = 0; i < len; i++) {
            int p = (int) s.charAt(i);
            int k = (int) realKey.charAt(i);
            int ci = (p + k - 2 * 'a') % 26 + 'a'; // Assuming lowercase alphabetic characters
            char cipher = (char) ci;
            enc.append(cipher);
        }

        return enc.toString();
    }

    public static void main(String[] args) {
        String plaintext = "adittyanarayan"; // Ensuring lowercase input
        String ciphertext = vigenere(plaintext);
        System.out.println("Encrypted: " + ciphertext);
    }
}

