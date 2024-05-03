import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class DigitalSignatureExample {

    public static void main(String[] args) throws Exception {
        // Generate public and private keys
        KeyPair keyPair = generateKeyPair();

        // Original message to be signed
        String originalMessage = "Hello, World!";

        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initialize the signature object with the private key
        signature.initSign(keyPair.getPrivate());

        // Update the signature object with the original message
        signature.update(originalMessage.getBytes());

        // Generate the digital signature
        byte[] digitalSignature = signature.sign();

        // Print the digital signature
        System.out.println("Digital Signature: " + bytesToHex(digitalSignature));

        // Verify the digital signature
        boolean isVerified = verifySignature(originalMessage.getBytes(), digitalSignature, keyPair.getPublic());

        // Print the verification result
        System.out.println("Is signature verified? " + isVerified);
    }

    // Generate public and private keys
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Key size is 2048 bits
        return keyPairGenerator.generateKeyPair();
    }

    // Verify the digital signature
    public static boolean verifySignature(byte[] originalMessage, byte[] digitalSignature, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(originalMessage);
        return signature.verify(digitalSignature);
    }

    // Convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
