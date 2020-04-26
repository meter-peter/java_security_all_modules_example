package cardsaver.frontend.GUI;

import cardsaver.Controller;
import cardsaver.auth.Account;
import cardsaver.frontend.CreditCard;
import cardsaver.frontend.FrontendManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class WalletGUI extends JFrame {
   JSplitPane splitPane;
    JList<CreditCard> creditCardJList;
    DefaultListModel<CreditCard> model;
    JComboBox<String> typescombobox;
    ArrayList<CreditCard> list;
    FrontendManager frontendManager;
    CreditCard selectedCard;

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




        actions.add(typescombobox);
        actions.add(addbutton);
        actions.add(editbutton);
        actions.add(deletebutton);
        splitPane.setLeftComponent(actions);
        splitPane.setRightComponent(new JScrollPane(creditCardJList));
        splitPane.setVisible(true);
        add(splitPane);
        setSize(1024,768);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        editbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               CardEditPanel cardEditPanel = new CardEditPanel(frontendManager,selectedCard.getOwner(),selectedCard.getNumber(),selectedCard.getExpirationDate(),selectedCard.getCvv(),selectedCard.getType());
              cardEditPanel.setData(selectedCard.getOwner(),selectedCard.getNumber(),selectedCard.getExpirationDate(),selectedCard.getCvv());

            }
        });


        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CardEditPanel(frontendManager);
            }
        });

        typescombobox.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatelist(list);
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



