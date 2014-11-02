package algoritm;

import java.math.BigInteger;

/**
 * Created by anna on 31.10.14.
 */
public class PrivateKey {

    private BigInteger d;
    private BigInteger n;

    public PrivateKey(BigInteger d, BigInteger n) {
        this.d = d;
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getN() {
        return n;
    }

    public byte[] decrypt(byte[] cipherText) {
        return RSAUtils.modPowByte(cipherText, d, n);
    }
}
