<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
<script type="text/javascript" src="js/sizeChart.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="css/chart.css" rel="stylesheet" type="text/css"/>
<title>Size Chart Admin</title>
</head>
<body>
<div class = "header">
	<div id="caption">
		<div class="logo">
			<img src="http://i3.sdlcdn.com/img/snapdeal/sprite/snapdeal_logo_v9.png">
		</div>
	</div>
</div>
<div class="alert alert-warning <s:if test="error == null">hidden</s:if>" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Error is : <s:property value="error"/></strong>
</div>

<div class="alert alert-success <s:if test="ticket == null">hidden</s:if>" role="alert" id="result_box">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4>Your request has been registered and the <strong>Ticket</strong> generated is : <strong><s:property value="ticket"/></strong></h4>
</div>
<div>
	<input type="text" id="error" name="error">
</div>
<div class="mid_pane">
	<div class="container">
		<div class="panel panel-success">
	      <div class="myPanelHead panel-heading">
	        <h3 class="panel-title" id="panel-title">Add Size Chart<a class="anchorjs-link" href="#panel-title"><span class="anchorjs-icon"></span></a></h3>
	      </div>
	      <div class="panel-body">
	        <!-- <form id="chartform" action="SizeChart" onsubmit="return formcheck()" method="post" enctype="multipart/form-data"> -->
	        <form id="chartform" action="Chart" method="post"> 
						
			<div class="form-group col-xs-6 item-required">
				<label class = "control-label" for="sellerCode">Seller Code</label>
				<span id="required">*</span>
				<input class="form-control" id = "sellerCode" type="text" name="sellerCode">
				<span class="error" id = "sellerCodeerror"></span>
			</div>
			
			<div class="form-group col-xs-6 item-required">
				<label class = "control-label" for="brandName">Brand Name</label>
				<span id="required">*</span>
				<input class="form-control" id = "brandName" type="text" name="brandName">
				<span class="error" id = "brandNameerror"></span>
			</div>
			
			<div class="form-group col-xs-6 margin_top_20 item-required">
				<label class = "control-label" for="cat_name">Category</label>
				<span id="required">*</span>
				<select class="form-control" id="category" name="cat_name"></select>
				<span class="error" id = "cat_nameerror"></span> 
			</div>
			
			<div id="size_chart_box" class="col-xs-12">
				<span id = "size_table"></span>
				<span id= "size_tableerror" class="error"></span>	
				<div class="form-group col-xs-3" id="submit_button">
					<input class="form-control btn btn-success" id="submitbrand" type = "button" onclick="return formcheck()" name = "button1" value = "button1">
				</div>			
			</div>
			
		</form>
	      </div>
	    </div>
	</div>
</div>

</body>
</html>