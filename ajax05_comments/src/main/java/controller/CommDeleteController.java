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

@WebServlet("/comm/delete")
public class CommDeleteController extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int num = Integer.parseInt(req.getParameter("num"));

		int result = CommentsDao.delete(num);

		resp.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		if (result > 0)
			json.put("code", "del success");
		else
			json.put("code", "del failure");
		pw.print(json);
		
//		resp.setContentType("text/xml; charset=utf-8");
//		PrintWriter pw = resp.getWriter();
//		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		pw.print("<result>");
//		if (result > 0)
//			pw.print("<code>success</code>");
//		else
//			pw.print("<code>fail</code>");
//		pw.print("</result>");
	}
	
}
