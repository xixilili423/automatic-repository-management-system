package com.vo.param;

public class incAccountsPaymentparam {
    /**
     * 客户id
     */
    private String customId;
    /**
     * 增加的应付款项
     */
    private String incAccounts;
    /**
     * 备注
     */
    private String notes;

    public String getCustomId() { return customId; }
    public void setCustomId(String value) { this.customId = value; }

    public String getIncAccounts() { return incAccounts; }
    public void setIncAccounts(String value) { this.incAccounts = value; }

    public String getNotes() { return notes; }
    public void setNotes(String value) { this.notes = value; }
}
