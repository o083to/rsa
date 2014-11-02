package algoritm;

import java.lang.reflect.Field;
import java.math.BigInteger;

/**
 * Created by anna on 02.11.14.
 */
public class RSAUtils {

    private static Field bigIntegerDataField;

    public static byte[] modPowByte(byte[] arg, BigInteger e, BigInteger n) {
        BigInteger source = new BigInteger(arg);
        BigInteger result = source.modPow(e, n);
        hideBigInteger(source);
        return result.toByteArray();
    }

    public static void hideBigInteger(BigInteger source) {
        try {
            if (bigIntegerDataField == null) {
                bigIntegerDataField = BigInteger.class.getDeclaredField("mag");
                bigIntegerDataField.setAccessible(true);
            }
            int[] mag = (int[])bigIntegerDataField.get(source);
            for (int i = 0; i < mag.length; i++) {
                mag[i] = 0;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("[Warning] Parts of the plaintext may remain in memory");
        }
    }
}
