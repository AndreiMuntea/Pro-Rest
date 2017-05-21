package com.example.Repository.DBRepository;

import com.example.Domain.Trial;
import com.example.Repository.Exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by andrei on 2017-05-07.
 */
@Repository
public class TrialDBRepository {
    private DAO dao;

    @Autowired
    public TrialDBRepository(DAO dao) {
        this.dao = dao;
    }


    public void addItem(Trial item) throws RepositoryException {
        dao.add(Trial.class, item);
    }


    public void deleteItem(String s) throws RepositoryException {
        dao.delete(Trial.class, s);
    }


    public Boolean existsItem(String s) throws RepositoryException {
        return dao.exists(Trial.class, s);
    }


    public Trial getItem(String s) throws RepositoryException {
        return dao.get(Trial.class, s);
    }


    public void updateItem(Trial updatedItem) throws RepositoryException {
        dao.update(Trial.class, updatedItem);
    }


    public List<Trial> getAll() throws RepositoryException {
        return dao.getAll(Trial.class);
    }
}
