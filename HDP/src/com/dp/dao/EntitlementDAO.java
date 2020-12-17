package com.dp.dao;

import java.util.List;

import com.dp.model.Entitlement;

public interface EntitlementDAO
{
   public void write(Entitlement model) throws Exception;

   public void approve(String email, String approver) throws Exception;

   public void reject(String email, String approver) throws Exception;

   public void cancel(String email) throws Exception;

   public Entitlement getByEmail(String email) throws Exception;

   public List<Entitlement> getAllApproved() throws Exception;

   public List<Entitlement> getAllOpen() throws Exception;

   List<Entitlement> getAllRejected() throws Exception;
}
