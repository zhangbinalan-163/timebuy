package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * 用户信息实体
 * Created by zhangbinalan on 15/8/15.
 */
public class User {

    public static final int SEX_MALE = 0;//性别男
    public static final int SEX_FEMALE = 1;//性别女

    public static final int NEARBY_VISIBLE = 1;
    public static final int NEARBY_INVISIBLE = 0;

    public static final int STATUS_NORMAL = 1;//正常状态
    public static final int STATUS_FROBIDDEN = 2;//禁用状态
    public static final int STATUS_DELETED = 3;//删除状态

    public static final int SOURCE_APP = 1;//APP注册 即手机号注册
    public static final int SOURCE_THIRD_QQ = 2;//第三方系统绑定注册QQ
    public static final int SOURCE_THIRD_SINA = 3;//第三方系统绑定注册新浪
    public static final int SOURCE_THIRD_WX = 4;//第三方系统绑定注册微信
    public static final int SOURCE_THIRD_ZFB = 5;//第三方系统绑定注册支付宝

    private Long userId;
    private String phone;//手机号
    private String userName;//第三方账号
    private String nickName;//用户昵称
    private String headIcon;//头像URL
    private Integer sex =SEX_MALE;//用户性别
    private String profession;//职业
    private String address;//地址
    private String password;//密码
    private Integer salt;//密码盐
    private Date createTime;//创建时间
    private Date updateTime;//最近一次修改时间
    private Date birthDay;//出生日期
    private String signature;//个性签名
    private Integer status=STATUS_NORMAL;//用户的状态
    private Integer nearby = NEARBY_VISIBLE;
    private Integer source=SOURCE_APP;//账号的注册来源
    private float money;//钱包

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
