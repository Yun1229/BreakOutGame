package breakout;

import java.util.ArrayList;
import java.util.Arrays;

import gui.GameView;

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
		ArrayList<BallState> newballs2 = new ArrayList<BallState>(); 
		for (BallState ball : balls) {
			BallState newball = new BallState(ball.getTl().plus(ball.getVelocity()),ball.getBr().plus(ball.getVelocity()),ball.getVelocity());		
			newballs2.add(newball);
		}

		return newballs2.toArray(balls);
	}

	public BlockState[] getBlocks() {
		ArrayList<BlockState> newblocks = new ArrayList<BlockState>();
		int i=0;
		//判斷球有無撞到block
		ArrayList<BallState> newballs = new ArrayList<BallState>();
		for (BallState ball : balls) {
			boolean ballreflectX = false;
			boolean ballreflectY = false;
			for (BlockState block: blocks) {
				boolean hit = false;
				int ballTY=ball.getTl().getY();
				int ballBY=ball.getBr().getY();
				int ballLX=ball.getTl().getX();
				int ballRX=ball.getBr().getX();

				int blockTY=block.getTl().getY();
				int blockBY=block.getBr().getY();
				int blockLX=block.getTl().getX();
				int blockRX=block.getBr().getX();
				if (ballLX <= blockRX && ballRX >= blockLX && blockBY >= ballTY && blockTY <= ballBY) {
					hit = true;
					i++;
					if (ballLX <= blockRX&&ballRX >= blockRX|| ballRX >= blockLX&&ballLX <= blockLX) {
						ballreflectX=true;
					}
					if (blockBY >= ballTY&&blockBY <= ballBY || blockTY <= ballBY&&blockTY >= ballTY) {
						
						ballreflectY=true;
					}
				}
				if (hit == false) {
					newblocks.add(block);
				}

			} 
			if(ballreflectY) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (ball.getVelocity().getX(),-ball.getVelocity().getY()));
				ball=newball;
			}
			if(ballreflectX) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (-ball.getVelocity().getX(),ball.getVelocity().getY()));
				ball=newball;
			}
			newballs.add(ball);
		}
		newballs.toArray(balls);
		BlockState[] newblocksarray = new BlockState[newblocks.size()];
		newblocks.toArray(newblocksarray);
		blocks = newblocksarray;
		return newblocksarray;
	}

	public PaddleState getPaddle() {
		PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(paddle.getVelocity(),0)),paddle.getBr().plus(new Vector(paddle.getVelocity(),0)),0);
		paddle = newpaddle;
		return paddle;
	}

	public Point getBottomRight() {
		return this.bottomRight;
	}

	public void tick(int paddleDir) {

		ArrayList<BallState> newballs2 = new ArrayList<BallState>();
		for (BallState ball:getBalls()) {
			if (ball.getBr().getY() >= getPaddle().getTl().getY()&&ball.getTl().getY() <= getPaddle().getBr().getY() && ball.getBr().getX()>= getPaddle().getTl().getX() && ball.getTl().getX()<= getPaddle().getBr().getX()) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (ball.getVelocity().getX()+2*paddleDir,-ball.getVelocity().getY()));
				ball=newball;
			}
			if (ball.getBr().getX() >= GameMap.getWidth()) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (-ball.getVelocity().getX(),ball.getVelocity().getY()));
				ball=newball;
			}
			if (ball.getTl().getX() <= 0) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (-ball.getVelocity().getX(),ball.getVelocity().getY()));
				ball=newball;
			}
			if (ball.getTl().getY() <= 0) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (ball.getVelocity().getX(),-ball.getVelocity().getY()));
				ball=newball;
			}
				newballs2.add(ball);

		}

		newballs2.toArray(balls);

		
		getBlocks();
		getBalls();
		getPaddle();
	}

	public void movePaddleRight() {
		if (paddle.getBr().getX() <=GameMap.getWidth()) {
		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),100);
		paddle = newpaddle;
		} 
	}

	public void movePaddleLeft() {
		if (paddle.getTl().getX() >=0) {
			PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),-100);
			paddle = newpaddle;
		}
		
	}

	public boolean isWon() {
		if(getBlocks().length == 0){
			return true;
		} else {

			return false;
		}
	}

	public boolean isDead() {
		if(Arrays.stream(getBalls(),0,getBalls().length).allMatch(e -> e.getBr().getY()>=GameMap.getHeight())){
			return true;
		} else {

			return false;
		}

	}

	








}
