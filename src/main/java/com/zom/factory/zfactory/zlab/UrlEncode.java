package com.zom.factory.zfactory.zlab;

import com.zom.factory.utils.DateFormat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UrlEncode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        test();
    }

    public static void test() throws UnsupportedEncodingException {
        String str = "test test://test";

        String str2 = URLEncoder.encode(str, "utf-8");

        System.out.println(str2);
        System.out.println(URLDecoder.decode(str2, "utf-8"));

    }


}
