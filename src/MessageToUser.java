import javax.swing.*;
import java.awt.*;

public class MessageToUser extends JTextField {

    MessageToUser(String message){
        this.setText(message);
        this.setOpaque(false);
        this.setEditable(false);
        this.setFocusable(false);
        this.setFont(new Font("Ariel", Font.BOLD, 18));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setHorizontalAlignment(JTextField.CENTER);
    }
}
