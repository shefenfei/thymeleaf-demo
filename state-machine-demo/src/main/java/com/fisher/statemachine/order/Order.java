package com.fisher.statemachine.order;


import java.util.Date;

public class Order {

    private String orderNo;
    private OrderState states;
    private Date paytime;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public OrderState getStates() {
        return states;
    }

    public void setStates(OrderState states) {
        this.states = states;
    }
}
