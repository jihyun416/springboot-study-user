package com.jessy.user.util;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AESUtil {
    public static final String AES_KEY = "safe-diilabs-aes";
    public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
        try {
            final byte[] finalKey = new byte[16];
            int i = 0;
            for(byte b : key.getBytes(encoding))
                finalKey[i++%16] ^= b;
            return new SecretKeySpec(finalKey, "AES");
        } catch(UnsupportedEncodingException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * 암호화
     * @param text
     * @return
     */
    public static String encrypt(String text) {
        if(text!=null) {
            try {
                final Cipher encryptCipher = Cipher.getInstance("AES");
                encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(AES_KEY, "UTF-8"));
                return new String(Hex.encodeHex(encryptCipher.doFinal(text.getBytes("UTF-8")))).toUpperCase();
            } catch(Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
    public static String decrypt(String text) {
        if(text!=null) {
            try {
                final Cipher decryptCipher = Cipher.getInstance("AES");
                decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(AES_KEY, "UTF-8"));
                return new String(decryptCipher.doFinal(Hex.decodeHex(text.toCharArray())));
            } catch(Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
