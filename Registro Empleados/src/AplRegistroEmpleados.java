
public class AplRegistroEmpleados {
	
	private class Empleado{
		public String nombre;
		public byte edad;
		public float estatura;
		
		public Empleado(String nombre, byte edad, float estatura) {
			this.nombre = nombre;
			this.edad = edad; 
			this.estatura = estatura;
		}
		
		public String toString() {
			return Rutinas.PonCeros(edad,3) + Rutinas.PonCeros((int) estatura, 3)+ Rutinas.PonBlancos(nombre, 30);
		}
	}

}
