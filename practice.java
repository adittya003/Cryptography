import javax.crypto.*;
import java.security.*;
import java.util.*;

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
    }
}
