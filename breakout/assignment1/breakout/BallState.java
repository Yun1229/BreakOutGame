package breakout;

public class BallState {
	// TODO: implement
	
	private Point tl;
	private Point br;
	private Vector velocity;
	
	
	public Point getCenter() {
		Point center = new Point((this.tl.getX()+this.br.getX())/2,(this.tl.getY()+this.br.getY())/2);
		return center;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
}
