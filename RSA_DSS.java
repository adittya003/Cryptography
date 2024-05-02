import javax.crypto.*;
import java.security.*;

public class RSA_DSS {
    public static void main(String[] args) {
        // KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // keyPairGenerator.initialize(2048);
        // KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // PublicKey publicKey = keyPair.getPublic();
        // PrivateKey privateKey = keyPair.getPrivate();

        String plainText = "Hello World";
        int p = 7;
        int q = 13;
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e = 5;
        int d = 29; // d*e*mod(phi)=1
        //public key(e,n) private key(d,n)
        StringBuilder enc = new StringBuilder(100);
        StringBuilder Sig = new StringBuilder(100);
        StringBuilder ver = new StringBuilder(100);
        // Encryption
        for (int i = 0; i < plainText.length(); i++) {
            int ascii = (int) plainText.charAt(i);
            int enc_ascii = (int) (Math.pow(ascii, e) % n);
            enc.append((char) enc_ascii);
        }
        
        // Signature
        for (int i = 0; i < enc.length(); i++) {
            int ascii = (int) enc.charAt(i);
            int sig_ascii = (int) (Math.pow(ascii, d) % n);
            Sig.append((char) sig_ascii);
        }
        //verfication
        for(int i =0 ; i<Sig.length();i++){
            int ascii = (int) Sig.charAt(i);
            int ver_ascii = (int) (Math.pow(ascii, e) % n);
            ver.append((char) ver_ascii);
        }

        if (ver.toString().equals(enc.toString())) {
            System.out.println("Signature is valid");
        }
        // Decryption
        StringBuilder dec = new StringBuilder(100);
        for (int i = 0; i < enc.length(); i++) {
            int ascii = (int) enc.charAt(i);
            int dec_ascii = (int) (Math.pow(ascii, d) % n);
            dec.append((char) dec_ascii);
        }

    }
}
