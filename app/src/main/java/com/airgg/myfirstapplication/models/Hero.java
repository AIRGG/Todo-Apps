package com.airgg.myfirstapplication.models;

public class Hero {
    String name, team;

    public Hero(String name, String team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }
}