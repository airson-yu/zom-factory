package com.zom.factory.zfactory.zlab;

import com.zom.factory.utils.DateFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SHAtest {

    public static void main(String[] args) {
        String encrytString = "api_key123456timestamp1572921481/rtv/api/3rd/v1/invite_tg";
        String securityKey = "C7CAE01FFDEE19DD6EB448EFE638A4C9D8FDFD3559";
        String encrypt = EncryptUtils.HMACSHA256(encrytString.getBytes(), securityKey.getBytes());
        System.out.println(encrypt);
    }

}
