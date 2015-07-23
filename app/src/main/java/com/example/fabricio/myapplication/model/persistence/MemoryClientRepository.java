package com.example.fabricio.myapplication.model.persistence;

import com.example.fabricio.myapplication.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabricio on 21/07/2015.
 */
public class MemoryClientRepository implements ClientRepository {

    private static  MemoryClientRepository singletonSnstance;
    private List<Client> clients;

    private MemoryClientRepository(){
        super();
        clients = new ArrayList<>();
    }

    public static ClientRepository getInstance(){
        if (MemoryClientRepository.singletonSnstance == null) {
            MemoryClientRepository.singletonSnstance = new MemoryClientRepository();
        }

        return  MemoryClientRepository.singletonSnstance;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }
}
