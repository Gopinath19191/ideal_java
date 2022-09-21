/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function showHRDiv(id){
    
   /* if(id=="Clarification"){
       // document.getElementById('clarification').style.display='block';
       // document.getElementById('training').style.display='none';
        document.getElementById('addressProof').style.display='none';
       // document.getElementById('resource').style.display='none';
        document.getElementById('confAddress').style.display='none';
        document.getElementById('SalaryCertificate').style.display='none';
        document.getElementById('ProofOfEmployment').style.display='none';
    }*/
    if(id=="0"){
       //  document.getElementById('clarification').style.display='none';
       // document.getElementById('training').style.display='none';
        document.getElementById('addressProof').style.display='block';
        //document.getElementById('resource').style.display='none';
        document.getElementById('confAddress').style.display='none';
        document.getElementById('SalaryCertificate').style.display='none';
        document.getElementById('ProofOfEmployment').style.display='none';
    }else if(id=="1"){
       //  document.getElementById('clarification').style.display='none';
        document.getElementById('SalaryCertificate').style.display='block';
        document.getElementById('addressProof').style.display='none';
        document.getElementById('ProofOfEmployment').style.display='none';
        document.getElementById('confAddress').style.display='none';
    }else if(id=="2"){
       //  document.getElementById('clarification').style.display='none';
        document.getElementById('SalaryCertificate').style.display='none';
        document.getElementById('addressProof').style.display='none';
        document.getElementById('ProofOfEmployment').style.display='block';
        document.getElementById('confAddress').style.display='none';
    }
    /*else if(id=="Training"){
         document.getElementById('clarification').style.display='none';
        document.getElementById('training').style.display='block';
        document.getElementById('addressProof').style.display='none';
        document.getElementById('resource').style.display='none';
        document.getElementById('confAddress').style.display='none';
    }else if(id=="Resource"){
         document.getElementById('clarification').style.display='none';
        document.getElementById('training').style.display='none';
        document.getElementById('addressProof').style.display='none';
        document.getElementById('confAddress').style.display='none';
        document.getElementById('resource').style.display='block';
}*/
else if(id=="Confirm"){
       //  document.getElementById('clarification').style.display='none';
      // document.getElementById('training').style.display='none';
        document.getElementById('addressProof').style.display='none';
      //  document.getElementById('resource').style.display='none';
        document.getElementById('confAddress').style.display='block';
        document.getElementById('SalaryCertificate').style.display='none';
        document.getElementById('ProofOfEmployment').style.display='none';
}


}
