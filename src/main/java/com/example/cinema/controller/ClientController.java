package com.example.cinema.controller;

import com.example.cinema.model.Client;
import com.example.cinema.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @PostMapping("/new")
    public ResponseEntity<Client> createClient(
            @RequestBody Client client
    ) {
        return ResponseEntity.created(URI.create("/client" + client.getClientId()))
                .body(clientService.saveClient(client));
    }

    @PostMapping("/membership")
    public ResponseEntity<Client> addMembershipToClient(
            @RequestParam int clientId
    ) {
        return ResponseEntity.ok().body(clientService.addMembershipToClient(clientId));
    }

    @PutMapping("/membership")
    public ResponseEntity<Client> toggleMembership(
            @RequestParam int clientId
    ) {
        return ResponseEntity.ok().body(clientService.toggleMembership(clientId));
    }

}
