import java.math.BigInteger;
import java.util.Random;

public class DigitalSig {

    private static final BigInteger P = new BigInteger("1234567890123456789012345678901234567890123456789012345678901234567890123456789");
    private static final BigInteger Q = new BigInteger("9876543210987654987654318765431897654319876543124567896543134567931234765987643123");
    private static final BigInteger G = new BigInteger("666666666666666666666666666666666666666666666666666666666666666666666666666666");

    private static BigInteger generatePrivateKey() {
        // Generate private key
        Random random = new Random();
        BigInteger privateKey;
        do {
            privateKey = new BigInteger(Q.bitLength(), random);
        } while (privateKey.compareTo(Q) >= 0);
        return privateKey;
    }

    private static BigInteger generatePublicKey(BigInteger privateKey) {
        // Generate public key
        BigInteger publicKey = G.modPow(privateKey, P);
        return publicKey;
    }

    private static BigInteger[] generateSignature(BigInteger privateKey, byte[] message) {
        // Generate signature
        BigInteger[] signature = new BigInteger[2];
        BigInteger k;
        BigInteger r;
        BigInteger s;

        Random random = new Random();
        do {
            do {
                k = new BigInteger(Q.bitLength(), random);
            } while (k.compareTo(Q) >= 0);

            r = G.modPow(k, P).mod(Q);
            s = (privateKey.multiply(r).add(new BigInteger(message))).multiply(k.modInverse(Q)).mod(Q);
        } while (r.equals(BigInteger.ZERO) || s.equals(BigInteger.ZERO));

        signature[0] = r;
        signature[1] = s;

        return signature;
    }

    private static boolean verifySignature(BigInteger publicKey, byte[] message, BigInteger[] signature) {
        // Verify signature
        BigInteger r = signature[0];
        BigInteger s = signature[1];

        if (r.compareTo(BigInteger.ONE) < 0 || r.compareTo(Q) >= 0 || s.compareTo(BigInteger.ONE) < 0 || s.compareTo(Q) >= 0) {
            return false;
        }

        BigInteger w = s.modInverse(Q);
        BigInteger u1 = (new BigInteger(message)).multiply(w).mod(Q);
        BigInteger u2 = r.multiply(w).mod(Q);

        BigInteger v = (G.modPow(u1, P).multiply(publicKey.modPow(u2, P))).mod(P).mod(Q);

        return v.equals(r);
    }

    public static void main(String[] args) {
        // Generate key pair
        BigInteger privateKey = generatePrivateKey();
        BigInteger publicKey = generatePublicKey(privateKey);
        System.out.println("Private key: " + privateKey);
        System.out.println("Public key: " + publicKey);

        // Sign and verify
        byte[] message = "Hello, world!".getBytes();
        BigInteger[] signature = generateSignature(privateKey, message);
        System.out.println("Signature: " + signature[0] + ", " + signature[1]);
        boolean verified = verifySignature(publicKey, message, signature);
        System.out.println("Signature verification: " + verified);
    }
}
