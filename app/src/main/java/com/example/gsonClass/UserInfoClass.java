package com.example.gsonClass;

import java.util.List;

public class UserInfoClass {

    private int level;
    private long listenSongs;
    private Boolean mobileSign;
    private Boolean pcSign;
    private ProfileDTO profile;
    private Boolean peopleCanSeeMyPlayRecord;
    private Boolean adValid;
    private Integer code;
    private Long createTime;
    private Integer createDays;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public long getListenSongs() {
        return listenSongs;
    }

    public void setListenSongs(Integer listenSongs) {
        this.listenSongs = listenSongs;
    }


    public Boolean getMobileSign() {
        return mobileSign;
    }

    public void setMobileSign(Boolean mobileSign) {
        this.mobileSign = mobileSign;
    }

    public Boolean getPcSign() {
        return pcSign;
    }

    public void setPcSign(Boolean pcSign) {
        this.pcSign = pcSign;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public Boolean getPeopleCanSeeMyPlayRecord() {
        return peopleCanSeeMyPlayRecord;
    }

    public void setPeopleCanSeeMyPlayRecord(Boolean peopleCanSeeMyPlayRecord) {
        this.peopleCanSeeMyPlayRecord = peopleCanSeeMyPlayRecord;
    }


    public Boolean getAdValid() {
        return adValid;
    }

    public void setAdValid(Boolean adValid) {
        this.adValid = adValid;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateDays() {
        return createDays;
    }

    public void setCreateDays(Integer createDays) {
        this.createDays = createDays;
    }



    public static class ProfileDTO {
        private long followeds;
        private long follows;

        public long getFolloweds() {
            return followeds;
        }

        public void setFolloweds(Integer followeds) {
            this.followeds = followeds;
        }

        public long getFollows() {
            return follows;
        }

        public void setFollows(Integer follows) {
            this.follows = follows;
        }

        public static class ExpertsDTO {
        }
    }

}
