package com.example.PhaseTwo.model.entity.dto;

import javax.validation.constraints.NotEmpty;

public class PayingWithCredit {
    @NotEmpty
    private Long customerId;
    @NotEmpty
    private Long price;
    @NotEmpty
    private Long orderId;
    @NotEmpty
    private Long expertId;
    @NotEmpty
    private Long bidId;

    public PayingWithCredit(Long customerId, Long price, Long orderId, Long expertId, Long bidId) {
        this.customerId = customerId;
        this.price = price;
        this.orderId = orderId;
        this.expertId = expertId;
        this.bidId = bidId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }
}
