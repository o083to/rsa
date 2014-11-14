package algoritm.files;

import algoritm.block.BlockCipher;
import algoritm.rsa.PrivateKey;
import algoritm.rsa.PublicKey;
import algoritm.utils.CipherUtils;

import java.io.*;

/**
 * Created by anna on 14.11.14.
 */
public class FileCipher {

    public static final int BUFFER_SIZE = 4096; // 4 kb

    public static void encrypt(String filename, PublicKey publicKey, int blockKeyLength) throws IOException {
        try(BufferedInputStream input = new BufferedInputStream(new FileInputStream(filename));
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(filename + ".rsa"))) {

            BlockCipher blockCipher = new BlockCipher(blockKeyLength);
            byte[] sessionKey = blockCipher.getKey();
            byte[] encryptedSessionKey = publicKey.encrypt(sessionKey);
            output.write(CipherUtils.convertIntsToBytes(new int [] {encryptedSessionKey.length}));
            output.write(encryptedSessionKey);

            long fileSize = new File(filename).length();
            long completeBlocksCnt = fileSize / BUFFER_SIZE;
            int lastBlockSize = (int)(fileSize % BUFFER_SIZE);
            if (lastBlockSize == 0) {
                completeBlocksCnt--;
                lastBlockSize = BUFFER_SIZE;
            }

            byte[] buffer = new byte[BUFFER_SIZE];
            for (int i = 0; i < completeBlocksCnt; i++) {
                input.read(buffer);
                output.write(blockCipher.encrypt(buffer, false));
            }

            byte[] last = new byte[lastBlockSize];
            input.read(last);
            output.write(blockCipher.encrypt(last, true));
        }
    }

    public static void decrypt(String filename, String resFileName, PrivateKey privateKey) throws IOException {
        try(BufferedInputStream input = new BufferedInputStream(new FileInputStream(filename));
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(resFileName))) {

            byte[] encKeyLengthB = new byte[4];
            input.read(encKeyLengthB);
            int encKeyLength = CipherUtils.convertBytesToInts(encKeyLengthB)[0];

            byte[] encKey = new byte[encKeyLength];
            input.read(encKey);

            byte[] sessionKey = privateKey.decrypt(encKey);
            BlockCipher blockCipher = new BlockCipher(sessionKey);

            long fileSize = new File(filename).length();
            long dataSize = fileSize - encKeyLength - 4;
            long completeBlocksCount = dataSize / BUFFER_SIZE;
            int lastBlockSize = (int)(dataSize % BUFFER_SIZE);
            if (lastBlockSize <= 1) {
                completeBlocksCount--;
                lastBlockSize += BUFFER_SIZE;
            }

            byte[] buffer = new byte[BUFFER_SIZE];
            for (int i = 0; i < completeBlocksCount; i++) {
                input.read(buffer);
                output.write(blockCipher.decrypt(buffer));
            }

            byte[] last = new byte[lastBlockSize - 1];
            input.read(last);
            int padSize = input.read();
            output.write(blockCipher.decrypt(last, padSize));
        }
    }
}
