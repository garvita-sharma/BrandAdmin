<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<script src="jquery-1.9.1.min.js"></script>-->
<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/brand.js"></script>
<script type="text/javascript" src="js/dynamic_table.js"></script>
<link href="<s:url value="/css/brand.css"/>" rel="stylesheet" type="text/css"/>
<title>Add Brand Here</title>
</head>
<body>
<div class = "header">
	<div id="caption">
		SNAPDEAL.COM
	</div>
</div>
<div class="error message">
	<span id="message"></span>
</div>
<div class="mid_pane">
	<div class="left_bar">
		<table id="add_brand">
			<tr>
				<td id="add_brand_here">ADD BRAND</td>
			</tr>
		</table>
	</div>
	<div class="right_bar">
		<form id="addbrandform" action="addBrand" method="post" enctype="multipart/form-data">
			<div id = "submit_bar">
				<div id = "new_brand">
					New Brand
				</div>
				<div id = "submit_button">
					<input id = "submitbrand" type = "submit" name = "submit" value = "Submit">
				</div>
			</div>
			<div id="main_content">
				<div id="main_content_row">
					<div id="main_content_row_left">
						<div id="main_content_row_left_name">
							Seller Code
							<span id="required">
								*
							</span>
						</div>
					</div>
					<div id="main_content_row_right">
						<input id = "sellerCode" type="text" readonly="readonly" name="sellerCode" ><!-- value = ${param.sellercode} >--> 
					</div>
				</div>
				<div id="main_content_row">
					<div id="main_content_row_left">
						<div id="main_content_row_left_name">
							Brand Name
							<span id="required">
								*
							</span>
						</div>
					</div>
					<div id="main_content_row_right">
						<input id = "brandName" type="text" name="brandName" required> 
					</div>
				</div>
				<div id="main_content_row">
					<div id="main_content_row_left">
						<div id="main_content_row_left_name">
							Description
						</div>
					</div>
					<div id="main_content_row_right">
						<textarea id="description" rows="15" cols="20" name="description">
						</textarea> 
					</div>
				</div>
				 
				<div id="main_content_row">
					<div id="main_content_row_left">
						<div id="main_content_row_left_name">
							Category
						</div>
					</div>
					<div id="main_content_row_right">
						<select id="category" name="cat_name">
						</select>						 
					</div>
				</div>
				<!-- 
				<div id="main_content_row">
					<div id="main_content_row_left">
						<div id="main_content_row_left_name">
							Brand Type
						</div>
					</div>
					<div id="main_content_row_right">
						 
						<input id="brandTypeCheckbox" type="checkbox" name="international_national" value="international_national">International_National<br/>
						<input id="brandTypeCheckbox" type="checkbox" name="indian" value="indian">Indian<br/>
						<input id="brandTypeCheckbox" type="checkbox" name="regional" value="regional">Regional<br/>
						<input id="brandTypeCheckbox" type="checkbox" name="local" value="local">Local<br/>
						<input id="brandTypeCheckbox" type="checkbox" name="state" value="state">State<br/>
						<input id="brandTypeCheckbox" type="checkbox" name="city" value="city">City<br/>
					</div>
				</div>
				 -->
				<div id="main_content_row">
					<div id="main_content_row_left">
						<div id="main_content_row_left_name">
								<label for="brandImage">BRAND LOGO</label>
	      						<span id ="required">
									*
								</span>
	      						
	      						<input type="file" name="brandImage" />
	      						<!--<input type="submit" value="Upload"/>-->
						</div>
					</div>
			</div>
			</div>
			
			<div id="size_chart_box">
				<!-- 
				<input type="hidden" value="0" id="col_value" name="col_value">
				<input type="hidden" value="0" id="row_value" name="row_value">
				<table id="tbl">
					<thead id="thead">
						<tr id="head_row">
							<td>
								<input type="button" value="add_row" id="row">
							</td>
							<td id="head_label"><input type="text" value="Label"></td>
							<td id="D_copy"><input type="text" value="Col_Label"></td>
							<td id="head_col"><input type="text" value="Col_Label"></td>
							<td>
								<input type="button" value="add_column" id="column">
							</td>
						</tr>
					</thead>
					<tbody id="tbody">
						<tr id="tbl_row">
							<td></td>
							<td id="D_copy"><input type="text" value="Row"></td>
							<td id="row_col"><input type="text" value="Row"></td>
							<td id="row_col"><input type="text" value="Row"></td>
							<td></td>
						</tr>
					</tbody>	
				</table>
				 -->
				<span id = "size_table"></span>
				
			</div>
		</form>
	</div>
</div>
<div class="footer">
	<div id="foot_note">
		snapdeal.com
	</div>
</div>
</body>
</html>