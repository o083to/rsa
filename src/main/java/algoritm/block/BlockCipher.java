package algoritm.block;

import algoritm.utils.CipherUtils;

import java.security.SecureRandom;

/**
 * Created by anna on 03.11.14.
 */

public class BlockCipher {

    public static final int ROUND_KEY_BIT_LENGTH = 32;
    public static final int BLOCK_SIZE = 8;

    private static final int INTEGER_SIZE = 4;
    private static final String INVALID_BIT_LENGTH_MESSAGE = "Bit length of key must be 128, 256 or 512. Actual length: ";

    private final int roundsCount;
    private final byte[] byteKeys;
    private final int[] keys;

    public BlockCipher(int bitLength) {
        if (bitLength == 128 || bitLength == 256 || bitLength == 512) {
            roundsCount = bitLength / ROUND_KEY_BIT_LENGTH;
            byteKeys = CipherUtils.generateRandomBytes(roundsCount * INTEGER_SIZE);
            keys = CipherUtils.convertBytesToInts(byteKeys);
        } else {
            throw new IllegalArgumentException(INVALID_BIT_LENGTH_MESSAGE + bitLength);
        }
    }

    public BlockCipher(byte[] keyBytes) {
        roundsCount = keyBytes.length / INTEGER_SIZE;
        if (roundsCount == 4 || roundsCount == 8 || roundsCount == 16) {
            byteKeys = keyBytes;
            keys = CipherUtils.convertBytesToInts(byteKeys);
        } else {
            throw new IllegalArgumentException(INVALID_BIT_LENGTH_MESSAGE + keyBytes.length * 8);
        }
    }

    public byte[] encrypt(byte[] source, boolean isLastBlock) {
        int[] blocks = CipherUtils.bytesToIntsWithPad(source);
        for (int i = 0; i < blocks.length; i += 2) {
            encryptBlock(blocks, i, i + 1);
        }
        byte[] bytes = CipherUtils.convertIntsToBytes(blocks);
        if (isLastBlock) {
            int padCount = CipherUtils.paddingSize(source.length);
            byte[] tmp = new byte[bytes.length + 1];
            System.arraycopy(bytes, 0, tmp, 0, bytes.length);
            tmp[tmp.length - 1] = (byte)padCount;
            bytes = tmp;
        }
        return bytes;
    }

    public byte[] decrypt(byte[] cipher, int padCount) {
        byte[] decrypted = decrypt(cipher);
        return CipherUtils.removePadding(decrypted, padCount);
    }

    public byte[] decrypt(byte[] cipher) {
        int[] blocks = CipherUtils.convertBytesToInts(cipher);
        for (int i = 0; i < blocks.length; i += 2) {
            decryptBlock(blocks, i, i + 1);
        }
        return CipherUtils.convertIntsToBytes(blocks);
    }

    void encryptBlock(int[] data, int left, int right) {
        for (int i = 0; i < roundsCount; i++) {
            int tmp = data[right] ^ function(data[left], keys[i]);
            data[right] = data[left];
            data[left] = tmp;
        }
    }

    void decryptBlock(int[] data, int left, int right) {
        for (int i = roundsCount - 1; i >= 0; i--) {
            int tmp = data[left] ^ function(data[right], keys[i]);
            data[left] = data[right];
            data[right] = tmp;
        }
    }

    public int getRoundsCount() {
        return roundsCount;
    }

    public byte[] getKey() {
        return byteKeys;
    }

    int function(int txt, int key) {
        return Integer.rotateLeft((key & 0xA55AAA55) | (txt & 0x5AA555AA), 16);
    }
}
