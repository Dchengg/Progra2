package com.example.poo.progra2.xml;

import com.example.poo.progra2.modelo.Practicante;

import java.util.ArrayList;

public interface DaoInterface {
    ArrayList<Practicante> practicantes = new ArrayList<Practicante>();

    public void parseXml();

}
