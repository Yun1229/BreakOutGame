package breakout;

/**
 * @immutable
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 //* @invar | getTl().getX()>=0 && getBr().getX()<= 右邊界
 //* @invar | getTl().getY()>=0 && getBr().getX()<= 下邊界 or paddle.getTl().getY
 * 
 * 
 *
 */

public class BlockState {
	// TODO: implement
	private Point tl;
	private Point br;
	private boolean removed=false;
	private BallState[] balls;
	
	
	/**
	 * 
	 * @pre 
	 * @pre 
	 */

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
	
	

	public boolean isRemoved() {
		return removed;
	}

	
	
	public void getPosition() {
		
	}
	
	public void getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
	}






}
