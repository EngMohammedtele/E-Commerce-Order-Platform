package com.codelegends.ecommerce.entity;
// Defines the Enums type used by the ecommerce app
public final class Enums {
    private Enums() {
    }
    // Lists the allowed Gender values for the application
    public enum Gender {
        MALE,FEMALE
    }
    // Lists the allowed AddressType values for the application
    public enum AddressType {
        HOME,WORK,OTHER
    }
    // Lists the allowed OrderStatus values for the application
    public enum OrderStatus {
        PENDING,PAID,PROCESSING,SHIPPED,DELIVERED,CANCELLED
    }
    // Lists the allowed PaymentMethod values for the application
    public enum PaymentMethod {
        CARD,CASH_ON_DELIVERY,BANK_TRANSFER
    }
    // Lists the allowed PaymentStatus values for the application
    public enum PaymentStatus {
        PENDING,PAID,FAILED,REFUNDED
    }
    // Lists the allowed ShipmentStatus values for the application
    public enum ShipmentStatus {
        PREPARING,SHIPPED,IN_TRANSIT,DELIVERED
    }
}
