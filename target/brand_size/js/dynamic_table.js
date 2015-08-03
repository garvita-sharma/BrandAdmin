/**
 * 
 */
$(document).ready(function(){
      $('#row').click(function(){
    	  $('#tbl_row:first').clone().insertAfter('#tbl_row:first');
    	  //alert($("#row_value").val());
    	  $('#row_value').val(parseInt($("#row_value").val()) + 1);
    	  //alert(parseInt($('#row_value').val())); 
      })
      
      $('#column').click(function(){
    	  $('#tbl_row > #D_copy, #head_row > #D_copy').each(function(){$(this).clone().insertBefore($(this))});   
    	  $('#col_value').val(parseInt($("#col_value").val()) + 1);
      })
});