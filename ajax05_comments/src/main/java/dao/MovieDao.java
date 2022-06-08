package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.MovieVo;

public class MovieDao
{
	public static ArrayList<MovieVo> selectAll()
	{
		ArrayList<MovieVo> list = new ArrayList<MovieVo>();
		
		try(Connection con = JdbcUtil.getCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from movie");)
		{
			while (rs.next())
			{
				MovieVo vo = new MovieVo();
				vo.setMnum(rs.getInt("mnum"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDirector(rs.getString("director"));
				list.add(vo);
			}
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static MovieVo select(int num)
	{
		String sql = "select * from movie where mnum = ?";
		try(Connection con = JdbcUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);)
		{
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				MovieVo vo = new MovieVo();
				vo.setMnum(rs.getInt("mnum"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDirector(rs.getString("director"));
				rs.close();
				return vo;
			}
			return null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
