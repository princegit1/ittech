package com.itgd.parser;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/*
import javax.ws.rs.core.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import javax.ws.rs.core.Response;
*/

@Path("/generate")
public class JsonParser {

	@GET
	@Path("/jsonParser")
	@Produces(MediaType.APPLICATION_XML)
	//public JsonRec getMeText(@QueryParam("path") String jsonUrl) {
		public JsonRec getMeText(String jsonUrl) {
		String webUrl = jsonUrl;
		JsonRec recJson = null;
		ItemRec recItem = null;
		ItemFeed recItemFeed = null;
		RelatedFeedVideo RelatedFeedVideo = null;
		RelatedFeedPhoto RelatedFeedPhoto = null;
		Bitrate bitrate = null;
		AuthorList authorBean=null;
		
		List<ItemRec> itemList = null;
		List<ItemFeed> itemFeedList = null;
		List<RelatedFeedVideo> RelatedFeedVideoList = null;
		List<RelatedFeedPhoto> RelatedFeedPhotoList = null;
		List<Bitrate> bitrateList = null;
		List<AuthorList> authorList=null;

		String itemTitle = null;
		String itemType = null;
		String itemId = null;
		Integer itemStart = null;
		Integer itemEnd = null;
		Integer totalRecords=null;
		String blogTitle=null;
		String blogDesc=null;

		String feedId = null;
		String feedTitle = null;
		String feedSelfUrl = null;
		String feedByLine = null;
		String feedCity = null;
		String introText = null;

		String kicker_image = null;
		String extralarge_image = null;
		String mobile_image = null;
		String large_kicker_image = null;
		String large_kicker_image_alt_text = null;
		String is_external = null;
		String createddate = null;
		String createddateYYMMDD = null;
		String content_url = null;
		String primary_category = null;
		String medium_kicker_image = null;
		String short_introtext = null;
		String sectitle = null;
		String secid = null;
		String secsefurl = null;
		String seccontenturl = null;
		String cattitle = null;
		String catid = null;
		String catsefurl = null;
		String catcontenturl = null;
		String curcattitle = null;
		String curcatid = null;
		String curcatsefurl = null;
		String curcatcontenturl = null;
		String metatitle = null;
		String metakey = null;
		String metadesc = null;
		String topnav_path = null;
		String rightnav_path = null;
		String bottomnav_path = null;
		String leftnav_path = null;
		String header_image = null;
		//new fields
		 String photo_small_name= null;
		 String gallery_name= null;
		 String strap_headline= null;
		 String gallery_caption= null;
		 String gallery_sefurl= null;
		 String photo_category= null;
		 String gallerykeywords= null;
		 String gallerydesc= null;
		 String photoid= null;
		 String photoname= null;
		 String imagestrap= null;
		 String photoCaption= null;
		 String photo_title= null;
		 String photo_width= null;
		 String photo_height= null;
		 String image_alt_text= null;
		 String image_metakey= null;
		 String image_title= null;
		 String catname= null;
		 String full_text=null;
		 
		 String story_highlights_flag=null;
		 String story_highlights=null;
		 String comment_question=null;
		 String reporter_twitter_handle=null;
		 String isMedium=null;
		 String issueId=null;
		 String titlealias=null;
		 String titleMagazine=null;
		 String courtesey=null;
		 String imagePath=null;
		 String metatags=null;
		 String story_syndication=null;
		 String crt=null;
		 String mdate=null;
		 String snd=null;
		 String article_icon=null;
		 String article_icon_img=null;
		 String ctime=null;
		 String mtime=null;
		 String quotes=null;
		 String blurbs=null;
		 String tables=null;
		 String large_kicker_image_width=null;
		 String kicker_image_caption=null;
		 String indiatoday_expert=null;
		 String google_standout=null;	
		 String commentboxflag=null;
		 String byLineModifiedBy=null;
		 String externalUrl=null;
		 String modified=null;
		 String catName=null;
		 String catId=null;
		 String multibitrate_flag =null;
		 String video_duration=null;
		 String created=null;
		 String mp4_flag=null;
		 String VideoGallery_foldername=null;
		 String VideoGallery_filename=null;
		 String cat_template_path=null;
		 String cat_leftnave_path=null;
		 String cat_bottomnav_path=null;
		 String cat_rightnav_path=null;
		 String cat_topnav_path=null;
		 String comments=null;
		 String date_time=null;

		try {
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			//System.out.println("jsonObject-->" + jsonObject);
			recJson = new JsonRec();
			itemList = new ArrayList<ItemRec>();

			JSONObject channel = (JSONObject) jsonObject.get("channel");
			JSONArray items = (JSONArray) channel.get("item");
			//System.out.println("items-->" + (items));

			for (int i = 0; i < items.size(); i++) {
				recItem = new ItemRec();
				itemFeedList = new ArrayList<ItemFeed>();
				JSONObject innerObj = (JSONObject) items.get(i);
				if(innerObj.get("name")!=null && innerObj.get("name").toString().length()>0)
				{
					itemTitle = innerObj.get("name").toString();
				}
				if(innerObj.get("type")!=null && innerObj.get("type").toString().length()>0)
				{
					itemType = innerObj.get("type").toString();
				}
				if(innerObj.get("id")!=null && innerObj.get("id").toString().length()>0)
				{
					itemId = innerObj.get("id").toString();
				}
				if(innerObj.get("start")!=null && innerObj.get("start").toString().length()>0)
				{
					itemStart = Integer.parseInt(innerObj.get("start").toString());
				}
				if(innerObj.get("end")!=null && innerObj.get("end").toString().length()>0)
				{
					itemEnd = Integer.parseInt(innerObj.get("end").toString());
				}
				if(innerObj.get("total_records")!=null && innerObj.get("total_records").toString().length()>0)
				{
					totalRecords = Integer.parseInt(innerObj.get("total_records").toString());
				}
				if(innerObj.get("blog_title")!=null && innerObj.get("blog_title").toString().length()>0)
				{
					blogTitle = innerObj.get("blog_title").toString();
				}
				if(innerObj.get("blog_description")!=null && innerObj.get("blog_description").toString().length()>0)
				{
					blogDesc = innerObj.get("blog_description").toString();
				}
				
				JSONArray feed = (JSONArray) innerObj.get("feed");
				
				for (int j = 0; j < feed.size(); j++) {
					recItemFeed = new ItemFeed();
					
					JSONObject feedRec = (JSONObject) feed.get(j);
					//System.out.println("feedRec>>>>"+webUrl+">>>>>>>>>>"+feedRec);
					if (feedRec.get("id") != null && feedRec.get("id").toString().length() > 0) {
						feedId = feedRec.get("id").toString();
						recItemFeed.setId(feedId);
					}
					if (feedRec.get("title") != null && feedRec.get("title").toString().length() > 0) {
						feedTitle = feedRec.get("title").toString();
						recItemFeed.setTitle(feedTitle);
					}
					if (feedRec.get("sef_url") != null && feedRec.get("sef_url").toString().length() > 0) {
						feedSelfUrl = feedRec.get("sef_url").toString();
						recItemFeed.setSef_url(feedSelfUrl);
					}
					if (feedRec.get("byline") != null && feedRec.get("byline").toString().length() > 0) {
						feedByLine = feedRec.get("byline").toString();
						recItemFeed.setByline(feedByLine);
					}
					if (feedRec.get("city") != null && feedRec.get("city").toString().length() > 0) {
						feedCity = feedRec.get("city").toString();
						recItemFeed.setCity(feedCity);
					}
					if (feedRec.get("introtext") != null && feedRec.get("introtext").toString().length() > 0) {
						introText = feedRec.get("introtext").toString();
						recItemFeed.setIntrotext(introText);
					}
					if (feedRec.get("extralarge_image") != null
							&& feedRec.get("extralarge_image").toString().length() > 0) {
						extralarge_image = feedRec.get("extralarge_image").toString();
						recItemFeed.setExtralarge_image(extralarge_image);
					}
					if (feedRec.get("kicker_image") != null && feedRec.get("kicker_image").toString().length() > 0) {
						kicker_image = feedRec.get("kicker_image").toString();
						recItemFeed.setKicker_image(kicker_image);
					}
					if (feedRec.get("mobile_image") != null && feedRec.get("mobile_image").toString().length() > 0) {
						mobile_image = feedRec.get("mobile_image").toString();
						recItemFeed.setMobile_image(mobile_image);
					}
					if (feedRec.get("large_kicker_image") != null
							&& feedRec.get("large_kicker_image").toString().length() > 0) {
						large_kicker_image = feedRec.get("large_kicker_image").toString();
						recItemFeed.setLarge_kicker_image(large_kicker_image);
					}
					if (feedRec.get("large_kicker_image_alt_text") != null
							&& feedRec.get("large_kicker_image_alt_text").toString().length() > 0) {
						large_kicker_image_alt_text = feedRec.get("large_kicker_image_alt_text").toString();
						recItemFeed.setLarge_kicker_image_alt_text(large_kicker_image_alt_text);
					}
					if (feedRec.get("is_external") != null && feedRec.get("is_external").toString().length() > 0) {
						is_external = feedRec.get("is_external").toString();
						recItemFeed.setIs_external(is_external);
					}
					if (feedRec.get("createddate") != null && feedRec.get("createddate").toString().length() > 0) {
						createddate = feedRec.get("createddate").toString();
						recItemFeed.setCreateddate(createddate);
					}
					if (feedRec.get("createddateYYMMDD") != null
							&& feedRec.get("createddateYYMMDD").toString().length() > 0) {
						createddateYYMMDD = feedRec.get("createddateYYMMDD").toString();
						recItemFeed.setCreateddateYYMMDD(createddateYYMMDD);
					}
					if (feedRec.get("content_url") != null && feedRec.get("content_url").toString().length() > 0) {
						content_url = feedRec.get("content_url").toString();
						recItemFeed.setContent_url(content_url);
					}
					if (feedRec.get("primary_category") != null
							&& feedRec.get("primary_category").toString().length() > 0) {
						primary_category = feedRec.get("primary_category").toString();
						recItemFeed.setPrimary_category(primary_category);
					}
					if (feedRec.get("medium_kicker_image") != null
							&& feedRec.get("medium_kicker_image").toString().length() > 0) {
						medium_kicker_image = feedRec.get("medium_kicker_image").toString();
						recItemFeed.setMedium_kicker_image(medium_kicker_image);
					}
					if (feedRec.get("short_introtext") != null
							&& feedRec.get("short_introtext").toString().length() > 0) {
						short_introtext = feedRec.get("short_introtext").toString();
						recItemFeed.setShort_introtext(short_introtext);
					}
					if (feedRec.get("sectitle") != null && feedRec.get("sectitle").toString().length() > 0) {
						sectitle = feedRec.get("sectitle").toString();
						recItemFeed.setSectitle(sectitle);
					}
					if (feedRec.get("secid") != null && feedRec.get("secid").toString().length() > 0) {
						secid = feedRec.get("secid").toString();
						recItemFeed.setSecid(secid);
					}
					if (feedRec.get("secsefurl") != null && feedRec.get("secsefurl").toString().length() > 0) {
						secsefurl = feedRec.get("secsefurl").toString();
						recItemFeed.setSecsefurl(secsefurl);
					}
					if (feedRec.get("seccontenturl") != null && feedRec.get("seccontenturl").toString().length() > 0) {
						seccontenturl = feedRec.get("seccontenturl").toString();
						recItemFeed.setSeccontenturl(seccontenturl);
					}
					if (feedRec.get("cattitle") != null && feedRec.get("cattitle").toString().length() > 0) {
						cattitle = feedRec.get("cattitle").toString();
						recItemFeed.setCattitle(cattitle);
					}
					if (feedRec.get("catid") != null && feedRec.get("catid").toString().length() > 0) {
						catid = feedRec.get("catid").toString();
						recItemFeed.setCatid(catid);
					}
					if (feedRec.get("catsefurl") != null && feedRec.get("catsefurl").toString().length() > 0) {
						catsefurl = feedRec.get("catsefurl").toString();
						recItemFeed.setCatsefurl(catsefurl);
					}
					if (feedRec.get("catcontenturl") != null && feedRec.get("catcontenturl").toString().length() > 0) {
						catcontenturl = feedRec.get("catcontenturl").toString();
						recItemFeed.setCatcontenturl(catcontenturl);
					}
					if (feedRec.get("curcattitle") != null && feedRec.get("curcattitle").toString().length() > 0) {
						curcattitle = feedRec.get("curcattitle").toString();
						recItemFeed.setCurcattitle(curcattitle);
					}
					if (feedRec.get("curcatid") != null && feedRec.get("curcatid").toString().length() > 0) {
						curcatid = feedRec.get("curcatid").toString();
						recItemFeed.setCurcatid(curcatid);
					}
					if (feedRec.get("curcatsefurl") != null && feedRec.get("curcatsefurl").toString().length() > 0) {
						curcatsefurl = feedRec.get("curcatsefurl").toString();
						recItemFeed.setCurcatsefurl(curcatsefurl);
					}
					if (feedRec.get("curcatcontenturl") != null
							&& feedRec.get("curcatcontenturl").toString().length() > 0) {
						curcatcontenturl = feedRec.get("curcatcontenturl").toString();
						recItemFeed.setCurcatcontenturl(curcatcontenturl);
					}
					if (feedRec.get("metatitle") != null && feedRec.get("metatitle").toString().length() > 0) {
						metatitle = feedRec.get("metatitle").toString();
						recItemFeed.setMetatitle(metatitle);
					}
					if (feedRec.get("metakey") != null && feedRec.get("metakey").toString().length() > 0) {
						metakey = feedRec.get("metakey").toString();
						recItemFeed.setMetakey(metakey);
					}
					if (feedRec.get("metadesc") != null && feedRec.get("metadesc").toString().length() > 0) {
						metadesc = feedRec.get("metadesc").toString();
						recItemFeed.setMetadesc(metadesc);
					}
					if (feedRec.get("topnav_path") != null && feedRec.get("topnav_path").toString().length() > 0) {
						topnav_path = feedRec.get("topnav_path").toString();
						recItemFeed.setTopnav_path(topnav_path);
					}
					if (feedRec.get("rightnav_path") != null && feedRec.get("rightnav_path").toString().length() > 0) {
						rightnav_path = feedRec.get("rightnav_path").toString();
						recItemFeed.setRightnav_path(rightnav_path);
					}
					if (feedRec.get("bottomnav_path") != null
							&& feedRec.get("bottomnav_path").toString().length() > 0) {
						bottomnav_path = feedRec.get("bottomnav_path").toString();
						recItemFeed.setBottomnav_path(bottomnav_path);
					}
					if (feedRec.get("leftnav_path") != null && feedRec.get("leftnav_path").toString().length() > 0) {
						leftnav_path = feedRec.get("leftnav_path").toString();
						recItemFeed.setLeftnav_path(leftnav_path);
					}
					if (feedRec.get("header_image") != null && feedRec.get("header_image").toString().length() > 0) {
						header_image = feedRec.get("header_image").toString();
						recItemFeed.setHeader_image(header_image);
					}
					if (feedRec.get("photo_small_name") != null && feedRec.get("photo_small_name").toString().length() > 0) {
						photo_small_name = feedRec.get("photo_small_name").toString();
						recItemFeed.setPhoto_small_name(photo_small_name);
					}
					if (feedRec.get("Gallery_name") != null && feedRec.get("Gallery_name").toString().length() > 0) {
						gallery_name = feedRec.get("Gallery_name").toString();
						recItemFeed.setGallery_name(gallery_name);
					}
					if (feedRec.get("strap_headline") != null && feedRec.get("strap_headline").toString().length() > 0) {
						strap_headline = feedRec.get("strap_headline").toString();
						recItemFeed.setStrap_headline(strap_headline);
					}
					if (feedRec.get("Gallery_caption") != null && feedRec.get("Gallery_caption").toString().length() > 0) {
						gallery_caption = feedRec.get("Gallery_caption").toString();
						recItemFeed.setGallery_caption(gallery_caption);
					}
					if (feedRec.get("gallery_sefurl") != null && feedRec.get("gallery_sefurl").toString().length() > 0) {
						gallery_sefurl = feedRec.get("gallery_sefurl").toString();
						recItemFeed.setGallery_sefurl(gallery_sefurl);
					}
					if (feedRec.get("photo_category") != null && feedRec.get("photo_category").toString().length() > 0) {
						photo_category = feedRec.get("photo_category").toString();
						recItemFeed.setPhoto_category(photo_category);
					}
					if (feedRec.get("gallerykeywords") != null && feedRec.get("gallerykeywords").toString().length() > 0) {
						gallerykeywords = feedRec.get("gallerykeywords").toString();
						recItemFeed.setGallerykeywords(gallerykeywords);
					}
					if (feedRec.get("gallerydesc") != null && feedRec.get("gallerydesc").toString().length() > 0) {
						gallerydesc = feedRec.get("gallerydesc").toString();
						recItemFeed.setGallerydesc(gallerydesc);
					}
					if (feedRec.get("photoid") != null && feedRec.get("photoid").toString().length() > 0) {
						photoid = feedRec.get("photoid").toString();
						recItemFeed.setPhotoid(photoid);
					}
					if (feedRec.get("photo_name") != null && feedRec.get("photo_name").toString().length() > 0) {
						photoname = feedRec.get("photo_name").toString();
						recItemFeed.setPhotoname(photoname);
					}
					if (feedRec.get("imagestrap") != null && feedRec.get("imagestrap").toString().length() > 0) {
						imagestrap = feedRec.get("imagestrap").toString();
						recItemFeed.setImagestrap(imagestrap);
					}
					if (feedRec.get("photo_caption") != null && feedRec.get("photo_caption").toString().length() > 0) {
						photoCaption = feedRec.get("photo_caption").toString();
						recItemFeed.setPhotoCaption(photoCaption);
					}
					if (feedRec.get("photo_title") != null && feedRec.get("photo_title").toString().length() > 0) {
						photo_title = feedRec.get("photo_title").toString();
						recItemFeed.setPhoto_title(photo_title);
					}
					if (feedRec.get("photo_name_width") != null && feedRec.get("photo_name_width").toString().length() > 0) {
						photo_width = feedRec.get("photo_name_width").toString();
						recItemFeed.setPhoto_width(photo_width);
					}
					if (feedRec.get("photo_name_height") != null && feedRec.get("photo_name_height").toString().length() > 0) {
						photo_height = feedRec.get("photo_name_height").toString();
						recItemFeed.setPhoto_height(photo_height);
					}
					if (feedRec.get("image_alt_text") != null && feedRec.get("image_alt_text").toString().length() > 0) {
						image_alt_text = feedRec.get("image_alt_text").toString();
						recItemFeed.setImage_alt_text(image_alt_text);
					}
					if (feedRec.get("image_metakey") != null && feedRec.get("image_metakey").toString().length() > 0) {
						image_metakey = feedRec.get("image_metakey").toString();
						recItemFeed.setImage_metakey(image_metakey);
					}
					if (feedRec.get("image_title") != null && feedRec.get("image_title").toString().length() > 0) {
						image_title = feedRec.get("image_title").toString();
						recItemFeed.setImage_title(image_title);
					}
					if (feedRec.get("catname") != null && feedRec.get("catname").toString().length() > 0) {
						catname = feedRec.get("catname").toString();
						recItemFeed.setCatname(catname);
					}
					if (feedRec.get("fulltext") != null && feedRec.get("fulltext").toString().length() > 0) {
						full_text = feedRec.get("fulltext").toString();
						recItemFeed.setFullText(full_text);
					}
					if (feedRec.get("story_highlights_flag") != null && feedRec.get("story_highlights_flag").toString().length() > 0) {
						story_highlights_flag = feedRec.get("story_highlights_flag").toString();
						recItemFeed.setStory_highlights_flag(story_highlights_flag);
					}
					if (feedRec.get("story_highlights") != null && feedRec.get("story_highlights").toString().length() > 0) {
						story_highlights = feedRec.get("story_highlights").toString();
						recItemFeed.setStory_highlights(story_highlights);
					}
					if (feedRec.get("comment_question") != null && feedRec.get("comment_question").toString().length() > 0) {
						comment_question = feedRec.get("comment_question").toString();
						recItemFeed.setComment_question(comment_question);
					}
					if (feedRec.get("reporter_twitter_handle") != null && feedRec.get("reporter_twitter_handle").toString().length() > 0) {
						reporter_twitter_handle = feedRec.get("reporter_twitter_handle").toString();
						recItemFeed.setReporter_twitter_handle(reporter_twitter_handle);
					}
					if (feedRec.get("ismedium") != null && feedRec.get("ismedium").toString().length() > 0) {
						isMedium = feedRec.get("ismedium").toString();
						recItemFeed.setIsMedium(isMedium);
					}
					if (feedRec.get("issueid") != null && feedRec.get("issueid").toString().length() > 0) {
						issueId = feedRec.get("issueid").toString();
						recItemFeed.setIssueId(issueId);
					}
					if (feedRec.get("title_alias") != null && feedRec.get("title_alias").toString().length() > 0) {
						titlealias = feedRec.get("title_alias").toString();
						recItemFeed.setTitlealias(titlealias);
					}
					if (feedRec.get("title_magazine") != null && feedRec.get("title_magazine").toString().length() > 0) {
						titleMagazine = feedRec.get("title_magazine").toString();
						recItemFeed.setTitleMagazine(titleMagazine);
					}
					if (feedRec.get("courtesy") != null && feedRec.get("courtesy").toString().length() > 0) {
						courtesey = feedRec.get("courtesy").toString();
						recItemFeed.setCourtesey(courtesey);
					}
					if (feedRec.get("imagepath") != null && feedRec.get("imagepath").toString().length() > 0) {
						imagePath = feedRec.get("imagepath").toString();
						recItemFeed.setImagePath(imagePath);
					}
					if (feedRec.get("metatags") != null && feedRec.get("metatags").toString().length() > 0) {
						metatags = feedRec.get("metatags").toString();
						recItemFeed.setMetatags(metatags);
					}
					if (feedRec.get("story_syndication") != null && feedRec.get("story_syndication").toString().length() > 0) {
						story_syndication = feedRec.get("story_syndication").toString();
						recItemFeed.setStory_syndication(story_syndication);
					}
					if (feedRec.get("crt") != null && feedRec.get("crt").toString().length() > 0) {
						crt = feedRec.get("crt").toString();
						recItemFeed.setCrt(crt);
					}
					if (feedRec.get("mdate") != null && feedRec.get("mdate").toString().length() > 0) {
						mdate = feedRec.get("mdate").toString();
						recItemFeed.setMdate(mdate);
					}
					if (feedRec.get("snd") != null && feedRec.get("snd").toString().length() > 0) {
						snd = feedRec.get("snd").toString();
						recItemFeed.setSnd(snd);
					}
					if (feedRec.get("article_icon") != null && feedRec.get("article_icon").toString().length() > 0) {
						article_icon = feedRec.get("article_icon").toString();
						recItemFeed.setArticle_icon(article_icon);
					}
					if (feedRec.get("article_icon_img") != null && feedRec.get("article_icon_img").toString().length() > 0) {
						article_icon_img = feedRec.get("article_icon_img").toString();
						recItemFeed.setArticle_icon_img(article_icon_img);
					}
					if (feedRec.get("ctime") != null && feedRec.get("ctime").toString().length() > 0) {
						ctime = feedRec.get("ctime").toString();
						recItemFeed.setCtime(ctime);
					}
					if (feedRec.get("mtime") != null && feedRec.get("mtime").toString().length() > 0) {
						mtime = feedRec.get("mtime").toString();
						recItemFeed.setMtime(mtime);
					}
					if (feedRec.get("quotes") != null && feedRec.get("quotes").toString().length() > 0) {
						quotes = feedRec.get("quotes").toString();
						recItemFeed.setQuotes(quotes);
					}
					if (feedRec.get("blurbs") != null && feedRec.get("blurbs").toString().length() > 0) {
						blurbs = feedRec.get("blurbs").toString();
						recItemFeed.setBlurbs(blurbs);
					}
					if (feedRec.get("tables") != null && feedRec.get("tables").toString().length() > 0) {
						tables = feedRec.get("tables").toString();
						recItemFeed.setTables(tables);
					}
					if (feedRec.get("large_kicker_image_width") != null && feedRec.get("large_kicker_image_width").toString().length() > 0) {
						large_kicker_image_width = feedRec.get("large_kicker_image_width").toString();
						recItemFeed.setLarge_kicker_image_width(large_kicker_image_width);
					}
					if (feedRec.get("kicker_image_caption") != null && feedRec.get("kicker_image_caption").toString().length() > 0) {
						kicker_image_caption = feedRec.get("kicker_image_caption").toString();
						recItemFeed.setKicker_image_caption(kicker_image_caption);
					}
					if (feedRec.get("indiatoday_expert") != null && feedRec.get("indiatoday_expert").toString().length() > 0) {
						indiatoday_expert = feedRec.get("indiatoday_expert").toString();
						recItemFeed.setIndiatoday_expert(indiatoday_expert);
					}
					if (feedRec.get("google_standout") != null && feedRec.get("google_standout").toString().length() > 0) {
						google_standout = feedRec.get("google_standout").toString();
						recItemFeed.setGoogle_standout(google_standout);
					}
					if (feedRec.get("commentbox_flag") != null && feedRec.get("commentbox_flag").toString().length() > 0) {
						commentboxflag = feedRec.get("commentbox_flag").toString();
						recItemFeed.setCommentboxflag(commentboxflag);
					}
					if (feedRec.get("byline_modified_by") != null && feedRec.get("byline_modified_by").toString().length() > 0) {
						byLineModifiedBy = feedRec.get("byline_modified_by").toString();
						recItemFeed.setByLineModifiedBy(byLineModifiedBy);
					}
					if (feedRec.get("external_url") != null && feedRec.get("external_url").toString().length() > 0) {
						externalUrl = feedRec.get("external_url").toString();
						recItemFeed.setExternalUrl(externalUrl);
					}
					
					if (feedRec.get("multibitrate_flag") != null && feedRec.get("multibitrate_flag").toString().length() > 0) {
						multibitrate_flag = feedRec.get("multibitrate_flag").toString();
						recItemFeed.setMultibitrate_flag(multibitrate_flag);
					}
					if (feedRec.get("video_duration") != null && feedRec.get("video_duration").toString().length() > 0) {
						video_duration = feedRec.get("video_duration").toString();
						recItemFeed.setVideo_duration(video_duration);
					}
					if (feedRec.get("created") != null && feedRec.get("created").toString().length() > 0) {
						created = feedRec.get("created").toString();
						recItemFeed.setCreated(created);
					}
					
					if (feedRec.get("mp4_flag") != null && feedRec.get("mp4_flag").toString().length() > 0) {
						mp4_flag = feedRec.get("mp4_flag").toString();
						recItemFeed.setMp4_flag(mp4_flag);
					}
					if (feedRec.get("VideoGallery_foldername") != null && feedRec.get("VideoGallery_foldername").toString().length() > 0) {
						VideoGallery_foldername = feedRec.get("VideoGallery_foldername").toString();
						recItemFeed.setVideoGallery_foldername(VideoGallery_foldername);
					}
					if (feedRec.get("VideoGallery_filename") != null && feedRec.get("VideoGallery_filename").toString().length() > 0) {
						VideoGallery_filename = feedRec.get("VideoGallery_filename").toString();
						recItemFeed.setVideoGallery_filename(VideoGallery_filename);
					}
					if (feedRec.get("multibitrate_flag") != null && feedRec.get("multibitrate_flag").toString().length() > 0) {
						multibitrate_flag = feedRec.get("multibitrate_flag").toString();
						recItemFeed.setMultibitrate_flag(multibitrate_flag);
					}
					
					if (feedRec.get("cat_name") != null && feedRec.get("cat_name").toString().length() > 0) {
						catName = feedRec.get("cat_name").toString();
						recItemFeed.setCat_name(catName);
					}
					
					if (feedRec.get("cat_id") != null && feedRec.get("cat_id").toString().length() > 0) {
						catId = feedRec.get("cat_id").toString();
						recItemFeed.setCat_id(catId);
					}
					if (feedRec.get("cat_template_path") != null && feedRec.get("cat_template_path").toString().length() > 0) {
						cat_template_path = feedRec.get("cat_template_path").toString();
						recItemFeed.setCat_template_path(cat_template_path);
					}
					if (feedRec.get("cat_leftnave_path") != null && feedRec.get("cat_leftnave_path").toString().length() > 0) {
						cat_leftnave_path = feedRec.get("cat_leftnave_path").toString();
						recItemFeed.setCat_leftnave_path(cat_leftnave_path);
					}
					if (feedRec.get("cat_bottomnav_path") != null && feedRec.get("cat_bottomnav_path").toString().length() > 0) {
						cat_bottomnav_path = feedRec.get("cat_bottomnav_path").toString();
						recItemFeed.setCat_bottomnav_path(cat_bottomnav_path);
					}
					if (feedRec.get("cat_rightnav_path") != null && feedRec.get("cat_rightnav_path").toString().length() > 0) {
						cat_rightnav_path = feedRec.get("cat_rightnav_path").toString();
						recItemFeed.setCat_rightnav_path(cat_rightnav_path);
					}
					if (feedRec.get("cat_topnav_path") != null && feedRec.get("cat_topnav_path").toString().length() > 0) {
						cat_topnav_path = feedRec.get("cat_topnav_path").toString();
						recItemFeed.setCat_topnav_path(cat_topnav_path);
					}
					 if (feedRec.get("comments") != null && feedRec.get("comments").toString().length() > 0) {
						 comments = feedRec.get("comments").toString();
						 recItemFeed.setComments(comments);
						}
					 if (feedRec.get("date_time") != null && feedRec.get("date_time").toString().length() > 0) {
						 date_time = feedRec.get("date_time").toString();
							recItemFeed.setDate_time(date_time);
						}
					
					//System.out.println("::::::::::::"+feedRec.get("fulltext"));
					JSONArray related_video = (JSONArray) feedRec.get("related_video");
					JSONArray related_photo = (JSONArray) feedRec.get("related_photo");
					JSONArray authorsList=null;
					JSONArray bitrate_data=null;
					int authorListSize=0;
					int bitrateSize=0;
					if(feedRec.get("authorsList")!=null)
					{
					 authorsList = (JSONArray) feedRec.get("authorsList");
					 authorListSize=authorsList.size();
					}
					if(feedRec.get("bitrate")!=null)
					{
					 bitrate_data = (JSONArray) feedRec.get("bitrate");
					 bitrateSize=bitrate_data.size();
					}
					//System.out.println(">>>>>>>" + related_video.size());
					if (related_video.size() > 0) {
						for (int k = 0; k < related_video.size(); k++) {
							RelatedFeedVideo = new RelatedFeedVideo();
							RelatedFeedVideoList = new ArrayList<RelatedFeedVideo>();
							JSONObject feedRelVideo = (JSONObject) related_video.get(k);

							if (feedRelVideo.get("article_id") != null
									&& feedRelVideo.get("article_id").toString().length() > 0) {
								String articleVid = feedRelVideo.get("article_id").toString();
								RelatedFeedVideo.setArticleId(articleVid);
							}

							if (feedRelVideo.get("related_article_id") != null
									&& feedRelVideo.get("related_article_id").toString().length() > 0) {
								String relatedarticleVid = feedRelVideo.get("related_article_id").toString();
								RelatedFeedVideo.setRelatedArticleId(relatedarticleVid);
							}
							if (feedRelVideo.get("related_type") != null
									&& feedRelVideo.get("related_type").toString().length() > 0) {
								String relatedTypeV = feedRelVideo.get("related_type").toString();
								RelatedFeedVideo.setRelatedType(relatedTypeV);
							}
							if (feedRelVideo.get("featured") != null
									&& feedRelVideo.get("featured").toString().length() > 0) {
								String featuredV = feedRelVideo.get("featured").toString();
								RelatedFeedVideo.setFeatured(featuredV);
							}
							if (feedRelVideo.get("sef_url") != null
									&& feedRelVideo.get("sef_url").toString().length() > 0) {
								String selfUrlV = feedRelVideo.get("sef_url").toString();
								RelatedFeedVideo.setSelfUrl(selfUrlV);
							}
							if (feedRelVideo.get("content_url") != null
									&& feedRelVideo.get("content_url").toString().length() > 0) {
								String contentUrlV = feedRelVideo.get("content_url").toString();
								RelatedFeedVideo.setContentUrl(contentUrlV);
							}
							if (feedRelVideo.get("title") != null
									&& feedRelVideo.get("title").toString().length() > 0) {
								String titleV = feedRelVideo.get("title").toString();
								RelatedFeedVideo.setTitle(titleV);
							}
							if (feedRelVideo.get("kicker_image") != null
									&& feedRelVideo.get("kicker_image").toString().length() > 0) {
								String kickerVimage = feedRelVideo.get("kicker_image").toString();
								RelatedFeedVideo.setKickerImage(kickerVimage);
							}
							RelatedFeedVideoList.add(RelatedFeedVideo);
							recItemFeed.setRelatedFeedsVideo(RelatedFeedVideoList);
						}

					}
					if (related_photo.size() > 0) {
						for (int k = 0; k < related_photo.size(); k++) {
							RelatedFeedPhoto = new RelatedFeedPhoto();
							RelatedFeedPhotoList = new ArrayList<RelatedFeedPhoto>();
							JSONObject feedRelPhoto = (JSONObject) related_photo.get(k);

							if (feedRelPhoto.get("article_id") != null
									&& feedRelPhoto.get("article_id").toString().length() > 0) {
								String articleVid = feedRelPhoto.get("article_id").toString();
								RelatedFeedPhoto.setArticleId(articleVid);
							}

							if (feedRelPhoto.get("related_article_id") != null
									&& feedRelPhoto.get("related_article_id").toString().length() > 0) {
								String relatedarticleVid = feedRelPhoto.get("related_article_id").toString();
								RelatedFeedPhoto.setRelatedArticleId(relatedarticleVid);
							}
							if (feedRelPhoto.get("related_type") != null
									&& feedRelPhoto.get("related_type").toString().length() > 0) {
								String relatedTypeV = feedRelPhoto.get("related_type").toString();
								RelatedFeedPhoto.setRelatedType(relatedTypeV);
							}
							if (feedRelPhoto.get("featured") != null
									&& feedRelPhoto.get("featured").toString().length() > 0) {
								String featuredV = feedRelPhoto.get("featured").toString();
								RelatedFeedPhoto.setFeatured(featuredV);
							}
							if (feedRelPhoto.get("sef_url") != null
									&& feedRelPhoto.get("sef_url").toString().length() > 0) {
								String selfUrlV = feedRelPhoto.get("sef_url").toString();
								RelatedFeedPhoto.setSelfUrl(selfUrlV);
							}
							if (feedRelPhoto.get("content_url") != null
									&& feedRelPhoto.get("content_url").toString().length() > 0) {
								String contentUrlV = feedRelPhoto.get("content_url").toString();
								RelatedFeedPhoto.setContentUrl(contentUrlV);
							}
							if (feedRelPhoto.get("kicker_image") != null
									&& feedRelPhoto.get("kicker_image").toString().length() > 0) {
								String kickerVimage = feedRelPhoto.get("kicker_image").toString();
								RelatedFeedPhoto.setKickerImage(kickerVimage);
							}
							RelatedFeedPhotoList.add(RelatedFeedPhoto);
							recItemFeed.setRelatedFeedsPhoto(RelatedFeedPhotoList);
						}
						
					}
					if (authorListSize > 0) {
						for (int k = 0; k < authorsList.size(); k++) {
							authorBean = new AuthorList();
							authorList = new ArrayList<AuthorList>();
							JSONObject authorsListObj = (JSONObject) authorsList.get(k);

							if (authorsListObj.get("id") != null
									&& authorsListObj.get("id").toString().length() > 0) {
								String id = authorsListObj.get("id").toString();
								authorBean.setId(id);
							}
							if (authorsListObj.get("author_name") != null
									&& authorsListObj.get("author_name").toString().length() > 0) {
								String author_name = authorsListObj.get("author_name").toString();
								authorBean.setAuthorName(author_name);
							}
							if (authorsListObj.get("author_email") != null
									&& authorsListObj.get("author_email").toString().length() > 0) {
								String author_email = authorsListObj.get("author_email").toString();
								authorBean.setAuthorEmail(author_email);
							}
							if (authorsListObj.get("profile_image") != null
									&& authorsListObj.get("profile_image").toString().length() > 0) {
								String profile_image = authorsListObj.get("profile_image").toString();
								authorBean.setProfieImage(profile_image);
							}
							if (authorsListObj.get("designation") != null
									&& authorsListObj.get("designation").toString().length() > 0) {
								String designation = authorsListObj.get("designation").toString();
								authorBean.setDesignation(designation);
							}
							if (authorsListObj.get("full_description") != null
									&& authorsListObj.get("full_description").toString().length() > 0) {
								String full_description = authorsListObj.get("full_description").toString();
								authorBean.setFullDescription(full_description);
							}
							if (authorsListObj.get("sef_url") != null
									&& authorsListObj.get("sef_url").toString().length() > 0) {
								String sef_url = authorsListObj.get("sef_url").toString();
								authorBean.setSefUrl(sef_url);
							}
							if (authorsListObj.get("twitter_id") != null
									&& authorsListObj.get("twitter_id").toString().length() > 0) {
								String twitter_id = authorsListObj.get("twitter_id").toString();
								authorBean.setTwitterId(twitter_id);
							}
							if (authorsListObj.get("facebook_id") != null
									&& authorsListObj.get("facebook_id").toString().length() > 0) {
								String facebook_id = authorsListObj.get("facebook_id").toString();
								authorBean.setFacebookId(facebook_id);
							}
							if (authorsListObj.get("profile_type") != null
									&& authorsListObj.get("profile_type").toString().length() > 0) {
								String profile_type = authorsListObj.get("profile_type").toString();
								authorBean.setProfileType(profile_type);
							}
							authorList.add(authorBean);	
							recItemFeed.setAuthorList(authorList);
						}
						
					}
					if (bitrateSize > 0) {
						for (int k = 0; k < bitrate_data.size(); k++) {
							//bitrate = new Bitrate();
							//bitrateList = new ArrayList<Bitrate>();
							//JSONObject feedBitrate = (JSONObject) bitrateList.get(k);
							
							bitrate = new Bitrate();
							bitrateList = new ArrayList<Bitrate>();
							JSONObject feedBitrate = (JSONObject) bitrate_data.get(k);

							if (feedBitrate.get("bitrate") != null
									&& feedBitrate.get("bitrate").toString().length() > 0) {
								String bitrateGet = feedBitrate.get("bitrate").toString();
								bitrate.setBitrate(bitrateGet);
							}
							if (feedBitrate.get("source_file_name") != null
									&& feedBitrate.get("source_file_name").toString().length() > 0) {
								String bitrateSourceFile = feedBitrate.get("source_file_name").toString();
								bitrate.setSourceFileName(bitrateSourceFile);
							}
							if (feedBitrate.get("file_path") != null
									&& feedBitrate.get("file_path").toString().length() > 0) {
								String bitrateFilePath = feedBitrate.get("file_path").toString();
								bitrate.setFilePath(bitrateFilePath);
							}
							if (feedBitrate.get("STATUS") != null
									&& feedBitrate.get("STATUS").toString().length() > 0) {
								String bitrateStatus = feedBitrate.get("STATUS").toString();
								bitrate.setStatus(bitrateStatus);
							}
							if (feedBitrate.get("file_duration") != null
									&& feedBitrate.get("file_duration").toString().length() > 0) {
								String bitratefile_duration = feedBitrate.get("file_duration").toString();
								bitrate.setFileDuration(bitratefile_duration);
							}
							if (feedBitrate.get("website") != null
									&& feedBitrate.get("website").toString().length() > 0) {
								String bitratewebsite = feedBitrate.get("website").toString();
								bitrate.setWebsite(bitratewebsite);
							}
							if (feedBitrate.get("hls_domain") != null
									&& feedBitrate.get("hls_domain").toString().length() > 0) {
								String bitratehls_domain = feedBitrate.get("hls_domain").toString();
								bitrate.setHlsDomain(bitratehls_domain);
							}
							if (feedBitrate.get("s3_domain") != null
									&& feedBitrate.get("s3_domain").toString().length() > 0) {
								String bitrates3_domain = feedBitrate.get("s3_domain").toString();
								bitrate.setS3Domain(bitrates3_domain);
							}
							if (feedBitrate.get("rtmp_domain") != null
									&& feedBitrate.get("rtmp_domain").toString().length() > 0) {
								String bitratertmp_domain = feedBitrate.get("rtmp_domain").toString();
								bitrate.setRtmpDomain(bitratertmp_domain);
							}
							if (feedBitrate.get("video_type") != null
									&& feedBitrate.get("video_type").toString().length() > 0) {
								String bitratevideo_type = feedBitrate.get("video_type").toString();
								bitrate.setVideoType(bitratevideo_type);
							}
							if (feedBitrate.get("multipart_video") != null
									&& feedBitrate.get("multipart_video").toString().length() > 0) {
								String bitratemultipart_video = feedBitrate.get("multipart_video").toString();
								bitrate.setMultipartVideo(bitratemultipart_video);
							}
							if (feedBitrate.get("ordering") != null
									&& feedBitrate.get("ordering").toString().length() > 0) {
								String bitrateordering = feedBitrate.get("ordering").toString();
								bitrate.setOrdering(bitrateordering);
							}

							
							bitrateList.add(bitrate);
							recItemFeed.setBitrate(bitrateList);
						}
						
					}
					itemFeedList.add(recItemFeed);
					recItem.setFeed(itemFeedList);
				}
				recItem.setId(itemId);
				recItem.setName(itemTitle);
				recItem.setType(itemType);
				recItem.setStart(itemStart);
				recItem.setEnd(itemEnd);
				recItem.setTotal_records(totalRecords);
				recItem.setBlogTitle(blogTitle);
				recItem.setBlogDesc(blogDesc);
				itemList.add(recItem);
				recJson.setItems(itemList);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return recJson;

	}
	
	public Vector getMostRead(String jsonUrl)
	{
		 String id=null;
		 String website_id=null;
		 String data_date=null;
		 String article_id=null;
		 String content_url=null;
		 String content_type=null;
		 String stumbleupon=null;
		 String googleplusone=null;
		 String twitter=null;
		 String pinterest=null;
		 String linkedin=null;
		 String fb_commentsbox_count=null;
		 String fb_click_count=null;
		 String fb_total_count=null;
		 String fb_comment_count=null;
		 String fb_like_count=null;
		 String fb_share_count=null;
		 String image=null;
		 String published=null;
		 String added_on=null;
		 String updated_on=null;
		 String total_count=null;
		 List<String> completeData=null;
		 Vector<List> mostReadData=new Vector<List>();
		try {
			URL url = new URL(jsonUrl);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONArray JsonData = (JSONArray) jsonObject.get("JsonData");
			for(int i=0;i<JsonData.size();i++)
			{
				  completeData=new ArrayList<String>();
				 
				  JSONObject content =(JSONObject) JsonData.get(i);
				  id=(String) content.get("id");
				  website_id=(String) content.get("website_id");
				  data_date=(String) content.get("data_date");
				  article_id=(String) content.get("article_id");
				  content_url=content.get("content_url").toString();
				  content_type=content.get("content_type").toString();
				  stumbleupon=content.get("stumbleupon").toString();
				  googleplusone=content.get("googleplusone").toString();
				  twitter=content.get("twitter").toString();
				  pinterest=content.get("pinterest").toString();
				  linkedin=content.get("linkedin").toString();
				  fb_commentsbox_count=content.get("fb_commentsbox_count").toString();
				  fb_click_count=content.get("fb_click_count").toString();
				  fb_total_count=content.get("fb_total_count").toString();
				  fb_comment_count=content.get("fb_comment_count").toString();
				  fb_like_count=content.get("fb_like_count").toString();
				  fb_share_count=content.get("fb_share_count").toString();
				  image=content.get("image").toString();
				  published=content.get("published").toString();
				  added_on=content.get("added_on").toString();
				  updated_on=content.get("updated_on").toString();
				  total_count=content.get("total_count").toString();
					  completeData.add(id);
					  completeData.add(website_id);
					  completeData.add(data_date);
					  completeData.add(article_id);
					  completeData.add(content_url);
					  completeData.add(content_type);
					  completeData.add(stumbleupon);
					  completeData.add(googleplusone);
					  completeData.add(twitter);
					  completeData.add(pinterest);
					  completeData.add(linkedin);
					  completeData.add(fb_commentsbox_count);
					  completeData.add(fb_click_count);
					  completeData.add(fb_total_count);
					  completeData.add(fb_comment_count);
					  completeData.add(fb_share_count);
					  completeData.add(image);
					  completeData.add(published);
					  completeData.add(added_on);
					  completeData.add(updated_on);
					  completeData.add(total_count);
					  mostReadData.add(completeData);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mostReadData;
	}
  
	/*public JsonRec callService(String servicePath, String url) {
		JsonRec output = null;
		try {
			Client client = Client.create();
			WebResource resource = client.resource(servicePath + url);
			ClientResponse response = resource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

			if (response.getStatus() == 200) {
				output = response.getEntity(JsonRec.class);
			}
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;

	}*/

}
