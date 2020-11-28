package com.bluemarble.model;

import com.bluemarble.view.GameBoardView;

public class Country extends Board
{
    private String name;
    private int tollFee, countryPrice, villaBuyPrice, buildingBuyPrice, hotelBuyPrice, villaPayPrice, buildingPayPrice, hotelPayPrice;
    private int villaCount, buildingCount, hotelCount;
    private Player owner;

    public Country(String name, int countryPrice, int tollFee, int villaBuyPrice, int buildingBuyPrice, int hotelBuyPrice, int villaPayPrice, int buildingPayPrice, int hotelPayPrice)
    {
        super(true);
        this.name = name;
        this.countryPrice = countryPrice;
        this.tollFee = tollFee;
        this.villaBuyPrice = villaBuyPrice;
        this.buildingBuyPrice = buildingBuyPrice;
        this.hotelBuyPrice = hotelBuyPrice;
        this.villaPayPrice = villaPayPrice;
        this.buildingPayPrice = buildingPayPrice;
        this.hotelPayPrice = hotelPayPrice;
    }

    public int getTotalPrice()
    {
        return villaBuyPrice * villaCount + buildingBuyPrice * buildingCount + hotelBuyPrice * hotelCount;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public void buyConstructor(int choices, int count)
    {
        switch (choices)
        {
            case 0:
                this.tollFee += villaPayPrice * count;
                villaCount += count;
                break;

            case 1:
                this.tollFee += buildingPayPrice * count;
                buildingCount += count;
                break;

            case 2:
                this.tollFee += hotelPayPrice * count;
                hotelCount += count;
                break;
        }
    }

    @Override
    public void buyCountry(Player player)
    {

        this.owner = player;
    }

    @Override
    public Player getOwner()
    {
        return owner;
    }

    public String getOwnerName()
    {
        if(owner != null)
        {
            return owner.getName();
        }
        return "주인 없음";
    }

    @Override
    public int getTollFee()
    {
        return tollFee;
    }

    @Override
    public int getCountryPrice()
    {
        return countryPrice;
    }

    @Override
    public int getVillaPrice()
    {
        return villaBuyPrice;
    }

    @Override
    public int getBuildingPrice()
    {
        return buildingBuyPrice;
    }

    @Override
    public int getHotelPrice()
    {
        return hotelBuyPrice;
    }

    public int getVillaPayPrice()
    {
        return villaPayPrice;
    }

    public int getHotelPayPrice()
    {
        return hotelPayPrice;
    }

    public int getBuildingPayPrice()
    {
        return buildingPayPrice;
    }

    public int getVillaCount()
    {
        return villaCount;
    }

    public int getBuildingCount()
    {
        return buildingCount;
    }

    public int getHotelCount()
    {
        return hotelCount;
    }

    // Others 전용 메서드
    @Override
    public int getOtherType() { return -1; }

    @Override
    public void isolated(Player player) { }

    @Override
    public void donation(Player player) {  }
    @Override
    public void travel(Player player) { }
    @Override
    public void getPaid(Player player) { }
}
