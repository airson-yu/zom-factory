/*
package com.zom.domain.tools.zlab;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.gjt.jclasslib.io.ClassFileWriter;
import org.gjt.jclasslib.structures.CPInfo;
import org.gjt.jclasslib.structures.ClassFile;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;

public class Byteman {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {

        String filePath = "D:\\apache-tomcat-7.0.59\\webapps\\CCDS\\WEB-INF\\classes\\com\\frsoft\\util\\JdbcCon.class";
        FileInputStream fis = new FileInputStream(filePath);

        DataInput di = new DataInputStream(fis);
        ClassFile cf = new ClassFile();
        cf.read(di);
        CPInfo[] infos = cf.getConstantPool();

        int count = infos.length;
        for (int i = 0; i < count; i++) {
            if (infos[i] != null) {
                System.out.print(i);
                System.out.print(" = ");
                System.out.print(infos[i].getVerbose());
                System.out.print(" = ");
                System.out.println(infos[i].getTagVerbose());
                if (i == 17) {
                    ConstantUtf8Info uInfo = (ConstantUtf8Info) infos[i];
                    uInfo.setBytes("jdbc:mysql://192.168.2.222:3306/oms".getBytes());
                    infos[i] = uInfo;
                }
                if (i == 20) {
                    ConstantUtf8Info uInfo = (ConstantUtf8Info) infos[i];
                    uInfo.setBytes("root".getBytes());
                    infos[i] = uInfo;
                }
                if (i == 23) {
                    ConstantUtf8Info uInfo = (ConstantUtf8Info) infos[i];
                    uInfo.setBytes("root".getBytes());
                    infos[i] = uInfo;
                }
            }
        }
        cf.setConstantPool(infos);
        fis.close();
        File f = new File(filePath);
        ClassFileWriter.writeToFile(f, cf);
    }


}
*/
