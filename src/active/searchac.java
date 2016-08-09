package active;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JdbcConnection;
import active.acentity;
import user.uentity;

public class searchac extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public searchac() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		Connection con = JdbcConnection.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String SQL_FIND="select a.activename,a.acontent,a.acdate from active as a left join user as u on a.u_id=u.id where 1=1";
			StringBuilder SQL_OPT_FIND=new StringBuilder(SQL_FIND);
			
			String name=request.getParameter("uname");
			System.out.println(name);
			if(name!=null){
				SQL_OPT_FIND.append(" AND u.name="+'"'+name+'"');
			}
			rs=st.executeQuery(SQL_OPT_FIND.toString());
			List<acentity> acentities=new ArrayList<>();
			List<uentity> userlist=new ArrayList<>();
			uentity u=new uentity();
			u.setName(name);
			userlist.add(u);
			while(rs.next()){
				acentity a=new acentity();
				String acname  = rs.getString("activename");
				Date adate = rs.getDate("acdate");
				String acont=rs.getString("acontent"); 
				a.setAcdate(adate);
				a.setActivename(acname);
				a.setAcontent(acont);
				acentities.add(a);
				}
			if(acentities.size()>0){
				request.setAttribute("acentitiy",acentities);
				request.setAttribute("userli", userlist);
			request.getRequestDispatcher("/content.jsp").forward(request, response);
			}
			else request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnection.colse(rs, st, con);
		}
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

