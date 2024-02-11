package com.visz.tv;

public class Channel {
    public String num;
    public String tvgId;
    public String tvgName;
    public String tvgLogo;
    public String groupTitle;
    public String name;
    public String url;

    @Override
    public String toString() {
        return "Channel{" +
                "num='" + num + '\'' +
                "tvgId='" + tvgId + '\'' +
                ", tvgName='" + tvgName + '\'' +
                ", tvgLogo='" + tvgLogo + '\'' +
                ", groupTitle='" + groupTitle + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
