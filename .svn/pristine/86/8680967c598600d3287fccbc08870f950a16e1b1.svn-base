package com.kfwy.park.controller.act.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2019/3/18 15:25
 */
public class ExportTest {


    //测试方法
    public static void main(String args[]) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("partyA","上海环颂");
        map.put("partyB","周黑鸭");
        map.put("throwLeaseDate","2019-02-23");
        map.put("leasedArea","上海汇联路XX园区");
        map.put("throwLeaseArea","111");
        map.put("monthlyRent","33333");
        map.put("throwLeaseRemark","事项说明事项说明事项说明事项说明事项说明事项说明");
        map.put("refundBank","招商银行");
        map.put("refundAccountName","贝克汉姆");
        map.put("refundAccountNumber","1234567890");
        map.put("partyAAddress","上海汇联路88号");
        map.put("partyALegalRepresentative","鲁大师");
        map.put("partyAPhone","13387578894");
        map.put("partyBAddress","上海双浜路222号");
        map.put("partyBLegalRepresentative","猎豹");
        map.put("partyBPhone","15288577584");

        //Configuration  用于读取ftl文件
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");


        try {

            //图片
//            Map<String, Object> picture = new HashMap<String, Object>();
//            picture.put("width",100);
//            picture.put("height", 150);
//            picture.put("type","png");
//            FileInputStream fileInputStream = new FileInputStream("D:/import/园区无忧logo.png");
//            picture.put("content", WorderToNewWordUtils.inputStream2ByteArray(fileInputStream, true));
//
//            map.put("picture","sss");

            /**
             * 指定ftl文件所在目录路径
             */
            configuration.setDirectoryForTemplateLoading(new File("D:/import"));

            //输出文档路径及名称
            File outFile = new File("D:/export/退租协议.doc");

            //以UTF-8的编码读取ftl文件
            Template template = configuration.getTemplate("退.ftl","UTF-8");
//            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"), 10240);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            Writer out = new BufferedWriter(outputStreamWriter,10240);

            template.process(map,out);
            out.flush();
            out.close();
            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
