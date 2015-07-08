<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="pt-BR" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- 		<link rel="icon" href="<c:url value="/bootstrap/images/favicon.ico" />" /> --%>
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
		
		<link rel="stylesheet" href="<c:url value="/font-awesome/css/font-awesome.min.css" />" />
		<link rel="stylesheet" href="<c:url value="/font-awesome/css/bootstrap-social.css" />" />
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
	          <a class="navbar-brand" href="<c:url value="/" />">Social Network Analysis</a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="https://github.com/guilhermedelima/tcc">Documentação</a></li>
	            <li><a href="https://github.com/guilhermedelima/tcc/tree/master/implementacao/projects/social-interface">Código Fonte</a></li>
	          </ul>
	        </div>
	      </div>
	    </nav>
	    
	    
		<div class="container-fluid">
	      <div class="row">
	        <div class="col-sm-3 col-md-2 sidebar">
	          <ul class="nav nav-sidebar">
		          <c:choose>
		          	<c:when test="${empty leftActive}">
		          		<li class="active"><a href="<c:url value="/"/>" >Página Inicial <span class="sr-only">(current)</span></a></li>
		          	</c:when>
		          	<c:otherwise>
		          		<li><a href="<c:url value="/"/>" >Página Inicial</a></li>
		          	 </c:otherwise>
		          </c:choose>
		          
		          <c:forEach items="${enumList}" var="politician" >
			        <c:choose>
				      <c:when test="${ leftActive == politician.id }">
				        <li class="active"><a href="<c:url value="/politician/${politician.id}"/>" >${politician.name} <span class="sr-only">(current)</span></a></li>
				      </c:when>
				      <c:otherwise>
				        <li><a href="<c:url value="/politician/${politician.id}"/>" >${politician.name}</a></li>
				      </c:otherwise>
			        </c:choose>
		          </c:forEach>
	          </ul>
	        </div>
	        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	          <h1 class="page-header">Análise de Sentimentos</h1>
			  
			  <div class="row placeholders">
			  	<div class="col-sm-12 col-md-4 col-xs-4">
			  		<div class="panel panel-primary">
						<div class="panel-heading">Redes Sociais Avaliadas</div>
				  		<ul class="list-group">
				  			<c:forEach items="${sourceTable.rows}" var="row">
				  				<li class="list-group-item" style="text-align: left;">
				  					<a class="btn btn-social btn-${row.source.id}">
				  						<i class="fa fa-${row.source.id}"></i>
				  					</a>
				  					${row.source.name}: ${row.allValues} publicações
				  				</li>
				  			</c:forEach>				  			
			  			</ul>
			  		</div>
			  	</div>
			  </div>

	        </div>
	      </div>
	    </div>
	</body>
</html>