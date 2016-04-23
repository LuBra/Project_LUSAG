import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lukas_000 on 20.02.2016.
 */
public class Item {
    private BufferedImage img;
    private int item_ID;
    private int type;

    /*
    Spitzhacke: Type 1
    Axt:        Type 2
    Waffen:     Type 3
    Blöcke:     Type 4
     */

    public Item(int item_ID, int type, String path) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.item_ID = item_ID;
        this.type = type;
    }

    public Item(){
        item_ID = 0;
        type = 0;
        img = null;
    }

    public BufferedImage getImg() {
            return img;
    }

    public int getItem_ID() {
        return item_ID;
    }

    public int getType() {
        return type;
    }
}

