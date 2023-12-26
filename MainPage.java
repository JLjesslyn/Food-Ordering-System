package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame implements ActionListener {
    //Get screen's size
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();
    
    //JFrames
    private Container c;
    private JLabel title;
    private JLabel imageLabel;
    private JButton orderButton;
    private Menu menu;
    
    public MainPage() {
        //Program's Title and run at middle of the screen
        setTitle("Food Ordering System");
        setBounds((screenWidth/2 - 360), (screenHeight/2 - 270), 720, 540);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        c = getContentPane();
        c.setLayout(null);
        
        //Title Label
        title = new JLabel("Happy Cafe");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(285, 30);
        c.add(title);
        
        //Image for homepage
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("Images/logo.jpg"));
        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(160, 60, 400, 300);
        c.add(imageLabel);
        
        //Start to place order
        orderButton = new JButton("Place Order");
        orderButton.setFont(new Font("Arial", Font.PLAIN, 20));
        orderButton.setSize(200, 50);
        orderButton.setLocation(260, 400);
        orderButton.addActionListener(this);
        c.add(orderButton);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {
            //Close homepage and run menu
            dispose();
            menu = new Menu();
        }
    }
    
    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            try {
                //Run the program
                MainPage main = new MainPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
