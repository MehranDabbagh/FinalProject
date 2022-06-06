package com.example.PhaseTwo.model.entity.dto;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Orders;

import java.util.List;

public class ActivitiesHistory {
    private List<Orders> ordersList;
    private List<Bid> bidList;

    public ActivitiesHistory(List<Orders> ordersList, List<Bid> bidList) {
        this.ordersList = ordersList;
        this.bidList = bidList;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }
}
