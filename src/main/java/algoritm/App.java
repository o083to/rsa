package algoritm;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Random;

public class App
{
    public static void main( String[] args ) throws UnsupportedEncodingException {
        int[] key = new int[10];
        int[] text = {
                 ByteBuffer.wrap("1фj".getBytes()).getInt()
                ,ByteBuffer.wrap(" m0*".getBytes()).getInt()
                ,ByteBuffer.wrap("хз".getBytes()).getInt()
                ,ByteBuffer.wrap("tEsT".getBytes()).getInt()
                ,ByteBuffer.wrap("аoP".getBytes()).getInt()
                ,ByteBuffer.wrap("Lоl".getBytes()).getInt()
                ,ByteBuffer.wrap("1k&U".getBytes()).getInt()
                ,ByteBuffer.wrap("АД".getBytes()).getInt()
                ,ByteBuffer.wrap("HeР".getBytes()).getInt()
                ,ByteBuffer.wrap("1Q()".getBytes()).getInt()
        };
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            key[i] = random.nextInt(Integer.MAX_VALUE);
        }
        int i = 0;
        for (int k : key) {
            System.out.println("Key: " + Integer.toBinaryString(k));
            System.out.println("Txt: " + Integer.toBinaryString(text[i]));
            System.out.println("____________________________________________");
            int res = function(text[i], k);
            System.out.println("Fnc: " + Integer.toBinaryString(res));
            System.out.println("Cnt: " + Integer.bitCount(res) + "\n");
        }
    }

    public static int function(int txt, int key) {
        return Integer.rotateLeft((key & 0xA55AAA55) | (txt & 0x5AA555AA), 16);
    }
}
