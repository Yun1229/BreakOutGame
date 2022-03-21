package breakout;

import java.util.Arrays;

/**
 * Abstract state invarient:
 * @immutable
 *
 */

public class BallState {
	// TODO: implement


	private Point tl;
	private Point br;
	private Vector velocity;
	


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

	



	




}
