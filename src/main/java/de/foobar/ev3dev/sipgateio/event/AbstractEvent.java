package de.foobar.ev3dev.sipgateio.event;

public abstract class AbstractEvent
{

  private String callId;
  private Enum event;

  public AbstractEvent()
  {
  }

  public AbstractEvent(String callId, Enum event)
  {
    this.callId = callId;
    this.event = event;
  }

  public String getCallId()
  {
    return callId;
  }

  public void setCallId(String callId)
  {
    this.callId = callId;
  }

  public Enum getEvent()
  {
    return event;
  }

  public void setEvent(Enum event)
  {
    this.event = event;
  }
}
