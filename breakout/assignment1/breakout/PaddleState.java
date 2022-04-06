package breakout;
/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 * 
 * @immutable
 */

public class PaddleState extends Rectangle{
	// TODO: implement
	
	private final int velocity;

	/**
	 * 
	 * @pre | tl != null
	 * @pre | br != null
	 * @pre | tl.getX() <= br.getX()
	 * @pre | tl.getY() <= br.getY()
	 * 
	 * @post | getTl() == tl
	 * @post | getBr() == br
	 */


	public PaddleState(Point tl, Point br,int velocity) {
		super(tl,br);
		this.velocity = velocity;
	}


	public int getVelocity() {
		return velocity;
	}
	
	

}
