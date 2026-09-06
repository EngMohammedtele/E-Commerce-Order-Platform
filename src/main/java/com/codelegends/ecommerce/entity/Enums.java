package com.codelegends.ecommerce.entity;
// Defines the Enums type used by the ecommerce app
// Represents the Enums database object in Java
public final class Enums {
    // Block object creation for this utility holder
    private Enums() {
    }
    // Lists the allowed Gender values for the application
    // Groups the fixed Gender choices used by entities
    public enum Gender {
        // List the allowed values for this enum
        MALE,FEMALE
    }
    // Lists the allowed AddressType values for the application
    // Groups the fixed AddressType choices used by entities
    public enum AddressType {
        // List the allowed values for this enum
        HOME,WORK,OTHER
    }
    // Lists the allowed OrderStatus values for the application
    // Groups the fixed OrderStatus choices used by entities
    public enum OrderStatus {
        // List the allowed values for this enum
        PENDING,PAID,PROCESSING,SHIPPED,DELIVERED,CANCELLED
    }
    // Lists the allowed PaymentMethod values for the application
    // Groups the fixed PaymentMethod choices used by entities
    public enum PaymentMethod {
        // List the allowed values for this enum
        CARD,CASH_ON_DELIVERY,BANK_TRANSFER
    }
    // Lists the allowed PaymentStatus values for the application
    // Groups the fixed PaymentStatus choices used by entities
    public enum PaymentStatus {
        // List the allowed values for this enum
        PENDING,PAID,FAILED,REFUNDED
    }
    // Lists the allowed ShipmentStatus values for the application
    public enum ShipmentStatus {
        PREPARING,SHIPPED,IN_TRANSIT,DELIVERED
    }
}
