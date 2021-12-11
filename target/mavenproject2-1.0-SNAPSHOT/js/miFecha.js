function fecha()
{
var mifecha
var mydate=new Date();
var year=mydate.getYear();
if (year < 1000)
 year+=1900;
var daym=mydate.getDay();
var month=mydate.getMonth()+1;
if (month<10)
 month="0"+month;
var daym=mydate.getDate();
if (daym<10)
 daym="0"+daym;
mifecha=daym+"/"+month+"/"+year;
document.getElementById('fecha_actual').value=mifecha.toString();
}

