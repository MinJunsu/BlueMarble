package com.bluemarble.controller;

import com.bluemarble.model.*;
import com.bluemarble.view.GameBoardView;
import com.bluemarble.view.MainView;
import com.bluemarble.view.PlayerPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGame
{
    private int playerCount;
    private GoldenKey goldenKey;
    private GameBoard gameBoard = new GameBoard();
    private Board[] boards;
    private Player[] players;
    private Dice dice;
    private MainView view;
    private GameBoardView gameBoardView;
    private JButton diceButton, turnOverButton;
    private Bank bank;
    private int doubleCount, turn;
    private int firstNumber, secondNumber;
    private Color[] colors = {Color.RED, Color.BLUE, Color.WHITE, Color.YELLOW};
    private PlayerPanelView[] playerPanelViews;

    public PlayGame(int playerCount, Dice dice, MainView view, Bank bank)
    {
        boards = gameBoard.getBoards();
        this.bank = bank;
        turn = 0;
        this.playerCount = playerCount;
        this.dice = dice;
        this.view = view;
        this.gameBoardView = view.getGameBoardView();
        this.diceButton = gameBoardView.getDiceButton();
        this.turnOverButton = gameBoardView.getTurnOverButton();
        players = bank.getPlayers();
        for(Player p : players)
        {
            p.setGameBoardView(gameBoardView);
        }
        gameBoardView.setBoards(boards);
        gameBoardView.setPlayerCount(bank.playerCount);
        goldenKey = new GoldenKey(bank, gameBoardView, this);
        playerPanelViews = gameBoardView.getPlayerPanelViews();
    }

    public void play(int firstNumber, int secondNumber)
    {
        if(players[turn].getIsIsolated())
        {
            if((firstNumber == secondNumber) || (players[turn].getIsolateCount() > 2))
            {
                players[turn].escapeIsolate();
                JOptionPane.showMessageDialog(null, "무인도 탈출 성공!! 다음턴 부터 정상적으로 움직일 수 있습니다.");
            }
            else
            {
                players[turn].increaseIsolateCount();
                JOptionPane.showMessageDialog(null, "현재 " + (players[turn].getIsolateCount() - 1) + " 번째 탈출 시도"
                        + "\n첫번째 주사위 : " + firstNumber + ", 두번째 주사위 : " + secondNumber + "로 탈출 불가합니다.");
            }
            doTurnOver();
            return;
        }

        gameBoardView.moveAirPlane(firstNumber + secondNumber, players[turn].getPosition(), turn);
        if((players[turn].getPosition() + firstNumber + secondNumber) / 40 == 1)
        {
            bank.getPaid(players[turn]);
        }
        players[turn].move(firstNumber + secondNumber);

        if (boards[players[turn].getPosition()].getType())
        {
            if (boards[players[turn].getPosition()].getOwner() == null)
            {
                if(bank.buyCountry(players[turn], (Country) boards[players[turn].getPosition()]))
                {
                    gameBoardView.setButtonColor(colors[turn], players[turn].getPosition());
                }
            }
            else if(boards[players[turn].getPosition()].getOwner() != players[turn])
            {
                if(boards[players[turn].getPosition()].getName() == "제주도" || boards[players[turn].getPosition()].getName() == "콩코드 여객기" || boards[players[turn].getPosition()].getName() == "부산"
                        || boards[players[turn].getPosition()].getName() == "타이타닉호" || boards[players[turn].getPosition()].getName() == "콜롬비아호" || boards[players[turn].getPosition()].getName() == "서울" ) {
                    return;
                }
                bank.payTollFee(boards[players[turn].getPosition()].getOwner(), players[turn], boards[players[turn].getPosition()].getTollFee());
            }
            else
            {
                bank.buyConstructor((Country) boards[players[turn].getPosition()], players[turn]);
            }
        }
        else
        {
            int value = boards[players[turn].getPosition()].getOtherType();
            switch (value)
            {
                case 1:
                    JOptionPane.showMessageDialog(null, "무인도에 들어오셨습니다. 더블이 나오시거나, 3턴이 지나면 탈출할 수 있습니다.");
                    boards[players[turn].getPosition()].isolated(players[turn]);
                    doTurnOver();
                    break;

                case 2:
                    boards[players[turn].getPosition()].donation(players[turn]);
                    break;

                case 3:
                    boards[players[turn].getPosition()].travel(players[turn]);
                    break;

                case 4:
                    // 사회 복지기금 받기
                    break;

                case 5:
                    goldenKey.makeRandomNumber();
                    goldenKey.setPlayer(turn);
                    goldenKey.pickGoldenKey(players[turn]);
                    break;
            }
        }
        playerPanelViews[turn].updatePlayer();
    }
    
    public void goldenKeyPlay(int arrival_index)
    {
    	int move = arrival_index > players[turn].getPosition() ? players[turn].getPosition() - arrival_index : 40 - (arrival_index - players[turn].getPosition());
        play(move, 0);
    }

    public void doTurnOver()
    {

        turn++;

        if(turn >= bank.playerCount)
        {
            turn = 0;
        }
        gameBoardView.setTurn(turn);
        gameBoardView.repaint();
        turnOverButton.setEnabled(false);
        diceButton.setEnabled(true);
        doubleCount = 0;
    }

    public void isClicked()
    {
        diceButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dice.throwDice();
                firstNumber = dice.getFirstDice();
                secondNumber = dice.getSecondDice();
                gameBoardView.setDice(firstNumber, secondNumber);
                if(firstNumber != secondNumber)
                {
                    turnOverButton.setEnabled(true);
                    diceButton.setEnabled(false);
                }
                gameBoardView.setDoubleLabelText(++doubleCount);
                if(doubleCount == 3)
                {
                    boards[players[turn].getPosition()].isolated(players[turn]);
                    doTurnOver();
                }
                play(firstNumber, secondNumber);
            }
        });

        turnOverButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                doTurnOver();
            }
        });
    }
}
