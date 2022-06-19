package com.example.PhaseTwo.model.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PayingWithCredit {
    @NotNull
    private Long customerId;
    @NotNull
    private Long price;
    @NotNull
    private Long orderId;
    @NotNull
    private Long expertId;
    @NotNull
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
