package com.bluemarble.view;

import com.bluemarble.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameBoardView extends JPanel
{
    private Image smallRedAirPlane, smallBlueAirPlane, smallWhiteAirPlane, smallYellowAirPlane;
    private Image bigRedAirPlane, bigBlueAirPlane, bigWhiteAirPlane, bigYellowAirPlane;
    private Image[] smallAirPlaneImage, bigAirPlaneImage;
    private Image oneDice, twoDice, threeDice, fourDice, fiveDice, sixDice;
    private ImageIcon firstInfo, secondInfo, thirdInfo, forthInfo;
    private Image[] diceImage;
    private Image firstDice, secondDice;
    private JButton diceButton, turnOverButton;
    private JLabel doubleLabel;
    private Dice dice;
    private JFrame frame;
    private JButton[] buttons;
    private Board[] boards;
    private ArrayList<int[]> playersPosition = new ArrayList<>();
    private int[] airPlanePosition;
    private int playerCount;
    private PlayerPanelView firstPlayerPanel, secondPlayerPanel, thirdPlayerPanel, forthPlayerPanel;
    private PlayerPanelView[] playerPanelViews;
    private Bank bank;
    private Player[] players;
    private int turn;
    private ArrayList<Integer> bankruptPlayer;

    public GameBoardView(JFrame frame, Dice dice, Bank bank)
    {
        this.bank = bank;
        this.frame = frame;
        this.dice = dice;
        this.players = bank.getPlayers();
        buttons = new JButton[36];
        setLayout(null);
        makeButtons();
        loadImage();
        initializePosArray();
        initializePanel(bank.playerCount);
        bankruptPlayer = new ArrayList<>();

        diceButton = new JButton("주사위 굴리기");
        diceButton.setBounds(720, 820, 130, 40);

        doubleLabel = new JLabel();
        doubleLabel.setBounds(590, 765, 130, 40);
        doubleLabel.setVisible(false);

        turnOverButton = new JButton("Turn Over");
        turnOverButton.setEnabled(false);
        turnOverButton.setBounds(170, 820, 130, 40);

        add(turnOverButton);
        add(doubleLabel);
        add(diceButton);
    }

    public PlayerPanelView[] getPlayerPanelViews()
    {
        return playerPanelViews;
    }

    public void initializePanel(int playerCount)
    {
        switch (playerCount)
        {
            case 4:
                forthPlayerPanel = new PlayerPanelView(players[3]);
                forthPlayerPanel.setBounds(550, 450, 300, 300);
                add(forthPlayerPanel);

            case 3:
                thirdPlayerPanel = new PlayerPanelView(players[2]);
                thirdPlayerPanel.setBounds(150, 450, 300, 300);
                add(thirdPlayerPanel);

            case 2:
                secondPlayerPanel = new PlayerPanelView(players[1]);
                secondPlayerPanel.setBounds(550, 150, 300, 300);
                firstPlayerPanel = new PlayerPanelView(players[0]);
                firstPlayerPanel.setBounds(150, 150, 300, 300);
                add(secondPlayerPanel);
                add(firstPlayerPanel);
                break;
        }
        playerPanelViews = new PlayerPanelView[] { firstPlayerPanel, secondPlayerPanel, thirdPlayerPanel, forthPlayerPanel };
    }

    public void setPlayerCount(int playerCount)
    {
        this.playerCount = playerCount;
        playersPosition = new ArrayList<>();
        initializePos(playerCount);
    }

    public void initializePos(int playerCount)
    {
        for(int i = 0; i < playerCount; i++) { playersPosition.add(new int[] { 0, 0 }); }
        switch (playerCount)
        {
            case 4:
                playersPosition.set(3, new int[] { 950, 950 });
            case 3:
                playersPosition.set(2, new int[] { 920, 950 });
            case 2:
                playersPosition.set(1, new int[] { 950, 920 });
            case 1:
                playersPosition.set(0, new int[] { 920, 920 });
                break;
        }
    }

    public void setTurn(int turn)
    {
        this.turn = turn;
    }

    public void paintComponent(Graphics g)
    {
        paintBackGrounds(g);
        paintPlayers(g);
        paintDice(g);
        setTurnImage(g);
    }

    public void setTurnImage(Graphics g)
    {
        g.drawImage(bigAirPlaneImage[turn], 475, 800, this);
    }

    public void setButtonColor(Color c, int index)
    {
        buttons[index - (1 + (index / 10))].setBackground(c);
    }

    public void paintBackGrounds(Graphics g)
    {
        Image image = new ImageIcon("imageFiles/BlueMarble.jpg").getImage();
        g.drawImage(image, 0, 0, this);
    }

    public void initializePosArray()
    {
        airPlanePosition = new int[11];
        airPlanePosition[0] = 920;
        for(int i = 1; i < 10; i++)
        {
            airPlanePosition[i] = (airPlanePosition[0] - 100) - ((i - 1) * 83);
        }
        airPlanePosition[10] = airPlanePosition[9] - 150;
    }

    public void paintPlayers(Graphics g)
    {
        for(int i = 0; i < playerCount; i++)
        {
            if(!(bankruptPlayer.contains(i)))
            {
                g.drawImage(smallAirPlaneImage[i], playersPosition.get(i)[0], playersPosition.get(i)[1], this);
            }
        }
    }

    public void setBankruptPlayer(int turn)
    {
        if(!(bankruptPlayer.contains(turn)))
        {
            bankruptPlayer.add(turn);
        }
    }

    public void paintDice(Graphics g)
    {
        g.drawImage(firstDice, 590, 800, this);
        g.drawImage(secondDice, 650, 800, this);
    }

    public void moveAirPlane(int length, int position, int player)
    {
        for(int i = 1; i <= length; i++)
        {
            try
            {
                moveAirplane(player, (position + i) % 40);
            }
            catch (Exception e) { }
        }
    }

    private void moveAirplane(int player, int position)
    {
        int tmpX = position / 10;
        int tmpY = position % 10;
        int posX, posY;
        if(tmpX % 2 == 0)
        {
            posX = tmpX == 0 ? airPlanePosition[tmpY] : airPlanePosition[10 - tmpY];
            posY = tmpX == 0 ? airPlanePosition[0] : airPlanePosition[10];
        }
        else
        {
            posX = tmpX == 1 ? airPlanePosition[10] : airPlanePosition[0];
            posY = tmpX == 1 ? airPlanePosition[tmpY] : airPlanePosition[10 - tmpY];
        }

        switch (player)
        {
            case 0:
                playersPosition.set(player, new int[] { posX, posY });
                break;

            case 1:
                playersPosition.set(player, new int[] { posX + 20, posY });
                break;

            case 2:
                playersPosition.set(player, new int[] { posX, posY + 20 });
                break;

            case 3:
                playersPosition.set(player, new int[] { posX + 20, posY + 20 });
                break;
        }
    }

    public void setDoubleLabelText(int count)
    {
        doubleLabel.setText("한번 더 던지기 " + count + " 번");
    }

    public void setDice(int first, int second)
    {
        firstDice = diceImage[first - 1];
        secondDice = diceImage[second - 1];
        if(firstDice == secondDice)
        {
            doubleLabel.setVisible(true);
        }
        else
        {
            doubleLabel.setVisible(false);
        }
        repaint();
    }

    public void setBoards(Board[] boards)
    {
        this.boards = boards;
    }

    public void loadImage()
    {
        smallRedAirPlane = new ImageIcon("imageFiles/smallRedAirplane.png").getImage();
        smallBlueAirPlane = new ImageIcon("imageFiles/smallBlueAirplane.png").getImage();
        smallWhiteAirPlane = new ImageIcon("imageFiles/smallWhiteAirplane.png").getImage();
        smallYellowAirPlane = new ImageIcon("imageFiles/smallYellowAirplane.png").getImage();
        smallAirPlaneImage = new Image[] { smallRedAirPlane, smallBlueAirPlane, smallWhiteAirPlane, smallYellowAirPlane };

        bigRedAirPlane = new ImageIcon("imageFiles/bigRedAirPlane.png").getImage();
        bigBlueAirPlane = new ImageIcon("imageFiles/bigBlueAirPlane.png").getImage();
        bigWhiteAirPlane = new ImageIcon("imageFiles/bigWhiteAirPlane.png").getImage();
        bigYellowAirPlane = new ImageIcon("imageFiles/bigYellowAirPlane.png").getImage();
        bigAirPlaneImage = new Image[] { bigRedAirPlane, bigBlueAirPlane, bigWhiteAirPlane, bigYellowAirPlane };
        oneDice = new ImageIcon("imageFiles/oneDice.png").getImage();
        twoDice = new ImageIcon("imageFiles/twoDice.png").getImage();
        threeDice = new ImageIcon("imageFiles/threeDice.png").getImage();
        fourDice = new ImageIcon("imageFiles/fourDice.png").getImage();
        fiveDice = new ImageIcon("imageFiles/fiveDice.png").getImage();
        sixDice = new ImageIcon("imageFiles/sixDice.png").getImage();
        firstInfo = new ImageIcon("imageFiles/firstInfo.png");
        secondInfo = new ImageIcon("imageFiles/secondInfo.png");
        thirdInfo = new ImageIcon("imageFiles/thirdInfo.png");
        forthInfo = new ImageIcon("imageFiles/forthInfo.png");
        diceImage = new Image[] { oneDice, twoDice, threeDice, fourDice, fiveDice, sixDice };
    }

    public JButton getDiceButton()
    {
        return diceButton;
    }

    public JButton getTurnOverButton()
    {
        return turnOverButton;
    }

    public void makeButtons()
    {
        for(int i = 0; i < 36; i++)
        {
            JButton button = new JButton();
            int tmp = i / 9;
            switch (tmp)
            {
                case 0:
                    button.setBounds(791 - ((i % 9) * 83), 873, 83, 33);
                    break;

                case 1:
                    button.setBounds(95, 792 - ((i % 9) * 83), 30, 83);
                    break;

                case 2:
                    button.setBounds(127 + ((i % 9) * 83), 95, 83, 33);
                    break;

                case 3:
                    button.setBounds(873, 128 + ((i % 9) * 83), 30, 83);
                    break;
            }
            button.addActionListener(new ButtonListener());
            buttons[i] = button;
            this.add(button);
        }
    }

    public JButton[] getButtons()
    {
        return buttons;
    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int tmp = 0;
            for(int i = 0; i < 36; i++)
            {
                if(e.getSource() == buttons[i])
                {
                    tmp = i + 1 + (i / 9);
                    break;
                }
            }
            StatusView statusView = new StatusView(frame, "상세 정보 보기", (Country) boards[tmp]);
            statusView.setVisible(true);
        }
    }
}