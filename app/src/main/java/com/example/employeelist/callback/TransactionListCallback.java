package com.example.employeelist.callback;


import com.example.employeelist.model.TransactionModel;

import java.util.List;

public interface TransactionListCallback {

    void onSuccess(List<TransactionModel> list);

    void onFailure(String status);

}
