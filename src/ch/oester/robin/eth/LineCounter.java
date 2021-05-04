package ch.oester.robin.eth;

import java.io.*;

public class LineCounter {

  private final File file;

  public LineCounter(String path) {
    this.file = new File(path);
  }

  public int countLines() {
    try {
      FileReader fReader = new FileReader(file);
      BufferedReader reader = new BufferedReader(fReader);
      int counter = 0;
      while(reader.readLine() != null) {
        counter++;
      }
      return counter;
    } catch (IOException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) {
    for(String path : args) {
      LineCounter c = new LineCounter(path);
      int val = c.countLines();
      if(val == -1) {
        System.out.println("Error while counting lines of file " + path);
      } else {
        System.out.println("File " + path + " contains " + val + " lines");
      }
    }
  }
}
