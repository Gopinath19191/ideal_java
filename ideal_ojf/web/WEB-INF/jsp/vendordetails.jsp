<%@include file="header.jsp"  %>
<title>Joining Formalities - Initiation Process</title>
<script type="text/javascript">
function VinChecknum(e)
{
    var keynum=0;
    var keychar;
    var charcheck;
    if(window.event) // IE
       keynum = e.keyCode;
    else if(e.which) // Netscape/Firefox/Opera
       keynum = e.which;
   
    if(keynum==8 || keynum==0)
    {
        
    }
    else
    {
    keychar = String.fromCharCode(keynum);
    charcheck = /[a-zA-Z0-9& ]/;
    return charcheck.test(keychar);        
    }
    return true;
}
     function validatation()
     {
         if(document.getElementById("newvendorName").value=='')
         {
            $('.customError').html('Enter Vendor Name'); 
            $('.customError').attr('style', 'color:red;cursor:default;');
            document.getElementById("newvendorName").focus();
             return false;
         }else{
             
             return true;
         }
     }
     function backToList()
     {
         window.location.href="${pageContext.request.contextPath}/com/defiance/ideal/joiningForm/begin.do";
     }
     function fnedit(i)
     {
         $("#venlab"+i).hide();
         $("#venname"+i).hide();
         $("#vendorid"+i).show();
         $("#vendorName"+i).show();
         $("#Update"+i).show();
         $("#Edit"+i).hide();
         $("#Delete"+i).hide();
         $("#Cancel"+i).show();
         
     }
     function fncancel(m)
     {
       $("#venlab"+m).show();
         $("#venname"+m).show();
         $("#vendorid"+m).hide();
         $("#vendorName"+m).hide();
         $("#Update"+m).hide();
         $("#Edit"+m).show();
         $("#Delete"+m).show();
         $("#Cancel"+m).hide();  
     }
     function fnsavepartvendor(k)
     {
         var venid=document.getElementById('vendorid'+k).value;
         var venname=document.getElementById('vendorName'+k).value;
            $.post('updateparticularvendor.htm', {
                selectedvenid:venid,
                selectedvenname:venname
            }, 
            function(ajaxObj) { 
              var arr=ajaxObj.split(",");
              document.getElementById('vendorid'+k).value=arr[0];
              document.getElementById('vendorName'+k).value=arr[1];
              //alert(arr[2])
              $("#venlab"+k).html(arr[0])
              $("#venname"+k).html(arr[1]);
              $("#venlab"+k).show();
         $("#venname"+k).show();
         $("#vendorid"+k).hide();
         $("#vendorName"+k).hide();
         $("#Update"+k).hide();
         $("#Edit"+k).show();
         $("#Delete"+k).show();
         $("#Cancel"+k).hide();
         
         if(arr[2]=='update')
         {
             $('#errmsg').html('Vendor Updated Successfully');
              $('#errmsg').attr('style', 'color:green;cursor:default;margin-right:50%');
         }
         else if(arr[2]=='notupdate')
         {
             $('#errmsg').html('Vendor Already Exists');  
             $('#errmsg').attr('style', 'color:red;cursor:default;margin-right:50%');
         } 
         
         
          }
                );
         
     }
     function fndelete(k)
     {
         var con = confirm("Are you sure want to delete ?");
         if(con) {
            var selvenid=document.getElementById('vendorid'+k).value;
            $.post('deleteparticularvendor.htm', {
                selectvenid:selvenid
                },
                function(ajaxObj) {
                    var arrli=ajaxObj.split(":");
                    $("#row"+k).remove();
                     $("#venlab"+k).show();
                     $("#venname"+k).show();
                     $("#vendorid"+k).hide();
                     $("#vendorName"+k).hide();
                     $("#Update"+k).hide();
                     $("#Edit"+k).show();
                }
            );
         } else return false;
          
     }
     $(document).ready(function(){
        if(document.getElementById('saveindication').value=='save')
         {
             $('#errmsg').html('Vendor Saved Successfully');
              $('#errmsg').attr('style', 'color:green;cursor:default;margin-right:50%');
         }
         else if(document.getElementById('saveindication').value=='Exists')
         {
             $('#errmsg').html('Vendor Already Exists');  
             $('#errmsg').attr('style', 'color:red;cursor:default;margin-right:50%');
         }    
          
          $("#vendorForm").validate({
                        errorElement:"div",
                        errorClass:"customError"
                        });
          
          
});

</script>
        
<body class="ext-gecko ext-gecko3" />
    <div id="container">
        <%@include file="menu.jsp" %>
        <div id="commonforms">
            <div class="commonformheader">Add Vendor</div>
            <form name="vendorForm" id="vendorForm" action="vendorsave.htm" method="post">
                <input type="hidden" name="saveindication" id="saveindication" value="${saveindication}"/>
                <div id="datadisplay">
                    <table cellspacing="0" cellpadding="0" id="prjectPhaseDetails" align="center" style="width: 80%" style="border-collapse:collapse;">
                        <tr class="header">
                            <th style="width:70%;" align="center">Vendor Name</th>
                            <th style="width:40%;" align="center">Action</th>
                        </tr>
                        <c:forEach items="${vendorList}" var="vendorList" varStatus="indxz">
                            <tr class="${indxz.index%2==0?'oddrow':'altrow'}" id="row${indxz.index}">

                                <td  style="width:11%;display: none;">
                                    <div id="venlab${indxz.index}">${vendorList.vendorId}</div>
                                    <input type="text" name="vendorid" id="vendorid${indxz.index}"value="${vendorList.vendorId}" style="width: 95%;display: none;" maxlength="100" />
                                </td>
                                <td  style="width:11%;">
                                    <div id="venname${indxz.index}">${vendorList.vendorName}</div>
                                    <input type="text" name="vendorName" id="vendorName${indxz.index}" onKeyPress="return VinChecknum(event);" value="${vendorList.vendorName}" style="width: 95%;display: none;"/>
                                </td>
                                <td  style="width:10%;">

                                     <input class="editbutton" type="button" value="Edit" id="Edit${indxz.index}" onclick="fnedit(${indxz.index})">
                                     <input class="savebutton" type="button" value="Update" style="display: none;" id="Update${indxz.index}" onclick="fnsavepartvendor(${indxz.index})">
                                     <input class="cancelbutton" type="button" value="Delete" id="Delete${indxz.index}" onclick="return fndelete(${indxz.index})">
                                     <input class="cancelbutton" type="button" value="Cancel" style="display: none;" id="Cancel${indxz.index}" onclick="fncancel(${indxz.index})">
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div style="padding: 15px;"></div>
                    <div style="padding-left: 5%;margin-left:-46%;margin-top:-2%;"><b>Add New Vendor</b></div>
                    <table width="99%" border="0" align="center" id="prjectPhaseDetails" style="width: 50%">
                        <tr class="header">
                             <th>Vendor Name</th>
                             <th></th>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="newvendorName" id="newvendorName" class="required" onKeyPress="return VinChecknum(event);" maxlength="100" />
                                <div class="customError"></div>
                            </td>
                            <td>
                                <input class="submitbutton" type="submit" value="Submit">
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
