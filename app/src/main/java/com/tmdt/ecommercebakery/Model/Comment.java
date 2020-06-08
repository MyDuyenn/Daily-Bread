package com.tmdt.ecommercebakery.Model;

public class Comment {
    private String pid, cmtName, cmtComment, date, time;
    public Comment() {
    }

    public Comment(String pid, String cmtName, String cmtComment, String date, String time) {
        this.pid = pid;
        this.cmtName = cmtName;
        this.cmtComment = cmtComment;
        this.date = date;
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCmtName() {
        return cmtName;
    }

    public void setCmtName(String cmtName) {
        this.cmtName = cmtName;
    }

    public String getCmtComment() {
        return cmtComment;
    }

    public void setCmtComment(String cmtComment) {
        this.cmtComment = cmtComment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
