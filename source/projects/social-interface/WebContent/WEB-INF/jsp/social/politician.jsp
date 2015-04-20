<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="pt-BR" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="<c:url value="/bootstrap/images/favicon.ico" />" />
		<title>Social Network Analysis</title>
		
		<link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css" />" />
		<link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap-theme.min.css" />" />
		<link rel="stylesheet" href="<c:url value="/bootstrap/css/dashboard.css" />" />
		<link rel="stylesheet" href="<c:url value="/bootstrap/css/morris.css" />" />

		<script type="text/javascript" src="<c:url value="/bootstrap/js/jquery-1.11.1.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bootstrap/js/holder.js" />"></script>

		<script type="text/javascript" src="<c:url value="/bootstrap/js/raphael-min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/bootstrap/js/morris.min.js" />"></script>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>	
	          <a class="navbar-brand" href="#">Social Network Analysis</a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="https://github.com/guilhermedelima/tcc">Projeto Source Network Analysis</a></li>
	            <li><a href="https://github.com/guilhermedelima/tcc/tree/master/source/projects/social-interface">Código Fonte</a></li>
	          </ul>
	        </div>
	      </div>
	    </nav>
	    
		<div class="container-fluid">
	      <div class="row">
	        <div class="col-sm-3 col-md-2 sidebar">
	          <ul class="nav nav-sidebar">
		          <c:choose>
		          	<c:when test="${empty politician}">
		          		<li class="active"><a href="<c:url value="/"/>" >Página Inicial <span class="sr-only">(current)</span></a></li>
		          	</c:when>
		          	<c:otherwise>
		          		<li><a href="<c:url value="/"/>" >Página Inicial</a></li>
		          	 </c:otherwise>
		          </c:choose>
		          
		          <c:forEach items="${enumList}" var="politicianEnum" >
			        <c:choose>
				      <c:when test="${ politician == politicianEnum }">
				        <li class="active"><a href="<c:url value="/politician/${politicianEnum.id}"/>" >${politicianEnum.name} <span class="sr-only">(current)</span></a></li>
				      </c:when>
				      <c:otherwise>
				        <li><a href="<c:url value="/politician/${politicianEnum.id}"/>" >${politicianEnum.name}</a></li>
				      </c:otherwise>
			        </c:choose>
		          </c:forEach>
	          </ul>
	        </div>
	        
	        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	          <h1 class="page-header">${politician.name}</h1>
	
	          <div class="row placeholders">
	            <div class="col-xs-6 col-sm-6 placeholder">
	              <img src="<c:url value="/bootstrap/images/${politician.id}.jpg"/>" class="img-responsive" alt="Generic placeholder thumbnail">
	              <h4>${politician.name}</h4>
	              <span class="text-muted">${politician.description}</span>
	            </div>
	            <div class="col-xs-4 col-sm-4">
	              <div id="donut-chart"></div>
	            </div>
	          </div>
	
			  <c:forEach items="${tableList}" var="table">
  	          	<h2 class="sub-header">${table.year}</h2>
				<div class="table-responsive">
	              <table class="table table-bordered table-striped">
		              <thead>
		                <tr>
		                  <th></th>
		                  <c:forEach items="${monthList}" var="month">
		                  	<th>${month}</th>
		                  </c:forEach>
		                  <th>Total</th>
		                </tr>
		              </thead>
		              <tbody>
			              <c:forEach items="${table.rows}" var="row">
				          	<tr>
			                  <td>${row.classification.name}</td>
			                  <c:forEach items="${row.monthCountList}" var="val">
			                  	<td>${val}</td>
			                  </c:forEach>
			                  <td>${row.sum}</td>
		                	</tr>
			              </c:forEach>
		              </tbody>
	              </table>
	          	</div>
<%-- 	            <div id="Area-${table.year}"></div> --%>
<!-- 	            <script type="text/javascript"> -->
				
<!--  	            </script> -->
			  </c:forEach>
	        </div>
	      </div>
	    </div>
	    
	    <script type="text/javascript">
			new Morris.Donut({
				element : 'donut-chart',
				colors : ['#008000', '#FF0000'],
				data : [ {
					label : "Positivo",
					value : "${positiveSum}"
				}, {
					label : "Negativo",
					value : "${negativeSum}"
				} ]
			});
		</script>
	</body>
</html>