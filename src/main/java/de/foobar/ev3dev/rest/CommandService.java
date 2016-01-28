package de.foobar.ev3dev.rest;

import de.foobar.ev3dev.Command;
import de.foobar.ev3dev.CommandQueue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("command")
public class CommandService {


  @GET
  @Path("forward")
  @Produces(MediaType.TEXT_PLAIN)
  public String moveForward()
  {
    CommandQueue.getInstance().addCommand(Command.FORWARD);
    return "Got it! move forward...";
  }

  @GET
  @Path("back")
  @Produces(MediaType.TEXT_PLAIN)
  public String moveBack()
  {
    CommandQueue.getInstance().addCommand(Command.BACK);
    return "Got it! move back...";
  }

  @GET
  @Path("right")
  @Produces(MediaType.TEXT_PLAIN)
  public String turnRight()
  {
    CommandQueue.getInstance().addCommand(Command.RIGHT);
    return "Got it! move right ...";
  }

  @GET
  @Path("left")
  @Produces(MediaType.TEXT_PLAIN)
  public String turnLeft()
  {
    CommandQueue.getInstance().addCommand(Command.LEFT);
    return "Got it! move right ...";
  }

}
