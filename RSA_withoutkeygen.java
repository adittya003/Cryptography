import java.util.*;

public class RSA_withoutkeygen {
    public static void main(String[] args) {
        String plainText = "Hello World";
        int p = 7;
        int q = 13;
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e = 5;
        int d = 29; // d*e*mod(phi)=1
        //public key(e,n) private key(d,n)
        StringBuilder enc = new StringBuilder(100);
        // Encryption
        for (int i = 0; i < plainText.length(); i++) {
            int ascii = (int) plainText.charAt(i);
            int enc_ascii = (int) (Math.pow(ascii, e) % n);
            enc.append((char) enc_ascii);
        }

        // Decryption
        StringBuilder dec = new StringBuilder(100);
        for (int i = 0; i < enc.length(); i++) {
            int ascii = (int) enc.charAt(i);
            int dec_ascii = (int) (Math.pow(ascii, d) % n);
            dec.append((char) dec_ascii);
        }

        System.out.println("Encrypted text: " + enc.toString());
        System.out.println("Decrypted text: " + dec.toString());
    }
}
