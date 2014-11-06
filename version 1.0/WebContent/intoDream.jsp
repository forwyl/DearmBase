<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>		
		
<!DOCTYPE html>

<html>	
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
	    <script src="http://code.jquery.com/jquery.js"></script>
	    <script src="js/bootstrap.min.js"></script>
	    <script type="text/javascript">
	    
	    var xmlHttp;
	    
	    function generatePDF(){
	    	
	    }
	    
	    function generateXML(){
		    var url = "OutputServlet?timeStamp" + new Date().getTime() +"&";
		    createXMLHttpRequest();
		    xmlHttp.onreadystatechange = handleStateChange;
		    xmlHttp.open("POST", url, false);
		    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		    xmlHttp.send("from=askXML");
		    
	    }
	    	    
		function createXMLHttpRequest() {
		    if(window.XMLHttpRequest) {
		        xmlHttp = new XMLHttpRequest();
		    }
		    else if(window.ActiveXObject) {
		        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		    }
		}
		
		function handleStateChange() {
		    if(xmlHttp.readyState == 4) {
		        if(xmlHttp.status == 200) {
		            document.getElementById("response").innerHTML = xmlHttp.responseText;
		        }
		    }
		}
		
	    </script>
	
		<title>你的2014願望清單</title>
	</head>
<body>
	
		<form action="OutputServlet" method="POST">		
				<c:import url="http://localhost:8080/DreamBase/${sessionScope.xmlName}" var="inputDoc"></c:import>
				<c:import url="http://localhost:8080/DreamBase/intoDream.xsl" var="displayDoc"></c:import>
			
				<x:transform doc="${inputDoc}" xslt="${displayDoc}" ></x:transform>
				<div style="width:85%;margin:auto;">				
					<button class="btn btn-large btn-block btn-primary" value="下載XML" onclick="generateXML()">Download XML</button>
				</div>
		</form>
				
</body>
</html>