package breakout;
/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 //* @invar | getTl().getX()>=0 && getBr().getX()<= 右邊界
 //* @invar | getTl().getY()>=最後一個block.getBr().getY && getBr().getY()<= 下邊界 
 * 
 *@immutable
 */

public class PaddleState {
	// TODO: implement

	private final Point tl;
	private final Point br;
	
	private final int velocity;


	public int getVelocity() {
		return velocity;
	}


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

	public void getPosition() {

	}

	public void getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
	}
	
	public PaddleState move(int direction) {
		return new PaddleState(new Point(tl.getX()+direction,tl.getY()),new Point(br.getX()+direction,br.getY()),direction);
		
	}
	
	//public PaddleState clone() {
		//return new PaddleState(new Point(tl.getX(),tl.getY()),new Point(br.getX(),br.getY()),getVelocity());
		
	//}
	
	



}
