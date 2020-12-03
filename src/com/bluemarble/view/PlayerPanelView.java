package com.bluemarble.view;

import com.bluemarble.model.Bank;
import com.bluemarble.model.Country;
import com.bluemarble.model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPanelView extends JPanel
{
    private Player player;
    private DefaultTableModel Model;
    private JTable TradingView;
    private JLabel playerName, playerBalance;
    private JPanel tablePanel;
    private ArrayList<JCheckBox> checkBoxes;
    private DefaultTableCellRenderer renderer;

    public PlayerPanelView(Player p)
    {
        this.player = p;
        renderer = new MyDefaultTableCellRenderer();
        setLayout(null);
        setBackground(new Color(146, 209, 79));
        makeTable();

        playerName = new JLabel("이름 : " + player.getName());
        playerBalance = new JLabel();
        playerName.setBounds(10, 10, 100, 30);
        playerBalance.setBounds(10, 40, 100, 30);
        tablePanel.setBounds(10, 75, 280, 180);

        JButton saleButton = new JButton("선택 항목 판매");
        saleButton.setBounds(16, 265, 268, 25);
        saleButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(int i = 0; i < TradingView.getRowCount(); i++)
                {
                    if(((Boolean) TradingView.getValueAt(i, 0)))
                    {
                    	for (int j = 0; j < player.getCountries().size(); j++)
                    	{
                    		if (player.getCountries().get(j).getName().equals((String) TradingView.getValueAt(i, 1)))
                    		{
                    			player.saleCountry(player.getCountries().get(j), 100);
                    			Model.removeRow(i);
                                Model.fireTableDataChanged();
                                setPlayerBalance();
                    		}
                    	}
                    }
                }
            }
        });

        this.add(saleButton);
        this.add(playerName);
        this.add(playerBalance);
        this.add(tablePanel);
        updatePlayer();
    }

    public void updatePlayer()
    {
        setPlayerBalance();
        makeModel();
    }

    public void makeTable()
    {
        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(146, 209, 79));
        String header[] = { "선택", "국가명", "총 가격", "빌라", "빌딩", "호텔"} ;
        Model = new DefaultTableModel(header, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column == 0)
                {
                    return true;
                }
                return false;
            }
        };
        TradingView = new JTable();
        TradingView.setModel(Model);
        TradingView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TradingView.setPreferredScrollableViewportSize(new Dimension(260, 140));
        TradingView.setRowSelectionAllowed(true);
        TradingView.getColumnModel().getColumn(0).setPreferredWidth(28);
        TradingView.getColumnModel().getColumn(1).setPreferredWidth(65);
        TradingView.getColumnModel().getColumn(2).setPreferredWidth(65);
        TradingView.getColumnModel().getColumn(3).setPreferredWidth(35);
        TradingView.getColumnModel().getColumn(4).setPreferredWidth(35);
        TradingView.getColumnModel().getColumn(5).setPreferredWidth(35);
        tablePanel.add(TradingView);
        tablePanel.add(new JScrollPane(TradingView));
    }

    public void makeModel()
    {
        Model.setNumRows(0);
        ArrayList<Country> countries = player.getCountries();
        for(Country c : countries)
        {
            System.out.println(c.getName());
            TradingView.getColumn("선택").setCellRenderer(renderer);
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(JLabel.CENTER);
            TradingView.getColumn("선택").setCellEditor(new DefaultCellEditor(checkBox));
            Model.addRow(new Object[] { false, c.getName(), c.getTotalPrice(), c.getVillaCount(), c.getBuildingCount(), c.getHotelCount() });
        }
    }

    public void setPlayerBalance()
    {
        playerBalance.setText("잔액 : " + player.getBalance() + " 원");
    }
}

class MyDefaultTableCellRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column)
    {
        JCheckBox box = new JCheckBox();
        box.setSelected(((Boolean) value).booleanValue());
        box.setHorizontalAlignment(JLabel.CENTER);
        return box;
    }
}