package com.example.fabricio.myapplication.model.persistence;

import com.example.fabricio.myapplication.model.entities.User;

import java.util.List;

/**
 * Created by Fabricio on 30/07/2015.
 */
public interface UserRepository {

    public abstract List<User> search(User user);

    public abstract void save(User user);

}
