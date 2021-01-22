package com.example.retrofitutil;

import okhttp3.Headers;
import okhttp3.RequestBody;

import java.util.ArrayList;

public class RequestEntity {
    private String url;
    private Object tags;
    private RequestBody body;
    private Headers headers;

    public RequestEntity(String url, RequestBody body, Object tags,Headers headers) {
        this.url = url;
        this.body = body;
        this.tags = tags;
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public RequestBody getBody() {
        return body;
    }

    public Headers getHeaders() {
        return headers;
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
        private Headers.Builder headers;

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
            return new RequestEntity(url, body, tags,headers.build());
        }

        public void addHeader(String key, String val) {
            headers.add(key,val);
        }
    }
}
