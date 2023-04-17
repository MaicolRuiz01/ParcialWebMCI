package co.empresa.parcialWeb.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.parcialWeb.DAO.PacienteDao;


/**
 * Servlet implementation class PacienteServlet
 */
@WebServlet("/")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PacienteDao pacienteDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PacienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		this.pacienteDao=new PacienteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String action = request.getServletPath();
		
		try {

			switch (action) {

			case "/new":
				showNewForm(request, response);
				break;

			case "/insert":
				insertarpaciente(request, response);
				break;

			case "/delete":
				eliminarpaciente(request, response);
				 
				break;
				
			case "/edit":
				showEditForm(request,response);
				
				break;

			case "/update":
				actualizarpaciente(request,response);
				break;

			default:
				listPacientes(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void showNewForm(HttpServletRequest request,HttpServletResponse response) 
			throws IOException, ServletException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("paciente.jsp");
		dispatcher.forward(request,response);
	}
	
	public void insertarusuario(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException, SQLException{
		
		String nombre=request.getParameter("nombre");
		String email=request.getParameter("email");
		String pais=request.getParameter("pais");
		
		Paciente paciente= new Paciente(nombre,email,pais);
		
		pacienteDao.insert(paciente);
		
		response.sendRedirect("list");
	}

}
