package me.hollowdev.core.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger
{
  private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
  private File logged;
  
  public FileLogger(File folder)
  {
    if (folder == null) {
      return;
    }
    if (!folder.exists()) {
      folder.mkdir();
    }
    if (this.logged == null) {
      this.logged = new File(folder, "log.yml");
    }
    if (!this.logged.exists()) {
      try
      {
        this.logged.createNewFile();
      }
      catch (IOException e)
      {
        System.out.println("Failure to create 'log.yml'");
      }
    }
    try
    {
      this.logged.setWritable(true);
      Date d = new Date();
      PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(this.logged, true)));
      w.println("[Core] [" + format.format(d) + "] Started logging.");
      w.close();
    }
    catch (IOException localIOException1) {}
  }
  
  public void log(String msg)
  {
    try
    {
      PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(this.logged, true)));
      this.logged.setWritable(true);
      Date d = new Date();
      w.println("[Core] [" + format.format(d) + "] " + msg);
      w.close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void warn(String msg)
  {
    log("[WARNING] " + msg);
  }
  
  public void close()
  {
    log("Ended logging.\n");
  }
}
