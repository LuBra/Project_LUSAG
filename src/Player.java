import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lukas_000 on 10.02.2016.
 */
public class Player {
    private int Offset_X, Offset_Y;
    private BufferedImage spieler;
    private int selected_itemX;
    private int selected_itemY;
    private Inventar inventar;
    private int inventar_size_X;
    private int inventar_size_Y;

    public Player(){
        Offset_X = 0;
        Offset_Y = 800;
        inventar_size_X = 10;
        inventar_size_Y = 2;
        inventar = new Inventar();

        try {
            spieler = ImageIO.read(new File("Tiles//Player_clean.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setOffset_X(int offset_X) {
        Offset_X = offset_X;
    }

    public void setOffset_Y(int offset_Y) {
        Offset_Y = offset_Y;
    }

    public int getOffset_X() {
        return Offset_X;
    }

    public int getOffset_Y() {
        return Offset_Y;
    }

    public int getSelectedItem() {
        return inventar.getItemType(selected_itemX,selected_itemY);
    }
    public int getSelectedItem_ID(){return inventar.getItemID(selected_itemX,selected_itemY);}
    public void setSelectedItem(int x, int y) {
        selected_itemX = x;
        selected_itemY = y;
    }

    public BufferedImage getInventar_Image(int x, int y) {
        return inventar.getItem_Image(x,y);}

    public int getInventar_size_X() {
        return inventar_size_X;
    }
    public int getInventar_size_Y() {
        return inventar_size_Y;
    }

    public BufferedImage getSpieler() {
        return spieler;
    }
}
