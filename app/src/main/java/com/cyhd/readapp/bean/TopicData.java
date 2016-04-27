package com.cyhd.readapp.bean;

import java.util.List;

/**
 * Created by chuangyihudonghu on 16/3/30.
 */
public class TopicData {

    private String message;
    private DataEntity data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataEntity {

        private List<ExpertListEntity> expertList;

        public List<ExpertListEntity> getExpertList() {
            return expertList;
        }

        public void setExpertList(List<ExpertListEntity> expertList) {
            this.expertList = expertList;
        }

        public static class ExpertListEntity {
            private String expertId;
            private String alias;
            private String stitle;
            private String picurl;
            private String name;
            private String description;
            private String headpicurl;
            private String classification;
            private int state;
            private int expertState;
            private int concernCount;
            private long createTime;
            private String title;
            private int questionCount;
            private int answerCount;

            public String getExpertId() {
                return expertId;
            }

            public void setExpertId(String expertId) {
                this.expertId = expertId;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getStitle() {
                return stitle;
            }

            public void setStitle(String stitle) {
                this.stitle = stitle;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getHeadpicurl() {
                return headpicurl;
            }

            public void setHeadpicurl(String headpicurl) {
                this.headpicurl = headpicurl;
            }

            public String getClassification() {
                return classification;
            }

            public void setClassification(String classification) {
                this.classification = classification;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getExpertState() {
                return expertState;
            }

            public void setExpertState(int expertState) {
                this.expertState = expertState;
            }

            public int getConcernCount() {
                return concernCount;
            }

            public void setConcernCount(int concernCount) {
                this.concernCount = concernCount;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getQuestionCount() {
                return questionCount;
            }

            public void setQuestionCount(int questionCount) {
                this.questionCount = questionCount;
            }

            public int getAnswerCount() {
                return answerCount;
            }

            public void setAnswerCount(int answerCount) {
                this.answerCount = answerCount;
            }
        }
    }
}
