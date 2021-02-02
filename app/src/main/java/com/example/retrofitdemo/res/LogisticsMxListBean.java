package com.example.retrofitdemo.res;

import java.util.List;

public class LogisticsMxListBean {

    /**
     * apList : [{"apMxList":[],"gxsj":null,"hhid":null,"hhmc":"主力","id":7,"idArr":null,"mxid":11,"qwdMxId":null,"qwid":null,"xxrksj":"2020-12-02 19:28:52","yq":"主力"},{"apMxList":[],"gxsj":null,"hhid":null,"hhmc":"辅助","id":8,"idArr":null,"mxid":11,"qwdMxId":null,"qwid":null,"xxrksj":"2020-12-02 19:28:52","yq":"辅助"}]
     * apMxList : [{"apid":8,"cl":"{}","gxsj":null,"hasAp":1,"id":19,"idArr":null,"isContact":0,"mxid":11,"qwdMxId":null,"qwid":null,"type":null,"userid":4197,"username":"胡凌云","xxrksj":"2020-12-02 19:28:52","zb":"{\"zb\":[],\"zbb\":[]}"},{"apid":8,"cl":"{}","gxsj":null,"hasAp":1,"id":20,"idArr":null,"isContact":0,"mxid":11,"qwdMxId":null,"qwid":null,"type":null,"userid":13663,"username":"韩承昊","xxrksj":"2020-12-02 19:28:52","zb":"{\"zb\":[],\"zbb\":[]}"},{"apid":9,"cl":"{}","gxsj":null,"hasAp":1,"id":21,"idArr":null,"isContact":0,"mxid":12,"qwdMxId":null,"qwid":null,"type":null,"userid":22863,"username":"臧君","xxrksj":"2020-12-02 20:05:48","zb":"{\"zb\":[],\"zbb\":[]}"},{"apid":10,"cl":"{\"licensePlate\":\"苏A3905警\",\"id\":631}","gxsj":null,"hasAp":1,"id":22,"idArr":null,"isContact":0,"mxid":12,"qwdMxId":null,"qwid":null,"type":null,"userid":22788,"username":"肖博云","xxrksj":"2020-12-02 20:05:48","zb":"{\"zb\":[],\"zbb\":[]}"},{"apid":10,"cl":"{}","gxsj":null,"hasAp":0,"id":23,"idArr":null,"isContact":0,"mxid":12,"qwdMxId":null,"qwid":null,"type":null,"userid":24933,"username":"孔志磊","xxrksj":"2020-12-02 20:05:48","zb":"{\"zb\":[],\"zbb\":[]}"}]
     * bsf :
     * bushu : null
     * cars : [{"limitNum":5,"brandName":"大众","color":"1","modelId":45,"vehiclePhotoUrl":"/police-run/attachment/download?id=null","typeName":"普通巡逻车","vehicleIconUrl":"/police-run/attachment/download?id=null","capacity":"","modelName":"朗逸","jsy":[{"realName":"胡淑敏","phone":"13952023235","id":25587}],"licensePlate":"苏A3905警","aggregationIconUrl":"/police-run/attachment/download?id=null","fenpei":[{"orgName":"机动武装巡逻三大队（九大队）","orgCode":"320100165900","num":"6","id":2296},{"orgName":"鼓楼巡特警大队","orgCode":"320106160000","num":"3","id":848}],"orgCode":"320105168300","brandId":1,"typeId":2,"id":631}]
     * djrid : 25712
     * djrname : 支队五
     * dxtz : 1
     * fcsj : 2020-12-03 08:00:00
     * fcsjstr : []
     * gxsj : null
     * id : 13
     * idArr : null
     * isAppDenglu : 1
     * isBushu : null
     * isFinish : 1
     * jlsl : 6
     * jssj : 2020-12-05 00:00:00
     * kssj : 2020-12-01 00:00:00
     * lan : 32.0660655193336
     * lianluo : [{"realName":"陈腾飞","id":2151}]
     * lindao : [{"realName":"王百洲","id":1156}]
     * lon : 118.7555223257549
     * mjsl : 2
     * orgArr : null
     * orgcode : 320100165900
     * orgname : 机动武装巡逻三大队（九大队）
     * pic :
     * qsr : 朱笑凡
     * qsruserid : 14926
     * qssj : 2020-12-02 16:01:30
     * qunid : 104e2cc1575f49cc84c89f8ab7edd44a
     * qwaddr : 天津新村
     * qwid : 11
     * qwlx : null
     * qwname : 12月初保障勤务
     * qwyq : 武巡三
     * qwzt : 3
     * sjdList : null
     * sjdstr :
     * tjsl : 4
     * xxrksj : 2020-12-02 19:28:52
     * yjqwQwdMxVOList : null
     * zbyq : [{"organizationName":"机动武装巡逻三大队（九大队）","organizationCode":"320100165900","zb":[{"num":0,"equipmentCategory":"特种车辆","equipmentName":"巡逻车"},{"num":0,"equipmentCategory":"单警装备","equipmentName":"单警执法记录仪","iconPath":"/police-run/attachment/download?id=1368"}],"id":2296,"zbb":[]}]
     * zzyq : [{"equipmentName":"大檐帽","equipmentId":15},{"equipmentName":"战斗靴","equipmentId":16},{"equipmentName":"制式皮鞋","equipmentId":17}]
     */

    private String bsf;
    private Object bushu;
    private String cars;
    private int djrid;
    private String djrname;
    private int dxtz;
    private String fcsj;
    private String fcsjstr;
    private Object gxsj;
    private int id;
    private Object idArr;
    private int isAppDenglu;
    private Object isBushu;
    private int isFinish;
    private int jlsl;
    private String jssj;
    private String kssj;
    private double lan;
    private String lianluo;
    private String lindao;
    private double lon;
    private int mjsl;
    private Object orgArr;
    private String orgcode;
    private String orgname;
    private String pic;
    private String qsr;
    private int qsruserid;
    private String qssj;
    private String qunid;
    private String qwaddr;
    private int qwid;
    private Object qwlx;
    private String qwname;
    private String qwyq;
    private String qwzt;
    private Object sjdList;
    private String sjdstr;
    private int tjsl;
    private String xxrksj;
    private List<LogisticsQwdMxVOListBean> yjqwQwdMxVOList;
    private String zbyq;
    private String zzyq;
    private List<ApList> apList;
    private List<ApMxList> apMxList;
    private int apMxNumbers;

    public String getBsf() {
        return bsf;
    }

    public void setBsf(String bsf) {
        this.bsf = bsf;
    }

    public Object getBushu() {
        return bushu;
    }

    public void setBushu(Object bushu) {
        this.bushu = bushu;
    }

    public String getCars() {
        return cars;
    }

    public void setCars(String cars) {
        this.cars = cars;
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

    public int getDxtz() {
        return dxtz;
    }

    public void setDxtz(int dxtz) {
        this.dxtz = dxtz;
    }

    public String getFcsj() {
        return fcsj;
    }

    public void setFcsj(String fcsj) {
        this.fcsj = fcsj;
    }

    public String getFcsjstr() {
        return fcsjstr;
    }

    public void setFcsjstr(String fcsjstr) {
        this.fcsjstr = fcsjstr;
    }

    public Object getGxsj() {
        return gxsj;
    }

    public void setGxsj(Object gxsj) {
        this.gxsj = gxsj;
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

    public int getIsAppDenglu() {
        return isAppDenglu;
    }

    public void setIsAppDenglu(int isAppDenglu) {
        this.isAppDenglu = isAppDenglu;
    }

    public Object getIsBushu() {
        return isBushu;
    }

    public void setIsBushu(Object isBushu) {
        this.isBushu = isBushu;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public int getJlsl() {
        return jlsl;
    }

    public void setJlsl(int jlsl) {
        this.jlsl = jlsl;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    public String getLianluo() {
        return lianluo;
    }

    public void setLianluo(String lianluo) {
        this.lianluo = lianluo;
    }

    public String getLindao() {
        return lindao;
    }

    public void setLindao(String lindao) {
        this.lindao = lindao;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getMjsl() {
        return mjsl;
    }

    public void setMjsl(int mjsl) {
        this.mjsl = mjsl;
    }

    public Object getOrgArr() {
        return orgArr;
    }

    public void setOrgArr(Object orgArr) {
        this.orgArr = orgArr;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getQsr() {
        return qsr;
    }

    public void setQsr(String qsr) {
        this.qsr = qsr;
    }

    public int getQsruserid() {
        return qsruserid;
    }

    public void setQsruserid(int qsruserid) {
        this.qsruserid = qsruserid;
    }

    public String getQssj() {
        return qssj;
    }

    public void setQssj(String qssj) {
        this.qssj = qssj;
    }

    public String getQunid() {
        return qunid;
    }

    public void setQunid(String qunid) {
        this.qunid = qunid;
    }

    public String getQwaddr() {
        return qwaddr;
    }

    public void setQwaddr(String qwaddr) {
        this.qwaddr = qwaddr;
    }

    public int getQwid() {
        return qwid;
    }

    public void setQwid(int qwid) {
        this.qwid = qwid;
    }

    public Object getQwlx() {
        return qwlx;
    }

    public void setQwlx(Object qwlx) {
        this.qwlx = qwlx;
    }

    public String getQwname() {
        return qwname;
    }

    public void setQwname(String qwname) {
        this.qwname = qwname;
    }

    public String getQwyq() {
        return qwyq;
    }

    public void setQwyq(String qwyq) {
        this.qwyq = qwyq;
    }

    public String getQwzt() {
        return qwzt;
    }

    public void setQwzt(String qwzt) {
        this.qwzt = qwzt;
    }

    public Object getSjdList() {
        return sjdList;
    }

    public void setSjdList(Object sjdList) {
        this.sjdList = sjdList;
    }

    public String getSjdstr() {
        return sjdstr;
    }

    public void setSjdstr(String sjdstr) {
        this.sjdstr = sjdstr;
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

    public List<LogisticsQwdMxVOListBean> getYjqwQwdMxVOList() {
        return yjqwQwdMxVOList;
    }

    public void setYjqwQwdMxVOList(List<LogisticsQwdMxVOListBean> yjqwQwdMxVOList) {
        this.yjqwQwdMxVOList = yjqwQwdMxVOList;
    }

    public String getZbyq() {
        return zbyq;
    }

    public void setZbyq(String zbyq) {
        this.zbyq = zbyq;
    }

    public String getZzyq() {
        return zzyq;
    }

    public void setZzyq(String zzyq) {
        this.zzyq = zzyq;
    }

    public List<ApList> getApList() {
        return apList;
    }

    public void setApList(List<ApList> apList) {
        this.apList = apList;
    }

    public List<ApMxList> getApMxList() {
        return apMxList;
    }

    public void setApMxList(List<ApMxList> apMxList) {
        this.apMxList = apMxList;
    }

    public int getApMxNumbers() {
        return apMxNumbers;
    }

    public void setApMxNumbers(int apMxNumbers) {
        this.apMxNumbers = apMxNumbers;
    }
}
