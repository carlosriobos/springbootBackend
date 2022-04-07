package com.example.demo.controllers;

import java.util.ArrayList;

import com.example.demo.models.Person;
import com.example.demo.services.RickNMortyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    RickNMortyService rickNMortyService;

    @RequestMapping("/ricknmorty/listview")
    public String getRickNMortyListView(Model model) {
        ArrayList<Person> persons = rickNMortyService.getCharactersFromAPI();
        model.addAttribute("persons", persons);
        return "rickandmorty";
    }
}
