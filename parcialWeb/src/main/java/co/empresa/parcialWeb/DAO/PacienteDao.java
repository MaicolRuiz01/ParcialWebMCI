package co.empresa.parcialWeb.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.parcialWeb.modelo.Paciente;
import co.empresa.parcialWeb.util.Conexion;



public class PacienteDao {
	
private Conexion conexion;
	
private static final String INSERT_PACIENTE_SQL = "INSERT INTO paciente (documento, nombre, apellido, email, genero, fechanacimiento, telefono, direccion, peso, estatura) VALUES (?,?,?,?,?,?,?,?,?,?);" ;
private static final String DELETE_PACIENTE_SQL = "DELETE FROM paciente WHERE id=?;";
private static final String UPDATE_PACIENTE_SQL = "UPDATE paciente SET documento = ?,nombre = ?,apellido = ?, email= ?, genero = ?, fechanacimiento = ?, telefono = ?, direccion = ?, peso = ?, estatura = ? WHERE id=?;";
private static final String SELECTT_PACIENTE_BY_ID = "SELECT * FROM paciente WHERE id =?;";
private static final String SELECT_ALL_PACIENTE = "SELECT * FROM paciente;";
	
	
	public PacienteDao() {
		
		this.conexion = Conexion.getConexion();
	}
		
		public void insert(Paciente paciente) throws SQLException{
			try {
				PreparedStatement preparedStatement= (PreparedStatement)conexion.setPreparedStatement(INSERT_PACIENTE_SQL);
				preparedStatement.setString(1, paciente.getDocumento());
				preparedStatement.setString(2, paciente.getNombre());
				preparedStatement.setString(3, paciente.getApellido());
				preparedStatement.setString(4, paciente.getEmail());
				preparedStatement.setString(5, paciente.getGenero());
				preparedStatement.setString(6, paciente.getFechanacimiento());
				preparedStatement.setString(7, paciente.getTelefono());
				preparedStatement.setString(8, paciente.getDireccion());
				preparedStatement.setDouble(9, paciente.getPeso());
				preparedStatement.setDouble(10, paciente.getEstatura());
				conexion.execute();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void delete(int id) throws SQLException{
			try {
				PreparedStatement preparedStatement= (PreparedStatement)conexion.setPreparedStatement(DELETE_PACIENTE_SQL);
				preparedStatement.setInt(1, id);
			
				conexion.execute();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void update(Paciente paciente) throws SQLException {
			try {
				PreparedStatement preparedStatement= (PreparedStatement)conexion.setPreparedStatement(UPDATE_PACIENTE_SQL);
				preparedStatement.setString(1, paciente.getDocumento());
				preparedStatement.setString(2, paciente.getNombre());
				preparedStatement.setString(3, paciente.getApellido());
				preparedStatement.setString(4, paciente.getEmail());
				preparedStatement.setString(5, paciente.getGenero());
				preparedStatement.setString(6, paciente.getFechanacimiento());
				preparedStatement.setString(7, paciente.getTelefono());
				preparedStatement.setString(8, paciente.getDireccion());
				preparedStatement.setDouble(9, paciente.getPeso());
				preparedStatement.setDouble(10, paciente.getEstatura());
				preparedStatement.setInt(11, paciente.getId());
				conexion.execute();
				
			}
			
			
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public List<Paciente> selectAll(){
			
			List <Paciente> pacientes = new ArrayList<>();
			
			
			try {
				
				PreparedStatement preparedStatement= (PreparedStatement)conexion.setPreparedStatement(SELECTT_PACIENTE_BY_ID);
				ResultSet rs = conexion.query();
				
				while(rs.next()) {
					int id=rs.getInt("id");
					String documento= rs.getString("documento");
					String nombre=rs.getString("nombre");
					String apellido=rs.getString("apellido");
					String email=rs.getString("email");
					String genero=rs.getString("genero");
					String fechanacimiento=rs.getString("fechanacimineot");
					String telefono=rs.getString("telefono");
					String direccion=rs.getString("direccion");
					double peso=rs.getDouble("peso");
					double estatura=rs.getDouble("estatura");
					
					pacientes.add(new Paciente(id, documento, nombre,apellido,email,genero, fechanacimiento, telefono, direccion, peso, estatura));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return pacientes;
		}
		
		public Paciente select(int id){
			
			Paciente paciente = null;
			
			try {
				
				PreparedStatement preparedStatement= (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_PACIENTE);
				preparedStatement.setInt(1, id);
				
				ResultSet rs = conexion.query();
				
				while(rs.next()) {
					
					String documento= rs.getString("documento");
					String nombre=rs.getString("nombre");
					String apellido=rs.getString("apellido");
					String email=rs.getString("email");
					String genero=rs.getString("genero");
					String fechanacimiento=rs.getString("fechanacimineot");
					String telefono=rs.getString("telefono");
					String direccion=rs.getString("direccion");
					double peso=rs.getDouble("peso");
					double estatura=rs.getDouble("estatura");
					paciente= new Paciente(id,documento, nombre,apellido,email,genero, fechanacimiento, telefono,direccion, peso, estatura);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return paciente;
			
		}

}
