package com.itgd.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.text.*;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.PollDTO;

public class PollHelper {
	public static ArrayList latestPollDetail(int siteId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PollDTO pDTO = null;
		ArrayList pollAL = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			String sql = "SELECT pt.pollid, pm.title, pt.id, pt.text FROM jos_poll_data pt, jos_polls pm " +
					"where pt.pollid=(select Max(id) from jos_polls where  published=1 and site_id=?) and pm.id=pt.pollid and pt.text !='' and pm.published=1 order by pt.id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, siteId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pDTO = new PollDTO();
				pDTO.setPollOptionTitle(rs.getString("text"));
				pDTO.setPollOptionId(rs.getInt("id"));
				pDTO.setPollId(rs.getInt("pollid"));
				pDTO.setPollTitle(rs.getString("title"));
				pollAL.add(pDTO);
			}
		} catch (Exception e) {
			System.out.println("PollHelper pollDetail Exception is ->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return pollAL;
	}
	
	public static ArrayList pollResult(int pollid) throws Exception
	{
		ArrayList prAL = new ArrayList();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt1 = null;
		PollDTO pDTO = null;
		String pDateFirst = "";
		String pDateLast = "";
		String selectQuery ="";
			
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			selectQuery = "SELECT date_format(date, '%W, %M %e, %Y; %k:%i IST') as polldate FROM jos_poll_date where poll_id=?";
			pstmt1 = conn.prepareStatement(selectQuery);
			pstmt1.setInt(1, pollid);
			rs1 = pstmt1.executeQuery();
			int ctr=0;
			while(rs1.next()) {
				if(ctr==0)
					pDateFirst = rs1.getString("polldate");
				else
					pDateLast = rs1.getString("polldate");
				ctr++;
			}
			selectQuery = "";
			rs1 = null;
			pstmt1 = null;
			
			String sql = "SELECT pd.pollid, p.title, pd.id, pd.text, pd.hits FROM jos_poll_data pd, jos_polls p " +
					"where pd.pollid=? and pd.pollid=p.id and text != \'\'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pollid);
			rs = pstmt.executeQuery();
			//System.out.println(sql);
			while (rs.next()) {
				pDTO = new PollDTO();
				pDTO.setPollId(rs.getInt("pollid"));
				pDTO.setPollTitle(rs.getString("title"));
				pDTO.setPollOptionHits(rs.getInt("hits"));
				pDTO.setPollOptionTitle(rs.getString("text"));
				pDTO.setPollDateFirst(pDateFirst);
				pDTO.setPollDateLast(pDateLast);
				prAL.add(pDTO);
				}
		} catch (Exception e) {
			System.out.println("PollHelper previousPollResult Exception ->" + e);
		} finally {
			if(rs1!=null)
				rs1.close();
			if(pstmt1!=null)
				pstmt1.close();
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return prAL;
	}

	
	public static ArrayList insertUpdatePollResult(int pollid, int optionid) throws Exception
	{
		ArrayList prAL = new ArrayList();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		ResultSet rs1 = null;
		PreparedStatement st1 = null;
		PollDTO pDTO = null;
			
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			conn = connect.getConnectionForWriteOnly();
			
			st = conn.prepareStatement("update jos_poll_data set hits = hits +1 where pollid=? and id=? and text != \'\' ");
			st.setInt(1,pollid);
			st.setInt(2,optionid);
			st.executeUpdate();
			
			st1 = conn.prepareStatement("insert into jos_poll_date(date,vote_id,poll_id) values (now(),?,?)");
			st.setInt(1,pollid);
			st.setInt(2,optionid);
			st1.executeUpdate();
		} catch (Exception e) {
			System.out.println("PollHelper insertUpdatePollResult Exception ->" + e);
		} finally {
			if(rs1!=null)
				rs1.close();
			if(st1!=null)
				st1.close();
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(conn!=null)
				conn.close();
		}
		return prAL;
	}

	
	public static ArrayList previousPollResults(int siteid, int countToDisplay) throws Exception
	{
		ArrayList prAL = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PollDTO pDTO = null;
		String pollIds = "";
		String pollIdCondition = "";
		String selectQuery = "";
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			conn = connect.getConnection();
			
			selectQuery = "SELECT id FROM jos_polls where published=1 and site_id=? order by id desc limit ?";
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, siteid);
			pstmt.setInt(2, countToDisplay);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(pollIds.equals(""))
					pollIds = "" + rs.getInt("id");
				else
					pollIds = pollIds + ", " + rs.getInt("id");
			}
			System.out.println("pollIds->"+pollIds);
			
			selectQuery = "";
			rs = null;
			pstmt = null;

			if(!pollIds.equals("")) {
				if(pollIds.indexOf(",") > 0)
					pollIdCondition = "p.id in ("+pollIds+") ";
				else
					pollIdCondition = "p.id="+pollIds ;
			}
			
			selectQuery = "SELECT pd.pollid, p.title, pd.id, pd.text, pd.hits FROM jos_poll_data pd, jos_polls p " +
					"where "+pollIdCondition+" and pd.pollid=p.id and text != \'\'";
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			System.out.println(selectQuery);
			while (rs.next()) {
				pDTO = new PollDTO();
				pDTO.setPollId(rs.getInt("pollid"));
				pDTO.setPollTitle(rs.getString("title"));
				pDTO.setPollOptionHits(rs.getInt("hits"));
				pDTO.setPollOptionTitle(rs.getString("text"));
				pDTO.setPreviousPollIds(pollIds);
				prAL.add(pDTO);
				}
		} catch (Exception e) {
			System.out.println("PollHelper previousPollResults Exception ->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return prAL;
	}
	
		public static ArrayList pollDate(String pollids) throws Exception
		{
			ArrayList pdAL = new ArrayList();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			PollDTO pDTO = null;
			String[] pollIdArray = null;
			String sqlSelect = "";
			String pDateFirst = "";
			String pDateLast = "";
			int ctr=0;
			if(pollids.length() > 0) {
				if(pollids.indexOf(',') > 0) {
					pollIdArray = pollids.split(","); 
				} else {
					pollIdArray = new String[1];
					pollIdArray[0] = pollids; 
				}
			}
			try {
				Dbconnection connect = null;
				connect = Dbconnection.getInstance();
				conn = connect.getConnection();
				for(int i=0; i < pollIdArray.length; i++) {
					sqlSelect = "SELECT date_format(date, '%W, %M %e, %Y; %k:%i IST') as polldate FROM jos_poll_date d where poll_id=?";
					pstmt = conn.prepareStatement(sqlSelect);
					pstmt.setInt(1, Integer.parseInt(pollIdArray[i].trim()));
					rs = pstmt.executeQuery();
					while (rs.next()) {
						if(ctr==0) {
							pDateFirst = rs.getString("polldate");
						} else {
							pDateLast = rs.getString("polldate");
						}
						ctr++;
					}
					pDTO = new PollDTO();
					pDTO.setPollId(Integer.parseInt(pollIdArray[i].trim()));
					pDTO.setPollDateFirst(pDateFirst);
					pDTO.setPollDateLast(pDateLast);
					pdAL.add(pDTO);
					ctr=0;
				}
			} catch (Exception e) {
				System.out.println("PollHelper polldate Exception ->" + e);
			} finally {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}
			return pdAL;
		}
	}