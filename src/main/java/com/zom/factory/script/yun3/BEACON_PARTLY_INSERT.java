package com.zom.factory.script.yun3;

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
                //"信标MAC地址,位置信息ID,信标名称",
                //"52:44:4C:4E:02:A0,132,双店路672",
                //"A0:E6:F8:25:00:9B,138,瀛海8S-104",
                //"信标MAC地址,位置信息ID,信标名称",
                "52:44:4C:4E:02:BD,48,福田701",
                "52:44:4C:4E:02:BE,48,福田702",
                "52:44:4C:4E:02:BF,48,福田703",
                "52:44:4C:4E:02:C0,48,福田704",
                "52:44:4C:4E:02:C1,48,福田705",
                "52:44:4C:4E:02:C2,48,福田706",
                "52:44:4C:4E:02:C3,48,福田707",
                "52:44:4C:4E:02:C4,48,福田708",
                "52:44:4C:4E:02:C5,49,车公庙709",
                "52:44:4C:4E:02:C6,49,车公庙710",
                "52:44:4C:4E:02:C7,49,车公庙711",
                "52:44:4C:4E:02:C8,49,车公庙712",
                "52:44:4C:4E:02:C9,49,车公庙713",
                "52:44:4C:4E:02:CA,49,车公庙714",
                "52:44:4C:4E:02:CB,49,车公庙715",
                "52:44:4C:4E:02:CC,49,车公庙716",
                "52:44:4C:4E:02:CD,50,红树湾南717",
                "52:44:4C:4E:02:CE,50,红树湾南718",
                "52:44:4C:4E:02:CF,50,红树湾南719",
                "52:44:4C:4E:02:D0,50,红树湾南720",
                "52:44:4C:4E:02:D1,50,红树湾南721",
                "52:44:4C:4E:02:D2,50,红树湾南722",
                "52:44:4C:4E:02:D3,50,红树湾南723",
                "52:44:4C:4E:02:D4,50,红树湾南724",
                "52:44:4C:4E:02:D5,51,后海725",
                "52:44:4C:4E:02:D6,51,后海726",
                "52:44:4C:4E:02:D7,51,后海727",
                "52:44:4C:4E:02:D8,51,后海728",
                "52:44:4C:4E:02:D9,51,后海729",
                "52:44:4C:4E:02:DA,51,后海730",
                "52:44:4C:4E:02:DB,51,后海731",
                "52:44:4C:4E:02:DC,51,后海732",
                "52:44:4C:4E:02:DD,52,南山733",
                "52:44:4C:4E:02:DE,52,南山734",
                "52:44:4C:4E:02:DF,52,南山735",
                "52:44:4C:4E:02:E0,52,南山736",
                "52:44:4C:4E:02:E1,52,南山737",
                "52:44:4C:4E:02:E2,52,南山738",
                "52:44:4C:4E:02:E3,52,南山739",
                "52:44:4C:4E:02:E4,52,南山740",
                "52:44:4C:4E:02:E5,53,前海湾741",
                "52:44:4C:4E:02:E6,53,前海湾742",
                "52:44:4C:4E:02:E7,53,前海湾743",
                "52:44:4C:4E:02:E8,53,前海湾744",
                "52:44:4C:4E:02:E9,53,前海湾745",
                "52:44:4C:4E:02:EA,53,前海湾746",
                "52:44:4C:4E:02:EB,53,前海湾747",
                "52:44:4C:4E:02:EC,53,前海湾748",
                "52:44:4C:4E:02:ED,54,宝安749",
                "52:44:4C:4E:02:EE,54,宝安750",
                "52:44:4C:4E:02:EF,54,宝安751",
                "52:44:4C:4E:02:F0,54,宝安752",
                "52:44:4C:4E:02:F1,54,宝安753",
                "52:44:4C:4E:02:F2,54,宝安754",
                "52:44:4C:4E:02:F3,54,宝安755",
                "52:44:4C:4E:02:F4,54,宝安756",
                "52:44:4C:4E:02:F5,55,碧海湾757",
                "52:44:4C:4E:02:F6,55,碧海湾758",
                "52:44:4C:4E:02:F7,55,碧海湾759",
                "52:44:4C:4E:02:F8,55,碧海湾760",
                "52:44:4C:4E:02:F9,55,碧海湾761",
                "52:44:4C:4E:02:FA,55,碧海湾762",
                "52:44:4C:4E:02:FB,55,碧海湾763",
                "52:44:4C:4E:02:FC,55,碧海湾764",
                "52:44:4C:4E:02:FD,56,机场765",
                "52:44:4C:4E:02:FE,56,机场766",
                "52:44:4C:4E:02:FF,56,机场767",
                "52:44:4C:4E:03:00,56,机场768",
                "52:44:4C:4E:03:01,56,机场769",
                "52:44:4C:4E:03:02,56,机场770",
                "52:44:4C:4E:03:03,56,机场771",
                "52:44:4C:4E:03:04,56,机场772",
                "52:44:4C:4E:03:05,57,机场北773",
                "52:44:4C:4E:03:06,57,机场北774",
                "52:44:4C:4E:03:07,57,机场北775",
                "52:44:4C:4E:03:08,57,机场北776",
                "52:44:4C:4E:03:09,57,机场北777",
                "52:44:4C:4E:03:0A,57,机场北778",
                "52:44:4C:4E:03:0B,57,机场北779",
                "52:44:4C:4E:03:0C,57,机场北780",
                "52:44:4C:4E:03:0D,58,福永781",
                "52:44:4C:4E:03:0E,58,福永782",
                "52:44:4C:4E:03:0F,58,福永783",
                "52:44:4C:4E:03:10,58,福永784",
                "52:44:4C:4E:03:11,58,福永785",
                "52:44:4C:4E:03:12,58,福永786",
                "52:44:4C:4E:03:13,58,福永787",
                "52:44:4C:4E:03:14,58,福永788",
                "52:44:4C:4E:03:15,59,桥头789",
                "52:44:4C:4E:03:16,59,桥头790",
                "52:44:4C:4E:03:17,59,桥头791",
                "52:44:4C:4E:03:18,59,桥头792",
                "52:44:4C:4E:03:19,59,桥头793",
                "52:44:4C:4E:03:1A,59,桥头794",
                "52:44:4C:4E:03:1B,59,桥头795",
                "52:44:4C:4E:03:1C,59,桥头796",
                "52:44:4C:4E:03:1D,60,塘尾797",
                "52:44:4C:4E:03:1E,60,塘尾798",
                "52:44:4C:4E:03:1F,60,塘尾799",
                "52:44:4C:4E:03:20,60,塘尾800",
                "52:44:4C:4E:03:21,60,塘尾801",
                "52:44:4C:4E:03:22,60,塘尾802",
                "52:44:4C:4E:03:23,60,塘尾803",
                "52:44:4C:4E:03:24,60,塘尾804",
                "52:44:4C:4E:03:25,61,马安山805",
                "52:44:4C:4E:03:26,61,马安山806",
                "52:44:4C:4E:03:27,61,马安山807",
                "52:44:4C:4E:03:28,61,马安山808",
                "52:44:4C:4E:03:29,61,马安山809",
                "52:44:4C:4E:03:2A,61,马安山810",
                "52:44:4C:4E:03:2B,61,马安山811",
                "52:44:4C:4E:03:2C,61,马安山812",
                "52:44:4C:4E:03:2D,62,沙井813",
                "52:44:4C:4E:03:2E,62,沙井814",
                "52:44:4C:4E:03:2F,62,沙井815",
                "52:44:4C:4E:03:30,62,沙井816",
                "52:44:4C:4E:03:31,62,沙井817",
                "52:44:4C:4E:03:32,62,沙井818",
                "52:44:4C:4E:03:33,62,沙井819",
                "52:44:4C:4E:03:34,62,沙井820",
                "52:44:4C:4E:03:35,63,后亭821",
                "52:44:4C:4E:03:36,63,后亭822",
                "52:44:4C:4E:03:37,63,后亭823",
                "52:44:4C:4E:03:38,63,后亭824",
                "52:44:4C:4E:03:39,63,后亭825",
                "52:44:4C:4E:03:3A,63,后亭826",
                "52:44:4C:4E:03:3B,63,后亭827",
                "52:44:4C:4E:03:3C,63,后亭828",
                "52:44:4C:4E:03:3D,64,松岗829",
                "52:44:4C:4E:03:3E,64,松岗830",
                "52:44:4C:4E:03:3F,64,松岗831",
                "52:44:4C:4E:03:40,64,松岗832",
                "52:44:4C:4E:03:41,64,松岗833",
                "52:44:4C:4E:03:42,64,松岗834",
                "52:44:4C:4E:03:43,64,松岗835",
                "52:44:4C:4E:03:44,64,松岗836",
                "52:44:4C:4E:03:45,65,碧头837",
                "52:44:4C:4E:03:46,65,碧头838",
                "52:44:4C:4E:03:47,65,碧头839",
                "52:44:4C:4E:03:48,65,碧头840",
                "52:44:4C:4E:03:49,65,碧头841",
                "52:44:4C:4E:03:4A,65,碧头842",
                "52:44:4C:4E:03:4B,65,碧头843",
                "52:44:4C:4E:03:4C,65,碧头844",
                ""
        };

        String ts = "2020-05-07 00:00:00";
        String ts2 = "2020-11-07 00:00:00";       //相差半年
        int id_start = 24;

        // XXX XXX 记得更新 rtv_beacon_location 对应位置的绑定状态：
        // XXX XXX UPDATE `rtv_beacon_location` SET `status` = 1 WHERE id >= 65 and id <= 83;
        /**
         * UPDATE `rtv_beacon_location` SET `status` = 1 WHERE id >= 88 and id <= 103;
         * UPDATE `rtv_beacon_location` SET `status` = 1 WHERE id >= 104 and id <= 122;
         * UPDATE `rtv_beacon_location` SET `status` = 1 WHERE id >= 126 and id <= 138;
         * UPDATE `rtv_beacon_location` SET `status` = 1 WHERE id >= 48 and id <= 132;
         * UPDATE `rtv_beacon_location` SET `status` = 1 WHERE id >= 48 and id <= 65;
         */

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


