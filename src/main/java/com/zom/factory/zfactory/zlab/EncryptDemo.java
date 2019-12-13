package com.zom.factory.zfactory.zlab;

/**
 * TODO
 *
 * @author airson
 */
public class EncryptDemo {

    public static String decryptPassword(String encrypt_passwd) {
        String decrypt_passwd = "";
        if (encrypt_passwd != null && !encrypt_passwd.equals("")) {
            int[] tmpNum = new int[encrypt_passwd.length() / 2];
            tmpNum[0] = (Integer.valueOf(encrypt_passwd.substring(0, 2), 16)) ^ 0xEE;
            decrypt_passwd += (char) tmpNum[0];
            for (int i = 1; i < encrypt_passwd.length() / 2; i++) {
                tmpNum[i] = (Integer.valueOf(encrypt_passwd.substring(i * 2, i * 2 + 2), 16)) ^ (Integer.valueOf(encrypt_passwd.substring((i - 1) * 2, (i - 1) * 2 + 2), 16));
                decrypt_passwd += (char) tmpNum[i];
            }
        }
        return decrypt_passwd;
    }

    public static void main(String[] args) {
        System.out.println(decryptPassword("9ffe84f380f8"));
    }

}
