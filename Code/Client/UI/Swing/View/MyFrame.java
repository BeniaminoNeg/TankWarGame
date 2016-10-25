package Client.UI.Swing.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by beniamino on 18/02/16.
 */
public class MyFrame extends JFrame {

    JLabel jl = new JLabel("Buona lezione");
    JButton uno = new JButton("Uno");
    JButton due = new JButton("Due");
    JButton tre = new JButton("Tre");
    JButton quattro = new JButton("Quattro");
    JButton cinque = new JButton("Cinque");

    public MyFrame() {
        super("Flow Layout");
        Container c = this.getContentPane();
        c.add(jl);
        c.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 15; i++) {
            final JButton button = new JButton(String.valueOf(i));
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button.setSize(400, 400);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            c.add(button);

        }


        /*
        c.add(uno);
        c.add(due);
        c.add(tre);
        c.add(quattro);
        c.add(cinque);
        */
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}
