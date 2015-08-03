<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://i3.sdlcdn.com/img/icons/favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="css/login.css" rel="stylesheet" type="text/css"/>
<title>Seller Log In</title>
</head>
<body style="background-color: rgb(245, 245, 245);">

<div class = "header header-topbar">
	<div id="caption">
		<div class="logo">
			<img src="http://i3.sdlcdn.com/img/snapdeal/sprite/snapdeal_logo_v9.png">
		</div>
		<div class="header-right-content">
			<span class="divider">&nbsp;&nbsp;Create Brand</span>
		</div>
	</div>
</div>
<div class="alert alert-warning <s:if test="error == null">hidden</s:if>" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Error is : <s:property value="error"/></strong>
</div>
<div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-12 col-sm-offset-0">                    
            <div class="panel panel-info" >
                    <div class="whiteHeader panel-heading">
                        <div class="panel-title"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span>&nbsp&nbspSign In</div>
                     
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >                            
                        <form id="loginform" class="form-horizontal" role="form" method="post" action="login">
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="email" value="" placeholder="email Id" title="email" required aria-required=”true”>                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password" title="password" required aria-required=”true”>
                                    </div>
                     
                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->
									
                                    <div class="col-sm-12 controls">
                                      <button id="btn-login" type="submit" class="myButton btn btn-success">Login  </button>

                                    </div>
                                </div>
                            </form>     
							

                        </div>                     
                    </div>  
        </div> 
</form>
</body>
</html>