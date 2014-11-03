package algoritm.rsa;

import java.lang.reflect.Field;
import java.math.BigInteger;

/**
 * Created by anna on 02.11.14.
 */
public class RSAUtils {

    private static Field bigIntegerDataField;

    public static byte[] modPowByte(byte[] arg, BigInteger e, BigInteger n) {
        BigInteger source = new BigInteger(1, arg);
        BigInteger result = source.modPow(e, n);
        hideBigInteger(source);
        return getBytesWithoutSign(result);
    }

    private static byte[] getBytesWithoutSign(BigInteger arg) {
        byte[] sourceArray = arg.toByteArray();
        if (sourceArray[0] != 0) {
            return sourceArray;
        } else {
            byte[] withoutSign = new byte[sourceArray.length - 1];
            System.arraycopy(sourceArray, 1, withoutSign, 0, withoutSign.length);
            return withoutSign;
        }
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
