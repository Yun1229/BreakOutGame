package breakout;

import java.awt.Rectangle;

public class PaddleState2 {
	
	public static final int PADDLE_SPEED = 10;
	private final Rectangle location;
	
	public PaddleState2(Rectangle location) {
		this.location = location;
	}
	
	public Rectangle getLocation() {return location;}
	
	public PaddleState2 move(int direction) {
		return new PaddleState2(location.shift(Vector.RIGHT.scaled((PADDLE_SPEED)*direction)));
	}
	
	

}
