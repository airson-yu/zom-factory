package com.zom.keep.tech.script.yun3;

public class GROUP_INSERT {

    public static void main(String[] args) {
        generateInsertSQL();
    }

    public static void generateInsertSQL() {
        StringBuilder sb = new StringBuilder(1000);
        String sql_base = "INSERT INTO `rtv_group`(`id`, `group_ts`, `owner_id`, `group_name`, `create_date`, `corp_id`, `rank`, `dcg`, `preconfig`, `status`, `zone_id`, `console_dtg`, `admin_id`, `ext_tgid`, `parent_type`, `parent_id`, `priority`, `timer`, `type`) \n" +
                "VALUES ";
        sb.append(sql_base);

        //(74125, 3, 0, '测试导入群组创建账号', '2019-02-28 14:11:16', 1, 0, 0, 1, 1, 1, 0, NULL, '', 1, 160, 15, 15, 0);";

        String[] data = {
                "组名",
                "应急巴沟",
                "应急火器营",
                "应急长春桥",
                "应急车道沟",
                "应急慈寿寺",
                "应急西钓鱼台",
                "应急公主坟",
                "应急莲花桥",
                "应急六里桥",
                "应急西局",
                "应急泥洼",
                "应急丰台站",
                "应急首经贸",
                "应急纪家庙",
                "应急草桥",
                "应急角门西",
                "应急角门东",
                "应急大红门",
                "应急石榴庄",
                "应急宋家庄",
                "应急成寿寺",
                "应急分钟寺",
                "应急十里河",
                "应急潘家园",
                "应急劲松",
                "应急双井",
                "应急国贸",
                "应急金台夕照",
                "应急呼家楼",
                "应急团结湖",
                "应急农业展览馆",
                "应急亮马桥",
                "应急三元桥",
                "应急太阳宫",
                "应急芍药居",
                "应急惠新西街南口",
                "应急安贞门",
                "应急北土城",
                "应急健德门",
                "应急牡丹园",
                "应急西土城",
                "应急知春路",
                "应急知春里",
                "应急海淀黄庄",
                "应急苏州街"
        };

        int id_start = 74167;
        int paren_unit = 160;
        String ts = "2019-03-01 10:00:00";

        for (String i : data) {
            id_start++;
            sb.append("\n(" + id_start + ", 1, 0, '" + i + "', '" + ts + "', 1, 0, 0, 1, 1, 1, 0, NULL, '', 1, " + paren_unit + ", 15, 15, 0),");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        System.out.println(sb.toString());

        // XXX 需要更新zone_id_dessign的id
        id_start++;
        String zone_id_sql = "UPDATE `zone_id_assign` SET `cur_tgid` = " + id_start + " WHERE `id` = 1;";
        System.out.println(zone_id_sql);


    }

}


        