package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CommentsDao;
import vo.CommentsVo;

@WebServlet("/comm/list")
public class CommListController extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int mnum = Integer.parseInt(req.getParameter("mnum"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int startRow = (pageNum - 1) * 5 + 1;
		int endRow = startRow + 4;
		ArrayList<CommentsVo> list = CommentsDao.cList(mnum, startRow, endRow);
		int pageCount = (int)Math.ceil(CommentsDao.getCount(mnum) / 5.0);
		int startPage = (pageNum - 1) / 5 * 5 + 1;
		int endPage = startPage + 4;
		if (endPage > pageCount)
		{
			endPage = pageCount;
		}
		
		resp.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		JSONArray arr = new JSONArray();
		for (CommentsVo vo : CommentsDao.select(mnum))
		{
			JSONObject temp = new JSONObject();
			temp.put("num", vo.getNum());
			temp.put("mnum", vo.getMnum());
			temp.put("id", vo.getId());
			temp.put("comments", vo.getComments());
			
			arr.put(temp);
		}
		JSONObject json = new JSONObject();
		json.put("list", arr);
		json.put("pageCount", pageCount);
		json.put("startPage", startPage);
		json.put("endPage", endPage);
		json.put("pageNum", pageNum);
		pw.print(json);
	}
	
}
