package breakout;

import java.util.Arrays;

// TODO: implement, document
public class BreakoutState {
	
	private BallState[] balls;
	private BlockState[] blocks;
	private PaddleState paddle;
	private Point bottomRight;

	
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight,PaddleState paddle ) {
		this.balls = balls;
		this.blocks = blocks;
		this.paddle = paddle;
		this.bottomRight = bottomRight;
	}


	
	public BallState[] getBalls() {
		return this.balls;
	}

	public BlockState[] getBlocks() {
		
		return this.blocks;
	}

	public PaddleState getPaddle() {
		return this.paddle;
	}

	public Point getBottomRight() {
		return this.bottomRight;
	}

	public void tick(int paddleDir) {
	}

	public void movePaddleRight() {
		paddle.setBr(new Point(paddle.getBr().getX()+10,paddle.getBr().getY()));
		paddle.setTl(new Point(paddle.getTl().getX()+10,paddle.getTl().getY()));
	}

	public void movePaddleLeft() {
		paddle.setBr(new Point(paddle.getBr().getX()-10,paddle.getBr().getY()));
		paddle.setTl(new Point(paddle.getTl().getX()-10,paddle.getTl().getY()));
		
	}
	
	public boolean isWon() {
		if(Arrays.stream(blocks,0,blocks.length-1).allMatch(e -> e.isRemoved()==true)){
			return true;
		}
		
		return false;
	}

	public boolean isDead() {
		if(Arrays.stream(balls,0,balls.length-1).allMatch(e -> e.isRemoved()==true)){
			return true;
		}
		return false;
	}
}
