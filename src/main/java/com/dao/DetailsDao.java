package com.dao;

import com.bean.Details;

import java.util.List;

public interface DetailsDao {
    public List<Details> getDetailsByPurchaseId(String purchaseId);
}