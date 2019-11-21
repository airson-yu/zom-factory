package com.zom.factory.zfactory.zlab;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 编码工具类 实现aes加密、解密
 */
public class EncryptUtils {

    /**
     * 密钥
     */
    private static final String KEY = "abcdefgabcdefg12";

    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        String content = "我爱你";
        System.out.println("加密前：" + content);

        System.out.println("加密密钥和解密密钥：" + KEY);

    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * rtv consoleUser 业务方法
     *
     * @param pd
     * @return
     */
    public static String encryptPassword(String pd) {
        //byte[] salt = Digests.generateSalt(SALT_SIZE);
        //user.setSalt(Encodes.encodeHex(salt));

        //		byte[] hashPassword = Digests.sha1(user.getPlain_password().getBytes(), salt, HASH_INTERATIONS);
        //		user.setUser_password(Encodes.encodeHex(hashPassword));
        // Below 2 lines keep no change for salt
        //		byte[] salt = Digests.generateSalt(SALT_SIZE);
        //		user.setSalt(Encodes.encodeHex(salt));
        String tmp_passwd = pd;
        String encrypt_passwd = "";
        int[] tmpNum = new int[tmp_passwd.length()];

        tmpNum[0] = tmp_passwd.charAt(0) ^ 0xEE;
        encrypt_passwd += String.format("%02x", tmpNum[0]);
        for (int i = 1; i < tmp_passwd.length(); i++) {
            tmpNum[i] = tmp_passwd.charAt(i) ^ tmpNum[i - 1];
            encrypt_passwd += String.format("%02x", tmpNum[i]);
            //		  encrypt_passwd += Integer.toHexString(tmpNum[i]);
        }
        return encrypt_passwd;
        // user.setUser_password(encrypt_passwd);
    }

    //HMACSHA256 加密
    public static String HMACSHA256(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

}
