package algoritm.block;

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
    public void lol() {
        String lol = "This is ****ing string";
        BlockCipher cipher = new BlockCipher(128);
        byte[] b1 = cipher.encrypt(lol.getBytes());
        String s1 = new String(b1);
        System.out.println("\n\n\n" + s1.length());
        byte[] b2 = cipher.decrypt(b1);
        String s2 = new String(b2);
        System.out.println(s2.length());
        assertEquals(lol, s2);
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
