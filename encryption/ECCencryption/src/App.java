import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class App {

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyGen.initialize(ecSpec, new SecureRandom());
        return keyGen.generateKeyPair();
    }

    public static SecretKey deriveSharedSecret(PrivateKey privateKey, PublicKey publicKey) throws Exception {
        KeyAgreement keyAgree = KeyAgreement.getInstance("ECDH");
        keyAgree.init(privateKey);
        keyAgree.doPhase(publicKey, true);
        byte[] sharedSecret = keyAgree.generateSecret();

        MessageDigest hash = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = hash.digest(sharedSecret);
        return new SecretKeySpec(keyBytes, 0, 16, "AES");
    }

    public static String encrypt(String data, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] cipherText = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

    public static void main(String[] args) throws Exception {
        try {

            KeyPair aliceKeyPair = generateKeyPair();
            KeyPair bobKeyPair = generateKeyPair();

            SecretKey aliceSharedSecret = deriveSharedSecret(aliceKeyPair.getPrivate(), bobKeyPair.getPublic());
            SecretKey bobSharedSecret = deriveSharedSecret(bobKeyPair.getPrivate(), aliceKeyPair.getPublic());

            System.out.println("¿Claves compartidas iguales? " + aliceSharedSecret.equals(bobSharedSecret));

            String data = "{\"msg\":\"Hello, World!\"}";
            byte[] iv = new byte[12];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            String cipherText = encrypt(data, aliceSharedSecret, iv);
            System.out.println("Texto encriptado: " + cipherText);

            String decryptedText = decrypt(cipherText, bobSharedSecret, iv);
            System.out.println("Texto desencriptado: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
