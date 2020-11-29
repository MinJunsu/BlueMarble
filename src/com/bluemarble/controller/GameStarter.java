package com.bluemarble.controller;

import com.bluemarble.model.Bank;
import com.bluemarble.model.Dice;
import com.bluemarble.model.Player;
import com.bluemarble.view.MainView;

public class GameStarter
{
     Player firstPlayer, secondPlayer, thirdPlayer, forthPlayer;
    private Bank bank;
    private int playerCount;
    private String[] playerNames;
    public GameStarter(int playerCount, String[] playerNames)
    {
        this.playerCount = playerCount;
        this.playerNames = playerNames;

        setPlayer();

        Dice dice = new Dice();
        Bank bank = new Bank(firstPlayer, secondPlayer, thirdPlayer, forthPlayer, playerCount);
        MainView view = new MainView(dice, bank);
        PlayGame game = new PlayGame(4, dice, view, bank);
        game.isClicked();
    }

    public void setPlayer()
    {
        switch (playerCount)
        {
            case 4:
                forthPlayer = new Player(playerNames[3], 1000000);

            case 3:
                thirdPlayer = new Player(playerNames[2], 1000000);

            case 2:
                secondPlayer = new Player(playerNames[1], 1000000);
                firstPlayer = new Player(playerNames[0], 1000000);
                break;
        }
    }
}
