package fsr.banque.io.gestionBanque.service.security;

import fsr.banque.io.gestionBanque.exceptions.InvalidHashPasswordException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface HashPasswordContrat {

     public String hashPassword( final String password ) throws InvalidHashPasswordException;
}
