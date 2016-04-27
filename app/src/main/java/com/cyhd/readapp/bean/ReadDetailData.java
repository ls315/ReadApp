package com.cyhd.readapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huzhimin on 16/4/4.
 */
public class ReadDetailData implements Serializable {

    private String body;
    private int replyCount;
    private String shareLink;
    private String digest;
    private String dkeys;
    private String docid;
    private boolean picnews;
    private String title;

    private SourceinfoEntity sourceinfo;
    private String tid;
    private String template;
    private int threadVote;
    private int threadAgainst;
    private String replyBoard;
    private String source;
    private String voicecomment;
    private boolean hasNext;
    private String ptime;
    private List<?> users;
    private List<?> ydbaike;
    private List<?> link;
    private List<?> votes;

    private List<ImgEntity> img;
    private List<?> topiclist_news;
    private List<?> topiclist;
    private List<?> boboList;
    private List<?> apps;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDkeys() {
        return dkeys;
    }

    public void setDkeys(String dkeys) {
        this.dkeys = dkeys;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public boolean isPicnews() {
        return picnews;
    }

    public void setPicnews(boolean picnews) {
        this.picnews = picnews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SourceinfoEntity getSourceinfo() {
        return sourceinfo;
    }

    public void setSourceinfo(SourceinfoEntity sourceinfo) {
        this.sourceinfo = sourceinfo;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getThreadVote() {
        return threadVote;
    }

    public void setThreadVote(int threadVote) {
        this.threadVote = threadVote;
    }

    public int getThreadAgainst() {
        return threadAgainst;
    }

    public void setThreadAgainst(int threadAgainst) {
        this.threadAgainst = threadAgainst;
    }

    public String getReplyBoard() {
        return replyBoard;
    }

    public void setReplyBoard(String replyBoard) {
        this.replyBoard = replyBoard;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVoicecomment() {
        return voicecomment;
    }

    public void setVoicecomment(String voicecomment) {
        this.voicecomment = voicecomment;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<?> getUsers() {
        return users;
    }

    public void setUsers(List<?> users) {
        this.users = users;
    }

    public List<?> getYdbaike() {
        return ydbaike;
    }

    public void setYdbaike(List<?> ydbaike) {
        this.ydbaike = ydbaike;
    }

    public List<?> getLink() {
        return link;
    }

    public void setLink(List<?> link) {
        this.link = link;
    }

    public List<?> getVotes() {
        return votes;
    }

    public void setVotes(List<?> votes) {
        this.votes = votes;
    }

    public List<ImgEntity> getImg() {
        return img;
    }

    public void setImg(List<ImgEntity> img) {
        this.img = img;
    }

    public List<?> getTopiclist_news() {
        return topiclist_news;
    }

    public void setTopiclist_news(List<?> topiclist_news) {
        this.topiclist_news = topiclist_news;
    }

    public List<?> getTopiclist() {
        return topiclist;
    }

    public void setTopiclist(List<?> topiclist) {
        this.topiclist = topiclist;
    }

    public List<?> getBoboList() {
        return boboList;
    }

    public void setBoboList(List<?> boboList) {
        this.boboList = boboList;
    }

    public List<?> getApps() {
        return apps;
    }

    public void setApps(List<?> apps) {
        this.apps = apps;
    }

    public static class SourceinfoEntity implements Serializable{
        private String alias;
        private String tname;
        private String ename;
        private String tid;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }

    public static class ImgEntity implements Serializable{
        private String ref;
        private String pixel;
        private String alt;
        private String src;

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getPixel() {
            return pixel;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
