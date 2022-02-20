package fsr.banque.io.gestionBanque.service.security;

import fsr.banque.io.gestionBanque.exceptions.InvalidHashPasswordException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class HashPasswordContratImpl implements HashPasswordContrat{

    final String salt = "1234";
    final byte[] saltBytes = salt.getBytes();
    final int iterations = 10000;
    final int keyLength = 255;

    @Override
    public String hashPassword(String password) throws InvalidHashPasswordException {


        try {
            char[] passwordChars = password.toCharArray();
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( passwordChars, saltBytes, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );

            return Hex.encodeHexString(res);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new InvalidHashPasswordException("L'algorithme cryptographique est demandé mais n'est pas disponible dans l'environnement et/ou La clé de cryptage est invalide");
        }
    }
}
