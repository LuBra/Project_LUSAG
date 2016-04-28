import java.awt.image.BufferedImage;

/**
 * Created by Lukas_000 on 31.01.2016.
 */
public class World {
    private int size_x, size_y;
    private int[][] worldmap;
    private Generator generate;
    //Tiles nicht vergessen get_tile_image und getBegehbar und getabbau_id einzutragen
    private Tile grass;         //1
    private Tile cloud;         //2
    private Tile rinde;         //3
    private Tile dirt;          //4
    private Tile stone;         //5

    public World(){
        size_x = 300;
        size_y = 200;
        generate = new Generator(size_x,size_y);
        worldmap = generate.getWorld();
        //create_world();

        grass = new Tile("Tiles//grass.jpg",1);        //tile bild pfad, und abbau ID  1=test
        cloud = new Tile("Tiles//cloud.jpg",1);
        rinde = new Tile("Tiles//rinde.JPG",2);
        dirt =  new Tile("Tiles//dirt.png",1);
        stone = new Tile("Tiles//stone.png",1);
    }

    public void create_world(){
        worldmap = new int[][]{ {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                                {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
                                {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
                                {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
                                {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
                                {3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3},
                                {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
        };
    }
    public BufferedImage get_tile_image(int x,int y){
        if(worldmap[x][y] == 1){
            return grass.get_tile_pic();
        }
        if(worldmap[x][y] == 2){
            return cloud.get_tile_pic();
        }
        if(worldmap[x][y] == 3){
            return rinde.get_tile_pic();
        }
        if(worldmap[x][y] == 4){
            return dirt.get_tile_pic();
        }
        if(worldmap[x][y] == 5){
            return stone.get_tile_pic();
        }
        else return null;
    }
    public int getSize_x(){return size_x;}
    public int getSize_y(){return size_y;}
    public boolean getBegehbar(int x, int y){
        if(worldmap[x][y] == 1){
            return grass.isBegehbar();
        }
        if(worldmap[x][y] == 2){
            return cloud.isBegehbar();
        }
        if(worldmap[x][y] == 3){
            return rinde.isBegehbar();
        }
        if(worldmap[x][y] == 4){
            return dirt.isBegehbar();
        }
        if(worldmap[x][y] == 5){
            return stone.isBegehbar();
        }
        else return true;
    }
    public int getAbbau_ID(int x,int y){
        if(worldmap[x][y] == 1){
            return grass.getAbbau_ID();
        }
        if(worldmap[x][y] == 2){
            return cloud.getAbbau_ID();
        }
        if(worldmap[x][y] == 3){
            return rinde.getAbbau_ID();
        }
        if(worldmap[x][y] == 4){
            return dirt.getAbbau_ID();
        }
        if(worldmap[x][y] == 5){
            return stone.getAbbau_ID();
        }
        else return 0;
    }
    public boolean getBebaubar(int x, int y){
        if(x < 0) x = 0;        //sicherheit um nicht auserhalb des Arrays zu landen.  Sollete noch am anderen rand implementiert werden kek
        if(y < 0) y = 0;
        if(worldmap[y][x] == 0) return true;
        else return false;
    }
    public void Abbauen(int x, int y){
        worldmap[x][y] = 0;
    }
    public void bauen(int x, int y, int number){
        worldmap[y][x]=number;
    }
}
