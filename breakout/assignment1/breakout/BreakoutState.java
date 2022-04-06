package breakout;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: implement, document
/**
 * Abstract state invariants
 * @invar | getBalls() != null
 * @invar | getBlocks() != null
 * @invar | getPaddle() != null
 * @invar | getBottomRight() != null
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
	 * @representationObject
	 */

	private BallState[] balls;
	private BlockState[] blocks;
	private PaddleState paddle;
	private Point bottomRight;
	private static final int BOUNDARY = GameMap.getWidth();
	private static final int PADDLE_VELOCITY = 100;


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
			BallState newball = new BallState(ball.getTl(),ball.getBr(),ball.getVelocity());		
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
		return blocks;
	}

	/**
	 * @post | result != null
	 * @creates | result
	 * @inspect | this
	 */

	public PaddleState getPaddle() {
		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),paddle.getVelocity());
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
//	 * @post | 0 <= getBalls().length
	 * @throws IllegalArgumentException
	 *   | paddleDir !=0 || paddleDir != 1 || paddleDir != -1
	 * @creates | result
	 * @inspect | this //add?
	 * 
	 * 
//	 * @post | getBalls()[0].getTl().getX() < getBalls()[0].getBr().getX()
	 * 
	 */

	public void tick(int paddleDir) {

		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),PADDLE_VELOCITY*paddleDir);
			if(paddle.getTl().getX()<=0&&paddleDir==-1||paddle.getBr().getX()>=BOUNDARY&&paddleDir==1) {
			newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),0);
		}
		paddle = newpaddle;
		ArrayList<BallState> newballs = new ArrayList<BallState>();

		for (BallState ball: balls) {
			//Move all balls one step forward according to their current velocity.
			BallState newball = new BallState(ball.getTl().plus(ball.getVelocity()),ball.getBr().plus(ball.getVelocity()),ball.getVelocity());
			ball=newball;
		
	
		
			List <Rectangle> rectangleList = new ArrayList<Rectangle>();
			//Add blocks first
			for (BlockState block: blocks) {
				rectangleList.add(block);
			}
			//then add paddle
			rectangleList.add(paddle);
			
			boolean removeball = false;
			boolean ballreflectX = false;
			boolean ballreflectY = false;

			int ballTY=ball.getTl().getY();
			int ballBY=ball.getBr().getY();
			int ballLX=ball.getTl().getX();
			int ballRX=ball.getBr().getX();

			Vector newvelocity = ball.getVelocity();
			Point newballTl = ball.getTl();
			Point newballBr = ball.getBr();

			ArrayList<Point> blockstl = new ArrayList<Point>();
			ArrayList<BlockState> newblocks = new ArrayList<BlockState>();

			// Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
			// ball touch GameMapRight 
			int x = 0;
			if (ball.getBr().getX() >= GameMap.getWidth()) {
				x = newballBr.getX()-BOUNDARY;
				newballTl = newballTl.plus(new Vector(-2*x,0));
				newballBr = newballBr.plus(new Vector(-2*x,0));
				newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
			}
			//ball touch GameMapLeft
			if (ball.getTl().getX() <= 0) {
				x = newballTl.getX();
				newballTl = newballTl.plus(new Vector(-2*x,0));
				newballBr = newballBr.plus(new Vector(-2*x,0));
				newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
			}

			// ball touch GameMapTop
			if (ball.getTl().getY() <= 0) {			
				
				int y = newballTl.getY();
				newballTl = newballTl.plus(new Vector(0,-2*y));
				newballBr = newballBr.plus(new Vector(0,-2*y));
				newvelocity = newvelocity.mirrorOver(Vector.DOWN);
				
				
			}

			//Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
			if (ball.getBr().getY()>=GameMap.getHeight()) {
				removeball =true;
			}


			//Check whether any ball hit any block, in which case the block must be removed from the game and the ball must bounce back.
			//Check whether any ball hit the paddle, in which case it must bounce back.
			//Since the elements in `rectangleList` are in the order, the checking is also in the order.
			for (Rectangle rectangle : rectangleList) {				
				int rectangleTY=rectangle.getTl().getY();
				int rectangleBY=rectangle.getBr().getY();
				int rectangleLX=rectangle.getTl().getX();
				int rectangleRX=rectangle.getBr().getX();

				//If the ball hits the rectangles
				if (ballLX <= rectangleRX && ballRX >= rectangleLX &&rectangleTY <= ballBY && rectangleBY >= ballTY) {
					
					Vector balltorectanglevector= new Vector((rectangleRX+rectangleLX)/2-ball.getCenter().getX(),(rectangleBY+rectangleTY)/2-ball.getCenter().getY());
					//If the ball hit two sides of the rectangle
					if (ballLX <= rectangleRX && ballRX >= rectangleRX 
							|| ballRX >= rectangleLX && ballLX <= rectangleLX) {
						ballreflectX=true;
					}
					//If the ball hit the top of the paddle or the the top and bottom of the blocks
					if (rectangleTY <= ballBY && rectangleTY >= ball.getCenter().getY() 
							|| rectangleBY <= ballBY && rectangleBY >= ballTY && rectangle instanceof BlockState) {
						ballreflectY=true;
					}
					//Deal with the reflection on edges
					if(balltorectanglevector.getX()*ball.getVelocity().getX()<0 
							&& ballreflectX && ballreflectY) {
						ballreflectX=false;
					}
					if(balltorectanglevector.getY()*ball.getVelocity().getY()<0 
							&& ballreflectX && ballreflectY&&rectangle instanceof BlockState) {
						ballreflectY=false;
					}
					
					//Renew blocks
					if (rectangle instanceof BlockState) {
						blockstl.add(rectangle.getTl());
					}

					//Renew paddle
					if (rectangle instanceof PaddleState) {
						if(ballreflectY&&!ballreflectX) {
							 
//							int a = newballBr.getY()-paddle.getTl().getY();
//							newballTl = newballTl.plus(new Vector(-2*a,0));
//							newballBr = newballBr.plus(new Vector(-2*a,0));
							//System.out.println(ball.getVelocity().toString());
							newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5,-2*newvelocity.getY()));
							//System.out.println(newvelocity.toString());

							
						}
						if(ballreflectY&&ballreflectX) {
							newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5-2*newvelocity.getX(),-2*newvelocity.getY()));
//							if (ball.getBr().getX()<= BOUNDARY&&ball.getTl().getX()>=BOUNDARY-(ballRX-ballLX) 
//									|| ball.getTl().getX() <= ballRX-ballLX&&ball.getBr().getX() >=0) {
//
////
////								if(ball.getCenter().getX()>=rectangle.getPosition().getX()) {
////									newballTl = new Point(BOUNDARY-(ballRX-ballLX),ballTY);
////									newballBr = new Point(BOUNDARY,ballBY);
////									newvelocity = new Vector(-paddle.getVelocity()*2/5,newvelocity.getY());
////								}
////								else {
////									newballTl = new Point(0,ballTY);
////									newballBr = new Point(ballRX-ballLX,ballBY);
////									newvelocity = new Vector(paddle.getVelocity()*2/5,newvelocity.getY());
////								}
//							}

						}
						if(ballreflectX&&!ballreflectY) {
							//Deal with the vibration when the ball is squeezed by the paddle and the wall
							if (ballRX>= BOUNDARY||ballLX<=0) {

								if(ball.getCenter().getX()>=rectangle.getPosition().getX()) {
									newpaddle = new PaddleState(paddle.getTl().minus(new Vector(paddle.getVelocity(),0)),paddle.getBr().minus(new Vector(paddle.getVelocity(),0)),0);
									newballTl = new Point(BOUNDARY-(ballRX-ballLX),ballTY);
									newballBr = new Point(BOUNDARY,ballBY);
									newvelocity = new Vector(0,ball.getVelocity().getY());
								}  
								else {
									newpaddle = new PaddleState(paddle.getTl().minus(new Vector(paddle.getVelocity(),0)),paddle.getBr().minus(new Vector(paddle.getVelocity(),0)),0);
									newballTl = new Point(0,ballTY);
									newballBr = new Point(ballRX-ballLX,ballBY);
									newvelocity = new Vector(0,ball.getVelocity().getY());
								}
								paddle = newpaddle;
							}
							else{			
								if (newvelocity.getX()*paddle.getVelocity()>0) {
								newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5,0));	
								}
								else {
									newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5-2*newvelocity.getX(),0));
								}
							}					
						}
					}
				}
				else {
					if (rectangle instanceof BlockState) {
						newblocks.add((BlockState) rectangle);
					}
				}
			}


			if(blockstl.size() == 1||blockstl.size() == 3) {
				if(ballreflectY) {
					newvelocity = newvelocity.mirrorOver(Vector.DOWN);
				}
				if(ballreflectX) {	
					newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
				}
			}

			if(blockstl.size() == 2) {
				if(blockstl.get(0).getY() == blockstl.get(1).getY()) {
					newvelocity = newvelocity.mirrorOver(Vector.DOWN);
				}
				if(blockstl.get(0).getX() == blockstl.get(1).getX()) {
					newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
				}
			}


			newball = new BallState(newballTl,newballBr,newvelocity);
			ball=newball;

			if (!removeball) {
				newballs.add(ball);
			}

			BlockState[] newblocksarray = new BlockState[newblocks.size()];
			newblocks.toArray(newblocksarray);
			blocks = newblocksarray;
			

		}


		BallState[] newballsarray = new BallState[newballs.size()];
		newballs.toArray(newballsarray);
		balls = newballsarray;
		


	}



	/**
	 * @creates | result
	 * @inspects | this //add?
	 */
 
	public void movePaddleRight() {
		PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(paddle.getVelocity(),0)),paddle.getBr().plus(new Vector(paddle.getVelocity(),0)),paddle.getVelocity());
		paddle = newpaddle;
//		int ballsqueezed=0;
//		for (BallState ball : balls) {
//			if(paddle.getBr().getX()+(ball.getBr().getX()-ball.getTl().getX()) >= BOUNDARY 
//					&& paddle.getTl().getY()<=ball.getBr().getY() 
//					&& paddle.getBr().getY()>=ball.getTl().getY()
//					&& paddle.getBr().getX()<=ball.getTl().getX() 
//					) {
//				ballsqueezed=1;
//			}
//			if (paddle.getBr().getX() < BOUNDARY&&ballsqueezed==0) {
//				PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(PADDLE_VELOCITY,0)),paddle.getBr().plus(new Vector(PADDLE_VELOCITY,0)),paddle.getVelocity());
//				paddle = newpaddle;
//			} else {
//				PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),0);
//				paddle = newpaddle;
//			}
//
//		}
	}

	/**
	 * @creates | result
	 * @inspects | this //add?
	 */

	public void movePaddleLeft() {
//		int ballsqueezed=0;
//		for (BallState ball : balls) {
//			if(paddle.getTl().getX() <= (ball.getBr().getX()-ball.getTl().getX()) 
//					&& paddle.getTl().getY()<=ball.getBr().getY() 
//					&& paddle.getBr().getY()>=ball.getTl().getY()
//					&& paddle.getTl().getX()>=ball.getBr().getX()) {
//				ballsqueezed=1;
//			}			

//			if (paddle.getTl().getX() > 0&&ballsqueezed==0) {
				PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(paddle.getVelocity(),0)),paddle.getBr().plus(new Vector(paddle.getVelocity(),0)),paddle.getVelocity());
				paddle = newpaddle;
//				} else {
//				PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),0);
//				paddle = newpaddle;
//			}
//		}


	}

	public boolean isWon() {
		if(blocks.length == 0 && balls.length>0){
			return true;
		} else {

			return false;
		}
	}

	public boolean isDead() {
		if(balls.length == 0){
			return true;
		} else {

			return false;
		}

	}





}


