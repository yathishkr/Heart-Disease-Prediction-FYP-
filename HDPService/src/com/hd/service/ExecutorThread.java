package com.hd.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutorThread implements Runnable
{

   Thread t;
   String filename;
   String dir;
   List<String> params;

   public ExecutorThread(String filename, String dir, List<String> params)
   {
      this.filename = filename;
      this.dir = dir;
      this.params = params;
      t = new Thread(this);
      t.start();
   }

   @Override
   public void run()
   {
      try
      {
         String output = runScript();
         String lines[] = output.split("\n");
         int result = -1;
         if (lines[0].trim().length() > 2)
         {
            // Sequential
            result = (int) Float.parseFloat(lines[lines.length - 1]);
         }
         else
         {
            // Others
            int r1 = Integer.parseInt(lines[0]);
            int r2 = Integer.parseInt(lines[1]);
            int r3 = Integer.parseInt(lines[2]);
            Map<Integer, Integer> resultCheck = new HashMap<Integer, Integer>();

            if (resultCheck.containsKey(r1))
               resultCheck.put(r1, resultCheck.get(r1) + 1);
            else
               resultCheck.put(r1, 1);

            if (resultCheck.containsKey(r2))
               resultCheck.put(r2, resultCheck.get(r2) + 1);
            else
               resultCheck.put(r2, 1);

            if (resultCheck.containsKey(r3))
               resultCheck.put(r3, resultCheck.get(r3) + 1);
            else
               resultCheck.put(r3, 1);

            if (!resultCheck.containsKey(0))
               resultCheck.put(0, 0);
            if (!resultCheck.containsKey(1))
               resultCheck.put(1, 0);

            int n0 = resultCheck.get(0);
            int n1 = resultCheck.get(1);

            result = n0 > n1 ? 0 : 1;

         }

         OutputCollector.output.put(filename, result);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.out.println(filename + " .. thrown Exception");
      }
   }

   public String runScript() throws Exception
   {
      List<String> command = new ArrayList<String>();
      command.add("python3");
      command.add(filename);
      command.addAll(params);
      ProcessBuilder builder = new ProcessBuilder(command);
      builder.directory(new File(dir));
      System.out.println("Running command .. "+command);

      final Process process = builder.start();
      InputStream is = process.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String line = "";
      String output = "";
      while ((line = br.readLine()) != null)
      {
         if (output.length() != 0)
            output += "\n";
         output += line;
      }
      return output;

   }
}
