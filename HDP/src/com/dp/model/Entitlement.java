package com.dp.model;

import java.sql.Timestamp;

public class Entitlement
{
   private String email;
   private String status;
   private String approver;
   private Timestamp entrytime;

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getApprover()
   {
      return approver;
   }

   public void setApprover(String approver)
   {
      this.approver = approver;
   }

   public Timestamp getEntrytime()
   {
      return entrytime;
   }

   public void setEntrytime(Timestamp entrytime)
   {
      this.entrytime = entrytime;
   }

}
