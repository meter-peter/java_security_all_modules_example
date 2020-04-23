package cardsaver.frontend.GUI;

import cardsaver.frontend.FrontendManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginRegister extends JFrame {
    FrontendManager frontendManager;

    public LoginRegister (FrontendManager frontendManager) {
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.frontendManager = frontendManager;
        setTitle("Please Register To Continue");
        //init comps
        JLabel firstLabel = new JLabel("First Name");
        JTextField firstTextfield = new JTextField();
        JLabel lastLabel = new JLabel("Last Name");
        JTextField lastTextfield = new JTextField();
        JLabel emailLabel = new JLabel("Email");
        JTextField emailTextfield = new JTextField();
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameTextfield = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        JTextField passwordTextfield = new JTextField();
        JLabel confirmLabel = new JLabel("Password Confirmation");
        JTextField confirmTextfield = new JTextField();

        //init register panel
        JPanel registerpanel = new JPanel(new GridLayout(7,2));
        registerpanel.add(firstLabel);
        registerpanel.add(firstTextfield);
        registerpanel.add(lastLabel);
        registerpanel.add(lastTextfield);
        registerpanel.add(emailLabel);
        registerpanel.add(emailTextfield);
        registerpanel.add(usernameLabel);
        registerpanel.add(usernameTextfield);
        registerpanel.add(passwordLabel);
        registerpanel.add(passwordTextfield);
        registerpanel.add(confirmLabel);
        registerpanel.add(confirmTextfield);
        JButton submitregister = new JButton("Submit Register");
        JLabel changetologin = new JLabel("Or Click here to Log in");
        registerpanel.add(submitregister);
        registerpanel.add(changetologin);

        //init login panel
        JPanel loginpanel = new JPanel(new GridLayout(2,4));
        loginpanel.add(usernameLabel);
        loginpanel.add(usernameTextfield);
        loginpanel.add(passwordLabel);
        loginpanel.add(passwordTextfield);
        Button loginbtn = new Button("Enter");
        loginpanel.add(loginbtn);
        JLabel returnregister = new JLabel("Get me back to register");
        loginpanel.add(returnregister);
        loginpanel.setVisible(true);
        registerpanel.setVisible(true);

        JPanel content = new JPanel(new CardLayout());
        content.add(registerpanel,"Register");
        content.add(loginpanel,"Login");
        content.setVisible(true);
        add(content);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        CardLayout cl = (CardLayout)(content.getLayout());
        cl.show(content, "Register");
        pack();

        changetologin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cl.show(content,"Login");
                setTitle("Login");

            }
        });

        returnregister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cl.show(content,"Register");
                setTitle("Register");
            }
        });

        submitregister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(passwordTextfield.equals(confirmTextfield)){
                frontendManager.register(usernameTextfield.getText(),firstTextfield.getText(),lastTextfield.getText(),emailTextfield.getText(),passwordTextfield.getText());

            }
            else {
                JOptionPane.showMessageDialog(content, "Passwords do not match");

            }}
        });
    }
    public void onregister(){

    }




}
