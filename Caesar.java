
import java.util.*;

public class Caesar {
    public static StringBuilder caesar(String s) {
        int k = 3;
        int len = s.length();
        int ord_a = (int) 'a';
        int ord_A = (int) 'A';
        int ord_z = (int) 'z';
        int ord_Z = (int) 'Z';
        int[] arr = new int[len];
        StringBuilder res = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (c + k > ord_z) {
                    int j = (int) c;
                    j = (j + k) % ord_z + ord_a;
                    arr[i] = j;
                } else {
                    int j = (int) c;
                    j += k;
                    arr[i] = j;
                }
            } else if (c >= 'A' && c <= 'Z') {
                if (c + k > ord_Z) {
                    int j = (int) c;
                    j = (j + k) % ord_Z + ord_A;
                    arr[i] = j;
                } else {
                    int j = (int) c;
                    j += k;
                    arr[i] = j;
                }
            } else {
                arr[i] = 0;
            }
        }
        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                res.append(" ");
            } else if (arr[i] > 0) {
                char x = (char) arr[i];
                res.append(x);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String x = "Adittya Narayan";
        System.out.println(caesar(x));
    }
}
