package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Joke;
import com.example.demo.repositories.JokeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeService {
    @Autowired
    JokeRepository jokeRepository;
    public void saveJoke(Joke joke) {
        //INSERT INTO joke (text) VAULES ('XXXXXXXX')
        //save()
        jokeRepository.save(joke);
    }

    public ArrayList<Joke> getAllJokes() {
        //SELECT * FROM joke
        //findAll()
        ArrayList<Joke> jokes = (ArrayList<Joke>)jokeRepository.findAll();
        return jokes;
    }
}
