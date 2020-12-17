package com.hd.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.hd.util.Constants;

public class ExecutorService
{

   public static String analyzeData(List<String> params) throws Exception
   {
      ExecutorThread e1 = new ExecutorThread(Constants.SCRIPT_NAME_1, Constants.SCRIPT_DIR, params);
      ExecutorThread e2 = new ExecutorThread(Constants.SCRIPT_NAME_2, Constants.SCRIPT_DIR, params);
      ExecutorThread e3 = new ExecutorThread(Constants.SCRIPT_NAME_3, Constants.SCRIPT_DIR, params);
      ExecutorThread e4 = new ExecutorThread(Constants.SCRIPT_NAME_4, Constants.SCRIPT_DIR, params);
      ExecutorThread e5 = new ExecutorThread(Constants.SCRIPT_NAME_5, Constants.SCRIPT_DIR, params);
      ExecutorThread e6 = new ExecutorThread(Constants.SCRIPT_NAME_6, Constants.SCRIPT_DIR, params);
      //ExecutorThread e7 = new ExecutorThread(Constants.SCRIPT_NAME_7, Constants.SCRIPT_DIR, params);
      //ExecutorThread e8 = new ExecutorThread(Constants.SCRIPT_NAME_8, Constants.SCRIPT_DIR, params);

      e1.t.join();
      e2.t.join();
      e3.t.join();
      e4.t.join();
      e5.t.join();
      e6.t.join();
      //e7.t.join();
      //e8.t.join();

      Iterator<String> it = OutputCollector.output.keySet().iterator();
      int n0 = 0;
      int n1 = 0;
      while (it.hasNext())
      {
         String key = it.next();
         int val = OutputCollector.output.get(key);
         System.out.println(key + " ============================ " + val);
         if (val == 0)
            n0++;
         else if (val == 1)
            n1++;
      }

      return (n0 > n1) ? "0" : "1";
   }

   public static void main(String arg[]) throws Exception
   {
      String[] params = new String[] { "0", "1.19185711131486", "0.26615071205963", "0.16648011335321",
               "0.448154078460911", "0.0600176492822243", "-0.0823608088155687",
               "-0.0788029833323113", "0.0851016549148104", "-0.255425128109186", "-0.166974414004614", "1.61272666105479", "1.06523531137287", "0.48909501589608",
               "-0.143772296441519", "0.635558093258208", "0.463917041022171", "-0.114804663102346", "-0.183361270123994", "-0.145783041325259", "-0.0690831352230203",
               "-0.225775248033138", "-0.638671952771851", "0.101288021253234", "-0.339846475529127", "0.167170404418143", "0.125894532368176", "-0.00898309914322813",
               "0.0147241691924927", "2.69" };
      String runDir = "C:\\Ashok\\Projects2020\\DevWorkspace\\notebooks";
      System.out.println("Running ***");
//      String output = runPythonScript("CardFraudDetection.py", runDir, Arrays.asList(params));
//      System.out.println("Output");
//      System.out.println(output);
   }
}
