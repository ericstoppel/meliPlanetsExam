package com.meli.Controllers;

import com.google.gson.JsonObject;
import com.meli.Model.MySQLDatabase;
import com.meli.Model.Weathers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clima")
public class WeatherController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> index(@RequestParam(value = "dia") int day){

        if(day<0){
            return ResponseEntity.badRequest().body("Revise el dia enviado, debe ser positivo.");
        }

        int dayCode = MySQLDatabase.getWeather(day);

        JsonObject response = new JsonObject();
        response.addProperty("dia", day);
        response.addProperty("clima", Weathers.stringify(dayCode));
        return ResponseEntity.ok().body(response.toString());
    }
}
