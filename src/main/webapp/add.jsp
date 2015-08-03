<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="http://i3.sdlcdn.com/img/icons/favicon.ico" rel="shortcut icon">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>


<%-- <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> --%>

<script type="text/javascript" src="js/brand.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-filestyle-min.js"></script>
<script type="text/javascript">
$(":file").filestyle();
</script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="css/brand.css" rel="stylesheet" type="text/css"/>
<title>Brand Admin</title>
</head>
<body>
<div class = "header header-topbar">
	<div id="caption">
		<div class="logo">
			<img src="http://i3.sdlcdn.com/img/snapdeal/sprite/snapdeal_logo_v9.png">
			<div class="logoutbutton" onclick="window.location.href='login.jsp'">
				<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
				<input id="logout" type = "button"  name = "logout" value = "LogOut">
			</div>
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
	        <h3 class="panel-title" id="panel-title">Add Brand<a class="anchorjs-link" href="#panel-title"><span class="anchorjs-icon"></span></a></h3>
	      </div>
	      <div class="panel-body">
	        <form id="addbrandform" action="addBrand"  method="post" enctype="multipart/form-data"> 
			<!-- <form id="addbrandform" action="addBrand" onsubmit="return formcheck()" method="post" enctype="multipart/form-data"> -->
			<!-- <form id="addbrandform" action="addBrand" method="post" enctype="multipart/form-data">-->
			<!-- <input type="text" id="error" name="error"> -->
			
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
			
			<div class="form-group col-xs-6 item-required">
				<label class = "control-label" for="description">Description</label>
				<span id="required">*</span>
				<textarea class="form-control" id="description" rows="5" cols="25" name="description"></textarea>
				<span class="error" id = "descriptionerror"></span> 
			</div>
        	 
        	<div class="form-group col-xs-6 item-required">
				<label class = "control-label ui-widget" for="cat_name">Choose Category</label>
				<span id="required">*</span>
				<input class="form-control" id = "category" type="text" name="cat_name">
				<span id="searchResult"></span>
				<span class="error" id = "cat_nameerror"></span>
			</div>
			
			<div class="form-group col-xs-6 item-required">
				<label class = "control-label" for="brandImage">Brand Logo(Should be less than 1 Mb)</label>
				<span id="required">*</span>
				<!-- <input class="form-control filestyle" type="file" id = "brandImage" data-iconName="glyphicon-inbox" name="brandImage"/> -->
				<input class="form-control" type="file" id = "brandImage" data-iconName="glyphicon-inbox" name="brandImage"/>
				<span class="error" id = "brandImageerror"></span>
			</div>
			
			<div id="size_chart_box" class="col-xs-12" style="display:none">
				<span id = "size_table"></span>
				<span id= "size_tableerror" class="error"></span>	
							
			</div>
			<div class=" form-group col-xs-3" id="submit_button">
					<input class="myButton form-control btn btn-success" id="submitbrand" type = "button" onclick="return formcheck()" value = "Create Brand"></button>
				</div>
			
		</form>
	      </div>
	    </div>
	</div>
</div>

</body>
</html>