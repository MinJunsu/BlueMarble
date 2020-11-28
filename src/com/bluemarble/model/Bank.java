package com.bluemarble.model;

import javax.swing.*;

public class Bank
{
    private Player[] players;
    private Player player1, player2, player3, player4;
    public int playerCount;

    public Bank(Player player1, Player player2, Player player3, Player player4, int playerCount)
    {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.playerCount = playerCount;
        players = new Player[playerCount];

        switch (5 - playerCount)
        {
            case 1:
                players[3] = player4;
            case 2:
                players[2] = player3;
            case 3:
                players[1] = player2;
            case 4:
                players[0] = player1;
                break;
        }

    }

    public boolean buyCountry(Player player, Country country)
    {
        boolean flag = false;
        int tmp;

        // 구매를 하려는 돈이 부족하면 바로 종료
        if(country.getCountryPrice() > player.getBalance())
        {
            JOptionPane.showMessageDialog(null, "돈이 부족하여 구매할 수 없습니다.", "구매 불가", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        do
        {
            tmp = JOptionPane.showConfirmDialog(null, country.getName() + " 국가를 구매하시겠습니까?\n 구매(Y) / 구매 안함(N)");
            if(tmp == JOptionPane.CANCEL_OPTION)
            {
                JOptionPane.showMessageDialog(null, "확인 아니면 아니오 둘 중 하나를 눌러주세요");
            }
        }
        while(tmp == JOptionPane.CANCEL_OPTION);

        if(tmp == JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(null, country.getName() + " 구매에 성공하셨습니다!!");
            country.buyCountry(player);
            flag = true;
        }
        return flag;
    }

    public void buyConstructor(Country country, Player player)
    {
        if(country.getVillaPrice() > player.getBalance())
        {
            JOptionPane.showMessageDialog(null, "돈이 부족하여 구매할 수 없습니다.", "구매 불가", JOptionPane.ERROR_MESSAGE);
            return ;
        }

        do
        {
            String tmp = JOptionPane.showInputDialog(
                    "어떤 건물을 지으시겠습니까?\n"
                            + "빌라(최대 2개) : " + country.getVillaPrice() + " 원\n"
                            + "빌딩(최대 1개) : " + country.getBuildingPrice() + " 원\n"
                            + "호텔(최대 1개) : " + country.getHotelPrice() + " 원\n"
                            + "입력 양식 예) 101 => 빌라 1개, 호텔 1개");

            try
            {
                int villaCount = Integer.parseInt(String.valueOf(tmp.charAt(0)));
                int buildingCount = Integer.parseInt(String.valueOf(tmp.charAt(1)));
                int hotelCount = Integer.parseInt(String.valueOf(tmp.charAt(2)));
                if(villaCount < 3 && buildingCount < 2 && hotelCount < 2)
                {
                    if(player.Construct(country, new int[] {villaCount, buildingCount, hotelCount}))
                    {
                        JOptionPane.showMessageDialog(null,
                                "빌라 " + villaCount +" 개, 빌딩 " + buildingCount + " 개, 호텔 " + hotelCount + " 개" +
                                "정상적으로 구매 완료하였습니다.");
                        break;
                    }
                    else
                    {
                        int result = JOptionPane.showConfirmDialog(null, "구매가 불가능한 조합입니다. 다시 선택하시겠습니까?");
                        if(!(result == JOptionPane.YES_OPTION))
                        {
                            break;
                        }
                    }
                }
            }
            catch (Exception e) {}
        }
        while(true);
    }

    public void payTollFee(Player owner, Player passer, int tollFee)
    {
        passer.withdraw(tollFee);
        owner.deposit(tollFee);
        JOptionPane.showMessageDialog(null,"통행료 지불" + tollFee);
    }

    public void getPaid(Player player)
    {
        player.deposit(200000);
        JOptionPane.showMessageDialog(null, "한 바퀴를 돌아 월급 20만원 을 받습니다!!");
    }

    public Player[] getPlayers()
    {
        return players;
    }
}
