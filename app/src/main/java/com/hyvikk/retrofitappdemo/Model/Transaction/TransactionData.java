package com.hyvikk.retrofitappdemo.Model.Transaction;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionData {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("customer")
        @Expose
        private String customer;
        @SerializedName("receipt_no")
        @Expose
        private String receiptNo;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("payment_id")
        @Expose
        private Object paymentId;
        @SerializedName("payment_status")
        @Expose
        private String paymentStatus;
        @SerializedName("amount")
        @Expose
        private Double amount;
        @SerializedName("booking_id")
        @Expose
        private Integer bookingId;
        @SerializedName("method")
        @Expose
        private String method;
        @SerializedName("sign")
        @Expose
        private Object sign;
        @SerializedName("reason")
        @Expose
        private Object reason;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("time")
        @Expose
        private String time;

        public TransactionData() {
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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
}
