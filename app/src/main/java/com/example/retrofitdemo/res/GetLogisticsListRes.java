package com.example.retrofitdemo.res;

import android.util.SparseArray;



import java.util.List;

public class GetLogisticsListRes {

    /**
     * code : 0
     * currentPage : 1
     * msg : OK
     * page : null
     * pageSize : 100
     * records : 11
     * rows : [{"daduiQunid":"","id":163,"isAppDenglu":1,"isFinish":1,"iszdui":"1","lan":null,"lianluo":"[{\"id\":177,\"realName\":\"联络员1\"}]","lindao":"[{\"id\":251,\"realName\":\"张三\"}]","lon":null,"mxid":417,"orgcode":"","orgstr":"[{\"organizationName\":\"龙虎突击大队（一大队）\",\"abbreviation\":\"龙虎一大队\",\"organizationCode\":\"320100165100\",\"id\":79,\"mjsl\":\"2\",\"tqsl\":\"2\"}]","qwaddr":"dddd","qwname":"支队2创建勤务给龙虎","xxrksj":"2020-09-15 11:30:47","zhiduiQunid":""},{"daduiQunid":"","id":160,"isAppDenglu":1,"isFinish":1,"iszdui":"1","lan":null,"lianluo":"[{\"id\":180,\"realName\":\"领导2\"}]","lindao":"[{\"id\":175,\"realName\":\"民警\"}]","lon":null,"mxid":412,"orgcode":"","orgstr":"[{\"organizationName\":\"龙虎突击大队（一大队）\",\"abbreviation\":\"龙虎一大队\",\"organizationCode\":\"320100165100\",\"id\":79,\"mjsl\":\"2\",\"tqsl\":\"2\"}]","qwaddr":"123","qwname":"勤务流转0910-1","xxrksj":"2020-09-10 18:37:37","zhiduiQunid":""},{"daduiQunid":"","id":159,"isAppDenglu":1,"isFinish":1,"iszdui":"1","lan":null,"lianluo":"[{\"id\":234,\"realName\":\"联络2\"},{\"id\":177,\"realName\":\"联络员1\"},{\"id\":180,\"realName\":\"领导2\"}]","lindao":"[{\"id\":251,\"realName\":\"张三\"},{\"id\":175,\"realName\":\"民警\"},{\"id\":251,\"realName\":\"张三\"},{\"id\":175,\"realName\":\"民警\"}]","lon":null,"mxid":410,"orgcode":"","orgstr":"[{\"organizationName\":\"龙虎突击大队（一大队）\",\"abbreviation\":\"龙虎一大队\",\"organizationCode\":\"320100165100\",\"id\":79,\"mjsl\":\"2\",\"tqsl\":\"2\",\"yq\":\"要求1\"},{\"organizationName\":\"勤务大队（三大队）\",\"abbreviation\":\"勤务\",\"organizationCode\":\"320100165300\",\"id\":80,\"mjsl\":\"3\",\"tqsl\":\"3\",\"yq\":\"要求2\"}]","qwaddr":"马良大街","qwname":"0909测试勤务","xxrksj":"2020-09-09 17:52:28","zhiduiQunid":""},{"daduiQunid":"","id":158,"isAppDenglu":1,"isFinish":1,"iszdui":"1","lan":null,"lianluo":"[{\"id\":234,\"realName\":\"联络2\"}]","lindao":"[{\"id\":251,\"realName\":\"张三\"},{\"id\":175,\"realName\":\"民警\"},{\"id\":182,\"realName\":\"民警2\"}]","lon":null,"mxid":407,"orgcode":"","orgstr":"[{\"organizationName\":\"龙虎突击大队（一大队）\",\"abbreviation\":\"龙虎一大队\",\"organizationCode\":\"320100165100\",\"id\":79,\"mjsl\":\"2\",\"tqsl\":\"2\",\"yq\":\"龙虎一大队\"},{\"organizationName\":\"勤务大队（三大队）\",\"abbreviation\":\"勤务\",\"organizationCode\":\"320100165300\",\"id\":80,\"mjsl\":\"3\",\"tqsl\":\"3\",\"yq\":\"勤务大队\"}]","qwaddr":"鸡鸣寺","qwname":"9月保障性勤务","xxrksj":"2020-09-08 15:49:58","zhiduiQunid":""},{"daduiQunid":"","id":386,"isAppDenglu":1,"isFinish":1,"iszdui":"2","lan":32.060773196076326,"lianluo":"[{\"realName\":\"联络员1\",\"id\":177}]","lindao":"[{\"realName\":\"张三\",\"id\":251}]","lon":118.75729232024298,"mxid":386,"orgcode":"320100165100","orgstr":"","qwaddr":"南京","qwname":"大队创建090401","xxrksj":"2020-09-04 09:16:45","zhiduiQunid":""},{"daduiQunid":"","id":380,"isAppDenglu":1,"isFinish":1,"iszdui":"2","lan":null,"lianluo":"[{\"realName\":\"联络员1\",\"id\":177}]","lindao":"[{\"realName\":\"张三\",\"id\":251}]","lon":null,"mxid":380,"orgcode":"320100165100","orgstr":"","qwaddr":"南京南京","qwname":"tt-0831-04","xxrksj":"2020-08-31 15:38:30","zhiduiQunid":""},{"daduiQunid":"","id":303,"isAppDenglu":1,"isFinish":1,"iszdui":"2","lan":null,"lianluo":"[]","lindao":"[{\"realName\":\"民警\",\"id\":175}]","lon":null,"mxid":303,"orgcode":"320100165100","orgstr":"","qwaddr":"","qwname":"20200811一大队自主勤务","xxrksj":"2020-08-11 10:24:16","zhiduiQunid":""},{"daduiQunid":"","id":293,"isAppDenglu":1,"isFinish":1,"iszdui":"2","lan":32.074267205787045,"lianluo":"[{\"realName\":\"联络员1\",\"id\":177}]","lindao":"[{\"realName\":\"张三\",\"id\":251}]","lon":118.7999175525705,"mxid":293,"orgcode":"320100165100","orgstr":"","qwaddr":"test3","qwname":"tes12","xxrksj":"2020-08-10 14:30:09","zhiduiQunid":""},{"daduiQunid":"","id":288,"isAppDenglu":1,"isFinish":1,"iszdui":"2","lan":32.04454536544172,"lianluo":"[{\"realName\":\"联络员1\",\"id\":177}]","lindao":"[{\"realName\":\"民警\",\"id\":175}]","lon":118.77378694990301,"mxid":288,"orgcode":"320100165100","orgstr":"","qwaddr":"test12","qwname":"test11","xxrksj":"2020-06-24 13:33:51","zhiduiQunid":""},{"daduiQunid":"","id":265,"isAppDenglu":1,"isFinish":1,"iszdui":"2","lan":32.02866149418054,"lianluo":"[{\"realName\":\"联络2\",\"id\":234}]","lindao":"[{\"realName\":\"民警\",\"id\":175},{\"realName\":\"民警2\",\"id\":182}]","lon":118.85066465884195,"mxid":265,"orgcode":"400101","orgstr":"","qwaddr":"勤务点","qwname":"0522勤务","xxrksj":"2020-05-22 14:16:41","zhiduiQunid":""},{"daduiQunid":"","id":262,"isAppDenglu":1,"isFinish":1,"iszdui":"2","lan":32.07382530710597,"lianluo":"[{\"realName\":\"联络员1\",\"id\":177}]","lindao":"[{\"realName\":\"民警\",\"id\":175}]","lon":118.77447168762475,"mxid":262,"orgcode":"400101","orgstr":"","qwaddr":"玄武区","qwname":"应急勤务测试","xxrksj":"2020-05-06 21:41:44","zhiduiQunid":""}]
     * total : 1
     */

    private int code;
    private int currentPage;
    private String msg;
    private Object page;
    private int pageSize;
    private int records;
    private int total;
    private List<RowsBean> rows;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * daduiQunid :
         * id : 163
         * isAppDenglu : 1
         * isFinish : 1
         * iszdui : 1
         * lan : null
         * lianluo : [{"id":177,"realName":"联络员1"}]
         * lindao : [{"id":251,"realName":"张三"}]
         * lon : null
         * mxid : 417
         * orgcode :
         * orgstr : [{"organizationName":"龙虎突击大队（一大队）","abbreviation":"龙虎一大队","organizationCode":"320100165100","id":79,"mjsl":"2","tqsl":"2"}]
         * qwaddr : dddd
         * qwname : 支队2创建勤务给龙虎
         * xxrksj : 2020-09-15 11:30:47
         * zhiduiQunid :
         */

        private String daduiQunid;
        private int id;
        private int isAppDenglu;
        private int isFinish;
        private String iszdui;
        private double lan;
        private String lianluo;
        private String lindao;
        private double lon;
        private int mxid;
        private String orgcode;
        private String orgstr;
        private String qwaddr;
        private String qwname;
        private String xxrksj;
        private String zhiduiQunid;
        private LogisticsDetailRes.DataBean dataBean;
        private SparseArray<LogisticsMxDetailRes.DataBean> mxDataBean;
        private String jobTitle; //
        private boolean flag;
        private int zt;
        private int type;  //0:组员，1：组长
        private int qwid;
        private int jobTitleType;

        public String getDaduiQunid() {
            return daduiQunid;
        }

        public void setDaduiQunid(String daduiQunid) {
            this.daduiQunid = daduiQunid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsAppDenglu() {
            return isAppDenglu;
        }

        public void setIsAppDenglu(int isAppDenglu) {
            this.isAppDenglu = isAppDenglu;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public String getIszdui() {
            return iszdui;
        }

        public void setIszdui(String iszdui) {
            this.iszdui = iszdui;
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

        public int getMxid() {
            return mxid;
        }

        public void setMxid(int mxid) {
            this.mxid = mxid;
        }

        public String getOrgcode() {
            return orgcode;
        }

        public void setOrgcode(String orgcode) {
            this.orgcode = orgcode;
        }

        public String getOrgstr() {
            return orgstr;
        }

        public void setOrgstr(String orgstr) {
            this.orgstr = orgstr;
        }

        public String getQwaddr() {
            return qwaddr;
        }

        public void setQwaddr(String qwaddr) {
            this.qwaddr = qwaddr;
        }

        public String getQwname() {
            return qwname;
        }

        public void setQwname(String qwname) {
            this.qwname = qwname;
        }

        public String getXxrksj() {
            return xxrksj;
        }

        public void setXxrksj(String xxrksj) {
            this.xxrksj = xxrksj;
        }

        public String getZhiduiQunid() {
            return zhiduiQunid;
        }

        public void setZhiduiQunid(String zhiduiQunid) {
            this.zhiduiQunid = zhiduiQunid;
        }

        public LogisticsDetailRes.DataBean getDataBean() {
            return dataBean;
        }

        public void setDataBean(LogisticsDetailRes.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        public SparseArray<LogisticsMxDetailRes.DataBean> getMxDataBean() {
            return mxDataBean;
        }

        public void setMxDataBean(SparseArray<LogisticsMxDetailRes.DataBean> mxDataBean) {
            this.mxDataBean = mxDataBean;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public int getZt() {
            return zt;
        }

        public void setZt(int zt) {
            this.zt = zt;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getQwid() {
            return qwid;
        }

        public void setQwid(int qwid) {
            this.qwid = qwid;
        }

        public int getJobTitleType() {
            return jobTitleType;
        }

        public void setJobTitleType(int jobTitleType) {
            this.jobTitleType = jobTitleType;
        }

    }
}
