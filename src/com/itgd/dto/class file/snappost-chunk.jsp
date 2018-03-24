<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %><%@ page language="java" pageEncoding="ISO-8859-1"%><%@page import="java.util.HashMap,java.util.List,javax.net.ssl.HttpsURLConnection,org.json.simple.JSONArray,org.json.simple.JSONObject,org.json.simple.parser.JSONParser,java.io.BufferedReader,java.io.DataOutputStream,java.io.InputStreamReader,java.net.HttpURLConnection,java.net.URL,com.itgd.utils.CommonFunctions,com.itgd.exception.IndiaTodayHelperException,java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet,com.itgd.conn.Dbconnection,com.itgd.helper.SocialSharingCountHelper,java.util.HashMap,java.util.ArrayList, com.itgd.dto.ContentChunkDTO, com.itgd.helper.ContentChunkHelper, com.itgd.utils.Constants,com.itgd.helper.StoryHelper,com.itgd.dto.StoryDTO"%>
<%
int contentId = 346460;
String storySefTitle = "";
if(request.getParameter("contentId")!=null)
	contentId = Integer.parseInt(request.getParameter("contentId").toString());
if(request.getParameter("storySefTitle")!=null)
	storySefTitle = request.getParameter("storySefTitle");
ArrayList storyData = null;
storyData = (ArrayList) storyDetail(contentId, 1);
String storyStrapHeadline ="";
	if (storyData != null && storyData.size() > 0) {
for (int i = 0; i < 1; i++) {
	StoryDTO story = (StoryDTO) storyData.get(i);
	storyStrapHeadline = story.getStrapHeadline();
	if(storyStrapHeadline != null && storyStrapHeadline.length() > 0) {
int fbShareCount=0;
int twitteterShareCount=0;
int commentShareCount=0;
String fbMapShareCount=null;
String twitteterMapShareCount=null;
String commentMapShareCount=null;
String syndicationCreateddate="";
String setDate="";
int setTime=0;
int setHour=0;
int upcount=0;
int downcount=0;
int totalcount=0;
String totalCommentCount="";
String LikeDislikeStr = CommonFunctions.LikeDislike(contentId,"story");
String[] temp;
String delimiter = "-";
temp = LikeDislikeStr.split(delimiter);
upcount=Integer.parseInt(temp[0]);
downcount=Integer.parseInt(temp[1]);
totalcount=Integer.parseInt(temp[2]);
 String yesPercentage="";
 String  noPercentage="";
	%>
    
<script src="http://media2.intoday.in/indiatoday/v1/jquery1.8.js"	type="text/javascript"></script> 
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-795349-17', 'auto');
  ga('send', 'pageview');
</script>
<script type="text/javascript">
$(document).ready(function(){   
	var win_width = $(window).width();
	var panelwd = $('.comment_box').width();
	win_width = win_width - panelwd;
	win_width = win_width/2;
	//$('.comment_box').css('left', win_width);
	$(".comclick").click(function(){ 
		//$(".comment_box").removeClass("trans");
		$(".comment_box").slideUp('slow');
		//$(this).parent().parent().parent().parent().next(".comment_box").toggleClass("trans")
		$(this).parent().parent().parent().parent().next(".comment_box").slideToggle('slow');
		//var current-color = $(this).parent().parent().parent().parent()
		});
		
		$(".arrow").click(function(){
		//$(this).parent().parent().removeClass("trans")	
		$(this).parent().parent().slideUp('slow');	
		});
		
	})
	function popup(mylink, windowname)
{
if (! window.focus)return true;
var href;
if (typeof(mylink) == 'string')
   href=mylink;
else
   href=mylink.href;
window.open(href, windowname, 'width=400,height=400,scrollbars=yes');
return false;
}
</script>
<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<style>
.snappost_container{ width:656px; float:left; height:135px; font-family: 'Roboto', sans-serif;}
.snappost_container .left_snp_l{ width:75px; float:left;}
.snappost_container .right_snp_l{width:580px; float:left; position:relative}
.snappost_container .right_snp_l .hd_snp_t{ width:100%; float:left}
.snappost_container .right_snp_l .hd_snp_t h3{ margin:0 0 16px 0;padding:0; margin-top:2px;display:block; font-size:27px; color:#363636;}
.sprite_nw{ background:url(http://media2.intoday.in/indiatoday/images/homenew/Sprit-IT-Home.png) no-repeat;}
.img_snp_pt{ display:block; background-position:-268px -684px; width:63px; height:60px;}

.agrr_disa_block{}
.agrr_disa_block .agree_btn{ cursor:pointer;width:auto;text-align:center; border-radius:5px; float:left; padding:5px 15px 8px; background:#EBEBEB; color:#111;}
.agrr_disa_block .disagree_btn{cursor:pointer; margin-left:20px; width:auto;text-align:center; border-radius:5px; float:left; padding:5px 15px 8px;  background:#EBEBEB; color:#111;}
.agrr_disa_block .thumsup{ width:25px; height:25px; display:inline-block; background-position:-387px -342px; float:left}
.agrr_disa_block .thumsdown{ width:25px; height:25px; display:inline-block; background-position:-421px -347px; float:left}
.agrr_disa_block .ag_txt{ position:relative; top:-4px; text-transform:uppercase; color:#111; text-decoration:none;}

.more_from_snp{ position:relative; top:3px; margin-left:10px;font-size:16px; color:#363636}
.more_from_snp a{ color:#949494; text-decoration:none; text-transform:uppercase; font-size:25px; position:relative; top:4px;}
.parent-bar{width: 113px; height: 28px; float:right; }
.parent-bar .agreedata{background: #fff none repeat scroll 0 0;height: 27px;border: 1px solid #8c8c8c;border-radius: 3px;display: block;font-size: 18px;position: relative;text-align: center;padding: 1px;width: 113px;box-shadow: 1px 1px 2px #FFF;line-height: 27px;}
.parent-bar .disagreedata{background: #fff none repeat scroll 0 0;height: 27px;border: 1px solid #8c8c8c;border-radius: 3px;display: block;font-size: 18px;position: relative;text-align: center;padding: 1px;width: 113px;box-shadow: 1px 1px 2px #FFF;line-height: 27px;}
.parent-bar  .bar_d{  background: #c0c0c0 none repeat scroll 0 0;border-radius: 3px;display: block;height: 27px;opacity: 0.5;z-index: 1; position: relative;top: -29px;}
.agree_btn .parent_bar{ display:none}
.disagree_btn .parent-bar{ display:none}
.commenttext {
    clear: both;
    color: #000 !important;
    float: left;
    font-family: roboto;
    font-size: 24px;
    left: -19px;
    padding: 20px;
    position: absolute;
    text-align: center;
    top: 80px;
    z-index: 2;
}
.parent-bar {
    display: none;
    float: left;
    height: 28px;
    width: 113px;
}

.agree1, .disagree1 {
    text-align: center;
    padding-top: 6px;
   width: 100px;
   display:block;
}
@media (max-width: 640px){
.snappost_container{ width:100%; }
.snappost_container .left_snp_l{ width:75px; }
.snappost_container .right_snp_l{width:75%;}
.snappost_container .right_snp_l .hd_snp_t h3{ font-size: 25px;}
.agrr_disa_block{ }
.agrr_disa_block .agree_btn{clear:both;display:block;}
.agrr_disa_block .disagree_btn{ clear:both;display:block; margin:10px 0;}
.more_from_snp{ display:block; clear:both;}
.more_from_snp{ top:0; margin-left:0;	}
}
@media (max-width: 640px) and (orientation: landscape){
.agrr_disa_block .agree_btn{ float:left;  margin-top:10px;}
.agrr_disa_block .disagree_btn{float:left;  clear: none;display: inline; margin-left:10px;}
}
</style>

	<script>
function trim(field)
{
while(field.charAt(field.length-1)==" "){
		field=field.substring(0,field.length-1);
	}
	while(field.charAt(0)==" "){
		field=field.substring(1,field.length);
	}
	return field;
}
var likedislikeaction = '0';
var actn='';
var imgr='';
var storytext='';
var introtext='';
function handleHttpResponse_likedislike()
{
	var urltoPass = "<%=Constants.SITE_URL+CommonFunctions.storyURL(storySefTitle, 1, contentId) %>";
	urltoPass=trim(urltoPass);
	if(urltoPass.length==0){
		urltoPass = url;
	}
	if (http2.readyState == 4)
	{
		var results = http2.responseText;
		var arry = results.split('|'); 	
		var upcnt=	(arry[0]);
		var dncnt=(arry[1]);
		var cmnt=(arry[2]);
		var ccount=(arry[3]);
		
		var totalccount;
		totalccount=parseInt(upcnt)+parseInt(dncnt);
		//alert(totalccount);
		var upcountpercentage=Math.floor((parseInt(upcnt)*100)/ parseInt(totalccount))
		
		var downcountpercentage=100-upcountpercentage;
		
		
		if (http2.status == 200) {
			
			////if(!results.match('already')) {
				if(likedislikeaction==1) {					
					ga('send', 'event', 'LIKE', 'click',urltoPass, 1, {'nonInteraction': 1});
					shareFB('Cast your Opinion :"'+storytext+'" ' ,actn,imgr,upcountpercentage +'% of people agreeing with the post - :'+introtext );
				}
				if(likedislikeaction==2) {					
					ga('send', 'event', 'DISLIKE', 'click',urltoPass, 1, {'nonInteraction': 1});
					shareFB('Cast your Opinion :"'+storytext+'" ' ,actn,imgr,downcountpercentage +'% of people disagreeing with the post- :'+introtext );
				} 
			//}		
				
			document.getElementById("upcount"+ccount).innerHTML = upcnt;
			document.getElementById("downcount"+ccount).innerHTML = dncnt;	
			document.getElementById("upcount"+ccount).style.display = "none";
			document.getElementById("downcount"+ccount).style.display = "none";
			document.getElementById("ag"+ccount).style.display = "none";
			document.getElementById("dis"+ccount).style.display = "none";			
			document.getElementById("upcountper"+ccount).style.display = "inline-block";
			document.getElementById("downcountper"+ccount).style.display = "inline-block";
			document.getElementById("upcountper"+ccount).innerHTML = upcountpercentage +"%";
			document.getElementById("downcountper"+ccount).innerHTML = downcountpercentage +"%"
			$('.disagreeclick').children('.parent-bar').css('display', 'block');
			$('.disagreeclick').children('.disagree1').fadeOut('slow');
			$('.disagreeclick').children('.disagreedata').fadeIn('slow');
			$('.disagreeclick').children('.parent-bar').children('.bar').css('width', downcountpercentage+"%");
			$('.disagreeclick').children('.disagreedata').css('display','inline-block');
			$('.agreeclick').children('.parent-bar').css('display', 'block');
			$('.agreeclick').children('.agree1').fadeOut('slow');
			$('.agreeclick').children('.agreedata').fadeIn('slow');		
			$('.agreeclick').children('.parent-bar').children('.bar').css('width', upcountpercentage+"%");
			$('.agreeclick').children('.agreedata').css('display','inline-block');

		if(results.match('already')) {
			document.getElementById("commenttext"+ccount).innerHTML = cmnt;
			}			
		} else {                 
			//alert('There was a problem retrieving the data');
		} 
	}
}
function getHTTPObject_2()
{
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		xmlhttp = new XMLHttpRequest();
	}
		else if (window.ActiveXObject)
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		if (!xmlhttp)
		{
			xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
	}
	return xmlhttp;
}

var http2 = getHTTPObject_2();
function changeRating_story(storytitle,contentId,content_type,action,url1,image,kicker)
{ 
	//alert("aa");
	upc = document.getElementById('upcount<%=contentId%>').firstChild.nodeValue;
	downc = document.getElementById('downcount<%=contentId%>').firstChild.nodeValue;
	totalc = parseInt(upc) +parseInt(downc);	
	
	likedislikeaction = action;
	url="<%=Constants.SITE_URL%>rating_data.jsp?content_id="+contentId+"&content_type="+content_type+"&action="+action+"&upcount="+upc+"&downcount="+downc+"&totalcount="+totalc;
	//alert(url);
	actn = url1;
	imgr = image;
	storytext=storytitle;
	introtext=kicker;
	http2.open("GET", url, true); 
	http2.onreadystatechange = handleHttpResponse_likedislike;
	http2.send(null);
}

</script>

<div id="fb-root"></div>


<div class="snappost_container">
  <div class="left_snp_l"> <span class="img_snp_pt sprite_nw"></span> </div>
  <div class="right_snp_l">
    <div class="hd_snp_t">
      <h3><%=storyStrapHeadline%></h3>
    </div>
    <div class="agrr_disa_block"> 
    
    
    <div class="agree_btn">
    
    <span class="thumsup sprite_nw"></span>
    <!--<span class="ag_txt">Agree</span>
    	<span class="parent_bar">
       <span class="agreedata"></span>
        <span class="bar_d"></span>-->
        
        <a class="agreeclick ag_txt" href="javascript:changeRating_story('<%=story.getTitle().replaceAll("'","").replaceAll("\\<.*?>","")%>','<%=story.getId()%>','story','1','<%=Constants.SITE_URL%>snappost/%23snap<%=story.getId() %>','<%=Constants.KICKER_IMG_PATH+story.getExtraLargeImage()%>','<%=story.getShortDescription().replaceAll("'","").replaceAll("\\<.*?>","") %>')" ><em  class="agree bg" >
     
     <span id="upcount<%=story.getId()%>" style="display:none;"><%=upcount%></span></em>
     
     <span class="agree1" id="ag<%=story.getId()%>">AGREE</span>
     
     <span class="parent-bar parent_bar"><span class="agreedata" id="upcountper<%=story.getId()%>"><%=yesPercentage%></span><span class="bar bar_d"></span></span></a>
         
        </span>
    </div>
    
    <div class="disagree_btn">
    <span class="thumsdown sprite_nw"></span>
    <!--<span class="ag_txt">Disagree</span>
    
    <span class="parent_bar">
        <span class="disagreedata"></span>
        <span class="bar_d"></span>
        </span>-->
        
        <a class="disagreeclick ag_txt" href="javascript:changeRating_story('<%=story.getTitle().replaceAll("'","").replaceAll("\\<.*?>","")%>','<%=story.getId()%>','story','2','<%=Constants.SITE_URL%><%=story.getId() %>','<%=Constants.KICKER_IMG_PATH+story.getExtraLargeImage()%>','<%=story.getShortDescription().replaceAll("'","").replaceAll("\\<.*?>","") %>')"><em class="disagree bg" ><span id="downcount<%=story.getId()%>" style="display:none;"><%=downcount%></span></em>
      
      <span class="disagree1" id="dis<%=story.getId()%>">DISAGREE</span>
      
      <span class="parent-bar parent_bar"><span class="disagreedata" id="downcountper<%=story.getId()%>"><%=noPercentage%></span><span class="bar bar_d"></span></span></a>
      
        <span class="commenttext" id="commenttext<%=story.getId()%>"></span>
        
    </div>
    
    <span class="more_from_snp">More from <a href="http://alpha.indiatoday.intoday.in/snappost/" title="Snap post"><strong>Snap</strong> post</a></span>
    </div>
  </div>
</div>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
window.fbAsyncInit = function() {
	FB.init({ appId: '613580381995012',
        status: true, 
        cookie: true,
        xfbml: true,
        oauth: true
		});
};
(function() {
	var e = document.createElement('script'); e.async = true;
	e.src = document.location.protocol 
		+ '//connect.facebook.net/en_US/all.js';
	document.getElementById('fb-root').appendChild(e);
}());
function shareFB(texttosend,pageurl,image,introtext){

//alert(texttosend);
//alert(pageurl);
//alert(image);
//alert(introtext);

	FB.ui({method: 'feed',
			name: texttosend,
			link: pageurl,
			description:introtext,
			picture: image
		});
		return false;
}
</script>
<%}} } %>
<%!
public static ArrayList storyDetail(int storyId, int publishedStatus) throws Exception
	{
    	//System.out.println("Story "+storyId + "----ststus--"+publishedStatus);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ArrayList storyData = new ArrayList();
		String storyStatus = "";
		if(publishedStatus==1)
			storyStatus = " and c.state=1 ";
		
	    int primaryCategoryLength = 0;
	    String primaryCategory = "0";
	    String primaryCategoryId[] = null;
		
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT c.strap_headline, c.story_highlights_flag,c.story_highlights,c.comment_question,c.id,c.reporter_twitter_handle,c.ismedium,c.id, c.issueid, c.primary_category, c.title, c.title_alias, title_magazine, c.sef_url, c.byline, c.city, c.courtesy, " +
	    			"c.images as imagepath, c.introtext, c.fulltext, c.strap_headline, c.metadesc, c.metakey, c.metatags, c.story_syndication, " +
	    			"date_format( c.created,'%M %e, %Y' ) AS crt, date_format( c.modified,'%M %e, %Y' ) AS mdate, date_format( c.created,'%Y-%m-%d' ) AS snd, c.article_icon, c.article_icon_img, " +
	    			"date_format( c.created,'%H:%i') AS ctime, date_format( c.modified,'%H:%i') AS mtime,  c.quotes, c.blurbs,	c.tables, c.article_icon, " +
	    			"c.article_icon_img, c.kicker_image, c.large_kicker_image, c.large_kicker_image_width, c.kicker_image_caption, c.large_kicker_image_alt_text, c.indiatoday_expert, c.google_standout, " +
	    			"c.kicker_image, c.mobile_image, c.commentbox_flag, c.extralarge_image, c.byline_modified_by,c.content_url,c.is_external,c.external_url  " +
	    			"FROM jos_content c, jos_article_section a WHERE c.id = ? and c.content_type='0' and a.article_id=c.id "+storyStatus+" group by c.id";
	    	//System.out.println("Story Detail ->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, storyId);
            //pstmt.setInt(2, storyStatus);
            rs = pstmt.executeQuery ();
	    	//System.out.println("Story Detail 111->"+sql);
			while (rs.next ()){
		        StoryDTO sDTO = new StoryDTO();
		        sDTO.setId(rs.getInt("id"));
		    	//System.out.println("Story Detail ->"+rs.getInt("id"));
		    	sDTO.setStrapHeadline(rs.getString("strap_headline"));
		        sDTO.setLongDescription(rs.getString("fulltext"));
		        sDTO.setCommentQuestion(rs.getString("comment_question"));
		        sDTO.setTitleAlias(rs.getString("title_alias"));		       
		        sDTO.setTitleMagazine(rs.getString("title_magazine") == null ? "" : rs.getString("title_magazine"));		       
		        sDTO.setTitle(rs.getString("title"));
		        sDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline")); 
		        sDTO.setBylineModifiedBy(rs.getString("byline_modified_by") == null ? "" : rs.getString("byline_modified_by")); 
		        sDTO.setCity(rs.getString("city"));
		        sDTO.setShortDescription(rs.getString("introtext"));
		        sDTO.setStoryImage(rs.getString("imagepath") == null ? "" : rs.getString("imagepath"));
		        sDTO.setSefTitle(rs.getString("sef_url"));
		        sDTO.setCreatedDate(rs.getString("crt") == null ? "" : rs.getString("crt"));
		        sDTO.setUpdatedDate(rs.getString("mdate") == null ? "" : rs.getString("mdate"));
				sDTO.setCreatedTime(rs.getString("ctime") == null ? "" : rs.getString("ctime"));
				sDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));
		        sDTO.setSyndication(rs.getInt("story_syndication"));
		        sDTO.setSyndicationDate(rs.getString("snd") == null ? "" : rs.getString("snd"));
		        sDTO.setMetaKeyword(rs.getString("metakey"));
		        sDTO.setMetaDescription(rs.getString("metadesc"));
		        sDTO.setCourtesy(rs.getString("courtesy") == null ? "" : rs.getString("courtesy"));
		        sDTO.setBlurb(rs.getString("blurbs") == null ? "" : rs.getString("blurbs"));
		        sDTO.setTable(rs.getString("tables") == null ? "" : rs.getString("tables"));
		        sDTO.setQuote(rs.getString("quotes") == null ? "" : rs.getString("quotes"));
		        sDTO.setStoryIcon(rs.getInt("article_icon"));
		        sDTO.setStoryIconImage(rs.getString("article_icon_img") == null ? "" : rs.getString("article_icon_img"));
		        sDTO.setLargeKickerImage(rs.getString("large_kicker_image")== null?"":rs.getString("large_kicker_image"));
		        sDTO.setLargeKickerImageWidth(rs.getString("large_kicker_image_width") == null ? "0" : rs.getString("large_kicker_image_width"));
		        sDTO.setLargeKickerImageCaption(rs.getString("kicker_image_caption") == null ? "" : rs.getString("kicker_image_caption"));
		        sDTO.setLargeKickerImageAltText(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
	        	sDTO.setPrimaryCategory(rs.getString("primary_category") == null ? "0" : rs.getString("primary_category"));
		        primaryCategory = rs.getString("primary_category");
	        	sDTO.setIssueId(rs.getInt("issueid"));
		        sDTO.setMetaTags(rs.getString("metatags") == null ? "" : rs.getString("metatags"));
		        sDTO.setExpertComment(rs.getString("indiatoday_expert") == null ? "" : rs.getString("indiatoday_expert"));
		        sDTO.setGoogleStandout(rs.getInt("google_standout"));
		        sDTO.setKickerImage(rs.getString("kicker_image")== null?"":rs.getString("kicker_image"));
		        sDTO.setSmallImage(rs.getString("mobile_image")== null?"":rs.getString("mobile_image"));
	        	sDTO.setCommentboxFlag(rs.getInt("commentbox_flag"));
		        sDTO.setExtraLargeImage(rs.getString("extralarge_image")== null?"":rs.getString("extralarge_image"));
				sDTO.setContentUrl(rs.getString("content_url")== null?"":rs.getString("content_url"));
		        sDTO.setExternalUrl(rs.getString("external_url")== null?"":rs.getString("external_url"));
		        sDTO.setIsExternal(rs.getInt("is_external"));
		        sDTO.setMedium(rs.getInt("ismedium"));
		        sDTO.setTwitterHandle(rs.getString("reporter_twitter_handle"));
		        sDTO.setStoryHighlightsFlag(rs.getInt("story_highlights_flag"));
		        sDTO.setStoryHighlights(rs.getString("story_highlights") == null ? "" : rs.getString("story_highlights"));
	        	
		    	//System.out.println("Story 1"+primaryCategory);
		    	try { 
			        if(primaryCategory != null && primaryCategory.trim().length() > 0 ){
				    	//System.out.println("Story 2");
					    if((primaryCategory.indexOf("#") > 0) && (primaryCategory.lastIndexOf("#") < primaryCategory.trim().length())) {
					    	primaryCategoryId = primaryCategory.split("#");
					    	primaryCategoryLength = primaryCategoryId.length;
					    	//System.out.println("PCAT length - "+pcatlength);
					    } else {
					    	primaryCategoryId = new String[1];
					    	primaryCategoryId[0] = primaryCategory;
					    }
				    	primaryCategoryLength = primaryCategoryId.length;
				    	String sql1 = "select js.id as jsid, js.title as jstitle, js.sef_url as jssefurl, js.template_path as jspath, js.header_image, js.middlenav_path, ";
				    	if(primaryCategoryLength==1) {
							sql1 = sql1 + " js.topnav_path, js.rightnav_path, js.bottomnav_path, js.leftnav_path " +
									"from jos_sections js where js.id="+primaryCategoryId[0];
				    	}
				    	if(primaryCategoryLength==2) {
							sql1 = sql1 + " jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.topnav_path, jc.rightnav_path, jc.bottomnav_path, jc.leftnav_path, jc.template_path as jcpath " +
									"from jos_categories jc, jos_sections js where jc.id="+primaryCategoryId[1]+" and jc.section=js.id";
				    	}
				    	if(primaryCategoryLength==3) {
							sql1 = sql1 + " jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.template_path as jcpath, jsc.title as jsctitle, jsc.id as jscid, jsc.sef_url as jscsefurl, jsc.topnav_path, jsc.rightnav_path, jsc.bottomnav_path, jsc.leftnav_path, jsc.template_path as jscpath " +
									"from jos_subcategories jsc, jos_sections js, jos_categories jc where jsc.id="+primaryCategoryId[2]+" and  jsc.category=jc.id and jsc.section=js.id";
				    	}
				    	if(primaryCategoryLength==4) {
							sql1 = sql1 + " jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.template_path as jcpath, jsc.title as jsctitle, jsc.id as jscid, jsc.sef_url as jscsefurl, jsc.template_path as jscpath, jssc.title as jssctitle, jssc.id as jsscid, jssc.sef_url as jsscsefurl, jssc.topnav_path, jssc.rightnav_path, jssc.bottomnav_path, jssc.leftnav_path, jssc.template_path as jsscpath " +
									"from jos_sub_subcategories jssc, jos_sections js, jos_categories jc, jos_subcategories jsc where jssc.id="+primaryCategoryId[3]+" and jssc.section=js.id and jssc.category=jc.id and jssc.sub_category=jsc.id";
				    	}
				    	//System.out.println("Story Primary Detail ->"+sql1);
	
				    	pstmt1 = connection.prepareStatement(sql1);
				    	rs1 = pstmt1.executeQuery();
						while (rs1.next ()){
							//System.out.println("primaryCategoryLength ->"+primaryCategoryLength);
							sDTO.setSectionId(rs1.getInt("jsid"));
				        	sDTO.setSectionTitle(rs1.getString("jstitle"));
				        	sDTO.setSectionSefTitle(rs1.getString("jssefurl"));
							sDTO.setSectionPageURL(rs1.getString("jspath") == null ? "" : rs1.getString("jspath"));		
							sDTO.setHeaderImage(rs1.getString("header_image") == null ? "" : rs1.getString("header_image"));						
							sDTO.setMiddleNavigationPath(rs1.getString("middlenav_path") == null ? "" : rs1.getString("middlenav_path"));		
				        	
				        	if(primaryCategoryLength >= 2) {
					        	sDTO.setCategoryId(rs1.getInt("jcid"));
					        	sDTO.setCategoryTitle(rs1.getString("jctitle"));
					        	sDTO.setCategorySefTitle(rs1.getString("jcsefurl"));
								sDTO.setCategoryPageURL(rs1.getString("jcpath") == null ? "" : rs1.getString("jcpath"));		
					    	} 
				        	if(primaryCategoryLength >= 3) {
					        	sDTO.setSubCategoryId(rs1.getInt("jscid"));
					        	sDTO.setSubCategoryTitle(rs1.getString("jsctitle"));
					        	sDTO.setSubCategorySefTitle(rs1.getString("jscsefurl"));
								sDTO.setSubCategoryPageURL(rs1.getString("jscpath") == null ? "" : rs1.getString("jscpath"));		
					    	} 
				        	if(primaryCategoryLength == 4) {
					        	sDTO.setSubSubCategoryId(rs1.getInt("jsscid"));
					        	sDTO.setSubSubCategoryTitle(rs1.getString("jssctitle"));
					        	sDTO.setSubSubCategorySefTitle(rs1.getString("jsscsefurl"));
								sDTO.setSubSubCategoryPageURL(rs1.getString("jsscpath") == null ? "" : rs1.getString("jsscpath"));		
					    	}
				        	sDTO.setTopNavigationPath(rs1.getString("topnav_path") == null ? "" : rs1.getString("topnav_path"));
				        	sDTO.setRightNavigationPath(rs1.getString("rightnav_path") == null ? "" : rs1.getString("rightnav_path"));
				        	sDTO.setBottomNavigationPath(rs1.getString("bottomnav_path") == null ? "" : rs1.getString("bottomnav_path"));
				        	sDTO.setLeftNavigationPath(rs1.getString("leftnav_path") == null ? "" : rs1.getString("leftnav_path"));
						}
			        }
				} catch (Exception e) {
					System.out.println("StoryHelper articleDetail Primary Category Detail Exception is :" + e.toString());
				}
		        
		    	pstmt1 = null;
		    	rs1 = null;
		    	try {
			        if(rs.getInt("issueid") > 0){
				    	String sql2 = "SELECT id as issueid, title as issuetitle, sef_url as issuesefurl " +
				    			"FROM jos_magazine_categories where id="+rs.getInt("issueid");
				    	//System.out.println("Story Issue Detail ->"+sql2);
				    	pstmt1 = connection.prepareStatement(sql2);
				    	rs1 = pstmt1.executeQuery();
						while (rs1.next ()){
				        	sDTO.setIssueTitle(rs1.getString("issuetitle") == null ? "" : rs1.getString("issuetitle"));
				        	sDTO.setIssueSefTitle(rs1.getString("issuesefurl") == null ? "" : rs1.getString("issuesefurl"));
						}
			        }
				} catch (Exception e) {
					System.out.println("StoryHelper articleDetail Issue Detail Exception is :" + e.toString());
				}
		        sDTO.setPrimaryCategoryLength(primaryCategoryLength); 
		        storyData.add(sDTO);
			}


		} catch (Exception e) {
			System.out.println("StoryHelper articleDetail Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs1!=null)
				rs1.close();
			if(pstmt1!=null)
				pstmt1.close();
			if(connection!=null)
				connection.close();
		}
		return storyData;	
	
	}
%>