package com.vo.param;

public class checkStaffInformationparam {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 所属中转站名称
     */
    private String transferStation;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 仓库id
     */
    private String warehouseId;

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getTransferStation() { return transferStation; }
    public void setTransferStation(String value) { this.transferStation = value; }

    public String getUserName() { return userName; }
    public void setUserName(String value) { this.userName = value; }

    public String getWarehouseId() { return warehouseId; }
    public void setWarehouseId(String value) { this.warehouseId = value; }
}
