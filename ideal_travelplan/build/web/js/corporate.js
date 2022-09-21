/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function showDiv(id){
   // alert(id);
    if(id=="Id Card"){
        document.getElementById('idCard').style.display='inline';
        document.getElementById('visitorCard').style.display='none';
        document.getElementById('conference').style.display='none';
        document.getElementById('projector').style.display='none';
        document.getElementById('accessCard').style.display='none';
        document.getElementById('training').style.display='none';
        document.getElementById('mobile').style.display='none';
        document.getElementById('stationary').style.display='none';
    }else if(id=="Visiting Card"){
         document.getElementById('idCard').style.display='none';
        document.getElementById('visitorCard').style.display='inline';
        document.getElementById('conference').style.display='none';
        document.getElementById('projector').style.display='none';
        document.getElementById('accessCard').style.display='none';
        document.getElementById('training').style.display='none';
        document.getElementById('mobile').style.display='none';
        document.getElementById('stationary').style.display='none';

    }else if(id=="Projector"){
         document.getElementById('idCard').style.display='none';
        document.getElementById('visitorCard').style.display='none';
        document.getElementById('conference').style.display='none';
        document.getElementById('projector').style.display='inline';
        document.getElementById('accessCard').style.display='none';
        document.getElementById('training').style.display='none';
        document.getElementById('mobile').style.display='none';
        document.getElementById('stationary').style.display='none';
    }else if(id=="Conference Room"){
         document.getElementById('idCard').style.display='none';
        document.getElementById('visitorCard').style.display='none';
        document.getElementById('conference').style.display='inline';
        document.getElementById('projector').style.display='none';
        document.getElementById('accessCard').style.display='none';
        document.getElementById('training').style.display='none';
        document.getElementById('mobile').style.display='none';
        document.getElementById('stationary').style.display='none';
}else if(id=="Access Card"){
        document.getElementById('idCard').style.display='none';
        document.getElementById('visitorCard').style.display='none';
        document.getElementById('conference').style.display='none';
        document.getElementById('projector').style.display='none';
        document.getElementById('training').style.display='none';
        document.getElementById('accessCard').style.display='inline';
        document.getElementById('mobile').style.display='none';
        document.getElementById('stationary').style.display='none';
}else if(id=="Training Room"){
         document.getElementById('idCard').style.display='none';
        document.getElementById('visitorCard').style.display='none';
        document.getElementById('conference').style.display='none';
        document.getElementById('projector').style.display='none';
        document.getElementById('accessCard').style.display='none';
        document.getElementById('training').style.display='inline';
        document.getElementById('mobile').style.display='none';
        document.getElementById('stationary').style.display='none';
}else if(id=="Stationary"){
        document.getElementById('idCard').style.display='none';
        document.getElementById('visitorCard').style.display='none';
        document.getElementById('conference').style.display='none';
        document.getElementById('projector').style.display='none';
        document.getElementById('accessCard').style.display='none';
        document.getElementById('stationary').style.display='inline';
        document.getElementById('training').style.display='none';
        document.getElementById('mobile').style.display='none';
}else if(id=="Mobile"){
        document.getElementById('idCard').style.display='none';
        document.getElementById('visitorCard').style.display='none';
        document.getElementById('conference').style.display='none';
        document.getElementById('projector').style.display='none';
        document.getElementById('accessCard').style.display='none';
        document.getElementById('stationary').style.display='none';
        document.getElementById('training').style.display='none';
        document.getElementById('mobile').style.display='inline';

}


}
