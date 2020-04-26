package cardsaver.frontend.GUI;

import cardsaver.Controller;
import cardsaver.auth.Account;
import cardsaver.frontend.CreditCard;
import cardsaver.frontend.FrontendManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


public class WalletGUI extends JFrame {
   JSplitPane splitPane;
    JList<CreditCard> creditCardJList;
    DefaultListModel<CreditCard> model;
    JComboBox<String> typescombobox;
    ArrayList<CreditCard> list;
    FrontendManager frontendManager;
    CreditCard selectedCard;
    WalletGUI clone;

    public WalletGUI(FrontendManager frontendManager){
        setTitle("CardManager Panel");
        //setVisible(true);
        this.frontendManager=frontendManager;

        splitPane = new JSplitPane();
        model = new DefaultListModel<>();
        creditCardJList = new JList<>(model);
        JPanel actions = new JPanel(new FlowLayout());
        typescombobox = new JComboBox();
        JLabel filter = new JLabel("Filter");
        actions.add(filter);
        typescombobox.addItem("VISA");
        typescombobox.addItem("MASTERCARD");
        JButton addbutton = new JButton("Add Card");
        addbutton.setBackground(Color.cyan);
        JButton editbutton = new JButton("Edit Selected Card");
        editbutton.setBackground(Color.GREEN);
        JButton deletebutton = new JButton("Delete Selected Card");
        deletebutton.setBackground(Color.RED);
        JButton exit = new JButton("Exit SAFELY NOW");
        exit.setBackground(Color.white);


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    frontendManager.finalmethod();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        actions.add(typescombobox);
        actions.add(addbutton);
        actions.add(editbutton);
        actions.add(deletebutton);
        actions.add(exit);
        splitPane.setLeftComponent(actions);
        splitPane.setRightComponent(new JScrollPane(creditCardJList));
        splitPane.setVisible(true);
        add(splitPane);
        setSize(1024,768);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        clone = this;

        editbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    CardEditPanel cardEditPanel = new CardEditPanel(clone,frontendManager,selectedCard.getOwner(),selectedCard.getNumber(),selectedCard.getExpirationDate(),selectedCard.getCvv(),selectedCard.getType());
                } catch (Exception e) {}

            }
        });

        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    frontendManager.delete(selectedCard.getId());
                } catch (Exception e) {
                }
            }
        });

        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new CardEditPanel(frontendManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        typescombobox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatelist(list);
            }
        });

       this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                try {
                    //frontendManager.finalmethod();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Runtime.getRuntime().exec("taskkill /f /im java.exe");
                } catch (IOException e4) {

                    e4.printStackTrace();
                }

            }
        });

    }

    public void updatelist (ArrayList<CreditCard> list){
        this.list=list;

        model.clear();
        int x =0 ;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getType().equals(typescombobox.getSelectedItem())){
                model.add(x,list.get(i));
                x++;
            }
        }
        creditCardJList.getSelectionModel().addListSelectionListener(e -> {
            selectedCard = creditCardJList.getSelectedValue();
        });

    }





}



