package com.wokun.dset.store.bean;


import com.google.gson.annotations.SerializedName;

public class VersionBean {

    /**
     * status : 0001
     * message : 获取成功
     * data : {"switch":1,"version":"2.0","url":"http://api.dasether.com/apk/app-release.apk","time":1553583251,"type":"1"}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * switch : 1
         * version : 2.0
         * url : http://api.dasether.com/apk/app-release.apk
         * time : 1553583251
         * type : 1
         */

        @SerializedName("switch")
        private int switchX;
        private String version;
        private String url;
        private int time;
        private String type;

        public int getSwitchX() {
            return switchX;
        }

        public void setSwitchX(int switchX) {
            this.switchX = switchX;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
