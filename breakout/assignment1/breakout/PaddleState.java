package breakout;

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
	public void setTl(Point tl) {
		this.tl = tl;
	}
	public Point getBr() {
		return br;
	}
	public void setBr(Point br) {
		this.br = br;
	}
	
	
	

}
