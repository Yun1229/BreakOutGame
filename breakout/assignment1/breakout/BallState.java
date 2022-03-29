package breakout;

/**
 * Abstract state invarient:
 * @immutable
 *
 */

public class BallState {
	// TODO: implement


	private final Point tl;
	private final Point br;
	private final Vector velocity;
	


	public BallState(Point tl, Point br, Vector velocity) {
		this.tl = tl;
		this.br = br;
		this.velocity = velocity;
	}
	

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

	
	public int getSize() {
		double diameter = (br.getY()-tl.getY())*Math.sqrt(2);
		System.out.println(Math.PI*(diameter/2)*(diameter/2));
		return (int) Math.round((Math.PI*(diameter/2)*(diameter/2)));

	}
	
	

	



	




}
