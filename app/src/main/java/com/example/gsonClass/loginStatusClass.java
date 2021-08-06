package com.example.gsonClass;

import java.util.List;

public class loginStatusClass{

    private String loginType;
    private int code;
    private AccountDTO account;
    private String token;
    private ProfileDTO profile;
    private List<BindingsDTO> bindings;
    private String cookie;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public List<BindingsDTO> getBindings() {
        return bindings;
    }

    public void setBindings(List<BindingsDTO> bindings) {
        this.bindings = bindings;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public static class AccountDTO {
        private Integer id;
        private String userName;
        private Integer type;
        private Integer status;
        private Integer whitelistAuthority;
        private Long createTime;
        private String salt;
        private Integer tokenVersion;
        private Integer ban;
        private Integer baoyueVersion;
        private Integer donateVersion;
        private Integer vipType;
        private Long viptypeVersion;
        private Boolean anonimousUser;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getWhitelistAuthority() {
            return whitelistAuthority;
        }

        public void setWhitelistAuthority(Integer whitelistAuthority) {
            this.whitelistAuthority = whitelistAuthority;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public Integer getTokenVersion() {
            return tokenVersion;
        }

        public void setTokenVersion(Integer tokenVersion) {
            this.tokenVersion = tokenVersion;
        }

        public Integer getBan() {
            return ban;
        }

        public void setBan(Integer ban) {
            this.ban = ban;
        }

        public Integer getBaoyueVersion() {
            return baoyueVersion;
        }

        public void setBaoyueVersion(Integer baoyueVersion) {
            this.baoyueVersion = baoyueVersion;
        }

        public Integer getDonateVersion() {
            return donateVersion;
        }

        public void setDonateVersion(Integer donateVersion) {
            this.donateVersion = donateVersion;
        }

        public Integer getVipType() {
            return vipType;
        }

        public void setVipType(Integer vipType) {
            this.vipType = vipType;
        }

        public Long getViptypeVersion() {
            return viptypeVersion;
        }

        public void setViptypeVersion(Long viptypeVersion) {
            this.viptypeVersion = viptypeVersion;
        }

        public Boolean getAnonimousUser() {
            return anonimousUser;
        }

        public void setAnonimousUser(Boolean anonimousUser) {
            this.anonimousUser = anonimousUser;
        }
    }

    public static class ProfileDTO {
        private Integer userType;
        private String description;
        private Long backgroundImgId;
        private String avatarUrl;
        private Boolean defaultAvatar;
        private Integer province;
        private Integer vipType;
        private Integer gender;
        private Integer accountStatus;
        private String nickname;
        private Long avatarImgId;
        private Long birthday;
        private Integer city;
        private Boolean mutual;
        private Object remarkName;
        private Object expertTags;
        private Integer authStatus;
        private Integer djStatus;
        private ExpertsDTO experts;
        private String detailDescription;
        private Boolean followed;
        private String backgroundUrl;
        private String signature;
        private Integer authority;
        private String avatarImgId_str;
        private Integer followeds;
        private Integer follows;
        private Integer eventCount;
        private Object avatarDetail;
        private Integer playlistCount;
        private Integer playlistBeSubscribedCount;

        public Integer getUserType() {
            return userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(Long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public Boolean getDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(Boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public Integer getProvince() {
            return province;
        }

        public void setProvince(Integer province) {
            this.province = province;
        }

        public Integer getVipType() {
            return vipType;
        }

        public void setVipType(Integer vipType) {
            this.vipType = vipType;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public Integer getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(Integer accountStatus) {
            this.accountStatus = accountStatus;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(Long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public Long getBirthday() {
            return birthday;
        }

        public void setBirthday(Long birthday) {
            this.birthday = birthday;
        }

        public Integer getCity() {
            return city;
        }

        public void setCity(Integer city) {
            this.city = city;
        }

        public Boolean getMutual() {
            return mutual;
        }

        public void setMutual(Boolean mutual) {
            this.mutual = mutual;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public Integer getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(Integer authStatus) {
            this.authStatus = authStatus;
        }

        public Integer getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(Integer djStatus) {
            this.djStatus = djStatus;
        }

        public ExpertsDTO getExperts() {
            return experts;
        }

        public void setExperts(ExpertsDTO experts) {
            this.experts = experts;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public Boolean getFollowed() {
            return followed;
        }

        public void setFollowed(Boolean followed) {
            this.followed = followed;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public Integer getAuthority() {
            return authority;
        }

        public void setAuthority(Integer authority) {
            this.authority = authority;
        }

        public String getAvatarImgId_str() {
            return avatarImgId_str;
        }

        public void setAvatarImgId_str(String avatarImgId_str) {
            this.avatarImgId_str = avatarImgId_str;
        }

        public Integer getFolloweds() {
            return followeds;
        }

        public void setFolloweds(Integer followeds) {
            this.followeds = followeds;
        }

        public Integer getFollows() {
            return follows;
        }

        public void setFollows(Integer follows) {
            this.follows = follows;
        }

        public Integer getEventCount() {
            return eventCount;
        }

        public void setEventCount(Integer eventCount) {
            this.eventCount = eventCount;
        }

        public Object getAvatarDetail() {
            return avatarDetail;
        }

        public void setAvatarDetail(Object avatarDetail) {
            this.avatarDetail = avatarDetail;
        }

        public Integer getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(Integer playlistCount) {
            this.playlistCount = playlistCount;
        }

        public Integer getPlaylistBeSubscribedCount() {
            return playlistBeSubscribedCount;
        }

        public void setPlaylistBeSubscribedCount(Integer playlistBeSubscribedCount) {
            this.playlistBeSubscribedCount = playlistBeSubscribedCount;
        }

        public static class ExpertsDTO {
        }
    }

    public static class BindingsDTO {
        private Long bindingTime;
        private String tokenJsonStr;
        private Integer expiresIn;
        private Boolean expired;
        private Integer refreshTime;
        private Long id;
        private Integer type;

        public Long getBindingTime() {
            return bindingTime;
        }

        public void setBindingTime(Long bindingTime) {
            this.bindingTime = bindingTime;
        }

        public String getTokenJsonStr() {
            return tokenJsonStr;
        }

        public void setTokenJsonStr(String tokenJsonStr) {
            this.tokenJsonStr = tokenJsonStr;
        }

        public Integer getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(Integer expiresIn) {
            this.expiresIn = expiresIn;
        }

        public Boolean getExpired() {
            return expired;
        }

        public void setExpired(Boolean expired) {
            this.expired = expired;
        }

        public Integer getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(Integer refreshTime) {
            this.refreshTime = refreshTime;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }
}
