import javax.swing.*;

/**
 * Created by Lukas_000 on 31.01.2016.
 */
public class Gui extends JFrame {
        Spielfeld spielfeld;
    public Gui(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Project LUSAG");
        spielfeld = new Spielfeld();
        spielfeld.addKeyListener(spielfeld);
        spielfeld.setFocusable(true);
        spielfeld.setSize(800,600);
        add(spielfeld);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args){
        new Gui();
    }
}
