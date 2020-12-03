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
    private boolean isBankrupt;

    public Player(String name, int balance)
    {
        this.position = 0;
        this.name = name;
        this.balance = balance;
        countries = new ArrayList<Country>();
        this.isolateCount = 1;
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

    public void saleCountry(Country country, int per)
    {
        int sale_price = (int) (country.getTotalPrice() * (per / 100));
        this.deposit(sale_price);
        countries.remove(country);
    }

    public void setBankrupt()
    {
        this.isBankrupt = true;
        for(int i = 0; i < countries.size(); i++)
        {
            countries.get(i).saleCountry();
        }
        this.name = "파산";
    }

    public boolean getBankrupt()
    {
        return isBankrupt;
    }

    public void escapeIsolate()
    {
        isIsolated = false;
        isolateCount = 1;
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

    public void sellCountry(String countryName)
    {
        for(int i = 0; i < countries.size(); i++)
        {
            if(countries.get(i).getName().equals(countryName))
            {
                countries.get(i).saleCountry();
                countries.remove(i);
                // 우선 20만원 입금
                this.deposit(200000);
            }
        }
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
