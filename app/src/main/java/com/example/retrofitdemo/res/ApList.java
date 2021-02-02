package com.example.retrofitdemo.res;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApList{
    @SerializedName(value = "apMxList", alternate = {"yjqwMxApMxCommandVOList"})
    private List<ApMxList> apMxList;
    private String gxsj;
    private Object hhid;
    private String hhmc;
    private int id;
    private Object idArr;
    private int mxid;
    private int qwdMxId;
    private int qwid;
    private String xxrksj;
    private String yq;
    private String url;
    private String djrname;
    private int djrid;

    public List<ApMxList> getApMxList() {
        return apMxList;
    }

    public void setApMxList(List<ApMxList> apMxList) {
        this.apMxList = apMxList;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public Object getHhid() {
        return hhid;
    }

    public void setHhid(Object hhid) {
        this.hhid = hhid;
    }

    public String getHhmc() {
        return hhmc;
    }

    public void setHhmc(String hhmc) {
        this.hhmc = hhmc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getIdArr() {
        return idArr;
    }

    public void setIdArr(Object idArr) {
        this.idArr = idArr;
    }

    public int getMxid() {
        return mxid;
    }

    public void setMxid(int mxid) {
        this.mxid = mxid;
    }

    public int getQwdMxId() {
        return qwdMxId;
    }

    public void setQwdMxId(int qwdMxId) {
        this.qwdMxId = qwdMxId;
    }

    public int getQwid() {
        return qwid;
    }

    public void setQwid(int qwid) {
        this.qwid = qwid;
    }

    public String getXxrksj() {
        return xxrksj;
    }

    public void setXxrksj(String xxrksj) {
        this.xxrksj = xxrksj;
    }

    public String getYq() {
        return yq;
    }

    public void setYq(String yq) {
        this.yq = yq;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDjrname() {
        return djrname;
    }

    public void setDjrname(String djrname) {
        this.djrname = djrname;
    }

    public int getDjrid() {
        return djrid;
    }

    public void setDjrid(int djrid) {
        this.djrid = djrid;
    }
}