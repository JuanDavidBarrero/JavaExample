import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class App {

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public static byte[] generateIv() {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

    public static void main(String[] args) throws Exception {
        try {
            SecretKey key = generateKey();
            byte[] iv = generateIv();
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            String input = "{\"msg\":\"Hello, World!\"}";

            String algorithm = "AES/CBC/PKCS5Padding";
            String cipherText = encrypt(algorithm, input, key, ivSpec);
            System.out.println("Texto encriptado: " + cipherText);

            String plainText = decrypt(algorithm, cipherText, key, ivSpec);
            System.out.println("Texto desencriptado: " + plainText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
