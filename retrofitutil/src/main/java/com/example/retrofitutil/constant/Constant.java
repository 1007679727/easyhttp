package com.example.retrofitutil.constant;

import com.example.retrofitutil.MacAddressUtils;
import okhttp3.Cookie;
import org.json.JSONArray;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {
    public static String BASE_URL = "http://open.iciba.com/";
    public static ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap();
    public static String ADDRESS = "127.0.0.1";
    public static String SERIAL_NUMBER  = "1111";
}
