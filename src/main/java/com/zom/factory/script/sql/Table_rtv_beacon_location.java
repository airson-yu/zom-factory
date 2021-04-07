package com.zom.factory.script.sql;

public class Table_rtv_beacon_location {

    public static void main(String[] args) {
        generateInsertSQL();
    }

    public static void generateInsertSQL() {
        StringBuilder sb = new StringBuilder(1000);
        String sql_base = "INSERT INTO `rtv_beacon_location`(`loc_name`, `tg_id`, `loc_mark`, `status`, `owner_id`, `role_id`, `corp_id`, `updirection`, `downdirection`, `updateTime`, `metroline`, `order`, `message`) " +
                "VALUES ";
        sb.append(sql_base);

        // (28, '西直门', 00000074053, '{\"loc_lat\":39.940474,\"loc_lon\":116.355426}', 01, 0, 44, 2, 0, 180, '2019-01-11 10:28:00', '2')";

        String[] data = {
                //`loc_name`, `tg_id`, `loc_mark`, `status`, `owner_id`, `role_id`, `corp_id`, `updirection`, `downdirection`, `updateTime`, `metroline`, `order`, `message`
                //"位置名称,群组ID(主键ID),经度,纬度,部门ID,组织ID,上行,下行,线路,序号(非必填，默认为ID*100，无值就填NULL),语音(非必填，默认为空，无值就填NULL)",
                "金融城东,164059,104.084367,30.579117,476,1,270,90,9,NULL,NULL",
                "心岛,164058,104.073423,30.575718,476,1,270,90,9,NULL,NULL",
                "孵化园,164057,104.064325,30.575579,476,1,270,90,9,NULL,NULL",
                "锦城大道,164056,104.05041,30.574101,476,1,270,90,9,NULL,NULL",
                "三元,164055,104.025943,30.580652,476,1,270,90,9,NULL,NULL",
                "太平寺,164054,104.012446,30.591027,476,1,270,90,9,NULL,NULL",
                "华兴,164053,104.00136,30.598597,476,1,0,90,9,NULL,NULL",
                "簇桥,164052,103.989923,30.60767,476,1,0,180,9,NULL,NULL",
                "武青南路,164051,103.985095,30.623136,476,1,0,180,9,NULL,NULL",
                "机投桥,164050,103.978238,30.644826,476,1,0,180,9,NULL,NULL",
                "培风,164049,103.977194,30.660111,476,1,0,180,9,NULL,NULL",
                "成都西,164048,103.975325,30.684809,476,1,0,180,9,NULL,NULL",
                "黄田坝,164047,103.972063,30.697523,476,1,0,180,9,NULL,NULL",
                "金星,164038,103.794066,30.756182,478,1,180,0,17,NULL,NULL",
                "黄石,164039,103.813977,30.721646,478,1,180,0,17,NULL,NULL",
                "市五医院,164040,103.829238,30.699041,478,1,180,0,17,NULL,NULL",
                "凤溪河,164041,103.842966,30.687707,478,1,180,0,17,NULL,NULL",
                "温泉大道,164042,103.855175,30.672593,478,1,180,0,17,NULL,NULL",
                "明光,164043,103.869145,30.65771,478,1,180,0,17,NULL,NULL",
                "九江北,164044,103.921297,30.634538,478,1,90,0,17,NULL,NULL",
                "白佛桥,164045,103.963309,30.640993,478,1,90,270,17,NULL,NULL",
                "机投桥,164046,103.978367,30.644789,478,1,90,270,17,NULL,NULL",
                "火车南站,164025,104.0677,30.603791,480,1,180,0,18,NULL,NULL",
                "孵化园,164026,104.068218,30.57554,480,1,180,0,18,NULL,NULL",
                "锦城广场东,164027,104.069592,30.568224,480,1,180,0,18,NULL,NULL",
                "世纪城,164028,104.068948,30.554884,480,1,180,0,18,NULL,NULL",
                "海昌路,164029,104.071496,30.49251,480,1,180,0,18,NULL,NULL",
                "西博城,164030,104.0748,30.423864,480,1,180,0,18,NULL,NULL",
                "兴隆,164031,104.102953,30.408913,480,1,180,0,18,NULL,NULL",
                "天府新站,164032,104.151276,30.389072,480,1,180,0,18,NULL,NULL",
                "三岔,164033,104.318045,30.310412,480,1,180,0,18,NULL,NULL",
                "福田,164034,104.387997,30.277507,480,1,180,0,18,NULL,NULL",
                "天府机场3号4号航站楼,164035,104.423531,30.2675,480,1,180,0,18,NULL,NULL",
                "天府机场1号2号航站楼,164036,104.4328,30.286846,480,1,180,0,18,NULL,NULL",
                "天府机场北站,164037,104.457176,30.337898,480,1,180,0,18,NULL,NULL",
        };

        //int roleid = 16;

        /**
         *  XXX XXX status默认为0，即位置未绑定信标，在导入信标的时候再手动更新位置的status为1
         */

        String ts = "2021-04-01 14:49:00";

        for (String i : data) {
            String[] item = i.split(",");
            //int size = item.length;
            //"位置名称,群组ID,经度,纬度,部门ID,组织ID,上行,下行,线路,序号(非必填，默认为ID*100),语音(非必填，默认为空)",
            String name = item[0];
            String tgid = item[1];
            String lat = item[2];
            String lon = item[3];
            String roleId = item[4];
            String corpId = item[5];
            String up = item[6];
            String down = item[7];
            String line = item[8];
            String order = item[9];
            String message = item[10];
            //order = order.equalsIgnoreCase("NULL") ? "" : order;
            message = message.equalsIgnoreCase("NULL") ? "NULL" : ("'" + message + "'");

            String loc_json = "{\"loc_lat\":" + lat + ",\"loc_lon\":" + lon + "}";

            sb.append("\n('" + name + "', " + tgid + ", '" + loc_json + "', 0, 0, " + roleId + ", " + corpId + ", " + up + ", " + down + ", '" + ts + "', '" + line + "', " + order + ", " + message + "),");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        //更新 ORDER
        sb.append("\nUPDATE rtv_beacon_location SET `order` = id * 100 WHERE `order` IS NULL;");

        System.out.println(sb.toString());

    }

}


