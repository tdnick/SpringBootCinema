package com.example.cinema.model;

import com.example.cinema.enums.MembershipStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int membershipId;

    @NotNull
    private MembershipStatus membershipStatus;

    public Membership() {
        this.membershipStatus = MembershipStatus.ACTIVE;
    }

    public Membership(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }
}
