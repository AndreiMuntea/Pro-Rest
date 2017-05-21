package com.example.RestController;

import com.example.Domain.Trial;
import com.example.Repository.DBRepository.TrialDBRepository;
import com.example.Repository.Exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by andrei on 2017-05-21.
 */
@RestController
@RequestMapping("/trials")
public class TrialController {

    private final TrialDBRepository repository;

    @Autowired
    public TrialController(TrialDBRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Trial> getAll() throws RepositoryException {
        return repository.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            Trial trial = repository.getItem(id);
            return new ResponseEntity<Trial>(trial, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Trial not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  ResponseEntity<?> create(@RequestBody Trial trial){
        try{
            repository.addItem(trial);
            return new ResponseEntity<String>("Trial created!",HttpStatus.OK);
        } catch (RepositoryException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{trialName}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String trialName){
        try {
            repository.deleteItem(trialName);
            return new ResponseEntity<String>("Trial deleted!",HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update(@RequestBody Trial trial){
        try{
            repository.updateItem(trial);
            return new ResponseEntity<String>("Trial updated!",HttpStatus.OK);
        } catch (RepositoryException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
