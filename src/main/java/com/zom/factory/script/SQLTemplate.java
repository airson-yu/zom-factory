package com.zom.factory.script;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class SQLTemplate {

    public static void main(String[] args) {
        generateInsertSQL();
    }

    public static void generateInsertSQL() {
        StringBuilder sb = new StringBuilder(1000);
        String sql_base = "INSERT INTO `rtvitrunk`.`rtv_user_group_map`(`group_id`, `user_id`, `group_right`) VALUES ";
        sb.append(sql_base);

        //"(73806, 65876, 20);";

        int[] groups = {
                73786,
                73787,
                73788,
                73789,
                73790,
                73791,
                73792,
                73793,
                73794,
                73795,
                73796,
                73797,
                73798,
                73799,
                73800,
                73801,
                73802,
                73803,
                73804,
                73805,
                73806,
                73807,
                73808,
                73813
        };

        int[] users = {
                65537,
                65545,
                65546,
                65547,
                65548,
                65554,
                65555,
                65556,
                65557,
                65562,
                65563,
                65564,
                65565,
                65570,
                65571,
                65572,
                65578,
                65579,
                65580,
                65588,
                65589,
                65595,
                65596,
                65602,
                65603,
                65604,
                65605,
                65611,
                65612,
                65613,
                65620,
                65621,
                65630,
                65631,
                65637,
                65638,
                65639,
                65640,
                65641,
                65649,
                65650,
                65651,
                65659,
                65660,
                65661,
                65670,
                65671,
                65679,
                65680,
                65687,
                65688,
                65697,
                65698,
                65708,
                65709,
                65717,
                65718,
                65726,
                65727,
                65728,
                65729,
                65736,
                65737,
                65738,
                65739,
                65740,
                65917,
                65918,
                65925,
                65926,
                65938,
                65939
        };

        //int tg_len = groups.length;
        int right = 20;
        for (int i : groups) {
            if (73813 == i) {
                right = 10;
            }
            for (int j : users) {
                sb.append("\n(" + i + ", " + j + ", " + right + "),");
            }

        }
        sb.append(";");

        System.out.println(sb.toString());

    }

}


        