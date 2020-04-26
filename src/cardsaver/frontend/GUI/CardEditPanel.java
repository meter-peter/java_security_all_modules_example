package cardsaver.frontend.GUI;

import cardsaver.frontend.FrontendManager;

import javax.swing.*;
import java.awt.*;

public class CardEditPanel {

   FrontendManager frontendManager;
   JTextField namefield;
   JTextField numberfield;
   JTextField datetextfield;
   JTextField cvvfielf;
   JComboBox<String> types;
   JPanel myPanel;
   String ownerdata;
   String numberdata;
   String datedata;
   String cvvdata;
   String typedata;
   WalletGUI WalletGUI;

   public void setData(String owner , String number , String Date , String cvv){
       namefield.setText(owner);
       namefield.setVisible(true);
       numberfield.setText(number);
       numberfield.setVisible(true);
       datetextfield.setText(Date);
       datetextfield.setVisible(true);
       cvvfielf.setText(cvv);
       cvvfielf.setVisible(true);
       myPanel.setVisible(true);

   }



   public CardEditPanel(FrontendManager frontendManager) throws Exception {
       this.frontendManager= frontendManager;
       JLabel name =new JLabel("Owner's Name");
       namefield = new JTextField();
       JLabel number = new JLabel("Card's Number");
       numberfield = new JTextField();
       JLabel date= new JLabel("Expiration Date(Any Format)");
       datetextfield= new JTextField();
       JLabel cvv = new JLabel("CVV");
       cvvfielf = new JTextField();

       types = new JComboBox<>();
       types.addItem("VISA");
       types.addItem("MASTERCARD");


       myPanel = new JPanel(new GridLayout(5,1));
       myPanel.add(name);
       myPanel.add(namefield);
       myPanel.add(number);
       myPanel.add(numberfield);
       myPanel.add(date);
       myPanel.add(datetextfield);
       myPanel.add(cvv);
       myPanel.add(cvvfielf);
       myPanel.add(types);

       int result = JOptionPane.showConfirmDialog(null, myPanel,
               "Add A new Card", JOptionPane.OK_CANCEL_OPTION);
       if (result == JOptionPane.OK_OPTION) {
           frontendManager.addCard(numberfield.getText(),namefield.getText(),cvvfielf.getText(),types.getSelectedItem().toString(),datetextfield.getText());

       }

   }

   public CardEditPanel(WalletGUI WalletGUI ,FrontendManager frontendManager,String ownerdata , String numberdata , String datedata , String cvvdata , String typedata) throws Exception {
       this.ownerdata=ownerdata;
       this.numberdata=numberdata;
       this.datedata=datedata;
       this.cvvdata=cvvdata;
       this.frontendManager= frontendManager;
       this.typedata=typedata;
       this.WalletGUI=WalletGUI;
       JLabel name =new JLabel("Owner's Name");
       namefield = new JTextField(ownerdata);
       JLabel number = new JLabel("Card's Number");
       numberfield = new JTextField(numberdata);
       JLabel date= new JLabel("Expiration Date(Any Format)");
       datetextfield= new JTextField(datedata);
       JLabel cvv = new JLabel("CVV");
       cvvfielf = new JTextField(cvvdata);
       types = new JComboBox<>();
       types.addItem("VISA");
       types.addItem("MASTERCARD");

       if(typedata.equals("VISA")){
           types.setSelectedIndex(0);
       }else{
           types.setSelectedIndex(1);
       }


       myPanel = new JPanel(new GridLayout(5,1));
       myPanel.add(name);
       myPanel.add(namefield);
       myPanel.add(number);
       myPanel.add(numberfield);
       myPanel.add(date);
       myPanel.add(datetextfield);
       myPanel.add(cvv);
       myPanel.add(cvvfielf);
       myPanel.add(types);

       int result = JOptionPane.showConfirmDialog(null, myPanel,
               "Card Editor", JOptionPane.OK_CANCEL_OPTION);
       if (result == JOptionPane.OK_OPTION) {
                frontendManager.edit(namefield.getText(),numberfield.getText(),datetextfield.getText(),cvvfielf.getText(),types.getSelectedItem().toString(),WalletGUI.selectedCard.getId());
       }
   }
}
