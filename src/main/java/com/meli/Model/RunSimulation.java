package com.meli.Model;

public class RunSimulation {
    public static void main(String[] args){
        MySQLDatabase.clearDB();

        Planet ferengi = new Planet(0, 500, 90, true, 500, 1);
        Planet betasoide = new Planet(0, 2000, 90, true, 2000, 3);
        Planet vulcano = new Planet(0,1000, 90, false, 1000, 5);
        Galaxy galaxy = new Galaxy();

        galaxy.addPlanet(ferengi);
        galaxy.addPlanet(betasoide);
        galaxy.addPlanet(vulcano);

        for(int i = 1; i < 3651; i++){
            galaxy.oneDayForward();
        }

        System.out.print("Durante los 10 años simulados se producirian ["+MySQLDatabase.getSumDaysByWeather(Weathers.droughtCode)+"] periodos de sequia. \n");
        System.out.print("Durante los 10 años simulados se producirian ["+MySQLDatabase.getSumDaysByWeather(Weathers.rainCode)+"] periodos de lluvia. \n");
        System.out.print("El pico maximo de lluvia se dio el dia "+galaxy.getDayMaxRain() +"\n");
        System.out.print("Durante los 10 años simulados se producirian ["+MySQLDatabase.getSumDaysByWeather(Weathers.optimalConditionsCode)+"] de condiciones optimas de presion y temperatura. \n");

    }
}
