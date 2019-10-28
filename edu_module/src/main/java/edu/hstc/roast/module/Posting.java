package edu.hstc.roast.module;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

public class Posting {
    private int posting_id;
    private int theme_id;
    private String id;
    private String name;
    private String introduce;
    private int owner_id;
    private User owner;
    private Date time;
    private String context;// 发帖人吐槽内容


    private List<Reply> replyedList;
    private User user;
    private Theme parent;


    public int getPosting_id() {
        return posting_id;
    }

    public void setPosting_id(int posting_id) {
        this.posting_id = posting_id;
    }

    public int getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(int theme_id) {
        this.theme_id = theme_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Reply> getReplyedList() {
        return replyedList;
    }

    public void setReplyedList(List<Reply> replyedList) {
        this.replyedList = replyedList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Theme getParent() {
        return parent;
    }

    public void setParent(Theme parent) {
        this.parent = parent;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Posting{" +
                "posting_id=" + posting_id +
                ", theme_id=" + theme_id +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", owner_id='" + owner_id + '\'' +
                ", owner=" + owner +
                ", time=" + time +
                ", context='" + context + '\'' +
                ", replyedList=" + replyedList +
                ", user=" + user +
                ", parent=" + parent +
                '}';
    }
}
