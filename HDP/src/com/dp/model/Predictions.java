package com.dp.model;

import java.sql.Timestamp;

public class Predictions
{

   private String id;
   private String email;
   private String type;
   private String input;
   private String admin;
   private String status;
   private String result;
   private Timestamp entry_time;

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getInput()
   {
      return input;
   }

   public void setInput(String input)
   {
      this.input = input;
   }

   public String getAdmin()
   {
      return admin;
   }

   public void setAdmin(String admin)
   {
      this.admin = admin;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getResult()
   {
      return result;
   }

   public void setResult(String result)
   {
      this.result = result;
   }

   public Timestamp getEntry_time()
   {
      return entry_time;
   }

   public void setEntry_time(Timestamp entry_time)
   {
      this.entry_time = entry_time;
   }

}
