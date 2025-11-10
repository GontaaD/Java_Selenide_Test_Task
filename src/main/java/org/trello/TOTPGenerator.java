package org.trello;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import org.apache.commons.codec.binary.Base32;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;

public class TOTPGenerator {
    public static String generateTOTP(String secretBase32) {
        try {
                byte[] secretBytes = new Base32().decode(secretBase32);

                TimeBasedOneTimePasswordGenerator totp =
                        new TimeBasedOneTimePasswordGenerator();

                SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacSHA1");

                int password = totp.generateOneTimePassword(keySpec, Instant.now());

                return String.format("%06d", password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
