package com.hd.rest;

import java.util.Arrays;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hd.service.ExecutorService;
import com.hd.util.Constants;

@Path("/hd")
public class ExecutorController
{
   @POST
   @Path("/run")
   public Response generate(String body) throws Exception
   {
      String input[] = body.split(Constants.DELIMITER);
      String params[] = new String[Constants.NUMBER_OF_PARAMS];
      int i = 0;
      for (String s : input)
      {
         params[i++] = s.trim();
      }
      String output = ExecutorService.analyzeData(Arrays.asList(params));
      return Response.status(Status.OK).entity(output).build();
   }
}
