package cardsaver.frontend.GUI;

import cardsaver.frontend.FrontendManager;

import javax.swing.*;
import java.awt.*;

public class LoginRegister extends JFrame {
    FrontendManager frontendManager;

    public LoginRegister (FrontendManager frontendManager) {
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.frontendManager = frontendManager;
        JLabel state = new JLabel("");
        add(state);
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
        JPanel registerpanel = new JPanel(new GridLayout(2,6));
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

        //init login panel
        JPanel loginpanel = new JPanel(new GridLayout(2,2));
        loginpanel.add(usernameLabel);
        loginpanel.add(usernameTextfield);
        loginpanel.add(passwordLabel);
        loginpanel.add(passwordTextfield);
        loginpanel.setVisible(true);
        registerpanel.setVisible(true);

        JPanel content = new JPanel(new CardLayout());
        content.add(registerpanel,"Register");
        content.add(loginpanel,"Login");
        loginpanel.show();
        content.setVisible(true);
        add(content);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();




    }
}
