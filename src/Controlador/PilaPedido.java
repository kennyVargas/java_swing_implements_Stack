package Controlador;

import java.util.Scanner;

public class PilaPedido {
	private int max;
	private Pedido v[];
	private int tope;
	
	public PilaPedido() {
		tope=0;
		max=50;
		v= new Pedido[max];
	}

	public boolean esvacia ()
    {
		if (tope == 0)
		    return (true);
		return (false);
	}
	public boolean esllena ()
	 {
	    if (tope == max)
		    return (true);
		return (false);
	 }
	public int nroelem ()
	 {
		return (tope);
	 }
	public void adicionar (Pedido elem)
	 {
		if (!esllena ())
		{
		    tope++;
		    v[tope] = elem;
		}
		else
		    System.out.println ("Pila llena");
	 }
	public Pedido eliminar ()
	 {
		 Pedido elem =new Pedido();
		if (!esvacia ())
		{
		    elem = v[tope];
		    tope--;
		}
		else
		    System.out.println ("Pila vacia");
		return (elem);
	 }
	public void llenar(int n) {
			Scanner leer = new Scanner(System.in);
			for (int i = 0; i < n; i++) {
				System.out.println("");
				String no = leer.next();
				int e = leer.nextInt();
				Pedido pc= new Pedido();
				adicionar(pc);
			}
	}
	public void mostrar ()
	 {
		 Pedido ele=new Pedido();
		if (esvacia ())
		    System.out.println ("Pila vacia");
		else
		{
			PilaPedido aux = new PilaPedido ();
		    System.out.println ("\n Datos de la Pila ");
		   
		    while (!esvacia ())
		    {
		    ele = eliminar ();
		    System.out.println(ele.getClt().getNombre()+" "+ele.getClt().getNit());
		    System.out.println(ele.getCodPlato()+"  "+ele.getCantidad());
			//System.out.println(ele.getNombreParque()+"  "+ele.getSuperficie()+" " +ele.getUbicacion());
			//ele.getPj().mostrar();
			aux.adicionar (ele);
		    }
		    vaciar(aux);
		}
		System.out.println("\n");
	  }
	    
	 
	public void vaciar (PilaPedido a)
	 {
		while (!a.esvacia ())
		    adicionar (a.eliminar ());
	 }
}
