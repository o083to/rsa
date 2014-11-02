package algoritm;

import java.math.BigInteger;

/**
 * Открытый ключ.
 * Created by anna on 31.10.14.
 */
public class PublicKey {

    private BigInteger e;
    private BigInteger n;

    public PublicKey(BigInteger e, BigInteger n) {
        this.e = e;
        this.n = n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }

    public byte[] encrypt(byte[] plainText) {
        byte[] cipherText = RSAUtils.modPowByte(plainText, e, n);
        for (int i = 0; i < plainText.length; i++) {
            plainText[i] = 0;
        }
        return cipherText;
    }

}
