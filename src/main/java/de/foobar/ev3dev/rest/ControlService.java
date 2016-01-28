package de.foobar.ev3dev.rest;

import de.foobar.ev3dev.SipgateIOEv3Dev;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("control")
public class ControlService {

  private static final int DEFAULT = 0;

  @GET
  @Path("forward")
  @Produces(MediaType.TEXT_PLAIN)
  public String moveForward()
  {
    SipgateIOEv3Dev.moveForward(DEFAULT);
    return "Got it! move forward...";
  }

  @GET
  @Path("back")
  @Produces(MediaType.TEXT_PLAIN)
  public String moveBack()
  {
    SipgateIOEv3Dev.moveBack(DEFAULT);
    return "Got it! move back...";
  }

  @GET
  @Path("right")
  @Produces(MediaType.TEXT_PLAIN)
  public String turnRight(@QueryParam("ms") int ms)
  {
    SipgateIOEv3Dev.turnRight(ms);
    return "Got it! move right ...";
  }

  @GET
  @Path("left")
  @Produces(MediaType.TEXT_PLAIN)
  public String turnLeft(@QueryParam("ms") int ms)
  {
    SipgateIOEv3Dev.turnLeft(ms);
    return "Got it! move right ...";
  }

}
