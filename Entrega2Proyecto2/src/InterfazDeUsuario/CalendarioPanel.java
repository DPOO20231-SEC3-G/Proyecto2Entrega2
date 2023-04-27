package InterfazDeUsuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Calendar;


import javax.swing.JPanel;

public class CalendarioPanel extends JPanel {

    private int month;
    private int year;

    public CalendarioPanel(int month, int year) {
        
        this.month = month;
        this.year = year;

        setSize(50, 50);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    
        int cellSize = Math.min(getWidth() / 7, getHeight() / 9);
    
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, cellSize / 2));
        String[] weekdays = {"", "D", "L", "M", "X", "J", "V", "S"};
    
        for (int i = 1; i <= 7; i++) {
            int x = (i - 1) * cellSize;
            int y = cellSize + (cellSize / 2);
            g2d.drawString(weekdays[i].toUpperCase(), x + (cellSize / 2) - 10, y);
        }
    
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, cellSize));
        String monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, getLocale());
        int monthNameWidth = g2d.getFontMetrics().stringWidth(monthName);
        int monthNameX = (getWidth() - monthNameWidth) / 2;
        int monthNameY = cellSize;
        g2d.drawString(monthName, monthNameX, monthNameY);
    
        int day = 1;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                int x = col * cellSize;
                int y = (row + 3) * cellSize;
                if (row == 0 && col < firstDayOfMonth - 1) {
                } else if (day > lastDayOfMonth) {
                } else {
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x, y - cellSize / 2, cellSize, cellSize);
                    g2d.setFont(new Font("Arial", Font.PLAIN, cellSize / 2));
                    String dayStr = String.valueOf(day);
                    int textWidth = g2d.getFontMetrics().stringWidth(dayStr);
                    int textHeight = g2d.getFontMetrics().getHeight();
                    int xOffset = (cellSize - textWidth) / 2;
                    int yOffset = (cellSize - textHeight)/4;
                    g2d.drawString(dayStr, x + xOffset, y - yOffset);
                    day++;
                }
            }
        }
    }
    
}