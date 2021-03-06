package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.CANJaguar;

public class Lifter extends CANJaguar
{
	int toteHeight = 0;
	ControlMode m_controlMode;
	double homePos;
	final int UP = 0, DOWN = 1;
	
	public Lifter(int deviceNumber)
	{
		super(deviceNumber);
		setPercentMode(CANJaguar.kQuadEncoder, 360);
		enableControl();
		configNeutralMode(CANJaguar.NeutralMode.Brake);
		configLimitMode(LimitMode.SoftPositionLimits);
	}
	
	public void liftTote(double height) // 1 TOTE = 2'
	{
		configForwardLimit(getPosition() + (height * 25));
    	move(UP);
	}
	
	public void liftCan(double height) // 1 CAN = 2'5"
	{
		configForwardLimit(getPosition() + (height * 25) + 11);
		move(UP);
	}
	
	public void goHome() // Call in periodic
	{
		if(getReverseLimitOK()) {
			move(DOWN);
		}
		else {
			stop();
			setHome();
		}
	}
	
	public void stop() {
		set(0);
	}
	
	public void move(int direction) {
		switch(direction) {
		case UP:
			set(1);
		case DOWN:
			set(-1);
		}
	}
	
	public void setHome() {
		homePos = getPosition();
	}
	
	public double getHome() {
		return homePos;
	}
}