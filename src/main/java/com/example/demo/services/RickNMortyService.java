package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Person;
import com.example.demo.models.Persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RickNMortyService {
    @Autowired
    RestTemplate restTemplate;

    public Person getCharacterFromAPI() {
        //TODO mejorar el random
        long rndNum = Math.round(Math.random()*826)+1;
        String url =  "https://rickandmortyapi.com/api/character/" + rndNum;
        Person person = restTemplate.getForObject(url, Person.class);
        return person;
    }

    public ArrayList<Person> getCharactersFromAPI() {
        String url = "https://rickandmortyapi.com/api/character";
        Persons persons = restTemplate.getForObject(url, Persons.class);
        return persons.results;
    }
/*  //Burrada porque se sobrecarga la ram del procesador del cliente

    public ArrayList<Person> getAllCharactersFromAPI() {
        ArrayList<Person> allPersons = new ArrayList<Person>();
        for(int i=1; i<=42; i++) {
            String url = "https://rickandmortyapi.com/api/character?page="+i;
            Persons persons = restTemplate.getForObject(url, Persons.class);
            allPersons.addAll(persons.results);
        }
        return allPersons;
    }
*/
}

