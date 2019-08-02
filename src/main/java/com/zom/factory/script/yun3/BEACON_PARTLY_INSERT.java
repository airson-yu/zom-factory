package com.zom.keep.tech.script.yun3;

import org.springframework.util.StringUtils;

public class BEACON_PARTLY_INSERT {

    public static void main(String[] args) {
        generateInsertSQL();
    }

    public static void generateInsertSQL() {
        StringBuilder sb = new StringBuilder(1000);
        String sql_base = "INSERT INTO `rtv_beacon`(`beaconNo`, `bind_loc_id`, `start_time`, `work_status`, `change_battery_time`, `battery_info`, `update_time`, `power_status`, `mac_addres`, `name`) " +
                "VALUES ";
        sb.append(sql_base);

        //
        // ('27', '8', '2019-01-10 00:00:00', 0, '2019-03-10 00:00:00', NULL, '2019-02-17 15:18:26', 100, 'A0:E6:F8:25:D7:91', '积水潭51');
        //('8986043201187962500', '1', 1, '2019-02-28 15:14:00', 0, '', NULL, 3, 0, 0),;";

        String[] data = {
                "A0:E6:F8:25:00:D6,83,金安桥01",
                "A0:E6:F8:25:00:D4,83,金安桥02",
                "A0:E6:F8:25:00:D3,83,金安桥03",
                "A0:E6:F8:25:00:D2,83,金安桥04",
                "A0:E6:F8:25:00:C7,83,金安桥05",
                "A0:E6:F8:25:00:CA,83,金安桥06",
                "A0:E6:F8:25:00:C6,83,金安桥07",
                "A0:E6:F8:25:00:CB,83,金安桥08",
                "A0:E6:F8:25:00:CD,82,四道桥01",
                "A0:E6:F8:25:00:C9,82,四道桥02",
                "A0:E6:F8:25:00:BF,82,四道桥03",
                "A0:E6:F8:25:00:CE,82,四道桥04",
                "A0:E6:F8:25:00:CF,82,四道桥05",
                "A0:E6:F8:25:00:C8,82,四道桥06",
                "A0:E6:F8:25:00:33,82,四道桥07",
                "A0:E6:F8:25:00:2A,82,四道桥08",
                "A0:E6:F8:25:00:15,81,桥户营01",
                "A0:E6:F8:25:00:07,81,桥户营02",
                "A0:E6:F8:25:00:BE,81,桥户营03",
                "A0:E6:F8:25:00:C3,81,桥户营04",
                "A0:E6:F8:25:00:32,81,桥户营05",
                "A0:E6:F8:25:00:C5,81,桥户营06",
                "A0:E6:F8:25:00:BD,81,桥户营07",
                "A0:E6:F8:25:00:C2,81,桥户营08",
                "A0:E6:F8:25:00:C1,80,上岸01",
                "A0:E6:F8:25:00:CC,80,上岸02",
                "A0:E6:F8:25:00:C0,80,上岸03",
                "A0:E6:F8:25:00:C4,80,上岸04",
                "A0:E6:F8:25:00:17,80,上岸05",
                "A0:E6:F8:25:00:1F,80,上岸06",
                "A0:E6:F8:25:00:06,80,上岸07",
                "A0:E6:F8:25:00:24,80,上岸08",
                "A0:E6:F8:25:00:14,79,栗园庄01",
                "A0:E6:F8:25:00:28,79,栗园庄02",
                "A0:E6:F8:25:00:10,79,栗园庄03",
                "A0:E6:F8:25:00:18,79,栗园庄04",
                "A0:E6:F8:25:00:1D,79,栗园庄05",
                "A0:E6:F8:25:00:12,79,栗园庄06",
                "A0:E6:F8:25:00:1E,79,栗园庄07",
                "A0:E6:F8:25:00:11,79,栗园庄08",
                "A0:E6:F8:25:00:01,78,小园01",
                "A0:E6:F8:25:00:09,78,小园02",
                "A0:E6:F8:25:00:05,78,小园03",
                "A0:E6:F8:25:00:26,78,小园04",
                "A0:E6:F8:25:00:03,78,小园05",
                "A0:E6:F8:25:00:25,78,小园06",
                "A0:E6:F8:25:00:08,78,小园07",
                "A0:E6:F8:25:00:1C,78,小园08",
                "A0:E6:F8:25:00:04,77,石厂01",
                "A0:E6:F8:24:35:7F,77,石厂02",
                "A0:E6:F8:25:00:0C,77,石厂03",
                "A0:E6:F8:25:00:21,77,石厂04",
                "A0:E6:F8:25:00:0D,77,石厂05",
                "A0:E6:F8:25:00:02,77,石厂06",
                "A0:E6:F8:25:00:0A,77,石厂07",
                "A0:E6:F8:25:00:0E,77,石厂08",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        };

        String ts = "2019-08-02 00:00:00";
        String ts2 = "2019-12-01 00:00:00";
        int id_start = 643;

        // XXX XXX 记得更新 rtv_beacon_location 对应位置的绑定状态：
        // XXX XXX UPDATE `rtv_beacon_location` SET `status` = 1 WHERE id >= 65 and id <= 83;

        //('27', '8', '2019-01-10 00:00:00', 0, '2019-03-10 00:00:00', NULL, '2019-02-17 15:18:26', 100, 'A0:E6:F8:25:D7:91', '积水潭51');

        for (String i : data) {
            if (StringUtils.isEmpty(i)) {
                continue;
            }
            id_start++;
            String[] item = i.split(",");
            String mac = item[0];
            String loc_id = item[1];
            String name = item[2];
            sb.append("\n('" + id_start + "', '" + loc_id + "', '" + ts + "', 0, '" + ts2 + "', NULL, '" + ts + "', 100, '" + mac + "', '" + name + "'),");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        System.out.println(sb.toString());

    }

}


