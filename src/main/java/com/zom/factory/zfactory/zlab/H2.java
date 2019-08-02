package com.zom.factory.zfactory.zlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2 {

    public static void main(String[] a) throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
                getConnection("jdbc:h2:tcp://192.168.0.222:9092/smartbi;SCHEMA=jxpg_smartbi;MULTI_THREADED=1;DB_CLOSE_DELAY=-1", "sa", "");
        // add application code here
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM JYJG_MDB_GD.DIM_CODE ");
        while (rs.next()) {
            System.out.println(rs.getString("DM_ID") + "," + rs.getString("DM_NAME"));
        }
        conn.close();
    }

}
