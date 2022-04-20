package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Joke;

@Repository
public interface JokeRepository extends CrudRepository<Joke, Long> {
    
}
