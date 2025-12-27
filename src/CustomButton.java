import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String textInside) {
        this.setText(textInside);
        this.setFont(new Font("", Font.BOLD, 18));
        this.setAlignmentX(JButton.CENTER_ALIGNMENT);
        this.setFocusable(false);
        this.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 3),new EmptyBorder(5, 5, 5, 5)));
    }
}
