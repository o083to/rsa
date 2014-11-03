package algoritm;

import algoritm.rsa.RSAUtils;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by anna on 02.11.14.
 */
public class RSAUtilsTest {

    @Test
    public void hideBigIntegerTest() {
        BigInteger bigInteger = new BigInteger("12345123451234512345123451234512345");
        RSAUtils.hideBigInteger(bigInteger);
        assertEquals("0", bigInteger.toString());
    }
}
