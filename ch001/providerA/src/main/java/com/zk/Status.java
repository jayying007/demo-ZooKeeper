package com.zk;

/**
 * @author jayying
 * @since 2021/1/15
 */
public class Status {
    private String IP;
    private String port;
    private String serverName;
    private String path;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Status{" +
                "IP='" + IP + '\'' +
                ", port='" + port + '\'' +
                ", serverName='" + serverName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
