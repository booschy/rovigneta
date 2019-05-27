package com.proiect.rovigneta;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public void delete(long id){
        repo.deleteById(id);
    }

    public void save(Client client){
        repo.save(client);
    }

    public Client getById(long id){
        return repo.findById(id).get();
    }

    public List<Client> listAll(){
        return repo.findAll();
    }


}
