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

            String plainteksti = "Informatike e Aplikuar!";
            String tekstiEnkriptuar = enkriptoTekst(plainteksti, celesi);
            String tekstiDekriptuar = dekriptoTekst(tekstiEnkriptuar, celesi);

            System.out.println("\nTeksti");
            System.out.println("Teksti Origjinal: " + plainteksti);
            System.out.println("Teksti i Enkriptuar: " + tekstiEnkriptuar);
            System.out.println("Teksti i Dekriptuar: " + tekstiDekriptuar);

            // Enkriptimi i file
            String emriFajllit = "plaintext.txt";
            String permbajtja = "Ky eshte teksti qe ndodhet brenda file dhe do te enkriptohet me DES.";
            Files.write(Paths.get(emriFajllit), permbajtja.getBytes());

            
            byte[] fajlliByte = Files.readAllBytes(Paths.get(emriFajllit));
            byte[] fajlliEnkriptuarByte = operacioniCipher(fajlliByte, celesi, Cipher.ENCRYPT_MODE);
            Files.write(Paths.get("fajlli_enkriptuar.des"), fajlliEnkriptuarByte);

            // Dekriptimi i file
            byte[] teDhenatEnkriptuara = Files.readAllBytes(Paths.get("fajlli_enkriptuar.des"));
            byte[] fajlliDekriptuarByte = operacioniCipher(teDhenatEnkriptuara, celesi, Cipher.DECRYPT_MODE);
            Files.write(Paths.get("fajlli_i_dekriptuar.txt"), fajlliDekriptuarByte);

            System.out.println("\nFile");
            System.out.println("File u enkriptua dhe u rikthye me sukses!");
            System.out.println("Procesi Perfundoi");

        } catch (Exception e) 
        { e.printStackTrace(); 

        }
    }
     public static String enkriptoTekst(String data, SecretKey key) throws Exception {
        byte[] enkriptuar = operacioniCipher(data.getBytes(), key, Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(enkriptuar);
    }

    public static String dekriptoTekst(String encryptedData, SecretKey key) throws Exception {
        byte[] dekoduar = Base64.getDecoder().decode(encryptedData);
        byte[] dekriptuar = operacioniCipher(dekoduar, key, Cipher.DECRYPT_MODE);
        return new String(dekriptuar);
    }

    private static byte[] operacioniCipher(byte[] data, SecretKey key, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, key);
        return cipher.doFinal(data);
    }
}

     
    
  

 
    


