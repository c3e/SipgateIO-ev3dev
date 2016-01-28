package de.foobar.ev3dev.motor;

import de.foobar.ev3dev.FileHandler;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Motor {

  public static String PATH_TO_MOTORS = "/sys/class/tacho-motor";

  private String name;

  private FileHandler commandHandler;
  private FileHandler timeSPHandler;
  private FileHandler dutyCycleSPHandler;
  private FileHandler polarityHandler;

  public Motor(String name)
  {
    this.name = name;
    initMotor();
  }

  private void initMotor()
  {
    this.commandHandler = new FileHandler(String.format("%s/%s/%s", PATH_TO_MOTORS, name, "command"));
    this.timeSPHandler = new FileHandler(String.format("%s/%s/%s", PATH_TO_MOTORS, name, "time_sp"));
    this.dutyCycleSPHandler = new FileHandler(String.format("%s/%s/%s", PATH_TO_MOTORS, name, "duty_cycle_sp"));
    this.polarityHandler = new FileHandler(String.format("%s/%s/%s", PATH_TO_MOTORS, name, "polarity"));
  }

  public boolean reset()
  {
    return commandHandler.writeToFile("reset");
  }

  public boolean setTimeSP(int milliseconds)
  {
    return timeSPHandler.writeToFile(String.valueOf(milliseconds));
  }

  public boolean setSpeed(int percent)
  {
    return dutyCycleSPHandler.writeToFile(String.valueOf(percent));
  }

  public boolean setPolarity(boolean forward)
  {
    if(forward)
    {
      return polarityHandler.writeToFile("normal");
    }
    return polarityHandler.writeToFile("inversed");
  }

  public boolean runTimed(int milliseconds, int speed, boolean forward)
  {

    return reset() && setPolarity(forward) && setSpeed(speed) && this.setTimeSP(milliseconds) && commandHandler.writeToFile("run-timed");
  }

  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
