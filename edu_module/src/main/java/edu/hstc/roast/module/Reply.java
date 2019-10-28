package edu.hstc.roast.module;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

public class Reply {
    private String id;
    private int owner_id;
    private String introduce;
    private String context;
    private Date time;
    private String posting_id;
    private int target_user;
    private String target_reply;

    private User owerUser;
    private User targetUser;
    private Reply target;
    private Posting posting;
    private List<Reply> replies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPosting_id() {
        return posting_id;
    }

    public void setPosting_id(String posting_id) {
        this.posting_id = posting_id;
    }

    public int getTarget_user() {
        return target_user;
    }

    public void setTarget_user(int target_user) {
        this.target_user = target_user;
    }

    public String getTarget_reply() {
        return target_reply;
    }

    public void setTarget_reply(String target_reply) {
        this.target_reply = target_reply;
    }

    public User getOwerUser() {
        return owerUser;
    }

    public void setOwerUser(User owerUser) {
        this.owerUser = owerUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public Reply getTarget() {
        return target;
    }

    public void setTarget(Reply target) {
        this.target = target;
    }

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", owner_id=" + owner_id +
                ", introduce='" + introduce + '\'' +
                ", context='" + context + '\'' +
                ", time=" + time +
                ", posting_id='" + posting_id + '\'' +
                ", target_user=" + target_user +
                ", target_reply='" + target_reply + '\'' +
                ", owerUser=" + owerUser +
                ", targetUser=" + targetUser +
                ", target=" + target +
                ", posting=" + posting +
                ", replies=" + replies +
                '}';
    }
}
