package algoritm.files;

import algoritm.rsa.PrivateKey;
import algoritm.rsa.PublicKey;
import algoritm.rsa.RSAKeyPair;

import java.io.*;
import java.math.BigInteger;

/**
 * Created by anna on 14.11.14.
 */
public class KeysGenerator {

    public static void generate(String name, int keyBitLength) {
        RSAKeyPair keyPair = new RSAKeyPair(keyBitLength);
        try(PrintWriter publicKeyWriter = new PrintWriter(name + ".public");
            PrintWriter privateKeyWriter = new PrintWriter(name + ".private")) {

            PublicKey publicKey = keyPair.getPublicKey();
            publicKeyWriter.println(publicKey.getN());
            publicKeyWriter.println(publicKey.getE());

            PrivateKey privateKey = keyPair.getPrivateKey();
            privateKeyWriter.println(privateKey.getN());
            privateKeyWriter.println(privateKey.getD());

        } catch (FileNotFoundException e) {
            System.err.println("Cannot create file with key");
            e.printStackTrace();
        }
    }

    public static PublicKey loadPublicKey(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            BigInteger n = new BigInteger(reader.readLine());
            BigInteger e = new BigInteger(reader.readLine());
            return new PublicKey(e, n);
        }
    }

    public static PrivateKey loadPrivateKey(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            BigInteger n = new BigInteger(reader.readLine());
            BigInteger d = new BigInteger(reader.readLine());
            return new PrivateKey(d, n);
        }
    }
}
