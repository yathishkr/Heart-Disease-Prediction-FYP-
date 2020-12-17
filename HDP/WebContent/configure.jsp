
<%@page import="com.dp.model.EndPoint"%>
<%@page import="java.util.List"%>
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
					<a class="navbar-brand" href="adminwelcome.jsp"> Disease Predictor App
					</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div
					class="collapse navbar-collapse navbar-right navbar-main-collapse">
					<ul class="nav navbar-nav">
						<li class=""><a href="adminwelcome.jsp">Welcome</a></li>
						<li class="active"><a href="endpoint?reqType=get">Model Configuration</a></li>
						<li class=""><a href="adminentitlements.jsp">Entitlements</a></li>
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
								<h2 class="h-bold">Model Configuration</h2>
								<p>Here, you can configure the model</p>
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


						<%
							EndPoint ep = (EndPoint) session.getAttribute("endPoint");
							if (ep != null && ep.getAppname() != null)
							{
							   %>
									<h4>Configured End Point</h4>
									<hr/>
												<div class="card card-outline-secondary my-8">
													<div class="card-header"><%=ep.getType() %> (<%=ep.getHost() %>) <span style='float: right;'><a data-toggle="modal" data-target="#delete" href=''>Delete</a></span> <span style='float: right;'> &nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp; </span><span style='float: right;'><a data-toggle="modal" data-target="#edit" href='#'>Edit</a></span></div>
													<div class="card-body">
														<table class='table'>
															<tr>
																<th> Host</th>
																<td> <%=ep.getHost() %></td>
															</tr>
															<tr>
																<th> Port</th>
																<td> <%=ep.getPort() %></td>
															</tr>
															<tr>
																<th> App Name</th>
																<td> <%=ep.getAppname() %></td>
															</tr>
															<tr>
																<th> Context Root</th>
																<td> <%=ep.getContextroot() %></td>
															</tr>															
														</table>														
													</div>
												</div>		
												<br/>							
												<!-- Edit sModal -->
												<div class="modal fade" id="edit" style='margin-top:150px;' tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
												  <div class="modal-dialog" role="document">
												    <div class="modal-content">
												      <div class="modal-header">
												        <h4 class="modal-title" id="myModalLabel"><%=ep.getType() %> (<%=ep.getHost() %>) </h4>
												      </div>
														<form action='endpoint' method=post>
													      <div class="modal-body">
																<input type=hidden name='reqType' value='update' />
																<input type=hidden name='id' value='<%=ep.getId()%>' />
																<input type=hidden name='type' value='<%=Constants.ENDPOINT_TYPE_HEART_DISEASE_PREDICTOR%>' />
																<label>Host name</label>
																<input type=text value='<%=ep.getHost() %>' class='form-control' name='host' required="required" placeholder="Host Name"/>
																<br/>
																<label>Port Number</label>
																<input type=text value='<%=ep.getPort() %>' class='form-control' name='port' required="required" placeholder="Port Number"/>
																<br/>
																<label>App Name</label>
																<input type=text value='<%=ep.getAppname() %>' class='form-control' name='appname' required="required" placeholder="Application Name"/>
																<br/>
																<label>Context Root</label>
																<input type=text value='<%=ep.getContextroot() %>' class='form-control' name='contextroot' required="required" placeholder="Context Root"/>
																<br/>
													      </div>
													      <div class="modal-footer">
													        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
													        <button type="submit" class="btn btn-primary">Save changes</button>
													      </div>
														</form>
												    </div>
												  </div>
												</div>
												<!-- Delete Modal -->
												<div class="modal fade" id="delete" tabindex="-1" style='margin-top:150px;' role="dialog" aria-labelledby="myModalLabel">
												  <div class="modal-dialog" role="document">
												    <div class="modal-content">
												      <div class="modal-header">
												        <h4 class="modal-title" id="myModalLabel"><%=ep.getType() %> (<%=ep.getHost() %>) </h4>
												      </div>
														<form action='endpoint' method=post>
													      <div class="modal-body">
																<input type=hidden name='reqType' value='delete' />
																<input type=hidden name='id' value='<%=ep.getId()%>' /> 
																<div>Are you sure to Delete this End Point?</div>
													      </div>
													      <div class="modal-footer">
													        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
													        <button type="submit" class="btn btn-primary">Delete</button>
													      </div>
														</form>
												    </div>
												  </div>
												</div>
																																					
									<br/>
									<br/>	   
									<hr/>
							   <%
							}
							else
							{
						%>
						
						<br/>
						
						<form action='endpoint' method=post>
							<input type=hidden name='reqType' value='add' />
							<input type=hidden name='type' value='<%=Constants.ENDPOINT_TYPE_HEART_DISEASE_PREDICTOR%>' />
							<label>Host name</label>
							<input type=text class='form-control' name='host' required="required" placeholder="Host Name"/>
							<br/>
							<label>Port Number</label>
							<input type=text class='form-control' name='port' required="required" placeholder="Port Number"/>
							<br/>
							<label>App Name</label>
							<input type=text class='form-control' name='appname' required="required" placeholder="Application Name"/>
							<br/>
							<label>Context Root</label>
							<input type=text class='form-control' name='contextroot' required="required" placeholder="Context Root"/>
							<br/>
							<input type=submit value='Add Endpoint' class='btn btn-primary' />
						</form>
						
						<% } %>
					

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