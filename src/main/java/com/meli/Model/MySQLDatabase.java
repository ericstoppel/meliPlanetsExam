package com.meli.Model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLDatabase {

    static Connection myConnection = null;
    static PreparedStatement prepareStat = null;

    private static void openConnection(){
        makeJDBCConnection();
    }

    private static void closeConnection(){
        try {
            prepareStat.close();
            myConnection.close(); // connection close
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String getProperty(String property){
        String prop = "";
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("Sensitive.conf");
            Properties props = new Properties();
            props.load(in);
            prop = props.getProperty(property);
            in.close();
        }catch (IOException ex){
            return prop;
        }
        return prop;
    }

    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log("No se encontro el driver");
            e.printStackTrace();
            return;
        }

        try {
            //myConnection = DriverManager.getConnection("jdbc:mysql://y0nkiij6humroewt.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/v5q34vhl8lirv0ck", "uv201leyd0sjjyz9", "vn4bygfc4rb5twz3");
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meli", getProperty("user"), getProperty("pw"));
            if (myConnection != null) {
            } else {
                log("Fallo al conectar a la BD!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }

    public static void addDataToDB(int dia, int clima) {

        try {
            openConnection();
            String insertQueryStatement = "INSERT  INTO  weathers  VALUES  (?,?)";

            prepareStat = myConnection.prepareStatement(insertQueryStatement);
            prepareStat.setInt(1, dia);
            prepareStat.setInt(2, clima);

            prepareStat.executeUpdate();
            log(" Se inserto a la bd Dia["+dia+"], clima ["+clima+"]");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    public static int getWeather(int dia) {

        try {
            openConnection();
            String getQueryStatement = "SELECT * FROM weathers WHERE dia="+dia;

            prepareStat = myConnection.prepareStatement(getQueryStatement);
            ResultSet rs = prepareStat.executeQuery();

            while (rs.next()) {
                int weather = rs.getInt("condicion_climatica");
                return weather;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            closeConnection();
        }
        return 0;
    }

    public static int getSumDaysByWeather(int code){
        try {
            openConnection();
            String getQueryStatement = "SELECT sum(1) as sum FROM weathers WHERE condicion_climatica="+code;

            prepareStat = myConnection.prepareStatement(getQueryStatement);
            ResultSet rs = prepareStat.executeQuery();

           while (rs.next()) {
                int cant = rs.getInt("sum");
                return cant;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            closeConnection();
        }
        return 0;
    }

    public static void clearDB(){
        try {
            openConnection();
            String getQueryStatement = "DELETE from weathers";
            prepareStat = myConnection.prepareStatement(getQueryStatement);
            prepareStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    private static void log(String string) {
        System.out.println(string);
    }
}
