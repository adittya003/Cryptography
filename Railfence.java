import java.util.*;

public class Railfence {
    public static void main(String[] args) {
        String msg="Railfence Cypher";
        int depth=2;
        StringBuilder enc=new StringBuilder(msg.length());
        for(int i=0;i<msg.length();i+=depth){
            enc.append(msg.charAt(i));
        }
        for(int i=depth-1;i<msg.length();i+=depth){
            enc.append(msg.charAt(i));
        }
        System.out.println("Encoded: "+enc);
    }
}
