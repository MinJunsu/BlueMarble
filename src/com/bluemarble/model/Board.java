package com.bluemarble.model;

import com.bluemarble.view.GameBoardView;

public abstract class Board
{
    private boolean type;

    public Board(boolean type)
    {
        this.type = type;
    }

    public boolean getType()
    {
        return this.type;
    }

    public abstract String getName();

    public abstract void buyConstructor(int choices, int count);

    public abstract void buyCountry(Player player);

    public abstract Player getOwner();

    public abstract int getTollFee();

    public abstract int getCountryPrice();

    public abstract int getOtherType();

    public abstract int getVillaPrice();

    public abstract int getBuildingPrice();

    public abstract int getHotelPrice();

    public abstract void travel(Player player);

    public abstract void isolated(Player player);

    public abstract void donation(Player player);

    public abstract void getPaid(Player player);
}
