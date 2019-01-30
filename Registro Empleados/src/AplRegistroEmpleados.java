
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
	
	public AplRegistroEmpleados() {
		
	}
	
	public static void main (String[] Args) {
		new AplRegistroEmpleados();
	}
	
	private void displayMainMenu() {
		System.out.print("1.Elegir criterio de ordenamiento\n"+
						 "2.Capturar empleado\n"+
				         "3.Consultar");
	}
	
	private void displaySubMenu() {
		System.out.print("1.Nombre\n"+
				 "2.Edad\n"+
		         "3.Estatura\n"+
				 "4.Edad-Estatura-Nombre");
	}

}
