package com.codelegends.ecommerce.entity;
public final class Enums {
    private Enums() {
    }
    public enum Gender {
        MALE,FEMALE
    }
    public enum AddressType {
        HOME,WORK,OTHER
    }
    public enum OrderStatus {
        PENDING,PAID,PROCESSING,SHIPPED,DELIVERED,CANCELLED
    }
    public enum PaymentMethod {
        CARD,CASH_ON_DELIVERY,BANK_TRANSFER
    }
    public enum PaymentStatus {
        PENDING,PAID,FAILED,REFUNDED
    }
    public enum ShipmentStatus {
        PREPARING,SHIPPED,IN_TRANSIT,DELIVERED
    }
}
