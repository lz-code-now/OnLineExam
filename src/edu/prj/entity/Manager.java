package edu.prj.entity;

public class Manager {
    private Long managerID;                     //管理员ID
    private String loginName;                   // 账号
    private String loginPwd;                    //密码
    private String nickName;                    //昵称
    private Long isDisabled = Long.valueOf(0);  //是否禁用

    public Long getManagerID() {
        return managerID;
    }

    public void setManagerID(Long managerID) {
        this.managerID = managerID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Long isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerID=" + managerID +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isDisabled=" + isDisabled +
                '}';
    }
}
