package breakout;

/**
 * Abstract state invariants:
 * @immutable
 * @invar | getCenter() !=null
 * @invar | getVelocity() !=null
 * @invar | getTl() !=null
 * @invar | getBr() !=null
 * @invar | getSize() >= 0
 * @invar | getRadius() >= 0
 * 
// * @invar | 0<=getTl().getX() && getTl().getX()<=GameMap.getWidth()-(getBr().getX()-getTl().getX())
// * @invar | getTl().getX()<=getBr().getX() && getBr().getX()<=GameMap.getWidth()
 * 
 */

public class BallState {
	// TODO: implement
	
	
	/**
	 * Representation invariants
	 * 
	 * @invar | tl !=null
	 * @invar | br !=null
	 * @invar | velocity !=null
	 */
	private final Point tl;
	private final Point br;
	private final Vector velocity;
	

	/**
	 * Initializes this object with the given tl, br, velocity.
	 * @pre | tl != null
	 * @pre | br != null
	 * @pre | velocity != null
	 * @pre | tl.getX() <= br.getX()
	 * @pre | tl.getY() <= br.getY()
	 * 
	 * 
	 * 
	 * 
	 * @post | getTl() == tl
	 * @post | getBr() == br
	 * @post | getVelocity() == velocity
	 * 
	 */
	public BallState(Point tl, Point br, Vector velocity) {
		this.tl = tl;
		this.br = br;
		this.velocity = velocity;
	}
	
	
	/**
	 * @post | result != null
	 * @create | result
	 */
	public Point getCenter() {
		Point center = new Point((this.tl.getX()+this.br.getX())/2,(this.tl.getY()+this.br.getY())/2);
		return center;
	}
	

	public Vector getVelocity() {
		return this.velocity;
	}
	

	public Point getTl() {
		return tl;
	}

	public Point getBr() {
		return br;
	}

	/**
	 * @post | result >= 0
	 * @create | result 
	 */
	public int getSize() {
		double diameter = (br.getY()-tl.getY())*Math.sqrt(2);
		return (int) Math.round((Math.PI*(diameter/2)*(diameter/2)));

	}
	
	/**
	 * @post | result >= 0
	 * @create | result
	 */
	public int getRadius() {
		int radius = (br.getY()-tl.getY())/2;
		return radius;
	}
	
	
	

	



	




}
