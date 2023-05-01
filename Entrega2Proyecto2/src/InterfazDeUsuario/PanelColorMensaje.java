package InterfazDeUsuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelColorMensaje extends JPanel{
    private Color color;

    private String mensaje;

    public void paint(Graphics g) {
        Dimension panelSize = this.getSize();
        int panelWidth = panelSize.width;
        int panelHeight = panelSize.height;

        int squareSize = Math.min(panelWidth, panelHeight) / 6;
        int squareX = (panelWidth - squareSize) / 6;
        int squareY = (panelHeight - squareSize) / 6;

        g.setColor(color);
        g.fillRect(squareX, squareY, squareSize, squareSize);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(mensaje, squareX + squareSize + 10, squareY + squareSize / 2);
    }

    public PanelColorMensaje(Color color, String mensaje) {
        this.color = color;
        this.mensaje = mensaje;
    }

}
