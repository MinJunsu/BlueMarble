package com.bluemarble.model;

import com.bluemarble.controller.PlayGame;
import com.bluemarble.view.GameBoardView;

import javax.swing.*;

public class GoldenKey
{
    private int lastValue, randomNumber, turn;
    private String message;
    private Player player;
    private Bank bank;
    private GameBoardView gameBoardView;
    private PlayGame playGame;

    public GoldenKey(Bank bank, GameBoardView gameBoardView, PlayGame playGame)
    {
        lastValue = 24;
        this.bank = bank;
        this.gameBoardView = gameBoardView;
        this.playGame = playGame;
    }

    public void setPlayer(int turn)
    {
        this.turn = turn;
    }

    public void makeRandomNumber()
    {
        randomNumber = (int) (Math.random() * lastValue) + 1;
    }

    public void pickGoldenKey(Player p)
    {
        switch (randomNumber)
        {
            case 1:
                hospitalBill(p);
                break;
            case 2:
                lottery(p);
                break;
            case 3:
                uninhabitedIsland(p);
                break;
            case 4:
                partyInvitation(p);
                break;
            case 5:
                jejuTour(p);
                break;
            case 6:
                finesSpeeding(p);
                break;
            case 7:
                studyingAbroad(p);
                break;
            case 8:
                pension(p);
                break;
            case 9:
                back3(p);
                break;
            case 10:
                back2(p);
                break;
            case 11:
                goStartLine(p);
                break;
            case 12:
                winner(p);
                break;
            case 13:
                exemption(p);
                break;
            case 14:
                concordeToTaipai(p);
                break;
            case 15:
                busanTour(p);
                break;
            case 16:
                crimePrevention(p);
                break;
            case 17:
                scholarship(p);
                break;
            case 18:
                worldTrip(p);
                break;
            case 19:
                repairCost(p);
                break;
            case 20:
                peacePrize(p);
                break;
            case 21:
                halfPriceSale(p);
                break;
            case 22:
                seoulTour(p);
                break;
            case 23:
                incomeTax(p);
                break;
            case 24:
                spaceTour(p);
                break;
            case 25:
                fundDividend(p);
                break;
        }
    }

    private void showMessage(String m)
    {
        message = m;
        JOptionPane.showMessageDialog(null, message);
    }

    private void hospitalBill(Player p)
    {
        message = "   • 병원비지불  • \n" +
                "병원에서 건강진단을 받았음. \n" +
                "병원비 5만원을 은행에 내시오.\n";
        showMessage(message);
        if (bank.isBankrupt(p, 50000))
        {
            showMessage(p.getName() + "님이 파산하셨습니다.");
        }
        else
        {
        	p.withdraw(50000);
        }
            
    }

    private void lottery(Player p)
    {
        message = "   • 복권당첨  • \n" +
                "복권에 당첨되었음.\n" +
                " 당첨금 20만원 ";
        showMessage(message);
        p.deposit(200000);
    }

    private void uninhabitedIsland(Player p)
    {
        message = "   • 무인도  • \n" +
                "무인도로 가시오.\n" +
                "폭풍을 만났음. 무인도로 곧장 가되 출발지를 지날때도 월급 못받음";
        showMessage(message);
        p.setPosition(10);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
        playGame.play(-1, 1);
    }

    private void partyInvitation(Player p)
    {
        message = "   • 파티초대권  • \n" +
                "대중앞에서 장기자랑하시오 \n" +
                "공연비 10만원 받으시오.";
        showMessage(message);
        p.deposit(100000);
    }

    private void jejuTour(Player p)
    {
        message = "   • 관광여행(제주도)  • \n" +
                "제주도로 가시오.(제주도에 통행료 지불, 출발지 거쳐갈경우 월급 받으시오.)";
        showMessage(message);
        
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
        playGame.goldenKeyPlay(5);
    }

    private void finesSpeeding(Player p) 
    {
        message = "   • 과속운전벌금  • \n" +
                "과속운전을 했으므로 벌금 5만원을 내시오.\n";
        showMessage(message);
        if (bank.isBankrupt(p, 50000))
        {
            showMessage(p.getName() + "님이 파산하셨습니다.");
        }
        else
        {
        	p.withdraw(50000);
        }
    }

    private void studyingAbroad(Player p)
    {
        message = "   • 해외유학  • \n" +
                "학교 등록금 10만원을 내시오";
        showMessage(message);
        if (bank.isBankrupt(p, 100000))
        {
            showMessage(p.getName() + "님이 파산하셨습니다.");
        }
        else
        {
        	p.withdraw(100000);
        }
    }

    private void pension(Player p)
    {
        message = "   • 연금해택  • \n" +
                "은행에서 노후연금 5만원을 받으시오";
        showMessage(message);
        p.deposit(50000);
    }

    private void back3(Player p)
    {
        message = "   • 이사가시오  • \n" +
                "뒤로 세칸 옮기시오\n";
        showMessage(message);
        p.setPosition((p.getPosition() + 37) % 40);
        playGame.play(-1, 1);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }

    private void back2(Player p)
    {
        message = "   • 이사가시오  • \n" +
                "뒤로 두칸 옮기시오\n";
        showMessage(message);
        p.setPosition((p.getPosition() + 38) % 40);
        playGame.play(-1, 1);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }

    private void goStartLine(Player p)
    {
        message = "   • 고속도로  • \n" +
                "출발지까지 곧바로 가시오";
        showMessage(message);
        playGame.goldenKeyPlay(0);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }

    private void winner(Player p)
    {
        message = "   • 우승  • \n" +
                "자동차경주에서 챔피온이 되었음. 상금 10만원";
        showMessage(message);
        p.deposit(100000);
    }

    private void exemption(Player p)
    {
        message = "   • 우대권  • \n" +
                "이 우대권을 가지고 있을경우 상대방의 장소를 통행료없이 머무를 수 있음\n" +
                "1회 사용후 열쇠함에 반납하시오. 중요한 순간에만 쓰시오.";
        showMessage(message);
        p.setTicketCount(p.getTicketCount() + 1);
    }

    private void concordeToTaipai(Player p)
    {
        message = "   • 항공여행  • \n" +
                "콩코드 여객기를 타시고 타이페이로 가시오\n" +
                "콩코드에게 객실료를 지불하시오, 출발지를 거쳐갈경우 월급을 타시오.)";
        showMessage(message);
        p.withdraw(300000);
        if (p.getPosition() > 1)
        {
            bank.getPaid(p);
        }
        p.setPosition(1);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }

    private void busanTour(Player p) {
        message = "   • 관광여행  • \n" +
                "부산으로 가시오\n" +
                "부산에 통행료 지불, 출발지 거쳐갈 경우 월급을 타시오.";
        showMessage(message);
        playGame.goldenKeyPlay(24);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }

    private void crimePrevention(Player p)
    {
        message = "   • 방범비  • \n" +
                "방범비를 각 건물별로 다음과 같이 은행에 내시오\n" +
                "호텔 : 5만원, 빌딩 : 3만원, 별장 : 1만원";
        showMessage(message);
        int[] constructCount = p.getConstructCount();

        int pay = constructCount[2] * 50000 + constructCount[1] * 30000 + constructCount[0] * 10000;
        if (bank.isBankrupt(p, pay))
        {
            showMessage(p.getName() + "님이 파산하셨습니다.");
        }
        else
        {
        	if (p.withdraw(pay))
        		showMessage("납부 성공");
        }
    }

    private void scholarship(Player p) 
    {
        message = "   • 장학금혜택  • \n" +
                "은행에서 장학금 10만원을 받으시오";
        showMessage(message);
        p.deposit(100000);
    }

    private void worldTrip(Player p) 
    {
        message = "   • 세계일주초대권  • \n" +
                "축하하오. 현재 위치에서부터 한바퀴 돌아오시오.\n" +
                "출발지 거쳐가면서 월급을 타시고, \n 복지기금을 거쳐가면서 모아놓은 기금을 받으시오.";
        showMessage(message);
        playGame.play(40,0);
    }

    private void repairCost(Player p)
    {
        message = "   • 건물수리비지불  • \n" +
                "건물 수리비를 각 건물별로 다음과 같이 은행에 지불하시오.\n" +
                "호텔 : 10만원, 빌딩 : 6만원, 별장 : 3만원";
        showMessage(message);
        int[] constructCount = p.getConstructCount();

        int pay = constructCount[2] * 100000 + constructCount[1] * 60000 + constructCount[0] * 30000;
        if (bank.isBankrupt(p, pay))
        {
            showMessage(p.getName() + "님이 파산하셨습니다.");
        }
        else
        {
        	p.withdraw(pay);
        }
    }

    private void peacePrize(Player p)
    {
        message = "   • 노벨평화상수상  • \n" +
                "당신은 세계평화를 위해 공헌하였으므로 은행으로부터 상금 30만원을 받습니다.";
        showMessage(message);
        p.deposit(300000);
    }

    private void halfPriceSale(Player p)
    {
        message = "   • 반액대매출  • \n" +
                "당신의 재산중에서 제일 비싼곳을 반액으로 은행에 파시오";
        showMessage(message);
        p.saleCountry(p.getHighPrice(), 50);
    }

    private void seoulTour(Player p)
    {
        message = "   • 관광여행\n" +
                "88년도 올림픽 개최지인 서울로 가시오\n" +
                "서울에 통행료 200만원 지불, 출발지 거칠경우 월급을 타시오.";
        showMessage(message);
        playGame.goldenKeyPlay(39);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }

    private void incomeTax(Player p)
    {
        message = "   • 정기종합소득세\n" +
                "정기종합소득세를 각 건물별로 다음과 같이 은행에 내시오\n" +
                "호텔 : 15만원, 빌딩 : 10만원, 별장 : 3만원";
        showMessage(message);
        int[] constructCount = p.getConstructCount();
        int pay = constructCount[2] * 150000 + constructCount[1] * 100000 + constructCount[0] * 30000;
        if (bank.isBankrupt(p, pay))
        {
            showMessage(p.getName() + "님이 파산하셨습니다.");
        }
        else
        {
        	p.withdraw(pay);
        }
    }

    private void spaceTour(Player p)
    {
        message = "   • 우주여행초청장\n" +
                "우주항공국에서 우주여행초청장이 왔음\n" +
                "출발지 거칠경우 월급을 타시오.";
        showMessage(message);
        playGame.goldenKeyPlay(30);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }

    private void fundDividend(Player p)
    {
        message = "   • 사회복지기금배당\n" +
                "사회복지기금 접수처로 가시오\n" +
                "출발지 거칠경우 월급타시오";
        showMessage(message);
        playGame.goldenKeyPlay(38);
        gameBoardView.moveAirPlane(1, p.getPosition(), turn);
    }
}