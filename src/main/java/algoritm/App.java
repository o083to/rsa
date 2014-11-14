package algoritm;

import algoritm.files.FileCipher;
import algoritm.files.KeysGenerator;
import algoritm.rsa.PrivateKey;
import algoritm.rsa.PublicKey;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
//        KeysGenerator.generate("test", 4096);
//        PublicKey publicKey = KeysGenerator.loadPublicKey("test.public");
//        PrivateKey privateKey = KeysGenerator.loadPrivateKey("test.private");
//
//        FileCipher.encrypt("1.JPG", publicKey, 512);
//        FileCipher.decrypt("1.JPG.rsa", "2.JPG", privateKey);
        if (args.length == 0) {
            help();
        } else {
            switch (args[0]) {
                case "generate" : generate(args);
                    break;
                case "encrypt" : encrypt(args);
                    break;
                case "decrypt" : decrypt(args);
                    break;
                default: help();
            }
        }
    }

    private static void generate(String[] args) throws IOException {
        String keyName = args[1];
        int keyLength = Integer.parseInt(args[2]);
        KeysGenerator.generate(keyName, keyLength);
    }

    private static void encrypt(String[] args) throws IOException {
        int blockKeyLength = Integer.parseInt(args[1]);
        String inputFileName = args[2];
        String keyFileName = args[3];
        PublicKey publicKey = KeysGenerator.loadPublicKey(keyFileName);
        FileCipher.encrypt(inputFileName, publicKey, blockKeyLength);
    }

    private static void decrypt(String[] args) throws IOException {
        String inputFileName = args[1];
        String resFileName = args[2];
        String keyFileName = args[3];
        PrivateKey privateKey = KeysGenerator.loadPrivateKey(keyFileName);
        FileCipher.decrypt(inputFileName, resFileName, privateKey);
    }

    private static void help() {
        System.out.println("generate  key_name  rsa_key_length_in_bits");
        System.out.println("encrypt  block_key_length_in_bits  file_name  key_file_name");
        System.out.println("decrypt  file_name  result_file_name  key_file_name");
    }
}
