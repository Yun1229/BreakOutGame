package breakout;

public class BlockState {
	// TODO: implement
	private Point tl;
	private Point br;
	private boolean removed=false;


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
	
	

	public boolean isRemoved() {
		return removed;
	}

	//判斷球有無撞到
	public boolean isHit() {
		boolean hit = false;
		for (BallState ball : balls) {
			int ballTY=ball.getTl().getY();
			int ballBY=ball.getBr().getY();
			int ballLX=ball.getTl().getX();
			int ballRX=ball.getBr().getX();

			int blockTY=this.getTl().getY();
			int blockBY=this.getBr().getY();
			int blockLX=this.getTl().getX();
			int blockRX=this.getBr().getX();

			if (ballLX == blockRX || ballRX == blockLX) {
				if (ballTY>=blockTY && blockBY>=ballTY) {
					hit = true;
				}
				if (ballBY>=blockTY && blockBY>=ballBY) {
					hit = true;
				}
			}

			if(ballTY == blockBY || ballBY == blockTY) {
				if (ballLX>=blockRX && ballLX<=blockLX) {
					hit = true;
				}
				if (ballRX>=blockLX && ballRX<=blockRX) {
					hit = true;
				}
			}

		}
		return hit;

	}






}
