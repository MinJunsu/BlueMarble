package com.bluemarble.view;

import com.bluemarble.model.Country;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel
{
    private Country country;

    public StatusPanel(Country c)
    {
        country = c;
    }

    public void paintComponent(Graphics g)
    {
        g.drawString(country.getName() + "(" + (country.getCountryPrice() / 10000) + "만원)",10, 20);
        g.drawString(country.getOwnerName(),150, 20);
        g.drawString("수",80, 48);
        g.drawString("가 치",150, 48);
        g.drawString("통 행 료",250, 48);
        g.drawString("별 장 :",10, 86);
        g.drawString(String.valueOf(country.getVillaCount()),80, 86);
        g.drawString(country.getVillaPrice() + " 원",150, 86);
        g.drawString(country.getVillaPayPrice() * country.getVillaCount()+ " 원",250, 86);
        g.drawString("빌 딩 :",10, 124);
        g.drawString(String.valueOf(country.getBuildingCount()),80, 124);
        g.drawString(country.getBuildingPrice() + "원",150, 124);
        g.drawString(country.getBuildingPayPrice() * country.getBuildingCount()+ "원",250, 124);
        g.drawString("호 텔 :",10, 162);
        g.drawString(String.valueOf(country.getHotelCount()),80, 162);
        g.drawString(country.getHotelPrice() + "원",150, 162);
        g.drawString(country.getHotelPayPrice() * country.getHotelCount() + "원",250, 162);
        g.drawString("총 계 :",10, 200);
        g.drawString(country.getTotalPrice() + "원",150, 200);
        g.drawString(country.getTollFee() + "원",250, 200);
    }
}