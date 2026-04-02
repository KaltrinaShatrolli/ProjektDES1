import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class AlgoritmiDES {
    public static void main(String[] args) {
        try {
            // 1. Gjenerimi i çelësit në DES
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            SecretKey celesi = keygen.generateKey();
            System.out.println("Procesi Filloi");
            
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static byte[] operacioniCipher(byte[] data, SecretKey key, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, key);
        return cipher.doFinal(data);
    }
}