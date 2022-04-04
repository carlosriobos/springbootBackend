package com.example.demo.controllers;

import com.example.demo.utils.Utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Le indica a springboot que esta clase es el controlador de la aplicacion
@RestController
public class Ejercicio {
    //cuando el cliente entre en http:localhost:8080/ ejecutamos este metodo para darle la bienvenida
    @GetMapping("/")
    public String greet() {
        return "Bienvenido al servidor backend";
    }

    //http:localhost:8080/aleatorio
    @GetMapping("/aleatorio")
    public String random() {
        int rnd = (int)(Math.random()*100);
        return rnd + "";
    }

    //http:localhost:8080/palindromo/ana
    @GetMapping("/palindromo/{name}")
    public String palindrome(@PathVariable String name) {
        boolean palindrome = Utils.isPalindrome(name);
        return palindrome ? "Si es palindromo" : "No es palindromo";
    }
}
