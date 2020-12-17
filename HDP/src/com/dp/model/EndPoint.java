package com.dp.model;

public class EndPoint
{
   private String id;
   private String type;
   private String email;
   private String host;
   private String port;
   private String appname;
   private String contextroot;

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public String getEmail()
   {
      return email;
   }

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getHost()
   {
      return host;
   }

   public void setHost(String host)
   {
      this.host = host;
   }

   public String getPort()
   {
      return port;
   }

   public void setPort(String port)
   {
      this.port = port;
   }

   public String getAppname()
   {
      return appname;
   }

   public void setAppname(String appname)
   {
      this.appname = appname;
   }

   public String getContextroot()
   {
      return contextroot;
   }

   public void setContextroot(String contextroot)
   {
      this.contextroot = contextroot;
   }

}
