var space="&nbsp;&nbsp;&nbsp;*&nbsp;&nbsp;";
var minMobile=10;
	
	//Radio Group Validation 
	//returns true if any one is checked otherwise false
	function rGpValidate(fr,el){
	var radios = fr.elements[el];//gets all the elements of the form (fr) which has the name (el)
	checkFwarning(el)	
 	for (var i=0; i <radios.length; i++) {
  		if (radios[i].checked) {
   		return true;
  		}
 	}
 	document.getElementById('Er'+el).innerHTML=space+" Select one";
 	 return false;
	}
	
	//Creates an span element in the parant tag of the validating element id of that 
	//element will be name of the corresponding validation element with prefix Er 
	function checkFwarning(el){
	if(document.getElementById('Er'+el))
		document.getElementById('Er'+el).innerHTML="";
	else{
		var newElement=document.createElement('span')
		newElement.setAttribute('id','Er'+el)
		newElement.setAttribute('class','fwarning')
		newElement.setAttribute('className','fwarning')
		if(document.getElementById(el))
			document.getElementById(el).parentNode.appendChild(newElement)
		if(document.getElementsByName(el)[0])
			document.getElementsByName(el)[0].parentNode.appendChild(newElement)
		}
	}
	
	//numeric validation 
	//given Element ID
	//Returns true if the value of the field is number, and the length>0
	function numValidate(el){
	checkFwarning(el)	
 	var t=document.getElementById(el).value;
 	t=trim(t)
 	document.getElementById(el).value=t
 	if((!isNaN(t))&&(t.length>0)) return true;
 	document.getElementById('Er'+el).innerHTML=space+"The Value Should Be a Number";
 	return false;
	}
	
	//Email Validation
	//given element ID
	//returns true if the value is valid email
	function mailValidate(el){	
	checkFwarning(el)	
 	var t=document.getElementById(el).value;
	var patt=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var result=patt.exec(t);
 	if(result!=null) return true;
 	document.getElementById('Er'+el).innerHTML=space+"The Email should be a valid one";
 	return false;
	}
	
	//Minimum length validation
	//given Element ID, Minimum length
	//returns false if the length of the element's value is < given length  
	function lenMinValidate(el,len){
	checkFwarning(el)	
		if(document.getElementById(el).value.length>=len) return true;
		document.getElementById('Er'+el).innerHTML=space+"Min length should be "+len;
		return false;
	}
	
	//Minimum length validation
	//given Element ID, Minimum length
	//returns false if the length of the element's value is < given length  
	function passwordlenValidate(el,len){
	checkFwarning(el)	
		if(document.getElementById(el).value.length>len) return true;
		document.getElementById('Er'+el).innerHTML=space+"Password should contain at least "+ (len+1) + " charaters";
		return false;
	}
	
	function verifyPasswordValidate(password,verify){
		checkFwarning(password)
		var originalPass = document.getElementById(password).value;
		var verifyPass   =	document.getElementById(verify).value;
		if(originalPass == verifyPass)return true;			
		document.getElementById('Er'+password).innerHTML = space + "Password should match with provided..";
	}
	
	function verifyNotNull(el,error){
		alert('hi');
		var input = document.getElementById(el).value;
		if(input == ' ')return true;
		document.getElementById('Er'+error).innerHTML = space + "Password should match with provided..";
		
	}
	//Maximum length validation
	//given Element ID, Maximum length
	//returns false if the length of the element's value is > given length  
	function lenMaxValidate(el,len){
	checkFwarning(el)	
		if(document.getElementById(el).value.length<len) return true;
		document.getElementById('Er'+el).innerHTML=space+"Max length should be "+len;
		return false;
	}
	
	function selectValidate(elid,defVal){
	checkFwarning(elid)
	var tempSE=document.getElementById(elid);
	//alert(document.getElementById(elid).value)
	//if((defVal==document.getElementById(elid).value)) return false;
	if(tempSE && (tempSE.value==defVal)){
	 document.getElementById('Er'+elid).innerHTML=space+'Select one'
	  return false;
	  }
	return true;
	}
	
	
	
	//confirmSubmit
	//given form name, action to which the form should be submitted
	//it gets the confirmation of submitting the form
	function confirmSubmit(f1,faction,msg){
	var agree=false
	//var n=document.getElementById('totRecord').value;
	//for(var i=0;i<n;i++)
	//if(document.getElementById("check"+i).checked==true){
	agree=confirm("Are you sure you wish to "+((msg)?msg:'Submit')+"?");
	if (agree){
	submitForm(f1,faction)
	}
	return;
	//}
	//alert('Please Select Atleast one record')
	}
	
	//submitForm
	//given formname, action
	//it submits the form to the given action
	function submitForm(f1,faction){
	document.forms[f1].action=faction
	document.forms[f1].submit();	
	}
	
	
	
	
/*Fleet Device Interface module	
* Created By: Mohanakannan.E
*	    Data: 05/01/2010
*/	

//To Select All option 

function Check(chk,minval,maxval)
{
if(chk==true)
{
for (i=minval;i<maxval;i++)
{
document.getElementById("checkList"+i).checked=true;
}
}
else if(chk==false)
{
for (i=minval;i<maxval;i++)
{
document.getElementById("checkList"+i).checked=false;
}
}
}


function displayError(msg) {
        var dividError= document.getElementById('classErrorSearch');
        dividError.style.display='block';
        document.getElementById('errorSearch').innerHTML=msg;
  }
// Removes leading whitespaces
function LTrim( value ) {
	
	var re = /\s*((\S+\s*)*)/;
	return value.replace(re, "$1");
	
}

// Removes ending whitespaces
function RTrim( value ) {
	
	var re = /((\s*\S+)*)\s*/;
	return value.replace(re, "$1");
	
}

// Removes leading and ending whitespaces
function trim( value ) {
	
	return LTrim(RTrim(value));
	
}


// Asyncronous AJAX Call..


function postCall(url){
	var div = document.getElementById("dynamic"); 
  	document.getElementById('warning').innerHTML=" ";
 	try{
 		if (window.XMLHttpRequest)
  			{  				
   				xmlhttp=new XMLHttpRequest();
   				//alert('XmlHttpRequest object created..');  			
  			}
		else if(window.ActiveXObject)
  			{
  				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");  		
  				//alert('activex component created..');
  			}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4)
  				{
  					//alert(xmlhttp.responseText);  
  					div.innerHTML = xmlhttp.responseText;
  					var payload = xmlhttp.responseText;  					
  					//alert("Payload: "+payload);					
   				}
		}
		
	
	//alert("URL: "+url);
	xmlhttp.open("GET",url,true);
	xmlhttp.send(null);
 	}
 	catch(e){
 		alert('Error: '+e);
 	}
}

function goto(element)
{
alert(document.getElementById("Go").selectedIndex.value);
}

function functional_valid()
{
alert('hai');
var choice=document.getElementById('choice').value;
if(choice=='readonly')
document.myform.reset();
return true;
}

function checkAlphNum(elid){
checkFwarning(elid)
var temp=document.getElementById(elid);
if(!temp) temp=document.getElementsByName(elid)[0];
	if (temp.value.match(/^[a-zA-Z0-9\s]+$/))
	{
		return true;
	}
	else
	{
	document.getElementById('Er'+elid).innerHTML=space+"Non Alpha Numeric Not Allowed";
		return false;
	}
}

function validateAsgmtForm(){
var myReturn=true;
return myReturn;
}
function validateGoalForm(){
var myReturn=true;
if(myReturn){
myReturn=numValidate('hours')
}
return myReturn
}

function disableSubmit(){
    alert("test data");
}