package breakout;

/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 * 
 * @invar | 0<=getTl().getX() && getTl().getX()<=GameMap.getWidth()-(getBr().getX()-getTl().getX())
 * @invar | getTl().getX()<=getBr().getX() && getBr().getX()<=GameMap.getWidth()
 * 
 * 
 * @immutable
 */

public abstract class Rectangle {
	
	
	/**
	 * @invar | tl != null
	 * @invar | br != null
	 */
	
	private final Point tl;
	private final Point br;
	
	private final static int WIDTH = GameMap.getWidth();
	private final static int HEIGTH = GameMap.getHeight();
	
	/**
	 * 
	 * @pre | tl != null
	 * @pre | br != null
	 * @pre | tl.getX() <= br.getX()
	 * @pre | tl.getY() <= br.getY()
	 * @post | 0 <= tl.getX() 
	 * @post | tl.getX() <= br.getX() && br.getX() <= GameMap.getWidth()
	 * 	
	 * @post | getTl() == tl
	 * @post | getBr() == br
	 */
	public Rectangle(Point tl, Point br) {
		this.tl = tl;
		this.br = br;
	}
	
	public Point getTl() {
		return tl;
	}

	public Point getBr() {
		return br;
	}
	
	/**
	 * 
	 * @create | result
	 * @inspect | this
	 * @post | result != null
	 * @post | getTl().getX()< result.getX() && result.getX() < getBr().getX() 
	 * @post | getTl().getY()< result.getY() && result.getY() < getBr().getY() 
	 */
	public Point getPosition() {
		Point center = new Point((tl.getX()+br.getX())/2, (tl.getY()+br.getY())/2);
		return center;
	}
	
	/**
	 * @post | result == (getBr().getX()-getTl().getX()) * (getBr().getY()-getTl().getY())
	 * @post | result >= 0
	 */
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}
	
	
	
	

}
