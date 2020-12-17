package com.dp.dao;

import java.util.List;

import com.dp.model.Predictions;

public interface PredictionsDAO
{

   public void write(Predictions model) throws Exception;

   public void delete(String id) throws Exception;

   public List<Predictions> getAllOpen() throws Exception;

   public List<Predictions> getAllCompleted() throws Exception;

   public List<Predictions> getAllOpen(String email) throws Exception;

   public List<Predictions> getAllCompleted(String email) throws Exception;

   public Predictions getByID(String id) throws Exception;

   public void updateResult(String id, String result) throws Exception;

   public void updateStatus(String id, String status, String admin) throws Exception;

}
