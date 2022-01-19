package com.example.cinema.service;

import com.example.cinema.model.Membership;
import com.example.cinema.repository.MembershipRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MembershipService membershipService;

    @Test
    @DisplayName("Get all memberships successfully")
    void getAll() {
        List<Membership> memberships = new ArrayList<>();
        Membership membership = new Membership();
        memberships.add(membership);
        when(membershipRepository.findAll()).thenReturn(memberships);

        List<Membership> result = membershipService.getAllMemberships();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(memberships, result);
    }
}
