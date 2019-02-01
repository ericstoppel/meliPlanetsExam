package com.meli.Model;

public class Weathers {
    public static final int rainCode = 1;
    public static final int droughtCode = 2;
    public static final int optimalConditionsCode = 3;
    public static final int normalDayCode = 4;

    public static String stringify(int code){
        switch (code){
            case rainCode: return "lluvia";
            case droughtCode: return "sequia";
            case optimalConditionsCode: return "condiciones optimas";
            default: return "normal";
        }
    }
}
