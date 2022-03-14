package breakout;
/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 //* @invar | getTl().getX()>=0 && getBr().getX()<= 右邊界
 //* @invar | getTl().getY()>=最後一個block.getBr().getY && getBr().getY()<= 下邊界 
 * 
 *
 */

public class PaddleState {
	// TODO: implement

	private Point tl;
	private Point br;



	public PaddleState(Point tl, Point br) {
		this.tl = tl;
		this.br = br;
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



}
