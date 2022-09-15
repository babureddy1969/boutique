package com.example.boutique.callback;


import com.example.boutique.model.TransactionModel;

import java.util.List;

public interface TransactionListCallback {

    void onSuccess(List<TransactionModel> list);

    void onFailure(String status);

}
