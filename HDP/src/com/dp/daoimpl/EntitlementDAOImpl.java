package com.dp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.EntitlementDAO;
import com.dp.model.Entitlement;
import com.dp.util.DBConnection;

public class EntitlementDAOImpl implements EntitlementDAO
{

   @Override
   public void write(Entitlement model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("insert into entitlements values (?,?,?,?)");
         ps.setString(1, model.getEmail());
         ps.setString(2, model.getStatus());
         ps.setString(3, model.getApprover());
         ps.setTimestamp(4, model.getEntrytime());
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
   public void approve(String email, String approver) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("update entitlements set status='APPROVED', approver='" + approver + "' where email='" + email + "'  ");

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
   public void reject(String email, String approver) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("update entitlements set status='REJECTED', approver='" + approver + "' where email='" + email + "'  ");
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
   public void cancel(String email) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("delete from entitlements where email='" + email + "'  ");
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
   public Entitlement getByEmail(String email) throws Exception
   {
      Connection con = null;
      Entitlement model = new Entitlement();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from entitlements where email='" + email + "' ");
         rs.next();
         model.setApprover(rs.getString("approver"));
         model.setEmail(rs.getString("email"));
         model.setEntrytime(rs.getTimestamp("entry_time"));
         model.setStatus(rs.getString("status"));
         System.out.println("Status: "+model.getStatus());
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
   public List<Entitlement> getAllApproved() throws Exception
   {
      Connection con = null;
      List<Entitlement> result = new ArrayList<Entitlement>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from entitlements where status='APPROVED' ");
         while (rs.next())
         {
            Entitlement model = new Entitlement();
            model.setApprover(rs.getString("approver"));
            model.setEmail(rs.getString("email"));
            model.setEntrytime(rs.getTimestamp("entry_time"));
            model.setStatus(rs.getString("status"));
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
   public List<Entitlement> getAllOpen() throws Exception
   {
      Connection con = null;
      List<Entitlement> result = new ArrayList<Entitlement>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from entitlements where status='NEW' ");
         while (rs.next())
         {
            Entitlement model = new Entitlement();
            model.setApprover(rs.getString("approver"));
            model.setEmail(rs.getString("email"));
            model.setEntrytime(rs.getTimestamp("entry_time"));
            model.setStatus(rs.getString("status"));
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
   public List<Entitlement> getAllRejected() throws Exception
   {
      Connection con = null;
      List<Entitlement> result = new ArrayList<Entitlement>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from entitlements where status='REJECTED' ");
         while (rs.next())
         {
            Entitlement model = new Entitlement();
            model.setApprover(rs.getString("approver"));
            model.setEmail(rs.getString("email"));
            model.setEntrytime(rs.getTimestamp("entry_time"));
            model.setStatus(rs.getString("status"));
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

}
