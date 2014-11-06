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
				<h1>2014 BUCKET LIST<small>Download Your Bucket List?</small></h1>
			</div>
		    <table class="table table-striped">
		    <tr class="info">
		      <td align="left">Type</td>
		      <td align="left">Title</td>
		       <td align="left">Price</td>
		    </tr>
		    <xsl:for-each select="wishes/dream">
				<tr>
				  <td><xsl:value-of select="type"/></td>
				  <td><xsl:value-of select="title"/></td>
				  <td><xsl:value-of select="price"/></td>
				</tr>
		    </xsl:for-each>
		    </table>
		  </body>
		  </html>
	</xsl:template>
</xsl:stylesheet>