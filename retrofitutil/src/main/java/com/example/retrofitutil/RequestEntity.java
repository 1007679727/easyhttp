package com.example.retrofitutil;

import okhttp3.RequestBody;

public class RequestEntity {
    private String url;
    private Object tags;
    private RequestBody body;
    private boolean changeThread;

    public RequestEntity(String url, RequestBody body, Object tags, boolean changeThread) {
        this.url = url;
        this.body = body;
        this.changeThread = changeThread;
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public RequestBody getBody() {
        return body;
    }

    public boolean isChangeThread() {
        return changeThread;
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
        private boolean changeThread;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setBody(RequestBody body) {
            this.body = body;
            return this;
        }

        public Builder setChangeThread(boolean changeThread) {
            this.changeThread = changeThread;
            return this;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public RequestEntity builder() {
            return new RequestEntity(url, body, tags, changeThread);
        }
    }
}
