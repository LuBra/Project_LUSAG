import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lukas_000 on 31.01.2016.
 */
public class Tile {
    private BufferedImage img;
    private boolean begehbar;
    private int abbau_ID;

    public Tile(String path, int abbau, boolean pBegehbar){

        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        begehbar = pBegehbar;
        abbau_ID = abbau;
    }
    public BufferedImage get_tile_pic(){
        return img;
    }

    public boolean isBegehbar() {
        return begehbar;
    }

    public int getAbbau_ID() {
        return abbau_ID;
    }
}
