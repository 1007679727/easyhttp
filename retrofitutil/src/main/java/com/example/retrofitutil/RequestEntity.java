package com.example.retrofitutil;

import okhttp3.RequestBody;

public class RequestEntity {
    private String url;
    private Object tags;
    private RequestBody body;

    public RequestEntity(String url, RequestBody body, Object tags) {
        this.url = url;
        this.body = body;
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public RequestBody getBody() {
        return body;
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
            return new RequestEntity(url, body, tags);
        }
    }
}
