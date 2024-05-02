import javax.crypto.*;
import java.security.*;
import java.util.*;
import java.security.spec.*;
public class practice {
    public static void main(String[] args) throws Exception{
        String plaintext="hi im adittya";
        //AES
        KeyGenerator keyGen=KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey seckey=keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,seckey);
        byte [] enc_message=cipher.doFinal(plaintext.getBytes()); //--> Encrypted AES

        Cipher cipher2 = Cipher.getInstance("AES");
        cipher2.init(Cipher.DECRYPT_MODE,seckey);
        byte[] dec_msg=cipher2.doFinal(enc_message);
        String decmsg=new String(dec_msg);//-->decrypted AES

        //DES
        KeyGenerator keyGen2=KeyGenerator.getInstance("DES");
        keyGen2.init(56);
        SecretKey seckey2=keyGen2.generateKey();

        Cipher cipher3=Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher3.init(Cipher.ENCRYPT_MODE,seckey2);
        byte [] enc_msg=cipher3.doFinal(plaintext.getBytes());

        Cipher cipher4 = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher4.init(Cipher.DECRYPT_MODE, seckey2);
        byte [] dec_msg2=cipher4.doFinal(enc_msg);
        String s=new String(dec_msg2);

        //RSA
        KeyPairGenerator keygen3= KeyPairGenerator.getInstance("RSA")
        keygen3.initialize(1024);
        KeyPair keypair=keygen3.generateKeyPair();
        PublicKey publickey=keypair.getPublic();
        PrivateKey privatekey=keypair.getPrivate();

        Cipher cipher5 = Cipher.getInstance("RSA");
        cipher5.init(Cipher.ENCRYPT_MODE, publickey);
        byte[] enc_msg2=cipher.doFinal(plaintext.getBytes());

        Cipher cipher6=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privatekey);
        byte []  dec_msg3=cipher6.doFinal(enc_msg2);
        String s1=new String(dec_msg3);

        //SHA-512

        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hash= digest.digest(plaintext.getBytes());
        StringBuilder s2= new StringBuilder(100);
        for(byte b:hash){
            String hex = Integer.toString(b, 16);
            s2.append(hex);
        }

        //SHA-256

        MessageDigest digest2 = MessageDigest.getInstance("SHA-256");
        byte[] hash2 = digest2.digest(plaintext.getBytes());
        StringBuilder s3= new StringBuilder(100);
        for(byte b:hash2){
            String hex = Integer.toString(b, 16);
            s3.append(hex);
        }

        //MD5
        
        MessageDigest digest3 = MessageDigest.getInstance("MD5");
        byte[] hash3 = digest3.digest(plaintext.getBytes());
        StringBuilder s4= new StringBuilder(100);
        for(byte b:hash3){
            String hex = Integer.toString(b, 16);
            s4.append(hex);
        }

        //HMAC-SHA-256
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec("secret_key".getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        
        byte[] hash4 = sha256_HMAC.doFinal(plaintext.getBytes());
        StringBuilder s5= new StringBuilder(100);
        for(byte b:hash4){
            String hex = Integer.toString(b, 16);
            s5.append(hex);
        }
    }
}
