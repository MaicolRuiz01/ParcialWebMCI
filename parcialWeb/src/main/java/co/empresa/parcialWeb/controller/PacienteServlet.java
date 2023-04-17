package co.empresa.parcialWeb.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.parcialWeb.DAO.PacienteDao;
import co.empresa.parcialWeb.modelo.Paciente;




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
				eliminarpacientes(request, response);
				 
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
	
	public void insertarpaciente(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException, SQLException{
		
		String documento= request.getParameter("documento");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String email=request.getParameter("email");
		String genero=request.getParameter("genero");
		String fechanacimiento=request.getParameter("fechanacimineto");
		String telefono=request.getParameter("telefono");
		String direccion=request.getParameter("direccion");
		String peso=request.getParameter("peso");
		String estatura=request.getParameter("estatura");
		
		Paciente paciente= new Paciente(documento, nombre, apellido, email, genero, fechanacimiento, telefono, direccion, peso, estatura);
		
		pacienteDao.insert(paciente);
		
		response.sendRedirect("list");
	}
	
	public void showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		int id= Integer.parseInt(request.getParameter("id"));
	      Paciente pacienteactual = pacienteDao.select(id);
	      request.setAttribute("paciente", pacienteactual);
	      
		RequestDispatcher dispatcher = request.getRequestDispatcher("paciente.jsp");
		dispatcher.forward(request,response);
		
	}
	
	public void eliminarpacientes(HttpServletRequest request,HttpServletResponse response) 
			throws IOException, SQLException, SQLException{
		int id= Integer.parseInt(request.getParameter("id"));
		pacienteDao.delete(id);
		response.sendRedirect("list");
	}
	
	private void listPacientes(HttpServletRequest request,HttpServletResponse response) 
			throws IOException,SQLException, ServletException{
		List <Paciente> listPacientes = pacienteDao.selectAll();
		request.setAttribute("listPacientes", listPacientes);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("pacientelist.jsp");
		dispatcher.forward(request,response);

	}
	
	public void actualizarpaciente(HttpServletRequest request,HttpServletResponse response) 
			
	}

}
