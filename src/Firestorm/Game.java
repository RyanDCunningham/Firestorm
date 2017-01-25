package Firestorm;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Input.KeyInput;
import Input.MouseInput;
import States.GameState;
import States.MenuState;
import States.StateManager;
import Textures.Sprite;
import Textures.SpriteSheet;
import Textures.Texture;

public class Game extends Canvas implements Runnable{

	public static final String TITLE = "Firestorm";
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 4 * 3;
	
	private boolean running;
	
	//private Menu menu;
	private StateManager stateManager;
	
	public static Game INSTANCE;
	
	 //Texture/Sprite Variables 
	/*
 	private Texture texture, t2, t3, t4;
	private SpriteSheet sheet;
	private Sprite sprite, s2;
	private double sX = 350, sY = 300;
	*/
	
	public Game() {

		/*
		texture = new Texture("rocks");
		t2 = new Texture("rocks");
		t3 = new Texture("rocks");
		t4 = new Texture("rocks");
		sheet = new SpriteSheet(new Texture("test_sheet"), 64);
		sprite = new Sprite(sheet, 3, 1);
		s2 = new Sprite(sheet, 1, 2);
		*/
		
		
		addKeyListener(new KeyInput());
		MouseInput mi = new MouseInput();
		addMouseListener(mi);
		addMouseMotionListener(mi);
		//menu = new Menu();
		stateManager = new StateManager();
		
		stateManager.addState(new MenuState());
		stateManager.addState(new GameState());
		
		INSTANCE = this;
		//System.out.println("Button 1: " + MouseEvent.BUTTON1);
		//System.out.println("Button 2: " + MouseEvent.BUTTON2);
		//System.out.println("Button 3: " + MouseEvent.BUTTON3);
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(-6,  -28);
		
		///////////////////
		
		//Art goes here
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		stateManager.render(g2d);
		
		//  Texture/Sprite Tests 
		/*texture.render(g, 100, 100);
		t2.render(g, 100, 150);
		t3.render(g, 150, 100);
		t4.render(g, 200, 200);
		sprite.render(g, sX, sY);
		s2.render(g, 0, 0);
		*/
		
		/////////////////// 
		
		g.dispose();
		bs.show();
		
	}
	
	private void tick(){
		
		stateManager.tick();
		
		//menu.tick();
		//debugging stuff
		/*if(KeyInput.isDown(KeyEvent.VK_SPACE)) {
			sY -= 2;
		}
		if(KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
			sY = 300;
			System.out.println("ENTER WAS PRESSED, & Your Mother");
		}
		if(MouseInput.isDown(MouseEvent.BUTTON1)){
			System.out.println("Left is Pressed");
		}
		if(MouseInput.wasPressed(MouseEvent.BUTTON2)) {
			System.out.println("Middle was Pressed");
		}
		if(MouseInput.wasReleased(MouseEvent.BUTTON3)) {
			System.out.println("Right was Released");
		}
		
		if(KeyInput.isDown(KeyEvent.VK_F) && MouseInput.isMoving()) {
			System.out.println("X: " + MouseInput.getX() + ", Y: " + MouseInput.getY());
		}*/
		
	}
	
	public void stop() {
		if(!running) return;
		running = false;
	}
	
	protected void start(){
		
		if(running) return;
		running = true;
		new Thread(this, "FirestormMain-Thread").start();
		
	}
	
	@Override
	public void run() {
		requestFocus();
		// TODO relax
		double target = 60.0;
		double nsPerTick = 1000000000.0 / target;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double unprocessed = 0.0;
		int fps = 0;
		int tps = 0;
		boolean canRender = false;
		
		System.out.println("running");
		while(running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			if(unprocessed >= 1.0) {
				tick();
				KeyInput.update();
				MouseInput.update();
				unprocessed--;
				tps++;
				canRender = true;
			}else canRender = false;
			
			try {
			Thread.sleep(1);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			
			if(canRender) {
				render();
				fps++;
			}
			
			if(System.currentTimeMillis() - 1000 > timer){
				timer += 1000;
				System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
				fps = 0;
				tps = 0;
			}
			
		}
		
		System.exit(0);
		
	}
}