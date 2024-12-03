package com.edutecno.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConexion {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/horoscopo_db";
        String user = "postgres";
        String password = "Nym3r1t0s.1108";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("¡Conexión exitosa!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
