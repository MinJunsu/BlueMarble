package com.bluemarble.model;

import com.bluemarble.view.GameBoardView;

import javax.swing.*;

public class Others extends Board
{
    private String name;
    private int otherType;

    public Others(String name, int otherType)
    {
        super(false);
        this.name = name;
        this.otherType = otherType;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getOtherType()
    {
        return otherType;
    }

    @Override
    public void travel(Player player)
    {

    }

    @Override
    public void isolated(Player player)
    {
        player.setIsolated();
    }

    @Override
    public void donation(Player player)
    {
        if(player.withdraw(300000))
        {
            JOptionPane.showMessageDialog(null, "기부처에 들어와 30만원 기부를 합니다! 기쁜 마음으로 기부하세요~");
        }
        else
        {
            // 강제 매각 메서드
        }
    }

    @Override
    public void getPaid(Player player)
    {

    }

    // Country 전용 메서드

    @Override
    public int getTollFee() { return 0; }

    @Override
    public int getCountryPrice() { return 0; }

    @Override
    public void buyConstructor(int choices, int count) { }

    @Override
    public void buyCountry(Player player) { }

    @Override
    public Player getOwner() { return null; }

    @Override
    public int getVillaPrice() { return 0; }

    @Override
    public int getBuildingPrice() { return 0; }

    @Override
    public int getHotelPrice() { return 0; }
}
