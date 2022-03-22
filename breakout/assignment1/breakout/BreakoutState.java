package breakout;


import java.util.ArrayList;
import java.util.Arrays;

// TODO: implement, document
/**
 * Abstract state invariants
 * @invar | getBalls() != null
 * @invar | getBlocks() != null
 * @invar | getPaddle() != null
 * @invar | getBottomRight() != null
 * @invar | 0 <= getBalls().length
 * @invar | 0 <= getBlocks().length
 *
 */

public class BreakoutState {
	
	/**
	 * Representation invariants
	 * 
	 * @invar | balls != null
     * @invar | blocks != null
     * @invar | paddle != null
     * @invar | bottomRight != null
	 * @invar | 0 <= balls.length
	 * @invar | 0 <= blocks.length
     * @representationObject
	 */

	private BallState[] balls;
	private BlockState[] blocks;
	private PaddleState paddle;
	private Point bottomRight;
	private static final int BOUNDARY = GameMap.getWidth();
	
	
	
	/**
	 * Initializes this object with the given balls, blocks, bottomRight and paddle.
	 * 
	 * @post | Arrays.equals(getBalls(),balls) 
	 * @post | Arrays.equals(getBlocks(),blocks) 
	 * @post | getBottomRight() == bottomRight
//	 * @post | getPaddle() == paddle
	 * @post some element in the balls equals ball.
//	 *   | Arrays.stream(balls,0,balls.length - 1).anyMatch(e -> e == ball) //?
	 * @post some element in the blocks equals block.
//	 *   | Arrays.stream(blocks,0,blocks.length - 1).anyMatch(e -> e == block) //?
	 * @throws IllegalArgumentException
	 *   | balls == null | blocks == null | paddle == null | bottomRight == null
	 * @throw IllegalArgumentException
	 *   | Arrays.stream(balls).anyMatch(e -> e == null)
	 *   | Arrays.stream(blocks).anyMatch(e -> e == null)
//	 * @inspect | balls | blocks | paddle | bottomRight //no need @inspect? because having getter
	 */

	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight,PaddleState paddle ) {
		
		if ( balls == null | blocks == null | paddle == null | bottomRight == null ) {
			throw new IllegalArgumentException("The input parameters should not be null.");
		}
		
		if (Arrays.stream(balls).anyMatch(e -> e == null)) {
			throw new IllegalArgumentException("BallState array should not be null.");
		}
		
		if (Arrays.stream(blocks).anyMatch(e -> e == null)) {
			throw new IllegalArgumentException("BlockState array should not be null.");
		}
		
		this.balls = balls;
		this.blocks = blocks;
		this.paddle = paddle;
		this.bottomRight = bottomRight;
	}
	
	
	/**
	 * @post | result != null
	 * @post | 0 <= result.length
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result
	 * @inspect | this
	 */

	public BallState[] getBalls() {
		ArrayList<BallState> newballs2 = new ArrayList<BallState>(); 
		for (BallState ball : balls) {
			BallState newball = new BallState(ball.getTl().plus(ball.getVelocity()),ball.getBr().plus(ball.getVelocity()),ball.getVelocity());		
			newballs2.add(newball);
		}
		newballs2.toArray(balls);
		
 
		return newballs2.toArray(balls);
	}
	
	/**
	 * @post | result != null
	 * @post | 0 <= result.length
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result
	 * @inspect | this
	 */

	public BlockState[] getBlocks() {
		
		int i=0;
		//判斷球有無撞到block
		ArrayList<BallState> newballs = new ArrayList<BallState>();
		for (BallState ball : balls) {
			ArrayList<Point> blockstl = new ArrayList<Point>();
			ArrayList<BlockState> newblocks = new ArrayList<BlockState>();
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
					blockstl.add(block.getTl());
					Vector balltoblockvector= new Vector((blockRX+blockLX)/2-ball.getCenter().getX(),(blockBY+blockTY)/2-ball.getCenter().getY());
					
					if (ballLX <= blockRX&&ballRX >= blockRX|| ballRX >= blockLX&&ballLX <= blockLX) {
						ballreflectX=true;
					}
					if (blockBY >= ballTY&&blockBY <= ballBY || blockTY <= ballBY&&blockTY >= ballTY) {
						ballreflectY=true;
					}
					if(balltoblockvector.getX()*ball.getVelocity().getX()<0&&ballreflectX&&ballreflectY) {
						ballreflectX=false;
					}
					if(balltoblockvector.getY()*ball.getVelocity().getY()<0&&ballreflectX&&ballreflectY) {
						ballreflectY=false;
					}
				}
				if (hit == false) {
					newblocks.add(block);
				}

			} 
			if(blockstl.size() == 1||blockstl.size() == 3) {
				if(ballreflectY) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.DOWN));
					ball=newball;
				}
				if(ballreflectX) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.RIGHT));
					ball=newball;
				}
			}
			if(blockstl.size() == 2) {
				if(blockstl.get(0).getY() == blockstl.get(1).getY()) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.DOWN));
					ball=newball;
				}
				if(blockstl.get(0).getX() == blockstl.get(1).getX()) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.RIGHT));
					ball=newball;
				}
			}
			
			newballs.add(ball);
			BlockState[] newblocksarray = new BlockState[newblocks.size()];
			newblocks.toArray(newblocksarray);
			blocks = newblocksarray;
			
			
		}
		newballs.toArray(balls);

		return blocks;
	}
	
	/**
	 * @post | result != null
	 * @creates | result
	 * @inspect | this
	 */

	public PaddleState getPaddle() {
		PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(paddle.getVelocity(),0)),paddle.getBr().plus(new Vector(paddle.getVelocity(),0)),0);
		paddle = newpaddle;
		return paddle;
	}
	
	/**
	 * @post | result != null
	 */

	public Point getBottomRight() {
		return this.bottomRight;
	}
	
	/**
	 * @pre | paddleDir == 0 || paddleDir == 1 || paddleDir == -1
	 * @post | 0 <= getBalls().length
	 * @throws IllegalArgumentException
	 *   | paddleDir !=0 || paddleDir != 1 || paddleDir != -1
	 * @creates | result
	 * @inspect | this //add?
	 */

	public void tick(int paddleDir) {

		ArrayList<BallState> newballs2 = new ArrayList<BallState>();
		for (BallState ball: balls) {
			// ball touch paddle
			if (ball.getBr().getY() >= getPaddle().getTl().getY()&&ball.getTl().getY() <= getPaddle().getBr().getY() && ball.getBr().getX()>= getPaddle().getTl().getX() && ball.getTl().getX()<= getPaddle().getBr().getX()) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (ball.getVelocity().getX()+getPaddle().getVelocity()*(1/5)*paddleDir,-ball.getVelocity().getY()));
				ball=newball;
			}
			// ball touch GameMapRight
			if (ball.getBr().getX() >= GameMap.getWidth()) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (-ball.getVelocity().getX(),ball.getVelocity().getY()));
				ball=newball;
			}
			// ball touch GameMapLeft
			if (ball.getTl().getX() <= 0) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (-ball.getVelocity().getX(),ball.getVelocity().getY()));
				ball=newball;
			}
			// ball touch GameMapTop
			if (ball.getTl().getY() <= 0) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (ball.getVelocity().getX(),-ball.getVelocity().getY()));
				ball=newball;
			}
				newballs2.add(ball);

		}

		newballs2.toArray(balls);

		
	}
	
	/**
	 * @creates | result
	 * @inspects | this //add?
	 */

	public void movePaddleRight() {
		if (paddle.getBr().getX() <= BOUNDARY) {
		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),100);
		paddle = newpaddle;
		} 
	}
	
	/**
	 * @creates | result
	 * @inspects | this //add?
	 */

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
		if(Arrays.stream(balls,0,balls.length).allMatch(e -> e.getBr().getY()>=GameMap.getHeight())){
			return true;
		} else {

			return false;
		}

	}


}
