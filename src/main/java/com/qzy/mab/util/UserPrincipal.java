package com.qzy.mab.util;

import java.io.Serializable;

public class UserPrincipal implements Serializable {

    private static final long serialVersionUID = -8898290283199289308L;
    private Serializable principalId;
    private String username;
    private String nickname;
    private String sessionKey;
    private int daysRemaining;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Serializable getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Serializable principalId) {
        this.principalId = principalId;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }


    @Override
    public String toString() {
        return "PlatformPrincipal{" +
                "principalId=" + principalId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", daysRemaining=" + daysRemaining +
                '}';
    }
}
