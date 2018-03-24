package com.itgd.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.PollBean;

public class Poll {

	public static ArrayList poll(int siteId) {
		Connection conn = null;
		Statement stmt2 = null;
		ResultSet rs2;
		PollBean pbean;
		ArrayList polldata = new ArrayList();
		try {

			Dbconnection connect = null;
			String sql = "SELECT pt.pollid, pm.title, pt.id, pt.text FROM jos_poll_data pt, jos_polls pm where pt.pollid=(select Max(id) from jos_polls where  published=1 and site_id='"+siteId+"') and pm.id=pt.pollid and pt.text !='' and pm.published=1 order by pt.id";
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(sql);
			//System.out.println(sql);
			while (rs2.next()) {
				pbean = new PollBean();
				pbean.setOption(rs2.getString("text"));
				pbean.setOptionid(rs2.getInt("id"));
				pbean.setPollid(rs2.getInt("pollid"));
				pbean.setQuestion(rs2.getString("title"));
				polldata.add(pbean);

			}
			rs2.close();
			stmt2.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
		}
		return polldata;
	}
	
	public static ArrayList previousid(int siteId)
	{
		ArrayList pid = new ArrayList();
		Connection conn = null;
		Statement stmt2 = null;
		ResultSet rs2;
		PollBean id;
		try {

			Dbconnection connect = null;
			String sql = "SELECT id FROM jos_polls where published=1 and site_id='"+siteId+"' order by id desc limit 10";
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(sql);
			//System.out.println(sql);
			while (rs2.next()) {
				id = new PollBean();
				id.setPreviouspollid(rs2.getInt("id"));
				pid.add(id);

			}
			rs2.close();
			stmt2.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
		}
		
		return pid;
	}
	public static int totalhits(int id)
	{
		int totalpoll = 0;
		Connection conn = null;
		Statement stmt2 = null;
		ResultSet rs2;
		try {

			Dbconnection connect = null;
			String sql = "SELECT sum(hits) as totHits FROM jos_poll_data where pollid="+id+" and text != \'\' ";
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(sql);
			//System.out.println(sql);
			while (rs2.next()) {
				
				totalpoll = rs2.getInt("totHits");

			}
			rs2.close();
			stmt2.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
		}
		
		return totalpoll;
	}
	
	public static ArrayList previousresults(int pollid)
	{
		ArrayList previousresult = new ArrayList();
		Connection conn = null;
		Statement stmt2 = null;
		ResultSet rs2;
		PollBean result;
		try {

			Dbconnection connect = null;
			String sql = "SELECT pd.id, pd.pollid, p.title, pd.text, pd.hits FROM jos_poll_data pd, jos_polls p where pd.pollid="+pollid+" and pd.pollid=p.id and text != \'\'";
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(sql);
			//System.out.println(sql);
			while (rs2.next()) {
				result = new PollBean();
				result.setPreviousHits(rs2.getInt("hits"));
				result.setPreviousOption(rs2.getString("text"));
				result.setPreviousQuestion(rs2.getString("title"));
				previousresult.add(result);
				}
			rs2.close();
			stmt2.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
		}
		
		return previousresult;
	}
	public static ArrayList polldate(int pollid)
	{
		ArrayList date = new ArrayList();
		Connection conn = null;
		Statement stmt2 = null;
		ResultSet rs2;
		PollBean polldate;
		
		try {

			Dbconnection connect = null;
			String sql = "SELECT date_format(date, '%W, %M %e, %Y; %k:%i IST') as polldate FROM jos_poll_date d where poll_id="+pollid+"";
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery(sql);
			//System.out.println(sql);
			while (rs2.next()) {
				polldate = new PollBean();
				polldate.setPolldates(rs2.getString("polldate"));
				date.add(polldate);
				}
			rs2.close();
			stmt2.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
		}
		
		
		return date;
	}
	
	
}
