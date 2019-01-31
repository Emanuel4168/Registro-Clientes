import java.util.Scanner;

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
			if(criterioOrdenamiento == 1)
				return nombre;
			if(criterioOrdenamiento == 2)
				return Rutinas.PonCeros(edad,3);
			if(criterioOrdenamiento == 3)
				return Rutinas.PonBlancos(""+estatura,3);
			
			return Rutinas.PonCeros(edad,3) + Rutinas.PonBlancos(""+estatura, 3)+ Rutinas.PonBlancos(nombre, 30);
		}
		
	}
	
	private static byte criterioOrdenamiento = 1; 
	
	public AplRegistroEmpleados() {
		Scanner scan = new Scanner(System.in);
		ListaDBL<Empleado> empleados = new ListaDBL<>();
		byte opcion = 1;
		
		while(opcion != 0) {
			displayMainMenu();
			opcion = scan.nextByte();
			
			switch(opcion) {
			case 1:
				displaySubMenu();
				criterioOrdenamiento = scan.nextByte();
				scan.nextLine();
				break;
				
			case 2:
				empleados.InsertaOrdenado(guardarEmpleado(scan));
				break;
				
			case 3: 
				break;
			}
		}
	}
	
	public static void main (String[] Args) {
		new AplRegistroEmpleados();
	}
	
	private void displayMainMenu() {
		System.out.println("1.Elegir criterio de ordenamiento\n"+
						 "2.Capturar empleado\n"+
				         "3.Consultar");
	}
	
	private void displaySubMenu() {
		System.out.println("1.Nombre\n"+
				 "2.Edad\n"+
		         "3.Estatura\n"+
				 "4.Edad-Estatura-Nombre");
	}
	
	private Empleado guardarEmpleado(Scanner scan) {
		Empleado empleado = new Empleado("",(byte) 0,0);
		System.out.println("Nombre:");
		empleado.nombre = scan.nextLine();
		System.out.println("Edad:");
		empleado.edad = scan.nextByte();
		scan.nextLine();
		System.out.println("Estatura:");
		empleado.estatura = scan.nextFloat();
		scan.nextLine();
		
		return empleado;
	}
	
	private void ordenarListaPorCriterio(ListaDBL<Empleado> empleados, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = lowerIndex+(higherIndex-lowerIndex)/2;
        NodoDBL<Empleado> nodoPivote = empleados.getFrente(), nodoIzq= empleados.getFrente(), nodoDer=empleados.getFin();
        for(int it = 0; it<pivot -1; nodoPivote = nodoPivote.getSig(), it++);
        // Divide into two arrays
        while (i <= j) {
        	
            while (nodoIzq.Info.toString().compareToIgnoreCase(nodoPivote.Info.toString()) < 0) {
                i++;
                nodoIzq = nodoIzq.getSig();
            }
            while (nodoDer.Info.toString().compareToIgnoreCase(nodoPivote.Info.toString()) > 0) {
                j--;
                nodoDer = nodoDer.getAnt();
            }
            if (i <= j) {
                exchangeNumbers(nodoIzq, nodoDer);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
        	ordenarListaPorCriterio(empleados, lowerIndex, j);
        if (i < higherIndex)
        	ordenarListaPorCriterio(empleados, i, higherIndex);
		
	}
	
	  private void exchangeNumbers(NodoDBL<Empleado> izq, NodoDBL<Empleado> der) {
		  NodoDBL<Empleado> aux = izq;
		  izq = der;
		  der = aux;
	  }

}
