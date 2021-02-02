package com.example.retrofitdemo.res;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LogisticsQwdMxVOListBean {
    private String abbreviation;
    private int djrid;
    private String djrname;
    private int dutyStationTypeIconId;
    private String dutyStationTypeIconName;
    private String dutyStationTypeIconUrl;
    private int dutyStationTypeId;
    private int id;
    private Double lan;
    private Double lon;
    private int mjsl;
    private String organizationCode;
    private String organizationName;
    private Object pid;
    private int qwMxId;
    private Object qwdGroupId;
    private int qwdId;
    private String qwdJc;
    private int qwdLx;
    private String qwdMc;
    private int qwid;
    private int tjsl;
    private String xxrksj;

    @SerializedName(value = "yjqwMxApCount", alternate = {"yjqwMxApCommandVOCount"})
    private Object yjqwMxApCount;

    @SerializedName(value = "yjqwMxApList", alternate = {"yjqwMxApCommandVOList"})
    private List<ApList> yjqwMxApList;

    @SerializedName(value = "yjqwMxApMxCount", alternate = {"yjqwMxApMxCommandVOCount"})
    private Object yjqwMxApMxCount;

    @SerializedName(value = "yjqwMxApMxList", alternate = {"yjqwMxApMxCommandVOList"})
    private List<ApMxList> yjqwMxApMxList;

    private String yq;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getDjrid() {
        return djrid;
    }

    public void setDjrid(int djrid) {
        this.djrid = djrid;
    }

    public String getDjrname() {
        return djrname;
    }

    public void setDjrname(String djrname) {
        this.djrname = djrname;
    }

    public int getDutyStationTypeIconId() {
        return dutyStationTypeIconId;
    }

    public void setDutyStationTypeIconId(int dutyStationTypeIconId) {
        this.dutyStationTypeIconId = dutyStationTypeIconId;
    }

    public String getDutyStationTypeIconName() {
        return dutyStationTypeIconName;
    }

    public void setDutyStationTypeIconName(String dutyStationTypeIconName) {
        this.dutyStationTypeIconName = dutyStationTypeIconName;
    }

    public String getDutyStationTypeIconUrl() {
        return dutyStationTypeIconUrl;
    }

    public void setDutyStationTypeIconUrl(String dutyStationTypeIconUrl) {
        this.dutyStationTypeIconUrl = dutyStationTypeIconUrl;
    }

    public int getDutyStationTypeId() {
        return dutyStationTypeId;
    }

    public void setDutyStationTypeId(int dutyStationTypeId) {
        this.dutyStationTypeId = dutyStationTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLan() {
        return lan;
    }

    public void setLan(Double lan) {
        this.lan = lan;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public int getMjsl() {
        return mjsl;
    }

    public void setMjsl(int mjsl) {
        this.mjsl = mjsl;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Object getPid() {
        return pid;
    }

    public void setPid(Object pid) {
        this.pid = pid;
    }

    public int getQwMxId() {
        return qwMxId;
    }

    public void setQwMxId(int qwMxId) {
        this.qwMxId = qwMxId;
    }

    public Object getQwdGroupId() {
        return qwdGroupId;
    }

    public void setQwdGroupId(Object qwdGroupId) {
        this.qwdGroupId = qwdGroupId;
    }

    public int getQwdId() {
        return qwdId;
    }

    public void setQwdId(int qwdId) {
        this.qwdId = qwdId;
    }

    public String getQwdJc() {
        return qwdJc;
    }

    public void setQwdJc(String qwdJc) {
        this.qwdJc = qwdJc;
    }

    public int getQwdLx() {
        return qwdLx;
    }

    public void setQwdLx(int qwdLx) {
        this.qwdLx = qwdLx;
    }

    public String getQwdMc() {
        return qwdMc;
    }

    public void setQwdMc(String qwdMc) {
        this.qwdMc = qwdMc;
    }

    public int getQwid() {
        return qwid;
    }

    public void setQwid(int qwid) {
        this.qwid = qwid;
    }

    public int getTjsl() {
        return tjsl;
    }

    public void setTjsl(int tjsl) {
        this.tjsl = tjsl;
    }

    public String getXxrksj() {
        return xxrksj;
    }

    public void setXxrksj(String xxrksj) {
        this.xxrksj = xxrksj;
    }

    public Object getYjqwMxApCount() {
        return yjqwMxApCount;
    }

    public void setYjqwMxApCount(Object yjqwMxApCount) {
        this.yjqwMxApCount = yjqwMxApCount;
    }

    public List<ApList> getYjqwMxApList() {
        return yjqwMxApList;
    }

    public void setYjqwMxApList(List<ApList> yjqwMxApList) {
        this.yjqwMxApList = yjqwMxApList;
    }

    public Object getYjqwMxApMxCount() {
        return yjqwMxApMxCount;
    }

    public void setYjqwMxApMxCount(Object yjqwMxApMxCount) {
        this.yjqwMxApMxCount = yjqwMxApMxCount;
    }

    public List<ApMxList> getYjqwMxApMxList() {
        return yjqwMxApMxList;
    }

    public void setYjqwMxApMxList(List<ApMxList> yjqwMxApMxList) {
        this.yjqwMxApMxList = yjqwMxApMxList;
    }

    public String getYq() {
        return yq;
    }

    public void setYq(String yq) {
        this.yq = yq;
    }
}