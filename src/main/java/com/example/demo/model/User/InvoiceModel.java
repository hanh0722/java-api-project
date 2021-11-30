package com.example.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class InvoiceModel {
    private String invoiceId;
    private Long date_created = System.currentTimeMillis();
    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    public Long getDate_created() {
        return date_created;
    }
    public void setDate_created(Long date_created) {
        this.date_created = date_created;
    }

}
