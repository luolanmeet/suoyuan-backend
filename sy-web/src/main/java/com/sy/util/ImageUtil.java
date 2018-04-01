package com.sy.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ImageUtil {

    @Autowired
    private NameFactory nameFactory;

    final static String PATH = "/pic";

    final static String HOST = "http://localhost:8080";

    /** 保存图片 */
    public String save(MultipartFile file) {

    	if (file == null) {
    		return "";
    	}

        return HOST + save(file, PATH);
    }

    private String save(MultipartFile file, String catalog) {

        String fileName = getFileName(file);
        try {
            file.transferTo(getFile(fileName, catalog));
            log.info("{} save success", fileName);
        } catch (IOException e) {
            log.error("save file error msg: {}", e);
        }

        return  catalog + "/" + fileName;
    }

    /** 删除图片 */
    public void delete(String path) {
        File file = new File(getBasePath() + path);
        Assert.isTrue(file.exists(),  file.getPath() + "不存在");
        Assert.isTrue(file.delete(), path + "删除失败");
        log.info("删除 {} 成功", path);
    }

    /** 校验并生成保存的文件名*/
    private String getFileName(MultipartFile file) {
        Assert.notNull(file, "file is null");

        String fileName = file.getOriginalFilename();
        log.info("file name {}", fileName);

        int indexOf = fileName.lastIndexOf(".");
        String fileType = indexOf != -1
                ? fileName.substring(indexOf + 1, fileName.length())
                        : null;
        log.info("file type {}", fileType);

        return nameFactory.getName() + "." + fileType;
    }

    private File getFile(String fileName, String path) {
        return new File(getFilePath(fileName, path));
    }

    /** 保存或删除的文件所在路径*/
    private String getFilePath(String fileName, String catalog) {
        StringBuffer savePath = new StringBuffer();
        savePath.append(getBasePath())
                .append("\\")
                .append(catalog)
                .append("\\")
                .append(fileName);
        log.info("file path : {}", savePath.toString());

        return savePath.toString();
    }

    /** 基本的文件路径 */
    private String getBasePath() {
        String property = System.getProperty("user.dir");
        return property.substring(0, property.lastIndexOf("\\") + 1)
                + "\\sy-web\\src\\main\\resources\\static\\";
    }

}
