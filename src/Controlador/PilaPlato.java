package Controlador;

import java.util.Scanner;

public class PilaPlato {
	private int max;
	private Plato v[];
	private int tope;
	
	public PilaPlato() {
		tope=0;
		max=50;
		v= new Plato[max];
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
	public  int nroelem ()
	 {
		return (tope);
	 }
	 public void adicionar (Plato elem)
	 {
		if (!esllena ())
		{
		    tope++;
		    v[tope] = elem;
		}
		else
		    System.out.println ("Pila llena");
	 }
	 public Plato eliminar ()
	 {
		 Plato elem =new Plato();
		if (!esvacia ())
		{
		    elem = v[tope];
		    tope--;
		}
		else
		    System.out.println ("Pila vacia");
		return (elem);
	 }
	 public void llenar() {
			Plato plt1 = new Plato();
			Plato plt2 = new Plato();
			Plato plt3 = new Plato();
			Plato plt4 = new Plato();
			Plato plt5 = new Plato();
			
			plt1.setNomp("POLLO");
			plt1.setCod(1);
			plt1.setPrecio(10);
			
			plt2.setNomp("PAPASFRITAS");
			plt2.setCod(2);
			plt2.setPrecio(5);
			
			plt3.setNomp("SALCHIPAPA");
			plt3.setCod(3);
			plt3.setPrecio(10);
			
			plt4.setNomp("HANBURGUESA");
			plt4.setCod(4);
			plt4.setPrecio(15);

			plt5.setNomp("SALCHIARROZ");
			plt5.setCod(5);
			plt5.setPrecio(15);

			adicionar(plt5);
			adicionar(plt4);
			adicionar(plt3);
			adicionar(plt2);
			adicionar(plt1);
	}
	 public void mostrar ()
	 {
		 Plato ele=new Plato();
		if (esvacia ())
		    System.out.println ("Pila vacia");
		else
		{
			PilaPlato aux = new PilaPlato ();
		    System.out.println ("\n Datos de la Pila ");
		   
		    while (!esvacia ())
		    {
		    ele = eliminar ();
			System.out.println(ele.getCod()+" "+ele.getNomp()+" "+ele.getPrecio());
			aux.adicionar (ele);
		    }
		    vaciar(aux);
		}
		System.out.println("\n");
	  }
	    
	 
	 public void vaciar (PilaPlato a)
	 {
		while (!a.esvacia ())
		    adicionar (a.eliminar ());
	 }
}
