package com.demo.dto;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class ConnectionConfigDTO {

    private String url;
    private String username;
    private String password;
    private String driver;

    private Long n;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }
}
