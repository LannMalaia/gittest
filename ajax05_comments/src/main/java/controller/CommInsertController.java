package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.CommentsDao;
import vo.CommentsVo;

@WebServlet("/comm/insert")
public class CommInsertController extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int mnum = Integer.parseInt(req.getParameter("mnum"));
		String id = req.getParameter("id");
		String comments = req.getParameter("comments");

		int result = CommentsDao.insert(new CommentsVo(0, mnum, id, comments));
		
		
		resp.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		if (result > 0)
			json.put("code", "success");
		else
			json.put("code", "failure");
		pw.print(json);
		
//		resp.setContentType("text/xml; charset=utf-8");
//		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		pw.print("<result>");
//		if (result > 0)
//			pw.print("<code>success</code>");
//		else
//			pw.print("<code>fail</code>");
//		pw.print("</result>");
	}
	
}
