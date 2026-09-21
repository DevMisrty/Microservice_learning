package com.learning.accounts.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Message {

    public static class AccountConstants {
        public static final String ACCOUNT_CREATED = "Account created successfully";
        public static final String ACCOUNT_UPDATED = "Account updated successfully";
        public static final String ACCOUNT_DELETED = "Account deleted successfully";
        public static final String ACCOUNT_FETCHED = "Account fetched successfully";
        public static final String ACCOUNT_NOT_FOUND = "Account not found";
        public static final String ACCOUNT_ALREADY_EXISTS = "Account already exists";
    }

    public static class CustomerConstants {
        public static final String CUSTOMER_CREATED = "Customer created successfully";
        public static final String CUSTOMER_UPDATED = "Customer updated successfully";
        public static final String CUSTOMER_DELETED = "Customer deleted successfully";
        public static final String CUSTOMER_FETCHED = "Customer fetched successfully";
        public static final String CUSTOMER_NOT_FOUND = "Customer not found";
        public static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";
    }

    public static class CardsConstants {
        public static final String CARDS_FETCHED = "Cards fetched successfully";
        public static final String CARDS_NOT_FOUND = "Cards not found";
    }
    
}
