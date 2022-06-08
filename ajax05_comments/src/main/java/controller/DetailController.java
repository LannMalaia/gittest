package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;

@WebServlet("/detail")
public class DetailController extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setAttribute("vo", MovieDao.select(Integer.parseInt(req.getParameter("mnum"))));
		req.getRequestDispatcher("detail.jsp").forward(req, resp);
	}
}
