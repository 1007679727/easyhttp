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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 此函数意义：传入任意对象，将其转换为请求体。
 * 推荐bean对象写url地址，无法揣测哪些参数是需要的，哪些是不需要的。
 * 能提供文件上传下载的解析，支持header、tag的解析。
 * bean中 map 默认为headers；文件统一上传；除tag、url关键字其余均默认为参数，拼接在url后
 */
public class Bean2ParmaUtil {
    private static String TAG = Bean2ParmaUtil.class.getName();

    /**
     * 将请求转换为实体
     * @param t   传入的泛型 任意类型对象
     * @param <T>
     * @return RequestEntity 请求的实体类
     * @throws IllegalAccessException
     */
    public static <T> RequestEntity parseUrl(T t) {
        if (t instanceof File) {
            // 暂时不知如何处理，不明确传入一个文件意图
        } else if (t instanceof Map) {
            Iterator iterator = ((Map) t).keySet().iterator();
            RequestEntity.Builder builder = new RequestEntity.Builder();
            while (iterator.hasNext()) {
                Map.Entry en = (Map.Entry) iterator.next();
                String key = (String) en.getKey();
                if (key.equals("header")) {
                    builder.addHeader(key,en.getValue()+"");
                }else if (key.equals("url")){
                    builder.setUrl(en.getValue()+"");
                }else if (key.equals("tag")){
                    builder.setTags(en.getValue());
                }else if (key.equals("body")){
                    builder.setBody((RequestBody) en.getValue());
                }
            }
            return builder.builder();
        } else if (t instanceof List) {
            // 暂不知如何处理，不明确传入一个list意图
        } else {
            try {
                return dealObject(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将对象类型的参数转换为请求实体
     * 处理逻辑为 文件存放在multipartBody中，默认map存放的是header，
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    private static <T> RequestEntity dealObject(T t) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuilder urlParamBuilder = new StringBuilder();
        String url = null;
        MultipartBody.Builder multipartBody = new MultipartBody.Builder(); // JSON
        multipartBody.setType(MultipartBody.FORM);
        RequestEntity.Builder builder = new RequestEntity.Builder();
        boolean hasFile = false;
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = field.get(t);
            if (obj != null) {
                if (obj instanceof File) {
                    multipartBody.addFormDataPart("file", ((File) obj).getName(), RequestBody.create(MediaType.parse(guessMimeType(((File) obj).getName())), (File) obj));
                    hasFile = true;
                } else if (obj instanceof Map) {
                    Iterator it = ((Map) obj).keySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry en = (Map.Entry) it.next();
                        String key = (String) en.getKey();
                        String val = (String) en.getValue();
                        builder.addHeader(key, val);
                    }
                } else {
                    if ("tags".equals(field.getName())) {
                        builder.setTags(obj);
                        continue;
                    }
                    if ("url".equals(field.getName())) {
                        url = (String) obj;
                        continue;
                    }
                    // 具体参数格式 url拼接方式有待商议
                    // 推荐url直接写死在bean里面
                    urlParamBuilder
                            .append(field.getName())
                            .append("=")
                            .append(obj)
                            .append("&");
                }
            }
        }
        if (url != null) {
            String param = urlParamBuilder.toString();
            if (param.endsWith("&")){
                param = param.substring(0,param.length()-1);
            }
            builder.setUrl(url + param);
        } else {
            new RuntimeException("not found url");
        }
        if (hasFile) {
            builder.setBody(multipartBody.build());
        }

        return builder.builder();
    }

    public static String guessMimeType(String path) {
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
