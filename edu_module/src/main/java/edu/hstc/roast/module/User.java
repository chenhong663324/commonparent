package edu.hstc.roast.module;

import java.util.List;

public class User {
    private int id;
    private String uuid;
    private String username;
    private String password;
    private String personal_signature;
    private String profile_pic;
    private String panel_pic;

    private List<Posting> ownPostingList;
    private List<Posting> replyed_postingList;
    private List<Reply> myReplies;
//    private String portrait; //


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonal_signature() {
        return personal_signature;
    }

    public void setPersonal_signature(String personal_signature) {
        this.personal_signature = personal_signature;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getPanel_pic() {
        return panel_pic;
    }

    public void setPanel_pic(String panel_pic) {
        this.panel_pic = panel_pic;
    }

    public List<Posting> getOwnPostingList() {
        return ownPostingList;
    }

    public void setOwnPostingList(List<Posting> ownPostingList) {
        this.ownPostingList = ownPostingList;
    }

    public List<Posting> getReplyed_postingList() {
        return replyed_postingList;
    }

    public void setReplyed_postingList(List<Posting> replyed_postingList) {
        this.replyed_postingList = replyed_postingList;
    }

    public List<Reply> getMyReplies() {
        return myReplies;
    }

    public void setMyReplies(List<Reply> myReplies) {
        this.myReplies = myReplies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", personal_signature='" + personal_signature + '\'' +
                ", profile_pic='" + profile_pic + '\'' +
                ", panel_pic='" + panel_pic + '\'' +
                ", ownPostingList=" + ownPostingList +
                ", replyed_postingList=" + replyed_postingList +
                ", myReplies=" + myReplies +
                '}';
    }
}
