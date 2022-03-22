package breakout;
/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 * 
 *@immutable
 */

public class PaddleState {
	// TODO: implement
	
	/**
	 * Representation invariants:
	 * 
	 * @invar | tl.getX() <= br.getX()
	 * @invar | tl.getY() <= br.getY()
	 */

	private final Point tl;
	private final Point br;
	private final int velocity;


	
	/**
	 * 
	 * @pre | tl != null
	 * @pre | br != null
	 * 
	 * @post | getTl() == tl
	 * @post | getBr() == br
	 */


	public PaddleState(Point tl, Point br,int velocity) {
		this.tl = tl;
		this.br = br;
		this.velocity = velocity;
	}

	public Point getTl() {
		return tl;
	}

	public Point getBr() {
		return br;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	/**
	 * 
	 * @post | result !=null
	 * @creates | result
	 */

	public Point getPosition() {
		Point center = new Point((this.tl.getX()+this.br.getX())/2,(this.tl.getY()+this.br.getY())/2);
		return center;
	}
	
	/**
	 * 
	 * @creates | result
	 */
	
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}
	



}
