<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes"></xsl:output>

<xsl:template match="/">
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
  	
  	<head>
	  	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"></link>	
	    <script src="http://code.jquery.com/jquery.js"></script>
	    <script src="js/bootstrap.min.js"></script>  	
  	
  	</head>

    
	<body>

	<div class="page-header">
		<h1>2014 BUCKET LIST<small>What's on Your Bucket List?</small></h1>
	</div>
	
    <div class="container" style="background-color:#000000">
    	<div class="span12">
		    <xsl:for-each select="wishes/dream">
			   <div class="span3" >	  
				  	<input type="checkbox" id="wishes" name="wishes">
				  		<xsl:attribute name="value"><xsl:value-of select="number"/></xsl:attribute>
				  	</input>
					<br/>
					<img style="height:250px;width:400px" class="img-circle">
				  		<xsl:attribute name="src"><xsl:value-of select="imgUrl" disable-output-escaping="yes"/></xsl:attribute>
				 	 </img>
					 <p style="font-family:verdana;color:white;font-size:15px;">	
						  <xsl:value-of select="title"/>
						  <br/>
						  
						  <i class="icon-shopping-cart" style="color:white"></i>$<xsl:value-of select="price"/>
					</p>
				</div>
		    </xsl:for-each>
	    </div>
    </div>
    
  </body>
  
</html>
</xsl:template>

</xsl:stylesheet>