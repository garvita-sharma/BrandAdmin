<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="css/success.css" rel="stylesheet" type="text/css"/>
<title>Success</title>
</head>
<body>
<div class="container">
<div class="box">
<div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">Ticket Id</h3>
  </div>
  <div class="panel-body">
    <h3>Ticket Generated For the Request : <s:property value = "%{ticket}" /></h3>
  </div>
</div>
</div>
</div>
</body>
</html>