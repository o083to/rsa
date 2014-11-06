package algoritm.block;

import algoritm.utils.CipherUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anna on 04.11.14.
 */
public class BlockCipherTest {

    @Test
    public void encryptDecryptBlockTest128() {
        encryptDecryptBlockTest(128);
    }

    @Test
    public void encryptDecryptBlockTest256() {
        encryptDecryptBlockTest(256);
    }

    @Test
    public void encryptDecryptBlockTest512() {
        encryptDecryptBlockTest(512);
    }

    @Test
    public void encryptDecryptStringTest128() {
        encryptDecryptStringTest(128);
    }

    @Test
    public void encryptDecryptStringTest256() {
        encryptDecryptStringTest(256);
    }

    @Test
    public void encryptDecryptStringTest512() {
        encryptDecryptStringTest(512);
    }

    private void encryptDecryptStringTest(int bitLength) {
        String secret = "This is 1234567 string";
        System.out.println("Key bit length: " + bitLength);
        System.out.println("Message: " + secret);
        BlockCipher cipher = new BlockCipher(bitLength);

        byte[] secretBytes = secret.getBytes();
        int padSize = CipherUtils.paddingSize(secretBytes.length);
        System.out.println("Padding: " + padSize);

        byte[] encrypted = cipher.encrypt(secretBytes, true);
        String encStr = new String(encrypted);
        System.out.println("Encrypted: " + encStr);
        System.out.println("Length: " + encStr.length());

        byte[] decrypted = cipher.decrypt(encrypted, padSize);
        String result = new String(decrypted);
        System.out.println("Decrypted: " + result);
        System.out.println("Length: " + result.length());

        assertEquals(secret, result);
    }

    private void encryptDecryptBlockTest(int bitLength) {
        BlockCipher cipher = new BlockCipher(bitLength);
        System.out.println("Key bit length: " + bitLength);

        int[] source = {2030031994, 1029011971};
        System.out.println("Src left   : " + source[0]);
        System.out.println("Src right  : " + source[1]);

        int[] encrypted = new int[2];
        System.arraycopy(source, 0, encrypted, 0, 2);
        cipher.encryptBlock(encrypted, 0, 1);
        System.out.println("Enc left   : " + encrypted[0]);
        System.out.println("Enc right  : " + encrypted[1]);

        int[] decrypted =  new int[2];
        System.arraycopy(encrypted, 0, decrypted, 0, 2);
        cipher.decryptBlock(decrypted, 0, 1);
        assertArrayEquals(source, decrypted);
    }

}
