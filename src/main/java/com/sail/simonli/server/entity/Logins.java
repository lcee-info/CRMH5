package com.sail.simonli.server.entity;

import java.io.Serializable;
import java.util.Date;

public class Logins implements Serializable {
    private Integer loginId;

    private String username;

    private String series;

    private String token;

    private Date validtime;

    private static final long serialVersionUID = 1L;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series == null ? null : series.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getValidtime() {
        return validtime;
    }

    public void setValidtime(Date validtime) {
        this.validtime = validtime;
    }
}