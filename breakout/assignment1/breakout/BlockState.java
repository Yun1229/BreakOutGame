package breakout;

/**
 * @immutable
 **/


public class BlockState {
	// TODO: implement
	private Point tl;
	private Point br;
	private BallState[] balls;
	

	public BlockState(Point tl, Point br) {
		this.tl = tl;
		this.br = br;
	}

	public Point getTl() {
		return this.tl;
	}


	public Point getBr() {
		return this.br;
	}
	

	public Point getPosition() {
		Point center = new Point((tl.getX()+br.getX())/2, (tl.getY()+br.getY())/2);
		return center;
	}
	
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}






}
