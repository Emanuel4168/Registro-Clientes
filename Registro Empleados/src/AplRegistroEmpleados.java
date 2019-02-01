import java.util.Scanner;

public class AplRegistroEmpleados {
	
	private class Empleado{
		public String nombre;
		public byte edad;
		public int estatura;
		
		public Empleado(String nombre, byte edad, int estatura) {
			this.nombre = nombre;
			this.edad = edad; 
			this.estatura = estatura;
		}
		
		public String toString() {
			if(criterioOrdenamiento == 1)
				return Rutinas.PonBlancos(nombre, 30);
			if(criterioOrdenamiento == 2)
				return Rutinas.PonCeros(edad,4);
			if(criterioOrdenamiento == 3)
				return Rutinas.PonCeros(estatura,4);
			
			return Rutinas.PonCeros(edad,4) + Rutinas.PonCeros(estatura, 4)+ Rutinas.PonBlancos(nombre, 30);
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
				int criterioAnterior = criterioOrdenamiento;
				displaySubMenu();
				criterioOrdenamiento = scan.nextByte();
				scan.nextLine();
				
				if(criterioAnterior != criterioOrdenamiento)
					ordenarListaPorCriterio(empleados,1,empleados.Length());
				break;
				
			case 2:
				empleados.InsertaOrdenado(guardarEmpleado(scan));
				break;
				
			case 3: 
				imprimirEmpleados(empleados);
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
		scan.nextLine();
		empleado.nombre = scan.nextLine();
		System.out.println("Edad:");
		empleado.edad = scan.nextByte();
		scan.nextLine();
		System.out.println("Estatura:");
		empleado.estatura = scan.nextInt();
		scan.nextLine();
		
		return empleado;
	}
	
	private void ordenarListaPorCriterio(ListaDBL<Empleado> empleados, int lowerIndex, int higherIndex) {
		int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = lowerIndex+(higherIndex-lowerIndex)/2;
        NodoDBL<Empleado> nodoPivote = empleados.getFrente(), nodoIzq= empleados.getFrente(), nodoDer=empleados.getFin();
        
        for(int it = 1 ; it < pivot; it++)
        	nodoPivote = nodoPivote.getSig();
        for(int it = 1 ; it < i; it++)
        	nodoIzq = nodoIzq.getSig();
        for(int it = empleados.Length(); it > j; it--)
        	nodoDer = nodoDer.getAnt();
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
                intercambiarNodos(nodoIzq, nodoDer);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        //System.out.println("XXXXXXXXX");
        // call quickSort method recursively
        if (lowerIndex < j)
        	ordenarListaPorCriterio(empleados, lowerIndex, j);
        if (i < higherIndex)
        	ordenarListaPorCriterio(empleados, i, higherIndex);
		
	}
	
	  private void intercambiarNodos(NodoDBL<Empleado> izq, NodoDBL<Empleado> der) {
		  System.out.println("XXXXXXXXX");
		  Empleado aux = new Empleado(izq.Info.nombre, izq.Info.edad, izq.Info.estatura);
		  izq.Info = der.Info;
		  der.Info = aux;
	  }
	  
	  private void imprimirEmpleados(ListaDBL<Empleado> empleados) {
		  NodoDBL<Empleado> empleadoActual = empleados.getFrente();
		  while(empleadoActual != null) {
			  System.out.println(empleadoActual.Info.nombre+"\t"+empleadoActual.Info.edad+"\t"+empleadoActual.Info.estatura);
			  empleadoActual = empleadoActual.getSig();
		  }
	  }

}
