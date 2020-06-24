package com.app.transfermoney.model;

public class TransferMoneyRequest {
    private String toAccount;
    private String fromAccount;
    private Long money;
    private String rate;

    public TransferMoneyRequest(String toAccount, String fromAccount, Long money, String rate) {
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.money = money;
        this.rate = rate;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
