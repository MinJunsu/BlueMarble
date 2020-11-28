package com.bluemarble.view;

import com.bluemarble.model.Country;

import javax.swing.*;

public class StatusView extends JDialog
{
    public StatusView(JFrame frame, String title, Country country)
    {
        super(frame, title);
        setBounds(300, 400, 400, 300);
        StatusPanel panel = new StatusPanel(country);
        add(panel);
    }
}
