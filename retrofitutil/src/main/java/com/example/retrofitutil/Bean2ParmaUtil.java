package com.example.retrofitutil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class Bean2ParmaUtil {
    private static String TAG = Bean2ParmaUtil.class.getName();

    /**
     * 此函数意义：传入任意对象，将其转换为请求体
     * @param t
     * @param <T>
     * @throws IllegalAccessException
     */
    public static <T> void getUrl(T t) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = field.get(t.getClass());
            if (obj != null) {
                if (obj instanceof File){

                }else {
                    stringBuilder.append("&")
                            .append(field.getName())
                            .append(obj);
                }
            }
        }
    }

}
