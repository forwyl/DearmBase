<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<html>	
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>2014願望清單</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>
	<script type="text/javascript">
	
		var xmlHttp;
		
		function handleAccount(thisObj, thisEvent) {
		    var json = prepareJSON();
		    var url = "CartServlet?from=welcome&timeStamp" + new Date().getTime();
		    createXMLHttpRequest();
		    xmlHttp.onreadystatechange = handleStateChange;
		    xmlHttp.open("POST", url, false);
		    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		    xmlHttp.send(json);
		    if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		    	window.location ="intoDream.jsp";
		    }
		    return false;
		    
		}
		
		function createXMLHttpRequest() {
		    if(window.XMLHttpRequest) {
		        xmlHttp = new XMLHttpRequest();
		    }
		    else if(window.ActiveXObject) {
		        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		    }
		}
	
		function prepareJSON() {
			var elements = document.getElementsByName("wishes");
			var wishlist = [];  //array declaration without given size in pure js
			
			for(var i=0; i< elements.length; i++){
				if(elements[i].checked)
					wishlist.push(elements[i].value);
			}
			
			return JSON.stringify(wishlist)
		}
			
		function handleStateChange() {
		    if(xmlHttp.readyState == 4) {
		        if(xmlHttp.status == 200) {
		            document.getElementById("response").innerHTML = xmlHttp.responseText;
		        }
		    }
		}
	
	</script>	
		
	</head>
	<body>

			<c:import url="http://localhost:8080/DreamBase/dream.xml" var="inputDoc1" />
			<c:import url="http://localhost:8080/DreamBase/dream.xsl" var="displayDoc1" />
		
			<form name="dreamForm" method="POST">
				<x:transform doc="${inputDoc1}" xslt="${displayDoc1}" ></x:transform>
				<br/>
				<div style="width:85%;margin:auto;">
					<input type="submit" onclick="return handleAccount(this, event);" class="btn btn-large btn-block btn-primary" value="Submit"/>
				</div>
			</form>
		
	</body>
</html>