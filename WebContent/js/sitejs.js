function validate1()
{
	if(document.searchform1.searchword.value=="")
	{
		alert("Please Enter A Search Keyword");
		document.searchform1.searchword.focus();
		return false;
	}
	if(document.searchform1.searchword.value!="")
	{
		if(document.searchform1.searchword.value=="Search..." || document.searchform1.searchword.value=="Search.." || document.searchform1.searchword.value=="Search." || document.searchform1.searchword.value=="Search")
		{
			alert("Please Enter A Valid Search Keyword");
			document.searchform1.searchword.focus();
			return false;
		}
		if(document.searchform1.searchword.value=="null") 
		{
			alert("Please Enter A Search Keyword");
			document.searchform1.searchword.focus();
			return false;
		}
		if(document.searchform1.searchword.value.length < 3) 
		{ 
			alert("Please Enter Atleast 3 characters");
			document.searchform1.searchword.focus();
			return false;
		}
	}
   if(document.searchform1.fromdate.value!="")
	{
		var fdt=document.searchform1.fromdate.value;
		if(isDate1(fdt)== false){
		document.searchform1.fromdate.focus();
		return false;
		}
	}
	if(document.searchform1.fromdate.value!="")
	{
				if(document.searchform1.todate.value=="")
				{           alert("Please Select To Date");
							document.searchform1.todate.focus();
							return false;
				}
	}
	if(document.searchform1.todate.value!=""){
		var fdt=document.searchform1.fromdate.value;
				var tdt=document.searchform1.todate.value;
				if(isDate1(tdt)== false){
				document.searchform1.todate.focus();
				return false;
				}
				if(datefun1(fdt,tdt,'To Date Should Be Greater Than From Date')==0){
				document.searchform1.todate.focus();
				return false;
				}
	}
}

function serchpagevalidate()
{
	if(document.searchform.searchword.value=="")
	{
		alert("Please Enter A Search Keyword");
		document.searchform.searchword.focus();
		return false;
	}
	if(document.searchform.searchword.value!="")
	{
		if(document.searchform.searchword.value=="Search..." || document.searchform.searchword.value=="Search.." || document.searchform.searchword.value=="Search." || document.searchform.searchword.value=="Search")
		{
			alert("Please Enter A Valid Search Keyword");
			document.searchform.searchword.focus();
			return false;
		}
		if(document.searchform.searchword.value=="null") 
		{
			alert("Please Enter A Search Keyword");
			document.searchform.searchword.focus();
			return false;
		}
		if(document.searchform.searchword.value.length < 3) 
		{
			alert("Please Enter Atleast 3 characters");
			document.searchform.searchword.focus();
			return false;
		}
	}
   if(document.searchform.fromdate.value!="")
	{
		var fdt=document.searchform.fromdate.value;
		if(isDate1(fdt)== false){
		document.searchform.fromdate.focus();
		return false;
		}
	}
	if(document.searchform.fromdate.value!="")
	{
				if(document.searchform.todate.value=="")
				{           alert("Please Select To Date");
							document.searchform.todate.focus();
							return false;
				}
	}
	if(document.searchform.todate.value!=""){
		var fdt=document.searchform.fromdate.value;
				var tdt=document.searchform.todate.value;
				if(isDate1(tdt)== false){
				document.searchform.todate.focus();
				return false;
				}
				if(datefun1(fdt,tdt,'To Date Should Be Greater Than From Date')==0){
				document.searchform.todate.focus();
				return false;
				}
	}
}

function getUrlVars(pageurl)
{
	//alert(pageurl);
    var vars = [], hash;
    var hashes = pageurl.slice(pageurl.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
$(document).ready(function($){$('.slideshow').live('mouseover mouseout',function(event){if(event.type=='mouseover'){$(this).children('.left-button, .right-button').stop(true,true).fadeIn();}
else{$(this).children('.left-button, .right-button').stop(true,true).fadeOut();}});var loadlinkk=window.location.hash+"";if(loadlinkk)
{var server_name=top.location.host;var newlinkid=loadlinkk.replace("#","");
if(totalPhotoCount=='' && totalPhotoCount==undefined) {
	totalPhotoCount = 1;	
}
$('#show').load('http://'+server_name+'/gallery_data.jsp?galleryId='+galleryId+'&currentPhotoNo='+newlinkid+'&totalPhotoCount='+totalPhotoCount,function(){simpleSlide({'swipe':'true'});addNotranslate();});}
$('.switch_button').live('click',function(){if($(this).hasClass('next')){var load_link=$(this).attr('rel');var pageTitle=$(this).attr('name');document.title=pageTitle;$('meta[name=Keywords]').attr('content',pageTitle);var loadlin=window.location.hash;if(loadlin)
{var hrrrrf=window.location+"";window.location=hrrrrf.replace(loadlin,"")+$(this).attr('title');}
else
window.location=window.location+$(this).attr('title');loadLink(load_link);var picno = $(this).attr('title');picno = picno.replace("#photo",'');counter_thumb = parseInt(picno)-1;
ga('send', 'pageview', load_plink);return false;}
if($(this).hasClass('prev')){var load_plink=$(this).attr('rel');var pageTitle=$(this).attr('name');document.title=pageTitle;$('meta[name=Keywords]').attr('content',pageTitle);var ploadlin=window.location.hash;if(ploadlin)
{var phref=window.location+"";window.location=phref.replace(ploadlin,"")+$(this).attr('title');}
else{window.location=window.location+$(this).attr('title');}
loadLink(load_plink);var picno = $(this).attr('title');picno = picno.replace("#photo",'');counter_thumb = parseInt(picno)+1;ga('send', 'pageview', load_plink);return false;}});$('.right-button, .left-button').live('click',function(){var action=$(this).attr('class');
ga('send', 'event', 'Slide Interactions', 'click',action, 1, {'nonInteraction': 1});
});$('#bigboy, #mini').live('click',function(){var version=$(this).attr('id');
ga('send', 'event', 'simpleSlide Downloads', 'Downloaded',version, 1, {'nonInteraction': 1});
});});function navColors(color_name){$('ul#nav_links li').removeAttr('class');$('ul#nav_links li span').each(function(){if($(this).attr('class')==color_name){$(this).parent().addClass(color_name);}});}
function loadLink(load_link){$('#show').html("<div style='width:675px;height:300px;margin-top:280px; '></div>");$('#show').css('position','relative');$('#show').animate({'left':'0px'},100,"swing",function(){$.ajax({url:''+load_link,dataType:'html',success:function(html){$('#show').html(html);
$('#show').animate({'left':'0px','opacity':'1'},300);
$('#show').css('position','static');
//simpleSlide({'swipe':'true','callback':function(){$('#show').animate({'left':'0px','opacity':'1'},300,"swing",function(){$('#show').css('position','static');});}});
var pagenextnumber=parseInt(getUrlVars(load_link)["currentPhotoNo"]);
var pagetotalnumber=parseInt(getUrlVars(load_link)["totalPhotoCount"]);
//alert(load_link + " -- pagenextnumber->"+pagenextnumber+" -- pagetotalnumber" +pagetotalnumber);
if(pagenextnumber==pagetotalnumber)
{
	$('#photooverlay').delay(7200).fadeIn(500);
	//alert(load_link);
}

$('.auto-slider').each(function(){var related_group=$(this).attr('rel');clearInterval($.autoslide);$.autoslide=setInterval("simpleSlideAction('.right-button', "+related_group+");",4000);});}});});}
function addNotranslate(){$('.codeblock').addClass('notranslate');$('code').addClass('notranslate');}
var rootdomain="http://"+window.location.hostname
function ajaxinclude(url){var page_request=false
if(window.XMLHttpRequest)
page_request=new XMLHttpRequest()
else if(window.ActiveXObject){try{page_request=new ActiveXObject("Msxml2.XMLHTTP")}
catch(e){try{page_request=new ActiveXObject("Microsoft.XMLHTTP")}
catch(e){}}}
else
return false
page_request.open('GET',url,false)
page_request.setRequestHeader("If-Modified-Since","Thu, 1 Jan 1970 00:00:00 GMT");page_request.send(null)
writecontent(page_request)}
function writecontent(page_request){if(window.location.href.indexOf("http")==-1||page_request.status==200)
document.write(page_request.responseText)}
function Open_win(uv,utwidth,utheight){var remoteWin=null;var params;params="toolbar=0,location=0,directories=0,status=0,scrollbars=1,scrolling=1,resizable=0,menubar=0,width="+utwidth+",height="+utheight+" ";remoteWin=window.open("","colorpicker",params);remoteWin.location.href=uv;}
var min=8;var max=17;function increaseFontSize(){var p=document.getElementsByTagName('p');for(i=0;i<p.length;i++){if(p[i].style.fontSize){var s=parseInt(p[i].style.fontSize.replace("px",""));}else{var s=14;}
if(s!=max){s+=1;}
p[i].style.fontSize=s+"px"}}
function decreaseFontSize(){var p=document.getElementsByTagName('p');for(i=0;i<p.length;i++){if(p[i].style.fontSize){var s=parseInt(p[i].style.fontSize.replace("px",""));}else{var s=14;}
if(s!=min){s-=1;}
p[i].style.fontSize=s+"px"}}
function resetFontSize(){var p=document.getElementsByTagName('p');for(i=0;i<p.length;i++){p[i].style.fontSize="14px"}}
function cemail(val){if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(val.value)){return(true)}
return false;}
function ajaxFunction(){var frm=document.feedback;if(frm.FNAME.value=="")
{alert("Please enter First Name");frm.FNAME.focus();return false;}
if(frm.FNAME.value!=""){var alp="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";for(var i=0;i<frm.FNAME.value.length;i++){temp=frm.FNAME.value.substring(i,i+1);if(alp.indexOf(temp)==-1){alert("First name should accept only alphabets[a-z,A-Z]");frm.FNAME.focus();return false;}}}
if(frm.LNAME.value!=""){var alp="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";for(var i=0;i<frm.LNAME.value.length;i++){temp=frm.LNAME.value.substring(i,i+1);if(alp.indexOf(temp)==-1){alert("Last name should accept only alphabets[a-z,A-Z]");frm.LNAME.focus();return false;}}}
if(frm.EMAIL.value=="")
{alert("Please enter Email Id");frm.EMAIL.focus();return false;}
if(cemail(frm.EMAIL)==false)
{alert("Please enter valid email ID");frm.EMAIL.focus();return false;}
if((frm.dob_date.value!=-1&&frm.dob_month.value==-1))
{alert("Please select Month")
frm.dob_month.focus();return false;}
if(frm.dob_month.value!=-1&&frm.dob_year.value==-1)
{alert("Please select Year")
frm.dob_year.focus();return false;}
if(frm.Location.value=="")
{alert("Please enter city");frm.Location.focus();return false;}
if(frm.Location.value!=""){var alp="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";for(var i=0;i<frm.Location.value.length;i++){temp=frm.Location.value.substring(i,i+1);if(alp.indexOf(temp)==-1){alert("City should accept only alphabets[a-z,A-Z]");frm.Location.focus();return false;}}}
if(frm.country.value!=""){var alp="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";for(var i=0;i<frm.country.value.length;i++){temp=frm.country.value.substring(i,i+1);if(alp.indexOf(temp)==-1){alert("Country should accept only alphabets[a-z,A-Z]");frm.country.focus();return false;}}}
if(frm.number.value=="")
{alert("Please Enter the  Code");frm.number.focus();return false;}
frm.submit();}
function Open(id){document.getElementById(id).style.display="block";}
function Close(id){document.getElementById(id).style.display="none";}
function myOpen(abc){document.location.href=abc.value;}












