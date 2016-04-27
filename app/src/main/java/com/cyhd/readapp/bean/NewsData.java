package com.cyhd.readapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huzhimin on 16/3/15.
 */

public class NewsData {

//    @SerializedName("url_3w")
    private String url_3w;
    private int replyCount;
    private int imgType;
    private String ltitle;
    private String digest;
    private String url;
    private String title;
    private String imgsrc;
    private List<AdsEntity> ads;
    private List<ImgextraEntity> imgextra;

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public String getUrl() {
        return url;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImgType() {
        return imgType;
    }


    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }


    public void setDigest(String digest) {
        this.digest = digest;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }


    public void setAds(List<AdsEntity> ads) {
        this.ads = ads;
    }

    public void setImgextra(List<ImgextraEntity> imgextra) {
        this.imgextra = imgextra;
    }


    public int getReplyCount() {
        return replyCount;
    }


    public String getDigest() {
        return digest;
    }


    public String getTitle() {
        return title;
    }


    public String getImgsrc() {
        return imgsrc;
    }

    public List<AdsEntity> getAds() {
        return ads;
    }

    public List<ImgextraEntity> getImgextra() {
        return imgextra;
    }

    @Override
    public String toString() {
        return "Data{" +
                "url_3w='" + url_3w + '\'' +
                ", replyCount=" + replyCount +
                ", ltitle='" + ltitle + '\'' +
                ", digest='" + digest + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", ads=" + ads +
                ", imgextra=" + imgextra +
                '}';
    }

    public static class AdsEntity {
        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "AdsEntity{" +
                    "title='" + title + '\'' +
                    ", tag='" + tag + '\'' +
                    ", imgsrc='" + imgsrc + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class ImgextraEntity {

        private String imgsrc;

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        @Override
        public String toString() {
            return "ImgextraEntity{" +
                    "imgsrc='" + imgsrc + '\'' +
                    '}';
        }
    }
}
