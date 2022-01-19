package com.example.cinema.service;

import com.example.cinema.enums.MembershipStatus;
import com.example.cinema.exceptions.MembershipAlreadyCreatedException;
import com.example.cinema.exceptions.NotFoundException;
import com.example.cinema.model.Client;
import com.example.cinema.model.Membership;
import com.example.cinema.repository.ClientRepository;
import com.example.cinema.repository.MembershipRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    @DisplayName("Client created successfully")
    void create() {
        //arrange
        Client client = new Client("Ion", "ion@test.com");
        when(clientRepository.save(client)).thenReturn(client);

        //act
        Client result = clientService.saveClient(client);

        //assert
        assertNotNull(result);
        assertEquals(client.getClientId(), result.getClientId());
        assertEquals(client.getName(), result.getName());
        assertEquals(client.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("Get all clients successfully")
    void getAll() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client("Ion", "ion@test.com");
        clients.add(client);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clients, result);
    }

    @Test
    @DisplayName("Add membership to client successfully")
    void addMembershipSuccess() {
        Client client = new Client("Ion", "ion@test.com");
        Membership membership = new Membership();

        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.addMembershipToClient(client.getClientId());

        assertNotNull(result);
        assertEquals(client.getMembership().getMembershipId(), result.getMembership().getMembershipId());
        assertEquals(client.getMembership().getMembershipStatus(), result.getMembership().getMembershipStatus());
    }

    @Test
    @DisplayName("Add membership failed - client does not exist")
    void addMembershipNoClient() {
        Client client = new Client(1, "Ion", "ion@test.com");
        when(clientRepository.findById(1)).thenThrow(new NotFoundException("Client"));

        try {
            Client result = clientService.addMembershipToClient(client.getClientId());
        } catch (NotFoundException e) {
            assertEquals("Client could not be found!", e.getMessage());
            verify(membershipRepository, times(0)).save(any());
            verify(clientRepository, times(0)).save(any());
        }
    }

    @Test
    @DisplayName("Add membership failed - client already has membership")
    void addMembershipAlreadyExists() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Membership membership = new Membership();
        client.setMembership(membership);

        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));

        MembershipAlreadyCreatedException result = assertThrows(
                MembershipAlreadyCreatedException.class,
                () -> clientService.addMembershipToClient(client.getClientId()));

        assertEquals("Membership already exists for this client!", result.getMessage());
    }

    @Test
    @DisplayName("Toggle membership successfully")
    void toggleMembershipSuccess() {
        Client client = new Client(1, "Ion", "ion@test.com");
        Membership membership = new Membership();
        client.setMembership(membership);

        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.toggleMembership(client.getClientId());

        assertNotNull(result);
        assertEquals(client.getMembership().getMembershipId(), result.getMembership().getMembershipId());
        assertEquals(MembershipStatus.INACTIVE, result.getMembership().getMembershipStatus());
    }

    @Test
    @DisplayName("Toggle membership failed - client does not have membership")
    void toggleMembershipFail() {
        Client client = new Client(1, "Ion", "ion@test.com");

        when(clientRepository.findById(client.getClientId())).thenReturn(Optional.of(client));

        NotFoundException result = assertThrows(
                NotFoundException.class,
                () -> clientService.toggleMembership(client.getClientId()));

        assertEquals("Membership could not be found!", result.getMessage());
    }
}
