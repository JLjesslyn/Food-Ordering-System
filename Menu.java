package Project;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Menu extends JFrame {
    //Get screen's size
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();
    
    //Saved items for receipt
    private List<String> selectedItems = new ArrayList<>();
    private List<String> selectedItemsName = new ArrayList<>();
    private List<Double> selectedPrices = new ArrayList<>();
    private List<Integer> selectedQuantities = new ArrayList<>();
    
    //JFrames
    private Container c;
    private JTabbedPane menu;
    private JPanel food;
    private JPanel drinks;
    private JPanel desserts;
    private GridBagConstraints gbc;
    private JTextArea receiptArea;
    private JButton bPrint;
    private JButton bReset;
    
    //Text for receipt
    private String receiptText;
    
    public Menu() {
        //Program's Title and run at middle of the screen
        setTitle("Menu");
        setBounds((screenWidth/2 - 500), (screenHeight/2 - 360), 1000, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        c = getContentPane();
        c.setLayout(null);
        
        //Tabs for food, drinks, desserts
        menu = new JTabbedPane();
        menu.setBounds(20, 15, 600, 650);
        
        //Panel under the food tab
        food = new JPanel();
        food.setLayout(new GridLayout(4, 4, 0, 0));
        //Food's items
        food.add(createFoodPanel("F001", "Salad", "6.00"));
        food.add(createFoodPanel("F002", "Spaghetti", "16.00"));
        food.add(createFoodPanel("F003", "Chicken Rice", "5.00"));
        food.add(createFoodPanel("F004", "Sandwich", "6.00"));
        food.add(createFoodPanel("F005", "Noodles", "7.00"));
        food.add(createFoodPanel("F006", "Burger", "7.50"));
        food.add(createFoodPanel("F007", "Ramen", "8.50"));
        food.add(createFoodPanel("F008", "Udon", "10.00"));
        food.add(createFoodPanel("F009", "Char Kuey Teow", "6.00"));
        food.add(createFoodPanel("F010", "Laksa", "6.50"));
        food.add(createFoodPanel("F011", "Nasi Lemak", "4.00"));
        food.add(createFoodPanel("F012", "Pizza", "20.00"));
        food.add(createFoodPanel("F013", "Chicken Chop", "18.00"));
        food.add(createFoodPanel("F014", "Fried Mihun", "5.50"));
        food.add(createFoodPanel("F015", "Fried Chicken", "4.00"));
        food.add(createFoodPanel("F016", "Satay", "3.00"));
        
        //Panel under the drinks tab
        drinks = new JPanel();
        drinks.setLayout(new GridLayout(4, 4, 0, 0));
        //Drinks' items
        drinks.add(createFoodPanel("D001", "Milk Tea", "4.50"));
        drinks.add(createFoodPanel("D002", "Chocolate Milkshake", "4.00"));
        drinks.add(createFoodPanel("D003", "Pepsi", "2.50"));
        drinks.add(createFoodPanel("D004", "Coke", "2.50"));
        drinks.add(createFoodPanel("D005", "Orange Juice", "5.00"));
        drinks.add(createFoodPanel("D006", "Iced Coffee", "8.00"));
        drinks.add(createFoodPanel("D007", "Ice Lemon tea", "5.50"));
        drinks.add(createFoodPanel("D008", "Latte", "8.50"));
        drinks.add(createFoodPanel("D009", "Green Tea", "7.00"));
        drinks.add(createFoodPanel("D010", "Sparkling Water", "3.00"));
        drinks.add(createFoodPanel("D011", "Hot Chocolate", "5.00"));
        drinks.add(createFoodPanel("D012", "Fruit Smoothie", "6.00"));
        drinks.add(createFoodPanel("D013", "Mocha", "9.90"));
        drinks.add(createFoodPanel("D014", "Cappuccino", "9.90"));
        drinks.add(createFoodPanel("D015", "Milo", "4.00"));
        drinks.add(createFoodPanel("D016", "Teh Tarik", "4.00"));
        
        //Panel under the desserts tab
        desserts = new JPanel();
        desserts.setLayout(new GridLayout(4, 4, 0, 0));
        //Desserts' items
        desserts.add(createFoodPanel("DE001", "Cheese Cake", "12.00"));
        desserts.add(createFoodPanel("DE002", "Red Velvet Cake", "12.00"));
        desserts.add(createFoodPanel("DE003", "Oreo Ice-Cream", "4.00"));
        desserts.add(createFoodPanel("DE004", "Pancakes", "5.00"));
        desserts.add(createFoodPanel("DE005", "Lava Cake", "12.00"));
        desserts.add(createFoodPanel("DE006", "Pudding", "5.50"));
        desserts.add(createFoodPanel("DE007", "Apple Pie", "3.50"));
        desserts.add(createFoodPanel("DE008", "Brownie", "6.00"));
        desserts.add(createFoodPanel("DE009", "Cupcake", "7.00"));
        desserts.add(createFoodPanel("DE010", "Cookies", "4.60"));
        desserts.add(createFoodPanel("DE011", "Fruit Tart", "6.50"));
        desserts.add(createFoodPanel("DE012", "Cheese Tart", "6.50"));
        desserts.add(createFoodPanel("DE013", "Tiramisu", "12.00"));
        desserts.add(createFoodPanel("DE014", "Strawberry Shortcake", "12.00"));
        desserts.add(createFoodPanel("DE015", "Banana Split", "5.50"));
        desserts.add(createFoodPanel("DE016", "Macarons", "8.00"));
        
        menu.add("Food", food);
        menu.add("Drinks", drinks);
        menu.add("Desserts", desserts);
        
        c.add(menu);
        
        //Check receipt
        receiptArea = new JTextArea();
        receiptArea.setBounds(640, 35, 320, 580);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        c.add(receiptArea);
        
        //Print receipt as txt file and open it
        //then reset the receipt in the program
        bPrint = new JButton("Print");
        bPrint.setBounds(640, 630, 80, 30);
        bPrint.addActionListener(e -> {
            printReceiptToFile(receiptText);
            resetQuantities();
            clearSelectedItems();
            displayReceipt(0.0);
        });

	c.add(bPrint);
        
        //Reset the receipt
        bReset = new JButton("Reset");
        bReset.setBounds(730, 630, 80, 30);
        bReset.addActionListener(e -> {
            resetQuantities();
            clearSelectedItems();
            displayReceipt(0.0);
        });
        
	c.add(bReset);
        
        setVisible(true);
    }

    private JPanel createFoodPanel(String id, String itemName, String price) {
        //Get layout for food, drinks, desserts
        JPanel foodPanel = new JPanel(new BorderLayout());
        
        //set border for each items
        foodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        //Panel for images, id and name, prices for items
        JPanel itemLabelPanel = new JPanel(new BorderLayout());
        
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("Images/" + itemName.replace(" ", "_")+".jpg"));
        Image originalImage = imageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedImageIcon);
        JLabel itemLabel = new JLabel("<html><div style='text-align: center;'>" + id + ". " + itemName + "<br>" + "Price: RM" + price +"</div></html>");
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        itemLabelPanel.add(imageLabel, BorderLayout.CENTER);
        itemLabelPanel.add(itemLabel, BorderLayout.SOUTH);
        
        //Panel for quantity
        JPanel quantityPanel = new JPanel(new FlowLayout());
        
        JLabel quantityLabel = new JLabel("Quantity:");
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 99, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        
        JFormattedTextField input = ((JSpinner.DefaultEditor) quantitySpinner.getEditor()).getTextField();
        input.setEditable(false);
        input.setBackground(Color.WHITE);
        
        //Auto apply items to receipt when quantity is changed
        quantitySpinner.addChangeListener(e -> {
            addReceipt(id, itemName , (int) quantitySpinner.getValue(), Double.parseDouble(price));
        });
        
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);
        
        //Set the layout
        foodPanel.add(itemLabelPanel, BorderLayout.NORTH);
        foodPanel.add(quantityPanel, BorderLayout.SOUTH);
        
        return foodPanel;
    }
    
    private void addReceipt(String id, String itemName, int quantity, double price) {
        //Check index for the saved items
        int index = selectedItems.indexOf(id);
        //If getting quantity as 0, then remove the saved item
        //Else save the items if not existed, else changed the saved quantity
        if (quantity <= 0) {
            selectedItems.remove(index);
            selectedItemsName.remove(index);
            selectedPrices.remove(index);
            selectedQuantities.remove(index);
        } else {
            if (index != -1) {
                selectedQuantities.set(index, quantity);
            } else {
                selectedItems.add(id);
                selectedItemsName.add(itemName);
                selectedPrices.add(price);
                selectedQuantities.add(quantity);
            }
        }
        //Show up the receipt with calculatedPrice
        displayReceipt(calculateTotalPrice());
    }
    
    private double calculateTotalPrice() {
        double totalPrice = 0.0;

        for (int i = 0; i < selectedItems.size(); i++) {
            double price = selectedPrices.get(i);
            int quantity = selectedQuantities.get(i);
            totalPrice += price * quantity;
        }

        return totalPrice;
    }

    private void displayReceipt(double totalAmount) {
        //Clear the receipt if there is no any items existed
        if (selectedItems.isEmpty()) {
            receiptText = "";
            receiptArea.setText(receiptText);
            return;
        }
        
        receiptArea.setText("Receipt:\n\n");
        
        //Setting up the receipt
        receiptText = "============================================\n";
        receiptText+= "Items\n";
        receiptText+= "============================================\n";
        receiptText+= String.format("%-5s%-20s%-9s%-10s\n", "Qty", "Item", "Price", "Subtotal");
        for (int i = 0; i < selectedItems.size(); i++) {
            String id = selectedItems.get(i);
            String itemName = selectedItemsName.get(i);
            double price = selectedPrices.get(i);
            int quantity = selectedQuantities.get(i);
            double itemTotal = price * quantity;
            
            String[] itemNameLines = splitItemName(itemName);
            
            for (int j = 0; j < itemNameLines.length; j++) {
                if (j == 0) {
                    receiptText+= String.format("%-5d%-20s%-2s%-7.2f%-2s%-8.2f\n", quantity, itemNameLines[j], "RM" , price, "RM" , itemTotal);
                } else {
                    receiptText+= String.format("%-5s%-20s%-9s%-10s\n", "", itemNameLines[j], "", "");
                }
            }
        }
        receiptText+= "--------------------------------------------\n";
        receiptText+= String.format("Total Amount: RM%.2f", totalAmount);
        receiptArea.append(receiptText);
    }
    
    //Splitting the item's name if it is too long to display and return to receipt
    private String[] splitItemName(String itemName) {
        int maxLength = 18;
        List<String> lines = new ArrayList<>();
        String[] words = itemName.split("\\s+");
        StringBuilder currentLine = new StringBuilder();
        
        for (String word : words) {
            if (currentLine.length() + word.length() <= maxLength) {
                currentLine.append(word).append(" ");
            } else {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder(word + " ");
            }
        }
        
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString().trim());
        }
        return lines.toArray(new String[0]);
    }
    
    //Reset quantity to 0 for all items in the menu
    private void resetQuantities() {
        resetPanelQuantities(food);
        resetPanelQuantities(drinks);
        resetPanelQuantities(desserts);
        for (int i = 0; i < selectedQuantities.size(); i++) {
            selectedQuantities.set(i, 0);
        }
    }
    
    //Clear the saved items
    private void clearSelectedItems() {
        selectedItems.clear();
        selectedPrices.clear();
        selectedQuantities.clear();
    }
    
    //Method for resetQuantities() to set quantity to 0
    private void resetPanelQuantities(Container container) {
    Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                resetPanelQuantities((Container) component);
            } else if (component instanceof JSpinner) {
                JSpinner quantitySpinner = (JSpinner) component;
                SpinnerModel model = quantitySpinner.getModel();
                model.setValue(0);
            }
        }
    }
    
    private void printReceiptToFile(String receiptText) {
        //If receiptText is empty, then return back
        if (receiptText == null || "".equals(receiptText)) {
            return;
        }
        try {
            //Set folder to saved at
            String directoryPath = "Receipts";
            
            //Create folder to saved at
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            //Get Date and Time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String dateStr = dateFormat.format(new Date());
            //Add Date and Time to saved recreipt and set the receipt as txt file
            String receiptTextWithDate = "Receipt Date: " + dateStr + "\n\n" + receiptText;
            String filePath = directoryPath + File.separator + "receipt_" + dateStr + ".txt";
            //Print the receipt and open it
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(receiptTextWithDate);
            }
            Desktop.getDesktop().open(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}