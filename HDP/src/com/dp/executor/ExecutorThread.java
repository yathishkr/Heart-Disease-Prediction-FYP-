package com.dp.executor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.dp.dao.PredictionsDAO;
import com.dp.daoimpl.PredictionsDAOImpl;
import com.dp.model.EndPoint;

public class ExecutorThread implements Runnable
{

   Thread t;
   String input;
   EndPoint endpoint;
   PredictionsDAO pDao;
   String id; 
   
   public ExecutorThread(String id, String input, EndPoint endpoint)
   {
      pDao = new PredictionsDAOImpl();
      this.input = input;
      this.id = id;
      this.endpoint = endpoint;
      t = new Thread(this);
      t.start();
      
   }

   @Override
   public void run()
   {

      try
      {

         byte[] postData = input.getBytes();
         int postDataLength = postData.length;
         URL url = new URL("http://" + endpoint.getHost() + ":" + endpoint.getPort() + "/" + endpoint.getAppname() + "/" + endpoint.getContextroot());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setDoOutput(true);
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Type", "text/plain");
         conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
         conn.setUseCaches(false);
         try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream()))
         {
            wr.write(postData);
         }

         if (conn.getResponseCode() != 200)
         {
            throw new RuntimeException("Failed : HTTP error code : "
                     + conn.getResponseCode());
         }

         BufferedReader br = new BufferedReader(new InputStreamReader(
                  (conn.getInputStream())));

         String output = "";
         String line = "";
         while ((line = br.readLine()) != null)
         {
            output += line;
         }

         conn.disconnect();

         System.out.println("Output: "+output);
         pDao.updateResult(id, output);
         
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}
