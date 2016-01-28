package de.foobar.ev3dev.sipgateio.event;

public enum HangupCauses
{
  NORMAL_CLEARING("normalClearing"),
  BUSY("busy"),
  CANCEL("cancel"),
  NO_ANSWER("noAnswer"),
  CONGESTION("congestion"),
  NOT_FOUND("notFound");

  private String cause;

  HangupCauses(String cause)
  {
    this.cause = cause;
  }

  public String getCause()
  {
    return cause;
  }

  public static HangupCauses getHangupCause(String name)
  {
    for (HangupCauses event : HangupCauses.values())
    {
      if(event.getCause().equals(name))
      {
        return event;
      }
    }
    return null;
  }
}
