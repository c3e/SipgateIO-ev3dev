package de.foobar.ev3dev.sipgateio.event;

public class Answer extends AbstractEvent
{
  private String[] user;

  public Answer() {
  }

  public Answer(String callId, Enum event, String[] user) {
    super(callId, event);
    this.user = user;
  }

  public String[] getUser() {
    return user;
  }

  public void setUser(String[] user) {
    this.user = user;
  }
}
