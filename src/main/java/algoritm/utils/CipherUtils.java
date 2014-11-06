package algoritm.utils;

import algoritm.block.BlockCipher;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.security.SecureRandom;

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

    public static int[] bytesToIntsWithPad(byte[] bytes) {
        int padCount = paddingSize(bytes.length);
        if (padCount != 0) {
            byte[] tmp = new byte[bytes.length + padCount];
            byte[] padding = generateRandomBytes(padCount);
            System.arraycopy(bytes, 0, tmp, 0, bytes.length);
            System.arraycopy(padding, 0, tmp, bytes.length, padding.length);
            bytes = tmp;
        }
        return convertBytesToInts(bytes);
    }

    public static int paddingSize(long dataSize) {
        return (int)(BlockCipher.BLOCK_SIZE - (dataSize % BlockCipher.BLOCK_SIZE)) % 8;
    }

    public static byte[] removePadding(byte[] source, int padCount) {
        byte[] res = new byte[source.length - padCount];
        System.arraycopy(source, 0, res, 0, res.length);
        return res;
    }

    public static byte[] convertIntsToBytes(int[] ints) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ints.length * 4);
        byteBuffer.asIntBuffer().put(ints);
        return byteBuffer.array();
    }

    public static byte[] generateRandomBytes(int count) {
        byte[] bytes = new byte[count];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }
}
