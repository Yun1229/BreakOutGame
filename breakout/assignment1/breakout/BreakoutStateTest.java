package breakout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;



class BreakoutStateTest {

	@Test
	void test() {
		// ball
		Point myBallTl = new Point(3,3);
		Point myBallBr = new Point(5,5);
		Vector myBallVelocity = new Vector(5,7);
		BallState myBall = new BallState(myBallTl, myBallBr, myBallVelocity);


		BallState[] myBalls = new BallState[] {myBall};

		// block
		Point myBlockTl1 = new Point(0,0);
		Point myBlockBr1 = new Point(2,1);
		BlockState myBlock1 = new BlockState(myBlockTl1, myBlockBr1);
		Point myBlockTl2 = new Point(2,0);
		Point myBlockBr2 = new Point(4,1);
		BlockState myBlock2 = new BlockState(myBlockTl2, myBlockBr2);

		BlockState[] myBlocks = new BlockState[] {myBlock1,myBlock2};

		// paddle
		Point myPaddleTl = new Point(6,6);
		Point myPaddleBr = new Point(8,7);
		int PADDLE_VELOCITY = 10 ;
		PaddleState myPaddle = new PaddleState(myPaddleTl, myPaddleBr, PADDLE_VELOCITY);

		// bottomRight
		Point myBottomRight = new Point(GameMap.getWidth(), GameMap.getHeight());

		// breakoutState
		BreakoutState myBreakoutState = new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle);

		assertArrayEquals(myBalls, myBreakoutState.getBalls());
		assertArrayEquals(myBlocks, myBreakoutState.getBlocks());
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myBottomRight,myBreakoutState.getBottomRight());
		assertEquals(1, myBreakoutState.getBalls().length);

//		tick method
		int paddleDir0 = 0;
		myBreakoutState.tick(paddleDir0);

		//ball is moved by one step of the velocity.
		assertTrue(myBreakoutState.getBalls()[0].getTl().equals(myBalls[0].getTl().plus(myBallVelocity)));
		assertTrue(myBreakoutState.getBalls()[0].getBr().equals(myBalls[0].getBr().plus(myBallVelocity)));
		assertEquals(myBreakoutState.getBalls()[0].getVelocity(),new Vector(5,7));


		//paddle is not moved.
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myPaddle.getBr(),myBreakoutState.getPaddle().getBr());
		assertEquals(myPaddle.getVelocity()*paddleDir0,myBreakoutState.getPaddle().getVelocity());	


		//blocks are not moved.
		assertEquals(myBlock1.getTl(),myBreakoutState.getBlocks()[0].getTl());
		assertEquals(myBlock1.getBr(),myBreakoutState.getBlocks()[0].getBr());
		assertEquals(myBlock2.getTl(),myBreakoutState.getBlocks()[1].getTl());
		assertEquals(myBlock2.getBr(),myBreakoutState.getBlocks()[1].getBr());


//	    [1_1] After ball touch GameMapLeft  
		// new Ball1_1
		Point myBallTl1_1 = new Point(3,3);
		Point myBallBr1_1 = new Point(5,5);
		Vector myBallVelocity1_1 = new Vector(-6,0);
		BallState myBall1_1 = new BallState(myBallTl1_1, myBallBr1_1, myBallVelocity1_1); 	
		BallState myNewBall1_1 = new BallState(new Point(3,3), new Point(5,5), myBallVelocity1_1.mirrorOver(Vector.RIGHT));
		BallState[] myBalls1_1 = new BallState[] {myBall1_1};
		BallState[] myNewBalls1_1 = new BallState[] {myNewBall1_1};

		// new Block1_1
		Point myBlockTl1_1 = new Point(0,0);
		Point myBlockBr1_1 = new Point(2,1);
		BlockState myBlock1_1 = new BlockState(myBlockTl1_1, myBlockBr1_1);
		Point myBlockTl1_2 = new Point(2,0);
		Point myBlockBr1_2 = new Point(4,1);
		BlockState myBlock1_2 = new BlockState(myBlockTl1_2, myBlockBr1_2);

		BlockState[] myBlocks1 = new BlockState[] {myBlock1_1,myBlock1_2};

		// new Paddle1_1
		Point myPaddleTl_1 = new Point(6,6);
		Point myPaddleBrl_1 = new Point(8,7);
		int myPaddleVelocityl_1 = 10 ;
		PaddleState myPaddlel_1 = new PaddleState(myPaddleTl_1, myPaddleBrl_1, myPaddleVelocityl_1);

 
		// new BreakoutState1_1
		BreakoutState myNewBreakoutState1_1beforetick = new BreakoutState(myBalls1_1,myBlocks,myBottomRight, myPaddle);
		myNewBreakoutState1_1beforetick.tick(paddleDir0);
		BreakoutState myNewBreakoutState1_1aftertick = new BreakoutState(myNewBalls1_1,myBlocks1,myBottomRight, myPaddlel_1);

		assertEquals(1, myNewBreakoutState1_1beforetick.getBalls().length);
		assertTrue(myNewBreakoutState1_1aftertick.getBalls()[0].getTl().equals(myNewBreakoutState1_1beforetick.getBalls()[0].getTl()));
		assertTrue(myNewBreakoutState1_1aftertick.getBalls()[0].getBr().equals(myNewBreakoutState1_1beforetick.getBalls()[0].getBr()));
		assertTrue(myNewBreakoutState1_1aftertick.getBalls()[0].getVelocity().equals(myNewBreakoutState1_1beforetick.getBalls()[0].getVelocity()));
		assertEquals(myNewBreakoutState1_1aftertick.getBalls()[0].getTl(),new Point(3,3));
		assertEquals(myNewBreakoutState1_1aftertick.getBalls()[0].getBr(),new Point(5,5));
		assertEquals(myNewBreakoutState1_1beforetick.getBalls()[0].getVelocity(),new Vector(6,0));

		assertEquals(2, myNewBreakoutState1_1beforetick.getBlocks().length);
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[0].getTl().equals(myNewBreakoutState1_1aftertick.getBlocks()[0].getTl()));
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[0].getBr().equals(myNewBreakoutState1_1aftertick.getBlocks()[0].getBr()));	
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[1].getTl().equals(myNewBreakoutState1_1aftertick.getBlocks()[1].getTl()));
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[1].getBr().equals(myNewBreakoutState1_1aftertick.getBlocks()[1].getBr()));	
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[0].getTl(),new Point(0,0));
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[0].getBr(),new Point(2,1));
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[1].getTl(),new Point(2,0));
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[1].getBr(),new Point(4,1));

		assertTrue(myNewBreakoutState1_1beforetick.getPaddle().getTl().equals(myNewBreakoutState1_1aftertick.getPaddle().getTl()));
		assertTrue(myNewBreakoutState1_1beforetick.getPaddle().getBr().equals(myNewBreakoutState1_1aftertick.getPaddle().getBr()));
		assertEquals(myNewBreakoutState1_1beforetick.getPaddle().getTl(),new Point(6,6));
		assertEquals(myNewBreakoutState1_1beforetick.getPaddle().getBr(),new Point(8,7));



//		[1_2] After ball touch GameMapRight 
		// new Ball1_2
		Point myBallTl1_2 = new Point(49997,29997);
		Point myBallBr1_2 = new Point(49999,29999);
		Vector myBallVelocity1_2 = new Vector(6,0);
		BallState myBall1_2 = new BallState(myBallTl1_2, myBallBr1_2, myBallVelocity1_2);	
		BallState myNewBall1_2 = new BallState(new Point(49993,29997), new Point(49995,29999),myBall1_2.getVelocity().mirrorOver(Vector.RIGHT));
		BallState[] myBalls1_2 = new BallState[] {myBall1_2};
		BallState[] myNewBalls1_2 = new BallState[] {myNewBall1_2};


		// new BreakoutState1_2
		BreakoutState myNewBreakoutState1_2beforetick = new BreakoutState(myBalls1_2,myBlocks,myBottomRight,myPaddle);
		myNewBreakoutState1_2beforetick.tick(paddleDir0); //
		BreakoutState myNewBreakoutState1_2aftertick = new BreakoutState(myNewBalls1_2,myBlocks1,myBottomRight,myPaddlel_1);

		assertTrue(myNewBreakoutState1_2beforetick.getBalls()[0].getTl().equals(myNewBreakoutState1_2aftertick.getBalls()[0].getTl()));
		assertTrue(myNewBreakoutState1_2beforetick.getBalls()[0].getBr().equals(myNewBreakoutState1_2aftertick.getBalls()[0].getBr()));
		assertTrue(myNewBreakoutState1_2beforetick.getBalls()[0].getVelocity().equals(myNewBreakoutState1_2aftertick.getBalls()[0].getVelocity()));

		assertEquals(myNewBreakoutState1_2beforetick.getBalls()[0].getTl(),new Point(49993,29997));
		assertEquals(myNewBreakoutState1_2beforetick.getBalls()[0].getBr(),new Point(49995,29999));
		assertEquals(myNewBreakoutState1_2beforetick.getBalls()[0].getVelocity(),new Vector(-6,0));

		//		
//		[1_3] After ball touch GameMapTop 
		// new Ball1_3
		Point myBallTl1_3 = new Point(49997,1);
		Point myBallBr1_3 = new Point(49999,3);
		Vector myBallVelocity1_3 = new Vector(0,-6);
		BallState myBall1_3 = new BallState(myBallTl1_3, myBallBr1_3, myBallVelocity1_3);	
		BallState myNewBall1_3 = new BallState(new Point(49997,5), new Point(49999,7),myBall1_3.getVelocity().mirrorOver(Vector.DOWN));
		BallState[] myBalls1_3 = new BallState[] {myBall1_3};
		BallState[] myNewBalls1_3 = new BallState[] {myNewBall1_3};

		// new BreakoutState1_3
		BreakoutState myNewBreakoutState1_3beforetick = new BreakoutState(myBalls1_3,myBlocks,myBottomRight, myPaddle);
		myNewBreakoutState1_3beforetick.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_3aftertick = new BreakoutState(myNewBalls1_3,myBlocks1,myBottomRight, myPaddlel_1);

		assertTrue(myNewBreakoutState1_3aftertick.getBalls()[0].getTl().equals(myNewBreakoutState1_3beforetick.getBalls()[0].getTl()));
		assertTrue(myNewBreakoutState1_3aftertick.getBalls()[0].getBr().equals(myNewBreakoutState1_3beforetick.getBalls()[0].getBr()));
		assertTrue(myNewBreakoutState1_3aftertick.getBalls()[0].getVelocity().equals(myNewBreakoutState1_3beforetick.getBalls()[0].getVelocity()));

		assertEquals(myNewBreakoutState1_3beforetick.getBalls()[0].getTl(),new Point(49997,5));
		assertEquals(myNewBreakoutState1_3beforetick.getBalls()[0].getBr(),new Point(49999,7));
		assertEquals(myNewBreakoutState1_3beforetick.getBalls()[0].getVelocity(),new Vector(0,6));
		
		assertEquals(myNewBreakoutState1_3aftertick.isDead(),false);
		assertEquals(myNewBreakoutState1_3aftertick.isWon(),false);


//		[1_4] After ball touch GameMapBottom 
		// new Ball1_4
		Point myBallTl1_4 = new Point(GameMap.getWidth()-3,GameMap.getHeight()-3);
		Point myBallBr1_4 = new Point(GameMap.getWidth()-1,GameMap.getHeight()-1);
		Vector myBallVelocity1_4 = new Vector(0,6);
		BallState myBall1_4 = new BallState(myBallTl1_4, myBallBr1_4, myBallVelocity1_4);
		//a "fake" ball
		BallState myNewBall1_4 = new BallState(myBall1_4.getTl().plus(myBall1_4.getVelocity()), myBall1_4.getBr().plus(myBall1_4.getVelocity()),myBall1_4.getVelocity().mirrorOver(Vector.DOWN));
		BallState[] myBalls1_4 = new BallState[] {myBall1_4};
		BallState[] myNewBalls1_4 = new BallState[] {}; 


		// new BreakoutState1_4
		BreakoutState myNewBreakoutState1_4beforetick = new BreakoutState(myBalls1_4,myBlocks,myBottomRight, myPaddle);
		myNewBreakoutState1_4beforetick.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_4aftertick = new BreakoutState(myNewBalls1_4,myBlocks1,myBottomRight, myPaddlel_1);
		
		assertArrayEquals(myNewBreakoutState1_4beforetick.getBalls(),myNewBreakoutState1_4aftertick.getBalls()); 
		assertArrayEquals(myNewBreakoutState1_4beforetick.getBalls(),new BallState[] {});
		
		assertEquals(myNewBreakoutState1_4beforetick.getBalls().length,0);
		assertEquals(myNewBreakoutState1_4beforetick.isDead(),true);
		assertEquals(myNewBreakoutState1_4beforetick.isWon(),false);





//		[2_1_a] When the ball vertically falls down and touches the top of the paddle (the paddle does't move)
		// paddle2_1_a
		Point myPaddleTl2_1_a = new Point(60,60);
		Point myPaddleBr2_1_a = new Point(80,70);
		PaddleState myPaddle2_1_a = new PaddleState(myPaddleTl2_1_a, myPaddleBr2_1_a, paddleDir0*PADDLE_VELOCITY);
		
		// new Ball2_1
		Point myBallTl2_1_a = new Point(60,30);
		Point myBallBr2_1_a = new Point(80,50);
		Vector myBallVelocity2_1_a = new Vector(0,20);
		BallState myBall2_1_a = new BallState(myBallTl2_1_a, myBallBr2_1_a, myBallVelocity2_1_a);
//		BallState myNewBall2_1_a = new BallState(myBallTl2_1_a.plus(new Vector(myBall2_1_a.getVelocity().getX()+myPaddle2_1_a.getVelocity()/5,myBall2_1_a.getVelocity().getY())), myBallBr2_1_a.plus(new Vector(myBall2_1_a.getVelocity().getX()+myPaddle2_1_a.getVelocity()/5,myBall2_1_a.getVelocity().getY())), myBallVelocity2_1_a.plus(new Vector((myPaddle2_1_a.getVelocity()/5),-2*myBallVelocity2_1_a.getY())));
		BallState myNewBall2_1_a = new BallState(new Point(60,50),new Point(80,70),new Vector(0,-20));
		//tl(6,9) br(8,11) velocity(0,-6)
		//System.out.println(myNewBall2_1_a.getVelocity()); //(0,-6)
		BallState myNewNewBall2_1_a = new BallState(new Point(60,30),new Point(80,50),new Vector(0,-20));

		BallState[] myBalls2_1_a = new BallState[] {myBall2_1_a};
		BallState[] myNewBalls2_1_a = new BallState[] {myNewBall2_1_a};	
		BallState[] myNewNewBalls2_1_a = new BallState[] {myNewNewBall2_1_a};	


		// new BreakoutState2_1_a
		BreakoutState myBreakoutState2_1_a = new BreakoutState(myBalls2_1_a,myBlocks,myBottomRight, myPaddle2_1_a);
		myBreakoutState2_1_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState2_1_a = new BreakoutState(myNewBalls2_1_a,myBlocks,myBottomRight,myPaddle2_1_a);
		myBreakoutState2_1_a.tick(paddleDir0);
		BreakoutState myNewNewBreakoutState2_1_a = new BreakoutState(myNewNewBalls2_1_a,myBlocks,myBottomRight,myPaddle2_1_a);
		
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getTl().equals(myNewNewBreakoutState2_1_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getBr().equals(myNewNewBreakoutState2_1_a.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_a.getBalls()[0].getVelocity())); //not working [1]
//      System.out.println(myBreakoutState2_1_a.getBalls()[0].getVelocity()); //(0,6)not change?? // not yet solved [1]
        
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getTl(),new Point(60,30));
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getBr(),new Point(80,50));
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getVelocity(),new Vector(0,-20)); // not working [1]
	  
		assertTrue(myBreakoutState2_1_a.getPaddle().getTl().equals(myNewBreakoutState2_1_a.getPaddle().getTl()));
		assertTrue(myBreakoutState2_1_a.getPaddle().getBr().equals(myNewBreakoutState2_1_a.getPaddle().getBr()));
		assertEquals(myBreakoutState2_1_a.getPaddle().getVelocity(),myNewBreakoutState2_1_a.getPaddle().getVelocity()); //

		
//		[2_1_b] After ball touch top of paddle (ball and paddle are in the same direction: both right)
		// paddle2_1_b
		Point myPaddleTl2_1_b = new Point(6,6);
		Point myPaddleBr2_1_b = new Point(8,7);
		int paddleDir1 = 1 ;
		
		PaddleState myPaddle2_1_b = new PaddleState(myPaddleTl2_1_b, myPaddleBr2_1_b, paddleDir1*PADDLE_VELOCITY);
		assertEquals(myPaddle2_1_b.getVelocity(),10);
		//PaddleState myNewPaddle2_1_b = new PaddleState(new Point(myPaddleTl2_1_b.getX()+myPaddleVelocity2_1_b, myPaddleTl2_1_b.getY()),new Point (myPaddleBr2_1_b.getX()+myPaddleVelocity2_1_b,myPaddleBr2_1_b.getY()), myPaddleVelocity2_1_b*PADDLE_VELOCITY);
										//tl(7,6) br(9,7) velocity = 1
		PaddleState myNewPaddle2_1_b = new PaddleState(new Point(16,6),new Point (18,7), paddleDir1*PADDLE_VELOCITY);
		assertEquals(myNewPaddle2_1_b.getVelocity(),10);

		
		// new Ball2_1_b
		Point myBallTl2_1_b = new Point(6,3);
		Point myBallBr2_1_b = new Point(8,5);
		Vector myBallVelocity2_1_b = new Vector(10,2);
		BallState myBall2_1_b = new BallState(myBallTl2_1_b, myBallBr2_1_b, myBallVelocity2_1_b);
		BallState myNewBall2_1_b = new BallState(new Point(16,5),new Point (18,7), new Vector(-8,-2));
		BallState myNewNewBall2_1_b = new BallState(new Point(8,3),new Point (10,5), new Vector(-8,-2));
		//BallState myNewBall2_1_b = new BallState(myBallTl2_1_b.plus(new Vector(myBall2_1_b.getVelocity().getX()+myPaddle2_1_b.getVelocity()/5,myBall2_1_b.getVelocity().getY())), myBallBr2_1_b.plus(new Vector(myBall2_1_b.getVelocity().getX()+myPaddle2_1_b.getVelocity()/5,myBall2_1_b.getVelocity().getY())), myBallVelocity2_1_b.plus(new Vector((myPaddle2_1_b.getVelocity()/5),-2*myBallVelocity2_1_b.getY())));
			 		                                //tl(7,5) br(9,7) velocity(1,-2)
		
//		System.out.println(myNewBall2_1_b.getVelocity()); //(1,-2)
		BallState[] myBalls2_1_b = new BallState[] {myBall2_1_b};
		BallState[] myNewBalls2_1_b = new BallState[] {myNewBall2_1_b};	
		BallState[] myNewNewBalls2_1_b = new BallState[] {myNewNewBall2_1_b};	
		
		// new BreakoutState2_1_b
		BreakoutState myBreakoutState2_1_b = new BreakoutState(myBalls2_1_b,myBlocks,myBottomRight, myPaddle2_1_b);
		myBreakoutState2_1_b.movePaddleRight();
		myBreakoutState2_1_b.tick(paddleDir1);
		BreakoutState myNewBreakoutState2_1_b = new BreakoutState(myNewBalls2_1_b,myBlocks,myBottomRight,myNewPaddle2_1_b);
		
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getTl().equals(myNewBreakoutState2_1_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getBr().equals(myNewBreakoutState2_1_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_b.getBalls()[0].getVelocity())); //not working [2]
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getTl(),new Point(16,5));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getBr(),new Point(18,7));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getVelocity(),new Vector(-8,-2)); 
		
		assertTrue(myBreakoutState2_1_b.getPaddle().getTl().equals(myNewBreakoutState2_1_b.getPaddle().getTl()));
		assertTrue(myBreakoutState2_1_b.getPaddle().getBr().equals(myNewBreakoutState2_1_b.getPaddle().getBr()));
		assertEquals(myBreakoutState2_1_b.getPaddle().getVelocity(),myNewBreakoutState2_1_b.getPaddle().getVelocity()); //
		
		assertEquals(myBreakoutState2_1_b.getPaddle().getTl(),new Point(16,6));
		assertEquals(myBreakoutState2_1_b.getPaddle().getBr(),new Point(18,7));
		assertEquals(myBreakoutState2_1_b.getPaddle().getVelocity(),10);
		
		myBreakoutState2_1_b.tick(paddleDir1);
		BreakoutState myNewNewBreakoutState2_1_b = new BreakoutState(myNewNewBalls2_1_b,myBlocks,myBottomRight,myNewPaddle2_1_b);
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getTl().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getBr().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_b.getBalls()[0].getVelocity())); //not working [2]
//      System.out.println(myBreakoutState2_1_b.getBalls()[0].getVelocity()); //(21,-2)??? // not yet solved [2]
		
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getTl().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getBr().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getVelocity().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getVelocity())); //not working [2]
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getTl(),new Point(8,3));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getBr(),new Point(10,5));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getVelocity(),new Vector(-8,-2)); // not working [2]
	
		
//		System.out.println(myBreakoutState2_1_b.getPaddle().getTl()); //(6,6)
//		System.out.println(myBreakoutState2_1_b.getPaddle().getBr()); //(8,7)
//		System.out.println(myBreakoutState2_1_b.getPaddle().getVelocity()); //100

		
		//**** because paddle velocity only equals 100, cannot be 1, so 4/6 continue
		
		assertTrue(myBreakoutState2_1_b.getPaddle().getTl().equals(myNewBreakoutState2_1_b.getPaddle().getTl())); //not working
		assertTrue(myBreakoutState2_1_b.getPaddle().getBr().equals(myNewBreakoutState2_1_b.getPaddle().getBr())); // not working
		assertEquals(myBreakoutState2_1_b.getPaddle().getVelocity(),myNewBreakoutState2_1_b.getPaddle().getVelocity()); //int
		


		//	    [2_2_a] After ball touch left of paddle

		// new Ball2_2_a
		Point myBallTl2_2_a = new Point(3,5);
		Point myBallBr2_2_a = new Point(5,7);
		Vector myBallVelocity2_2_a = new Vector(6,0);
		BallState myBall2_2_a = new BallState(myBallTl2_2_a, myBallBr2_2_a, myBallVelocity2_2_a);

		BallState myNewBall2_2_a = new BallState(myBallTl2_2_a.plus(myBallVelocity2_2_a), myBallBr2_2_a.plus(myBallVelocity2_2_a), myBallVelocity2_2_a.mirrorOver(Vector.RIGHT));
		//tl(9,5) br(11,7) velocity(-6,0)
		BallState[] myBalls2_2_a = new BallState[] {myBall2_2_a};		
		BallState[] myNewBalls2_2_a = new BallState[] {myNewBall2_2_a};		

		// paddle2_2
		Point myPaddleTl2_2_a = new Point(6,6);
		Point myPaddleBr2_2_a = new Point(8,7);
		int myPaddleVelocity2_2_a = 0 ;
		PaddleState myPaddle2_2_a = new PaddleState(myPaddleTl2_2_a, myPaddleBr2_2_a, myPaddleVelocity2_2_a);

		// new BreakoutState2_2
		BreakoutState myBreakoutState2_2_a = new BreakoutState(myBalls2_2_a,myBlocks,myBottomRight,myPaddle2_2_a);
		BreakoutState myNewBreakoutState2_2_a = new BreakoutState(myNewBalls2_2_a,myBlocks,myBottomRight,myPaddle2_2_a);

		myBreakoutState2_2_a.tick(myPaddleVelocity2_2_a);

		assertArrayEquals(myNewBalls2_2_a,myNewBreakoutState2_2_a.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_2_a.getBlocks());
		assertEquals(myPaddle2_2_a.getTl(),myNewBreakoutState2_2_a.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_2_a.getBottomRight());


		//		[2_2_b] After ball touch left of paddle (squeezed)
		// new Ball2_2_b
		Point myBallTl2_2_b = new Point(3,5);
		Point myBallBr2_2_b = new Point(5,7);
		Vector myBallVelocity2_2_b = new Vector(6,6);
		BallState myBall2_2_b = new BallState(myBallTl2_2_b, myBallBr2_2_b, myBallVelocity2_2_b);

		BallState myNewBall2_2_b = new BallState(new Point(0,myBallTl2_2_b.getY()), new Point(myBallBr2_2_b.getX()-myBallTl2_2_b.getX(),myBallBr2_2_b.getY()), new Vector(0,Math.abs(myBallVelocity2_2_b.getY())));
		//tl(0,5) br(2,7) velocity(0,6)

		BallState[] myNewBalls2_2_b = new BallState[] {myNewBall2_2_b};		

		// new paddle2_2_b
		Point myPaddleTl2_2_b = new Point(100,25000);
		Point myPaddleBr2_2_b = new Point(200,25050);
		int myPaddleVelocity2_2_b = -100 ;
		PaddleState myPaddle2_2_b = new PaddleState(myPaddleTl2_2_b, myPaddleBr2_2_b, myPaddleVelocity2_2_b);
		PaddleState myNewPaddle2_2_b = new PaddleState(myPaddleTl2_2_b.plus(new Vector(myPaddleVelocity2_2_b,0)), myPaddleBr2_2_b.plus(new Vector(myPaddleVelocity2_2_b,0)), myPaddleVelocity2_2_b);
		//tl(-94,6) br(-92,7) velocity(-100,0)
		// new BreakoutState2_2_b
		BreakoutState myNewBreakoutState2_2_b = new BreakoutState(myNewBalls2_2_b,myBlocks,myBottomRight,myPaddle2_2_b);
		
		
		
		
		


		assertArrayEquals(myNewBalls2_2_b,myNewBreakoutState2_2_b.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_2_b.getBlocks());
		assertEquals(myPaddle2_2_b.getTl(),myNewBreakoutState2_2_b.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_2_b.getBottomRight());


		//	    [3] After ball touch block
		// new Ball3
		Point myBallTl3 = new Point(0,2);
		Point myBallBr3 = new Point(2,4);
		Vector myBallVelocity3 = new Vector(0,-6);
		BallState myBall3 = new BallState(myBallTl3, myBallBr3, myBallVelocity3);

		BallState myNewBall3 = new BallState(myBallTl3.plus(myBallVelocity3), myBallBr3.plus(myBallVelocity3), myBallVelocity3.mirrorOver(Vector.DOWN));
		//tl(0,-2) br(2,0) velocity(0,6)
		BallState[] myNewBalls3 = new BallState[] {myNewBall3};		

		// new Blocks3
		BlockState[] myNewBlocks3 = new BlockState[] {myBlock2};
 
		// new BreakoutState3
		BreakoutState myNewBreakoutState3 = new BreakoutState(myNewBalls3,myNewBlocks3,myBottomRight, myPaddle);

		assertEquals(myNewBalls3,myNewBreakoutState3.getBalls());
		assertEquals(myNewBlocks3,myNewBreakoutState3.getBlocks());
		assertEquals(Arrays.asList(myNewBalls3), Arrays.asList(myNewBreakoutState3.getBalls())); 


		//	    [4] movePaddleRight
		myBreakoutState.movePaddleRight();

		// new paddle4
		PaddleState myNewPaddle4 = new PaddleState(myPaddleTl.plus(new Vector(PADDLE_VELOCITY,0)), myPaddleBr.plus(new Vector(PADDLE_VELOCITY,0)), PADDLE_VELOCITY);
		// new BreakoutState4
		BreakoutState myNewBreakoutState4 = new BreakoutState(myNewBalls3,myNewBlocks3,myBottomRight, myNewPaddle4);

		assertEquals(myNewPaddle4.getTl(),myNewBreakoutState4.getPaddle().getTl());


		//		[5] movePaddleLeft
		myBreakoutState.movePaddleLeft();

		// new paddle5

		PaddleState myNewPaddle5 = new PaddleState(new Point(0,25000), new Point(200,25050), PADDLE_VELOCITY);

		// new BreakoutState5
		BreakoutState myNewBreakoutState5 = new BreakoutState(myNewBalls3,myNewBlocks3,myBottomRight, myNewPaddle5);

		assertEquals(myNewPaddle5.getTl(),myNewBreakoutState5.getPaddle().getTl());


		//		[6] isWon

		BlockState[] emptyBlocks = new BlockState[] {};

		// new BreakoutState6_1
		BreakoutState myBreakoutState6_1 = new BreakoutState(myBalls,emptyBlocks,myBottomRight, myPaddle);
		assertEquals(1,myBreakoutState6_1.getBalls().length);
		assertEquals(0,myBreakoutState6_1.getBlocks().length);

		assertTrue(myBreakoutState6_1.isWon());

		// new BreakoutState6_2
		BreakoutState myBreakoutState6_2 = new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle);

		assertEquals(1,myBreakoutState6_2.getBalls().length);
		assertEquals(2,myBreakoutState6_2.getBlocks().length);
		assertFalse(myBreakoutState6_2.isWon()); 



		//		assertFalse(myBreakoutState.isWon());
		//		assertSame(myBreakoutState.isWon(),false);
		//		assertEquals(false,myBreakoutState.isWon());
		//		System.out.println(myBreakoutState.isWon());

		//		[7] isDead

		// new ball7
		Point myBallTl7 = new Point(GameMap.getWidth()/2-2,GameMap.getHeight()-1-2);
		Point myBallBr7 = new Point(GameMap.getWidth()/2,GameMap.getHeight()-1);
		Vector myBallVelocity7 = new Vector(0,4);
		BallState myBall7 = new BallState(myBallTl7, myBallBr7, myBallVelocity7);
		//tl(24998,29997) br(25000,29999) velocity(0,4)
		BallState myNewBall7 = new BallState(myBallTl7.plus(myBallVelocity7), myBallBr7.plus(myBallVelocity7), myBall7.getVelocity().mirrorOver(new Vector(0,-1)));
		//tl(24998,30001) br(25000,30003) velocity(0,-4)
		BallState[] myNewBalls7 = new BallState[] {myNewBall7} ;
		BallState[] emptyBalls = new BallState[] {};

		// new BreakoutState7_1
		BreakoutState myBreakoutState7_1 = new BreakoutState(emptyBalls,myBlocks,myBottomRight, myPaddle);

		assertEquals(0,myBreakoutState7_1.getBalls().length);
		assertTrue(myBreakoutState7_1.isDead());

		// new BreakoutState7_2
		BreakoutState myBreakoutState7_2 = new BreakoutState(myNewBalls7,myBlocks,myBottomRight, myPaddle);

		assertEquals(1,myBreakoutState7_2.getBalls().length);
		assertFalse(myBreakoutState7_2.isDead()); 
	}				

}
