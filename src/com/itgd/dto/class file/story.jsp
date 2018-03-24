<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %><%@ page language="java" import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet,java.util.ArrayList, com.itgd.dto.StoryDTO, com.itgd.dto.LatestContentDTO, com.itgd.dto.byLineDTO,com.itgd.dto.RelatedContentDTO, com.itgd.helper.StoryHelper,com.itgd.helper.SocialSharingCountHelper, com.itgd.utils.CommonFunctions, com.itgd.utils.Constants,com.itgd.helper.VideoHelper,com.itgd.conn.Dbconnection,java.util.Calendar" pageEncoding="UTF-8"%><%@ page import="java.text.CharacterIterator"%><%@ page import="java.text.StringCharacterIterator"%><%@ page import="java.util.regex.Matcher"%><%@ page import="java.util.regex.Pattern"%><%@ page import="java.util.regex.Pattern,twitter4j.Twitter,twitter4j.User,java.net.URLEncoder,org.apache.commons.codec.binary.Base64,java.util.Arrays"%><%!
	String relatedChunk(String fullbodytext, String relatedStroyId) 
	{ 
		StringBuffer relatedSB = null;
		String relatedStr = "";
		ArrayList resultStoryRelated=null;
		String relatedtype = "";
		
		try {
		resultStoryRelated = (ArrayList) CommonFunctions.relatedContent(relatedStroyId, "text");
		if (resultStoryRelated != null && resultStoryRelated.size() > 0) {
			relatedSB = new StringBuffer();
			for (int ctr = 0; ctr < resultStoryRelated.size(); ctr++) {
				RelatedContentDTO rcDTO = (RelatedContentDTO) resultStoryRelated.get(ctr); 
				relatedtype = rcDTO.getRelatedType();
				if(ctr==0){
					relatedSB.append("<div class='leftspac'><div class='strbox'><h2>The Relateds</h2>");	
				}
				relatedSB.append("<div class='strinnerbox lft'>");	
				if(relatedtype.trim().equals("text")) {	
					if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
						relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.storyURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\"><img align='left' width='170' height='120'src=\""+Constants.STORY_IMG_PATH+rcDTO.getRelatedThumbImage()+"\"></a>"); 
						relatedSB.append("<span class='st_white'><span class='small-story-icon sp_bg'></span></span>");       
					} 
					relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.storyURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\">"+rcDTO.getRelatedTitle()+"</a>");
				} else if(relatedtype.trim().equals("photo"))	{
						if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
							relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.galleryURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\"><img align='left' width='170' height='120'  src=\""+Constants.GALLERY_IMG_PATH+rcDTO.getRelatedThumbImage()+"\"></a>");
						}
					relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.galleryURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\">"+rcDTO.getRelatedTitle()+"</a>");
					if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {
						relatedSB.append("<span class='st_white'><span class='small-image-icon sp_bg'></span></span>");
					}
				} else if(relatedtype.trim().equals("video"))	{
					if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
						relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.videoURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\"><img align='left' width='170' height='120' src=\""+Constants.STORY_IMG_PATH+rcDTO.getRelatedThumbImage()+"\"></a>");
					} 
						relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.videoURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\">"+rcDTO.getRelatedTitle()+"</a>");
						if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
							relatedSB.append("<span class='st_white'><span class='small-play-icon sp_bg'></span><span>");
						}
					}     
					relatedSB.append("</div>");	
				}
			relatedSB.append("</div></div>");
		}
		} catch (Exception ex) {
		     System.out.println("IT Story.jsp Related Exception->"+ex.getMessage());
			//System.out.println("IT Story Related Exception->"+ex.toString());
		}
		if(relatedSB.toString().trim().length() > 0) {
			relatedStr = relatedSB.toString().trim();
		}

		String htmlData = "";
		String completeHtmlData = "";
		String textFormat = "\\{relateds\\}";
		try {
				completeHtmlData = relatedStr;
				Pattern pa = Pattern.compile(textFormat);
				Matcher matcher = pa.matcher(fullbodytext); 
				fullbodytext = matcher.replaceFirst(completeHtmlData);
				htmlData=fullbodytext;
		} catch(Exception ee){
			//htmlData = "<font color=red><br>HTML Chunk eeerrr="+ee.toString()+"<>"+"</font>";
			htmlData = fullbodytext; 
		}
		return htmlData;  
	}
	String htmlChunks(String fullbodytext,String htmlText, String htmlFormat)
	{
		String htmlData = "";
		String completeHtmlData = "";
		htmlText=forRegex(htmlText); 
		String[] htmlDataArray = (htmlText.trim()).split("\\~");
		int noOfQuotes = htmlDataArray.length;
		String textFormat = htmlFormat.replace("{", "\\{").replace("}", "\\}");
		try {
			for(int k=0;k<noOfQuotes ;k++)	
			{
				completeHtmlData = htmlDataArray[k];
				Pattern pa = Pattern.compile(textFormat);
				Matcher matcher = pa.matcher(fullbodytext); 
				fullbodytext = matcher.replaceFirst(completeHtmlData);
				htmlData=fullbodytext;
			}
		} catch(Exception ee){
			//htmlData = "<font color=red><br>HTML Chunk eeerrr="+ee.toString()+"<>"+"</font>";
			htmlData = fullbodytext; 
		}
		return htmlData;  
	}
	public static String forRegex(String aRegexFragment){ 
    final StringBuilder result = new StringBuilder();
    final StringCharacterIterator iterator =   new StringCharacterIterator(aRegexFragment);
    char character =  iterator.current();
    while (character != CharacterIterator.DONE ){
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
  public String byLineLinkCreater(String byLine){
		String doubleByLine[] = null;
		String doubleByLine1[] = null;
		StringBuffer sb= null;
		sb= new StringBuffer();
		String byLineWithLink="";
		String byLineWithInLink="";
		if (!byLine.trim().equals("") && byLine != null) {
			if (byLine.contains("/")) {
				doubleByLine = byLine.split("\\/");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					//System.out.println("with/@@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("/");
						sb.append(" /");
					}
				}
			} else if (byLine.contains(",")) {
				doubleByLine = byLine.split(",");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					// System.out.println("with , @@@@@@@@"+doubleByLine[cid]);
					if (!doubleByLine[cid].contains(" and "))
						//System.out.print(doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.print(",");
						sb.append(" ,");
					}
					if (doubleByLine[cid].contains(" and ")) {
						doubleByLine1 = doubleByLine[cid].split(" and ");
						for (int cid1 = 0; cid1 < doubleByLine1.length; cid1++) {
							//System.out.print(doubleByLine1[cid1]);
							sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine1[cid1].replace(" ","-")+"/1.html\">"+doubleByLine1[cid1]+"</a>");
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
				sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("and");
						sb.append(" and");
					}
				}
			} else if (byLine.contains(" with ")) {
				doubleByLine = byLine.split("with");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					if (doubleByLine[cid].contains(" in ")) {
					
					byLineWithInLink=doubleByLine[cid];
						doubleByLine[cid] = doubleByLine[cid].substring(0,doubleByLine[cid].indexOf(" in "));
						
						//System.out.println("with-->"+doubleByLine[cid]);
						//System.out.println("with-->"+byLineWithInLink);
						byLineWithInLink=byLineWithInLink.substring(doubleByLine[cid].length()+1,byLineWithInLink.length());
						
						
					}
					//System.out.println("with with @@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a> "+byLineWithInLink+"");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("with");
						sb.append(" with");
					}
				}

			} else if (byLine.contains(" & ")) {
				doubleByLine = byLine.split("&");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					//System.out.println("with & @@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("&");
						sb.append(" &");
					}
				}
			}
			else {
				if (byLine.contains(" in ")) {
					byLine = byLine.substring(0, byLine.indexOf(" in "));
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+byLine.replace(" ","-")+"/1.html\">"+byLine+"</a>");
				}else{
				//System.out.println("only @@@@@@@@" + byLine);
				sb.append("<a href=\""+Constants.SITE_URL+"author/"+byLine.replace(" ","-")+"/1.html\">"+byLine+"</a>");
			}
			}

		}
		byLineWithLink=sb.toString();
	return byLineWithLink;
	}
	public String getProfileLink(String description, String metatags) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String[][] profileArray = null;
		String[][] metatagArray = null;
		String[] tempMetaTagsA = null;
		String sefTitle = "";
		int profileCount = 0;
		int ctr = 0;
		String thumbImage = "";
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			sql = "select id, headline, sef_url,kicker_image,thumb_image from jos_snippets where sectionid=171 and published=1 group by headline";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last();
			profileCount = rs.getRow();
			profileArray = new String[profileCount][2];
			rs.beforeFirst();
			while (rs.next()) {
				profileArray[ctr][0] = rs.getString("headline") == null ? "" : rs.getString("headline");		
				sefTitle = rs.getString("sef_url") == null ? "" : rs.getString("sef_url");
				thumbImage = rs.getString("thumb_image") == null ? "" : rs.getString("thumb_image");
				if(sefTitle.indexOf(".html") >= 0)
					sefTitle = sefTitle.replaceAll(".html", "");
					if(thumbImage!=null && !thumbImage.equals("")){
				profileArray[ctr][1] = "<a href=\""+Constants.SITE_URL+"people/"+sefTitle+"/"+rs.getInt("id")+".html\"><img class=\"pf_img\" src=\"http://media2.intoday.in/indiatoday/images/stories/"+thumbImage+"\" width=\"30\" height=\"30\" alt=\"\"/>"+profileArray[ctr][0]+"</a>";
				}else{
				profileArray[ctr][1] = "<a href=\""+Constants.SITE_URL+"people/"+sefTitle+"/"+rs.getInt("id")+".html\"><img class=\"pf_img\" src=\"\" width=\"30\" height=\"30\" alt=\"\"/>"+profileArray[ctr][0]+"</a>";
				}
				ctr++;
			}
			for(int i1=0; i1 < profileArray.length; i1++) {
				if(description.contains(profileArray[i1][0])) {
					description = description.replaceFirst(profileArray[i1][0], profileArray[i1][1]);
				}
			}
			//Meta Tags from
			/*if(metatags.trim().length() > 0) {
				if(metatags.contains(",")) {
					tempMetaTagsA = (metatags.trim()).split(",");
				} else {
					tempMetaTagsA = new String[1];
					tempMetaTagsA[0] =  metatags.trim();
				}
				metatagArray = new String[tempMetaTagsA.length][2];
				for(int i=0; i < tempMetaTagsA.length; i++) {
					metatagArray[i][0] = tempMetaTagsA[i];
					metatagArray[i][1] = "<a href=\""+Constants.SITE_BASEPATH+"advanced_search.jsp?searchword="+tempMetaTagsA[i].trim()+"&searchtype=text&search_type=zedo\" >"+tempMetaTagsA[i]+"</a>";

				}
				int metaTagMatched = 0;
				for(int m=0; m < metatagArray.length; m++) {
					for(int p=0; p < profileArray.length; p++) {
						if(!profileArray[p][0].trim().equals(metatagArray[m][0].trim())) {
							metaTagMatched = 1;
						}
					}
					if(metaTagMatched==1 && description.contains(metatagArray[m][0])) {
						description = description.replaceFirst(metatagArray[m][0].trim(), metatagArray[m][1].trim());
					}
					metaTagMatched = 0;
				}
			}*/
			//Meta Tags till


		} catch (Exception e) {
			//System.out.println("StoryHelper getProfileLink Exception is :" + e);
			
			System.out.println("StoryHelper getProfileLink Exception is :" + e.getMessage());
			
			
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return description;
	}
public static ArrayList getBylineData(int storyId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byLineDTO aDTO = null;	
		ArrayList storyList = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT jap.id,jap.author_name,jap.author_email,jap.profile_image,jap.designation,jap.full_description,jap.sef_url,jap.twitter_id,jap.facebook_id,jap.profile_type FROM `jos_content_author_relation` jar,`jos_author_profile` jap WHERE jar.content_id=? AND jar.author_id=jap.id AND jap.published='1' AND jar.published='1' AND jar.content_type='0' ORDER BY `ordering` DESC";	    	
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, storyId);
	    	rs = pstmt.executeQuery();
			while (rs.next ()){
				aDTO = new byLineDTO();
				aDTO.setId(rs.getInt("id"));
				aDTO.setAuthor_name(rs.getString("author_name"));
				aDTO.setAuthor_email(rs.getString("author_email"));
				aDTO.setProfile_image(rs.getString("profile_image"));
				aDTO.setDesignation(rs.getString("designation"));
				aDTO.setFull_description(rs.getString("full_description"));
				aDTO.setSef_url(rs.getString("sef_url"));
				aDTO.setTwitter_id(rs.getString("twitter_id"));
				aDTO.setFacebook_id(rs.getString("facebook_id"));
				aDTO.setProfile_type(rs.getString("profile_type"));
		        storyList.add(aDTO);
			}
		} catch (Exception e) {
			System.out.println("StoryHelper storybyline Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return storyList;
	}
%><% 
String baseUrl = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()))+"/";
//baseUrl="http://indiatoday.intoday.in/";	
int storyId = 346460;
int currentStoryPageNo = 1;
if(request.getAttribute("currentStoryId")!=null)
	storyId = (Integer)request.getAttribute("currentStoryId");
	
if(request.getAttribute("currentStoryPageNo")!=null)
	currentStoryPageNo = (Integer)request.getAttribute("currentStoryPageNo");
if(request.getParameter("complete")!=null)
	currentStoryPageNo = 0;
%><cache:cache key="<%=Constants.cacheURL+"story/"+currentStoryPageNo+"/"+storyId%>" scope="application " time="0" refresh="t" ><%
ArrayList resultRelated = null;
String vuukleSectionTags ="";
String VuukleAuthorData="";
String expertComment = null;
int expertCommentLength = 25;
String storyByline = null;
String courtesy = null;
String largeKickerImage =null;
String kickerImage =null;
String smallImage =null;
String largeKickerImageCaption = null;
String largeKickerImageAltText = null;
String storyTitle = null;
String storyTitleAlias = null;
String storyTitleMagazine = null;
String storySefTitle = null;
String storyBody = null;
String pageMetaTitle = null;
String pageMetaKeywords = null;
String pageMetaDescription = null ;
String topNavigationPath = null ;
String rightNavigationPath = null ;
String bottomNavigationPath = null ;
String leftNavigationPath = null ;
int checkPagebreak = 0;
String pageMetaTags = null;
int issueId = 0;
int sectionId = 0;
String sectionTitle  = null ;
String sectionSefTitle  = null ;
int primaryCategoryId = 0;
String primaryCategoryTitle  = null ;
String primaryCategorySefTitle  = null ;
String storyPrimaryCategory = null;
int primaryCategoryLength = 0;
String storyPrimaryCatId = null;
String contentUrl=null;
int isExternal=0;
String  externalUrl=null;
int syndicate = 0;
String syndicateDate = null;
String syndicateByline = null;
String syndicateTitle = null;
String cacheErrMsg = request.getParameter("message");
if(cacheErrMsg==null)
cacheErrMsg = null;
int storyIcon = 0;
String storyIconImage = null;
String headerImage = null;
int googleStandout = 0;
int commentboxFlag = 1;
String extraLargeImage =null;
String primarySectionId = "";
ArrayList storyData = null;
String newsKeywords=null;
int medium=0;
String twitterHandle=null;
int    storyHighlightsFlag=0;
String  storyHighlights=null;
String shortDescription = "";
storyData = (ArrayList) StoryHelper.storyDetail(storyId, 1);
//out.println("storyData.size()->"+storyData.size());
if (storyData != null && storyData.size() > 0) {
	for (int i = 0; i < storyData.size(); i++) {
		StoryDTO aDTO = (StoryDTO) storyData.get(i);
		storyId = aDTO.getId();
		storyTitle = aDTO.getTitle();
		storyTitleAlias = aDTO.getTitleAlias();
		storyTitleMagazine = aDTO.getTitleMagazine();
		storySefTitle = aDTO.getSefTitle();
		storyBody = aDTO.getLongDescription();
		headerImage = aDTO.getHeaderImage();
		medium=aDTO.getMedium();
		syndicate = aDTO.getSyndication();
		syndicateDate = aDTO.getSyndicationDate();
		syndicateByline = aDTO.getByline().replace(" ","+");
		storyByline = aDTO.getByline();
		if(storySefTitle!=null && storySefTitle.length()>0){
		if(storySefTitle.contains(".html")){
		syndicateTitle = storySefTitle.replace(".html","");
		}}
		storyIcon = aDTO.getStoryIcon();
		storyIconImage = aDTO.getStoryIconImage();
		storyPrimaryCatId = aDTO.getPrimaryCategory(); 
		topNavigationPath = aDTO.getTopNavigationPath();
		rightNavigationPath = aDTO.getRightNavigationPath();
		bottomNavigationPath = aDTO.getBottomNavigationPath();
		leftNavigationPath = aDTO.getLeftNavigationPath();
		storyPrimaryCategory = aDTO.getPrimaryCategory();
		primaryCategoryLength = aDTO.getPrimaryCategoryLength();
		contentUrl=aDTO.getContentUrl();
 		isExternal=aDTO.getIsExternal();
  		externalUrl=aDTO.getExternalUrl();//not null no index
		courtesy = aDTO.getCourtesy();
		largeKickerImage = aDTO.getLargeKickerImage();
		kickerImage = aDTO.getKickerImage();
		smallImage = aDTO.getSmallImage();
		largeKickerImageCaption = aDTO.getLargeKickerImageCaption();
		largeKickerImageAltText = aDTO.getLargeKickerImageAltText();
		issueId = aDTO.getIssueId();
		twitterHandle=aDTO.getTwitterHandle();
		//System.out.println("issueId->"+issueId);		
		storyHighlightsFlag=aDTO.getStoryHighlightsFlag();
		storyHighlights=aDTO.getStoryHighlights();
		sectionId = aDTO.getSectionId();
		sectionTitle = aDTO.getSectionTitle();
		sectionSefTitle = aDTO.getSectionSefTitle();
		primaryCategoryTitle = aDTO.getSectionTitle();
		shortDescription=aDTO.getShortDescription();
		if(primaryCategoryLength == 2) {
			primaryCategoryId = aDTO.getCategoryId();
			primaryCategoryTitle = aDTO.getCategoryTitle();
			primaryCategorySefTitle = aDTO.getCategorySefTitle();
		}
		if(primaryCategoryLength == 3) {
			primaryCategoryId = aDTO.getSubCategoryId();
			primaryCategoryTitle = aDTO.getSubCategoryTitle();
			primaryCategorySefTitle = aDTO.getSubCategorySefTitle();
		}
		if(primaryCategoryLength == 4) {
			primaryCategoryId = aDTO.getSubSubCategoryId();
			primaryCategoryTitle = aDTO.getSubSubCategoryTitle();
			primaryCategorySefTitle = aDTO.getSubSubCategorySefTitle();
		}
		if(issueId==0) {
			pageMetaTitle = (storyTitleAlias + " : " + primaryCategoryTitle + ", News - India Today").replaceAll("\\<.*?>","");
		} else {
			pageMetaTitle = (storyTitleAlias + " : " + primaryCategoryTitle + " - India Today").replaceAll("\\<.*?>","");
		}
		pageMetaKeywords = aDTO.getMetaKeyword().replaceAll("\\<.*?>","");
		pageMetaTags = aDTO.getMetaTags();
		if(pageMetaKeywords.trim().length()==0)
			pageMetaKeywords = pageMetaTags;

		pageMetaDescription = aDTO.getMetaDescription().replaceAll("\\<.*?>","");
		request.setAttribute("idForAd", storyPrimaryCatId);
		expertComment = aDTO.getExpertComment();
		//System.out.println("expertComment->"+expertComment);
		if(expertComment.length() <= expertCommentLength)
			expertCommentLength = expertComment.length(); 

		googleStandout = aDTO.getGoogleStandout();
		commentboxFlag = aDTO.getCommentboxFlag();
		extraLargeImage = aDTO.getExtraLargeImage();
		
		//System.out.println("@@@@@@@-->"+extraLargeImage);
		
		if(storyPrimaryCategory.indexOf("#") > 0) {
			primarySectionId = storyPrimaryCategory.substring(0,storyPrimaryCategory.indexOf("#"));
		} else {
			primarySectionId = storyPrimaryCategory;
		}
	}



if(storyBody.trim().indexOf("{relateds}") >= 0) {}else{
request.setAttribute("idForRelated", ""+storyId); 
}
if(storyBody.indexOf("mospagebreak") > 0)
	checkPagebreak = 1;

if(pageMetaTitle.trim().length() <= 0) {
	pageMetaTitle = "India Today: India News, Latest India News, Breaking News India, News in India, World, Business, Cricket, Sports, Bollywood News India : News India Today";
}

String[] storyBodyDisplay = null;
int relatedFeaturedVideo=0;
int relatedFeaturedPhoto=0;
resultRelated = (ArrayList) CommonFunctions.relatedContent(""+storyId, "text");
if (resultRelated != null && resultRelated.size() > 0) {
	for (int ctr = 0; ctr < resultRelated.size(); ctr++) {
		RelatedContentDTO rcDTO = (RelatedContentDTO) resultRelated.get(ctr);
		if(rcDTO.getRelatedType().trim().equals("video") && rcDTO.getFeatured() >0) {
			relatedFeaturedVideo = rcDTO.getRelatedContentId();
		}if(rcDTO.getRelatedType().trim().equals("photo") && rcDTO.getFeatured() >0) {
			relatedFeaturedPhoto = rcDTO.getRelatedContentId();
		}	
	}
}
String keywordLength[]=null;
int finalKeywordLength=0;
if(pageMetaKeywords!=null && !pageMetaKeywords.equals("")){ 					
if(pageMetaKeywords.contains(",")){	       
keywordLength = pageMetaKeywords.split("\\,");
if(keywordLength.length>=6)
finalKeywordLength=6;
else
finalKeywordLength=keywordLength.length;
for(int cid=0;cid<finalKeywordLength;cid++){
newsKeywords=newsKeywords+keywordLength[cid]+",";	    	
}
}else{
newsKeywords=pageMetaKeywords;
}
}
ArrayList bylineData=null;
String gaAuthorData ="";
String gaAuthorDatacomma =""; 
bylineData = (ArrayList) getBylineData(storyId);

if (bylineData != null && bylineData.size() > 0) {
for (int bylineDataID = 0; bylineDataID < bylineData.size(); bylineDataID++) {
	byLineDTO bylineDto = (byLineDTO) bylineData.get(bylineDataID);	
if( bylineDto.getAuthor_name()!=null && !bylineDto.getAuthor_name().equals("")) { 

gaAuthorData+=gaAuthorDatacomma+bylineDto.getAuthor_name();
gaAuthorDatacomma=",";
}}}else{
	if(storyByline!=null && !storyByline.equals("")) {
		gaAuthorData=storyByline;
	}
}
	
%><!DOCTYPE html>
<html>
<head>
<base href="<%=baseUrl%>"/>
<meta charset="utf-8">
<title><%=pageMetaTitle%></title>
<meta name="description" content="<%=pageMetaDescription.replace("{table}","").replace("mospagebreak","").replace("{","").replace("}","").replace("mosimage","").replace("blurb","").replace("quote","").replace("funfacts","").replaceAll("<[^/bp][^>]*>|<p[a-z][^>]*>|<b[^r][^>]*>|<br[a-z][^>]*>|</[^bp]+>|</p[a-z]+>|</b[^r]+>|</br[a-z]+>","").replaceAll( "</?a[^>]*>", "" ).replaceAll( "</?font[^>]*>", "" ).replaceAll( "<br /><br /><br /><br />", "<br /><br />" ).replaceAll( "<br /><br /><br />", "<br /><br />" ).replaceAll( "\"", "-" )%>" />
<meta name="news_keywords" content="<%=pageMetaKeywords%>"/>
<!-- GA-START -->
<script src="http://cdn51.vizury.com/pam/api/pam.js?name=PAM_IND_PC_IndiaToday&id=7075&v=1.2&geo=ap"></script>
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
  <img src="http://b.scorecardresearch.com/p?c1=2&amp;ac2=8549097&amp;cv=2.0&amp;cj=1"/>
</noscript>
<!-- End comScore Tag -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-795349-17', 'auto');<%if(gaAuthorData!=null &&!gaAuthorData.equals("")&&!gaAuthorData.equals("null")  ) {%>
  ga('set', 'dimension1', '<%=gaAuthorData.trim()%>');<%}%>
  ga('set', 'dimension2', '<%=primaryCategoryTitle%>');
  ga('set', 'dimension3', '<%=sectionTitle%>');
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
<!-- GA-END -->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link href='http://fonts.googleapis.com/css?family=Roboto:100,300,700,500,400,900' rel='stylesheet' type='text/css'/>
<link rel="stylesheet" type="text/css" href="css/it-common-15.css"/>
<link rel="stylesheet" type="text/css" href="css/it-inner-15.css"/>
<link href="css/it-responsive-15.css" type="text/css" rel="stylesheet"/>
<link rel='shortcut icon' type='image/x-icon' href='http://media2.intoday.in/indiatoday/images/dot-in-fav-icon.ico' />
<script src="js/sitejs.js"></script>
<%if(googleStandout == 1) { %><link rel="standout" href="<%=Constants.SITE_URL+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>"/>
<%} if(sectionId == 109 || sectionId == 62 || sectionId == 204) { %><meta name="robots" content="noindex, nofollow" />
<%} if(externalUrl!=null && !externalUrl.equals("") && !externalUrl.equals("null")) { %><meta name="robots" content="noindex, nofollow"/><%} %><%if(storyId == 344237){%><meta name="robots" content="noindex, nofollow" /><%}%>
<meta name="twitter:card" content="summary"/>
<meta name="twitter:site" content="@indiatoday"/>
<meta name="twitter:creator" content="@indiatoday"/>
<meta name="twitter:url" content="<%=Constants.SITE_URL+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>"/>
<meta name="twitter:title" content="<%=storyTitleAlias.replace("{table}","").replace("mospagebreak","").replace("{","").replace("}","").replace("mosimage","").replace("blurb","").replace("quote","").replace("funfacts","").replaceAll("<[^/bp][^>]*>|<p[a-z][^>]*>|<b[^r][^>]*>|<br[a-z][^>]*>|</[^bp]+>|</p[a-z]+>|</b[^r]+>|</br[a-z]+>","").replaceAll( "</?a[^>]*>", "" ).replaceAll( "</?font[^>]*>", "" ).replaceAll( "<br /><br /><br /><br />", "<br /><br />" ).replaceAll( "<br /><br /><br />", "<br /><br />" ).replaceAll( "\"", "-" )%>"/>
<meta name="twitter:description" content="<%=pageMetaDescription.replace("{table}","").replace("mospagebreak","").replace("{","").replace("}","").replace("mosimage","").replace("blurb","").replace("quote","").replace("funfacts","").replaceAll("<[^/bp][^>]*>|<p[a-z][^>]*>|<b[^r][^>]*>|<br[a-z][^>]*>|</[^bp]+>|</p[a-z]+>|</b[^r]+>|</br[a-z]+>","").replaceAll( "</?a[^>]*>", "" ).replaceAll( "</?font[^>]*>", "" ).replaceAll( "<br /><br /><br /><br />", "<br /><br />" ).replaceAll( "<br /><br /><br />", "<br /><br />" ).replaceAll( "\"", "-" )%>"/>
<meta name="twitter:app:name:iphone" content="India Today Live"/>
<meta name="twitter:app:id:iphone" content="510733452"/>
<meta name="twitter:app:url:iphone" content="https://itunes.apple.com/us/app/india-today-live/id510733452?ls=1&amp;mt=8"/>
<meta name="twitter:app:name:ipad" content="India Today Live for iPad"/>
<meta name="twitter:app:id:ipad" content="560404098"/>
<meta name="twitter:app:url:ipad" content="https://itunes.apple.com/us/app/india-today-live-for-ipad/id560404098?ls=1&amp;mt=8"/>
<meta name="twitter:app:name:googleplay" content="India Today"/>
<meta name="twitter:app:id:googleplay" content="com.indiatoday"/>
<meta name="twitter:app:url:googleplay" content="https://play.google.com/store/apps/details?id=com.indiatoday&amp;hl=en"/>
<% if(largeKickerImage!=null && !largeKickerImage.equals("")) { %>
<meta name="twitter:image" content="<%=Constants.STORY_IMG_PATH+largeKickerImage%>"/>
<meta property="og:image" content="<%=Constants.STORY_IMG_PATH+largeKickerImage%>"/>
<link rel="image_src" href="<%=Constants.STORY_IMG_PATH+largeKickerImage%>"/>
<%} else {%>
<meta name="twitter:image" content="http://media2.intoday.in/indiatoday/it-logo-200x200-white.gif"/> 
<meta property="og:image" content="http://media2.intoday.in/indiatoday/it-logo-200x200-white.gif"/>
<link rel="image_src" href="http://media2.intoday.in/indiatoday/it-logo-200x200-white.gif"/>
<%}%>
<link rel="canonical" href="<%=Constants.SITE_URL+CommonFunctions.storyURL(storySefTitle, 1, storyId) %>"/>
<!-- html5.js for IE less than 9 -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!-- css3-mediaqueries.js for IE less than 9 -->
<!--[if lt IE 9]>
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<style type="text/css">
@media screen and (max-width:800px){
.alpha{margin-top:100px;}}
.share-story ul { display:block} 
.share-story li {padding: 0 9px 0 0;}
.factolds ul{padding-top:0px !important;}
</style>

<%if(rightNavigationPath!=null&&rightNavigationPath.trim().length() != 0) { %>
<style type="text/css">
#spc-topnav { margin: 0 auto; width: 1003px; }
#spc-rightnav {width:300px; float:right; text-align:left}
.commenttxt span{float:left;margin-right: 10px;}
.commenttxt h4{float:left;}
.commenttxt{margin-top:10px;}
</style>
<%} %>

<script type="text/javascript">
window._taboola = window._taboola || [];
_taboola.push({article:'auto'});
!function (e, f, u) {
e.async = 1;
e.src = u;
f.parentNode.insertBefore(e, f);
}(document.createElement('script'), document.getElementsByTagName('script')[0], 'http://cdn.taboola.com/libtrc/indiatoday-indiatoday/loader.js');
</script>

<!--Newsroom Tabolla Ad-->
<script>
window._newsroom = window._newsroom || [];
!function (e, f, u) {
e.async = 1;
e.src = u;
f.parentNode.insertBefore(e, f);
}(document.createElement('script'),
document.getElementsByTagName('script')[0], '//c2.taboola.com/nr/indiatoday-indiatoday/newsroom.js');
</script>
<%if(medium==1){ %>
<script type="text/javascript">
$(document).ready(function(){
   $(".mediumcontent > p").mouseover(function(){
     $(this).find('.com-icon').show();
   });   
   
    $(".mediumcontent > p").mouseout(function(){
    // $(this).find('.com-icon').hide();
   });

	$(".msg-box").css("display", "none");
	
	$(".com-icon").click(function(){
		$('.msg-box').hide();
		var boxiid=$(this).attr('boxid');
		var boiddd = '#boxie_'+$(this).attr('boxid');
		//alert(boxiid+boiddd+$(boiddd).html().length)
		//if($(boiddd).html().length<=0){
			var commntbx = showCommentBox('<%=storyId%>',boxiid);
			$(boiddd).html(commntbx);
			//call comments from ajax here below fuck it ,fuck the bitch 
			//alert("@@@@@@@@@@@@@@");
				showComments("<%=storyId%>",boxiid);
			 
			<%
			try{
//System.out.println("####"+session.getAttribute("twitter"));			
if(null != session.getAttribute("twitter")){
Twitter twitetr=(Twitter)session.getAttribute("twitter");
String twitetrScreenName="";
 twitetrScreenName=twitetr.getScreenName();
 User user = twitetr.showUser(twitetr.getId());
            String userurl= user.getProfileImageURL();
%> 
$('input[name=fname]').val("@<%=twitetrScreenName%>");
$('input[name=email]').val("dev.support@intoday.com");
$('input[name=sn_image]').val("<%=userurl%>");
$('input[name=sn_source]').val("twitter");
$('#user-info').html('<img class="imgs" src="<%=userurl%>">@<%=twitetrScreenName%>');
$("#facebook").html('<a href="twitterlogout.jsp">Logout</a>');
<%
}
}catch(Exception e){
 //e.printStackTrace();
}
%>
		//}
		$('.strleft p.common-p').css({'width':'660px'});		
		captcha_reload_separte($(this).attr('boxid'));		
		$(this).next('span').show(10);	
		
	});
   
   $('.close').click(function(){
	   $('.msg-box').hide(10);
	    $('.strleft p.common-p').css({'width':'932px'});
	});	
	
//$( "span:contains('+')" ).addClass('plusicon');
$( "span:contains('+')" ).css('display', 'none');
$('.mediumcontent > p > span:first-child ').addClass('plusicon');
$('.mediumcontent > p').mouseover(function(){
 $(this).children("span.plusicon").show();
});
$('.mediumcontent > p').mouseout(function(){
 $(this).children("span.plusicon").hide();
});

/*
var x=$(".mediumcontent .right-story-container p:eq(0)").html();var texts='<span class="fChar">'+x.charAt(0)+'</span>';$(".mediumcontent .right-story-container p:eq(0)").html(texts+x.slice(1,x.length));
*/

});
</script> 
<style type="text/css">
.strleft{width:925px !important;}
.marg10px{padding-left:5px;}
p { display:block; position:relative  }
.con{width:600px; margin:auto}
.common-p{position:relative;}
.com-icon{width:30px; height:22px; color:white; padding:0; line-height:19px; padding-left:3px; background:url(http://media2.intoday.in/indiatoday/images/comBox.jpg) right center no-repeat; display:block;  position:absolute; top:15px; right:-6px; cursor:pointer; opacity:0.5l; margin-right:-25px;  text-align:center; font-family:arial; font-weight:bold; font-size:12pt;}
.com-icon:hover { opacity:1}
.msg-box{width:270px;  background-color:#fff; display:none; border:2px solid #ccc !important; border-radius:15px; padding:10px; position:absolute; right:-300px; top:0px; z-index:999;  font-family:roboto; font-size:16px;  }
.close {
background: none repeat scroll 0 0 #fff;
border-radius: 20px;
color: #ccc;
float: right;
font-family: roboto;
font-size: 14px;
font-weight: bold;
margin: -22px;
padding: 0 8px;
cursor: pointer;
border: 1px solid #ccc;
}
</style>
<%} %>
<style type="text/css">
H1, h2 {
    background-color:none !important;
}
.twitterhandle iframe{ margin-bottom:-7px !important;}
.strleft { padding-left:0px;} 
</style>

<script type="text/javascript" src="http://adcdn.forkmedia.in/impulse3/intodayarticle.impulse.js" defer='defer'></script>
<!--<script src="http://adserver.brandgeni.com/ad-server/js/ad-server-bootstrap.js?domain=indiatoday.intoday.in">
</script>-->
</head>
<body>
<%if(request.getAttribute("javax.servlet.forward.request_uri")!=null){ %>
<link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.indiatoday.in<%=request.getAttribute("javax.servlet.forward.request_uri").toString()%>" />
<script type="text/javascript">
function canonicalUrlRedirection(){for(var e="",i=document.getElementsByTagName("link"),a=0;a<i.length;a++)if("alternate"==i[a].getAttribute("rel")){e=i[a].getAttribute("href");break}""!=e&&(window.location.href=e)}var redirectagent=navigator.userAgent.toLowerCase(),mode=window.location.toString().split("mode=")[1],redirect_devices=["vnd.wap.xhtml+xml","sony","symbian","S60","SymbOS","nokia","samsung","mobile","windows ce","epoc","opera mini","nitro","j2me","midp-","cldc-","netfront","mot","up.browser","up.link","audiovox","blackberry","ericsson","panasonic","philips","sanyo","sharp","sie-","portalmmm","blazer","avantgo","danger","palm","series60","palmsource","pocketpc","smartphone","rover","ipaq","au-mic","alcatel","ericy","vodafone","wap1","wap2","teleca","playstation","lge","lg-","iphone","android","htc","dream","webos","bolt","nintendo"];if(!(redirectagent.indexOf("ipad")>=0))for(var i in redirect_devices)-1!=redirectagent.indexOf(redirect_devices[i])&&"classic"!=mode&&canonicalUrlRedirection();
</script>
<%} %>
<script type="text/javascript"> 
	var primaryCategoryJS = '<%=storyPrimaryCategory%>';
	if(primaryCategoryJS.indexOf("#") > 0) {
		if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) == "204"){ 
			window.location.href = "<%=Constants.SITE_URL+"education/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
			}else if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) == "229") {
			window.location.href = "<%=Constants.SITE_URL+"technology/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
			} else if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) == "230") {
			window.location.href = "<%=Constants.SITE_URL+"auto/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
			} else if(primaryCategoryJS.substring(0, primaryCategoryJS.indexOf("#")) == "340") {
			window.location.href = "<%=Constants.SITE_URL+"money/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
			} } else if(primaryCategoryJS == "204") {
			window.location.href = "<%=Constants.SITE_URL+"education/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
	} else if(primaryCategoryJS == "229") {
			window.location.href = "<%=Constants.SITE_URL+"technology/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
	} else if(primaryCategoryJS == "230") {
			window.location.href = "<%=Constants.SITE_URL+"auto/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
	} else if(primaryCategoryJS == "340") {
			window.location.href = "<%=Constants.SITE_URL+"money/"+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>";
	}
</script>
<div class="skinnin-ad-container"> 
<%if(rightNavigationPath!=null&&rightNavigationPath.trim().length() == 0) { %>
<%@include file="navigation_topnav_2015.jsp"%><%}else{ %>
<div id="spc-topnav"><jsp:include page="<%=topNavigationPath%>" flush="true" /></div>
<%} %>
<%if(relatedFeaturedVideo>0) { 
String videoFileNameComplete = "";
String videoFileFolderName = "";
String mp4videoWebPath = "";
int mp4videoFlag = 0;
String[] videoPageData = StoryHelper.relatedVideoDetail(relatedFeaturedVideo);
if (videoPageData.length>0) {
	videoFileNameComplete = videoPageData[1];
	if(videoFileNameComplete.trim().length() == 0){
		videoFileNameComplete=videoPageData[2];
	}
	videoFileFolderName = videoPageData[0];
	mp4videoFlag = Integer.parseInt(videoPageData[3]);
}
StringBuffer videoFilePathSB_CS=new StringBuffer();
ArrayList videoFilesAL = (ArrayList) VideoHelper.videoFileNameAL(videoFileNameComplete);
int noofvideos = videoFilesAL.size();
if (videoFilesAL != null && videoFilesAL.size() > 0) {
	for (int ctr = 0; ctr < videoFilesAL.size(); ctr++) {
		if(videoFileNameComplete.contains("http://")){
			videoFilePathSB_CS.append('"'+(String)videoFilesAL.get(ctr)+'"');
		}else{
			//videoFilePathSB_CS.append('"'+Constants.VIDEO_PATH);
			videoFilePathSB_CS.append('"'+"http://mediacloud.intoday.in/indiatoday/video/");
			if(videoFileFolderName.trim().length()>0) {
				videoFilePathSB_CS.append(videoFileFolderName+"/");
			}
			videoFilePathSB_CS.append((String)videoFilesAL.get(ctr)+'"');
		}
		if(ctr!=videoFilesAL.size()-1)
			videoFilePathSB_CS.append(",");
	}
}
int part = 0,ik=0;
String currentVideoFileName = "";
if(videoFilePathSB_CS.indexOf(",") > 0) {
	String[] videoFilesSA1 = videoFilePathSB_CS.toString().split(",");
	currentVideoFileName = videoFilesSA1[part].replaceAll("\"", "");
} else {
	currentVideoFileName = videoFilePathSB_CS.toString().replaceAll("\"", "");
}
String currentVideoFileType = "";
if(currentVideoFileName.length() > 0 && currentVideoFileName.indexOf(".") > 0) {
	currentVideoFileType=currentVideoFileName.substring(currentVideoFileName.lastIndexOf(".")+1,currentVideoFileName.length());
}
if(currentVideoFileType.equals("flv") || currentVideoFileType.equals("mp4")) {
	int streamer=1;
	String arrPlaylistnew[]={videoFilePathSB_CS.toString()};
	String splittedArray[]=arrPlaylistnew[0].split(",");
	String mp4videoWebPathnew[]=new String [splittedArray.length];
	String mp4videonew[]=new String [splittedArray.length];
	String ThreeGPvideoPathFinalnew[]=new String [splittedArray.length];
	String mp4basepath = "";
	String mp4path = "";
	String ThreeGPvideoPath="";
	String ThreeGPvideoPath2="";
	String ThreeGPvideoPath3="";
	String ThreeGPvideoPathFinal="";
	for(int z=0;z<splittedArray.length;z++){
	if(splittedArray[z].indexOf(".mp4") >= 0 && (splittedArray[z].indexOf("videodeliverys3") >= 0 || splittedArray[z].indexOf("medias3d") >= 0)) {
		mp4videoWebPathnew[z]  = "mp4:"+splittedArray[ik].replace("http://videodeliverys3.s3.amazonaws.com/", "").replace("http://medias3d.intoday.in/", "").replaceAll("\"","");
		}else{
			mp4videoWebPathnew[z] = "mp4:"+splittedArray[z].replace("http://videodeliverys3.s3.amazonaws.com/", "").replace("http://medias3d.intoday.in/", "").replace(".flv", ".mp4").replaceAll("\"","");
		}
		if(splittedArray[z].indexOf(".flv") > 0 && mp4videoFlag==0)
		{
			mp4videoWebPathnew[z]=splittedArray[z];
		streamer=0;
		}
		else{	if(splittedArray[z].indexOf("mediacloud.intoday.in") > 0) {
			mp4videoWebPathnew[z] = "mp4:"+splittedArray[z].replace("http://mediacloud.intoday.in/", "").replace(".flv", ".mp4").replaceAll("\"","");
			mp4videoWebPathnew[z] = mp4videoWebPathnew[z].substring(0, mp4videoWebPathnew[z].lastIndexOf("_")) + ".mp4";
							streamer=1; 
							} else {
								mp4videoWebPathnew[z] = "mp4:"+splittedArray[ik].replace("http://videodeliverys3.s3.amazonaws.com/", "").replace("http://medias3d.intoday.in/", "").replace(".flv", ".mp4").replaceAll("\"","");
							streamer=1; 
							}
						}
		if(!videoPageData[2].equals("")) {
			if(splittedArray[z].indexOf(".flv") > 0) {
				splittedArray[z] = splittedArray[z].substring(0, splittedArray[z].lastIndexOf("."))+".mp4";
			}
			mp4videonew[z] = splittedArray[z];
			if(mp4videonew[z].indexOf("http://videodeliverys3.s3.amazonaws.com/indiatoday/video/") >= 0) {
				mp4videonew[z] = mp4videonew[z].replaceAll("http://videodeliverys3.s3.amazonaws.com/indiatoday/video/", "http://d1jhvk1xjm92rd.cloudfront.net/vods3/_definst_/mp4:amazons3/videodeliverys3/indiatoday/video/");
			}
			if(mp4videonew[z].indexOf("http://mediacloud.intoday.in/indiatoday/video/") >= 0) {
				mp4videonew[z] = mp4videonew[z].replaceAll("http://mediacloud.intoday.in/indiatoday/video/", "http://d1jhvk1xjm92rd.cloudfront.net/vods3/_definst_/mp4:amazons3/videodeliverys3/indiatoday/video/");
			}
		} else {
			//mp4basepath = "http://videodeliverys3.s3.amazonaws.com/indiatoday/video/";
			mp4basepath = "http://d1jhvk1xjm92rd.cloudfront.net/vods3/_definst_/mp4:amazons3/videodeliverys3/indiatoday/video/";
			if(splittedArray[z].indexOf(".flv") > 0) {
				mp4path = splittedArray[z].substring(splittedArray[z].lastIndexOf("/")+1, splittedArray[z].lastIndexOf("_"))+".mp4";
			} else {
				mp4path = splittedArray[z].substring(splittedArray[z].lastIndexOf("/")+1, splittedArray[z].length());
			}
			mp4videonew[z] = mp4basepath+""+videoFileFolderName+"/"+mp4path;
		}
		if(mp4videonew[z].indexOf("http://d1jhvk1xjm92rd.cloudfront.net/vods3/_definst_/mp4:amazons3/videodeliverys3") >= 0) {
			mp4videonew[z] = mp4videonew[z] + "/playlist.m3u8";
		} 
		 if(splittedArray[z].toString().indexOf(".flv")>0){
	         ThreeGPvideoPath=splittedArray[z].replace(".flv", ".3gp");
	         }
	         else if(splittedArray[z].indexOf(".mp4")>0)
	         {
	       	  ThreeGPvideoPath=splittedArray[z].replace(".mp4", ".3gp");
	         }
	         if(ThreeGPvideoPath.contains("media2.intoday.in")){
	         ThreeGPvideoPathFinal=ThreeGPvideoPath.replace("http://media2.intoday.in","http://mediacloud.intoday.in");
	         }
	         else{
	       int lastslash=  ThreeGPvideoPath.lastIndexOf("/");
	        ThreeGPvideoPath2=ThreeGPvideoPath.substring(0,lastslash)+"/3gp";
	        ThreeGPvideoPath3=ThreeGPvideoPath.substring(lastslash,ThreeGPvideoPath.length());
	        ThreeGPvideoPathFinalnew[z]=ThreeGPvideoPath2+ThreeGPvideoPath3;
	       }
	}
	
%>
<script type="text/javascript" src="http://media2.intoday.in/microsites/affle-player/jwplayer/jwplayer.js"></script>
<script type="text/javascript" src="http://media2.intoday.in/microsites/affle-player/jwplayer/ripple.js"></script>
<script type="text/javascript">
	var currentItem=<%=part%>;
	var vdopiavideoid=<%=relatedFeaturedVideo%>;
	var arrPlaylist=[<%=videoFilePathSB_CS.toString()%>];
	var autoplay="true";
	var mp4videoFlagJS = <%=mp4videoFlag%>;
            $(document).ready(function() {
                jwplayer("player").setup({
                    'flashplayer': "http://media2.intoday.in/microsites/affle-player/jwplayer/player.swf",
                    'controlbar.position': 'bottom',
                    'image': '<%=Constants.STORY_IMG_PATH+largeKickerImage%>',
		        	'autostart': true,
				    'stretching': 'uniform',
					'skin': 'http://media2.intoday.in/microsites/affle-player/newtubedark1/NewTubeDark.xml',	 
					<% if(splittedArray.length==1){ %>
					'width': 622,
                    'height': 386,
                    <% if(streamer==1){ %>
                    'provider': "rtmp",
		 'streamer': "rtmp://mediaclouds3.intoday.in:80/cfx/st",
		 <%}%>
				 'file': '<%=mp4videoWebPathnew[0].replaceAll("\"","")%>',					
					'modes': [
        {type: 'flash', src: 'http://media2.intoday.in/microsites/affle-player/jwplayer/player.swf',
        config: {
		 'file': '<%=mp4videoWebPathnew[0].replaceAll("\"","")%>'
		}
		},
		{
          type: 'html5',
          config: {
          
           'file': '<%=mp4videonew[0].replaceAll("\"","")%>',
           'provider': 'video'
          }
        },
		{
          type: 'download',
          config: {
           'file': '<%=ThreeGPvideoPathFinalnew[0].replaceAll("\"","")%>',
           'provider': 'video'
          }
        }
    ],   
    <%} else { %>
    	'width': 622,
                    'height': 386,
    'playlist': [
    	<% for(int ctr=0; ctr < splittedArray.length; ctr++) {%>
                    {'title': "Part<%=ctr+1%>: <%=storyTitleAlias%>  ",
                    'image': '<%=Constants.STORY_IMG_PATH+largeKickerImage%>',
                    
                    <% if(streamer==1){ %>
		 'streamer': "rtmp://mediaclouds3.intoday.in:80/cfx/st",
		 'provider': 'rtmp',
		 <%}%>
		 file: '<%=mp4videoWebPathnew[ctr].replaceAll("\"","")%>' }
		 <% if(ctr+1<splittedArray.length) out.print(","); }%>
    		],modes: [
          { 'type': 'flash', 'src': 'http://media2.intoday.in/microsites/affle-player/jwplayer/player.swf'},
		{
          type: 'html5',
		  config: {'playlist': [
		   <% for(int ctr2=0; ctr2 < splittedArray.length; ctr2++) { %>
		    {'title': "Part<%=ctr2+1%>: <%=storyTitleAlias%>  ",
			 'image': "<%=Constants.STORY_IMG_PATH+largeKickerImage%>", 
			
					'provider': "video",
			
			 file: '<%=mp4videonew[ctr2].replaceAll("\"","")%>'} 
			<% if(ctr2+1<splittedArray.length) out.print(",");  } %>]
			}
        },
		{
          type: 'download',
		  config: {
		     file: '<%=ThreeGPvideoPathFinalnew[0].replaceAll("\"","")%>',
		   'provider': 'video'
		  }
        }
    ], "playlist.position": "bottom",
        "playlist.size": 120,
    <% }%>
	'plugins': {
                        'http://media2.intoday.in/microsites/affle-player/jwplayer/ova-jw.swf': {
                        <% if(splittedArray.length>1){ %>
                        'allowPlaylistControl': true,
                        <%} %>
                        "debug": {
                     "levels": "none" },
						                            'ads': {
														'enforceLinearAdsOnPlaylistSelection': true,   
							  'companions': {
									'class':"storm-vast-companion",
                                    'restore': false,
                                    'regions': [
                                        {
                                        'id': "companion",
                                        'width': 300,
                                        'height': 250
                                        }
                                    ]
                                },
                                'vpaid': {
                                    'holdingClipUrl': "http://videodeliverys3.s3.amazonaws.com/indiatoday/video/jwplayer/blank.mp4"
                                },
                                'schedule': [
                                    {
                                    'position': "pre-roll",
                                    'tag': "http://xp1.zedo.com/jsc/d2/fns.vast?n=821&c=2040/1137&d=39&s=2&v=vast2&pu=__page-url__&ru=__referrer__&pw=__player-width__&ph=__player-height__&z=__random-number__",
                                    'duration': 25
                                    }
                                ]
                            }
                        }
                    }
                }); 
            });
	</script>
<%}}%>  
<!--Election From-->
<script type="text/javascript">ajaxinclude("chunk_story_top5stories.jsp")</script>
<div class="clear"></div>
<% if(storyId==372687||storyId==375189){ %>
<script type="text/javascript">
var axel = Math.random() + "";
var a = axel * 10000000000000;
document.write('<iframe src="http://4336058.fls.doubleclick.net/activityi;src=4336058;type=noofu681;cat=India0;ord=' + a + '?" width="1" height="1" frameborder="0" style="display:none"></iframe>');
</script>
<noscript>
<iframe src="http://4336058.fls.doubleclick.net/activityi;src=4336058;type=noofu681;cat=India0;ord=1?" width="1" height="1" frameborder="0" style="display:none"></iframe>
</noscript>
<!-- End of DoubleClick Floodlight Tag: Please do not remove -->
<% } %>
<% if(storyId==376537 || storyId==377645){ %>
<script type="text/javascript">
var axel = Math.random() + "";
var a = axel * 10000000000000;
document.write('<iframe src="http://4336058.fls.doubleclick.net/activityi;src=4336058;type=noofu681;cat=India0;ord=' + a + '?" width="1" height="1" frameborder="0" style="display:none"></iframe>');
</script>
<noscript>
<iframe src="http://4336058.fls.doubleclick.net/activityi;src=4336058;type=noofu681;cat=India0;ord=1?" width="1" height="1" frameborder="0" style="display:none"></iframe>
</noscript>
<!-- End of DoubleClick Floodlight Tag: Please do not remove -->
<% } %>
<%if(storyPrimaryCategory.indexOf("230#762")==0){%>
<div style="margin: 0 auto 15px;width: 1003px;"><a href="http://indiatoday.intoday.in/autoexpo/2014/"><img src="http://media2.intoday.in/indiatoday/images/autoexpo/topmast.jpg" width="1003" height="70" ></a></div>
<div class="clear"></div>
<%} 
if(storyPrimaryCategory.indexOf("103#1124")==0){%>
<div style="margin: 0 auto 15px;width: 1003px;"><a href="http://indiatoday.intoday.in/decor-special.jsp"><img src="http://media2.intoday.in/businesstoday/specials/decor-special-banner.gif" width="1003" height="70" ></a></div>
<div class="clear"></div>
<%} %>
<div>
<%if(storyPrimaryCategory.indexOf("84#926")==0){%>
<div style="margin: 10px auto 15px;width: 1003px;"><a target="_blank" href="http://www.asianpaints.com/ezycolour/index.aspx?utm_source=Indiatoday&amp;utm_medium=Bellyband&amp;utm_campaign=Ezycolour&amp;campaignID=2014-EZY-DIG"><img border="0" src="http://media2.intoday.in/indiatoday/banner/Banner.gif"></a></div>
<div class="clear"></div>
<%} %></div>
<div class="clear"></div>
<div id="main_container" style="position:relative; ">
<div style="width:1000px; margin:0 auto;">
<%if(storyPrimaryCategory.indexOf("84#926")==0){%>
<div><a target="_blank" href="http://www.asianpaints.com/ezycolour/index.aspx?utm_source=Indiatoday&amp;utm_medium=Bellyband&amp;utm_campaign=Ezycolour&amp;campaignID=2014-EZY-DIG"><img border="0" src="http://media2.intoday.in/btmt/asian-paints.jpg"></a></div>
<div class="clear"></div>
<%}else if(storyPrimaryCategory.indexOf("67#960")==0){%>
<div><img border="0" src="http://media2.intoday.in/microsites/mast/it/golden-globe-2015.JPG"></div>
<div class="clear"></div>
<%}else if(storyPrimaryCategory.indexOf("296#947")==0){%>
<div><a href="http://indiatoday.intoday.in/elections/assembly/2015/delhi/index.jsp" target="_blank"><img border="0" src="http://media2.intoday.in/indiatoday/election_delhi_2015/delhi_election2015.gif"></a></div>
<div class="clear"></div>
<%} else if(storyPrimaryCategory.indexOf("108")==0){%>
<div class="mailtoday-story"><img border="0" src="http://media2.intoday.in/indiatoday/images/homenew/mailtoday_Logo.png"></div>
<div class="clear"></div>
<%} %>
</div>
<div id="strwapper">
<div class="pathway" >
<div class="path" style="float:left;">
<%
out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathone\"><a href=\"http://indiatoday.intoday.in/\" itemprop=\"url\"><span itemprop=\"title\">News</span></a></div>");
if (storyData != null && storyData.size() > 0) {
for (int i = 0; i < 1; i++) {
	StoryDTO story = (StoryDTO) storyData.get(i);

			if(story.getIssueId() > 0) {	
				//out.print("<a href=\""+CommonFunctions.issueURL(story.getIssueId(), story.getIssueSefTitle())+"\">"+ story.getIssueTitle()+"</a>");
				out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathone\"><a href=\"archive\" itemprop=\"url\"><span itemprop=\"title\">Archive</span></a></div>");
				vuukleSectionTags ="Archive"; 
			}
			if(story.getSectionPageURL().trim().length() > 0) {
			
				
				if(story.getSectionId()==252 && story.getCategoryId()==904){
				////////// WOMAN SUMMIT 2014 URL /////////////
				out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathone\"><a itemprop=\"url\" href=\""+story.getCategoryPageURL().trim()+"\"><span itemprop=\"title\">"+story.getSectionTitle()+"</span></a>");
				vuukleSectionTags =story.getSectionTitle(); 
				
				}else if(story.getSectionId()==67){
				////////// Movie URL /////////////
				out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathone\"><a itemprop=\"url\" href=\"http://indiatoday.intoday.in/movies/\"><span itemprop=\"title\">"+story.getSectionTitle()+"</span></a>");
				vuukleSectionTags =story.getSectionTitle(); 
				
				}else{
				
				out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathone\"><a itemprop=\"url\" href=\""+story.getSectionPageURL().trim()+"\"><span itemprop=\"title\">"+story.getSectionTitle()+"</span></a>");
				vuukleSectionTags =story.getSectionTitle(); 
				}
				
				
				
				
			} else {
				if(story.getSectionId()==67){
				out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathone\"><a itemprop=\"url\" href=\"http://indiatoday.intoday.in/movies/\"><span itemprop=\"title\">"+story.getSectionTitle()+"</span></a>");
				vuukleSectionTags =story.getSectionTitle(); 
				}else{
				out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathone\"><a itemprop=\"url\" href=\""+Constants.SITE_BASEPATH+CommonFunctions.sectionURL(story.getSectionSefTitle(), 1, story.getSectionId())+"\"><span itemprop=\"title\">"+story.getSectionTitle()+"</span></a>");
				vuukleSectionTags =story.getSectionTitle(); 
				}
			}
			out.print("</div> ");
			if(primaryCategoryLength >= 2) {	
				if(story.getCategoryPageURL().trim().length() > 0) {
					out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathtwo\"><a itemprop=\"url\" href=\""+story.getCategoryPageURL().trim()+"\"><span itemprop=\"title\">"+ story.getCategoryTitle()+"</span></a>");
					vuukleSectionTags =story.getCategoryTitle(); 
				} else {
					out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathtwo\"><a itemprop=\"url\" href=\""+Constants.SITE_BASEPATH+CommonFunctions.categoryURL(story.getCategorySefTitle(), 1, story.getCategoryId())+"\"><span itemprop=\"title\">"+ story.getCategoryTitle()+"</span></a>");
					vuukleSectionTags =story.getCategoryTitle(); 
				}
				out.print("</div>");
			} 
			if(primaryCategoryLength >= 3) {	
				if(story.getSubCategoryPageURL().trim().length() > 0) {
					out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft paththree\"><a itemprop=\"url\" href=\""+story.getSubCategoryPageURL().trim()+"\"><span itemprop=\"title\">"+ story.getSubCategoryTitle()+"</span></a>");
					vuukleSectionTags =story.getSubCategoryTitle(); 
				} else {
					out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft paththree\"><a itemprop=\"url\" href=\""+Constants.SITE_BASEPATH+CommonFunctions.subCategoryURL(story.getSubCategorySefTitle(), 1, story.getSubCategoryId())+"\"><span itemprop=\"title\">"+ story.getSubCategoryTitle()+"</span></a>");
					vuukleSectionTags =story.getSubCategoryTitle();
				}
				out.print("</div>");
			} 
			if(primaryCategoryLength == 4) {	
				if(story.getSubSubCategoryPageURL().trim().length() > 0) {
					out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathfour\"><a itemprop=\"url\" href=\""+story.getSubSubCategoryPageURL().trim()+"\"><span itemprop=\"title\">"+ story.getSubSubCategoryTitle()+"</span></a>");
					vuukleSectionTags =story.getSubSubCategoryTitle();
				} else {
					out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathfour\"><a itemprop=\"url\" href=\""+Constants.SITE_BASEPATH+CommonFunctions.subSubCategoryURL(story.getSubSubCategorySefTitle(), 1, story.getSubSubCategoryId())+"\"><span itemprop=\"title\">"+ story.getSubSubCategoryTitle()+"</span></a>");
					vuukleSectionTags =story.getSubSubCategoryTitle();
				}
				out.print("&nbsp;/&nbsp;</div>");
			}
			if(story.getIssueId() > 0) {	
				//out.print("<a href=\""+CommonFunctions.issueURL(story.getIssueId(), story.getIssueSefTitle())+"\">"+ story.getIssueTitle()+"</a>");
				out.print("<div itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"lft pathfour\"><a itemprop=\"url pathfour\" href=\""+Constants.SITE_BASEPATH+CommonFunctions.issueURL(story.getIssueId(), "")+"\"><span itemprop=\"title\">"+ story.getIssueTitle()+"</span></a>");
				out.print("</div>");
				vuukleSectionTags =story.getIssueTitle();
			}
%><!--<div class="currentpath">Story  <img src="http://media2.intoday.in/indiatoday/images/bed-arrow.png" border="0"></div>--></div>
<%}}%>
</div><div class="clr"></div>
<div class="strleft">
<%
String byLine="";
String  doubleByLine []=null;
if (storyData != null && storyData.size() > 0) {
for (int i = 0; i < 1; i++) {
	StoryDTO story = (StoryDTO) storyData.get(i);
	if(storyTitleMagazine.trim().length()>0) { %>
		<h1><%=storyTitleMagazine%></h1>
		<div class="strtitlealias"><h2><%=storyTitleAlias%></h2></div>
	<%} else {%>
		<h1><%=storyTitleAlias%></h1>
         <div class="strtitlealias"><h2><%=shortDescription%></h2></div>
        
	<%}%>
<div class="strstrap" style="width:65%; float:left;">


<% 	 

String VuukleAuthorDatacomma ="";
String syndicateFrom ="";
if(syndicate==0) { syndicateFrom="Agency";} else {syndicateFrom="Internal";}

if (bylineData != null && bylineData.size() > 0) {
for (int bylineDataID = 0; bylineDataID < bylineData.size(); bylineDataID++) {
	byLineDTO bylineDto = (byLineDTO) bylineData.get(bylineDataID);
if(!bylineDto.getAuthor_name().trim().equals("") && bylineDto.getAuthor_name()!=null) { 
%>
			<span><% if(story.getStoryIcon() ==1) {%> <img src="<%=Constants.IMG_ARTICLE_ICON%><%=story.getStoryIconImage()%>" class="imgarrow" ><%}%><%=byLineLinkCreater(bylineDto.getAuthor_name())%></span>
            <%
            VuukleAuthorData =  VuukleAuthorData + VuukleAuthorDatacomma +" { \"name\": \"" + bylineDto.getAuthor_name() +" \",";
            VuukleAuthorData = VuukleAuthorData +"   \"email\": \"" + bylineDto.getAuthor_email() +" \",";            
            VuukleAuthorData = VuukleAuthorData +"   \"type\": \"" + syndicateFrom +" \"}";
			VuukleAuthorDatacomma =",";
            %>
<%	}	
out.print("&nbsp;&nbsp;|&nbsp;&nbsp;");
//if(bylineDto.getAuthor_email()!=null&&!bylineDto.getAuthor_email().equals("")&&!bylineDto.getAuthor_email().equals("null")){	
	//out.print("&nbsp;&nbsp;|&nbsp;&nbsp;<a  href=\"mailto:"+bylineDto.getAuthor_email()+"\"  >"+bylineDto.getAuthor_email()+"</a>&nbsp;&nbsp;|&nbsp;&nbsp;");	
	//}
	
	//if(bylineDto.getTwitter_id()!=null&&!bylineDto.getTwitter_id().equals("")&&!bylineDto.getTwitter_id().equals("null")){	
	//out.print("&nbsp;&nbsp;|&nbsp;&nbsp;<a  href=\"https://twitter.com/"+bylineDto.getTwitter_id()+"\" class=\"twitter-follow-button\" data-show-count=\"false\" data-lang=\"en\" >Follow "+bylineDto.getTwitter_id()+"</a>&nbsp;&nbsp;|&nbsp;&nbsp;");	
	//}
	}}else{
	if(story.getByline()!=null&&!story.getByline().equals("")) {%>
			<span><% if(story.getStoryIcon() ==1) {%> <img src="<%=Constants.IMG_ARTICLE_ICON%><%=story.getStoryIconImage()%>" class="imgarrow" ><%}%><%=byLineLinkCreater(story.getByline())%></span>
	<%	}if(twitterHandle!=null&&!twitterHandle.equals("")&&!twitterHandle.equals("null")){	
	out.print("&nbsp;&nbsp;|&nbsp;&nbsp;<a  href=\"https://twitter.com/"+twitterHandle+"\" class=\"twitter-follow-button\" data-show-count=\"false\" data-lang=\"en\" >Follow "+twitterHandle+"</a>&nbsp;&nbsp;|&nbsp;&nbsp;");	
	}    
    VuukleAuthorData =  " { \"name\": \"" +story.getByline() +" \",";
    VuukleAuthorData = VuukleAuthorData +"   \"type\": \"" + syndicateFrom +" \"}";
	}
	if (!courtesy.equals("")){ 
		if (bylineData != null && bylineData.size() > 0) {
			out.println("&nbsp;&nbsp;|&nbsp;&nbsp;");
		} else{
		
		if(story.getByline()!=null&&!story.getByline().equals("")) {
			out.println("&nbsp;&nbsp;|&nbsp;&nbsp;");
		}
		}
		out.println("<span class='courtesy'>"+courtesy+"</span>&nbsp;&nbsp;|&nbsp;&nbsp;");
	} else {	
		if((story.getCity()!=null&&story.getCity().length() > 0) || (!story.getCreatedDate().trim().equals("") && story.getCreatedDate()!=null)) 
			out.print("&nbsp;");
	}		
	if(story.getCity()!=null&&story.getCity().length() > 0) {
		out.print(story.getCity());
	} 
	 if(story.getCreatedDate()!=null&&!story.getCreatedDate().equals("")) {
		if(story.getCity()!=null&&story.getCity().length() > 0) {
			out.print(",&nbsp;");
		} 
		out.print(story.getCreatedDate());
		if(story.getUpdatedTime()!=null&&!story.getUpdatedTime().equals("")) 
			if(story.getBylineModifiedBy()!=null&&!story.getBylineModifiedBy().equals("")) {
				out.print("&nbsp;|&nbsp;"+story.getBylineModifiedBy());
			}
			out.print("&nbsp;| UPDATED&nbsp;"+story.getUpdatedTime()+" IST");
	} else {
		if(story.getBylineModifiedBy()!=null&&!story.getBylineModifiedBy().equals("")) {
			out.print("&nbsp;|&nbsp;"+story.getBylineModifiedBy());
		}
		out.print("&nbsp;| UPDATED&nbsp;"+story.getCreatedTime()+" IST");
	}%>
 </div>   
   

  <%
  if(storyId==440843){ %>
	
	 <div style="width:52%; float:right; margin-top:-40px;" >
 <img src="http://media2.intoday.in/indiatoday/images/brought-to-you-by.jpg" width="338" height="61" />
     
    
	
  <% } else{ %>
 <div style="width:35%; float:right">
<div class="commenttxt">
<span><a href="javascript:void('0');" onClick="javascript:window.open('<%=baseUrl%>articleEmail.jsp?email=0&amp;sid=<%=storyId%>','window','status=no,resize=no,toolbar=no,scrollbars=no,width=478,height=390'); event.returnValue=false; return false;"><img src="http://media2.intoday.in/indiatoday/images/mail.jpg" alt=""/></a></span>
<h4>Mail</h4></div>
<div class="commenttxt last">
<span><a href="javascript:void('0');" onClick="javascript:Open_win('<%=baseUrl%>articlePrint.jsp?aid=<%=storyId%>',710,700);return false;"><img src="http://media2.intoday.in/indiatoday/images/print.jpg" alt=""/></a></span>
<h4>Print</h4></div>
<div class="size_inc_dec"> <a href="javascript:increaseFontSize();">A+</a> <a href="javascript:decreaseFontSize();">A-</a> </div> 

<% } %>

</div>    

<div class="divclear"></div>
	<div class="st_share_area clearfix">
	  	<script type="text/javascript">
	ajaxinclude("<%=Constants.SITE_BASEPATH%>chunk_story_sharecount.jsp?createddate=<%=story.getSyndicationDate()%>&shareUrl=<%="http://indiatoday.intoday.in/"+story.getContentUrl()%>&sid=<%=storyId%>")
	</script>      
        <div class="socials_f"> <a href="https://facebook.com/sharer.php?u=http://indiatoday.intoday.in/<%=story.getContentUrl() %>" onClick="return popup(this, 'notes')" data-value-share="Facebook" ><span class="fb_s"></span></a> <a href="https://twitter.com/intent/tweet?url=http://indiatoday.intoday.in/<%=story.getContentUrl() %>&amp;text=<%=story.getTitleAlias().replaceAll("\\<.*?>","") %>&amp;via=indiatoday" onClick="return popup(this, 'notes')" data-value-share="Twitter"><span class="tw_s"></span></a> 
       <span id="more_social">
	   <a onClick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;" href="https://plus.google.com/share?url=http://indiatoday.intoday.in/<%=story.getContentUrl() %>" data-value-share="Google" ><span class="google_s"></span></a>              
        <a href="https://www.linkedin.com/shareArticle?mini=true&amp;url=http://indiatoday.intoday.in/<%=story.getContentUrl() %>&amp;summary=<%=story.getTitleAlias().replaceAll("\\<.*?>","") %>&amp;source=LinkedIn" onClick="return popup(this, 'notes')" data-value-share="Linkedin"><span class="in_s"></span></a>  
		</span> 
		<a href="javascript:void(0);"><span class="adt_s"></span></a>
        </div>
    </div>   
<div class="divclear"></div>
<div class="mediumcontent">
<%if(relatedFeaturedVideo>0) { %>
     	<div class="strcaptxt" id="vd1" style="display:none;">
    	<div class="str-vid-cont"><img src="http://media2.intoday.in/indiatoday/images/CloseIcon20x20.png" class="storyimgclose" align="right" height="20" width="20" alt="close" title="close" /></div>
        <div class="clear"></div>

<div class="str-vid-area" style="background:#000;"><div align="center" id="player"></div></div>
<div class="str-vid-strip" >
 </div>
   </div><% } 
String galleryPlayLeft=""; 
if(relatedFeaturedPhoto>0) { 
int galleryId = relatedFeaturedPhoto;
int currentPhotoNo = 1;
ArrayList photoDetails =null;
photoDetails = com.itgd.helper.GalleryHelper.photoDetails(galleryId, currentPhotoNo); 
String photoCaption = "";
int photoId = 0;
String photoTitle = "";
String galleryTitle = "";
int totalPhotoCount = 0;
String photoAltText = "";
if (photoDetails != null && photoDetails.size() > 0) {
	
	for (int pCtr = 0; pCtr < photoDetails.size(); pCtr++) {
		com.itgd.dto.GalleryDTO galleryDTO = (com.itgd.dto.GalleryDTO) photoDetails.get(pCtr);
		if (pCtr == 0) {
			photoId = galleryDTO.getPhotoId();
			photoTitle = galleryDTO.getPhotoTitle();
			photoCaption = galleryDTO.getPhotoCaption();			
			galleryId = galleryDTO.getGalleryId();			
			galleryTitle = galleryDTO.getGalleryTitle();
			photoAltText = galleryDTO.getPhotoAltText();			
			currentPhotoNo =galleryDTO.getCurrentPhotoNum();
			totalPhotoCount = galleryDTO.getPhotoCount();
			
		} 
	}
int backPhotoNo=currentPhotoNo-1;
int nextPhotoNo=currentPhotoNo+1;
if(currentPhotoNo>1 && totalPhotoCount>1) {
	backPhotoNo=currentPhotoNo-1;
}
if(currentPhotoNo<totalPhotoCount && totalPhotoCount>1) {
	nextPhotoNo=currentPhotoNo+1;
}

%>
<script src="js/site_story_js.js"></script>
<link href="http://media2.intoday.in/indiatoday/v1/jquery.bxslider.css" rel="stylesheet" type="text/css">

<style type="text/css">
.phleftcont {
    display: inline-block;    position: relative;    text-align: center; width:100%;!Important;
}
</style>
<script type="text/javascript">
var loadlinkk=window.location.hash+"";
if(loadlinkk)
{
var galleryId="<%=galleryId%>";
var currentPhotoNo="<%=currentPhotoNo%>";
var totalPhotoCount="<%=totalPhotoCount%>";

}
//var ftemp = jQuery.noConflict();
var counter_thumb =1;
var len = '';
$(document).ready(function(){
	len = $('.overview li').length;
	$('.overview').width(((103*len)+20));
	$('.prev').css('opacity', '0.5');
	$('.prev').live("click", function(){
		if (counter_thumb == 1) {
			$('.prev').css('opacity', '0.5');
		} else {
			$('.overview').animate({
				left : '+=103'
			});
			counter_thumb -=1;
			$('.next').css('opacity', '1');
		}
	});
	$('.next').live("click", function(){
		if (counter_thumb == len) {
			$('.next').css('opacity', '0.5');
		} else if (counter_thumb<len){
			$('.overview').animate({
				left : '-=103'
			});
			counter_thumb +=1;
			$('.prev').css('opacity', '1');
		}
	});
});

var counter_thumb =<%=currentPhotoNo%>;
var len = '';
$(document).ready(function(){

$('.bxslider').bxSlider({
  mode: 'fade',
  captions: true,
  auto: true
});
$('.bxslider2').bxSlider({
  mode: 'fade',
  captions: true,
});

	len = ticker('.overview li').length;
	$('.overview').width(((103*len)+20));
	$('.prev').css('opacity', '0.5');
	$('.prev').live("click", function(){
		if (counter_thumb == 1) {
			$('.prev').css('opacity', '0.5');
		} else {
			$('.overview').animate({
				left : '+=103'
			});
			counter_thumb -=1;
			$('.next').css('opacity', '1');
		}
	});
	$('.next').live("click", function(){
		if (counter_thumb == len) {
			$('.next').css('opacity', '0.5');
		} else if (counter_thumb<len){
			$('.overview').animate({
				left : '-=103'
			});
			counter_thumb +=1;
			$('.prev').css('opacity', '1');
		}
	});
});
</script><%
 galleryPlayLeft=""; 
    galleryPlayLeft=galleryPlayLeft+"<div class='phpadding' id='show'><div class='phleftcont'>";
    //galleryPlayLeft=galleryPlayLeft+"<h1>"+galleryTitle+"</h1>";
    galleryPlayLeft=galleryPlayLeft+"<img  src='http://media2.intoday.in/indiatoday/images/Photo_gallery/"+photoTitle+"'   alt=\""+photoAltText+"\" title=\""+photoAltText+"\" />";
    if(currentPhotoNo>1 && totalPhotoCount>1) {
	galleryPlayLeft=galleryPlayLeft+"<a title='#photo"+backPhotoNo+"'  name='"+galleryTitle.replaceAll("\'","")+" - India Today - photo "+backPhotoNo+"'  rel='"+Constants.SITE_URL+"story_gallery_data.jsp?galleryId="+galleryId+"&currentPhotoNo="+backPhotoNo+"&totalPhotoCount="+totalPhotoCount+"' class='phprev switch_button_story prev'>Previous</a>";
	}
 
if(currentPhotoNo<totalPhotoCount && totalPhotoCount>1) {
	galleryPlayLeft=galleryPlayLeft+"<a title='#photo"+nextPhotoNo+"' name='"+galleryTitle.replaceAll("\'","")+" - India Today - photo "+nextPhotoNo+"'  rel='"+Constants.SITE_URL+"story_gallery_data.jsp?galleryId="+galleryId+"&currentPhotoNo="+nextPhotoNo+"&totalPhotoCount="+totalPhotoCount+"'  class='phnext switch_button_story next' >Next</a><div class=\"clear\"></div>";
}
   galleryPlayLeft=galleryPlayLeft+"<span class=\"stimagecaption\">"+photoCaption+"</span></div></div>";  
} }%>
<% if(extraLargeImage!=null && !extraLargeImage.equals("")) { %>
	<%if(relatedFeaturedVideo>0) { %>
		   <script type="text/javascript">
			document.write("<div class='strcaptxt' id='vd2'><div class='divleft'><div style='position:relative;cursor:pointer;' class='storyimgclose' onClick=\"javascript:ga('send', 'pageview', '<%=Constants.SITE_URL+CommonFunctions.storyURL(storySefTitle, 1, storyId)%>#FVP');return true;\"><img src=\"<%=Constants.STORY_IMG_PATH+extraLargeImage%>\" width='647'  align='middle' alt=\"<%=largeKickerImageAltText%>\" title=\"<%=largeKickerImageAltText%>\" /><span class='pmedia-play-icon'></span></div></div></div><div class='clear'></div>");	
		</script>  
	<%} else if(relatedFeaturedPhoto>0) { 
	out.println(galleryPlayLeft);
	 }else{
	 
	 out.println("<div class='strcaptxt' ><div class='divleft'><div style='position:relative;' class='storyimgclose'><img src=\""+Constants.STORY_IMG_PATH+extraLargeImage+"\" width='647'  align='middle' alt=\""+largeKickerImageAltText+"\" title=\""+largeKickerImageAltText+"\" /><span class=\"stimagecaption\">"+largeKickerImageCaption+"</span></div></div></div><div class='clear'></div>");
	 } }else{} %><div class="divclear"></div><%

ArrayList resultStoryRelated1 = (ArrayList) CommonFunctions.relatedContent(Integer.toString(storyId), "text");
if (storyHighlightsFlag==1||(resultStoryRelated1 != null && resultStoryRelated1.size() > 0)) { 	 %>
<div class="highlights-container" style="display: block;float: left;margin-right: 20px; width:180px;" ><%
if(storyHighlightsFlag==1){ 
String storyHighlightsList [] =null;
	String storyHighlightsTitleList [] =null;
	StringBuffer relatedSB = new StringBuffer();
	if(storyHighlights!=null && storyHighlights.length() > 0) {
	if(storyHighlights.contains("##")){
		relatedSB.append("<div class=\"st_highlight\"><h2>cheat sheet</h2>");
		relatedSB.append("<ul>");
	storyHighlightsList=storyHighlights.split("\\##");
	for(int cid=0;cid<storyHighlightsList.length;cid++){
	if(storyHighlightsList[cid].contains("!~!")){
	storyHighlightsTitleList = storyHighlightsList[cid].split("\\!~!");
	if(storyHighlightsTitleList!=null && storyHighlightsTitleList.length>=2){
		relatedSB.append("<li><div class=\"ht_number\"><span>"+(cid+1)+"</span></div>");
		if(storyHighlightsTitleList[0]!=null && !storyHighlightsTitleList[0].equals(""))
		 relatedSB.append("<div class=\"ht_content\"><a href=\""+storyHighlightsTitleList[0]+"\"/>"+storyHighlightsTitleList[1]+"</a></div></li>");	
		 else
		  relatedSB.append("<div class=\"ht_content\">"+storyHighlightsTitleList[1]+"</div></li>");	
		 
	}}
	else{
		relatedSB.append("<li><div class=\"ht_number\"><span>"+(cid+1)+"</span></div>");
		 relatedSB.append("<div class=\"ht_content\">"+storyHighlightsList[cid]+"</div></li>");	
	}}}
	relatedSB.append("</ul>");
	relatedSB.append("</div>");
	
}
	out.println(relatedSB);

} %>
<div id="sandeep"></div>
<jsp:include page="chunk_story_related.jsp" flush="true" >
   <jsp:param name="idForRelated" value="<%=storyId%>"/>
</jsp:include>
</div><div class="right-story-container">
<%}else{ %><div class="right-story-container" style="width:100%;">
<%}
if(storyId>43900){
if(storyBody!=null && !storyBody.equals("")){
if(storyBody.length()>20){	if(storyBody.substring(0,15).contains("{mosimage}")){
	storyBody=storyBody.replaceFirst("\\{mosimage\\}","");
	storyBody=storyBody.replace("<p></p>","");	
	}}}}
if(story.getLongDescription().trim().indexOf("mosimage") >= 0) {
			storyBody = StoryHelper.mosimageToimagesOpt(storyBody, story.getStoryImage());

		}
		if(story.getBlurb().trim().length() > 10) 	{
			storyBody = htmlChunks(storyBody, story.getBlurb().trim(), "{blurb}"); 
		}
		if(story.getQuote().trim().length() > 10) {
			storyBody = htmlChunks(storyBody, story.getQuote().trim(), "{quote}");  
		}
		if(story.getTable().trim().length() > 10) {
			storyBody = htmlChunks(storyBody, story.getTable().trim(), "{table}");  
		}
	//Pagination Code
		storyBody = storyBody.replaceAll("\\{funfacts\\}","");
		if(checkPagebreak >= 0) {
			if(currentStoryPageNo == 0) {
					storyBody = storyBody.replaceAll("\\{mospagebreak\\}","");
			} else {
				storyBodyDisplay = storyBody.split("\\{mospagebreak\\}");
				if(currentStoryPageNo > 0 && currentStoryPageNo <= storyBodyDisplay.length) {
					storyBody = storyBodyDisplay[currentStoryPageNo-1];
				} else {
					storyBody = "Requested page does not exist. Please go from 0 to " + storyBodyDisplay.length + ".<br /> 0 for Complete story and 1 - " + storyBodyDisplay.length + " for Story part.";
				}
			}
		} else if(currentStoryPageNo != 1) {
			storyBody = "Requested page does not exist. Story exists in single part.";
		}

	if(storyId==130599 || storyId==130980 || storyId==133750 || storyId==133968 || storyId==151488 || storyId==163619 || storyId==176572 || storyId==259679 || storyId==266490 || storyId==332477|| storyId==361951 || storyId==376188 || storyId==381166) {
		int HighlightNum = 0;
		String tempStory = "";
		if(storyBody.indexOf("Highlight")>0) {
			int tempH = storyBody.indexOf("Highlight");
			int tempC = storyBody.indexOf(":");
			String t1 = storyBody.substring((storyBody.indexOf("Highlight")+9), storyBody.indexOf(":"));
			HighlightNum = Integer.parseInt(t1);
		}
		tempStory = storyBody;
		if(storyId==133750 || storyId==133968 || storyId==266490) {		
			for(int z=HighlightNum; z>=1; z--) {
				String tempAa = "Highlight"+z+":";
				String tempAa1 = "- ";
				tempStory = tempStory.replaceAll(tempAa, tempAa1);
			}
		} else {
			for(int z=HighlightNum; z>=1; z--) {
				String tempAa = "Highlight"+z;
				String tempAa1 = ""+z;
				tempStory = tempStory.replaceAll(tempAa, tempAa1);
			}
		}
		out.println(tempStory.replaceAll("#",""));
	} else {
		//out.println(storyBody);
		if(storyBody.trim().indexOf("{relateds}") >= 0) {
			storyBody = relatedChunk(storyBody, (""+storyId)); 
		}
		out.println(getProfileLink(storyBody, pageMetaTags));

	}	
	}}

%></div><%if(storyPrimaryCategory.indexOf("103#130#276")==0){%>
<div id="review-info" style="background:none repeat scroll 0 0 rgb(235, 235, 235); padding:5px 5px 0;">
<p style="line-height:19px; margin:0;"><img src="https://ssl.adam.com/urac/square-quart.gif" align="left" style="margin:0 10px 0 0;"><font size="1" face="Arial, Helvetica, sans-serif" class="review-info">A.D.A.M., Inc. is accredited by URAC, also known as the American Accreditation HealthCare Commission (www.urac.org). URAC's accreditation program is an independent audit to verify that A.D.A.M. follows rigorous standards of quality and accountability. A.D.A.M. is among the first to achieve this important distinction for online health information and services. Learn more about A.D.A.M.'s editorial policy, editorial process and privacy policy. A.D.A.M. is also a founding member of Hi-Ethics and subscribes to the principles of the Health on the Net Foundation (www.hon.ch).</font></p>
<p style="line-height:19px; margin:0;"><font size="1" face="Arial, Helvetica, sans-serif" class="review-info">The information provided herein should not be used during any medical emergency or for the diagnosis or treatment of any medical condition. A licensed medical professional should be consulted for diagnosis and treatment of any and all medical conditions. Call 911 for all medical emergencies. Links to other sites are provided for information only -- they do not constitute endorsements of those other sites. &copy;  1997-2014 A.D.A.M., Inc.  Any duplication or distribution of the information contained herein is strictly prohibited.</font></p>
</p><div align="center"><a target="_blank" href="//www.adam.com"><img width="77" height="15"  alt="adam.com" src="https://ssl.adam.com/graphics/global/hdr_ftradamlogo.gif"></a></div></div>
<%}%>
</div>
<div class="divclear"></div>
<% if(primarySectionId.equals("103") || primarySectionId.equals("67")) { %>
<p class="more-news">To get the latest entertainment news and gossip, follow us on Twitter <a style="font-weight:normal" rel="nofollow" target="_blank" href="https://twitter.com/Showbiz_IT">@Showbiz_IT</a> and Like us on <a style="font-weight:normal" rel="nofollow" target="_blank" href="https://www.facebook.com/IndiaToday">facebook.com/IndiaToday</a> 
<br><br>
For news and videos in Hindi, go to <a target="_blank" rel="nofollow" href="http://aajtak.intoday.in">AajTak.in</a>. ताज़ातरीन ख़बरों और वीडियो के लिए <a target="_blank" rel="nofollow" href="http://aajtak.intoday.in">आजतक.इन</a> पर आएं.
</p><div class="clear">&nbsp;</div>
<% } else if(primarySectionId.equals("177") ) { %>
<p class="more-news">This is unedited, unformatted feed from the Press Trust of India wire.</p><div class="clear">&nbsp;</div>
<% } else { %>
<p class="more-news">
For more news from India Today, follow us on Twitter <a style="font-weight:normal" rel="nofollow" target="_blank" href="https://twitter.com/indiatoday">@indiatoday</a> and on Facebook at <a style="font-weight:normal" rel="nofollow" target="_blank" href="https://www.facebook.com/IndiaToday">facebook.com/IndiaToday</a>
<br><span style="padding-top:10px; display:inline-block">
For news and videos in Hindi, go to <a target="_blank" rel="nofollow" href="http://aajtak.intoday.in">AajTak.in</a>. ताज़ातरीन ख़बरों और वीडियो के लिए <a rel="nofollow" target="_blank" href="http://aajtak.intoday.in">आजतक.इन</a> पर आएं.</span>
</p><div class="clear">&nbsp;</div>
<% }
if(storyId!=199697 && storyId!=199694 && storyId!=199712)
{
%>
<script type="text/javascript">
ajaxinclude("http://alpha.indiatoday.intoday.in/like-dislike-story.jsp?contentId=<%=storyId%>&contentType=text&ScfUrl=<%=storySefTitle%>&createddate=<%=syndicateDate%>&title=<%=storyTitleAlias%>")
</script>
    <% } %>
	

<script type="text/javascript">
ajaxinclude("http://alpha.indiatoday.intoday.in/snappost/snappost-chunk.jsp?contentId=<%=storyId%>&storySefTitle=<%=storySefTitle%>")
</script>

	
<div class="divclear"></div>
<%
if(pageMetaTags.trim().length() > 0) {
	out.print("<div class='tagstxt'>");
	if(pageMetaTags.contains(",")) {
		String[] metaTagsA = (pageMetaTags.trim()).split(",");
		for(int k=0; k<metaTagsA.length; k++)	
		{
			out.print("<a href=\""+Constants.SITE_BASEPATH+"advanced_search.jsp?searchword="+metaTagsA[k].trim().replace(" ","%20")+"&amp;searchtype=text&amp;search_type=article\">"+metaTagsA[k]+"</a>");
			//if(k < metaTagsA.length-1)
				//out.print("");
		}
	} else {
		out.print("<a href=\""+Constants.SITE_BASEPATH+"advanced_search.jsp?searchword="+pageMetaTags.replace(" ","%20")+"&amp;searchtype=text&amp;search_type=article\">"+pageMetaTags+"</a>");
	}
	out.print("</div>");
}
%>
<div class="divclear"></div>   
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
<div class="divclear"></div>
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
<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js"></script>
</div>
<div class="divclear">&nbsp;</div>
<%if(medium!=1){ %>
<!--<script type="text/javascript" defer>
ajaxinclude("<%=Constants.SITE_BASEPATH%>commentbox/comment_box.jsp?sId=<%=storyId%>&website=indiatoday&content_type=story&ScfUrl=<%=storySefTitle%>&StoryTitle=<%=storyTitleAlias%>&message=<%=cacheErrMsg%>&sectionInfo=&storytemplate=special");
</script>-->
<!--To display comments platform code start here-->
<%
VuukleAuthorData ="["+VuukleAuthorData.trim()+"]";
//out.println("JSON -->"+VuukleAuthorData);
String VuukleAuthorDataEncode  = URLEncoder.encode(VuukleAuthorData, "UTF-8").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\+", "%20").replaceAll("\\%27", "'").replaceAll("\\%21", "!").replaceAll("\\%7E", "~").replaceAll("%3A", ":").replaceAll("%2C", ",").replaceAll("%40", "@");
//out.println("<br/>urlencode-->"+VuukleAuthorDataEncode);
byte[] byteArray = Base64.encodeBase64(VuukleAuthorDataEncode.getBytes());
String encodedString = new String(byteArray);
//out.println("Base64.encodeBase64-->"+encodedString);
%>
<a name="comment"></a>
<div id="vuukle_div"></div>
<script src="http://vuukle.com/js/vuukle.js" type="text/javascript"></script><script type="text/javascript">
var UNIQUE_ARTICLE_ID = "<%=storyId%>"; 
var SECTION_TAGS =  "<%=vuukleSectionTags%>, news, story";
var ARTICLE_TITLE = "<% out.print(pageMetaTitle.replace("\"",""));%>";
var GA_CODE = "UA-795349-17";
var VUUKLE_API_KEY = "dc34b5cc-453d-468a-96ae-075a66cd9eb7"; 
var TRANSLITERATE_LANGUAGE_CODE = ""; //"en" for English, "hi" for hindi
var VUUKLE_COL_CODE = "d00b26";
var ARTICLE_AUTHORS = '<%=encodedString%>'; 
create_vuukle_platform(VUUKLE_API_KEY, UNIQUE_ARTICLE_ID, "0", SECTION_TAGS, ARTICLE_TITLE, TRANSLITERATE_LANGUAGE_CODE , "1", "", GA_CODE, VUUKLE_COL_CODE, ARTICLE_AUTHORS);
</script>
<!--To display comments platform code end here-->

<%} %>
<div class="divclear"></div>
</div>
<%
if(medium==1){}else{
if(rightNavigationPath!=null&&rightNavigationPath.trim().length() == 0) { %>
<div id="rightpanel">

<!----- Amlesh test --->

<%@include file="navigation_rightnav_2015.jsp"%> </div>
<%}else{ %>
<div id="spc-rightnav"><jsp:include page="<%=rightNavigationPath%>" flush="true" /></div>
<%}} %></div>
<div class="clear"></div>
<%if(bottomNavigationPath.trim().length() == 0) { %>
<%@include file="navigation_footernav_2015.jsp"%>
<%}else{ %>
<jsp:include page="<%=bottomNavigationPath%>" flush="true" />
<script src="http://indiatoday.intoday.in/js/jquery.lazyload.js?v=4" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
		$(document).find("img[data-original]").lazyload({
	      failure_limit : 20 
	    });
	});	
 </script>');
 
<%} 
if(medium==1){
%>
<!--Face Book Api -->
<div id="fb-root"></div>
<script type="text/javascript">
	var button;
	var userInfo;
	var boiddd = '';	
	function faebookinit(sid,cid) {
		FB.init({ appId: '613580381995012', //change the appId to your appId
			status: true, 
			cookie: true,
			xfbml: true,
			oauth: true});			
		boiddd = '#boxie_'+cid;
		function updateButton(response) {
			
			button       =   $(boiddd +" .fb-auth");
			userInfo     =   document.getElementById('user-info');
			
			if (response.authResponse) {
				//user is already logged in and connected
				FB.api('/me', function(info) {
					login(response, info);
				});
				
				$(".fb-auth").on('click',function() {
					FB.logout(function(response) {
						/*userInfo.innerHTML                             = "";
					
						$('.frmname').show(10);
						$('.frmemail').show(10);
						$(boiddd +" .fb-auth").html('Login With:&nbsp;<img src="http://media2.intoday.in/indiatoday/images/fb.png">');
						$(boiddd +" .twitte-auth").show(10);
						document.getElementById('fname').value		   = "";
						document.getElementById('email').value		   = "";
						document.getElementById('sn_image').value	   = "";
						document.getElementById('sn_source').value	   = "";*/
						logout(response);
					});
				});
				
			} else {
				//user is not connected to your app or logged out
				//button       =   $(".fb-auth");
				//alert(boiddd+'2');
				userInfo = document.getElementById('user-info');
				$(boiddd +" .fb-auth").innerHTML = 'Login With:&nbsp;<img src="http://media2.intoday.in/indiatoday/images/fb.png">';
				$(boiddd +" .fb-auth").on('click',function() {//alert(boiddd+'3');
					FB.login(function(response) {
						if (response.authResponse) {
							FB.api('/me', function(info) {
								login(response, info);
							});	   
						} else {
							//user cancelled login or did not grant authorization
						
						}
					}, {scope:'email,user_birthday,status_update,publish_stream,user_about_me'});  	
				});
			}
		}
		
		// run once with current status and whenever the status changes
		FB.getLoginStatus(updateButton);
		FB.Event.subscribe('auth.statusChange', updateButton);	
	};
	(function() {
		var e = document.createElement('script'); e.async = true;
		e.src = document.location.protocol 
			+ '//connect.facebook.net/en_US/all.js';
		document.getElementById('fb-root').appendChild(e);
	}());
	
	
	function login(response, info){console.log(info)
		if (response.authResponse && info.name!='' && info.name!=undefined) {
		   // $(boiddd+" .fb-auth").each(function(){ alert(boiddd+'5');
			userInfo =   document.getElementById('user-info');
			indexbb = boiddd.replace('#boxie_','');
			indexbb = parseInt(indexbb)-1;
			$(boiddd+' #user-info').html('<img class="imgs" src="https://graph.facebook.com/' + info.id + '/picture">' + info.name);
			$(boiddd+" .fb-auth").html('Logout');
			$(boiddd+" .twitte-auth").hide(10);
			//alert(boiddd+'=6='+indexbb);
			$('.frmname').hide(10);
			$('.frmemail').hide(10);
			$(boiddd+' #facebook').style  ="background:url(http://media2.intoday.in/indiatoday/images/homenew/Sprit-IT-Home.png) -21px -542px; background-repeat: no-repeat; text-indent: -100px";
			document.getElementById('fname').value = info.name;
			document.getElementById('email').value = info.email;			
			document.getElementById('sn_image').value = 'https://graph.facebook.com/' + info.id + '/picture';
			document.getElementById('sn_source').value = 'facebook';
		    //});
		}
	}
	
	function logout(response){
		$(".twitte-auth").show(10);
	   $(".fb-auth").each(function(){
		userInfo.innerHTML = "";
		document.getElementById("facebook").style="backgroundurl(http://media2.intoday.in/indiatoday/images/login-f.jpg); background-repeat: no-repeat; ";
		$(this).html('Login With:&nbsp;<img src="http://media2.intoday.in/indiatoday/images/fb.png">');
		$('.frmname').show(10);
		$('.frmemail').show(10);
		document.getElementById('fname').value		   = "";
		document.getElementById('email').value		   = "";
		document.getElementById('sn_image').value	   = "";
		document.getElementById('sn_source').value	   = "";
		//document.getElementById('authorize-button').style.display ="block";
	    });
	}

	//stream publish method
	function streamPublish(name, description, hrefTitle, hrefLink, userPrompt){
		FB.ui(
		{
			method: 'stream.publish',
			message: '',
			attachment: {
				name: name,
				caption: '',
				description: (description),
				href: hrefLink
			},
			action_links: [
				{ text: hrefTitle, href: hrefLink }
			],
			user_prompt_message: userPrompt
		},
		function(response) {
		});
	}
	function showStream(){
		FB.api('/me', function(response) {
			message = document.getElementById('message').value;
			streamPublish(location.href, message,'hrefTitle',location.href, "Share IndiaToday");
		});
	}

	function share(){
		var share = {
			method: 'stream.share',
			u: 'http://indiatoday.intoday.in/'
		};
		FB.ui(share, function(response) { 
			console.log(response); 
		});
	}

	//console.log("a");
    var x = 1;
	$( ".mediumcontent p" ).each(function( index ) {
		$(this).attr('id', 'p_'+x);
		$(this).addClass('common-p');
		$(this).append("<span id='commentBox_"+x+"' class='com-icon' boxid='"+x+"'>0</span>");
		 
		 var commntbxnew = "<span class='msg-box'><b class='close'>X</b><div id='boxie_"+x+"'></div></span>";		 
		 $(commntbxnew).appendTo("#p_"+x);	
	   x++;
	});
	showCountComments("<%=storyId%>",x);
	function showCountComments(sId,cId)
	{
	   $.post("/commentbox/comment_box_count.jsp",{"ac_contentid": sId,"ac_slideid": x}, function( data ) {
				var splitdata = data.split('#');
				$('.com-icon').each(function(){
					if(splitdata[$(this).attr('boxid')]!=undefined && splitdata[$(this).attr('boxid')]!=null) {
						$(this).html(splitdata[$(this).attr('boxid')])
					}
				});
					//$("#cB_"+cId).html("<u>"+data+" Comments</u>");
	//				alert(data);
	//console.log(cId+"--"+data);
					});
	}
	function showComments(sId,cId)
	{
		$.post("/commentbox/medium_comment_box_display.jsp",{"sId": sId,"slideid": cId}, function( data ) {
					//$(boiddd).html(data);
					 $("#boxie_"+cId).append("<div id=\"content-1\" class=\"content\"><ul"+cId+" _story_com><div class=\"marg10px no-comment\" style=\"display:none\">Comments</div>"+data+"</ul></div>");
				   // alert(data);
					//console.log(cId+"--"+data);
					});
	}			
	
	function captcha_reload_separte(cId)
	{
		var nnn=Math.floor(Math.random()*10000);
		if(document.getElementById("captcha1_"+cId)!=null && document.getElementById("captcha1_"+cId)!=undefined) {
			document.getElementById("captcha1_"+cId).innerHTML="<img src='http://media2.intoday.in/aajtak/images/small_loading.gif'>";
			document.getElementById("captcha1_"+cId).innerHTML="<img id='rootcaptchaimg_"+cId+"' src='captcha-img.jsp?pin="+nnn+"'>";
			document.getElementById("rootcaptcha_"+cId).value=nnn;
			//console.log(nnn);
			
		}
		
	}	
	function validate_email_new(val){
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(val.value)){
			return (true)
		}
		return false;
	}
	
	function submit_comment_root_separte(cId) {
     var frm = document.getElementById("commentForm_"+cId).name;
     var sn_image = "";
	 var sn_source = "";
	if(document.getElementById("fname").value=="") {
		  alert("please enter your name");
		  document.getElementById("fname").focus();
		  return false;
		} else if(document.getElementById("email").value=="") {
		  alert("please enter your email");
		  document.getElementById("email").focus();
		  return false;
		} else if(validate_email_new(document.getElementById("email"))==false) {
			alert("please enter your email");
			document.getElementById("email").focus();
			return false;
		}

		if(document.getElementById("sn_image").value != "")
		{
		  sn_image = document.getElementById("sn_image").value;
		}
		
		if(document.getElementById("sn_source").value != "")
		{
		  sn_source = document.getElementById("sn_source").value;
		}

		if(document.getElementById("message_"+cId).value=="") {					
			alert("Please enter your comment");
			document.getElementById("message_"+cId).focus();
			return false;
		} else {
			var comm_mess="";
			var cmnt_level = 0;
			comm_mess=document.getElementById("message_"+cId).value;
			var slideiddd = document.getElementById("slideid_"+cId).value;
			//+'&location='+escape(frm.location.value)
			var sn_image_val='http://media2.intoday.in/images/common/p2.png';
		
		$.post("/commentbox/comment_box_save.jsp",{"FNAME": document.getElementById("fname").value,"EMAIL": escape(document.getElementById("email").value),"ac_contenttype": "story","ac_contentid": escape(document.getElementById("storyid_"+cId).value),"ac_slideid": slideiddd,"message": comm_mess,"sn_image": sn_image,"sn_source": sn_source,"website": "indiatoday"}, function( data )
			{
			//	$("#cBPrev_"+cId).hide();
				alert("Your comment have been submitted");
				document.getElementById("message_"+cId).value = "";
				//document.getElementById("rootcaptcha_"+cId).value = "";
				//document.getElementById("number_"+cId).value = "";
			    //captcha_reload_separte(cId);
				 <%
			      java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				 Calendar cal = Calendar.getInstance();			      
			      %>
				var cmntid = cId+'_story_com';
				var todaydate = '<%=dateFormat.format(cal.getTime())%>';
				var newcmnt_level =1;
				var rootid=1;
				var splitbckk='1';
			    $('#'+cmntid).prepend(unescape('<li style="border-bottom:1px solid #000;"><img id="profile_img" class="imgs" src="'+sn_image+'" alt=""><div class="comm-title">'+document.getElementById("fname").value+'</div><div class="share-time">'+todaydate+'</div><div class="clr"></div><div class="marg10px" style="font-family: arial; font-size: 10pt; color: #222222; margin-top:10px;">'+(comm_mess)+'</div><div class="clr"></div><div class="lft marg15R"><input type="button" style="display:none;" class="share-reply-hindi" onclick="opencommentbox('+splitbckk+','+newcmnt_level+','+rootid+')"" value="Reply"></div><div class="ratingnew_comment"><div class="clear"></div></div><div class="clr"></div><ul id="comment_'+splitbckk+'"></ul></li>'));
				
				//$('.msg-box').fadeOut('fast');
				//$('.common-p').css('width', '955px')
			});		
			
		}
		//return false;

	}
	
	function showCommentBox(storyId,cId)
	{
		//console.log(storyId+"--"+cId);
		//<div class="formfield frmname">Name :<input name="fname_'+cId+'" value="" id="fname_'+cId+'"><br/></div><div class="formfield frmemail">Email: <input name="email_'+cId+'" value="" id="email_'+cId+'"><br/></div>
		//Comment Box Display on storypage
		var commentBox = '<form style="overflow:hidden; padding-left:11px" name="commentForm_'+cId+'" id="commentForm_'+cId+'" action="" method="post" onsubmit="return false;"><input type="hidden" name="fname" id="fname" value="" /><input type="hidden" name="email" id="email" value="" /><input type="hidden" name="website" value="indiatoday" /><input type="hidden" name="storyid_'+cId+'" id="storyid_'+cId+'" value="'+storyId+'" /><input type="hidden" name="parentid_'+cId+'" value="" /><input type="hidden" name="rootparentid_'+cId+'" value="" /><input type="hidden" id="sn_image" name="sn_image" value="" /><input type="hidden" id="sn_source_'+cId+'" name="sn_source" value="" /><input type="hidden" name="slideid_'+cId+'" id="slideid_'+cId+'" value="'+cId+'" /><br clear="all"><input type="hidden" id="sn_image" name="sn_image" value="" /><input type="hidden" id="sn_source" name="sn_source" value="" /><div id="facebook" ><div class="fb-auth" style="float:left;font-family: arial;font-size: 12pt;color: #222222;font-weight: bold;">Login With:&nbsp;<img src="http://media2.intoday.in/indiatoday/images/fb.png" style="cursor:pointer; margin-right:5px;"></div>&nbsp;&nbsp;<div class="twitte-auth" style="float:left;margin-left:4px;"><a href="twitetrsignin.jsp?url=<%=CommonFunctions.storyURL(storySefTitle,1,storyId)%>"><img src="http://media2.intoday.in/indiatoday/images/twitter.png"></a></div></div><div style="clear:both"></div> <div class="formfield"><textarea name="comment_'+cId+'" id="message_'+cId+'" class="com-box"></textarea><div id="user-info" style="font-family: arial;font-weight: bold; font-size: 10pt; color: #222222;"></div><br/></div><div class="formfield" style="width:100%"><input style="float:left; margin-left:8px;" type="button" onclick="submit_comment_root_separte('+cId+');" name="save" id="save" value="Post" /><br/></div><div class="formfield"></div></form><ul id="'+cId+'_story_com"></ul>';
		setTimeout(function(){
					//captcha_reload();
					//rightchk = 20;
					//handleClientLoad();
					faebookinit(storyId,cId);
				},1000);
		return commentBox;
		
       // $("[id^='cBPrev_']").hide();
		/*$("#cB_"+cId).hide();
		$("#cBPrev_"+cId).html(commentBox).show();
captcha_reload_separte(cId);*/
	}
function storymake(storyId,cId){	
	   var commentBox = '<span id = "cB_'+cId+'" onClick = "showCommentBox('+storyId+','+cId+')" style = "cursor:pointer;"></span><span id = "cBPrev_'+cId+'"></span>';       
	   return commentBox;
	}
</script>
<script src="http://media2.intoday.in/indiatoday/js/scroll-min.js"></script>
<script src="js/sitejs.js"></script>
<link href="http://media2.intoday.in/indiatoday/js/scrollbar-line.css" type="text/css" rel="stylesheet" />
<link href="http://media2.intoday.in/indiatoday/js/scroll-style-line.css" type="text/css" rel="stylesheet" />
<style type="text/css">
.plusicon{display:none;}
</style>


<script type="text/javascript">
$(document).ready(function(){
	$('.com-icon').click(function(){
	setTimeout(function(){
		$("#content-1").mCustomScrollbar({
				setTop:"540px"
			});
		}, 100);
	});
});

</script>
<script type="text/javascript">
(function($){
		$(window).load(function(){
			$("#search_paenl-1").mCustomScrollbar({});		
		});
	})(jQuery);
</script>
<%}%>
</div>
<script type='text/javascript'>
    var _sf_async_config={};
    /** CONFIGURATION START **/
    _sf_async_config.uid = 60355;
    _sf_async_config.domain = 'indiatoday.intoday.in';
    _sf_async_config.useCanonical = true;
    _sf_async_config.sections = '<%=primaryCategoryTitle%>';  //CHANGE THIS
    _sf_async_config.authors = '<%=gaAuthorData%>';    //CHANGE THIS
    /** CONFIGURATION END **/
    (function(){
      function loadChartbeat() {
        window._sf_endpt=(new Date()).getTime();
        var e = document.createElement('script');
        e.setAttribute('language', 'javascript');
        e.setAttribute('type', 'text/javascript');
        e.setAttribute('src', '//static.chartbeat.com/js/chartbeat.js');
        document.body.appendChild(e);
      }
      var oldonload = window.onload;
      window.onload = (typeof window.onload != 'function') ?
         loadChartbeat : function() { oldonload(); loadChartbeat(); };
    })();
</script>

<script src='http://821.tm.zedo.com/v1/7217327e-2fc7-4b32-bd53-1c943009b4ca/tm.js'></script>
<script type="text/javascript">
    var ttsmi2_data = { siteid: 38824, count: 'site' };
    (function() {
      var sc = document.createElement('script'); sc.type = 'text/javascript'; sc.async = true;
      sc.src = '//target.smi2.net/client/target.js';
      var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(sc, s);
    }());
</script>
<!-- Javascript tag: -->
<!-- begin ZEDO for channel:  IT RichMedia Ros , publisher: India Today , Ad Dimension: Photo flip - 1 x 1 -->
<script type="text/javascript">
var zflag_nid="821"; var zflag_cid="2044/1137"; var zflag_sid="2"; var zflag_width="1"; var zflag_height="1"; var zflag_sz="79"; 
</script>
<script type="text/javascript" src="http://d2.zedo.com/jsc/d2/fo.js"></script>
<!-- end ZEDO for channel:  IT RichMedia Ros , publisher: India Today , Ad Dimension: Photo flip - 1 x 1 -->
<script type="text/javascript">
ajaxinclude("<%=Constants.SITE_BASEPATH%>chunk_previous_next_story.jsp?contentId=<%=storyId%>&PrimaryCatId=<%=primarySectionId%>&createddate=<%=syndicateDate%>")
</script>
<!--<script type="text/javascript">
var x=$(".mediumcontent .right-story-container p:eq(0)").html();var texts='<span class="fChar">'+x.charAt(0)+'</span>';$(".mediumcontent .right-story-container p:eq(0)").html(texts+x.slice(1,x.length));
</script>-->
<%if(extraLargeImage!=null && !extraLargeImage.equals("")){if(relatedFeaturedVideo>0) {} else if(relatedFeaturedPhoto>0) {}else{
 %><script type="text/javascript" src="http://cdn4.wibbitz.com/indiatoday/embed.js" async></script><%}}%>
</body><%} else {
	System.out.println("No Content Found - Section Page ");
	response.sendRedirect(Constants.PAGE_NOT_FOUND);
} %>
</html></cache:cache>

