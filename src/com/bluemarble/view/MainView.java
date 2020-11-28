package com.bluemarble.view;

import com.bluemarble.model.Dice;

import javax.swing.*;

public class MainView extends JFrame
{
    private GameBoardView gameBoardView;
    public MainView(Dice dice)
    {
        super("부르마블");
        setSize(1014, 1037);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoardView = new GameBoardView(this, dice);
        add(gameBoardView);
        setVisible(true);
    }

    public GameBoardView getGameBoardView()
    {
        return gameBoardView;
    }
}
