package com.xiya.demo.vo;

/**
 * @author N3verL4nd
 * @date 2020/3/25
 */
public class UserLoginModel {

    private long userId;
    private String token;
    private String mobile;
    private String username;
    private String avatarUrl;

    public UserLoginModel(long userId, String token, String mobile, String username, String avatarUrl) {
        this.userId = userId;
        this.token = token;
        this.mobile = mobile;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public UserLoginModel() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
