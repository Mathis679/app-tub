package com.example.iem.apptub.classes;

import java.io.Serializable;

/**
 * Created by iem on 10/02/2017.
 */

public class Horaire implements Serializable{
    String name;
    String way;
    String hour;
    int numLine;

    public Horaire(String way, String hour, int numLine, String name){
        this.way = way;
        this.hour = hour;
        this.numLine = numLine;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWay() {
        return way;
    }

    public String getHour() {
        return hour;
    }

    public int getNumLine() {
        return numLine;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setNumLine(int numLine) {
        this.numLine = numLine;
    }
}
