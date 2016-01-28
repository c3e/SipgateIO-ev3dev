package de.foobar.ev3dev.sipgateio.event;

public class DTMF extends AbstractEvent
{
  private String dtmf;

  public DTMF() {
  }

  public DTMF(String callId, Enum event, String dtmf) {
    super(callId, event);
    this.dtmf = dtmf;
  }

  public String getDtmf() {
    return dtmf;
  }

  public void setDtmf(String dtmf) {
    this.dtmf = dtmf;
  }
}
