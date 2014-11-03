package algoritm.block;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

/**
 * Created by anna on 03.11.14.
 */

public class BlockCipher {

    public static final int ROUND_KEY_BIT_LENGTH = 32;

    private static final int INTEGER_SIZE = 4;
    private static final String INVALID_BIT_LENGTH_MESSAGE = "Bit length of key must be 128, 256 or 512. Actual length: ";

    private final int roundsCount;
    private final byte[] byteKeys;
    private final int[] keys;

    public BlockCipher(int bitLength) {
        if (bitLength == 128 || bitLength == 265 || bitLength == 512) {
            roundsCount = bitLength / ROUND_KEY_BIT_LENGTH;
            byteKeys = generateByteKeys(roundsCount * INTEGER_SIZE);
            keys = convertByteKeysToInt(byteKeys, roundsCount);
        } else {
            throw new IllegalArgumentException(INVALID_BIT_LENGTH_MESSAGE + bitLength);
        }
    }

    public BlockCipher(byte[] keyBytes) {
        roundsCount = keyBytes.length / INTEGER_SIZE;
        if (roundsCount == 4 || roundsCount == 8 || roundsCount == 16) {
            byteKeys = keyBytes;
            keys = convertByteKeysToInt(byteKeys, roundsCount);
        } else {
            throw new IllegalArgumentException(INVALID_BIT_LENGTH_MESSAGE + keyBytes.length * 8);
        }
    }

    public int getRoundsCount() {
        return roundsCount;
    }

    public byte[] getKey() {
        return byteKeys;
    }

    private byte[] generateByteKeys(int count) {
        byte[] byteKeys = new byte[count];
        SecureRandom random = new SecureRandom();
        random.nextBytes(byteKeys);
        return byteKeys;
    }

    private int[] convertByteKeysToInt(byte[] byteKeys, int count) {
        int[] keys = new int[count];
        for (int i = 0; i < byteKeys.length; i += INTEGER_SIZE) {
            keys[i] = ByteBuffer.wrap(byteKeys, i, INTEGER_SIZE).getInt();
        }
        return keys;
    }

    private int function(int txt, int key) {
        return Integer.rotateLeft((key & 0xA55AAA55) | (txt & 0x5AA555AA), 16);
    }
}
