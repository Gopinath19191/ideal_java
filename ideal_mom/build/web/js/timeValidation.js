//var globlevar = {};

  
  
$(document).ready(function() {
     $(document).delegate(".accrueTime","focusout",  function() {
//       alert("logeshwari");
        var text_box_val = $(this).val();
       
        
        if (text_box_val != '') {
            var explode_with = '.';
            var check_text_box_hours_format = text_box_val.indexOf(".");
            if (check_text_box_hours_format == -1)
                explode_with = ':';
            text_box_val = text_box_val.split(explode_with);
            var output = '';
            if ((typeof text_box_val[0] != 'undefined') && (text_box_val[0] != '')) {
                output = text_box_val[0] + ":";
            } else {
                output = "00" + ":";
            }
            if ((typeof text_box_val[1] != 'undefined') && (text_box_val[1] != '')) {
                output += ("0" + (text_box_val[1])).slice(-2);
            } else {
                output += "00"
            }
            if(text_box_val)
            $(this).val(output);
        }
        
    });
    $('.accrueTime').live('keyup', function() {
        var isHours = $(this).val().indexOf(":");
        if (isHours == -1) {
            $(this).val(this.value.replace(/^[0]/, ''));
        }

        var explode_with = ':';
        var check_24_hrs = $(this).val().split(explode_with);
        if ((check_24_hrs[0] !== '') && (typeof (check_24_hrs[0]) != "NaN")) {

            if (check_24_hrs[0] > 23) {
                $(this).val(23 + ":" + 59);
            }
            else if (check_24_hrs[1]) {
                if (check_24_hrs[1].length > 2) {
                $(this).val(23 + ":" + 59);}
            }
        } else if (check_24_hrs[1] == undefined) {
            $(this).val(0);
        }


    });
    $('.accrueTime').keydown(function(e) {
        if (e.keyCode == 32 || (e.shiftKey && e.keyCode == 190)) {// 32 is the ASCII value for a space
            e.preventDefault();
        }
// Allow: backspace, delete, tab, escape and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 58, 110, 190]) !== -1 ||
                // Allow: Ctrl+A, Command+A
                        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                        // Allow: home, end, left, right, down, up
                                (e.keyCode >= 35 && e.keyCode <= 40) ||
                                //Allow: colon
                                        (e.shiftKey && (e.keyCode == 186 || e.keyCode == 59)))
                        {
                            // let it happen, don't do anything
                            return;
                        }
                        // Ensure that it is a number and stop the keypress
                        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105 || e.keyCode == 190)) {
                            e.preventDefault();
                        }
                    });
          
                    $(".difference_reason").change(function () {
    $(".error_amt_exceed").hide();
            if ($(this).val() == '') {
    $(this).closest("td").addClass("reason_error");
    } else {
    $(this).closest("td").removeClass("reason_error");
    }
    });
       
})

      
       
