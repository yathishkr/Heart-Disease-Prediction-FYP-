package com.dp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dp.model.EndPoint;
import com.dp.model.User;
import com.dp.dao.EndPointDAO;
import com.dp.daoimpl.EndPointDAOImpl;

public class EndPointServlet extends HttpServlet
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
      try
      {
         String reqType = req.getParameter("reqType");
         String adminemail = (String) req.getSession().getAttribute("adminemail");
         EndPointDAO endPointDao = new EndPointDAOImpl();
         if (reqType == null)
         {
            resp.sendRedirect("configure.jsp?msg=Bad Request");
         }
         else
         {
            if (reqType.equals("add"))
            {
               EndPoint model = new EndPoint();
               model.setAppname(req.getParameter("appname"));
               model.setContextroot(req.getParameter("contextroot"));
               model.setEmail(adminemail);
               model.setHost(req.getParameter("host"));
               model.setId("EP-" + String.valueOf(System.currentTimeMillis()));
               model.setPort(req.getParameter("port"));
               model.setType(req.getParameter("type"));
               endPointDao.write(model);
               resp.sendRedirect("endpoint?reqType=get&msg=Successfully Created the End Point");
            }
            else if (reqType.equals("update"))
            {
               EndPoint model = new EndPoint();
               model.setAppname(req.getParameter("appname"));
               model.setContextroot(req.getParameter("contextroot"));
               model.setEmail(adminemail);
               model.setHost(req.getParameter("host"));
               model.setId(req.getParameter("id"));
               model.setPort(req.getParameter("port"));
               model.setType(req.getParameter("type"));
               endPointDao.update(model);
               resp.sendRedirect("endpoint?reqType=get&msg=Successfully Updated the End Point (ID:" + req.getParameter("id") + " )");
            }
            else if (reqType.equals("delete"))
            {
               String id = req.getParameter("id");
               endPointDao.delete(id);
               resp.sendRedirect("endpoint?reqType=get&msg=Deleted the EndPoint with ID: " + id);
            }
            else if (reqType.equals("get"))
            {
               EndPoint endPoints = endPointDao.get();
               req.getSession().setAttribute("endPoint", endPoints);
               req.getRequestDispatcher("configure.jsp").forward(req, resp);
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("configure.jsp?msg=Error: " + e.getMessage());
      }
   }

}
