package de.foobar.ev3dev.sipgateio;

import de.foobar.ev3dev.sipgateio.event.AbstractEvent;
import de.foobar.ev3dev.sipgateio.event.Answer;
import de.foobar.ev3dev.sipgateio.event.CallHangup;
import de.foobar.ev3dev.sipgateio.event.DTMF;
import de.foobar.ev3dev.sipgateio.event.HangupCauses;
import de.foobar.ev3dev.sipgateio.event.NewCall;

public enum Event
{
  NEW_CALL("newCall", NewCall.class),
  ANSWER("answer", Answer.class),
  HANGUP("hangup", CallHangup.class),
  DTMF("dtmf", de.foobar.ev3dev.sipgateio.event.DTMF.class);

  private String name;
  private Class clazz;

  Event(String name, Class clazz)
  {
    this.name = name;
    this.clazz = clazz;
  }

  public String getName()
  {
    return name;
  }

  public Class getClazz()
  {
    return clazz;
  }

  public static Event getEventType(String name)
  {
    for (Event event : Event.values())
    {
      if(event.getName().equals(name))
      {
        return event;
      }
    }
    return null;
  }

  public static AbstractEvent getEvent(String from, String to,
                                       String direction, String event,
                                       String callId, String[] user,
                                       String cause, String dtmf,
                                       String diversion)
  {
    Event current = getEventType(event);

    switch (current)
    {
      case NEW_CALL:
        return new NewCall(callId,NEW_CALL,from,to,direction,user,diversion);
      case ANSWER:
        return new Answer(callId,ANSWER,user);
      case HANGUP:
        return new CallHangup(callId,HANGUP, HangupCauses.getHangupCause(cause));
      case DTMF:
        return new DTMF(callId,DTMF,dtmf);
    }
    return null;
  }
}
