package com.hyvikk.retrofitappdemo.TransactionDatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "transaction_table")
public class TransactionModel {

    // below line is to auto increment
    // id for each Transaction.
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "customer_name")
    private String customer;
    @ColumnInfo(name = "receipt_no")
    private String receiptNo;
    @ColumnInfo(name = "order_id")
    private String orderId;
    @ColumnInfo(name = "payment_status")
    private String paymentStatus;
    @ColumnInfo(name = "timestamp")
    private String timestamp;


    public TransactionModel( String customer, String receiptNo, String orderId, String paymentStatus, String timestamp) {

        this.customer = customer;
        this.receiptNo = receiptNo;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
