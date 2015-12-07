package com.mknsri.drunktoss.Objects;

public class PlayerLauncher {
	private float angle = 0f, power = 0f;
	private float MAX_POWER = 10f;
	private float MAX_ANGLE = 5f;
	private float POWER_ADDITION = 1f;
	private float ANGLE_ADDITION = 1f;
	private boolean powerUp = true;
	private boolean angleUp = true;
	public boolean pressedState = false;
	
	public void calculatePower() {
		if (power <= MAX_POWER && power >= 0) {
			if (powerUp) {
				if (power >= MAX_POWER) {
					powerUp = false;
					power-= POWER_ADDITION;
				} else {
					power += POWER_ADDITION;
				}
			} else if (!powerUp) {
				if (power <= 0) {
					powerUp = true;
					power += POWER_ADDITION;
				} else {
					power -= POWER_ADDITION;
				}
			}
		}
	}
	
	public void calculateAngle() {
		if (angle <= MAX_ANGLE && angle >= 0) {
			if (angleUp) {
				if (angle >= MAX_ANGLE) {
					angleUp = false;
					angle -= ANGLE_ADDITION;
				} else {
					if (angle > MAX_ANGLE * 0.75) {
						angle += angle * 0.5f;
					} else {
						angle += ANGLE_ADDITION;
					}
				}
			} else if (!angleUp) {
				if (angle <= 0) {
					angleUp = true;
					angle += ANGLE_ADDITION;
				} else {
					angle -= ANGLE_ADDITION;
				}
			}
		}
		else if (angle > MAX_ANGLE) {
			angle = MAX_ANGLE;
		}
		else {
			angle = 0f;
		}
	}
	
	
	public float getPower() {
		return power;
	}
	
	public float getAngle() {
		return angle;
	}
	
	
}