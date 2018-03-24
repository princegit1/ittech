<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%@ page language="java"
	import="com.itgd.utils.Constants,com.itgd.parser.JsonParser,com.itgd.parser.JsonRec,com.itgd.parser.ItemRec,com.itgd.parser.ItemFeed,java.util.*,java.text.StringCharacterIterator,java.text.CharacterIterator,com.itgd.parser.AuthorList,java.text.SimpleDateFormat,java.util.Date"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.regex.Pattern,java.util.*,java.net.URLEncoder,org.apache.commons.codec.binary.Base64,java.util.regex.Matcher,com.itgd.utils.CommonFunctions"%>
<%@include file="global.jsp"%>
<%
	int currentstoryId = 0;
	int currentStoryPageNo = 1;
	String pagePath = null;
	try {
		if (request.getAttribute("currentstoryId") != null)
			currentstoryId = (Integer) request
					.getAttribute("currentstoryId");
		if (request.getAttribute("currentStoryPageNo") != null)
			currentStoryPageNo = (Integer) request
					.getAttribute("currentStoryPageNo");
		pagePath = request.getAttribute("newPagePath").toString();
	} catch (Exception e) {
		response.sendRedirect(Constants.PAGE_NOT_FOUND);
		return;
	}
%>
<cache:cache key='<%=Constants.cacheURL + "story/1/" + currentstoryId%>'
	scope="application " time="0" refresh="t">
	<%
		String storyPageJson = property.getProperty("storyPageJSON")
					.trim() + currentstoryId + "/json";
	%>
	<%=storyPageJson%>       
	<%
		JsonParser StoryJsonObj = new JsonParser();
			//JsonRec storyPageData=StoryJsonObj.callService(property.getProperty("servicePath").trim(),storyPageJson);
			JsonRec storyPageData = StoryJsonObj.getMeText(storyPageJson);
			List Story = storyPageData.getItems().get(0).getFeed();
			ItemFeed storyItem = (ItemFeed) Story.get(0);
			String VuukleAuthorData = "";
			String storyBody = "";
			String[] storyBodyDisplay = null;
			int checkPagebreak = 0;
			storyBody = storyItem.getFullText();
			if (storyBody.indexOf("mospagebreak") > 0)
				checkPagebreak = 1;
			//JsonRec topContentsData=StoryJsonObj.callService(property.getProperty("servicePath").trim(),property.getProperty("topBarJSON").trim());
			JsonRec topContentsData = StoryJsonObj.getMeText(property
					.getProperty("topBarJSON").trim());
			String HotGadgets = topContentsData.getItems().get(0).getFeed()
					.get(0).getIntrotext();
			List hp_second_top = topContentsData.getItems().get(1)
					.getFeed();
			int hp_second_topSize = hp_second_top.size();
			List bylineData = null;
			String gaAuthorData = "";
			String gaAuthorDatacomma = "";
			bylineData = storyPageData.getItems().get(0).getFeed().get(0)
					.getAuthorList();
			//List Story=storyPageData.getItems().get(0).getFeed();
			//bylineData = (ArrayList) getBylineData(currentstoryId);
			if (bylineData != null && bylineData.size() > 0) {
				for (int bylineDataID = 0; bylineDataID < bylineData.size(); bylineDataID++) {
					AuthorList authoList = (AuthorList) bylineData
							.get(bylineDataID);
					if (authoList.getAuthorName() != null
							&& !authoList.getAuthorName().equals("")) {
						gaAuthorData += gaAuthorDatacomma
								+ authoList.getAuthorName();
						gaAuthorDatacomma = ",";
					}
				}
			} else {
				if (storyItem.getByline() != null
						&& !storyItem.getByline().equals("")) {
					gaAuthorData = storyItem.getByline();
				}
			}
	%>
	<!DOCTYPE html>
	<html lang="en">
<head>
<base href="<%=Constants.SITE_URL%>/technology/" />
<link rel="amphtml"
	href="http://m.indiatoday.in/lite/<%=storyItem.getContent_url()%>" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>
	<%
		out.print((storyItem.getTitle() + " : "
					+ storyItem.getCat_name() + ", News - India Today")
					.replaceAll("\\<.*?>", ""));
	%>
</title>
<meta name="description" content="<%=storyItem.getMetadesc()%>" />
<meta name="news_keywords" content="<%=storyItem.getMetakey()%>" />
<script type="application/ld+json">
                {
                "@context": "http://schema.org",
 
                "@type": "NewsArticle",
                "mainEntityOfPage": "<%=Constants.SITE_URL
						+ pagePath.substring(1, pagePath.length())%>",
 
    "headline": "<%=storyItem.getTitle()%>",
    "datePublished": "<%=storyItem.getSnd()%>T<%=storyItem.getCtime()%>:00+05:30",
    "dateModified": "<%java.util.Date date = new Date(storyItem.getMdate());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String format = formatter.format(date);
				out.print(format);%>T<%=storyItem.getMtime()%>:00+05:30",
                "description": <%=storyItem.getMetadesc()%>,
                "author": {
                "@type": "Person",
                "name": "<%=storyItem.getByline()%>"
                },
                "publisher": {
                "@type": "Organization",
                "name": "India Today",
                "logo": {
                "@type": "ImageObject",
                "url": "http://media2.intoday.in/indiatoday/defaulimages/India-Today.jpg",
"width": 600,
                "height": 60
               
                }
                },
                "image": {
                "@type": "ImageObject",
                "url": "http://media2.intoday.in/indiatoday/images/stories/",
 
                "width": 696,
                "height": 404
                }
                }
                </script>
<meta property="fb:app_id" content="613580381995012" />
<meta property="og:type" content="article" />
<meta property="og:title" content="<%=storyItem.getTitle()%>" />
<meta property="og:url"
	content="<%=Constants.SITE_URL
						+ pagePath.substring(1, pagePath.length())%>" />
<meta name="twitter:card" content="summary" />
<meta name="twitter:site" content="@IndiaTodayTech" />
<meta name="twitter:creator" content="@IndiaTodayTech" />
<meta name="twitter:url"
	content="<%=Constants.SITE_URL + storyItem.getContent_url()%>" />
<meta name="twitter:title"
	content='<%=storyItem
						.getTitlealias()
						.replace("{table}", "")
						.replace("mospagebreak", "")
						.replace("{", "")
						.replace("}", "")
						.replace("mosimage", "")
						.replace("blurb", "")
						.replace("quote", "")
						.replace("funfacts", "")
						.replaceAll(
								"<[^/bp][^>]*>|<p[a-z][^>]*>|<b[^r][^>]*>|<br[a-z][^>]*>|</[^bp]+>|</p[a-z]+>|</b[^r]+>|</br[a-z]+>",
								"").replaceAll("</?a[^>]*>", "")
						.replaceAll("</?font[^>]*>", "")
						.replaceAll("<br /><br /><br /><br />", "<br /><br />")
						.replaceAll("<br /><br /><br />", "<br /><br />")
						.replaceAll("\"", "-")%>' />
<meta name="twitter:description"
	content='<%=storyItem
						.getMetadesc()
						.replace("{table}", "")
						.replace("mospagebreak", "")
						.replace("{", "")
						.replace("}", "")
						.replace("mosimage", "")
						.replace("blurb", "")
						.replace("quote", "")
						.replace("funfacts", "")
						.replaceAll(
								"<[^/bp][^>]*>|<p[a-z][^>]*>|<b[^r][^>]*>|<br[a-z][^>]*>|</[^bp]+>|</p[a-z]+>|</b[^r]+>|</br[a-z]+>",
								"").replaceAll("</?a[^>]*>", "")
						.replaceAll("</?font[^>]*>", "")
						.replaceAll("<br /><br /><br /><br />", "<br /><br />")
						.replaceAll("<br /><br /><br />", "<br /><br />")
						.replaceAll("\"", "-")%>' />
<meta name="twitter:app:name:iphone" content="India Today Live" />
<meta name="twitter:app:id:iphone" content="510733452" />
<meta name="twitter:app:url:iphone"
	content="https://itunes.apple.com/us/app/india-today-live/id510733452?ls=1&amp;mt=8" />
<meta name="twitter:app:name:ipad" content="India Today Live for iPad" />
<meta name="twitter:app:id:ipad" content="560404098" />
<meta name="twitter:app:url:ipad"
	content="https://itunes.apple.com/us/app/india-today-live-for-ipad/id560404098?ls=1&amp;mt=8" />
<meta name="twitter:app:name:googleplay" content="India Today" />
<meta name="twitter:app:id:googleplay" content="com.indiatoday" />
<meta name="twitter:app:url:googleplay"
	content="https://play.google.com/store/apps/details?id=com.indiatoday&amp;hl=en" />
<%
	if (storyItem.getLarge_kicker_image() != null
				&& !storyItem.getLarge_kicker_image().equals("")) {
%>
<meta name="twitter:image"
	content="<%=Constants.STORY_IMG_PATH
							+ storyItem.getLarge_kicker_image()%>" />
<meta property="og:image"
	content="<%=Constants.STORY_IMG_PATH
							+ storyItem.getLarge_kicker_image()%>" />
<link rel="image_src"
	href="<%=Constants.STORY_IMG_PATH
							+ storyItem.getLarge_kicker_image()%>" />
<%
	} else {
%>
<meta name="twitter:image"
	content="http://media2.intoday.in/indiatoday/it-logo-200x200-white.gif" />
<meta property="og:image"
	content="http://media2.intoday.in/indiatoday/it-logo-200x200-white.gif" />
<link rel="image_src"
	href="http://media2.intoday.in/indiatoday/it-logo-200x200-white.gif" />
<%
	}
%>
<script type='text/javascript'>var _sf_startpt=(new Date()).getTime()</script>
<!-- Begin comScore Tag -->
<script type='text/javascript'>
  var _comscore = _comscore || [];
  _comscore.push({ c1: "2", c2: "8549097" });
  (function() {
    var s = document.createElement("script"), el = document.getElementsByTagName("script")[0];
    s.src = (document.location.protocol == "https:" ? "https://sb" : "http://b") + ".scorecardresearch.com/beacon.js";
    el.parentNode.insertBefore(s, el);
  })();
</script>
<noscript>
	<img
		src="http://b.scorecardresearch.com/p?c1=2&amp;ac2=8549097&amp;cv=2.0&amp;cj=1" />
</noscript>
<!-- End comScore Tag -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-795349-17', 'auto');
  <%if (gaAuthorData != null && !gaAuthorData.equals("")
						&& !gaAuthorData.equals("null")) {%>
  ga('set', 'dimension1', '<%=gaAuthorData.trim()%>');
  <%}%>
  ga('set', 'dimension2', '<%=storyItem.getCat_name()%>');
  ga('set', 'dimension3', 'Technology');
  ga('send', 'pageview');
</script>
<!-- IndiaToday Group - IndiaToday - RTA script -->
<script type='text/javascript'>
var crtg_nid = '3197';
var crtg_cookiename = 'crtg_rta';
var crtg_varname = 'crtg_content';
function crtg_getCookie(c_name){ var i,x,y,ARRCookies=document.cookie.split(";");for(i=0;i<ARRCookies.length;i++){x=ARRCookies[i].substr(0,ARRCookies[i].indexOf("="));y=ARRCookies[i].substr(ARRCookies[i].indexOf("=")+1);x=x.replace(/^\s+|\s+$/g,"");if(x==c_name){return unescape(y);} }return'';}
var crtg_content = crtg_getCookie(crtg_cookiename);
var crtg_rnd=Math.floor(Math.random()*99999999999);
(function(){
var crtg_url=location.protocol+'//rtax.criteo.com/delivery/rta/rta.js?netId='+escape(crtg_nid);
crtg_url +='&cookieName='+escape(crtg_cookiename);
crtg_url +='&rnd='+crtg_rnd;
crtg_url +='&varName=' + escape(crtg_varname);
var crtg_script=document.createElement('script');crtg_script.type='text/javascript';crtg_script.src=crtg_url;crtg_script.async=true;
if(document.getElementsByTagName("head").length>0)document.getElementsByTagName("head")[0].appendChild(crtg_script);
else if(document.getElementsByTagName("body").length>0)document.getElementsByTagName("body")[0].appendChild(crtg_script);
})();
</script>
<script type="text/javascript">
	var primaryCategoryJS = '<%=storyItem.getPrimary_category()%>';
	if(primaryCategoryJS.indexOf("#") > 0) {
		if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) == "230"){ 
			window.location.replace( "<%="http://indiatoday.intoday.in/auto/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
			}else if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) == "204") {
			window.location.replace( "<%="http://indiatoday.intoday.in/education/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
			}else if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) == "340") {
			window.location.replace( "<%="http://indiatoday.intoday.in/money/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
			} else if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) != "229") {
			window.location.replace( "<%="http://indiatoday.intoday.in/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
			}} else if(primaryCategoryJS == "230") {
			window.location.replace( "<%="http://indiatoday.intoday.in/auto/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
	} else if(primaryCategoryJS == "204") {
			window.location.replace( "<%="http://indiatoday.intoday.in/education/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
	}else if(primaryCategoryJS == "340") {
			window.location.replace( "<%="http://indiatoday.intoday.in/money/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
	}else if(primaryCategoryJS != "229")  {

			window.location.replace( "<%="http://indiatoday.intoday.in/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>");
			}
</script>
<%
	String primaryCategoryJS = storyItem.getPrimary_category();
		String canonicalUrl = "";
		if (primaryCategoryJS != null) {
			if (primaryCategoryJS != null
					&& primaryCategoryJS.indexOf("#") > 0) {
				if (primaryCategoryJS.substring(0,
						primaryCategoryJS.indexOf("#")).equals("230")) {
					canonicalUrl = "http://indiatoday.intoday.in/auto/"
							+ CommonFunctions.storyURL(
									storyItem.getSef_url(), 1,
									currentstoryId);
				} else if (primaryCategoryJS.substring(0,
						primaryCategoryJS.indexOf("#")).equals("204")) {
					canonicalUrl = "http://indiatoday.intoday.in/education/"
							+ CommonFunctions.storyURL(
									storyItem.getSef_url(), 1,
									currentstoryId);
				} else if (primaryCategoryJS.substring(0,
						primaryCategoryJS.indexOf("#")).equals("340")) {
					canonicalUrl = "http://indiatoday.intoday.in/money/"
							+ CommonFunctions.storyURL(
									storyItem.getSef_url(), 1,
									currentstoryId);
				} else {
					if (primaryCategoryJS.substring(0,
							primaryCategoryJS.indexOf("#")).equals(
							"229")) {
						canonicalUrl = "http://indiatoday.intoday.in/technology/"
								+ CommonFunctions.storyURL(
										storyItem.getSef_url(), 1,
										currentstoryId);
					} else {
						canonicalUrl = "http://indiatoday.intoday.in/"
								+ CommonFunctions.storyURL(
										storyItem.getSef_url(), 1,
										currentstoryId);
					}
				}
			} else if (primaryCategoryJS.equals("230")) {
				canonicalUrl = "http://indiatoday.intoday.in/auto/"
						+ CommonFunctions.storyURL(
								storyItem.getSef_url(), 1,
								currentstoryId);
			} else if (primaryCategoryJS.equals("204")) {
				canonicalUrl = "http://indiatoday.intoday.in/education/"
						+ CommonFunctions.storyURL(
								storyItem.getSef_url(), 1,
								currentstoryId);
			} else if (primaryCategoryJS.equals("340")) {
				canonicalUrl = "http://indiatoday.intoday.in/money/"
						+ CommonFunctions.storyURL(
								storyItem.getSef_url(), 1,
								currentstoryId);
			} else {
				if (primaryCategoryJS.equals("229")) {
					canonicalUrl = "http://indiatoday.intoday.in/technology/"
							+ CommonFunctions.storyURL(
									storyItem.getSef_url(), 1,
									currentstoryId);
				} else {
					canonicalUrl = "http://indiatoday.intoday.in/"
							+ CommonFunctions.storyURL(
									storyItem.getSef_url(), 1,
									currentstoryId);
				}
			}

		} else {
			canonicalUrl = "http://indiatoday.intoday.in/technology/"
					+ CommonFunctions.storyURL(storyItem.getSef_url(),
							1, currentstoryId);
		}
%><!--Newsroom Tabolla Ad-->
<script type="text/javascript">
window._taboola = window._taboola || [];
_taboola.push({article:'auto'});
!function (e, f, u) {
e.async = 1;
e.src = u;
f.parentNode.insertBefore(e, f);
}(document.createElement('script'), document.getElementsByTagName('script')[0], 'http://cdn.taboola.com/libtrc/indiatoday-indiatoday/loader.js');
</script>

<!-- GA-END -->

<link rel="canonical" href="<%=canonicalUrl%>" />
<%@include file="resources.jsp"%>
<script src="http://vuukle.com/js/vuukle.js" type="text/javascript"></script>
<!-- Start: Gettng styling code -->
<!--<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/fontawesome.
min.css">
<link rel="stylesheet"
href="//www.gettng.com/gettng/static/dist/css/gettng.min.css">-->
<!-- End: Gettng styling code -->
</head>
<body>
	<%@include file="navigation_topnav_tech.jsp"%>
	<%
		try {
	%>
	<%=HotGadgets%>
	<%
		} catch (Exception e) {
				//out.println("Hot Gadgets not found");
				e.printStackTrace();
			}
	%>
	<div class="thumbarea hidden-xs">
		<div class="container">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="row">
					<div class="swiper-container" id="middle_cont">
						<div class="swiper-wrapper">
							<%
								try {
										for (int i = 0; i < hp_second_topSize; i++) {
											ItemFeed feeds = (ItemFeed) hp_second_top.get(i);
							%>
							<div class="swiper-slide">
								<a
									href="<%=Constants.SITE_URL + "technology/"
								+ feeds.getContent_url()%>"
									title="<%=feeds.getTitle()%>">
									<div class="thumb_box">
										<img
											src="<%=Constants.KICKER_IMG_PATH
								+ feeds.getMobile_image()%>"
											alt="<%=feeds.getTitle()%>">
										<p><%=feeds.getTitle()%></p>
									</div>
								</a>
							</div>
							<%
								}
									} catch (Exception e) {
										//out.println("Data not found");
										e.printStackTrace();
									}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="maincontainer clearfix">
		<div class="container">
			<div class="col-md-8 col-xs-12">
				<div class="row margrtl">
					<div class="leftpanel">
						<div class="custom-breadcrumb story_brd">
							<ul>
								<li itemtype="http://data-vocabulary.org/Breadcrumb"
									itemscope=""><a href="<%=Constants.SITE_URL%>"
									title="Home" itemprop="url"><span itemprop="title">Home</span></a>
								</li>
								<li>&gt;</li>
								<li itemtype="http://data-vocabulary.org/Breadcrumb"
									itemscope=""><a href="<%=Constants.SITE_URL%>technology/"
									title="Technology" itemprop="url"> <span itemprop="title">Technology</span>
								</a></li>
								<li>&gt;</li>
								<li itemtype="http://data-vocabulary.org/Breadcrumb"
									itemscope="">
									<%
										if (storyItem.getCat_name() != null
													&& storyItem.getCat_name().equals("null")
													&& storyItem.getCat_name().equals("")) {
									%>
									<a
									href="<%=Constants.SITE_URL%>technology/<%=CommonFunctions.categoryURL(storyItem
							.getCat_name().toLowerCase().replaceAll(" ", "-"),
							1, Integer.parseInt(storyItem.getCat_id()))%>"
									title="Technology" itemprop="url"><span itemprop="title"><%=storyItem.getCat_name()%></span>
								</a> <%
 	}
 %>
								</li>
							</ul>


							<%
								//=storyItem.getTitlealias()
							%>


						</div>
						<span class="clearfix"></span>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 review">
							<div class="row">
								<h1><%=storyItem.getTitlealias()%></h1>
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 ybord bnone">
									<div class="row">

										<script type="text/javascript">
	ajaxinclude("<%=Constants.SITE_URL%>technology/chunk_story_sharecount_tech.jsp?createddate=<%=storyItem.getSnd()%>&shareUrl=<%="http://indiatoday.intoday.in/technology/"
						+ storyItem.getContent_url()%>&sid=<%=currentstoryId%>")
	</script>
										<!-- <div class="common_row marg16 hidden-xs">
    <span><strong>7.2k</strong> Shares</span> </div> -->
										<div class="common_row author_name_h">
											<%
												String VuukleAuthorDatacomma = "";
													String syndicateFrom = "";
													if (storyItem.getStory_syndication().equals("0")) {
														syndicateFrom = "Agency";
													} else {
														syndicateFrom = "Internal";
													}
													if (bylineData != null && bylineData.size() > 0) {
														for (int bylineDataID = 0; bylineDataID < bylineData.size(); bylineDataID++) {
															AuthorList authoList = (AuthorList) bylineData
																	.get(bylineDataID);
															if (!authoList.getAuthorName().trim().equals("")
																	&& authoList.getAuthorName() != null) {
																VuukleAuthorData = VuukleAuthorData
																		+ VuukleAuthorDatacomma + " { \"name\": \""
																		+ authoList.getAuthorName() + " \",";
																VuukleAuthorData = VuukleAuthorData
																		+ "   \"email\": \""
																		+ authoList.getAuthorEmail() + " \",";
																VuukleAuthorData = VuukleAuthorData
																		+ "   \"type\": \"" + syndicateFrom
																		+ " \"}";
																VuukleAuthorDatacomma = ",";
															}
														}
													} else {
														VuukleAuthorData = " { \"name\": \""
																+ storyItem.getByline() + " \",";
														VuukleAuthorData = VuukleAuthorData + "   \"type\": \""
																+ syndicateFrom + " \"}";
													}
											%>


											<span><%=storyItem.getByline()%> <%
 	if (storyItem.getReporter_twitter_handle() != null
 				&& !storyItem.getReporter_twitter_handle().equals("")
 				&& !storyItem.getReporter_twitter_handle().equals(
 						"null")) {
 			out.print("&nbsp;&nbsp;|&nbsp;&nbsp;<a  href=\"https://twitter.com/"
 					+ storyItem.getReporter_twitter_handle()
 					+ "\"  data-show-count=\"false\" data-lang=\"en\" > "
 					+ storyItem.getReporter_twitter_handle() + "</a>");
 %> <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
												<%
													}
												%> </span>
										</div>
										<div class="common_row last_updated_h">
											<span> Last Updated: <%=storyItem.getMdate()%></span>
										</div>
										<%
											if (storyItem.getCity() != null) {
													out.println("<div class='common_row hidden-xs'><span>"
															+ storyItem.getCity() + "</span></div>");
												}
										%>


										<%
											String title = "";
												String authorEmail = "";
												if (storyItem.getAuthorList() != null
														&& storyItem.getAuthorList().get(0) != null) {
													if (storyItem.getAuthorList().get(0).getAuthorEmail() != null
															&& ((String) storyItem.getAuthorList().get(0)
																	.getAuthorEmail()).length() > 0) {
														authorEmail = (String) storyItem.getAuthorList().get(0)
																.getAuthorEmail();
													}
													if (authorEmail.length() > 0) {
										%><div class="common_row hidden-xs ">
											<a
												href="<%=Constants.SITE_URL%>content_Email.jsp?sid=<%=currentstoryId%>&title=<%=storyItem.getTitle()%>&URL=<%=storyItem.getSef_url()%>&email=0&emailId=<%=authorEmail%>"
												onClick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;">
												<span><i class="fa fa-envelope-o"></i> Email Author</span>
											</a>
										</div>
										<%
											}
												}
										%>

										<div
											class="common_row greybg col-md-12 col-lg-12 col-sm-12 col-xs-6">
											<div class="row">
												<a
													href="https://facebook.com/sharer.php?u=http://indiatoday.intoday.in/technology/<%=storyItem.getContent_url()%>"
													onClick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"
													data-value-share="Facebook"><span><i
														class="fa fa-facebook"></i></span></a> <a target="_blank"
													href='https://twitter.com/intent/tweet?url=http://indiatoday.intoday.in/technology/<%=storyItem.getContent_url()%>&amp;text=<%=storyItem.getMetadesc().replaceAll("\\<.*?>", "")%>&amp;via=IndiaTodayTech'
													onClick="return popup(this, 'notes')"
													data-value-share="Twitter"><span><i
														class="fa fa-twitter"></i></span></a> <a
													onClick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"
													href="https://plus.google.com/share?url=http://indiatoday.intoday.in/technology/<%=storyItem.getContent_url()%>"
													data-value-share="Google"><span><i
														class="fa fa-google-plus"></i></span></a>
											</div>
										</div>
										<div
											class="common_row greybg col-md-12 col-lg-12 col-sm-12 col-xs-6 nxt_rt">
											<div class="row">
												<a href="Javascript: void(0)" class="vukule_cmt"><span><i
														class="fa fa-comment"></i></span></a> <a
													href="<%=Constants.SITE_URL%>content_Email.jsp?sid=<%=currentstoryId%>&title=<%=storyItem.getTitle()%>&URL=<%=storyItem.getSef_url()%>&email=0"
													onClick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"><span><i
														class="fa fa-envelope"></i></span></a> <a
													href="http://indiatoday.intoday.in/articlePrint.jsp?aid=<%=currentstoryId%>&title=<%=storyItem.getTitle()%>&URL=<%=storyItem.getSef_url()%>"
													onClick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"><span><i
														class="fa fa-print"></i></span></a>

											</div>
										</div>
										<%
											if (storyItem.getPrimary_category().equals("229#897")
														&& storyItem.getComment_question() != null) {
										%>
										<div class="bluearrow"><%=storyItem.getComment_question()%>/10
										</div>
										<%
											}
										%>
									</div>
								</div>
								<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12 ybord">
									<div class="row">
										<%
											String imgAltText = null;
												if (storyItem.getLarge_kicker_image_alt_text() == null) {
													imgAltText = storyItem.getTitle();
												} else {
													imgAltText = storyItem.getLarge_kicker_image_alt_text();
												}
										%>
										<img
											src="<%=Constants.KICKER_IMG_PATH
						+ storyItem.getExtralarge_image()%>"
											alt="<%=imgAltText%>" title="<%=imgAltText%>">
									</div>
									<div class="row">
										<%
											if (storyItem.getKicker_image_caption() != null) {
										%>
										<div class="mos-caption-top"><%=storyItem.getKicker_image_caption()%></div>
										<%
											}
										%>
									</div>
								</div>
								<span class="clearfix"></span>
								<div class="story_main_cont">
									<%
										if (currentstoryId > 43900) {
												if (storyBody != null && !storyBody.equals("")) {
													if (storyBody.contains("height=\"396\" width=\"648\"")) {
														storyBody = storyBody.replace(
																"height=\"396\" width=\"648\"",
																"width=\"648\" height=\"496\"");
													} else if (storyBody
															.contains("height=\"396\" width=\"647\"")) {
														storyBody = storyBody.replace(
																"height=\"396\" width=\"647\"",
																"width=\"648\" height=\"496\"");
													} else if (storyBody
															.contains("width=\"648\" height=\"396\"")) {
														storyBody = storyBody.replace(
																"width=\"648\" height=\"396\"",
																"width=\"648\" height=\"496\"");
													} else if (storyBody
															.contains("height=\"647\" width=\"396\"")) {
														storyBody = storyBody.replace(
																"height=\"647\" width=\"396\"",
																"width=\"648\" height=\"496\"");
													} else if (storyBody
															.contains("width=\"648\" height=\"496\"")) {
														storyBody = storyBody.replace(
																"width=\"648\" height=\"496\"",
																"width=\"100%\" height=\"496\"");
													} else if (storyBody.contains("width:622px")) {
														storyBody = storyBody.replace("width:622px",
																"width:100%");
													}

													if (storyBody
															.contains("scrolling=\"no\" src=\"http://indiatoday.intoday.in/embed/\"")) {
														storyBody = storyBody
																.replace(
																		"scrolling=\"no\" src=\"http://indiatoday.intoday.in/embed/\"",
																		"scrolling=\"no\" allowfullscreen  src=\"http://indiatoday.intoday.in/embed/\"");
													}

													if (storyBody.length() > 20) {
														if (storyBody.substring(0, 15).contains(
																"{mosimage}")) {
															storyBody = storyBody.replaceFirst(
																	"\\{mosimage\\}", "");
															storyBody = storyBody.replace("<p></p>", "");
														}
													}
												}
											}
											if (storyBody.trim().indexOf("mosimage") >= 0) {
												storyBody = mosimageToimagesOpt(storyBody,
														storyItem.getImagePath());
											}
											if (storyItem.getBlurbs() != null
													&& storyItem.getBlurbs().trim().length() > 10) {
												storyBody = htmlChunks(storyBody, storyItem.getBlurbs()
														.trim(), "{blurb}");
											}
											if (storyItem.getQuotes() != null
													&& storyItem.getQuotes().trim().length() > 10) {
												storyBody = htmlChunks(storyBody, storyItem.getQuotes()
														.trim(), "{quote}");
											}
											if (storyItem.getTables() != null
													&& storyItem.getTables().trim().length() > 10) {
												storyBody = htmlChunks(storyBody, storyItem.getTables()
														.trim(), "{table}");
											}
											//Pagination Code
											storyBody = storyBody.replaceAll("\\{funfacts\\}", "");
											if (checkPagebreak >= 0) {
												if (currentStoryPageNo == 0) {
													storyBody = storyBody.replaceAll("\\{mospagebreak\\}",
															"");
												} else {
													storyBodyDisplay = storyBody
															.split("\\{mospagebreak\\}");
													if (currentStoryPageNo > 0
															&& currentStoryPageNo <= storyBodyDisplay.length) {
														storyBody = storyBodyDisplay[currentStoryPageNo - 1];
													} else {
														storyBody = "Requested page does not exist. Please go from 0 to "
																+ storyBodyDisplay.length
																+ ".<br /> 0 for Complete story and 1 - "
																+ storyBodyDisplay.length
																+ " for Story part.";
													}
												}
											} else if (currentStoryPageNo != 1) {
												storyBody = "Requested page does not exist. Story exists in single part.";
											}
									%>
									<%=storyBody.replaceAll("####", "")%>
								</div>
								<span class="clearfix"></span>
								<div class="ad_cont_middle">
									<%
										if (storyItem.getContent_url() != null
													&& !storyItem.getContent_url().contains("@")) {
									%>
									<div class="ad_cont_st">
										<script type="text/javascript"><!--
google_ad_client = "ca-pub-3793720534573472";
/* Google_Adword */
google_ad_slot = "3890855013";
google_ad_width = 468;
google_ad_height = 60;
//-->
</script>
										<script type="text/javascript"
											src="http://pagead2.googlesyndication.com/pagead/show_ads.js"></script>
									</div>
									<%
										}
									%>
								</div>
								<span class="clearfix"></span>
								<div id="where_to_buy_items"></div>
								<script async
									src="http://www.plannto.com/javascripts/plannto.elec_widget_1.js?ref_url=&item_id=&show_details=true&is_test=&page_type=type_1"></script>
								<p class="more-news">
									For more news from India Today, follow us on Twitter <a
										style="font-weight: normal" rel="nofollow" target="_blank"
										href="https://twitter.com/IndiaTodayTech">@IndiaTodayTech</a>
									and on Facebook at <a style="font-weight: normal"
										rel="nofollow" target="_blank"
										mce_href="https://www.facebook.com/indiatodaytech"
										href="https://www.facebook.com/indiatodaytech">facebook.com/indiatodaytech</a>
									<br>
									<span style="padding-top: 10px; display: inline-block">
										For news and videos in Hindi, go to <a target="_blank"
										rel="nofollow" href="http://aajtak.intoday.in">AajTak.in</a>.ताज़ातरीन
										ख़बरों और वीडियो के लिए <a target="_blank" rel="nofollow"
										href="http://aajtak.intoday.in">आजतक.इन</a> पर आएं.
									</span>
								</p>
								<div class="clear">&nbsp;</div>
								<script>
$(document).ready(function(){
var wdth = $(window).width();
var hght = $(window).height();
if(wdth>=800){
var w_embed = wdth-(wdth*0.20);
var h_embed = hght-(hght*0.20);
$('.modal-dialog').css({'max-width':w_embed,'max-height':h_embed});
}
else
{
var w_embed = wdth-(wdth*0.15);
var h_embed = hght-(hght*0.15);
$('.modal_embed .modal-dialog').css({'max-width':w_embed,'max-height':h_embed});
}
//$('.modal_embed .row .thumbnail').click(function(){  
$('#mn_img .thumbnail').click(function(){    
    var idx = $(this).parents('div').index();
  	var id = parseInt(idx);
        $('.modal_embed').modal('show'); 

  /* $(this).parents('.img-gal-box').find('.modal_embed').modal('show'); 
   $(this).parents('.img-gal-box').find('.carousel-inner .item').removeClass('active'); 
   $(this).parents('.img-gal-box').find('.carousel-inner .item:first-child').addClass('active'); */
  	//$('.modal_embed', this).modal('show'); 
    $('#modalCarousel').carousel(id);
		
		
  	
});
})
</script>
								<script type="text/javascript">
ajaxinclude("<%=Constants.SITE_URL%>technology/like-dislike-story-footer.jsp?contentId=<%=currentstoryId%>&contentType=text&ScfUrl=<%=storyItem.getTitle()%>&createddate=<%=storyItem.getSnd()%>&title=<%=storyItem.getTitlealias()%>")
</script>
								<span class="clearfix"></span>
								<script type="text/javascript">
ajaxinclude("/snappost/snappost-chunk.jsp?contentId=<%=currentstoryId%>&storySefTitle=<%=storyItem.getTitle()%>")
</script>

								<div class="divclear"></div>
								<div id="InRead"></div>
								<script type="text/javascript">
var zflag_parent="InRead";
var zflag_vast_domain="http://xp1.zedo.com/";
var zflag_nid="821";
var zflag_cid="2059";
var zflag_sz="85";
var zflag_sid="0";
var zflag_width="426";
var zflag_height="340";
var zflag_blocklb="1";
var zflag_breplay="1"
</script>
								<script type="text/javascript"
									src="http://d2.zedo.com/jsc/d2/frd.js"></script>
								<div id='taboola-below-main-column'></div>
								<script type="text/javascript">
    window._taboola = window._taboola || [];
    _taboola.push({mode:'thumbs-2r', container:'taboola-below-main-column', placement:'below-main-column'});
</script>
								<div id='taboola-text-2-columns'></div>
								<script type="text/javascript">
    window._taboola = window._taboola || [];
    _taboola.push({mode:'hybrid-text-links-2c', container:'taboola-text-2-columns', placement:'text-2-columns', target_type: 'mix'});
</script>
								<!--Taboola's last code -->
								<script type="text/javascript">
window._taboola = window._taboola || [];
_taboola.push({flush:true});
</script>


								<%
									if (storyItem.getContent_url() != null
												&& !storyItem.getContent_url().contains("@")) {
								%>
								<div class="divclear">
									<script type="text/javascript">
<!--
google_ad_client = "ca-pub-3793720534573472";
/* Google_Adword */
google_ad_slot = "3890855013";
google_ad_width = 468;
google_ad_height = 60;
//-->
</script>
									<script type="text/javascript"
										src="http://pagead2.googlesyndication.com/pagead/show_ads.js"></script>
								</div>
								<%
									}
								%>


								<!--To display comments platform code start here-->
								<%
									VuukleAuthorData = "[" + VuukleAuthorData.trim() + "]";
										//out.println("JSON -->"+VuukleAuthorData);
										String VuukleAuthorDataEncode = URLEncoder
												.encode(VuukleAuthorData, "UTF-8")
												.replaceAll("\\%28", "(").replaceAll("\\%29", ")")
												.replaceAll("\\+", "%20").replaceAll("\\%27", "'")
												.replaceAll("\\%21", "!").replaceAll("\\%7E", "~")
												.replaceAll("%3A", ":").replaceAll("%2C", ",")
												.replaceAll("%40", "@");
										//out.println("<br/>urlencode-->"+VuukleAuthorDataEncode);
										byte[] byteArray = Base64.encodeBase64(VuukleAuthorDataEncode
												.getBytes());
										String encodedString = new String(byteArray);
										//out.println("Base64.encodeBase64-->"+encodedString);
								%>
								<a name="comment"></a>
								<div id="vuukle_div"></div>
								<script type="text/javascript">
var UNIQUE_ARTICLE_ID = "story_<%=currentstoryId%>"; 
var SECTION_TAGS =  "<%=storyItem.getCat_name()%>, technology, story";
var ARTICLE_TITLE = "<%out.print(storyItem.getTitle().replace("\"", ""));%>";
var GA_CODE = "UA-795349-17";
var VUUKLE_API_KEY = "dc34b5cc-453d-468a-96ae-075a66cd9eb7"; 

var TRANSLITERATE_LANGUAGE_CODE = ""; //"en" for English, "hi" for hindi
var VUUKLE_COL_CODE = "d00b26";
var ARTICLE_AUTHORS = '<%=encodedString%>';
create_vuukle_platform(VUUKLE_API_KEY, UNIQUE_ARTICLE_ID, "0", SECTION_TAGS, ARTICLE_TITLE, TRANSLITERATE_LANGUAGE_CODE , "1", "", GA_CODE, VUUKLE_COL_CODE, ARTICLE_AUTHORS);
</script>
								<!--To display comments platform code end here-->
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="navigation_rightnav_tech.jsp"%>
		</div>
	</div>
	<script type='text/javascript'>
    var _sf_async_config = _sf_async_config || {};
      /** CONFIGURATION START **/
    _sf_async_config.uid = 60355;
    _sf_async_config.domain = 'indiatoday.intoday.in';
    _sf_async_config.useCanonical = true;
    _sf_async_config.sections = '<%=storyItem.getCat_name()%>';  
    _sf_async_config.authors = '<%=storyItem.getByline()%>';    
    _sf_async_config.title = '<%=storyItem.getTitlealias()%>'
                    ;
                _sf_async_config.path = '<%=Constants.SITE_BASEPATH
						+ "technology/"
						+ CommonFunctions.storyURL(storyItem.getSef_url(), 1,
								currentstoryId)%>
		';
		/** CONFIGURATION END **/
		(function() {
			function loadChartbeat() {
				window._sf_endpt = (new Date()).getTime();
				var e = document.createElement('script');
				e.setAttribute('language', 'javascript');
				e.setAttribute('type', 'text/javascript');
				e.setAttribute('src', '//static.chartbeat.com/js/chartbeat.js');
				document.body.appendChild(e);
			}
			loadChartbeat();
		})();
	</script>
	<%
		//@include file="navigation_footernav_tech_story.jsp"
	%>
	<%@include file="navigation_footernav_tech.jsp"%>

	<!-- Start: Gettng script code -->
	<!--<script type="text/javascript" src="//www.gettng.com/gettng/static/dist/js/gettng.min.js"></script>-->
	<!-- End: Gettng script code -->
	<script src="http://cdn.hoverr.co/tera/tera_indiatoday_web.js"
		type="text/javascript"></script>

	<script>
		$(window).load(function() {
			$('.trow .tthumbnail').on('load', function() {
			}).each(function(i) {
				if (this.complete) {
					var item = $('<div class="titem"></div>');
					var itemDiv = $(this).parents('div');
					var title = $(this).parent('a').attr("title");

					item.attr("ttitle", title);
					$(itemDiv.html()).appendTo(item);
					item.appendTo('.tcarousel-inner');
					if (i == 0) {
						item.addClass('tactive');
					}
				}
			});
		})
		$(document).ready(function() {
			$("#mn_img a").click(function(e) {
				e.preventDefault();
			});

			$('.pos_rel .seemore').click(function() {
				$(this).remove();
				$('.glr').removeClass('hd1');

			})

			$('#modalCarousel').carousel({
				interval : false
			});

			$('#modalCarousel').on('slid.bs.carousel', function() {
				$('.modal-title').html($(this).find('.active').attr("title"));
				$('.modal-content .seemore').remove();
			})

			/*$('.row .thumbnail').click(function(){
			 var idx = $(this).parents('div').index();   
			 var id = parseInt(idx);
			 $('#myModal').modal('show'); 
			 $('#modalCarousel').carousel(id); 
			
			 });*/

			$(document).ready(function() {
				$('.dtv ul li').click(function() {
					var at = $(this).children('img').attr("pr");
					var img_src = $(this).children('img').attr('src');
					$('.active>a>img').attr({
						'src' : img_src
					});
				})
			})

		})
	</script>
	<script type="text/javascript"
		src="http://d3clqjla00sltp.cloudfront.net/pm_ip_it.js"></script>
</body>
	</html>
</cache:cache>
<%!public String byLineLinkCreater(String byLine) {
		String doubleByLine[] = null;
		String doubleByLine1[] = null;
		StringBuffer sb = new StringBuffer();
		String byLineWithLink = "";
		if (!byLine.trim().equals("") && byLine != null) {
			if (byLine.contains("/")) {
				doubleByLine = byLine.split("\\/");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					//System.out.println("with/@@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\"" + Constants.SITE_URL + "author/"
							+ doubleByLine[cid].replace(" ", "-")
							+ "/1.html\">" + doubleByLine[cid] + "</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("/");
						sb.append("/");
					}
				}
			} else if (byLine.contains(",")) {
				doubleByLine = byLine.split(",");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					// System.out.println("with , @@@@@@@@"+doubleByLine[cid]);
					if (!doubleByLine[cid].contains(" and "))
						//System.out.print(doubleByLine[cid]);
						sb.append("<a href=\"" + Constants.SITE_URL + "author/"
								+ doubleByLine[cid].replace(" ", "-")
								+ "/1.html\">" + doubleByLine[cid] + "</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.print(",");
						sb.append(",");
					}
					if (doubleByLine[cid].contains(" and ")) {
						doubleByLine1 = doubleByLine[cid].split(" and ");
						for (int cid1 = 0; cid1 < doubleByLine1.length; cid1++) {
							//System.out.print(doubleByLine1[cid1]);
							sb.append("<a href=\"" + Constants.SITE_URL
									+ "author/"
									+ doubleByLine1[cid].replace(" ", "-")
									+ "/1.html\">" + doubleByLine1[cid]
									+ "</a>");
							if (cid1 != doubleByLine1.length - 1) {
								//System.out.print(" and ");
								sb.append(" and ");
							}
						}
					}
				}
			} else if (byLine.contains(" and ")) {
				doubleByLine = byLine.split("and");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					if (doubleByLine[cid].contains(" in ")) {
						doubleByLine[cid] = doubleByLine[cid].substring(0,
								doubleByLine[cid].indexOf(" in "));
					}
					//System.out.println("with and @@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\"" + Constants.SITE_URL + "author/"
							+ doubleByLine[cid].replace(" ", "-")
							+ "/1.html\">" + doubleByLine[cid] + "</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("and");
						sb.append("and");
					}
				}
			} else if (byLine.contains(" with ")) {
				doubleByLine = byLine.split("with");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					if (doubleByLine[cid].contains(" in ")) {
						doubleByLine[cid] = doubleByLine[cid].substring(0,
								doubleByLine[cid].indexOf(" in "));
					}
					//System.out.println("with with @@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\"" + Constants.SITE_URL + "author/"
							+ doubleByLine[cid].replace(" ", "-")
							+ "/1.html\">" + doubleByLine[cid] + "</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("with");
						sb.append("with");
					}
				}

			} else if (byLine.contains(" & ")) {
				doubleByLine = byLine.split("&");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					//System.out.println("with & @@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\"" + Constants.SITE_URL + "author/"
							+ doubleByLine[cid].replace(" ", "-")
							+ "/1.html\">" + doubleByLine[cid] + "</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("&");
						sb.append("&");
					}
				}
			} else {
				if (byLine.contains(" in ")) {
					byLine = byLine.substring(0, byLine.indexOf(" in "));
					sb.append("<a href=\"" + Constants.SITE_URL + "author/"
							+ byLine.replace(" ", "-") + "/1.html\">" + byLine
							+ "</a>");
				} else {
					//System.out.println("only @@@@@@@@" + byLine);
					sb.append("<a href=\"" + Constants.SITE_URL + "author/"
							+ byLine.replace(" ", "-") + "/1.html\">" + byLine
							+ "</a>");
				}
			}

		}
		byLineWithLink = sb.toString();
		return byLineWithLink;
	}

	String htmlChunks(String fullbodytext, String htmlText, String htmlFormat) {
		String htmlData = "";
		String completeHtmlData = "";
		htmlText = forRegex(htmlText);
		String[] htmlDataArray = (htmlText.trim()).split("\\~");
		int noOfQuotes = htmlDataArray.length;
		String textFormat = htmlFormat.replace("{", "\\{").replace("}", "\\}");
		try {
			for (int k = 0; k < noOfQuotes; k++) {
				completeHtmlData = htmlDataArray[k];
				Pattern pa = Pattern.compile(textFormat);
				Matcher matcher = pa.matcher(fullbodytext);
				fullbodytext = matcher.replaceFirst(completeHtmlData);
				htmlData = fullbodytext;
			}
		} catch (Exception ee) {
			//htmlData = "<font color=red><br>HTML Chunk eeerrr="+ee.toString()+"<>"+"</font>";
			htmlData = fullbodytext;
		}
		return htmlData;
	}

	public static String forRegex(String aRegexFragment) {
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(
				aRegexFragment);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			/*
			 All literals need to have backslashes doubled.
			 */
			if (character == '.') {
				result.append("\\.");
			} else if (character == '\\') {
				result.append("\\\\");
			} else if (character == '?') {
				result.append("\\?");
			} else if (character == '*') {
				result.append("\\*");
			} else if (character == '+') {
				result.append("\\+");
			} else if (character == '&') {
				result.append("\\&");
			} else if (character == ':') {
				result.append("\\:");
			} else if (character == '{') {
				result.append("\\{");
			} else if (character == '}') {
				result.append("\\}");
			} else if (character == '[') {
				result.append("\\[");
			} else if (character == ']') {
				result.append("\\]");
			} else if (character == '(') {
				result.append("\\(");
			} else if (character == ')') {
				result.append("\\)");
			} else if (character == '^') {
				result.append("\\^");
			} else if (character == '$') {
				result.append("\\$");
			} else {
				//the char is not a special one
				//add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	public static String mosimageToimagesOpt(String fullbody, String images)
			throws Exception {
		String full1 = "";
		int pipepos = 0;
		int error = 0;
		int p = 0;
		int tokenCount = 0;
		try {
			if ((images.trim().length()) > 0) {
				String[] bodyimages = (images.trim()).split("\n");
				tokenCount = bodyimages.length;

				for (int k = 0; k < tokenCount; k++) {
					bodyimages[k] = bodyimages[k].substring(0,
							bodyimages[k].length());
					String[] image1 = new String[] { "", "", "", "", "", "",
							"", "" };

					if (bodyimages[k].indexOf("|") > 0) {
						bodyimages[k] = bodyimages[k].substring(0,
								bodyimages[k].length());
						bodyimages[k] = bodyimages[k].replace("|", "#-#");
						image1 = bodyimages[k].split("#-#");
					} else {
						image1[0] = bodyimages[k];
					}
					String imageDiv = "";
					String captionDiv = "";
					String completeImage = "";
					String imagewidth = "";
					int imagewidthInt = 0;
					String divImagewidth = "";
					String imageHeight = "";
					String largeImage = "";
					String largeImageWidth = "";
					String largeImageHeight = "";
					String imageAlignment = "";
					String popupScrollbars = "no";
					String popupResize = "no";
					try {
						for (int k1 = 0; k1 < 1; k1++) {
							completeImage = "";
							if (imagewidth != null && imagewidth.length() > 0) {
								imagewidthInt = Integer.parseInt(imagewidth);
							}
							if (imagewidthInt > 450) {
								imagewidth = "100%";
								divImagewidth = imagewidth;
							} else {
								//divImagewidth=imagewidth+"px";
								//imagewidth=imagewidth+"px;margin-right:15px";
								divImagewidth = "100%";
								imagewidth = "100%;margin-right:15px";
							}
							if (image1[3] != null
									&& (image1[3].trim()).length() > 0)
								image1[3] = "0";

							if (image1[0] != null
									&& (image1[0].trim()).length() > 0) {
								if (image1[1].equals("right")) {
									imageAlignment = "margin:0 ; ";
								} else {
									imageAlignment = "margin:0 ; ";
								}
								imageDiv = "<div class=\"storycentimg\" style='border:0px solid #d7d7d7; "
										+ imageAlignment
										+ " width:"
										+ imagewidth
										+ "; float:"
										+ image1[1]
										+ "'><div style='width:"
										+ divImagewidth
										+ "'><img src='"
										+ Constants.STORY_IMG_PATH
										+ image1[0]
										+ "' align='"
										+ image1[1]
										+ "' alt='"
										+ image1[2]
										+ "' title='"
										+ image1[2]
										+ "'    style='border:1px solid #d7d7d7; margin:2px 0px'></div>";

								if (image1[4] != null
										&& (image1[4].trim()).length() > 0) {

									captionDiv = "<h2 class='mos-caption' style='width:100%; text-align:"
											+ image1[6]
											+ "'>"
											+ image1[4]
											+ "</h2>";

								}
								if (image1[5].equals("top")) {
									completeImage = captionDiv + imageDiv;
								} else {
									completeImage = imageDiv + captionDiv;
								}

								if (!largeImage.equals("")) {
									if (Integer.parseInt(largeImageWidth) > 800
											|| Integer
													.parseInt(largeImageHeight) > 700) {
										popupScrollbars = "yes";
										popupResize = "yes";
										largeImageWidth = ""
												+ (Integer
														.parseInt(largeImageWidth) + 21);
										largeImageHeight = ""
												+ (Integer
														.parseInt(largeImageHeight) + 21);
									}
									completeImage = completeImage
											+ "<div class='clicktoenlarge' style='width:"
											+ imagewidth
											+ " text-align:"
											+ image1[6]
											+ "' valign='"
											+ image1[5]
											+ "'><a href='#' onClick=\"javascript:window.open('http://indiatoday.intoday.in/story_image.jsp?img="
											+ largeImage
											+ "&caption="
											+ image1[4]
											+ "','window','status=no,resize="
											+ popupResize
											+ ",toolbar=no,scrollbars="
											+ popupScrollbars
											+ ",width="
											+ largeImageWidth
											+ ",height="
											+ largeImageHeight
											+ "'); return false;\">Click here to Enlarge</a></div>";
								}
								completeImage = completeImage + "</div>";
							}
						}
						Pattern pa = Pattern.compile("\\{mosimage\\}");
						Matcher matcher = pa.matcher(fullbody);
						fullbody = matcher.replaceFirst(completeImage);
						full1 = fullbody;
						//System.out.println("@@@-->"+completeImage);
					} catch (Exception ee) {
						full1 = fullbody;
						System.out
								.println("StoryHelper - mosimageToImage - image exception -> "
										+ ee.toString());
					}
				}
			} else {
				full1 = fullbody;
			}
		} catch (Exception ee) {
			full1 = fullbody;
			System.out.println("StoryHelper - mosimageToImage : "
					+ ee.toString());
		}
		//System.out.println("full1-->"+full1);
		//full1 = full1.replace("{<table", "<table");
		//full1 = full1.replace("</table>}", "</table>");
		return full1;
	}%>

