package com.dp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dp.dao.EndPointDAO;
import com.dp.model.EndPoint;
import com.dp.util.DBConnection;

public class EndPointDAOImpl implements EndPointDAO
{

   @Override
   public void write(EndPoint model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("insert into endpoint values (?,?,?,?,?,?,?)");
         ps.setString(1, model.getId());
         ps.setString(2, model.getType());
         ps.setString(3, model.getEmail());
         ps.setString(4, model.getHost());
         ps.setString(5, model.getPort());
         ps.setString(6, model.getAppname());
         ps.setString(7, model.getContextroot());
         ps.execute();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         con.close();
      }
   }

   @Override
   public void update(EndPoint model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("update endpoint set host=?, port=?, appname=?, contextroot=? where id=?");
         ps.setString(5, model.getId());
         ps.setString(1, model.getHost());
         ps.setString(2, model.getPort());
         ps.setString(3, model.getAppname());
         ps.setString(4, model.getContextroot());
         ps.execute();
      }
      catch (Exception e)
      {

      }
      finally
      {
         con.close();
      }

   }

   @Override
   public void delete(String id) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("delete from endpoint where id='" + id + "' ");
      }
      catch (Exception e)
      {

      }
      finally
      {
         con.close();
      }

   }

   @Override
   public EndPoint get() throws Exception
   {
      Connection con = null;
      EndPoint model = new EndPoint();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from endpoint");
         rs.next();
         model.setAppname(rs.getString("appname"));
         model.setContextroot(rs.getString("contextroot"));
         model.setEmail(rs.getString("email"));
         model.setHost(rs.getString("host"));
         model.setId(rs.getString("id"));
         model.setPort(rs.getString("port"));
         model.setType(rs.getString("type"));

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         con.close();
      }
      return model;
   }

   @Override
   public EndPoint getEndPointById(String id) throws Exception
   {
      Connection con = null;
      EndPoint model = new EndPoint();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from endpoint where id='" + id + "' ");
         while (rs.next())
         {
            model.setAppname(rs.getString("appname"));
            model.setContextroot(rs.getString("contextroot"));
            model.setEmail(rs.getString("email"));
            model.setHost(rs.getString("host"));
            model.setId(rs.getString("id"));
            model.setPort(rs.getString("port"));
            model.setType(rs.getString("type"));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         con.close();
      }
      return model;
   }

}
