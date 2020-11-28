package com.bluemarble.view;

import com.bluemarble.model.Board;
import com.bluemarble.model.Country;
import com.bluemarble.model.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameBoardView extends JPanel
{
    private Image redAirPlane, blueAirPlane, blackAirPlane, yellowAirPlane;
    private Image[] airPlaneImage;
    private Image oneDice, twoDice, threeDice, fourDice, fiveDice, sixDice;
    private ImageIcon firstInfo, secondInfo, thirdInfo, forthInfo;
    private Image[] diceImage;
    private Image firstDice, secondDice;
    private JButton diceButton, turnOverButton;
    private JLabel turnLabel, doubleLabel;
    private Dice dice;
    private JFrame frame;
    private JButton[] buttons;
    private Board[] boards;
    private ArrayList<int[]> playersPosition = new ArrayList<>();
    private int[] airPlanePosition;
    private int playerCount;

    public GameBoardView(JFrame frame, Dice dice)
    {
        this.frame = frame;
        this.dice = dice;
        buttons = new JButton[36];
        setLayout(null);
        makeButtons();
        loadImage();
        initializePosArray();

        diceButton = new JButton("주사위 굴리기");
        diceButton.setBounds(720, 820, 130, 40);

        turnLabel = new JLabel("Player 1 TURN");
        turnLabel.setBounds(400, 200, 200, 100);

        doubleLabel = new JLabel();
        doubleLabel.setBounds(590, 765, 130, 40);
        doubleLabel.setVisible(false);

        turnOverButton = new JButton("Turn Over");
        turnOverButton.setEnabled(false);
        turnOverButton.setBounds(170, 820, 130, 40);

        add(turnOverButton);
        add(doubleLabel);
        add(turnLabel);
        add(diceButton);
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

    public void paintComponent(Graphics g)
    {
        paintBackGrounds(g);
        paintPlayers(g);
        paintDice(g);
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
            g.drawImage(airPlaneImage[i], playersPosition.get(i)[0], playersPosition.get(i)[1], this);
        }
    }

    public void setTurnLabelText(int turn)
    {
        turnLabel.setText("Player " + turn + " TURN");
    }

    public void paintDice(Graphics g)
    {
        g.drawImage(firstDice, 590, 800, this);
        g.drawImage(secondDice, 650, 800, this);
    }

    public void moveAirPlane(int length, int position, int player)
    {
        System.out.println(player);
        for(int i = 1; i <= length; i++)
        {
            try
            {
                moveAirplane(player, (position + i) % 40);
                this.repaint();
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
        redAirPlane = new ImageIcon("imageFiles/redAirplane.png").getImage();
        blueAirPlane = new ImageIcon("imageFiles/blueAirplane.png").getImage();
        blackAirPlane = new ImageIcon("imageFiles/blackAirplane.png").getImage();
        yellowAirPlane = new ImageIcon("imageFiles/yellowAirplane.png").getImage();
        airPlaneImage = new Image[] { redAirPlane, blueAirPlane, blackAirPlane, yellowAirPlane };
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