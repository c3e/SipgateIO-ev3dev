package de.foobar.ev3dev;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.accesslog.AccessLogBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class Main implements Runnable{

  public static final String BASE_URI = "http://0.0.0.0:10815/ev3dev/";
  public static final boolean DRY_RUN = false;
  public static boolean SWITCH_MOTOR = false;

  private static final int DEFAULT = 0;
  private static final long SCHEDULE_RATE_MILLISECONDS = 750;


  private static Main INSTANCE;

  private HttpServer server;

  public HttpServer startServer() {

    final ResourceConfig rc = new ResourceConfig().packages("de.foobar.ev3dev");
    server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

    final AccessLogBuilder builder = new AccessLogBuilder("/tmp/access.log");
    builder.instrument(server.getServerConfiguration());

    return server;
  }


  public static void main(String[] args) throws IOException
  {
    INSTANCE = new Main();
    INSTANCE.startServer();
    System.out.println(String.format("server started? : %s ", String.valueOf(INSTANCE.getServer().isStarted())));
    System.out.println(String.format("server listen on %s ", BASE_URI));
    INSTANCE.schedule();

    if(args.length > 0 && args[0] == "switch-motor"){
      SWITCH_MOTOR = true;
    }
  }

  public void schedule ()
  {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(this, 0, SCHEDULE_RATE_MILLISECONDS, TimeUnit.MILLISECONDS);
    System.out.println(executor);
  }

  public void run()
  {
    Command toExecute = CommandQueue.getInstance().getMostVotedCommand();

    if(toExecute == null)
    {
      System.out.println("nothing to do... boring!");
      return;
    }

    executeCommand(toExecute);
  }

  private void executeCommand(Command toExecute)
  {
    switch (toExecute)
    {
      case FORWARD:
        SipgateIOEv3Dev.moveForward(DEFAULT); break;
      case BACK:
        SipgateIOEv3Dev.moveBack(DEFAULT); break;
      case LEFT:
        SipgateIOEv3Dev.turnLeft(DEFAULT); break;
      case RIGHT:
        SipgateIOEv3Dev.turnRight(DEFAULT); break;
    }
  }


  public static Main getInstance() {
    return INSTANCE;
  }

  public HttpServer getServer() {
    return server;
  }

  public void setServer(HttpServer server) {
    this.server = server;
  }
}

