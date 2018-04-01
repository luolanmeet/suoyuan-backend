package com.sy.word.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 资源加载
 * @author cck
 */
@Slf4j
public class ResourceLoader {

    /**
     * 加载资源
     * @param resourcePaths 多个资源路径，用逗号分隔
     * @return 资源内容
     */
    public static List<String> load(String resourcePaths) {
        List<String> result = new ArrayList<>();

        for (String resource : resourcePaths.split("[,，]")) {
            try {
                resource = resource.trim();

                // 类路径资源
                if (resource.startsWith("classpath:")) {
                    resource = resource.replace("classpath:", "");
                        result.addAll(loadClasspathResource(resource));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 加载类路径资源
     * @param resource 资源名称
     * @param resourceLoader 自定义资源加载逻辑
     * @return 资源内容
     * @throws IOException
     */
    private static List<String> loadClasspathResource(String resource)
            throws IOException {

        List<String> result = new ArrayList<>();
        try {
            InputStream in = ResourceLoader.class.getClassLoader().getResourceAsStream(resource);;

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"))) {
                String line;
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if ("".equals(line) || line.startsWith("#")){
                        continue;
                    }
                    result.add(line);
                }
            }

        } catch (Exception e) {
            log.info("加载资源失败：{}", e);
        }

        return result;
    }

    public static void main(String[] args) {
        load("classpath:punctuation");
    }

}
