package breakout;
/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 //* @invar | getTl().getX()>=0 && getBr().getX()<= �k���
 //* @invar | getTl().getY()>=�̫�@��block.getBr().getY && getBr().getY()<= �U��� 
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
