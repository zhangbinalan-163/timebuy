package com.alan.app.timebuy.entity;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.util.StringUtils;

/**
 * 客户端的版本信息
 *
 * Created by zhangbinalan on 15/8/16.
 */
public class ClientVersion  implements Comparable<ClientVersion> {
    public static ClientVersion MAX = new ClientVersion(999, 999, 999);
    public static ClientVersion MIN = new ClientVersion(0, 0, 0);
    private int major;
    private int minor;
    private int revision;

    public ClientVersion(int major, int minor, int revision)
    {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
    }

    public ClientVersion() {
    }

    public int getMajor() {
        return this.major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return this.minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getRevision() {
        return this.revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public static ClientVersion getInstance(String version) throws TimeBuyException {
        if (StringUtils.isEmpty(version)) {
            throw new TimeBuyException("版本号格式错误,version=" + version);
        }
        String[] array = version.split("\\.");
        if ((array.length < 2) || (array.length > 3))
            throw new TimeBuyException("版本号格式错误,version=" + version);
        try
        {
            int mainVersion = Integer.parseInt(array[0]);
            int minorVersion = Integer.parseInt(array[1]);
            int revisionVersion = 0;
            if (array.length == 3) {
                revisionVersion = Integer.parseInt(array[2]);
            }
            ClientVersion instance = new ClientVersion();
            instance.setMajor(mainVersion);
            instance.setMinor(minorVersion);
            instance.setRevision(revisionVersion);
            return instance; } catch (Exception e) {
        }
        throw new TimeBuyException("版本号格式错误,version=" + version);
    }

    public int compareTo(ClientVersion target) {
        if (target == null) {
            return 1;
        }
        int targetMajor = target.getMajor();
        int targetMinor = target.getMinor();
        int targetRevision = target.getRevision();
        if (this.major > targetMajor)
            return 1;
        if (this.major < targetMajor) {
            return -1;
        }

        if (this.minor > targetMinor)
            return 1;
        if (this.minor < targetMinor) {
            return -1;
        }

        if (this.revision > targetRevision)
            return 1;
        if (this.revision < targetRevision) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return this.major + "." + this.minor + "." + this.revision;
    }
}
