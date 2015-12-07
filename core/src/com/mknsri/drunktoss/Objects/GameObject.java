package com.mknsri.drunktoss.Objects;

import com.mknsri.drunktoss.Screen;

public abstract class GameObject {
	public float x, y;
	public float xVel = 0, yVel = 0;
	protected boolean moving = false;
	public float collBoxW = 0, collBoxH = 0;
	public boolean collisionDetected = false;

	protected void setCollisionBoxSize(float width, float height, int buffer) {
		collBoxW = width - buffer;
		collBoxH = height - buffer;
	}
	
	public boolean checkCollision(GameObject objA, GameObject objB) {
		float leftA, leftB;
		float rightA, rightB;
		float topA, topB;
		float bottomA, bottomB;
		
		leftA 	= objA.x;
		rightA 	= objA.x + objA.collBoxW;
		topA 	= objA.y;
		bottomA = objA.y + objA.collBoxH;
		
		leftB 	= objB.x;
		rightB 	= objB.x + objB.collBoxW;
		topB 	= objB.y;
		bottomB = objB.y + objB.collBoxH;
		
	    if( bottomA <= topB )
	    {
	        return false;
	    }
	    
	    if( topA >= bottomB )
	    {
	        return false;
	    }
	    
	    if( rightA <= leftB )
	    {
	        return false;
	    }
	    
	    if( leftA >= rightB )
	    {
	        return false;
	    }


	    return true;
		
	}
	
	public abstract void render(Screen g);
	

}