package com.vo.param;

@lombok.Data
public class ModifyUserInformationParam {
    /**
     * 账户创建时间
     */
    private String startTime;
    /**
     * 所属中转站名称
     */
    private String stationName;
    /**
     * 用户电子邮箱
     */
    private String userEmail;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 联系电话
     */
    private String userPhone;
}