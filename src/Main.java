import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Класс Main
public class Main
{
    public static void main(String[] args)
    {
        new CalculatorGUI();
    }
}

// Главное окно
class CalculatorGUI extends JFrame implements ActionListener 
{
    private JRadioButton[] foundationButtons, wallButtons, roofButtons, finishingButtons;
    private JTextField areaField;
    private JTextArea resultArea;
    private JButton calculateButton;

    public CalculatorGUI() 
    {
        super("Калькулятор стоимости строительства частного дома");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 510);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Фундамент
        JPanel foundationPanel = createElementPanel("Фундамент",
                new String[]{"Ленточный (4000 руб)", "Плитный (5000 руб)", "Свайный (3000 руб)"},
                new double[]{4000, 5000, 3000});
        add(foundationPanel);

        // Стены
        JPanel wallButtons = createElementPanel("Стены",
                new String[]{"Кирпичные (8000 руб)", "Газобетонные (5000 руб)", "Каркасные (3000 руб)"},
                new double[]{8000, 6000, 4000});
        add(wallButtons);

        // Крыша
        JPanel roofButtons = createElementPanel("Крыша",
                new String[]{"Металлочерепица (4000 руб)", "Мягкая кровля (3000 руб)", "Шифер (2000 руб)"},
                new double[]{4000, 3000, 2000});
        add(roofButtons);

        // Отделка
        JPanel finishingButtons = createElementPanel("Отделка",
                new String[]{"Ленточный (8000 руб)", "Плитный (5000 руб)", "Свайный (3000 руб)"},
                new double[]{8000, 5000, 3000});
        add(finishingButtons);

        // Площадь
        areaField = new JTextField(10);
        JPanel areaPanel = new JPanel();
        areaPanel.add(new JLabel("Площадь дома (кв.м.):"));
        areaPanel.add(areaField);
        add(areaPanel);

        
        // Кнопка
        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Рассчитать");
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);
        add(buttonPanel);
        
        // Область вывода
        JPanel resultPanel = new JPanel();
        resultPanel.add(new JScrollPane(resultArea));
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));
        add(resultPanel);

        setVisible(true);
    }

    // Создание панели для элемента
    private JPanel createElementPanel(String elementName, String[] options, double[] prices) 
    {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createTitledBorder(elementName));
        ButtonGroup group = new ButtonGroup();

        JRadioButton[] buttons = new JRadioButton[options.length];
        for (int i = 0; i < options.length; i++) {
            buttons[i] = new JRadioButton(options[i]);
            buttons[i].setActionCommand(String.valueOf(prices[i]));
            group.add(buttons[i]);
            panel.add(buttons[i]);
        }

        // Сохранение ссылок на кнопки для каждого элемента
        switch (elementName)
        {
            case "Фундамент":
                foundationButtons = buttons;
                break;
            case "Стены":
                wallButtons = buttons;
                break;
            case "Крыша":
                roofButtons = buttons;
                break;
            case "Отделка":
                finishingButtons = buttons;
                break;
        }

        return panel;
    }
}
