import java.util.*;

public class HIll {

    public int[] Enc(int[][] Key, char p1, char p2, char p3) {
        int[] enc_arr = new int[3];
        int a = (int) p1 - 'A';
        int b = (int) p2 - 'A';
        int c = (int) p3 - 'A';

        enc_arr[0] = (a * Key[0][0] + b * Key[1][0] + c * Key[2][0]) % 26;
        enc_arr[1] = (a * Key[0][1] + b * Key[1][1] + c * Key[2][1]) % 26;
        enc_arr[2] = (a * Key[0][2] + b * Key[1][2] + c * Key[2][2]) % 26;

        return enc_arr;
    }

    public static void main(String[] args) {
        int[][] Key = {
            {17, 17, 5},
            {21, 18, 21},
            {2, 2, 19}
        };

        String Message = "HILL CIPHER";
        StringBuilder enc = new StringBuilder(Message.length());

        // Pad the message if its length is not a multiple of 3
        if (Message.length() % 3 != 0) {
            int padLength = 3 - (Message.length() % 3);
            for (int i = 0; i < padLength; i++) {
                Message += 'X'; // Pad with 'X'
            }
        }

        for (int i = 0; i < Message.length(); i += 3) {
            char p1 = Message.charAt(i);
            char p2 = Message.charAt(i + 1);
            char p3 = Message.charAt(i + 2);
            int[] enc_arr = new HIll().Enc(Key, p1, p2, p3);

            enc.append((char) (enc_arr[0] + 'A'));
            enc.append((char) (enc_arr[1] + 'A'));
            enc.append((char) (enc_arr[2] + 'A'));
        }

        System.out.println("Encrypted message: " + enc.toString());
    }
}
