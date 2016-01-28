package de.foobar.ev3dev;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FileHandler {

  private Path path;

  public FileHandler(String path)
  {
    this.path = Paths.get(path);
    System.out.println("set path");
  }

  public boolean fileExists()
  {
    return Files.exists(this.path);
  }

  public boolean writeToFile(String content)
  {
    if(Main.DRY_RUN)
    {
      System.out.println(String.format("Write to %s : '%s'", this.path.toString(), content));
      return true;
    }
    if(! fileExists())
    {
      System.out.println("File not found: " + this.path.toString());
      return false;
    }

    try
    {
      Files.write(path, content.getBytes(), StandardOpenOption.WRITE);
    }
    catch (Exception e)
    {
      System.out.println("Cannot write to file: " + path.toString());
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public String readValueFromFile(String content) {
    if (Main.DRY_RUN)
    {
      System.out.println(String.format("Read from file %s ", this.path.toString()));
      return "";
    }
    if (!fileExists())
    {
      System.out.println("File not found: " + this.path.toString());
      return null;
    }
    try
    {
      return new String(Files.readAllBytes(this.path));
    }
    catch (Exception e)
    {
      System.out.println("Cannot write to file: " + path.toString());
      e.printStackTrace();
      return null;
    }

  }

  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
