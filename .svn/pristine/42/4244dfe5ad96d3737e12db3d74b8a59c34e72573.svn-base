package com.kfwy.park.common.utils;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description 导出文档公用方法
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class ExportFileUtil<T> {

    public static void exportThrowLeaseWord(HttpServletResponse response, Map<String, Object> map,
                                            Class clazz, String fileName, String fileTemplate, String filePathHeadStr) {

        //拼上文件的全路径
        String outPath = "";

        try {
            //Configuration  用于读取ftl文件
            Configuration configuration = new Configuration(new Version("2.3.28"));
            configuration.setDefaultEncoding("UTF-8");

            /**
             * 指定ftl文件所在目录路径
             */
            configuration.setClassForTemplateLoading(clazz, "/throwLeaseFile");

            /**
             * 1、创建检查文件路径
             */
            Date dt = new Date();
            String year = DateUtils.formatDate(dt, "yyyy");
            String month = DateUtils.formatDate(dt, "MM");
            String day = DateUtils.formatDate(dt, "dd");

            String cpyCode = "default_company";

            if (CurrentContext.getUserInfo() != null && StringUtils.isNotEmpty(CurrentContext.getUserInfo().getCpyCode())) {
                cpyCode = CurrentContext.getUserInfo().getCpyCode();
            }

            String userCode = StringUtils.isEmpty(CurrentContext.getUserCode()) ? "not_login" : CurrentContext.getUserCode();

            String fileMidPath =
                    FilenameUtils.concat(
                            FilenameUtils.concat(
                                    FilenameUtils.concat(
                                            FilenameUtils.concat(cpyCode, year), month), day), userCode);

            //输出文档路径及名称
            String filePath = FilenameUtils.concat(filePathHeadStr, fileMidPath);
            File file = new File(filePath);
            //文件不存在即创建
            if(!file.exists()) {
                file.mkdirs();
            }

            //拼上文件的全路径
            outPath = FilenameUtils.concat(filePath, fileName);

            File outFile = new File(outPath);

            if(!outFile.exists()) {
                //以UTF-8的编码读取ftl文件
                Template template = configuration.getTemplate(fileTemplate,"UTF-8");
//            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"), 10240);
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                Writer out = new BufferedWriter(outputStreamWriter,10240);

                template.process(map,out);
                out.flush();
                out.close();
                outputStreamWriter.close();
                fileOutputStream.close();

                //设置为只读
                outFile.setWritable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileInputStream inputStream = null;
        OutputStream outputStream = null;

        try {
/********************文件下载******************************************************/
//            fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
            fileName = new String(fileName.getBytes("UTF-8"));

            String downLoadPath = outPath;

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

        //删除下载到服务器的临时文件
        File delFile = new File(outPath);
        delFile.delete();

    }

    public void exportExcel(HttpServletResponse httpServletResponse, Class clazz, String path, List<T> data,String excelName) throws IOException {
        PrintWriter printWriter = null;
        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        try {

            ExportConfig exportConfig = ExportConfigFactory.getExportConfig(clazz.getResourceAsStream(path));
            workbook =  FileExportor.getExportWorkbook(exportConfig,data);
            String filename = new String(excelName.getBytes("UTF-8"), "ISO8859-1");

            httpServletResponse.setHeader("Content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + filename);
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("UTF-8");
            out = httpServletResponse.getOutputStream();
            if (workbook != null) {
                workbook.write(out);
            } else {
                workbook.write(out);
            }
            out.flush();
        }catch (Exception e){

        }finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    printWriter = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    out = null;
                }
            }

            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    workbook = null;
                }
            }
        }
    }
}
