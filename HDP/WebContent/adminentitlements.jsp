
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dp.model.Entitlement"%>
<%@page import="java.util.List"%>
<%@page import="com.dp.daoimpl.EntitlementDAOImpl"%>
<%@page import="com.dp.dao.EntitlementDAO"%>
<%
   String email = (String) session.getAttribute("adminemail");
   if (email != null)
   {
%>

<!DOCTYPE html>
<%@page import="com.dp.util.Constants"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Disease Predictor App</title>

<!-- css -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="plugins/cubeportfolio/css/cubeportfolio.min.css">
<link href="css/nivo-lightbox.css" rel="stylesheet" />
<link href="css/nivo-lightbox-theme/default/default.css"
	rel="stylesheet" type="text/css" />
<link href="css/owl.carousel.css" rel="stylesheet" media="screen" />
<link href="css/owl.theme.css" rel="stylesheet" media="screen" />
<link href="css/animate.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet">

<!-- boxed bg -->
<link id="bodybg" href="bodybg/bg1.css" rel="stylesheet" type="text/css" />
<!-- template skin -->
<link id="t-colors" href="color/default.css" rel="stylesheet">

<!-- =======================================================
    Theme Name: Medicio
    Theme URL: https://bootstrapmade.com/medicio-free-bootstrap-theme/
    Author: BootstrapMade
    Author URL: https://bootstrapmade.com
  ======================================================= -->
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">


	<div id="wrapper">

		<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
			<div class="top-area" style='background-color: navy;'>
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-md-6">
							<p class="bold text-left"></p>
						</div>
						<div class="col-sm-6 col-md-6">
							<p class="bold text-right"><%=Constants.COMPANY_NAME%>
								(<%=Constants.COMPANY_SHORT_NAME%>)
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="container navigation">

				<div class="navbar-header page-scroll">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-main-collapse">
						<i class="fa fa-bars"></i>
					</button>
					<a class="navbar-brand" href="adminwelcome.jsp"> Disease Predictor
						App </a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div
					class="collapse navbar-collapse navbar-right navbar-main-collapse">
					<ul class="nav navbar-nav">
						<li class=""><a href="adminwelcome.jsp">Welcome</a></li>
						<li class=""><a href="endpoint?reqType=get">Model Configuration</a></li>						
						<li class="active"><a href="adminentitlements.jsp">Entitlements</a></li>
						<li class=""><a href="adminpredictions.jsp">Predictions</a></li>
						<li class="dropdown"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"><span
								class="badge custom-badge red pull-right">ADMIN</span><%=email%> <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="account?request_type=adminlogout">Logout</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container -->
		</nav>

		<section id="partner" class="home-section paddingbot-60"
			style='min-height: 700px;'>
			<div class="container marginbot-50">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="wow lightSpeedIn" data-wow-delay="0.1s">
							<div class="section-heading text-center">
								<h2 class="h-bold">Entitlement</h2>
								<p>Here, you can take action on your clients entitlement requests</p>
							</div>
						</div>
					</div>
				</div>
				<div class='row'>
					<div class="col-lg-8 col-lg-offset-2">


						<%
						   String msg = request.getParameter("msg");
						%>
						<%
						   if (msg != null)
						      {
						%>
						<div class="alert alert-success alert-dismissable">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Message!</strong>
							<%=msg%>.
						</div>
						<%
						   }
						%>

					
					<br/><br/>
					
					<%
						EntitlementDAO eDao = new EntitlementDAOImpl();
						List<Entitlement> pending = eDao.getAllOpen();
						List<Entitlement> approved = eDao.getAllApproved();
						List<Entitlement> rejected = eDao.getAllRejected();
			    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy | kk:mm");
						
					%>
					
					<div>

					  <ul class="nav nav-tabs" role="tablist">
					    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Pending Requests</a></li>
					    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Approved Requests</a></li>
					    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Rejected Requests</a></li>
					  </ul>
					
					  <!-- Tab panes -->
					  <div class="tab-content">
					    <div role="tabpanel" class="tab-pane active" id="home">
					    	
					    	<%
					    		if (pending != null && pending.size() > 0)
					    		{
					    		   %>
					    		   
							    	<table class='table'>
							    		<tr>
							    			<th> Email </th>
							    			<th> Request Time </th>
							    			<th> Action</th>
							    		</tr>
							    		<%
							    			for (Entitlement ent: pending)
							    			{
							    			   %>
							    			   		<tr>
							    			   			<td> <%=ent.getEmail() %></td>
							    			   			<td> <%=sdf.format(ent.getEntrytime()) %></td>
							    			   			<td> 
							    			   				<a style='color: lightgreen; font-weight: bold;' href='entitlement?req_type=approve&email=<%=ent.getEmail()%>' >Approve</a>
							    			   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    			   				<a style='color: red; font-weight: bold;' href='entitlement?req_type=reject&email=<%=ent.getEmail()%>' >Reject</a>
						    			   			
							    			   			</td>
							    			   		</tr>
							    			   <%
							    			}
							    		%>
							    	</table>
					    		   
					    		   <%
					    		}
					    		else
					    		{
					    		   %>
					    		   		<hr/>
					    		   		NONE
					    		   		<hr/>
					    		   <%
					    		}
					    	%>
					    	
					    
					    </div>
					    <div role="tabpanel" class="tab-pane" id="profile">
					    	<%
					    		if (approved != null && approved.size() > 0)
					    		{
					    		   %>
					    		   
							    	<table class='table'>
							    		<tr>
							    			<th> Email </th>
							    			<th> Approved By </th>
							    			<th> Action</th>
							    		</tr>
							    		<%
							    			for (Entitlement ent: approved)
							    			{
							    			   %>
							    			   		<tr>
							    			   			<td> <%=ent.getEmail() %></td>
							    			   			<td> <%=ent.getApprover()%></td>
							    			   			<td> 
							    			   				<a style='color: red; font-weight: bold;' href='entitlement?req_type=reject&email=<%=ent.getEmail()%>' >Reject</a>
						    			   			
							    			   			</td>
							    			   			
							    			   		</tr>
							    			   <%
							    			}
							    		%>
							    	</table>
					    		   
					    		   <%
					    		}
					    		else
					    		{
					    		   %>
					    		   		<hr/>
					    		   		NONE
					    		   		<hr/>
					    		   <%
					    		}
					    	%>
					    	
					    
					    </div>
					    <div role="tabpanel" class="tab-pane" id="messages">
					    	<%
					    		if (approved != null && rejected.size() > 0)
					    		{
					    		   %>
					    		   
							    	<table class='table'>
							    		<tr>
							    			<th> Email </th>
							    			<th> Rejected By </th>
							    			<th> Action</th>
							    		</tr>
							    		<%
							    			for (Entitlement ent: rejected)
							    			{
							    			   %>
							    			   		<tr>
							    			   			<td> <%=ent.getEmail() %></td>
							    			   			<td> <%=ent.getApprover()%></td>
							    			   			<td> 
							    			   				<a style='color: red; font-weight: bold;' href='entitlement?req_type=approve&email=<%=ent.getEmail()%>' >Approve</a>
						    			   			
							    			   			</td>
							    			   			
							    			   		</tr>
							    			   <%
							    			}
							    		%>
							    	</table>
					    		   
					    		   <%
					    		}
					    		else
					    		{
					    		   %>
					    		   		<hr/>
					    		   		NONE
					    		   		<hr/>
					    		   <%
					    		}
					    	%>
					    
					    </div>
					    
					  </div>
					
					</div>
					
					


					</div>
				</div>
			</div>

		</section>

		<footer>


			<div class="sub-footer">
				<div class="container">
					<div class="row">
						<div class="col-sm-6 col-md-6 col-lg-6">
							<div class="wow fadeInLeft" data-wow-delay="0.1s">
								<div class="text-left">
									<p>&copy;Copyright - Disease Predictor App. All rights
										reserved.</p>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-6 col-lg-6">
							<div class="wow fadeInRight" data-wow-delay="0.1s">
								<div class="text-right">
									<div class="credits"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>

	</div>
	<a href="#" class="scrollup"><i class="fa fa-angle-up active"></i></a>

	<!-- Core JavaScript Files -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/jquery.scrollTo.js"></script>
	<script src="js/jquery.appear.js"></script>
	<script src="js/stellar.js"></script>
	<script src="plugins/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/nivo-lightbox.min.js"></script>
	<script src="js/custom.js"></script>

</body>

</html>
<%
   }
   else
   {
      response.sendRedirect("adminlogin.jsp?msg=Login Again");

   }
%>