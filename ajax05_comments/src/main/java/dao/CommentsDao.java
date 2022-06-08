package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.CommentsVo;

public class CommentsDao
{
	public static int delete(int num)
	{
		String sql = "delete from comments where num = ?";
		try(Connection con = JdbcUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);)
		{
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static int insert(CommentsVo vo)
	{
		String sql = "insert into comments values(comments_seq.nextval, ?, ?, ?)";
		try(Connection con = JdbcUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);)
		{
			pstmt.setInt(1, vo.getMnum());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getComments());
			return pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static ArrayList<CommentsVo> cList(int mnum, int startRow, int endRow)
	{
		ArrayList<CommentsVo> list = new ArrayList<CommentsVo>();
		
		String sql = "select * from ("
				+ "select com.*, rownum rnum from ("
				+ "select * from comments where mnum=? order by num desc"
				+ ") com"
				+ ") where rnum >= ? and rnum <= ?";
		try(Connection con = JdbcUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);)
		{
			pstmt.setInt(1, mnum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				CommentsVo vo = new CommentsVo();
				vo.setNum(rs.getInt("num"));
				vo.setMnum(rs.getInt("mnum"));
				vo.setId(rs.getString("id"));
				vo.setComments(rs.getString("comments"));
				list.add(vo);
			}
			rs.close();
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getCount(int mnum)
	{
		
	}
	
	public static ArrayList<CommentsVo> select(int mnum)
	{
		ArrayList<CommentsVo> list = new ArrayList<CommentsVo>();
		
		String sql = "select * from comments where mnum = ?";
		try(Connection con = JdbcUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);)
		{
			pstmt.setInt(1, mnum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				CommentsVo vo = new CommentsVo();
				vo.setNum(rs.getInt("num"));
				vo.setMnum(rs.getInt("mnum"));
				vo.setId(rs.getString("id"));
				vo.setComments(rs.getString("comments"));
				list.add(vo);
			}
			rs.close();
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
