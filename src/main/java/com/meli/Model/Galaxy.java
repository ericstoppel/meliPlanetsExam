package com.meli.Model;

import com.meli.Model.Exceptions.NotInstancedException;

import java.util.Vector;

public class Galaxy {
    Vector<Planet> planetVec = new Vector<Planet>(3);
    int day;
    private int dayMaxRain;
    private double intensityMaxRain;

    public Galaxy(){
        day = 1;
    }

    public int getDayMaxRain() {
        return dayMaxRain;
    }

    public void addPlanet(Planet planet){
        planetVec.add(planet);
    }

    public void setPlanetVector(Vector<Planet> vec){
        planetVec = vec;
    }

    private int calculateWeather() throws NotInstancedException{
        if (planetVec.size() == 0){
            throw new NotInstancedException();
        }

        int weather;

        if(Util.areAlineated(planetVec.get(0).getPos(), planetVec.get(1).getPos(), planetVec.get(2).getPos())){
            Position sunPosition = new Position(0,0);
            if (Util.areAlineated(planetVec.get(0).getPos(), planetVec.get(1).getPos(), sunPosition)){
                //ALINEADOS CON EL SOL
                weather = Weathers.droughtCode;
            }else{
                //ALINEADOS SIN EL SOL
                weather = Weathers.optimalConditionsCode;
            }
        }else{
            if (Util.containsSun(planetVec.get(0).getPos(), planetVec.get(1).getPos(), planetVec.get(2).getPos())){
                //TRIANGULO QUE CONTIENE AL SOL
                weather = Weathers.rainCode;
                double perimeter = Util.getPerimeter(planetVec.get(0).getPos(), planetVec.get(1).getPos(), planetVec.get(2).getPos());
                if (perimeter > intensityMaxRain){
                    intensityMaxRain = perimeter;
                    dayMaxRain = day;
                }
            }else {
                //TRIANGULO QUE NO CONTIENE AL SOL
                weather = Weathers.normalDayCode;
            }
        }
        return weather;
    }

    public void oneDayForward(){

        for(Planet planet : planetVec){
            planet.advanceOneDay();
        }

        try{
            int weather = calculateWeather();
            MySQLDatabase.addDataToDB(day, weather);
            day++;
        }catch (NotInstancedException ex){
            return;
        }

    }
}
