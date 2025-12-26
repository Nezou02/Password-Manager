import javax.swing.*;
//jscroll z panelem box od wierszy
public class PasswordTablePanel extends JScrollPane {
    private JPanel tablePanel = new JPanel();

    public PasswordTablePanel() {

        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setOpaque(false);
        this.setOpaque(false);
        this.getViewport().setOpaque(false);

        this.setViewportView(tablePanel);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    public void AddRow(TableRowPanel tableRow){
        tablePanel.add(tableRow);
        tablePanel.add(Box.createVerticalStrut(10));
        tablePanel.revalidate();
        tablePanel.repaint();
    }

}