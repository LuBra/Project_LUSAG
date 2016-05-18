import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Lukas_000 on 31.01.2016.
 */
public class Spielfeld extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
    private World world;
    private Player spieler;
    private Timer timer;
    private Timer jumptimer;
    private Timer gravitationtimer;
    private Timer keytimer;
    private BufferedImage background;
    private int tilesize;
    private int inventarsize;
    private int jumpcount;
    private int mauspos_X,mauspos_Y;
    private int zeichnenoffsetspieler_x, zeichnenoffsetspieler_y;
    private int selected_Inventory_Field_X,selected_Inventory_Field_Y;
    private boolean is_jumping;
    private char last_pressed_key;
    private Dimension resolution;

    public Spielfeld(){
        setLayout(null);
        resolution = new Dimension(1280,720);               //Fenster Auflösung
        setPreferredSize(resolution);
        zeichnenoffsetspieler_x = (int) resolution.getWidth()/2;             //muss zu int gemacht werden weil double
        zeichnenoffsetspieler_y = (int) resolution.getHeight()/2;
        world = new World();
        spieler = new Player();
        this.addKeyListener(this);
        addMouseMotionListener(this);               //MouseMotion Listener
        addMouseListener(this);
        mauspos_Y = 0;
        mauspos_X = 0;
        selected_Inventory_Field_X = 0;
        selected_Inventory_Field_Y = 0;
        setVisible(true);
        timer = new Timer(2,this);
        keytimer = new Timer(15,this);
        jumptimer = new Timer(15,this);
        gravitationtimer = new Timer(15,this);
        keytimer.start();
        gravitationtimer.start();
        timer.start();
        tilesize = 26;                  //am besten gehen gerade Zahlen
        inventarsize = 30;
        jumpcount = 1;
        is_jumping = true;

        try {
            background = ImageIO.read(new File("Tiles//back.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics gr){
        Graphics2D g = (Graphics2D)gr;
        g.drawImage(background,0,0,resolution.width,resolution.height,null);                   //Hintergrundbild

        //region Map_Zeichnen
        for (int i = 0; i < world.getSize_x(); i++) {                              //Zeichnet die Spielewelt
            for (int j = 0; j < world.getSize_y(); j++) {
                g.drawImage(world.get_tile_image(j, i), (i * tilesize) - spieler.getOffset_X(), (j * tilesize) - spieler.getOffset_Y(), tilesize, tilesize, null);
            }
        }
        //endregion
        //region Inventar_Zeichen
        for (int i = 0; i < spieler.getInventar_size_X() ; i++) {
            for (int j = 0; j < spieler.getInventar_size_Y() ; j++) {
                g.drawRect((i * inventarsize) + 10, (j * inventarsize) + 10, inventarsize,inventarsize);
                g.drawImage(spieler.getInventar_Image(j,i),(i * inventarsize) +10,(j * inventarsize) +10,inventarsize,inventarsize,null);
            }
        }
        //endregion
        g.drawImage(spieler.getSpieler(),zeichnenoffsetspieler_x,zeichnenoffsetspieler_y,tilesize,tilesize,null);       //Spieler
        //g.drawRect(400+(tilesize/3),400,tilesize/3,tilesize);
        g.setColor(Color.RED);
        g.drawRect((selected_Inventory_Field_X * inventarsize) + 10,(selected_Inventory_Field_Y * inventarsize) + 10, inventarsize,inventarsize );
    }

    public void jump(){
        jumpcount = 1;
        is_jumping = true;                      //verhindert unendlich hohes springen
        gravitationtimer.stop();
        jumptimer.start();
    }

    //region Keys
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case '1': {
                spieler.setSelectedItem(0, 0);
                selected_Inventory_Field_X = 0;
                selected_Inventory_Field_Y = 0;
            }
                break;
            case '2': {
                spieler.setSelectedItem(0, 1);
                selected_Inventory_Field_X = 1;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '3': {
                spieler.setSelectedItem(0, 2);
                selected_Inventory_Field_X = 2;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '4': {
                spieler.setSelectedItem(0, 3);
                selected_Inventory_Field_X = 3;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '5': {
                spieler.setSelectedItem(0, 4);
                selected_Inventory_Field_X = 4;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '6': {
                spieler.setSelectedItem(0, 5);
                selected_Inventory_Field_X = 5;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '7': {
                spieler.setSelectedItem(0, 6);
                selected_Inventory_Field_X = 6;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '8': {
                spieler.setSelectedItem(0, 7);
                selected_Inventory_Field_X = 7;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '9': {
                spieler.setSelectedItem(0, 8);
                selected_Inventory_Field_X = 8;
                selected_Inventory_Field_Y = 0;
            }
            break;
            case '0': {
                spieler.setSelectedItem(0, 9);
                selected_Inventory_Field_X = 9;
                selected_Inventory_Field_Y = 0;
            }
            break;
        }
        last_pressed_key = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
            last_pressed_key = 'm';
    }


    //endregion

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            repaint();
            //System.out.println(get_Mouse_Inventar_position_X() + " "+ get_Mouse_Inventar_position_Y());
            System.out.println(get_Mouse_Tile_position_X() + " "+ get_Mouse_Tile_position_Y());
            resolution.height = this.getHeight();
            resolution.width = this.getWidth();             //um Fenstergroese live anzupassen
            zeichnenoffsetspieler_x = (int) resolution.getWidth()/2;             //muss zu int gemacht werden weil double
            zeichnenoffsetspieler_y = (int) resolution.getHeight()/2;
        }
        if(e.getSource()==gravitationtimer){
            if(check_collision(2) == true) {                        //erst wenn er wieder den boden berührt ist isjumping false, weil man sonst unendlich hoch springen kann
                spieler.setOffset_Y(spieler.getOffset_Y() + 2);
                is_jumping = true;
            }
            else is_jumping=false;
        }
        if(e.getSource()==jumptimer) {
            if (jumpcount <= 30 && check_collision(1) == true) {
                spieler.setOffset_Y(spieler.getOffset_Y() - 2);
                jumpcount++;
                repaint();
            } else {
                jumptimer.stop();
                gravitationtimer.start();
            }
        }
        //region Steuerung
        if(e.getSource()==keytimer){
            switch (last_pressed_key){                                        //holt offset zum Startpunkt, und addiert bewegung dazu, damit sich die welt bewegt.
                case ' ':   if(check_collision(1)==true && is_jumping==false) {
                    //spieler.setOffset_Y(spieler.getOffset_Y() - 2);
                    jump();
                }
                    break;
                case 's':  if(check_collision(2)==true) {
                    spieler.setOffset_Y(spieler.getOffset_Y() + 2);
                }
                    break;
                case 'a':   if(check_collision(3)==true) {
                    spieler.setOffset_X(spieler.getOffset_X() - 2);
                }
                    break;
                case 'd':   if(check_collision(4)==true) {
                    spieler.setOffset_X(spieler.getOffset_X() + 2);
                }
                    break;
            }

        }
        //endregion
    }

    //region Maus_Klick_Zeug
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {                        //abbauen
        if((mauspos_X > 10 && mauspos_X < 310) && (mauspos_Y > 10 && mauspos_Y < 70)){
            spieler.setSelectedItem(get_Mouse_Inventar_position_Y(),get_Mouse_Inventar_position_X());
            selected_Inventory_Field_X = get_Mouse_Inventar_position_X();
            selected_Inventory_Field_Y = get_Mouse_Inventar_position_Y();
        }
        else {
            if(spieler.getSelectedItem() == 1 || spieler.getSelectedItem() == 2) {                                              //Wenn das ausgewählte Item dem Typ 1 (Spitzhacke) oder 2 (Axt) entspricht wird geprüft ob das tile abgebaut werden kann.
                if (spieler.getSelectedItem() == world.getAbbau_ID(get_Mouse_Tile_position_Y(), get_Mouse_Tile_position_X())) {
                    world.Abbauen(get_Mouse_Tile_position_Y(), get_Mouse_Tile_position_X());
                }
            }
            if(spieler.getSelectedItem() == 4){                                                                                 // Wenn Der Typ 4 ist (Block), wird überprüft ob er plaziert werden kann, dannach wird er plaziert
                if(check_build(get_Mouse_Tile_position_X(),get_Mouse_Tile_position_Y()) == true) {
                    world.bauen(get_Mouse_Tile_position_X(), get_Mouse_Tile_position_Y(), spieler.getSelectedItem_ID());
                }
            }
        }
    }           //abbauen

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    // endregion

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mauspos_X = e.getX();
        mauspos_Y = e.getY();
    }

    public boolean check_collision(int direction){
        double cache_player_X = spieler.getOffset_X()+zeichnenoffsetspieler_x;
        double cache_player_Y = spieler.getOffset_Y()+zeichnenoffsetspieler_y;
        double tilepos_player_X1;                               //rechte seite
        double tilepos_player_X2;                               //linke seite
        double tilepos_player_Y;                                //oben
        double tilepos_player_Y2;                               //unten
        int x;
        int x2;
        int y;
        int y2;
        switch (direction){
            case 1:
                tilepos_player_X1 = (int) ((cache_player_X + (tilesize/3*2 -1)) / tilesize);       //das Player Image hat die rechte untere X-Kante(Fuß bei 2/3 der Pixelzahl. -1 weil sonst am rand hängen bleibt
                tilepos_player_X2 = (int) ((cache_player_X + (tilesize/3 +1)) / tilesize);         // Die linke Fuß Seite ist bei 1/3 selbes Spiel
                tilepos_player_Y = (int) ((cache_player_Y - 1) / tilesize);
                x = (int) tilepos_player_X1;
                x2 = (int) tilepos_player_X2;
                y = (int) tilepos_player_Y;
                if(world.getBegehbar(y,x)==false || world.getBegehbar(y,x2)==false)return false;
                else return true;
            case 2:
                tilepos_player_X1 = (int) ((cache_player_X + (tilesize/3*2 -1)) / tilesize);        //+20
                tilepos_player_X2 = (int) ((cache_player_X + (tilesize/3 +1)) / tilesize);        //+10
                tilepos_player_Y = (int) ((cache_player_Y + 1) / tilesize);
                x = (int) tilepos_player_X1;
                x2 = (int) tilepos_player_X2;
                y = (int) tilepos_player_Y;;
                if(world.getBegehbar(y+1,x)==false || world.getBegehbar(y+1,x2)==false)return false;
                else return true;
            case 3:
                tilepos_player_X1 = (int) ((cache_player_X + (tilesize/3) - 1) / tilesize);
                tilepos_player_Y = (int) (cache_player_Y / tilesize);
                tilepos_player_Y2 = (int) ((cache_player_Y + (tilesize-1)) / tilesize);            //Obere Kante -1 weil sonst hängen bleibt
                x = (int) tilepos_player_X1;
                y = (int) tilepos_player_Y;
                y2 = (int) tilepos_player_Y2;
                if(world.getBegehbar(y,x)==false || world.getBegehbar(y2,x)==false)return false;
                else return true;
            case 4:
                tilepos_player_X1 = (int) ((cache_player_X + (tilesize/3*2) + 1) / tilesize);
                tilepos_player_Y = (int) (cache_player_Y / tilesize);
                tilepos_player_Y2 = (int) ((cache_player_Y + (tilesize-1)) / tilesize);             //same
                x = (int) tilepos_player_X1;
                y = (int) tilepos_player_Y;
                y2 = (int) tilepos_player_Y2;
                if(world.getBegehbar(y,x)==false || world.getBegehbar(y2,x)==false)return false;
                else return true;
        }
        return false;

    }               ///wand berührungs detection, am besten nix mehr ändern
    public boolean check_build(int x, int y){
        // platzier Regeln:
        // 1. Darf nicht im Spieler platziert werden
        // 2. Darf nicht in bereites mit anderen Blöcken belegte Plätze gebaut werden
        // 3. Darf nur gebaut werden wenn irgend ein anderer block an den zu bauenden Block anliegend ist.

        // spieler colisions check
        boolean player_collision = true;
        int player_tile_position_X1 = ((spieler.getOffset_X() +zeichnenoffsetspieler_x) + (tilesize/3*2 -1)) / tilesize;
        int player_tile_position_X2 = ((spieler.getOffset_X() +zeichnenoffsetspieler_x) + (tilesize/3 +1)) / tilesize;
        int player_tile_position_Y1 = (spieler.getOffset_Y() +zeichnenoffsetspieler_y) /tilesize;
        int player_tile_position_Y2 = ((spieler.getOffset_Y() +zeichnenoffsetspieler_y) + (tilesize-1)) / tilesize;
        if((x == player_tile_position_X1 || x == player_tile_position_X2) && (y == player_tile_position_Y1 || y == player_tile_position_Y2)){player_collision = true;}
        else player_collision = false;

        // bereits bebautes gebiet check
        boolean already_builded = true;
        if(world.getBebaubar(x,y) == false) already_builded = true;
        else already_builded = false;

        //umgebungs check
        boolean no_surrounding = true;
        if((world.getBebaubar(x+1,y) && world.getBebaubar(x-1,y) && world.getBebaubar(x,y+1) && world.getBebaubar(x,y-1)) == true) no_surrounding = true;
        else no_surrounding = false;

        //gesammtcheck
        if(player_collision == false && already_builded == false && no_surrounding == false) return true;
        else return false;
    }                       // beim bauen von zeug. Springen und bauen scheint noch buggy zu sein.

    public int get_Mouse_Tile_position_X(){
        int chache_MouseTilePosX;
        chache_MouseTilePosX = mauspos_X + spieler.getOffset_X();
        chache_MouseTilePosX = chache_MouseTilePosX / tilesize;
        return chache_MouseTilePosX;
    }
    public int get_Mouse_Tile_position_Y(){
        int chache_MouseTilePosY;
        chache_MouseTilePosY = mauspos_Y + spieler.getOffset_Y();
        chache_MouseTilePosY = chache_MouseTilePosY / tilesize;
        return chache_MouseTilePosY;
    }
    public int get_Mouse_Inventar_position_X(){
        int chache_MouseInventarPosX;
        chache_MouseInventarPosX = mauspos_X-10;
        chache_MouseInventarPosX = chache_MouseInventarPosX / inventarsize;
        return chache_MouseInventarPosX;
    }
    public int get_Mouse_Inventar_position_Y(){
        int chache_MouseInventarPosY;
        chache_MouseInventarPosY = mauspos_Y -10;
        chache_MouseInventarPosY = chache_MouseInventarPosY / inventarsize;
        return chache_MouseInventarPosY;
    }
}



