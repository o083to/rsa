package algoritm;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by anna on 31.10.14.
 */
public class PublicKey {

    private BigInteger e;
    private BigInteger n;
    private int blockLengthInBytes;

    public PublicKey(BigInteger e, BigInteger n) {
        this.e = e;
        this.n = n;
        blockLengthInBytes = n.bitLength() / 8 - 1;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }

    public int getBlockLengthInBytes() {
        return blockLengthInBytes;
    }

    public String encryptString(String plainText) {
        byte[] plainBytes = plainText.getBytes();
        ArrayList<Byte> cipherBytes;

        return "lol";
    }
}
