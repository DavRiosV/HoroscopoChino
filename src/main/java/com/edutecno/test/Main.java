package com.edutecno.test;

import com.edutecno.dao.HoroscopoDAOImp;
import com.edutecno.modelo.Horoscopo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        HoroscopoDAOImp dao = new HoroscopoDAOImp();
        List<Horoscopo> horoscopos = dao.obtenerHoroscopo();

        for (Horoscopo h : horoscopos) {
            System.out.println(h.getAnimal() + " - " + h.getFechaInicio() + " a " + h.getFechaFin());
        }
    }
}
