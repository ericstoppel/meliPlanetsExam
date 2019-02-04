package com.meli.Model;

public class Planet {

    //Se uso una clase civilizacion en caso de que el dia de mañana puedan cambiar de planeta, me parecio
    //una mejor practica que simplemente ponerle el nombre de la civilizacion al planeta, ademas, si en algun
    //momento se quiere agregar funcionalidad por civilizacion se facilita el codeo.

    Civilization civilization = null;
    Position pos;
    int grades;
    boolean clockwise;
    int distanceToSun;
    int gradesPerDay;

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public Planet(float x, float y, int g, boolean cw, int dts, int gradespd){
        distanceToSun = dts;
        gradesPerDay = gradespd;
        pos = new Position(x,y);
        grades = g;
        clockwise = cw;
    }

    public Position getPos() {
        return pos;
    }

    public void advanceOneDay(){
        this.rotate();
    }

    public void rotate(){
        //rota, cambia coordenadas
        if(clockwise){
            rotateClockWise();
        }else{
            rotateCounterClockWise();
        }
        updateCoordinates();
    }

    private void updateCoordinates(){
        pos.setxPosition(distanceToSun * Math.cos(Math.toRadians(grades)));
        pos.setyPosition(distanceToSun * Math.sin(Math.toRadians(grades)));
    }

    private void rotateClockWise(){
        if(grades - gradesPerDay < 0){
            grades = grades - gradesPerDay + 360;
        }else{
            grades -= gradesPerDay;
        }
    }

    private void rotateCounterClockWise(){
        if(grades + gradesPerDay > 360){
            grades = grades + gradesPerDay - 360;
        }else{
            grades += gradesPerDay;
        }
    }

    private static void log(String string) {
        System.out.println(string);

    }
}
