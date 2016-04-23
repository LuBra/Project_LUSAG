import java.awt.image.BufferedImage;

/**
 * Created by Lukas_000 on 20.02.2016.
 */
public class Inventar {
    private Item[][] inventar;

    public Inventar(){
        inventar = new Item[10][2];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                inventar[i][j] = new Item();
            }
        }
        inventar[0][0] = new Item(2,2,"Item_2.png");
        inventar[1][0] = new Item(1,1,"Item_1.png");
        inventar[2][0] = new Item(4,4,"dirt.png");
    }

    public int getItemType(int x, int y) {return inventar[y][x].getType();}
    public int getItemID(int x, int y){return inventar[y][x].getItem_ID();}
    public BufferedImage getItem_Image(int x, int y){return inventar[y][x].getImg();}
}
