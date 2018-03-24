package com.itgd.helper;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.ArchiveBean;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArchiveIssue
{
  public static ArrayList sections(int issueid)
  {
    ArrayList sectionlist = new ArrayList();
    Connection archiveConn = null;
    Statement stmtArch = null;
    try
    {
      Dbconnection connectArch = null;
      String sql = "select c.mobile_image,c.kicker_image_alt_text,c.kicker_image,c.content_highlighted,c.content_locked,s.id as id,s.name as name ,c.byline,c.id as contentid,c.title,c.content_url,c.introtext,c.sef_url from jos_content c, jos_sections s, jos_magazine_articles m , jos_article_section ars where ars.section_id=s.id and s.published=1 and c.id=m.article_id and ars.article_id=m.article_id and c.id=ars.article_id and m.catid='" + issueid + "' AND c.state=1 and s.id not in(20,30) and s.issue_free=0 Group By ars.article_id order by s.name desc";
      connectArch = Dbconnection.getInstance();
      archiveConn = connectArch.getConnection();
      stmtArch = archiveConn.createStatement();
      ResultSet rsArch = stmtArch.executeQuery(sql);

      while (rsArch.next()) {
        ArchiveBean list = new ArchiveBean();
        list.setArchivearticleid(rsArch.getInt("contentid"));
        list.setArchivekicker(rsArch.getString("introtext"));
        list.setArchivesectionbyline(rsArch.getString("byline"));
        list.setArchivesectionid(rsArch.getInt("id"));
        list.setArchivesectionname(rsArch.getString("name"));
        list.setArchivetitle(rsArch.getString("title"));
        list.setSefUrl(rsArch.getString("sef_url"));
        list.setContentHighlighted(rsArch.getInt("content_highlighted"));
        list.setContentLocked(rsArch.getInt("content_locked"));
        list.setKickerImage(rsArch.getString("kicker_image"));
        list.setKickerImageAltText(rsArch.getString("kicker_image_alt_text"));
        list.setSmallImage(rsArch.getString("mobile_image"));
        list.setContentUrl(rsArch.getString("content_url"));

        sectionlist.add(list);
      }
      rsArch.close();
      stmtArch.close();
      archiveConn.close();
    }
    catch (Exception e)
    {
      System.out.println("Exception in sections  is: " + e);
    }

    return sectionlist;
  }

  public static ArrayList Cover(int issueid)
  {
    ArrayList CoverStory = new ArrayList();
    Connection coverConn = null;
    Statement stmtCover = null;
    try
    {
      Dbconnection connectCover = null;
      String sql = "select  c.extralarge_image as extralarge_image ,c.byline_modified_by,date_format( c.created,'%M %e, %Y' ) AS crt, date_format( c.modified,'%M %e, %Y' ) AS mdate, date_format( c.created,'%Y-%m-%d' ) AS snd,date_format( c.created,'%H:%i') AS ctime, date_format( c.modified,'%H:%i') AS mtime,c.city, c.courtesy,c.content_highlighted,c.content_url, c.content_locked,c.large_kicker_image_alt_text,c.`large_kicker_image`,d.mast_head_image,d.title as date,s.id as id,s.name as name ,c.byline,c.id as contentid,c.title,c.introtext,c.sef_url from jos_content c, jos_sections s, jos_magazine_articles m , jos_article_section ars,jos_magazine_categories d where ars.section_id=s.id and s.published=1 and c.id=m.article_id and ars.article_id=m.article_id and c.id=ars.article_id and m.catid='" + issueid + "' and d.id=m.catid AND s.id=30 AND c.state=1 Group By ars.article_id order by ars.ordering desc ";
      connectCover = Dbconnection.getInstance();
      coverConn = connectCover.getConnection();
      stmtCover = coverConn.createStatement();
      ResultSet rs = stmtCover.executeQuery(sql);

      while (rs.next()) {
        ArchiveBean cover = new ArchiveBean();
        cover.setArchivecover(rs.getString("mast_head_image"));
        cover.setArchivedate(rs.getString("date"));
        cover.setCoverartid(rs.getInt("contentid"));
        cover.setCoverbyline(rs.getString("byline"));
        cover.setCoverKicker(rs.getString("introtext"));
        cover.setArchivesectionname(rs.getString("name"));
        cover.setCovertitle(rs.getString("title"));
        cover.setSefUrl(rs.getString("sef_url"));
        cover.setLargeKickerImage(rs.getString("large_kicker_image"));
        cover.setLargeKickerImageCaption(rs.getString("large_kicker_image_alt_text"));
        cover.setContentHighlighted(rs.getInt("content_highlighted"));
        cover.setContentLocked(rs.getInt("content_locked"));
        cover.setContentUrl(rs.getString("content_url"));
        cover.setCreatedDate(rs.getString("crt") == null ? "" : rs.getString("crt"));
        cover.setUpdatedDate(rs.getString("mdate") == null ? "" : rs.getString("mdate"));
        cover.setSyndicationDate(rs.getString("snd") == null ? "" : rs.getString("snd"));
        cover.setCity(rs.getString("city"));
        cover.setCourtesy(rs.getString("courtesy") == null ? "" : rs.getString("courtesy"));
        cover.setCreatedTime(rs.getString("ctime") == null ? "" : rs.getString("ctime"));
        cover.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));
        cover.setBylineModifiedBy(rs.getString("byline_modified_by") == null ? "" : rs.getString("byline_modified_by"));
        cover.setExtraLargeImage(rs.getString("extralarge_image")==null ?"" :rs.getString("extralarge_image"));
        CoverStory.add(cover);
      }
      rs.close();
      stmtCover.close();
      coverConn.close();
    }
    catch (Exception e)
    {
      System.out.println("Exception in Cover is: " + e);
    }
    return CoverStory;
  }

  public static String issueYear(int issueid) {
    ArrayList CoverStory = new ArrayList();
    Connection coverConn = null;
    Statement stmtCover = null;

    String issueYear = "";
    try
    {
      Dbconnection connectCover = null;
      String sql = "SELECT A.TITLE as title FROM jos_magazine_categories A  where ID='" + issueid + "'";
      connectCover = Dbconnection.getInstance();
      coverConn = connectCover.getConnection();
      stmtCover = coverConn.createStatement();
      ResultSet rs = stmtCover.executeQuery(sql);
      while (rs.next())
      {
        issueYear = rs.getString("title");
      }

      rs.close();
      stmtCover.close();
      coverConn.close();
    }
    catch (Exception e)
    {
      System.out.println("Exception in Cover is: " + e);
    }
    return issueYear;
  }

  public static String Supplements(int issueid) {
    String Suppid = "";
    String suppsS = "";
    int supps = 0;
    Connection suppConn = null;
    Statement stmtSupp = null;
    try
    {
      Dbconnection connectSupp = null;
      String sql = "select also_thisweek,supplements_subsection from jos_magazine_categories where id='" + issueid + "'";
      connectSupp = Dbconnection.getInstance();
      suppConn = connectSupp.getConnection();
      stmtSupp = suppConn.createStatement();
      ResultSet rs = stmtSupp.executeQuery(sql);

      if (rs.next()) {
        suppsS = rs.getString("also_thisweek");
        supps = rs.getInt("supplements_subsection");
      }
      rs.close();
      stmtSupp.close();
      suppConn.close();
    }
    catch (Exception e)
    {
      System.out.println("Exception in Supplements  is: " + e);
    }

    if (suppsS.equals(""))
    {
      Suppid = supps+"";
    }
    else
    {
      Suppid = suppsS;
    }
    return Suppid;
  }

  public static ArrayList SupplementsData(int issueid, int id) {
    ArrayList data = new ArrayList();
    Connection suppConn = null;
    Statement stmtSupp = null;
    try
    {
      Dbconnection connectSupp = null;
      String sql = "select c.content_highlighted,c.content_locked,s.section_id,c.byline,c.id as contentid,c.title,c.content_url, c.introtext, jc.id as suppID, mc.suppliment_image_url,jc.name as name,c.sef_url from jos_content c, jos_article_section s, jos_magazine_articles m, jos_magazine_categories mc, jos_categories jc where c.id=s.article_id AND s.article_id=m.article_id and s.cat_id='" + id + "' AND jc.id=s.cat_id and jc.id='" + id + "' and jc.published=1 and jc.section=s.section_id and m.catid='" + issueid + "' AND mc.id=m.catid and mc.published=1 and c.state=1 AND s.section_id='20' Group By s.article_id order by s.ordering desc";
      connectSupp = Dbconnection.getInstance();
      suppConn = connectSupp.getConnection();
      stmtSupp = suppConn.createStatement();
      ResultSet rs = stmtSupp.executeQuery(sql);

      while (rs.next()) {
        ArchiveBean supplement = new ArchiveBean();
        supplement.setSuppArtid(rs.getInt("contentid"));
        supplement.setSuppByline(rs.getString("byline"));
        supplement.setSuppId(rs.getInt("suppID"));
        supplement.setSuppKicker(rs.getString("introtext"));
        supplement.setSuppname(rs.getString("name"));
        supplement.setSuppTitle(rs.getString("title"));
        supplement.setSuppImage(rs.getString("suppliment_image_url"));
        supplement.setSefUrl(rs.getString("sef_url"));
        supplement.setContentHighlighted(rs.getInt("content_highlighted"));
        supplement.setContentLocked(rs.getInt("content_locked"));
        supplement.setContentUrl(rs.getString("content_url"));

        data.add(supplement);
      }
      rs.close();
      stmtSupp.close();
      suppConn.close();
    }
    catch (Exception e)
    {
      System.out.println("Exception in SupplementsData is: " + e);
    }

    return data;
  }
}