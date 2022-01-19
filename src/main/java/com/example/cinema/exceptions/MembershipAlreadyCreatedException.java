package com.example.cinema.exceptions;

public class MembershipAlreadyCreatedException extends RuntimeException {
    public MembershipAlreadyCreatedException(){
        super("Membership already exists for this client!");
    }
}
