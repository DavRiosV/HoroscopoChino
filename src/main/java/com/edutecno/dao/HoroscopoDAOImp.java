package com.edutecno.dao;

import com.edutecno.modelo.Horoscopo;
import com.edutecno.procesaconexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoroscopoDAOImp {
    public List<Horoscopo> obtenerHoroscopo() {
        List<Horoscopo> horoscopos = new ArrayList<>();
        String sql = "SELECT * FROM horoscopo";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                horoscopos.add(new Horoscopo(
                    rs.getString("animal"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return horoscopos;
    }
}
