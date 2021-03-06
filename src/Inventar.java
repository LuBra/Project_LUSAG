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
        // Als erstes ID (bei blöcken die nummer, die die tiles haben, bei werkzeug die nummer die sie abbauen können sollen), dann Type(Block oder Werkzeug) dann Pfad zum Tile Bild
        inventar[0][0] = new Item(2,2,"Tiles//Item_2.png");
        inventar[1][0] = new Item(1,1,"Tiles//Item_1.png");
        inventar[2][0] = new Item(4,4,"Tiles//dirt.png");
        inventar[3][0] = new Item(1,4,"Tiles//grass.jpg");
        inventar[4][0] = new Item(3,4,"Tiles//rinde.JPG");
        inventar[5][0] = new Item(6,4,"Tiles//wood.png");
        inventar[6][0] = new Item(5,4,"Tiles//stone.png");
        inventar[7][0] = new Item(7,4,"Tiles//leaf.png");
        /*
            Spitzhacke: Type 1
            Axt:        Type 2
            Waffen:     Type 3
            Blöcke:     Type 4
        */
    }

    public int getItemType(int x, int y) {return inventar[y][x].getType();}
    public int getItemID(int x, int y){return inventar[y][x].getItem_ID();}
    public BufferedImage getItem_Image(int x, int y){return inventar[y][x].getImg();}
}
