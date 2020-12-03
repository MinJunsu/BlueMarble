package com.bluemarble.model;

import java.util.ArrayList;

public class Player
{
    private String name;
    private boolean isIsolated;
    private int isolateCount;
    private int balance;
    private int position;
    private ArrayList<Country> countries;
    private int ticketCount;
    private String topCountry;

    public Player(String name, int balance)
    {
        this.position = 0;
        this.name = name;
        this.balance = balance;
        this.topCountry = "";
        countries = new ArrayList<Country>();
    }

    public Country getHighPrice()
    {
        int tmp = 0;
        Country country = null;
        for(Country c : countries)
        {
            if(c.getTotalPrice() > tmp)
            {
                tmp = c.getTotalPrice();
                country = c;
            }
        }
        return country;
    }
    
    public void setIsolated()
    {
        this.isIsolated = true;
    }

    public boolean getIsIsolated()
    {
        return isIsolated;
    }

    public int getIsolateCount()
    {
        return isolateCount;
    }

    public void increaseIsolateCount()
    {
        isolateCount++;
    }

    public void escapeIsolate()
    {
        isIsolated = false;
        isolateCount = 0;
    }

    public boolean Construct(Country country, int[] choices)
    {
        int sum = country.getVillaPrice() * choices[0] + country.getBuildingPrice() * choices[1] + country.getHotelPrice() * choices[2];
        if(withdraw(sum))
        {
            for(int i = 0; i < 3; i++)
            {
                country.buyConstructor(i, choices[i]);
            }
            return true;
        }
        return false;
    }

    public boolean buyCountry(Country country)
    {
        if(withdraw(country.getCountryPrice()))
        {
            country.buyCountry(this);
            countries.add(country);
            return true;
        }
        return false;
    }

    public void saleCountry(Country country, int per)
    {
    	int sale_price = (int) (country.getTotalPrice() * (per / 100));
    	this.deposit(sale_price);
    	countries.remove(country);
    }
    
    public boolean withdraw(int price)
    {
        if(price < balance)
        {
            balance -= price;
            return true;
        }
        return false;
    }

    public int[] getConstructCount()
    {
        int villaCount = 0, buildingCount = 0, hotelCount = 0;
        for(Country c : countries)
        {
            villaCount += c.getVillaCount();
            buildingCount += c.getBuildingCount();
            hotelCount += c.getHotelCount();
        }
        return new int[] { villaCount, buildingCount, hotelCount };
    }

    public void move(int num)
    {
        this.position = (this.position + num) % 40;
    }

    public ArrayList<Country> getCountries()
    {
        return countries;
    }

    public void deposit(int price)
    {
        this.balance += price;
    }

    public int getPosition()
    {
        return this.position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public String getName()
    {
        return name;
    }

    public int getBalance()
    {
        return this.balance;
    }

    public int getTicketCount()
    {
        return ticketCount;
    }

    public void setTicketCount(int count)
    {
        this.ticketCount = count;
    }

    public void addCountry(Country c)
    {
        countries.add(c);
    }
}
