package com.dp.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dp.dao.EntitlementDAO;
import com.dp.daoimpl.EntitlementDAOImpl;
import com.dp.model.Entitlement;
import com.dp.model.User;

public class EntitlementServlet extends HttpServlet
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
      EntitlementDAO eDao = new EntitlementDAOImpl();
      User user = (User) req.getSession().getAttribute("user");
      String adminemail = (String) req.getSession().getAttribute("adminemail");
      try
      {
         if (reqType == null)
         {
            if (adminemail == null)
               resp.sendRedirect("entitlements.jsp?msg=Bad Request");
            else
               resp.sendRedirect("adminentitlements.jsp?msg=Bad Request");
         }
         else
         {
            if (reqType.equals("add"))
            {
               Entitlement model = new Entitlement();
               model.setApprover(null);
               model.setEmail(user.getEmail());
               model.setEntrytime(new Timestamp(System.currentTimeMillis()));
               model.setStatus("NEW");
               eDao.write(model);
               resp.sendRedirect("entitlements.jsp?msg=Entitlement Request Submitted Successfully");
            }
            else if (reqType.equals("approve"))
            {
               String email = req.getParameter("email");
               eDao.approve(email, adminemail);
               resp.sendRedirect("adminentitlements.jsp?msg=Request from " + email + " APPROVED");
            }
            else if (reqType.equals("reject"))
            {
               String email = req.getParameter("email");
               eDao.reject(email, adminemail);
               resp.sendRedirect("adminentitlements.jsp?msg=Request from " + email + " REJECTED");
            }
            else if (reqType.equals("cancel"))
            {
               eDao.cancel(user.getEmail());
               resp.sendRedirect("entitlements.jsp?msg=Entitlement Request Cancelled");
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         if (adminemail == null)
            resp.sendRedirect("entitlements.jsp?msg=" + e.getMessage());
         else
            resp.sendRedirect("adminentitlements.jsp?msg=" + e.getMessage());
      }
   }

}
