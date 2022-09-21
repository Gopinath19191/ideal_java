function submitForm() {
    openLoader();
    document.filterForm.submit();
}

function openLoader(){
    dimensions = windowDimensions();
    myWidth = dimensions[0];
    myHeight = dimensions[1];
    myWidth = 1000;
    $('#loader').css({'width' : myWidth, 'height' : myHeight, 'opacity' : '0.9', 'z-index':'9100'});
    $('#loader').show();
}

function windowDimensions() {
    if (document.compatMode && document.compatMode != "BackCompat") {
        x = document.documentElement.clientWidth;
    } else {
        x = document.body.clientWidth;
    }
    y = document.body.clientHeight;

    return [x,y];
}