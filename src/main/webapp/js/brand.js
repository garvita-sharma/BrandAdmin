var row_flag , col_flag;
var row = 0;
var col = 0;
var check = 0;
var is_image = 1;
var size_in_range = 1;

$(document).ready(function()
{
		var sellerCode = getParameterByName("sellercode");
		$("#sellerCode").val(sellerCode);
		//validate(sellerCode);
		var cat = getParameterByName("category");
		 $("#brandImage").change(function () {
		        var fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
		        if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
		            alert("Only '.jpeg','.jpg', '.png', '.gif', '.bmp' formats are allowed.");
		            is_image = 0;
		        }
		        else
		        {
		        	is_image = 1;
		        	var iSize = 0;
					if($.browser.msie)
					{
						var objFSO = new ActiveXObject("Scripting.FileSystemObject");
				        var sPath = $("#brandImage")[0].value;
				        var objFile = objFSO.getFile(sPath);
				        var iSize = objFile.size;
				        iSize = iSize/ 1024;
				    }
					else
						iSize = ($("#brandImage")[0].files[0].size / 1024);
					if (iSize / 1024 > 1)
				    {
						size_in_range = 0;
				    }
					else
					{
						size_in_range = 1;
					}

		        }
		    });
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function populateList()
{
	$.ajax({
        type: "POST",
        url: "category",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(cat_json) 
        	{
	        	var b = $.parseJSON(cat_json['cat_json'])
	            $("#category").get(0).options.length = 0;
	            $("#category").get(0).options[0] = new Option("Select Category", "-1");
	            $.each(b, function(index, item) {
	                $("#category").get(0).options[$("#category").get(0).options.length] = new Option(item.name, item.name);
	            });
        	},
        error: function() {
            //alert("Failed to load categories");
        	console.log("Failed to load categories");
        },
        async : false
    });
}

function sizechart(value)
{
	//console.log("inside size chart");
	//console.log(value)
	$.ajax({
        url: "SizeChart",
        type: "POST",
        data: {
        		cat_name : value
        		},
        dataType: "json",
        success: function(Json) {
        	//var info = $.parseJSON(SizeChartJson['sizeChartJson']);
        	var info = $.parseJSON(Json['sizeChartJson']);
        	if (info["row_json"] != undefined && info["col_json"] != undefined )
        	{
        		row_flag = 1;
        		col_flag = 1;
        	}
        	if (info["row_json"] == undefined && info["col_json"] != undefined )
        	{
        		row_flag = 0;
        		col_flag = 1;
        	}
        	if (info["row_json"] != undefined && info["col_json"] == undefined )
        	{
        		row_flag = 1;
        		col_flag = 0;
        	}
        	createtable(row_flag,col_flag,info);  
        },
        error: function() {
            //alert("Failed to get size chart info");
        	console.log("Failed to get size chart info");
        },
        async : false
    });
}

function createtable(row_flag,col_flag,info)
{
	var html;
	//alert("inside create table");
	if (row_flag == 1 && col_flag == 1)
	{
		var row_header = info["row_json"].split(",");
		var col_header = info["col_json"].split(",");
		//var val = 12/col_header.length; 
		html = "<table class = 'table table-bordered table-condensed' id='info' name=\"values\"><tr id=\"row"+row+"\">";
		for (var i = 0; i < col_header.length; i++)
		{
			html += "<td id=\"data"+row+col+"\">"+col_header[i]+"</td>";
			col += 1;
		}
		html += "</tr><tbody>";
		
		for (var j = 0; j< row_header.length; j++)
		{
			row += 1;
			col = 0;
			html += "<tr id=\"row"+row+"\"><td id=\"data"+row+col+"\">"+row_header[j]+"</td>";
			col += 1;
			for (var k = 0; k < col_header.length-1; k++)
			{
				html += "<td><input type=\"text\"id=\"data"+row+col+"\"></td>";
				col += 1;
			}
			html += "</tr>";
		}
		html += "</tbody></table>";
		document.getElementById('size_chart_box').style.display = 'block';
		$("#size_table").html(html) ;
	}
	else if (row_flag == 1 && col_flag == 0)
	{
		var row_header = info["row_json"].split(",");
		html = "<table class = 'table table-bordered table-condensed' id='info' name=\"values\><thead id=\"row"+row+"\">";
		
		for (var i = 0; i < 3; i++)
		{
			html += "<td><input type=\"text\" readonly=\"readonly\"></td>";
		}
		html += "</thead>";
		
		for (var j = 0; j< row_header.length; j++)
		{
			html += "<tbody><tr><td>"+row_header[j]+"</td>";
			for (var k = 0; k < 3; k++)
			{
				html += "<td><input type=\"text\"></td>";
			}
			html += "</tr>";
		}
		html += "</table>";
		document.getElementById('size_chart_box').style.display = 'block';
		$("#size_table").html(html) ;
	}
	else
	{
		if (row_flag == 0 && col_flag == 1)
		{
			//col = 1;
		var col_header = info["col_json"].split(",");
		html = "<table class = 'table table-bordered table-condensed' id='info' name=\"values\"><thead id=\"row"+row+"\">";
		html += "<td><input type='button' id='decrement' onclick='removeRow()' value='DELETE ROW'></td>";
		for (var i = 1; i <= col_header.length; i++)
		{
			html += "<td id=\"data"+row+col+"\">"+col_header[i-1]+"</td>";
			col += 1;
		}
		
		html += "<td><input type='button' id='increment' onclick='addRow()' value='ADD ROW'></td></thead><tbody>";
		
		for (var j = 0; j< 2; j++)
		{
			col = 0;
			row += 1;
			
			html += "<tr id=\"row"+row+"\">";
			
			for (var k = 0; k <= col_header.length; k++)
			{
				if (k > 0)
				{
					html += "<td><input type=\"text\" id='data"+row+col+"'></td>";
					col += 1;
				}
				else
				{
					html += "<td></td>";
				}
				
			}
			html += "</tr>";
		}
		html += "</tbody></table>";
		document.getElementById('size_chart_box').style.display = 'block';
		$("#size_table").html(html) ;
	}
	}
}


 function  addRow(){
	
	var htm = "<tr id=\"row"+row+"\">";
	for (var k = 0;k<=col;k++)
	{
		if (k > 0)
			htm += "<td><input type=\"text\" id='data"+row+k+"'></td>";
		else
			htm += "<td></td>";
	}
	htm += "</tr>";
	row += 1;
	
	$('#info tr:last').after(htm);
}

function removeRow()
{
	if (row > 0)
	{
		$('#info tr:last').remove();
		row -= 1;
	}
	else
	{
		alert("Headers of the table can't be removed");
	}
	//console.log("no of rows"+row);
}

var viewData = { 
	    SizeChart : [] 
	};

var Data = {
		RowInfo : []
};

var Final = {};
Row = {};
Col = {};
function FormJson()
{
    var ColHeaders = {};
    var RowHeaders = {};
    
    table = $("#info");
    var j = 0;
    if (row_flag == 0 && col_flag == 1)
    {
	    table.find('tr').each(function (i, el) {
	    	
	        viewData.SizeChart.push({});
	    	$(this).find('td').each(function (index, val) {
	    		//console.log("row"+i+"column"+index);
	    		if (i > 0)
	    		{
	    			id = "#data"+i+index;
	    			data = $(id).val(); 
	    			if (data != undefined)
	    			{
	    				viewData.SizeChart[i][ColHeaders[index+1]] = data;
	    			}
	    		}
	    		else
	    		{
	    			ColHeaders[index] = val.innerHTML;
	    			Col[index] = val.innerHTML;
	    			//console.log(ColHeaders);
	    		}
	    	});
	    	Final[i] = viewData.SizeChart[i];
	    	});
	    JSON.stringify(ColHeaders);
	    //alert(JSON.stringify(Final));
    }
    else if ((row_flag == 1) && (col_flag == 1))
    {
    	var Chart = {};
    	$("#info").find('tr').each(function (i, el) {
    	    viewData.SizeChart.push({});
    	    Data.RowInfo.push({});
    	    
    		$(this).find('td').each(function (index, val) {
    			if ((i > 0) && (index > 0))
    			{
    				id = "#data"+i+index;
    				data = $(id).val(); 
    				
    				if (data != undefined)
    					Data.RowInfo[i][ColHeaders[index]] = data;
    					//alert(data);
    			}
    			if (i == 0 && index > 0)
    			{
    				
    				ColHeaders[index] = val.innerHTML;
    				Col[index] = val.innerHTML;
    			}
    			if ((i > 0) && (index == 0))
    			{
    				id = "#data"+i+index;
    				data = $(id).text(); 
    				
    				RowHeaders[i] = data;
    				Row[i] = data;
    				//alert(RowHeaders[i])
    			}
    		});
    		//viewData.SizeChart[i][RowHeaders[i]] = Data.RowInfo[i];
    		Final[RowHeaders[i]] = Data.RowInfo[i];
    		//console.log(viewData.SizeChart[i]);
    		Chart[RowHeaders[i]] = Data.RowInfo[i];
    		//alert(Chart[RowHeaders[i]]);
    		//console.log(Chart[RowHeaders[i]]);
    		});
    }
  
    //console.log(JSON.stringify(Final));
    //alert(Final);
    //if(Final.length!=0)
	//{
    $.ajax({
	    type: "POST",
	    url: "sendinfo",
	    data: {
    		sizechart : JSON.stringify(Final),//JSON.stringify(viewData.SizeChart),
    		row_flag  : row_flag,
    		col_flag  : col_flag,
    		Row		  : JSON.stringify(Row),
			Col		  : JSON.stringify(Col),
			brandName : $("#brandName").val(),
			sellerCode : $("#sellerCode").val()
            },
	    
	    success: function(sizechart) 
	    	{
	    		console.log("data sent successfully");
	    		//alert(viewData.SizeChart);
	        },
	    error: function() {
	        console.log("Failed to send information");
	    },
	    async : false
	});//}
}

function addError(handle, msg){
	$(handle).closest("div").children('span.error').html(msg);
}

function removeError(handle){
	$(handle).closest("div").children('span.error').html('');
}

function formcheck() {
	 var sellerCode = $('#sellerCode').val().trim();
	 var flag= 0;
	 //CheckTable();
	 if(!sellerCode || sellerCode.length < 1){
		 addError('#sellerCode', 'Please Enter Seller Code');
		 flag = 1;
	 }else{
		 removeError('#sellerCode');
	 }
	 var brandName = $('#brandName').val().trim();
	 if(!brandName || brandName.length < 1){
		 addError('#brandName', 'Please Enter Brand Name');
		 flag = 1;
	 }else{
		 removeError('#brandName');
	 }
	 var description = $('#description').val().trim();
	 if(!description || description.length < 1){
		 addError('#description', 'Please Enter Description');
		 flag = 1;
	 }else{
		 removeError('#description');
	 }
	 var category = $('#category').val().trim();
	 if(!category || category.length < 1){
		 addError('#category', 'Please Enter Category');
		 flag = 1;
	 }else{
		 removeError('#category');
	 }
	 var brandImage = $('#brandImage').val().trim();
	 if(!brandImage || brandImage.length < 1){
		 addError('#brandImage', 'Please Select File');
		 flag = 1;
	 }
	 else if (is_image == 0)
	 {
		 addError('#brandImage',"Please upload file with extension '.jpeg' or '.jpg' or '.png' or '.gif' or '.bmp' ");
		 flag = 1;
	 }
	 else if(size_in_range == 0)
	 {
		 addError('#brandImage',"Please upload file of size less than 1 Mb");
		 flag = 1;
	 }
	 else{
		 removeError('#brandImage');
	 }
	 /*if(check == 1)
	 {
		  addError('#size_table','Please Complete Size Chart');
		  check = 0;
	 }
	 else
	 {
		 removeError('#brandImage');
		 check = 0;
	 }*/
	  if (flag == 0)
	  {
		  CheckTable();
		  if(check == 1)
		  {
			  addError('#size_table','Please Complete Size Chart');
			  check = 0;
		  }
		  else if(is_image == 0)
		  {
			  addError('#brandImage',"Please upload file with extension '.jpeg' or '.jpg' or '.png' or '.gif' or '.bmp' ");
			  is_image = 1;
		  }
		  else
		  {
			  	removeError('#size_table');
			  	removeError('#brandImage');
			  	//var result = CheckFileType();
			  	FormJson();
				//alert("submitting form");
			  	//alert($('#category').val().trim());
				$( "#addbrandform" ).submit();
		  }
	  }
	  else
	  {  
		  CheckTable();
		  if(check == 1)
		  {
			  addError('#size_table','Please Complete Size Chart');
			  check = 0;
		  }
		  else
		  {
			  removeError('#size_table');
			  //check = 0;
		  }
		  return false;
	  }
	  
	  return true;
	}

function CheckTable()
{
	if($("#info").text() != "")
	{
		if (row == 0)
		{
			check = 1;
			return;
		}
		else
		{
			$("#info").find('tr').each(function (i, el) {
		    	$(this).find('td').each(function (index, val) {
		    		if (i > 0 && index > 0)
		    		{
		    			id = "#data"+i+index;
		    			data = $(id).val();
		    			console.log(data);
		    			if (data == "")
		    			{
		    				check = 1;
		    				return;
		    			}
		    		}
		    		
		    	});
		    	});
		}
	}
}

$(function(){
	var category_list = [];
	$.ajax({
        url : "searchAction",
        type : "POST",
        dataType : "json",
        success : function(jsonResponse) {
        		var data=(jsonResponse['list']);
        		//alert(data);
        		temp=String(data);
        		//alert(temp);
        		temp2=temp.split(":");
        		var i=0;
        		for(i=0;i<temp2.length;i++){
        			category_list.push(temp2[i]);
        		}
        }
});

	$("#category").autocomplete({
        source : function(request, response)
        		 {
        			var results = $.ui.autocomplete.filter(category_list, request.term);
	    			response(results.slice(0, 20));
	    		  },
        select : function(event, ui){
        			//console.log("inside event of auto fuction");
        			$("#size_table").html("");
        			//alert(ui.item.value);
        			sizechart(ui.item.value);
        		}
                });
	
});