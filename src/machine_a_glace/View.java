package machine_a_glace;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;

public class View extends BasicGame {
	private GameContainer container;
	private TiledMap map;
	
	private float x = 976, y = 32+16;
	private float xx = 976, yy = 960-32-16;
	private int direction = 2;
	private int direction2 = 0;
	private boolean moving = false;
	private long lasttime = System.currentTimeMillis();
	private long lasttime2 = System.currentTimeMillis();
	private long lasttime3 = System.currentTimeMillis();
	
	private ArrayList<Point> pos_color = new ArrayList<Point>();
	private ArrayList<Point> pos_color_2 = new ArrayList<Point>();

	private final float DEBUT_VIE_ROUGE_X = 1631;
	private final float FIN_VIE_ROUGE_X = 1778;
	private final float VIE_Y = 122;

	private final float DEBUT_VIE_BLEU_X = 130;
	private final float FIN_VIE_BLEU_X = 277;

	private boolean canmove = false;
	private boolean canmove2 = false;
	
	private Entite e;
	private Entite e2;

	private boolean moving2 = false;
	private Animation[] animations = new Animation[8];
	private Animation[] animations2 = new Animation[8];
	
	private String item[] = {"A","P","K"};
	private String item2[] = {"Robot1","Robot2","Robot3"};
	private JComboBox robot = new JComboBox(item);
	private JComboBox robot2 = new JComboBox(item2);
	private ImageIcon ic = new ImageIcon("robot.png");
	private Dimension d = new Dimension(100,100);
	private String tab[] = {"Frapper","Explorer", "Kamikaze",";","*",">"};
	private String tab2[] = {"Manger","Fumer", "Rond-Poing"};
	

	public View() {
		super("Fenetre");
		// TODO Auto-generated constructor stub
	}

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.container = arg0;
		this.map = new TiledMap("maps/map/map1.tmx");
		container.setShowFPS(false);
		
		SpriteSheet spriteSheet = new SpriteSheet("maps/char_2.png", 64, 64);
		SpriteSheet spriteSheet2 = new SpriteSheet("maps/char_1.png", 64, 64);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
		this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
		this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
		this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
		
		
		this.animations2[0] = loadAnimation(spriteSheet2, 0, 1, 0);
		this.animations2[1] = loadAnimation(spriteSheet2, 0, 1, 1);
		this.animations2[2] = loadAnimation(spriteSheet2, 0, 1, 2);
		this.animations2[3] = loadAnimation(spriteSheet2, 0, 1, 3);
		this.animations2[4] = loadAnimation(spriteSheet2, 1, 9, 0);
		this.animations2[5] = loadAnimation(spriteSheet2, 1, 9, 1);
		this.animations2[6] = loadAnimation(spriteSheet2, 1, 9, 2);
		this.animations2[7] = loadAnimation(spriteSheet2, 1, 9, 3);
		
		robot.setSize(100, 100);
	    robot2.setSize(100, 100);
	    
		Terrain.initialiser();
		
	    int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("Collision");
	    Image tile;
	    
	    
	   // for (int i = 15*32; i < )
	    for (int i = 16*32; i <= 56*32; i+=32) {
	    	for (int j = 32; j <= 28*32; j+=32) {
	    	    tile = this.map.getTileImage((int) i / tileW, (int) j / tileH, logicLayer);
	    	    if (tile!=null) {
	    	    	Terrain.terrain[i/tileW-15][j/tileH].setCase(Contenu.Obstacle);
	    	    	Terrain.afficher();
	    	    }
	    	}
	    }
		//Terrain.afficher();

		this.e = new Entite(15, 20);
		this.e2 = new Entite(16, 19);
		
		  Music background = new Music("maps/FoxieEpic.OGG");
		    background.loop();

		
	}
	
	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		Image peinture_rouge = new Image("maps/peinture_rouge.png");
		Image peinture_bleu = new Image("maps/peinture_bleu.png");
		Image hud_bleu = new Image("maps/hud_bleu.png");		
		Image hud_rouge = new Image("maps/hud_rouge.png");
		
		Image deb_v_r = new Image("maps/debut_vie_red.png");
		Image mil_v_r = new Image("maps/milieu_vie_red.png");
		Image fin_v_r = new Image("maps/fin_vie_red.png");
		
		Image deb_v_b = new Image("maps/debut_vie_bleu.png");
		Image mil_v_b = new Image("maps/milieu_vie_bleu.png");
		Image fin_v_b = new Image("maps/fin_vie_bleu.png");


		

		this.map.render(0, 0);
		
		hud_bleu.draw(15, 15);
		hud_rouge.draw(1920-300, 15);
		
		deb_v_r.draw(this.DEBUT_VIE_ROUGE_X,this.VIE_Y);
		for (float i = this.DEBUT_VIE_ROUGE_X+7; i<  this.DEBUT_VIE_ROUGE_X+112; i+=7) {
			mil_v_r.draw(i, this.VIE_Y);
		}
		//fin_v_r.draw(this.FIN_VIE_ROUGE_X,this.VIE_Y);
		deb_v_b.draw(this.DEBUT_VIE_BLEU_X,this.VIE_Y);
		for (float i = this.DEBUT_VIE_BLEU_X+7; i<  this.DEBUT_VIE_BLEU_X+147; i+=7) {
			mil_v_b.draw(i, this.VIE_Y);
		}
		fin_v_b.draw(this.FIN_VIE_BLEU_X,this.VIE_Y);

		for(int i = 0; i<this.pos_color.size(); i++) {
			peinture_rouge.drawCentered(this.pos_color.get(i).getX(), this.pos_color.get(i).getY());
		}
		
		for(int i = 0; i<this.pos_color_2.size(); i++) {
			peinture_bleu.drawCentered(this.pos_color_2.get(i).getX(), this.pos_color_2.get(i).getY());
		}
		

			//peinture_rouge.draw(15*32+e.getLine()*32, e.getCol()*32);
			
		
				if(e.next_case().isAccessible()){
					e.Avancer(1);

				}else{
					if(Math.random()<0.5){
						switch (e.direction()){
						case Nord:e.Tourner(Direction.Est);
						break;
						case Est:e.Tourner(Direction.Sud);
						break;
						case Sud:e.Tourner(Direction.Ouest);
						break;
						case Ouest:e.Tourner(Direction.Nord);
						break;
						}
					}else{
						switch (e.direction()){
						case Nord:e.Tourner(Direction.Ouest);
						break;
						case Est:e.Tourner(Direction.Nord);
						break;
						case Sud:e.Tourner(Direction.Est);
						break;
						case Ouest:e.Tourner(Direction.Sud);
						break;
						}
					}
				}
				
			
			
			
			//peinture_bleu.draw(15*32+e2.getLine()*32, e2.getCol()*32);
			
			
			if(e2.next_case().isAccessible()){
				e2.Avancer(1);
			}else{
				if(Math.random()<0.5){
					switch (e2.direction()){
					case Nord:e2.Tourner(Direction.Est);
					break;
					case Est:e2.Tourner(Direction.Sud);
					break;
					case Sud:e2.Tourner(Direction.Ouest);
					break;
					case Ouest:e2.Tourner(Direction.Nord);
					break;
					}
				}else{
					switch (e2.direction()){
					case Nord:e2.Tourner(Direction.Ouest);
					break;
					case Est:e2.Tourner(Direction.Nord);
					break;
					case Sud:e2.Tourner(Direction.Est);
					break;
					case Ouest:e2.Tourner(Direction.Sud);
					break;
					}
				}
			}
			
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			//System.out.println(e.toString());
		

		g.drawAnimation(animations[direction + (moving ? 4 : 0)], x - 32, y - 60);
		g.drawAnimation(animations2[direction2 + (moving2 ? 4 : 0)], xx - 32, yy - 60);

		//System.out.println("( "+ x + " , " + y + " ) ");

	}

	
	
//	@Override
//	public void render(GameContainer container, Graphics g) throws SlickException {
//		this.map.render(0, 0);
//}
	
	private boolean isCollision(float x, float y) {
	    int tileW = this.map.getTileWidth();
	    int tileH = this.map.getTileHeight();
	    int logicLayer = this.map.getLayerIndex("Collision");
	    Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
	    boolean collision = tile != null;
	    if (collision) {
	        Color color = tile.getColor((int) x % tileW, (int) y % tileH);
	        collision = color.getAlpha() > 0;
	    }
	    return collision;
	}


	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (this.moving) {
			switch (this.direction) {
			case 0:
				if (canmove) {
					if (!isCollision(this.x, this.y - (1024/32))) {
						this.y -= (1024/32);
						
						Point p = new Point(this.x, this.y);
						for(int i = 0; i<this.pos_color.size(); i++){
							if (this.pos_color.get(i).equals(p)) {
								this.pos_color.remove(this.pos_color.get(i));
							}
						}
						
						this.pos_color.add(p);
							
						for(int i = 0; i<this.pos_color_2.size(); i++){
							if (this.pos_color_2.get(i).equals(p)) {
								this.pos_color_2.remove(this.pos_color_2.get(i));
							}
						}
						
						//this.container.getGraphics().drawImage(grass_b, x, y);
						//arg0.getGraphics().drawImage(grass_b, x, y);
						//grass_b.draw(x, y);


					}

				}
				//System.out.println("pos : " + x + " - " + y);
				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;
			case 1:
				if (canmove) {
					if (!isCollision(this.x - (1024/32), this.y))
					this.x -= (1024/32);
					Point p = new Point(this.x, this.y);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					
					this.pos_color.add(p);

						
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
				}
				//System.out.println("pos : " + x + " - " + y);

				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;
			case 2:
				if (canmove) {
					if (!isCollision(this.x, this.y + (1024/32)))
					this.y += (1024/32);
					Point p = new Point(this.x, this.y);
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					
					this.pos_color.add(p);
					
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
				}
				//System.out.println("pos : " + x + " - " + y);

				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;
			case 3:
				if (canmove) {
					if (!isCollision(this.x + (1024/32), this.y))
					this.x += (1024/32);
					
					Point p = new Point(this.x, this.y);
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					
					this.pos_color.add(p);
					
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
				}
				//System.out.println("pos : " + x + " - " + y);

				if (System.currentTimeMillis()-lasttime<500) {
					canmove = false;
				} else {
					lasttime = System.currentTimeMillis();
					canmove = true;
				}
				break;

			}
}
		if (this.moving2) {
			switch (this.direction2) {
			case 0:
				if (canmove2) {
					if (!isCollision(this.xx, this.yy - (1024/32)))
					this.yy -= (1024/32);
					Point p = new Point(this.xx, this.yy);
					
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}				
				break;
			case 1:
				if (canmove2) {
					if (!isCollision(this.xx - (1024/32), this.yy))
					this.xx -= (1024/32);
					Point p = new Point(this.xx, this.yy);
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}	
				break;
			case 2:
				if (canmove2) {
					if (!isCollision(this.xx, this.yy + (1024/32)))
					this.yy += (1024/32);
					Point p = new Point(this.xx, this.yy);
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}	
				break;
			case 3:
				if (canmove2) {
					if (!isCollision(this.xx + (1024/32), this.yy))
					this.xx += (1024/32);
					Point p = new Point(this.xx, this.yy);
					for(int i = 0; i<this.pos_color_2.size(); i++){
						if (this.pos_color_2.get(i).equals(p)) {
							this.pos_color_2.remove(this.pos_color_2.get(i));
						}
					}
					
					this.pos_color_2.add(p);
					
					for(int i = 0; i<this.pos_color.size(); i++){
						if (this.pos_color.get(i).equals(p)) {
							this.pos_color.remove(this.pos_color.get(i));
						}
					}
					

				}
				if (System.currentTimeMillis()-lasttime2<500) {
					canmove2 = false;
				} else {
					lasttime2 = System.currentTimeMillis();
					canmove2 = true;
				}	
				break;

			}
}

	}
	
	
	@Override
	public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
        //System.out.println(key + "- " + c);
		switch (key) {
		case Input.KEY_UP:
			this.moving = false;
			break;
		case Input.KEY_LEFT:
			this.moving = false;

			break;
		case Input.KEY_DOWN:
			this.moving = false;

			break;
		case Input.KEY_RIGHT:
			this.moving = false;

			break;
		case Input.KEY_Z:
			this.moving2 = false;

			break;
		case Input.KEY_Q:
			this.moving2 = false;

			break;
		case Input.KEY_S:
			this.moving2 = false;

			break;
		case Input.KEY_D:
			this.moving2 = false;

			break;
		}
    }
	
	@Override
	public void keyPressed(int key, char c) {
	    switch (key) {
	        case Input.KEY_UP: this.direction = 0; this.moving = true; break;
	        case Input.KEY_LEFT: this.direction = 1; this.moving = true; break;
	        case Input.KEY_DOWN: this.direction = 2; this.moving = true; break;
	        case Input.KEY_RIGHT: this.direction = 3; this.moving = true; break;
	        
	        case Input.KEY_R: 
	        	JOptionPane r = new JOptionPane(); 
	        	r.setSize(d);
	        	String[] bouton ={"Créer","Modifier"};
	        	int retour = r.showOptionDialog(null, "Faite votre choix", "Menu des robots", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this.ic, bouton, bouton[0]);
	        	if (retour == 1 ){
	        		JOptionPane.showInputDialog(robot);
	        		
	        	}if (retour == 0){
	        		JOptionPane p = new JOptionPane();
	        		p.showInputDialog(tab, "Saisissez votre expression");
	        	}
	        	break;
	        
	        
	        case Input.KEY_T: 
	        	
	        	JOptionPane r2 = new JOptionPane(); 
	        	r2.setSize(d);
	        	String[] bouton2 ={"Créer","Modifier"};
	        	int retour2 = r2.showOptionDialog(null, "Faite votre choix", "Menu des robots", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, this.ic, bouton2, bouton2[0]);
	        	if (retour2 == 1 ){
	        		JOptionPane.showInputDialog(robot2); 
	        	}if (retour2 == 0){
	        		JOptionPane.showInputDialog(tab2, "Saisissez votre expression");
	        	}
	        	break;
	        
	        case Input.KEY_Z:    this.direction2 = 0; this.moving2 = true; break;
	        case Input.KEY_Q:  this.direction2 = 1; this.moving2 = true; break;
	        case Input.KEY_S:  this.direction2 = 2; this.moving2 = true; break;
	        case Input.KEY_D: this.direction2 = 3; this.moving2 = true; break;
	    }
	}

	 public static void main(String[] args) throws SlickException {
		 AppGameContainer game = new AppGameContainer(new View(), 1920, 960, false);
		 //game.setFullscreen(true);
		 //game.setDisplayMode(1920, 1080, true);
		 game.start();
		 //new AppGameContainer(new View(), 1920, 960, false).start();
	 }

}
