package breakout;

/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 * 
 * @immutable
 */


public class BlockState extends Rectangle{
	// TODO: implement

	
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
 public BlockState(Point tl, Point br) {
		super(tl, br);
	}






}
