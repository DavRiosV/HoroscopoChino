package com.edutecno.procesaconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/horoscopo_db";
	private static final String USER  = "postgres";
	private static final String PASS  = "Nym3r1t0s.1108";
	private static Connection cnx;
	
	public static Connection getConexion() throws SQLException, ClassNotFoundException {
		if (cnx == null || cnx.isClosed()) {
			Class.forName("org.postgresql.Driver");
			cnx = DriverManager.getConnection(URL, USER, PASS);
			System.out.print("conexcion establecida, porfin ");
		}
		return cnx;
	}

} 
 