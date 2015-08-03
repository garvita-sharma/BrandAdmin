var row_flag , col_flag;
var row = 0;
var col = 0;
//var check = 0;

$(document).ready(function()
{
		var sellerCode = getParameterByName("sellercode");
		
		$("#sellerCode").val(sellerCode);
		
		var cat = getParameterByName("category");
		var brand = getParameterByName("brand");
		
		populateList();
		
		$("#category").attr("selected",null);
		
		if (cat != "")
		{
			$('#category option[value="'+cat+'"]').attr("selected","selected");
		}
		
		if ($("#category").val() != -1)
		{
			sizechart();
		}
		
		$("#category").on("change",function()
		{
			$("#size_table").html("");
			sizechart();
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
            alert("Failed to load categories");
        },
        async : false
    });
}

function sizechart()
{
	$.ajax({
        url: "SizeChart",
        type: "POST",
        data: {
        		cat_name : $("#category").val()
        		},
        dataType: "json",
        success: function(SizeChartJson) {
        	var info = $.parseJSON(SizeChartJson['sizeChartJson']);
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
            alert("Failed to get size chart info");
        },
        async : false
    });
}

function createtable(row_flag,col_flag,info)
{
	var html;
	if (row_flag == 1 && col_flag == 1)
	{
		var row_header = info["row_json"].split(",");
		var col_header = info["col_json"].split(",");
		html = "<table class = 'table table-bordered' id='info' name=\"values\"><tr id=\"row"+row+"\">";
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
		$("#size_table").html(html) ;
	}
	else if (row_flag == 1 && col_flag == 0)
	{
		var row_header = info["row_json"].split(",");
		html = "<table class = 'table table-bordered' id='info' name=\"values\><thead id=\"row"+row+"\">";
		
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
		$("#size_table").html(html) ;
	}
	else
	{
		var col_header = info["col_json"].split(",");
		html = "<table class = 'table table-bordered' id='info' name=\"values\"><thead id=\"row"+row+"\">";
		//html += "<td><input type='button' id='decrement' onclick='removeRow()' value='-'></td>";
		for (var i = 0; i < col_header.length; i++)
		{
			html += "<td id=\"data"+row+col+"\">"+col_header[i]+"</td>";
			col += 1;
		}
		
		html += "<td><input type='button' id='increment' onclick='addRow()' value='+'></td></thead><tbody>";
		
		for (var j = 0; j< 2; j++)
		{
			col = 0;
			row += 1;
			
			html += "<tr id=\"row"+row+"\">";
			
			for (var k = 0; k < col_header.length; k++)
			{
				
				html += "<td><input type=\"text\" id='data"+row+col+"'></td>";
				col += 1;
			}
			html += "</tr>";
		}
		html += "</tbody></table>";
		
		$("#size_table").html(html) ;
	}
}


 function  addRow(){
	
	var htm = "<tr id=\"row"+row+"\">";
	for (var k = 0;k<col;k++)
	{
		htm += "<td><input type=\"text\" id='data"+row+k+"'></td>";
	}
	htm += "</tr>";
	row += 1;
	
	$('#info tr:last').after(htm);
}

function removeRow()
{
	$('#info tr:last').remove();
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
    if (row_flag == 0 && col_flag == 1)
    {
	    table.find('tr').each(function (i, el) {
	    	
	        viewData.SizeChart.push({});
	    	$(this).find('td').each(function (index, val) {
	    		if (i > 0)
	    		{
	    			id = "#data"+i+index;
	    			data = $(id).val(); 
	    			
	    			viewData.SizeChart[i][ColHeaders[index]] = data;
	    		}
	    		else
	    		{
	    			ColHeaders[index] = val.innerHTML;
	    			Col[index] = val.innerHTML;
	    			console.log(ColHeaders);
	    		}
	    		Final[i] = viewData.SizeChart[i];
	    	});
	    	});
	    JSON.stringify(ColHeaders);
    }
    else if ((row_flag == 1) && (col_flag == 1))
    {
    	/*table.find('tr').each(function (i, el) {
	        //viewData.SizeChart.push({});
	    	$(this).find('td').each(function (index, val) {
	    		if (i > 0) and (index > 0)
	    		{
	    			id = "#data"+i+index;
	    			data = $(id).val(); //document.getElementById(id).val();
	    			alert(data);
	    			//console.log(data);
	    			if (data != undefined)
	    				Data.RowInfo[i][ColHeaders[index]] = data;
	    		}
	    		if (i == 0) and (index == 0)
	    		{
	    			//console.log(val.innerHTML);
	    			id = "#data"+i+index;
	    			data = $(id).text(); //document.getElementById(id).val();
	    			if (data != undefined)
	    				ColHeaders[index] = data;
	    		}
	    		if (i > 0) and (index == 0)
	    		{
	    			id = "#data"+i+index;
	    			data = $(id).text(); //document.getElementById(id).val();
	    			//console.log(val.innerHTML);
	    			if (data != undefined)
	    				RowHeaders[index] = data;
	    		}
	    	});
	    	viewData.SizeChart[i][RowHeaders[i]] = Data.RowInfo[i];
	    	console.log(viewData.SizeChart[i]);
	    	});
    		//console.log(JSON.stringify(viewData.SizeChart));*/
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
    		console.log(Chart[RowHeaders[i]]);
    		});
    }
  
    //console.log(JSON.stringify(Final));
    //alert(Final);
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
	});
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
	 if(!sellerCode || sellerCode.length < 1){
		 addError('#sellerCode', 'Please, enter seller code');
		 flag = 1;
	 }else{
		 removeError('#sellerCode');
	 }
	 var brandName = $('#brandName').val().trim();
	 if(!brandName || brandName.length < 1){
		 addError('#brandName', 'Please, enter brand name');
		 flag = 1;
	 }else{
		 removeError('#brandName');
	 }
	 var category = $('#category').val().trim();
	 if(category == "-1"){
		 addError('#category', 'Please, select category');
		 flag = 1;
	 }else{
		 removeError('#category');
	 }
	 var check = CheckTable();
	 alert("value of check is:"+check);
	 if (flag == 0 && check == 0)
	 {
		 FormJson();
		 alert("submitting form");
		 $( "#chartform" ).submit();
	 }
	 else
	 {
		  if(check == 1)
			  addError('#size_table','Please Complete Size Chart');
		  else
			  removeError('#size_table');
		  return false;
	 }
	 return true;
	}

function CheckTable()
{
	var check = 0;
	if($("#info").text() != "")
	{
		$("#info").find('tr').each(function (i, el) {
	    	$(this).find('td').each(function (index, val) {
	    		if (i > 0)
	    		{
	    			id = "#data"+i+index;
	    			data = $(id).val();
	    			if (data == "")
	    				check = 1;
	    		}
	    		
	    	});
	    	});
	}
	return check;
}
/*function validate(sellerCode)
{
	$.ajax({
        type: "POST",
        url: "Validate",
        data: {
        		sellerCode : sellerCode
        }, 
        success : function(res){
        			if (res == true)
        				alert("seller code is valid");
        			else
        				alert("seller code is not valid");
        },
        async : false
    });
}
*/
/*table.find('tr').each(function (i, el) {
    //viewData.SizeChart.push({});
    alert("row is"+i);
	$(this).find('td').each(function (index, val) {
		if ((i > 0) && (index > 0))
		{
			id = "#data"+i+index;
			data = $(id).val(); //document.getElementById(id).val();
			alert(data);
			//console.log(data);
			//if (data != undefined)
				//Data.RowInfo[i][ColHeaders[index]] = data;
		}
		if (i == 0 && index > 0)
		{
			alert(val.innerHTML);
			//id = "#data"+i+index;
			//data = $(id).text(); //document.getElementById(id).val();
			//if (data != undefined)
			ColHeaders[index] = val.innerHTML;
		}
		if ((i > 0) && (index == 0))
		{
			id = "#data"+i+index;
			data = $(id).text(); //document.getElementById(id).val();
			//console.log(val.innerHTML);
			//if (data != undefined)
            //alert(val.innerHTML);
            alert(data);
			//RowHeaders[index] = val.innerHTML;
		}
	});
	//viewData.SizeChart[i][RowHeaders[i]] = Data.RowInfo[i];
	//console.log(viewData.SizeChart[i]);
	});*/
