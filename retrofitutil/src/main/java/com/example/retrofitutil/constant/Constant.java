package com.example.retrofitutil.constant;

import com.example.retrofitutil.MacAddressUtils;
import okhttp3.Cookie;
import org.json.JSONArray;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {
    public static String BASE_URL = "";
    public static ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap();
    public static String ADDRESS;
    public static String SERIAL_NUMBER;
}
