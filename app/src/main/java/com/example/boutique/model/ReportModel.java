package com.example.boutique.model;

public class ReportModel {
    private String month;
    private long total;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReportModel{" +
                "month='" + month + '\'' +
                ", total=" + total +
                '}';
    }
}
