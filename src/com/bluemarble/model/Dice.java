package com.bluemarble.model;

public class Dice
{
    private int firstDice, secondDice;

    public Dice()
    {
        firstDice = (int) (Math.random()*6 + 1);
        secondDice = (int) (Math.random()*6 + 1);
    }

    public int getFirstDice()
    {
        return firstDice;
    }

    public int getSecondDice()
    {
        return secondDice;
    }

    public void throwDice()
    {
        firstDice = (int) (Math.random()*6 + 1);
        secondDice = (int) (Math.random()*6 + 1);
    }
}