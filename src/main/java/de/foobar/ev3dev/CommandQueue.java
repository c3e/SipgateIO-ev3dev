package de.foobar.ev3dev;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

public class CommandQueue
{
  private static CommandQueue ourInstance = new CommandQueue();

  private Vector<Command> commands = new Vector<>();

  public static CommandQueue getInstance()
  {
    return ourInstance;
  }

  private CommandQueue() {
  }

  public void addCommand(Command command)
  {
    this.commands.addElement(command);
  }

  public Command getMostVotedCommand() {

    Vector<Command> current = (Vector) commands.clone();
    commands.clear();

    if(current.size() == 0 )
    {
      return null;
    }

    List<Map.Entry<Command, Integer>> winner = getCommandsWithMaxVotes(current);

    System.out.println(winner.get(0));

    return winner.get(0).getKey();
  }

  private List<Map.Entry<Command, Integer>> getCommandsWithMaxVotes(Vector<Command> current)
  {
    HashMap<Command, Integer> commandCount = new HashMap<>();

    for(Command c : Command.values())
    {
      commandCount.put(c, 0);
    }

    for(Command c : current)
    {
      commandCount.replace(c, commandCount.get(c)+1);
    }

    int max = commandCount.entrySet().stream().max(
        (e1, e2) -> e1.getValue() > e2.getValue() ? 1 : -1).get().getValue();

    List<Map.Entry<Command, Integer>> winner = commandCount.entrySet().stream()
        .filter((entry) -> entry.getValue() == max).collect(Collectors.toList());

    Collections.shuffle(winner);
    return winner;
  }
}
