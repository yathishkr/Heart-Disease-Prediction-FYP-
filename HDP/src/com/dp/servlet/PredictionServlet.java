package com.dp.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dp.dao.PredictionsDAO;
import com.dp.daoimpl.EndPointDAOImpl;
import com.dp.daoimpl.PredictionsDAOImpl;
import com.dp.executor.ExecutorThread;
import com.dp.model.EndPoint;
import com.dp.model.Predictions;
import com.dp.model.User;

public class PredictionServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      doPost(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      String reqType = req.getParameter("req_type");
      String admin = (String) req.getSession().getAttribute("adminemail");
      User user = (User) req.getSession().getAttribute("user");
      PredictionsDAO pDao = new PredictionsDAOImpl();
      try
      {
         if (reqType == null)
         {
            if (admin == null)
               resp.sendRedirect("predictions.jsp?msg=Bad Request");
            else
               resp.sendRedirect("adminpredictions.jsp?msg=Bad Request");
         }
         else
         {
            if (reqType.equals("add"))
            {
               String input = req.getParameter("input");
               Predictions model = new Predictions();
               model.setAdmin(null);
               model.setEmail(user.getEmail());
               model.setType(req.getParameter("type"));
               model.setEntry_time(new Timestamp(System.currentTimeMillis()));
               model.setId("PRED-" + System.currentTimeMillis());
               model.setInput(input);
               model.setResult(null);
               model.setStatus("NEW");
               pDao.write(model);
               resp.sendRedirect("predictions.jsp?msg=Prediction request submitted successfully");
            }
            else if (reqType.equals("delete"))
            {
               String id = req.getParameter("id");
               pDao.delete(id);
               resp.sendRedirect("predictions.jsp?msg=Prediction request Deleted successfully");

            }
            else if (reqType.equals("run"))
            {
               String id = req.getParameter("id");
               Predictions pred = pDao.getByID(id);
               EndPoint ep = new EndPointDAOImpl().get();
               System.out.println("End Point: "+ep);
               new ExecutorThread(id, pred.getInput(), ep);
               pDao.updateStatus(id, "RUNNING", admin);
               resp.sendRedirect("adminpredictions.jsp?msg=Prediction Job Successfully initiated");
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         if (admin == null)
            resp.sendRedirect("predictions.jsp?msg=" + e.getMessage());
         else
            resp.sendRedirect("adminpredictions.jsp?msg=" + e.getMessage());
      }
   }

}
