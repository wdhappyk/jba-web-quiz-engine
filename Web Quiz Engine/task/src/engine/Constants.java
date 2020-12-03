package engine;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Constants {
    public static String passwordSalt = BCrypt.gensalt();
}
