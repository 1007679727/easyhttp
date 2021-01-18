package com.example.retrofitutil;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Bean2ParmaUtil {
    private static String TAG = Bean2ParmaUtil.class.getName();

    /**
     * 此函数意义：传入任意对象，将其转换为请求体
     * @param t
     * @param <T>
     * @throws IllegalAccessException
     * @return RequestEntity 请求的实体类
     */
    public static <T> RequestEntity postUrl(T t) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuilder urlBuilder = new StringBuilder();
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);
        RequestEntity.Builder builder = new RequestEntity.Builder();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = field.get(t.getClass());
            if (obj != null) {
                if (obj instanceof File) {
                    multipartBody.addFormDataPart("file", ((File) obj).getName(), RequestBody.create(MediaType.parse(guessMimeType(((File) obj).getName())), (File) obj));
                } else {
                    if ("tags".equals(field.getName())){
                        builder.setTags(obj);
                    }
                    urlBuilder.append("&")
                            .append(field.getName())
                            .append("=")
                            .append(obj);
                }
            }
        }
        builder.setUrl(urlBuilder.toString())
                .setBody(multipartBody.build());
        return builder.builder();
    }

    private static String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;

        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }

        return contentTypeFor;
    }
}
