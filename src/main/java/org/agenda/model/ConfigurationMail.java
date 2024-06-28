package org.agenda.model;

public class ConfigurationMail {
    private String host;
    private String port;
    private String username;
    private String password;
    private String supportMail;

    public ConfigurationMail(String host, String port, String username, String password, String supportMail) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.supportMail = supportMail;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public String getSupportMail() {
        return supportMail;
    }

    public void setSupportMail(String supportMail) {
        this.supportMail = supportMail;
    }
}
