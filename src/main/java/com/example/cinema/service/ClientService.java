package com.example.cinema.service;

import com.example.cinema.enums.MembershipStatus;
import com.example.cinema.exceptions.MembershipAlreadyCreatedException;
import com.example.cinema.exceptions.NotFoundException;
import com.example.cinema.model.Client;
import com.example.cinema.model.Membership;
import com.example.cinema.repository.ClientRepository;
import com.example.cinema.repository.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final MembershipRepository membershipRepository;

    public ClientService(ClientRepository clientRepository, MembershipRepository membershipRepository){
        this.clientRepository = clientRepository;
        this.membershipRepository = membershipRepository;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Client addMembershipToClient(int clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client"));

        if (client.getMembership() != null){
            throw new MembershipAlreadyCreatedException();
        }

        Membership newMembership = new Membership();
        membershipRepository.save(newMembership);
        client.setMembership(newMembership);
        return clientRepository.save(client);
    }

    public Client toggleMembership(int clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client"));

        Membership membership = client.getMembership();
        if (membership == null) {
            throw new NotFoundException("Membership");
        }

        membership.setMembershipStatus(
                membership.getMembershipStatus().equals(MembershipStatus.ACTIVE) ? MembershipStatus.INACTIVE : MembershipStatus.ACTIVE
        );
        membershipRepository.save(membership);
        return clientRepository.save(client);
    }
}
