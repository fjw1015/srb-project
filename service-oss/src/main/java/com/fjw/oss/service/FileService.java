package com.fjw.oss.service;

import java.io.InputStream;

/**
 * @author fjw
 * @date 2021-07-17 17:46
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);

    void removeFile(String url);
}
