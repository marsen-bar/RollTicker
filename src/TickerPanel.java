import javax.swing.*;
import java.awt.*;

/**
 * Tickerに関するクラス
 * 表示したい文字を描画する
 */

public class TickerPanel extends JPanel {
    public static final int FONT_SIZE = 80;
    private int moveX;
    private String message;
    private Font font;
    private Color stringColor;

    public TickerPanel(String message){
        int DELAY = 32;
        Timer timer = new Timer(DELAY, e -> repaint());
        font = new Font("メイリオ",Font.BOLD,FONT_SIZE);
        stringColor = Color.WHITE;
        this.message = message;
        setPreferredSize(new Dimension(Window.WIDTH,FONT_SIZE + 50));
        setOpaque(false);
        setBackground(Color.RED);
        timer.start();
    }

    public void update_X(int stringLength){
        moveX -= 5;
        if(Window.WIDTH + stringLength <= Math.abs(moveX)){
            moveX = 0;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStringColor(Color stringColor) { this.stringColor = stringColor; }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        FontMetrics fm = g2.getFontMetrics(font);
        g2.setFont(font);
        g2.setColor(Color.BLACK);

        int SHADOW = 2;
        int ADD = 20;
        g2.drawString(message,Window.WIDTH + moveX + SHADOW,FONT_SIZE + ADD);
        g2.drawString(message,Window.WIDTH + moveX + SHADOW,FONT_SIZE + ADD + SHADOW);
        g2.drawString(message,Window.WIDTH + moveX,FONT_SIZE + ADD + SHADOW);

        g2.setColor(stringColor);
        g2.drawString(message,Window.WIDTH + moveX,FONT_SIZE + ADD);
        update_X(fm.stringWidth(message));
    }


}
