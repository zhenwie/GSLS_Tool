package com.gsls.gtlibrary.entity;


import com.gsls.gtlibrary.util.GT;

@GT.Hibernate.GT_Entity
public class LoginBean {

    @GT.Hibernate.GT_Key(autoincrement = false)
    private int loginID;//登录ID
    private String userName;//用户名称
    private String passWord;//用户密码
    private String loginRole;//角色

    public LoginBean() {
        super();
    }

    public LoginBean(int loginID, String userName, String passWord, String loginRole) {
        this.loginID = loginID;
        this.userName = userName;
        this.passWord = passWord;
        this.loginRole = loginRole;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLoginRole() {
        return loginRole;
    }

    public void setLoginRole(String loginRole) {
        this.loginRole = loginRole;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "loginID=" + loginID +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", loginRole='" + loginRole + '\'' +
                '}';
    }
}
