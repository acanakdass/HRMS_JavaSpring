package javaProjects.HRMS.core.utilities.security.Helpers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionHelper implements EncryptionService {

    private final PasswordEncoder passwordEncoder;

    public EncryptionHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public String EncodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
