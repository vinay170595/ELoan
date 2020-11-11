package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.iiht.evaluation.eloan.service.ILoanService;
import com.iiht.evaluation.eloan.service.IUserService;
import com.iiht.evaluation.eloan.service.LoanServiceImpl;
import com.iiht.evaluation.eloan.service.UserServiceImpl;






@WebServlet({"/user","/validate","/registernewuser","/placeloan","/application1","/editLoanProcess","/registeruser","/register",
	"/application",	"/trackloan","/editloan","/displaystatus","/logout"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	private IUserService userService;
	private ILoanService loanService;
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	HttpSession session = null;
	
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.userService = new UserServiceImpl();
		this.loanService =new LoanServiceImpl();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		session=request.getSession();
		String action = request.getServletPath();
		String viewName = "";
		
		try {
			switch (action) {
			case "/registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "/validate":
				viewName=validate(request,response);
				break;
			case "/placeloan":
				viewName=placeloan(request,response);
				break;
			case "/application1":
				viewName=application1(request,response);
				break;
			case "/editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "/registeruser":
				viewName=registerUser(request,response);
				break;
			case "/register":
				viewName = register(request, response);
				break;
			case "/application":
				viewName = application(request, response);
				break;
			case "/trackloan":
				viewName = trackloan(request, response);
				break;
			case "/editloan":
				viewName = editloan(request, response);
				break;	
			case  "/displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// write the code to validate the user //
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nextPage = "";
		
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			nextPage = "index.jsp";
			request.setAttribute("err", "Login ID or Password is empty!");
		} else {
			try {
				User user = userService.validateUser(username, password);
				if(user != null) {
					session.setAttribute("user", user);
					if(user.getRole().equals("ADMIN")) {
						nextPage = "adminhome1.jsp";
					}
					else {
						nextPage = "userhome1.jsp";
					}
				} else {
					nextPage = "index.jsp";
					request.setAttribute("err", "Login ID or Password is not valid!");
				}
			} catch (LoanException e) {
				request.setAttribute("err", e.getMessage());
				nextPage = "index.jsp";
			}
		}
		return nextPage;

	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) throws LoanException {
		// TODO Auto-generated method stub
	// write the code to place the loan information /
		LoanInfo loanInfo =new LoanInfo();
		System.out.println("****");
		
		session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		//loanInfo.set
		loanInfo.setUsername(user.getUsername());
		loanInfo.setPurpose(request.getParameter("purpose"));
		loanInfo.setAmtrequest(Integer.parseInt(request.getParameter("amtrequest")));
		loanInfo.setDoa(request.getParameter("doa"));
		loanInfo.setBstructure(request.getParameter("bstructure"));
		loanInfo.setBindicator(request.getParameter("bindicator"));
		loanInfo.setTindicator(request.getParameter("tindicator"));
		loanInfo.setAddress(request.getParameter("address"));
		loanInfo.setEmail(request.getParameter("email"));
		loanInfo.setMobile(request.getParameter("mobile"));
		loanInfo.setStatus("New");
			
		loanService.applyLoan(loanInfo);
		request.setAttribute("msg", "Loan Application saved sucessfully!");
		return "userhome1.jsp";
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	// write the code to display the loan application page /
		request.setAttribute("isNew", true);
		return "application.jsp";
		
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		// write the code to edit the loan info /
		
		return null;
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		// write the code to redirect page to read the user details /
		return "newuserui.jsp";
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nextPage = "";
		/*
		System.out.println("Entered username is"+username);
		System.out.println("Entered Password is"+password);*/
		
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			nextPage = "register.jsp";
			request.setAttribute("err", "Login ID or Password is Empty!");
		} else {
			User user = new User(username, password, "USER");
			try {
				User createdUser = userService.createUser(user);
				if(createdUser == null) {
					nextPage = "register.jsp";
					request.setAttribute("err", "Login ID already exists!");
				}
				else {
					nextPage = "index.jsp";
					request.setAttribute("msg", "Login ID created successfully!");
				}
			} catch (LoanException e) {
				request.setAttribute("err", e.getMessage());
				nextPage="errorPage.jsp";
			}
		}
		
		return nextPage;
		
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// write the code to redirect to register page /
		
		return "newuserui.jsp";
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		return null;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	// write a code to return to editloan page /
		return null;
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	// write a code to return to trackloan page /
		
		return null;
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	// write a code to return to trackloan page /
		return null;
	}
}
