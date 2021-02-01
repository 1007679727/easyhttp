package com.example.retrofitutil;

import okhttp3.Headers;
import okhttp3.RequestBody;

import java.util.Map;

public class RequestEntity {
    private String url;
    private Object tags;
    private RequestBody body;
    private Map<String, String> headersMap;

    public RequestEntity(String url, RequestBody body, Object tags,Map<String, String> headersMap) {
        this.url = url;
        this.body = body;
        this.tags = tags;
        this.headersMap = headersMap;
    }

    public String getUrl() {
        return url;
    }

    public RequestBody getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headersMap;
    }

    public Object getTags() {
        if (tags == null) {
            return "";
        }
        return tags;
    }

    static class Builder {
        private String url;
        private Object tags;
        private RequestBody body;
        private Map<String, String> headers;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setBody(RequestBody body) {
            this.body = body;
            return this;
        }

        public Builder setTags(Object tags) {
            this.tags = tags;
            return this;
        }

        public RequestEntity builder() {
            if (headers == null){
                return new RequestEntity(url, body, tags,null);
            }
            return new RequestEntity(url, body, tags,headers);
        }

        public void addHeader(String key, String val) {
            headers.put(key,val);
        }
    }
}
