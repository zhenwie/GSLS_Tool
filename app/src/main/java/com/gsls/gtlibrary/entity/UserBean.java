package com.gsls.gtlibrary.entity;


import com.gsls.gtlibrary.util.GT;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;

@GT.Hibernate.GT_Bean//标识为实体类
public class UserBean {

    @GT.Hibernate.GT_Key//标识ID属性
    private int userID;//用户ID
    private String userName;//姓名
    private short userAge;//年龄
    private boolean userSex;//性别
    private float userEndOfTermScore;//期末分数
    private Time userEnrolTime;//登记时间
    private Date userBirthday;//生日
    private long userStudentNumber;//学号
    private int userTuition;//学费
    private byte[] userPhoto;//相片

    public UserBean() {
        super();
    }

    public UserBean(String userName, short userAge, boolean userSex, float userEndOfTermScore, Time userEnrolTime, Date userBirthday, long userStudentNumber, int userTuition, byte[] userPhoto) {
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
        this.userEndOfTermScore = userEndOfTermScore;
        this.userEnrolTime = userEnrolTime;
        this.userBirthday = userBirthday;
        this.userStudentNumber = userStudentNumber;
        this.userTuition = userTuition;
        this.userPhoto = userPhoto;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public short getUserAge() {
        return userAge;
    }

    public void setUserAge(short userAge) {
        this.userAge = userAge;
    }

    public boolean isUserSex() {
        return userSex;
    }

    public void setUserSex(boolean userSex) {
        this.userSex = userSex;
    }

    public float getUserEndOfTermScore() {
        return userEndOfTermScore;
    }

    public void setUserEndOfTermScore(float userEndOfTermScore) {
        this.userEndOfTermScore = userEndOfTermScore;
    }

    public Time getUserEnrolTime() {
        return userEnrolTime;
    }

    public void setUserEnrolTime(Time userEnrolTime) {
        this.userEnrolTime = userEnrolTime;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public long getUserStudentNumber() {
        return userStudentNumber;
    }

    public void setUserStudentNumber(long userStudentNumber) {
        this.userStudentNumber = userStudentNumber;
    }

    public int getUserTuition() {
        return userTuition;
    }

    public void setUserTuition(int userTuition) {
        this.userTuition = userTuition;
    }

    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSex=" + userSex +
                ", userEndOfTermScore=" + userEndOfTermScore +
                ", userEnrolTime=" + userEnrolTime +
                ", userBirthday=" + userBirthday +
                ", userStudentNumber=" + userStudentNumber +
                ", userTuition=" + userTuition +
                ", userPhoto=" + Arrays.toString(userPhoto) +
                '}';
    }
}
