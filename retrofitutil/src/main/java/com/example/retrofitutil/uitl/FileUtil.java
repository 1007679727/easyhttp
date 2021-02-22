package com.example.retrofitutil.uitl;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

    public static MultipartBody.Part getPart(String fileName,File file) {
        RequestBody requestBody = getRequestBody(file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(fileName, file.getName(), requestBody);

        return part;
    }

    public static RequestBody getRequestBody(File file){
        MediaType mediaType = MediaType.parse("application/octet-stream");//设置类型，类型为八位字节流
        RequestBody requestBody = RequestBody.create(mediaType, file);//把文件与类型放入请求体

        return requestBody;
    }

    /**
     * 上传多个文件
     * @param files
     * @return
     */
    public static Map<String,RequestBody> filesToMultipartBodyParts(List<File> files) {
        Map<String,RequestBody> map = new HashMap<>();
        for (File file : files) {
            RequestBody requestBody = getRequestBody(file);
            map.put(file.getName(),requestBody);
        }
        return map;
    }
}
