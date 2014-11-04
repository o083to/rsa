package algoritm.utils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by anna on 04.11.14.
 */
public class CipherUtils {

    public static int[] convertBytesToInts(byte[] bytes) {
        IntBuffer intBuffer = ByteBuffer.wrap(bytes).asIntBuffer();
        int[] keys = new int[intBuffer.remaining()];
        intBuffer.get(keys);
        return keys;
    }

    public static int[] bytesToIntsPadding(byte[] bytes) {
        int padCount = 8 - (bytes.length % 8);
        if (padCount != 8) {
            byte[] tmp = new byte[bytes.length + padCount];
            System.arraycopy(bytes, 0, tmp, 0, bytes.length);
            bytes = tmp;
            for (int i = 1; i <= padCount; i++) {
                bytes[bytes.length - i] = (byte)padCount;
            }
        }
        IntBuffer intBuffer = ByteBuffer.wrap(bytes).asIntBuffer();
        int[] keys = new int[intBuffer.remaining()];
        intBuffer.get(keys);
        return keys;
    }

    public static byte[] removePadding(byte[] source) {
        int padCount = source[source.length - 1];
        for (int i = 2; i <= padCount; i++) {
            if (source[source.length - i] != padCount) {
                return source;
            }
        }
        byte[] res = new byte[source.length - padCount];
        System.arraycopy(source, 0, res, 0, res.length);
        return res;
    }

    public static byte[] convertIntsToBytes(int[] ints) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ints.length * 4);
        byteBuffer.asIntBuffer().put(ints);
        return byteBuffer.array();
    }
}
