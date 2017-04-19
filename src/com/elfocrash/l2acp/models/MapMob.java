package com.elfocrash.l2acp.models;

public class MapMob {
    public String Name;
    public int MaxHp;
    public int CurrentHp;
    public int X;
    public int Y;
    public int Level;
    
    public MapMob(String name, int maxHp, int currentHp, int level, int x, int y){
    	Name = name;
    	MaxHp = maxHp;
    	CurrentHp = currentHp;
    	Level = level;
    	X = x;
    	Y = y;
    }
}