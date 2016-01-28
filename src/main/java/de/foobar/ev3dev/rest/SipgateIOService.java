package de.foobar.ev3dev.rest;

import de.foobar.ev3dev.Command;
import de.foobar.ev3dev.CommandQueue;
import de.foobar.ev3dev.sipgateio.Event;
import de.foobar.ev3dev.sipgateio.event.AbstractEvent;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.lang3.StringUtils;

@Path("sipgateio")
public class SipgateIOService {


  @POST
  @Path("incoming")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_XML)
  public String incomingCall(@FormParam("from") String from, @FormParam("to") String to,
                             @FormParam("direction") String direction, @FormParam("event") String event,
                             @FormParam("callId") String callId, @FormParam("user") String user,
                             @FormParam("cause") String cause, @FormParam("dtmf") String dtmf,
                             @FormParam("diversion") String diversion, @Context UriInfo uriInfo)
  {
    String[] users = {user};
    AbstractEvent current = Event.getEvent(from, to, direction, event,
        callId, users, cause, dtmf, diversion);

    return handleEvent(current, uriInfo);

  }

  @POST
  @Path("dtmf")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_XML)
  public String incomingCall(@FormParam("event") String event,
                             @FormParam("callId") String callId, @FormParam("dtmf") String dtmf, @Context UriInfo uriInfo)
  {
    return handleDTMF(dtmf, uriInfo);
  }

  private String handleEvent(AbstractEvent current, UriInfo uriInfo)
  {
    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<Response>\n" +
        "    <Gather onData=\""+ uriInfo.getBaseUri().toString() +"sipgateio/dtmf\" maxDigits=\"1\" timeout=\"100000\">\n" +
        "    </Gather>\n" +
        "</Response>";
  }

  private String handleDTMF(String dtmf, UriInfo uriInfo){
    String number = StringUtils.defaultIfEmpty(dtmf,"");

    if(number.length() == 1)
    {
      switch (number)
      {
        case "2":
          CommandQueue.getInstance().addCommand(Command.FORWARD);
          break;
        case "4":
          CommandQueue.getInstance().addCommand(Command.LEFT);
          break;
        case "6":
          CommandQueue.getInstance().addCommand(Command.RIGHT);
          break;
        case "8":
          CommandQueue.getInstance().addCommand(Command.BACK);
          break;
      }
    }

    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<Response>\n" +
        "    <Gather onData=\"" + uriInfo.getBaseUri().toString() + "sipgateio/dtmf\" maxDigits=\"1\" timeout=\"100000\">\n" +
        "    </Gather>\n" +
        "</Response>";
  }

}
