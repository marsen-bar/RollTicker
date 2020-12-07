import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * 細かいコンポーネントをこのクラスに表示する。
 *
 */

public class Panel extends JPanel implements ActionListener {
    private final TickerPanel tickerPanel;
    private final JButton musicOn;
    private final JButton musicOff;
    private final JButton sendSentence;
    private final JButton colorButton;
    private final JTextField textField;
    private final JColorChooser chooser;
    private final JComboBox<Object> jComboBox;
    private final Map<String,Font> fontsMap;
    private final List<String> fontsName;

    public Panel(){
        Font[] localFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        setPreferredSize(new Dimension(Window.WIDTH,Window.HEIGHT));
        setBackground(Color.GREEN);
        setLayout(new FlowLayout());
        fontsMap = new HashMap<>();
        fontsName = new ArrayList<>();

        for(Font font : localFonts){
            fontsMap.put(font.getName(),font);
            fontsName.add(font.getName());
        }

        String MESSAGE = "Java研開始まで今しばらく";
        tickerPanel = new TickerPanel(MESSAGE);
        musicOn = new JButton("♪〇");
        musicOff = new JButton("♪×");
        sendSentence = new JButton("update");
        colorButton = new JButton("change color!!");
        textField = new JTextField(15);
        chooser = new JColorChooser();
        jComboBox = new JComboBox<>(fontsName.toArray());

        sendSentence.addActionListener(this);
        musicOn.addActionListener(this);
        musicOff.addActionListener(this);
        colorButton.addActionListener(this);

        add(textField);
        add(sendSentence);
        add(musicOn);
        add(musicOff);
        add(jComboBox);
        add(chooser);
        add(colorButton);
        add(tickerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendSentence){
            String message = textField.getText();
            Font font = fontsMap.get(jComboBox.getSelectedItem());
            tickerPanel.setFont(new Font(font.getName(),font.getStyle(), TickerPanel.FONT_SIZE));
            if (!message.equals("")) {
                tickerPanel.setMessage(message);
            } else {
                tickerPanel.setMessage("何か書いて");
            }
            System.out.println((String)jComboBox.getSelectedItem());

            repaint();
        }

        if(e.getSource() == musicOn)
            Music.balanceLoop(Window.BGM.getClip(),0.1);

        if(e.getSource() == musicOff)
            Music.stop(Window.BGM.getClip());

        if(e.getSource() == colorButton)
            tickerPanel.setStringColor(chooser.getColor());
    }
}
