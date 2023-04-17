package co.empresa.parcialWeb.modelo;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Paciente implements Serializable {
	
	private int id;
	private String documento;
	private String nombre;
	private String apellido;
	private String email;
	private String genero;
	private String fechanacimiento;
	private String telefono;
	private String direccion;
	private double peso;
	private double estatura;

}
