package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * 用户信息实体
 * Created by zhangbinalan on 15/8/15.
 */
public class User {
    public static int SEX_MALE = 0;
    public static int SEX_FEMALE = 1;
    public static int NEARBY_VISIBLE = 1;
    public static int NEARBY_INVISIBLE = 0;

    private Long userId;
    private String userName;
    private String nickName;
    private String headIcon;
    private Integer sex =SEX_MALE;
    private String profession;
    private String address;
    private String phone;
    private String password;
    private Integer salt;
    private Date createTime;
    private Date updateTime;
    private Date birthDay;
    private String signature;
    private Integer nearby = NEARBY_VISIBLE;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getNearby() {
        return nearby;
    }

    public void setNearby(Integer nearby) {
        this.nearby = nearby;
    }
}
