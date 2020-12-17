package com.dp.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InputServlet extends HttpServlet
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
         if (reqType == null)
         {
            resp.sendRedirect("input.jsp?msg=Bad Request");
         }
         else
         {
            if (reqType.equals("clear"))
            {
               req.getSession().removeAttribute("input");
               resp.sendRedirect("input.jsp?msg=Model Input Data cleared");
            }
            else if (reqType.equals("set"))
            {
               String inputType = req.getParameter("inputType");
               Map<String, String> input = new HashMap<String, String>();
               if (inputType.equals("field"))
               {
                  input.put("time", req.getParameter("time"));
                  input.put("v1", req.getParameter("v1"));
                  input.put("v2", req.getParameter("v2"));
                  input.put("v3", req.getParameter("v3"));
                  input.put("v4", req.getParameter("v4"));
                  input.put("v5", req.getParameter("v5"));
                  input.put("v6", req.getParameter("v6"));
                  input.put("v7", req.getParameter("v7"));
                  input.put("v8", req.getParameter("v8"));
                  input.put("v9", req.getParameter("v9"));
                  input.put("v10", req.getParameter("v10"));
                  input.put("v11", req.getParameter("v11"));
                  input.put("v12", req.getParameter("v12"));
                  input.put("v13", req.getParameter("v13"));
                  input.put("v14", req.getParameter("v14"));
                  input.put("v15", req.getParameter("v15"));
                  input.put("v16", req.getParameter("v16"));
                  input.put("v17", req.getParameter("v17"));
                  input.put("v18", req.getParameter("v18"));
                  input.put("v19", req.getParameter("v19"));
                  input.put("v20", req.getParameter("v20"));
                  input.put("v21", req.getParameter("v21"));
                  input.put("v22", req.getParameter("v22"));
                  input.put("v23", req.getParameter("v23"));
                  input.put("v24", req.getParameter("v24"));
                  input.put("v25", req.getParameter("v25"));
                  input.put("v26", req.getParameter("v26"));
                  input.put("v27", req.getParameter("v27"));
                  input.put("v28", req.getParameter("v28"));
                  input.put("amount", req.getParameter("amount"));
               }
               else
               {
                  String data = req.getParameter("data");
                  String[] arr = data.split(",");
                  input.put("time", arr[0]);
                  input.put("v1", arr[1]);
                  input.put("v2", arr[2]);
                  input.put("v3", arr[3]);
                  input.put("v4", arr[4]);
                  input.put("v5", arr[5]);
                  input.put("v6", arr[6]);
                  input.put("v7", arr[7]);
                  input.put("v8", arr[8]);
                  input.put("v9", arr[9]);
                  input.put("v10", arr[10]);
                  input.put("v11", arr[11]);
                  input.put("v12", arr[12]);
                  input.put("v13", arr[13]);
                  input.put("v14", arr[14]);
                  input.put("v15", arr[15]);
                  input.put("v16", arr[16]);
                  input.put("v17", arr[17]);
                  input.put("v18", arr[18]);
                  input.put("v19", arr[19]);
                  input.put("v20", arr[20]);
                  input.put("v21", arr[21]);
                  input.put("v22", arr[22]);
                  input.put("v23", arr[23]);
                  input.put("v24", arr[24]);
                  input.put("v25", arr[25]);
                  input.put("v26", arr[26]);
                  input.put("v27", arr[27]);
                  input.put("v28", arr[28]);
                  input.put("amount", arr[29]);
               }
               req.getSession().setAttribute("input", input);
               resp.sendRedirect("input.jsp");
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("input.jsp?msg=" + e.getMessage());
      }
   }

}
