import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JDialog {
    private JPanel contentPane;
    private JButton openFileButton;
    private JButton saveImageButton;
    private JButton findWayButton;
    private JButton saveWayTextButton;
    private JRadioButton startCoordinates;
    private JRadioButton goalCoordinates;
    private JFormattedTextField startLatitudeField;
    private JFormattedTextField goalLatitudeField;
    private JFormattedTextField startLongitudeField;
    private JFormattedTextField goalLongitudeField;
    private JRadioButton startId;
    private JRadioButton goalId;
    private JFormattedTextField startIdField;
    private JFormattedTextField goalIdField;
    private JButton checkStartCoordinates;
    private JButton checkGoalCoordinates;
    private JButton checkStartId;
    private JButton checkGoalId;

    GraphReader graphReader = new GraphReader();
    //WayFinder wayFinder = new WayFinder();

    public MainView() {
        setContentPane(contentPane);
        setModal(true);
        startCoordinates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLatitudeField.setEditable(true);
                startLongitudeField.setEditable(true);
                checkStartCoordinates.setEnabled(true);
                startId.setSelected(false);
                startIdField.setEditable(false);
                checkStartId.setEnabled(false);
            }
        });
        startId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startLatitudeField.setEditable(false);
                startLongitudeField.setEditable(false);
                checkStartCoordinates.setEnabled(false);
                startCoordinates.setSelected(false);
                startIdField.setEditable(true);
                checkStartId.setEnabled(true);
            }
        });
        goalCoordinates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goalLatitudeField.setEditable(true);
                goalLongitudeField.setEditable(true);
                checkGoalCoordinates.setEnabled(true);
                goalId.setSelected(false);
                goalIdField.setEditable(false);
                checkGoalId.setEnabled(false);
            }
        });
        goalId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goalLatitudeField.setEditable(false);
                goalLongitudeField.setEditable(false);
                checkGoalCoordinates.setEnabled(false);
                goalCoordinates.setSelected(false);
                goalIdField.setEditable(true);
                checkGoalId.setEnabled(true);
            }
        });
    }

    public static void main(String[] args) {
        MainView dialog = new MainView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
