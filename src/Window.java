import javax.swing.*;
import java.awt.*;

/**
 * フレームとその他コンポーネントを生成するクラス
 */

public class Window extends JFrame {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;
    private final String MESSAGE = "Java研開始まで今しばらく";
    public static Music BGM = new Music("spring");


    public Window(String title){
        super(title);

        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        init();

        setVisible(true);
    }

    public void init(){
        Panel panel = new Panel();
        Container contentPane = getContentPane();
        contentPane.add(panel);
        Music.balanceLoop(BGM.getClip(),0.1);
    }

}
