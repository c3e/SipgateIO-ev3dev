package de.foobar.ev3dev.sipgateio.event;

public class CallHangup extends AbstractEvent
{
  private HangupCauses cause;

  public CallHangup() {

  }

  public CallHangup(String callId, Enum event, HangupCauses cause) {
    super(callId, event);
    this.cause = cause;
  }

  public HangupCauses getCause() {
    return cause;
  }

  public void setCause(HangupCauses cause) {
    this.cause = cause;
  }
}
