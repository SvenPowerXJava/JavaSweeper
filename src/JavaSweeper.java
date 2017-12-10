import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {
	
	private Game game;
	
	private JPanel panel;
	private final int COLS = 9;
	private final int ROWS = 9;
	private final int BOMBS = 10;
	private final int IMAGE_SIZE = 50;
	
	public static void main(String[] args) {
		new JavaSweeper();
	}
	
	private JavaSweeper() throws HeadlessException {
		game = new Game(COLS, ROWS, BOMBS);
		game.start();
		setImages();
		initPanel();
		initFrame();
	}
	
	private void initPanel() {
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (Coord coord : Ranges.getAllCoords()) {
					g.drawImage((Image)game.getBox(coord).image,
							coord.getX()*IMAGE_SIZE,
							coord.getY()*IMAGE_SIZE,
							this);
				}
//				g.drawImage(getImage("bomb"),0,0,this);
//				g.drawImage(getImage("num1"), IMAGE_SIZE, 0, this);
			}
		};
		panel.setPreferredSize(
				new DimensionUIResource(
						Ranges.getSize().getX() * IMAGE_SIZE,
						Ranges.getSize().getY() * IMAGE_SIZE));
		add(panel);
	}
	
	private void initFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java Sweeper");
		setLocationRelativeTo(null); //Устанавливаем окошко по центру
		setResizable(false);
		setVisible(true);
		setIconImage(getImage("icon"));
		pack();
	}
	
	private void setImages() {
		for (Box box : Box.values()) {
			box.image = getImage(box.name());
		}
	}
	
	private Image getImage(String name) {
		String filename = "img/" + name.toLowerCase() + ".png";
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		return icon.getImage();
	}
}
