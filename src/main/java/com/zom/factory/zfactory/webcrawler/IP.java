package com.zom.factory.zfactory.webcrawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IP {

    public static void main(String[] args) throws Exception {
        interval_xicidaili(20);//西刺代理-国内高匿代理IP
    }

    public static void interval_xicidaili(int minutes) throws Exception {
        while (true) {
            xicidaili();
            Thread.sleep(1000 * 60 * minutes);//休眠minutes分钟:定期刷新IP
        }
    }

    public static void xicidaili() throws Exception {
        System.out.println("开始更新IP：" + (new Date()));
        Document doc = Jsoup.connect("http://www.xicidaili.com/nn").get();
        //https://jsoup.org/cookbook/introduction/parsing-a-document
        /***
         * <table id="ip_list">
         <tr class="odd">
         <td class="country"><img src="http://fs.xicidaili.com/images/flag/cn.png" alt="Cn" /></td>
         <td>60.169.78.218</td>
         <td>808</td>
         <td><a href="/2014-11-03/anhui">安徽芜湖</a></td>
         <td class="country">高匿</td>
         <td>HTTP</td>
         <td class="country">
         <div title="2.68秒" class="bar">
         <div class="bar_inner fast" style="width:73%"></div>
         </div>
         </td>
         <td class="country">
         <div title="0.3秒" class="bar">
         <div class="bar_inner fast" style="width:94%"></div>
         </div>
         </td>
         <td>954天</td>
         <td>17-06-15 09:00</td>
         </tr>
         */
        Element ip_list = doc.getElementById("ip_list");
        Elements tr_list = ip_list.getElementsByTag("tr");
        tr_list.remove(0);//移除标题
        StringBuilder sb = new StringBuilder();
        for (Element tr : tr_list) {
            String ip = tr.child(1).text();
            String port = tr.child(2).text();
            String ipport = ip + ":" + port + "\r\n";
            //System.out.println(ipport);
            sb.append(ipport);
        }
        String ipStr = sb.toString();
        System.out.println(ipStr);
        //File file = new File("D:\\apache-tomcat-8.5.20\\webapps\\ip.txt");
        File file2 = new File("G:\\vm_share\\ip.txt");
		/*if(!file.exists()){
			file.createNewFile();
		}*/
        if (!file2.exists()) {
            file2.createNewFile();
        }
        //ftp://byu2855970001.my3w.com/
        FileInputStream in = new FileInputStream(file2);
        //boolean flag = uploadFile("127.0.0.1", 21, "test", "test", "D:/ftp", "test.txt", in);
        boolean flag = FTPTool.uploadFile("byu2855970001.my3w.com", 21, "byu2855970001", "vfr47ujm", "htdocs/ip", "ip.txt", in);
        System.out.println(flag);

        //StringReader reader = new StringReader(ipStr);
		/*FileWriter writer = new FileWriter(file);
		writer.write(ipStr);
		writer.flush();
		writer.close();*/

        FileWriter writer2 = new FileWriter(file2);
        writer2.write(ipStr);
        writer2.flush();
        writer2.close();
    }

}
