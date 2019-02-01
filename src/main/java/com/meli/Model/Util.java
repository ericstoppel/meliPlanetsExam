package com.meli.Model;

import java.math.BigDecimal;
import java.math.MathContext;

public class Util {

    public static boolean areAlineated(Position p1, Position p2, Position p3){
        BigDecimal x1 =BigDecimal.valueOf(p1.getxPosition());
        BigDecimal x2 =BigDecimal.valueOf(p2.getxPosition());
        BigDecimal x3 =BigDecimal.valueOf(p3.getxPosition());
        BigDecimal y1=BigDecimal.valueOf(p1.getyPosition());
        BigDecimal y2=BigDecimal.valueOf(p2.getyPosition());
        BigDecimal y3=BigDecimal.valueOf(p3.getyPosition());

        if(p1.getxPosition() == p2.getxPosition() && p1.getxPosition() == p3.getxPosition()){
            return true;
        }
        if(p1.getyPosition() == p2.getyPosition() && p1.getyPosition() == p3.getyPosition()){
            return true;
        }

        BigDecimal res1 = (x2.subtract(x1)).divide(x3.subtract(x2), MathContext.DECIMAL128);
        BigDecimal res2 = (y2.subtract(y1)).divide(y3.subtract(y2), MathContext.DECIMAL128);

        return res1.equals(res2);
    }


    public static boolean containsSun(Position p1, Position p2, Position p3){
        double d1, d2, d3;
        boolean has_neg, has_pos;
        Position sun = new Position(0,0);

        d1 = sign(sun, p1, p2);
        d2 = sign(sun, p2, p3);
        d3 = sign(sun, p3, p1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }

    private static double sign (Position p1, Position p2, Position p3)
    {
        return (p1.getxPosition() - p3.getxPosition()) * (p2.getyPosition() - p3.getyPosition()) - (p2.getxPosition() - p3.getxPosition()) * (p1.getyPosition() - p3.getyPosition());
    }

    public static double getPerimeter(Position p1, Position p2, Position p3){
        double s1 = getDistance(p1, p2);
        double s2 = getDistance(p2, p3);
        double s3 = getDistance(p1, p3);

        return s1+s2+s3;
    }

    private static double getDistance(Position pos1, Position pos2){
        double x = pos1.getxPosition() - pos2.getxPosition();
        double y = pos1.getyPosition() - pos2.getyPosition();
        return Math.sqrt(Math.pow(x,2) + Math.pow(x,2));
    }

}
