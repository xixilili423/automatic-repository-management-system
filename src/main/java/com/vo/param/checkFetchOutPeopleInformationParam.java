package com.vo.param;

public class checkFetchOutPeopleInformationParam {
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 姓名
     */
    private String name;
    /**
     * 出库人id
     */
    private String outBoundPresonId;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 用户名
     */
    private String userName;

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getOutBoundPresonId() { return outBoundPresonId; }
    public void setOutBoundPresonId(String value) { this.outBoundPresonId = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getUserName() { return userName; }
    public void setUserName(String value) { this.userName = value; }
}