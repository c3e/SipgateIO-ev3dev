package de.foobar.ev3dev.sipgateio.event;

public class NewCall extends AbstractEvent
{
  private String from;
  private String to;
  private String direction;
  private String[] user;
  private String diversion;

  public NewCall() {
  }

  public NewCall(String callId, Enum event, String from, String to, String direction, String[] user, String diversion) {
    super(callId, event);
    this.from = from;
    this.to = to;
    this.direction = direction;
    this.user = user;
    this.diversion = diversion;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String[] getUser() {
    return user;
  }

  public void setUser(String[] user) {
    this.user = user;
  }

  public String getDiversion() {
    return diversion;
  }

  public void setDiversion(String diversion) {
    this.diversion = diversion;
  }
}
