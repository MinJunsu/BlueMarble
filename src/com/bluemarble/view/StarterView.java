package com.bluemarble.view;

import com.bluemarble.controller.GameStarter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StarterView extends JFrame
{
    private JRadioButton rb1, rb2, rb3;
    private JLabel firstLabel, secondLabel, thirdLabel, forthLabel;
    private JTextField firstTextField, secondTextField, thirdTextField, forthTextField;
    private int count = 2;

    public StarterView()
    {
        super("게임 시작");
        Container cp = getContentPane();
        cp.setLayout(null);

        rb1 = new JRadioButton("2 인용");
        rb2 = new JRadioButton("3 인용");
        rb3 = new JRadioButton("4 인용");
        rb1.setSelected(true);
        rb1.addActionListener(new RadioButtonListener());
        rb2.addActionListener(new RadioButtonListener());
        rb3.addActionListener(new RadioButtonListener());

        rb1.setBounds(20, 20, 80, 20);
        rb2.setBounds(110, 20, 80, 20);
        rb3.setBounds(200, 20, 80, 20);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);

        firstLabel = new JLabel("첫 번째 플레이어 이름 ");
        firstTextField = new JTextField();
        secondLabel = new JLabel("두 번째 플레이어 이름 ");
        secondTextField = new JTextField();
        thirdLabel = new JLabel("세 번째 플레이어 이름 ");
        thirdTextField = new JTextField();
        forthLabel = new JLabel("네 번째 플레이어 이름 ");
        forthTextField = new JTextField();

        firstLabel.setBounds(20, 60, 150, 20);
        secondLabel.setBounds(20, 90, 150, 20);
        thirdLabel.setBounds(20, 120, 150, 20);
        forthLabel.setBounds(20, 150, 150, 20);
        firstTextField.setBounds(160, 60, 100, 20);
        secondTextField.setBounds(160, 90, 100, 20);
        thirdTextField.setBounds(160, 120, 100, 20);
        forthTextField.setBounds(160, 150, 100, 20);

        thirdLabel.setVisible(false);
        forthLabel.setVisible(false);
        thirdTextField.setVisible(false);
        forthTextField.setVisible(false);

        JButton button = new JButton("START");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String[] playerNames;
                switch (count)
                {
                    case 2:
                        new GameStarter(2, new String[] { firstTextField.getText(), secondTextField.getText() });
                        dispose();
                        break;

                    case 3:
                        new GameStarter(3, new String[] { firstTextField.getText(), secondTextField.getText(), thirdTextField.getText() });
                        dispose();
                        break;

                    case 4:
                        new GameStarter(4, new String[] { firstTextField.getText(), secondTextField.getText(), thirdTextField.getText(), firstTextField.getText() });
                        dispose();
                        break;
                }
            }
        });
        button.setBounds(20, 180, 250, 30);

        cp.add(rb1);
        cp.add(rb2);
        cp.add(rb3);
        cp.add(firstLabel);
        cp.add(secondLabel);
        cp.add(thirdLabel);
        cp.add(forthLabel);
        cp.add(firstTextField);
        cp.add(secondTextField);
        cp.add(thirdTextField);
        cp.add(forthTextField);
        cp.add(button);

        setSize(300, 260);
        setVisible(true);
    }

    class RadioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(((JRadioButton) e.getSource()) == rb1)
            {
                thirdLabel.setVisible(false);
                forthLabel.setVisible(false);
                thirdTextField.setVisible(false);
                forthTextField.setVisible(false);
                count = 2;
            }
            else if(((JRadioButton) e.getSource()) == rb2)
            {
                thirdLabel.setVisible(true);
                forthLabel.setVisible(false);
                thirdTextField.setVisible(true);
                forthTextField.setVisible(false);
                count = 3;
            }
            else if(((JRadioButton) e.getSource()) == rb3)
            {
                thirdLabel.setVisible(true);
                forthLabel.setVisible(true);
                thirdTextField.setVisible(true);
                forthTextField.setVisible(true);
                count = 4;
            }
        }
    }
}