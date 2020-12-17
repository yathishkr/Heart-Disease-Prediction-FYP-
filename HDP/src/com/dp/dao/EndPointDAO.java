package com.dp.dao;

import com.dp.model.EndPoint;

public interface EndPointDAO
{
   public void write(EndPoint model) throws Exception;

   public void update(EndPoint model) throws Exception;

   public void delete(String id) throws Exception;

   public EndPoint get() throws Exception;
   
   public EndPoint getEndPointById(String id) throws Exception;
}
