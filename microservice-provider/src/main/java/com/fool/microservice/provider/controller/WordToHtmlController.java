package com.fool.microservice.provider.controller;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author fool
 * @date 2021/11/16 9:27
 */
@RestController
public class WordToHtmlController {

    @RequestMapping("word-to-html")
    public String wordToHtml(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Document document = new Document();
            document.loadFromStream(inputStream, FileFormat.Docx);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.saveToStream(outputStream, FileFormat.Html);

            return outputStream.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(value = "test.html", method = RequestMethod.GET)
    public void wordToHtml(String filePath, HttpServletResponse response) {
        if (filePath == null || filePath.isEmpty()) {
            filePath = "E:\\documents\\Mysql性能测试.docx";
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("not a file");
        }

        try (InputStream inputStream = new FileInputStream(file)) {
            Document document = new Document();
            document.loadFromStream(inputStream, FileFormat.Docx);
            response.setContentType("text/html");
            document.saveToStream(response.getOutputStream(), FileFormat.Html);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
