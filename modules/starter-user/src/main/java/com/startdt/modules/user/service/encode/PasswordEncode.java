package com.startdt.modules.user.service.encode;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordEncode {

    private final Pattern BCRYPT_PATTERN = Pattern
            .compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    /**
     * 循环Hash值，数值越大安全性越大
     */
    private static final int STRENGTH = 5;

    /**
     * 对用户密码进行BCrypt明文加密,相对于md5加密更安全，加密时间更长
     * @param rawPassword
     * @return
     */
    public String encode(CharSequence rawPassword) {
        return encode(rawPassword, STRENGTH);
    }

    public String encode(CharSequence rawPassword, int strength) {
        String salt;
        if (strength > 0) {
            salt = BCrypt.gensalt(strength);
        } else {
            salt = BCrypt.gensalt();
        }
        return BCrypt.hashpw(rawPassword.toString(), salt);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            return false;
        }
        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            return false;
        }
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}
