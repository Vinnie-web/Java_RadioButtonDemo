import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File; 

public class RadioButtonDemo extends JFrame implements ActionListener {

    // 1. Define the pet names (Action Commands)
    private static final String BIRD_COMMAND = "Bird";
    private static final String CAT_COMMAND = "Cat";
    private static final String DOG_COMMAND = "Dog";
    private static final String RABBIT_COMMAND = "Rabbit";
    private static final String PIG_COMMAND = "Pig";

    private JLabel pictureLabel; // To display the pet image

    public RadioButtonDemo() {
        // Set up the main frame
        super("RadioButtonDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Use a BorderLayout for simple layout: Radio Buttons on the left, Image in the center
        setLayout(new BorderLayout());

        // --- Create Radio Buttons and Button Group ---
        JRadioButton birdButton = new JRadioButton(BIRD_COMMAND);
        JRadioButton catButton = new JRadioButton(CAT_COMMAND);
        JRadioButton dogButton = new JRadioButton(DOG_COMMAND);
        JRadioButton rabbitButton = new JRadioButton(RABBIT_COMMAND);
        JRadioButton pigButton = new JRadioButton(PIG_COMMAND);

        // Group the radio buttons so only one can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        // Set 'Pig' as the default selected button, as shown in your image
        pigButton.setSelected(true);
        
        // Set the Action Command for each button (what is returned on event)
        birdButton.setActionCommand(BIRD_COMMAND);
        catButton.setActionCommand(CAT_COMMAND);
        dogButton.setActionCommand(DOG_COMMAND);
        rabbitButton.setActionCommand(RABBIT_COMMAND);
        pigButton.setActionCommand(PIG_COMMAND);

        // Register the ActionListener for each button
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

        // --- Create Panel for Radio Buttons ---
        JPanel radioPanel = new JPanel();
        // Use a box layout to stack the buttons vertically
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS)); 
        radioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        
        radioPanel.add(birdButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(rabbitButton);
        radioPanel.add(pigButton);

        // --- Create Picture Label ---
        // Initialize the JLabel with the default selected image (Pig)
        pictureLabel = new JLabel();
        updatePicture(PIG_COMMAND); // Load the initial pig image

        // --- Add components to the Frame ---
        add(radioPanel, BorderLayout.LINE_START); // Buttons on the left
        add(pictureLabel, BorderLayout.CENTER);  // Image in the center

        // Finalize frame settings
        pack(); // Adjusts window size to fit components
        setLocationRelativeTo(null); // Centers the window
    }

    /**
     * Handles the ActionEvent when a radio button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String petName = e.getActionCommand();
        
        // 1. Update the image
        updatePicture(petName);

        // 2. Display the selection using a message box
        JOptionPane.showMessageDialog(this, 
                                      "You have selected: " + petName, 
                                      "Pet Selection", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Loads and updates the image icon based on the selected pet name.
     */
    private void updatePicture(String petName) {
        // Construct the path to the image file
        // IMPORTANT: Ensure your images are in the 'images' folder and are .gif files
        String imagePath = "images/" + petName + ".gif";
        
        // Check if file exists (good practice for debugging image issues)
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            pictureLabel.setIcon(icon);
            pictureLabel.setText(null); // Clear any error text
        } else {
            // Display an error message if the image is missing
            pictureLabel.setIcon(null);
            pictureLabel.setText("Image not found: " + imagePath);
        }
        
        // Re-pack the frame to ensure the new image size is handled correctly
        pack(); 
    }

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        // Ensure GUI operations run on the Event-Dispatching Thread
        SwingUtilities.invokeLater(() -> {
            RadioButtonDemo frame = new RadioButtonDemo();
            frame.setVisible(true);
        });
    }
}