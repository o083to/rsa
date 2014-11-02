package algoritm;

public class App
{
    public static void main( String[] args ) {

        RSAKeyPair rsaKeyPair = new RSAKeyPair(1024);
        System.out.println("P: " + rsaKeyPair.getP());
        System.out.println("P len: " + rsaKeyPair.getP().bitLength());
        System.out.println("Q: " + rsaKeyPair.getQ());
        System.out.println("Q len: " + rsaKeyPair.getQ().bitLength());
        System.out.println("N: " + rsaKeyPair.getN());
        System.out.println("N len: " + rsaKeyPair.getN().bitLength());
        System.out.println("Fi: " + rsaKeyPair.getFi());
        System.out.println("Fi len: " + rsaKeyPair.getFi().bitLength());
        System.out.println("E: " + rsaKeyPair.getE());
        System.out.println("E len: " + rsaKeyPair.getE().bitLength());
        System.out.println("D: " + rsaKeyPair.getD());
        System.out.println("D len: " + rsaKeyPair.getD().bitLength());

        String textStr = "System.out.println(1994);";
        byte[] textBytes = textStr.getBytes();
        PublicKey publicKey = rsaKeyPair.getPublicKey();
        byte[] cTextBytes = publicKey.encrypt(textBytes);
        String cipher = new String(cTextBytes);
        System.out.println(cipher);
        PrivateKey privateKey = rsaKeyPair.getPrivateKey();
        byte[] finalRes = privateKey.decrypt(cTextBytes);
        String resStr = new String(finalRes);
        System.out.println(resStr);
    }
}
