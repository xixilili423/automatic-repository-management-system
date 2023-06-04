package com.vo.param;

public class balanceAccountsPaymentparam {
    /**
     * 结款数目
     */
    private String balAccounts;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 备注
     */
    private String notes;

    public String getbalAccounts() { return balAccounts; }
    public void setbalAccounts(String value) { this.balAccounts = value; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String value) { this.customerId = value; }

    public String getNotes() { return notes; }
    public void setNotes(String value) { this.notes = value; }
}
