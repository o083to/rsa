package algoritm;

import java.math.BigInteger;

public class App
{
    public static void main( String[] args ) {

//        RSAKeyPair rsaKeyPair = new RSAKeyPair(128);
//        System.out.println("P: " + rsaKeyPair.getP());
//        System.out.println("P len: " + rsaKeyPair.getP().bitLength());
//        System.out.println("Q: " + rsaKeyPair.getQ());
//        System.out.println("Q len: " + rsaKeyPair.getQ().bitLength());
//        System.out.println("N: " + rsaKeyPair.getN());
//        System.out.println("N len: " + rsaKeyPair.getN().bitLength());
//        System.out.println("Fi: " + rsaKeyPair.getFi());
//        System.out.println("Fi len: " + rsaKeyPair.getFi().bitLength());
//        System.out.println("E: " + rsaKeyPair.getE());
//        System.out.println("E len: " + rsaKeyPair.getE().bitLength());
//        System.out.println("D: " + rsaKeyPair.getD());
//        System.out.println("D len: " + rsaKeyPair.getD().bitLength());

        byte[] b = {0, 1};
        BigInteger bigInteger = new BigInteger(b);
        System.out.println(bigInteger);
        System.out.println(bigInteger.bitLength());
        System.out.println(bigInteger.bitCount());
        System.out.println(bigInteger.toByteArray().length);
    }
}
