package com.zom.factory.script.yun3;

public class BEACON_LOC_INSERT {

    public static void main(String[] args) {
        generateInsertSQL();
    }

    public static void generateInsertSQL() {
        StringBuilder sb = new StringBuilder(1000);
        String sql_base = "INSERT INTO `rtv_beacon_location`(`loc_name`, `tg_id`, `loc_mark`, `status`, `owner_id`, `role_id`, `corp_id`, `updirection`, `downdirection`, `updateTime`, `metroline`, `message`) " +
                "VALUES ";
        sb.append(sql_base);

        // (28, '西直门', 00000074053, '{\"loc_lat\":39.940474,\"loc_lon\":116.355426}', 01, 0, 44, 2, 0, 180, '2019-01-11 10:28:00', '2')";

        String[] data = {
                //"巴沟,39.974408,116.293362,270,90,74169,10",
                //"位置名称,经度,纬度,上行,下行,语音,群组,线路",
                "阜成门-复兴门-1,1946.61,928.46,0,180,阜成门-复兴门-1,0,2",
                "阜成门-复兴门-2,1946.61,907.14,0,180,阜成门-复兴门-2,0,2",
                "和平门-前门,2150.26,1001.52,90,270,和平门-前门,0,2",
                "西直门-车公庄,1946.6,809.7,0,180,西直门-车公庄,0,2",
                "建国门-朝阳门-1,2338.3,901.2,180,0,建国门-朝阳门-1,0,2",
                "建国门-朝阳门-2,2338.3,925.5,180,0,建国门-朝阳门-2,0,2",
                "东四十条-东直门,2338.3,812.7,180,0,东四十条-东直门,0,2",
                "雍和宫-安定门,2237.7,748.9,270,90,雍和宫-安定门,0,2",
                "鼓楼大街-积水潭-1,2048.4,748.9,270,90,鼓楼大街-积水潭-1,0,2",
                "鼓楼大街-积水潭-2,2092.9,748.9,270,90,鼓楼大街-积水潭-2,0,2",
                "长椿街-宣武门,1985,1002,90,270,长椿街-宣武门,0,2",
                "崇文门-北京站,2291,1002,90,270,崇文门-北京站,0,2",
                "朱辛庄-育知路-1,1998.436667,319.67,270,90,朱辛庄-育知路-1,0,8",
                "朱辛庄-育知路-2,1963.813333,319.67,270,90,朱辛庄-育知路-2,0,8",
                "育知路-平西府,2066.5675,319.67,270,90,育知路-平西府,0,8",
                "平西府-回龙观东大街-1,2124.905,319.67,270,90,平西府-回龙观东大街-1,0,8",
                "平西府-回龙观东大街-2,2137.32,319.67,0,180,平西府-回龙观东大街-2,0,8",
                "回龙观东大街-霍营,2137.32,372.515,0,180,回龙观东大街-霍营,0,8",
                "霍营-育新,2137.32,412.235,0,180,霍营-育新,0,8",
                "育新-西小口,2137.32,439.795,0,180,育新-西小口,0,8",
                "西小口-永泰庄,2137.32,467.225,0,180,西小口-永泰庄,0,8",
                "永泰庄-林萃桥-1,2137,498,0,180,永泰庄-林萃桥-1,0,8",
                "永泰庄-林萃桥-2,2137,492,0,180,永泰庄-林萃桥-2,0,8",
                "林萃桥-森林公园南门-1,2137,525,0,180,林萃桥-森林公园南门-1,0,8",
                "林萃桥-森林公园南门-2,2137,520,0,180,林萃桥-森林公园南门-2,0,8",
                "森林公园南门-奥林匹克公园,2137.32,549.25,0,180,森林公园南门-奥林匹克公园,0,8",
                "奥林匹克公园-奥体中心,2137.32,576.81,0,180,奥林匹克公园-奥体中心,0,8",
                "奥体中心-北土城,2137.3,605.02,0,180,奥体中心-北土城,0,8",
                "北土城-安华桥,2137.3,651.3,0,180,北土城-安华桥,0,8",
                "安华桥-安德里北街,2137.3,699.4,0,180,安华桥-安德里北街,0,8",
                "安德里北街-鼓楼大街,2137.32,731.965,0,180,安德里北街-鼓楼大街,0,8",
                "鼓楼大街-什刹海,2137.32,774.215,0,180,鼓楼大街-什刹海,0,8",
                "什刹海-南锣鼓巷,2137.32,817.254,0,180,什刹海-南锣鼓巷,0,8",
                "南锣鼓巷-中国美术馆,2159.485,862.485,0,180,南锣鼓巷-中国美术馆,0,8",
                "珠市口-天桥,2159.485,862.485,0,180,珠市口-天桥,0,8",
                "天桥-永定门外,2155,1107.665,0,180,天桥-永定门外,0,8",
                "永定门外-木樨园,2155,1065.09,0,180,永定门外-木樨园,0,8",
                "木樨园-海户屯,2155,1107.665,0,180,木樨园-海户屯,0,8",
                "海户屯-大红门,2155,1143.415,0,180,海户屯-大红门,0,8",
                "大红门-大红门南,2155,1171.69,0,180,大红门-大红门南,0,8",
                "大红门南-和义-1,2155,1198.08,0,180,大红门南-和义-1,0,8",
                "大红门南-和义-2,2155,1237.08,0,180,大红门南-和义-2,0,8",
                "和义-东高地,2155,1296.71,0,180,和义-东高地,0,8",
                "东高地-火箭万源,2155,1279.37,0,180,东高地-火箭万源,0,8",
                "火箭万源-五福堂,2155,1346.54,0,180,火箭万源-五福堂,0,8",
                "五福堂-德茂-1,2155,1411.54,0,180,五福堂-德茂-1,0,8",
                "五福堂-德茂-2,2170.5,1498.54,0,180,五福堂-德茂-2,0,8",
                "德茂-瀛海,2283,1550,0,180,德茂-瀛海,0,8",
                "首经贸-丰台,1778.065,1212.12,90,270,首经贸-丰台,0,10",
                "丰台-泥洼,1704,1197,180,0,丰台-泥洼,0,10",
                "泥洼-西局,1692.2,1136.2,180,0,泥洼-西局,0,10",
                "西局-六里桥,1692.2,1087.45,180,0,西局-六里桥,0,10",
                "六里桥-莲花桥-1,1692.2,1040,180,0,六里桥-莲花桥-1,0,10",
                "六里桥-莲花桥-2,1692.2,26667,180,0,六里桥-莲花桥-2,0,10",
                "莲花桥-公主坟,1692.2,978.185,180,0,莲花桥-公主坟,0,10",
                "公主坟-西钓鱼台-1,1656.146667,911.3866667,180,0,公主坟-西钓鱼台-1,0,10",
                "公主坟-西钓鱼台-2,1674.173333,930.5833333,180,0,公主坟-西钓鱼台-2,0,10",
                "西钓鱼台-慈寿寺,1611.08,863.59,180,0,西钓鱼台-慈寿寺,0,10",
                "慈寿寺-车道沟,1584.04,807.69,180,0,慈寿寺-车道沟,0,10",
                "车道沟-长春桥,1584.04,752.05,180,0,车道沟-长春桥,0,10",
                "长春桥-火器营,1584.04,96.605,180,0,长春桥-火器营,0,10",
                "火器营-巴沟,1597,633,180,0,火器营-巴沟,0,10",
                "巴沟-苏州街,1661.1,618.8,270,90,巴沟-苏州街,0,10",
                "苏州街-海淀黄庄,1720.5,618.8,270,90,苏州街-海淀黄庄,0,10",
                "海淀黄庄-知春里,1810.8,618.8,270,90,海淀黄庄-知春里,0,10",
                "知春里-知春路,1899.2,618.8,270,90,知春里-知春路,0,10",
                "知春路-西土城,1950.6,618.8,270,90,知春路-西土城,0,10",
                "西土城-牡丹园,1998.3,618.8,270,90,西土城-牡丹园,0,10",
                "牡丹园-建德门,2053.1,618.8,270,90,牡丹园-建德门,0,10",
                "建德门-北土城,2109.5,618.8,270,90,建德门-北土城,0,10",
                "北土城-安贞门,2175.6,618.8,270,90,北土城-安贞门,0,10",
                "安贞门-惠新西街南口,2237.7,618.8,270,90,安贞门-惠新西街南口,0,10",
                "惠新西街南口-芍药居,2313.7,618.8,270,90,惠新西街南口-芍药居,0,10",
                "芍药居-太阳宫,2403.1,618.8,270,90,芍药居-太阳宫,0,10",
                "太阳宫-三元桥,2460,642,0,180,太阳宫-三元桥,0,10",
                "三元桥-亮马桥,2460.6,699.4,0,180,三元桥-亮马桥,0,10",
                "亮马桥-农业展览馆,2460.63,745.81,0,180,亮马桥-农业展览馆,0,10",
                "农业展览馆-团结湖,2460.63,790.53,0,180,农业展览馆-团结湖,0,10",
                "团结湖-呼家楼,2460.63,44.935,0,180,团结湖-呼家楼,0,10",
                "呼家楼-金台夕照,2460.63,895.96,0,180,呼家楼-金台夕照,0,10",
                "金台夕照-国贸,2460.63,932.36,0,180,金台夕照-国贸,0,10",
                "国贸-双井,2460.63,995.735,0,180,国贸-双井,0,10",
                "双井-劲松,2460.63,1060.54,0,180,双井-劲松,0,10",
                "劲松-潘家园,2460.63,1095.64,0,180,劲松-潘家园,0,10",
                "潘家园-十里河,2460.63,1133.34,0,180,潘家园-十里河,0,10",
                "十里河-分钟寺,2438,1193,90,270,十里河-分钟寺,0,10",
                "分钟寺-成寿寺,2367.55,1212.12,90,270,分钟寺-成寿寺,0,10",
                "成寿寺-宋家庄,2299.95,1212.12,90,270,成寿寺-宋家庄,0,10",
                "宋家庄-石榴庄,2232.805,1212.12,90,270,宋家庄-石榴庄,0,10",
                "石榴庄-大红门,2179.505,1212.12,90,270,石榴庄-大红门,0,10",
                "大红门-角门东,2121.915,1212.12,90,270,大红门-角门东,0,10",
                "角门东-角门西,2059.97,1212.12,90,270,角门东-角门西,0,10",
                "角门西-草桥,1995.62,1212.12,90,270,角门西-草桥,0,10",
                "草桥-纪家庙,1924.64,1212.12,90,270,草桥-纪家庙,0,10",
                "纪家庙-首经贸,1853.14,1212.12,90,270,纪家庙-首经贸,0,10",
                "西直门-大钟寺-1,1928,735,180,0,西直门-大钟寺-1,0,13",
                "西直门-大钟寺-2,1930,760,180,0,西直门-大钟寺-2,0,13",
                "大钟寺-知春路,1929.19,664.56,180,0,大钟寺-知春路,0,13",
                "知春路-五道口,1929.19,593.91,180,0,知春路-五道口,0,13",
                "五道口-上地-1,1929.19,529.92,180,0,五道口-上地-1,0,13",
                "五道口-上地-2,1929.19,549.47,180,0,五道口-上地-2,0,13",
                "上地-清河,1929.19,491.99,180,0,上地-清河,0,13",
                "清河-西二旗,1929.19,455.20,180,0,清河-西二旗,0,13",
                "西二旗-龙泽-1,1951,401,90,270,西二旗-龙泽-1,0,13",
                "西二旗-龙泽-2,1935,409,90,270,西二旗-龙泽-2,0,13",
                "龙泽-回龙观,2006.67,398.32,90,270,龙泽-回龙观,0,13",
                "回龙观-霍营,2086.82,398.32,90,270,回龙观-霍营,0,13",
                "霍营-立水桥-1,2220.17,398.32,90,270,霍营-立水桥-1,0,13",
                "霍营-立水桥-2,2178.75,398.32,90,270,霍营-立水桥-2,0,13",
                "立水桥-北苑,2350,406,90,270,立水桥-北苑,0,13",
                "北苑-望京西-1,2365.86,515.61,0,180,北苑-望京西-1,0,13",
                "北苑-望京西-2,2365.86,502.02,0,180,北苑-望京西-2,0,13",
                "望京西-芍药居,2365.86,577.01,0,180,望京西-芍药居,0,13",
                "芍药居-光熙门,2365.86,651.95,0,180,芍药居-光熙门,0,13",
                "光熙门-柳芳,2365.86,710.32,0,180,光熙门-柳芳,0,13",
                "柳芳-东直门,2365,766,0,180,柳芳-东直门,0,13"
                
        };

        int roleid = 16;

        String ts = "2020-11-17 15:12:00";

        for (String i : data) {
            String[] item = i.split(",");
            //int size = item.length;
            String name = item[0];
            String lat = item[1];
            String lon = item[2];
            String up = item[3];
            String down = item[4];
            String message = item[5];
            String tgid = item[6];
            String line = item[7];

            String loc_json = "{\"loc_lat\":" + lat + ",\"loc_lon\":" + lon + "}";

            sb.append("\n('" + name + "', " + tgid + ", '" + loc_json + "', 1, 0, " + roleid + ", 1, " + up + ", " + down + ", '" + ts + "', '" + line + "', '" + message + "'),");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");

        System.out.println(sb.toString());

    }

}


