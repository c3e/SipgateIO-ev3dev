package de.foobar.ev3dev;

import de.foobar.ev3dev.motor.Motor;

public class SipgateIOEv3Dev {

  private static Motor motor1 = new Motor("motor0");
  private static Motor motor2 = new Motor("motor1");

  private static int MOVE_TIME_FORWARD = 1000;

  public static boolean moveForward(int ms)
  {
    if(ms == 0)
    {
      ms = MOVE_TIME_FORWARD;
    }

    return motor1.runTimed(ms, 75, true) &&
           motor2.runTimed(ms, 75, true);
  }

  public static boolean moveBack(int ms )
  {
    if(ms == 0)
    {
      ms = MOVE_TIME_FORWARD;
    }

    return motor1.runTimed(ms, 75, false)  &&
           motor2.runTimed(ms, 75, false);
  }

  public static boolean turnRight(int ms)
  {
    if(ms == 0)
    {
      ms = 200;
    }

    return motor1.runTimed(ms, 75, Main.SWITCH_MOTOR ) &&
           motor2.runTimed(ms, 75, !Main.SWITCH_MOTOR);
  }

  public static boolean turnLeft(int ms)
  {
    if(ms == 0)
    {
      ms = 200;
    }

    return motor1.runTimed(ms, 75, !Main.SWITCH_MOTOR) &&
           motor2.runTimed(ms, 75, Main.SWITCH_MOTOR);
  }
}
