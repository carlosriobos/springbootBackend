package com.example.demo.controllers;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

import com.example.demo.models.Person;
import com.example.demo.services.RickNMortyService;
import com.example.demo.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Le indica a springboot que esta clase es el controlador de la aplicacion
@RestController
public class Ejercicio {

    @Autowired
    RickNMortyService rickNMortyService;

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

    //http:localhost:8080/palindromo/ana parametro
    @GetMapping("/palindromo/{name}")
    public String palindrome(@PathVariable String name) {
        boolean palindrome = Utils.isPalindrome(name);
        return palindrome ? "Si es palindromo" : "No es palindromo";
    }

    //http://localhost:8080/sumar?num1=5&num2=2 query/consulta
    @GetMapping("/sumar")
    public String add(@RequestParam String num1, @RequestParam String num2){
        int resultado = Integer.parseInt(num1) + Integer.parseInt(num2);
        Object params[] = {num1, num2, resultado};
        return MessageFormat.format("La suma de {0} y {1} es {2}", params);
    }

    //Arrays, ArrayList, Map/Diccionario
    @PostMapping("/saveProductOnDisk")
    public String saveProductOnDisk(@RequestParam Map<String, String> body) {
        //obtener los datos de un producto {articulo, precio}
        String articleValue = body.get("article");
        String priceValue = body.get("price");
        //valido que el articulo y precio no sean vacios
        if (articleValue.equals("") || priceValue.equals("")) {
            return "Error datos incorrectos";
        }
        //valido que el precio no sea negativo
        if (Integer.parseInt(priceValue)<0) {
            return "Error el precio es negativo";
        }
        //guardo en el disco duro esa informacion
        try {
            Utils.save("datos.txt", articleValue + "," + priceValue + "\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error al guardar en disco";
        }

        //devuelvo un mensaje al cliente "producto guardado correctamente"
        return "product safe succesfully";
    }
    
    @DeleteMapping("/removeFile")
    public String removeFile() {
        boolean result = Utils.remove("datos.txt");
        return result ? "borrado correcto" : "no se puede borrar";
    }

    @GetMapping("/ricknmorty/random")
    public String getRnMRandomPic() {
        Person c = rickNMortyService.getCharacterFromAPI();
        return "<img src='"+c.image+"'/>";
        //return MessageFormat.format("<img src='{0}'/>", c.image);
    }

    @GetMapping("/ricknmorty/list")
    public String getRickNMortyList() {
        String web = "<h1>Lista de personas<h1>";
        ArrayList<Person> persons = rickNMortyService.getCharactersFromAPI();
        for(Person person : persons) {
            web += "<img src='" + person.image+"'/>";
        }
        return web;
    }
}
