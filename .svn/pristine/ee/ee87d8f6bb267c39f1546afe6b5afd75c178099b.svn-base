package com.kfwy.park.common.exportword;

import com.kfwy.park.common.utils.ParamUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author gengtao
 * @Date 2019/8/7 9:36
 **/
public class WordGenerator {

    //在本地服务器创建临时文件
    public static  void createDoc(Map<String, Object> dataMap, Class clazz, String backPackagePath, String templateName, String docFilePath, String fileName)throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassicCompatible(true);
        configuration.setClassForTemplateLoading(clazz, backPackagePath);

        String outFile = docFilePath + fileName;
        File f = new File(outFile);
        OutputStream out = new FileOutputStream(f);

        Template t = configuration.getTemplate(templateName,"utf-8");
        WordHtmlGeneratorHelper.handleAllObject(dataMap);

        try {
            Writer w = new OutputStreamWriter(out,Charset.forName("utf-8"));
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    //浏览器下载文件
    public static void downLoad(HttpServletResponse response, String docFilePath, String fileName){
        FileInputStream inputStream = null;
        OutputStream outputStream = null;
        try {
//            fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
            String downLoadPath = docFilePath + fileName;
            fileName = new String(fileName.getBytes("UTF-8"));

            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            //读取要下载的文件，保存到文件输入流
            inputStream = new FileInputStream(downLoadPath);

            //创建输出流
            outputStream = response.getOutputStream();

            //缓存区
            byte buffer[] = new byte[5*1024];
            int len = 0;
            //循环将输入流的内容读取到缓存区中
            while((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            inputStream.close();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //删除文件
    public static void deleteFile(String outPath){
        //删除下载到服务器的临时文件
        File delFile = new File(outPath);
        delFile.delete();
    }


    //判断是否是windows系统
    public static  boolean ifWindows(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return true;
        }else {
            return false;
        }
    }

    //获取fileCode
    public static List<String> getFileCode(String html){
        List<String> list = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements eles = doc.select("img");
        for ( Element e : eles) {
            String src = e.attr("src");
            String[] path = src.split("/");
            String fileCode = path[path.length - 1];
            list.add(fileCode);
        }
        return list;
    }

    /**
     *@author gtaotao
     *@Date 2019-08-07 09:55:06
     *@Description 导出文件
     *@param data 实体数据
     *@Param imgPathMap 图片的物理地址 key:fileCode, value:filePath
     *@param richTextFieldName 实体类中带有富文本的字段名
     *@param templateName ftl模板名
     *@param fileName 生成的文件名
     *@return
     **/
    public static void export(HttpServletResponse response, Map<String, Object> data,Class clazz,String backPackagePath, Map<String,Object> imgPathMap, String richTextFieldName, String templateName, String fileName, String docSrcLocationPrex, String docSrcParent, String nextPartId) throws Exception {
        //获取富文本
        String html = (String) data.get(richTextFieldName);
        Document doc = Jsoup.parse(html);
        Elements eles = doc.select("img");
        String docFilePath = null;
        if (ifWindows()){
            docFilePath = "D:\\park/file/";
        }else{
            docFilePath = "/srv/file/park/";
        }

        for ( Element e : eles){
            String src = e.attr("src");
            String[] path = src.split("/");
            String fileCode = path[path.length -1];
            //根据fileCode获取文件地址
            String imgPath = (String) imgPathMap.get(fileCode);

            //分别对windows和linux环境进行src拼接
            String newFilePath =docFilePath + imgPath;
            e.attr("style", "height:200px;width:250px");
            //生成新的src地址
            e.attr("src", newFilePath);
        }
        //生成新的html
        String newHtml = doc.toString();
        RichHtmlHandler handler = new RichHtmlHandler(newHtml);

        //配置template
        handler.setDocSrcLocationPrex(docSrcLocationPrex);
        handler.setDocSrcParent(docSrcParent);
        handler.setNextPartId(nextPartId);
        handler.setShapeidPrex("_x56fe__x7247__x0020");
        handler.setSpidPrex("_x0000_i");
        handler.setTypeid("#_x0000_t75");
        handler.handledHtml(false);

        String bodyBlock = handler.getHandledDocBodyBlock();

        String handledBase64Block = "";
        if (handler.getDocBase64BlockResults() != null
                && handler.getDocBase64BlockResults().size() > 0) {
            for (String item : handler.getDocBase64BlockResults()) {
                handledBase64Block += item + "\n";
            }
        }
        data.put("imagesBase64String", handledBase64Block);

        String xmlimaHref = "";
        if (handler.getXmlImgRefs() != null
                && handler.getXmlImgRefs().size() > 0) {
            for (String item : handler.getXmlImgRefs()) {
                xmlimaHref += item + "\n";
            }
        }
        data.put("imagesXmlHrefString", xmlimaHref);
        //改写富文本数据
        data.put(richTextFieldName,bodyBlock);

        try {
            //生成word文件
            createDoc(data,clazz, backPackagePath, templateName, docFilePath, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //下载
        downLoad(response, docFilePath, fileName);

        //删除服务器临时文件
        String outFile = docFilePath + fileName;
        deleteFile(outFile);

    }
}