import javax.swing.*;
import java.awt.*;

//jscroll z panelem box od wierszy
public class PasswordTablePanel extends JScrollPane {
    private JPanel tablePanel = new JPanel();

    public PasswordTablePanel() {

        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setOpaque(false);
        this.setOpaque(false);
        this.getViewport().setOpaque(false);

        this.setViewportView(tablePanel);
        this.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 2));
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        tablePanel.add(new TableRowPanel());
    }

    public void AddRow(TableRowPanel tableRow){
        tablePanel.add(tableRow);
        tablePanel.revalidate();
        tablePanel.repaint();
    }
    public void ClearTable(){
        tablePanel.removeAll();
        tablePanel.add(new TableRowPanel());
        tablePanel.revalidate();
        tablePanel.repaint();
    }
    public void RemoveRow(TableRowPanel tableRow){
        tablePanel.remove(tableRow);
        tablePanel.revalidate();
        tablePanel.repaint();
    }
}