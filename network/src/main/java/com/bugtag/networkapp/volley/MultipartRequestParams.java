package com.bugtag.networkapp.volley;

import org.apache.http.HttpEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 15-9-16.
 */
public class MultipartRequestParams {
    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, FileWrapper> fileParams;

    public MultipartRequestParams() {
        init();
    }

    public MultipartRequestParams(String key, String value) {
        init();
        put(key, value);
    }

    private void init() {
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }

    /**
     * 添加value到request中
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    /**
     * 添加文件到request中
     *
     * @param key
     * @param file
     */
    public void put(String key, File file) {
        try {
            put(key, new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * 添加stream到request中
     *
     * @param key
     * @param stream
     * @param fileName
     */
    public void put(String key, InputStream stream, String fileName) {
        put(key, stream, fileName, null);
    }

    /**
     * 添加stream到request中
     *
     * @param key
     * @param stream
     * @param fileName
     * @param contentType
     */
    public void put(String key, InputStream stream, String fileName, String contentType) {
        if (key != null && stream != null) {
            fileParams.put(key, new FileWrapper(stream, fileName, contentType));
        }
    }

    public HttpEntity getEntity() {
        HttpEntity entity = null;
        MultipartEntity multipartEntity = new MultipartEntity();

            // Add string params
            int curretItem = 0;
            int lastItem = urlParams.entrySet().size() -1;
            for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
                boolean isLast = curretItem == lastItem;
                if(!fileParams.isEmpty()){
                    isLast = false;
                }
                multipartEntity.addPart(entry.getKey(), entry.getValue(),isLast);
                curretItem ++;
            }

            if(!fileParams.isEmpty()) {
                // Add file params
                int currentIndex = 0;
                int lastIndex = fileParams.entrySet().size() - 1;
                for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
                    FileWrapper file = entry.getValue();
                    if (file.inputStream != null) {
                        boolean isLast = currentIndex == lastIndex;
                        if (file.contentType != null) {
                            multipartEntity.addPart(entry.getKey(), file.getFileName(), file.inputStream, file.contentType, isLast);
                        } else {
                            multipartEntity.addPart(entry.getKey(), file.getFileName(), file.inputStream, isLast);
                        }
                    }
                    currentIndex++;
                }
            }

        entity = multipartEntity;
        return entity;
    }

    private static class FileWrapper {
        public InputStream inputStream;
        public String fileName;
        public String contentType;

        public FileWrapper(InputStream inputStream, String fileName,
                           String contentType) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public String getFileName() {
            if (fileName != null) {
                return fileName;
            } else {
                return "nofilename";
            }
        }
    }

}
