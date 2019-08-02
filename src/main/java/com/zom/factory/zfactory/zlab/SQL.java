package com.zom.factory.zfactory.zlab;

public class SQL {

    public static void main(String[] args) {

        String base = "INSERT INTO rolemenu( roleid,menuid,deleteflag )VALUES ";

        for (int i = 1; i < 88; i++) {
            base += "(1," + i + ",1) ,";
        }
        System.out.println(base);

    }
}
