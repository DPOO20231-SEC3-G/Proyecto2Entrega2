package InterfazDeUsuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import javax.swing.JPanel;

public class CalendarioPanel extends JPanel {

    private Calendar today;
    private int month, year;

    public CalendarioPanel() {
        today = Calendar.getInstance();
        month = today.get(Calendar.MONTH);
        year = today.get(Calendar.YEAR);
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

        // Obtener el primer y último día del mes
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Calcular el tamaño de las celdas
        int cellSize = Math.min(getWidth() / 7, getHeight() / 9);

        // Dibujar los días de la semana
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, cellSize / 2));
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weekdays = dfs.getShortWeekdays();
        for (int i = 1; i <= 7; i++) {
            int x = (i - 1) * cellSize;
            int y = cellSize;
            g2d.drawString(weekdays[i].toUpperCase(), x + (cellSize / 2) - 10, y);
        }

        // Dibujar los días del mes
        int day = 1;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                int x = col * cellSize;
                int y = (row + 2) * cellSize;
                if (row == 0 && col < firstDayOfMonth - 1) {
                    // Espacio en blanco antes del primer día del mes
                } else if (day > lastDayOfMonth) {
                    // Espacio en blanco después del último día del mes
                } else {
                    // Dibujar el día del mes
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Arial", Font.PLAIN, cellSize / 2));
                    g2d.drawString(String.valueOf(day), x + (cellSize / 2) - 5, y - (cellSize / 2) + 5);
                    day++;
                }
            }
        }
    }



    public static void main(String[] args) {
        new CalendarioPanel();
    }
}
