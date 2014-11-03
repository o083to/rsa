package algoritm;

import algoritm.rsa.PrivateKey;
import algoritm.rsa.PublicKey;
import algoritm.rsa.RSAKeyPair;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by anna on 03.11.14.
 */
public class RSAEncryptionTest {

    @Test
    public void test1024KeyLatin() {
        testKey(1024, "System.out.println(1024);");
    }

    @Test
    public void test1024KeyCyrillic() {
        testKey(1024, "Тест с русскими символами");
    }

    @Test
    public void test2048KeyLatin() {
        testKey(2048, "System.out.println(System.out.println(2048););");
    }

    @Test
    public void test2048KeyCyrillic() {
        testKey(2048, "Тест алгоритма с ключём 2048 бит и русскими символами");
    }

    @Test
    public void test4096KeyLatin() {
        testKey(4096, "Does it work? Key length is 4096 bit. We try to encrypt ascii symbols");
    }

    @Test
    public void test4096KeyCyrillic() {
        testKey(4096, "Попытка запустить шифрование с ключом 4096 бит. Самая большай длина ключа, используемая сейчас");
    }

    private void printInfo(RSAKeyPair keyPair) {
        System.out.println("P:\n" + keyPair.getP());
        System.out.println("Bit length: " + keyPair.getP().bitLength());

        System.out.println("Q:\n" + keyPair.getQ());
        System.out.println("Bit length: " + keyPair.getQ().bitLength());

        System.out.println("N:\n" + keyPair.getN());
        System.out.println("Bit length: " + keyPair.getN().bitLength());

        System.out.println("Fi:\n" + keyPair.getFi());
        System.out.println("Bit length: " + keyPair.getFi().bitLength());

        System.out.println("E:\n" + keyPair.getE());
        System.out.println("Bit length: " + keyPair.getE().bitLength());

        System.out.println("D:\n" + keyPair.getD());
        System.out.println("Bit length: " + keyPair.getD().bitLength());
    }

    private void testKey(int length, String message) {
        RSAKeyPair keyPair = new RSAKeyPair(length);
        printInfo(keyPair);

        PublicKey publicKey = keyPair.getPublicKey();
        PrivateKey privateKey = keyPair.getPrivateKey();

        System.out.println("Input message: " + message);
        byte[] plainBytes = message.getBytes();
        byte[] cipherBytes = publicKey.encrypt(plainBytes);
        String cipherMessage = new String(cipherBytes);
        System.out.println("Cipher message: " + cipherMessage);

        byte[] decryptedBytes = privateKey.decrypt(cipherBytes);
        String decryptedMessage = new String(decryptedBytes);
        System.out.println("Decrypted message: " + decryptedMessage);

        assertArrayEquals(message.getBytes(), decryptedBytes);
    }
}
