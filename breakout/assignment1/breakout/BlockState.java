package breakout;

/**
 * 
 * Abstract state invariants:
// * @invar | getTl() != null
// * @invar | getBr() != null
 * 
 * @immutable
 */


public class BlockState extends Rectangle{
	// TODO: implement
	
//	/**
//	 * Representation invariants:
//	 * 
//	 * @invar | tl.getX() <= br.getX()
//	 * @invar | tl.getY() <= br.getY()
//	 */
//	private final Point tl;
//	private final Point br;
//	
	
	/**
	 * 
	 * @pre | tl != null
	 * @pre | br != null
	 * 
//	 * @post | getTl() == tl
//	 * @post | getBr() == br
	 */
 public BlockState(Point tl, Point br) {
		super(tl, br);
	}



//	public Point getTl() {
//		return this.tl;
//	}
//
//
//	
//
//	public Point getBr() {
//		return this.br;
//	}
//	
//	/**
//	 * 
//	 * @post | result !=null
//	 * @creates | result
//	 */
//	
//
//	public Point getPosition() {
//		Point center = new Point((tl.getX()+br.getX())/2, (tl.getY()+br.getY())/2);
//		return center;
//	}
//	
//	/**
//	 * 
//	 * @creates | result
//	 */
//	
//	public int getSize() {
//		int width = br.getY()-tl.getY();
//		int length = br.getX()-tl.getX();
//		return width*length;
//	}






}
