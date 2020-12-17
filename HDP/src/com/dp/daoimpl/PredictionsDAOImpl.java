package com.dp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.PredictionsDAO;
import com.dp.model.Predictions;
import com.dp.util.DBConnection;

public class PredictionsDAOImpl implements PredictionsDAO
{

   @Override
   public void write(Predictions model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("insert into predictions values(?,?,?,?,?,?,?,?)");
         ps.setString(1, model.getId());
         ps.setString(2, model.getEmail());
         ps.setString(3, model.getType());
         ps.setString(4, model.getInput());
         ps.setString(5, model.getStatus());
         ps.setString(6, model.getAdmin());
         ps.setString(7, model.getResult());
         ps.setTimestamp(8, model.getEntry_time());
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
   public void updateResult(String id, String result) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("update predictions set result='" + result + "', status='COMPLETED' where id='" + id + "'  ");
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
   public void updateStatus(String id, String status, String admin) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("update predictions set status='RUNNING', admin='"+admin+"' where id='" + id + "'  ");
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
   public void delete(String id) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("delete from predictions where id='" + id + "'");
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
   public List<Predictions> getAllOpen() throws Exception
   {
      Connection con = null;
      List<Predictions> result = new ArrayList<Predictions>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from predictions where status='NEW' ");
         while (rs.next())
         {
            Predictions model = new Predictions();
            model.setAdmin(rs.getString("admin"));
            model.setEmail(rs.getString("email"));
            model.setEntry_time(rs.getTimestamp("entry_time"));
            model.setId(rs.getString("id"));
            model.setInput(rs.getString("input"));
            model.setResult(rs.getString("result"));
            model.setStatus(rs.getString("status"));
            model.setType(rs.getString("type"));
            result.add(model);
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
      return result;
   }

   @Override
   public List<Predictions> getAllOpen(String email) throws Exception
   {
      Connection con = null;
      List<Predictions> result = new ArrayList<Predictions>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from predictions where status='NEW' and email='" + email + "' ");
         while (rs.next())
         {
            Predictions model = new Predictions();
            model.setAdmin(rs.getString("admin"));
            model.setEmail(rs.getString("email"));
            model.setEntry_time(rs.getTimestamp("entry_time"));
            model.setId(rs.getString("id"));
            model.setInput(rs.getString("input"));
            model.setResult(rs.getString("result"));
            model.setStatus(rs.getString("status"));
            model.setType(rs.getString("type"));
            result.add(model);
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
      return result;
   }

   @Override
   public List<Predictions> getAllCompleted() throws Exception
   {
      Connection con = null;
      List<Predictions> result = new ArrayList<Predictions>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from predictions where status='COMPLETED' or status='RUNNING' ");
         while (rs.next())
         {
            Predictions model = new Predictions();
            model.setAdmin(rs.getString("admin"));
            model.setEmail(rs.getString("email"));
            model.setEntry_time(rs.getTimestamp("entry_time"));
            model.setId(rs.getString("id"));
            model.setInput(rs.getString("input"));
            model.setResult(rs.getString("result"));
            model.setStatus(rs.getString("status"));
            model.setType(rs.getString("type"));
            result.add(model);
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
      return result;
   }

   @Override
   public List<Predictions> getAllCompleted(String email) throws Exception
   {
      Connection con = null;
      List<Predictions> result = new ArrayList<Predictions>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from predictions where status='COMPLETED' or status='RUNNING'  and email='" + email + "' ");
         while (rs.next())
         {
            Predictions model = new Predictions();
            model.setAdmin(rs.getString("admin"));
            model.setEmail(rs.getString("email"));
            model.setEntry_time(rs.getTimestamp("entry_time"));
            model.setId(rs.getString("id"));
            model.setInput(rs.getString("input"));
            model.setResult(rs.getString("result"));
            model.setStatus(rs.getString("status"));
            model.setType(rs.getString("type"));
            result.add(model);
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
      return result;
   }

   @Override
   public Predictions getByID(String id) throws Exception
   {
      Connection con = null;
      Predictions model = new Predictions();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from predictions where id='" + id + "' ");
         rs.next();
         model.setAdmin(rs.getString("admin"));
         model.setEmail(rs.getString("email"));
         model.setEntry_time(rs.getTimestamp("entry_time"));
         model.setId(rs.getString("id"));
         model.setInput(rs.getString("input"));
         model.setResult(rs.getString("result"));
         model.setStatus(rs.getString("status"));
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

}
