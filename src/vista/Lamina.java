package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.Cliente;
import Controlador.Pedido;
import Controlador.PilaPedido;
import Controlador.PilaPlato;
import Controlador.Plato;

public class Lamina extends JPanel{

	private JLabel titulo = new JLabel("<html><h1 style=\"background: #2a2266;color:white;padding: 20px\">SISTEMA DE VENTAS DE COMIDA</h1></html>", JLabel.CENTER);
	private JTextField t1,t2,t3,t4,total;
	private JLabel nombre,nit,codplato,cantidad,monto;
	private JButton ordenar, finalizar;
	private String[] table= {"CODIGO","NOMBRE","PRECIO Bs"};
	private PilaPedido pp = new PilaPedido();
	private PilaPlato ccp = new PilaPlato();
	//tabla factura
	private DefaultTableModel model = new DefaultTableModel();
	private JTable tabladato,factura;
	private JScrollPane tableSP,talbemenu; 
	
	private int count=0;
	public Lamina() {
		init();
	}
	public void init() {
		
		setLayout(new BorderLayout());
		titulo.setAlignmentY(CENTER_ALIGNMENT);
		titulo.setBorder(new LineBorder(Color.white));
		titulo.setBackground(Color.decode("#2a2266"));
		titulo.setOpaque(true);
		titulo.setPreferredSize(new Dimension(200, 60));
		Object v[][]=llenarTablaPlatos();
		tabladato= new JTable(v,table);
		talbemenu = new JScrollPane(tabladato);
		talbemenu.setPreferredSize(new Dimension(400, 120));
		//++++++++
		model.addColumn("NOMBRE PLATO");
		model.addColumn("CANTIDAD");
		model.addColumn("PRECIO");
		factura= new JTable(model);
		tableSP= new JScrollPane(factura);
		tableSP.setPreferredSize(new Dimension(70, 100));
		
		t1=new JTextField(10);
		t2=new JTextField(10);
		t3=new JTextField(10);
		t4=new JTextField(10);
		total=new JTextField(10);
		t1.setMaximumSize(t1.getPreferredSize());
		t2.setMaximumSize(t2.getPreferredSize());
		t3.setMaximumSize(t3.getPreferredSize());
		t4.setMaximumSize(t4.getPreferredSize());
		total.setMaximumSize(t4.getPreferredSize());
		this.nombre= new JLabel("NOMBRE");
		this.nit= new JLabel("NIT");
		this.codplato= new JLabel("CODPLATO");
		this.cantidad= new JLabel("CANTIDAD");
		this.monto=new JLabel("MONTO A PAGAR");
		
		getCliente();
		verFactura();
		CreateBox();
	}
	public void getCliente() {
		ordenar= new JButton("REALIZAR PEDIDO");
		ordenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().isEmpty()||t2.getText().isEmpty()||t3.getText().isEmpty()||t4.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "RELLENE DATOS DEL CLIENTE");
					return;}
				if(!t3.getText().matches("[+-]?\\d*(\\.\\d+)?")|| !t4.getText().matches("[+-]?\\d*(\\.\\d+)?")) { 
					JOptionPane.showMessageDialog(null, "el cod y cantidad no son numero");
					t3.setText("");
					t4.setText("");
					return;}
				
				if(t1.isEditable()) model.setRowCount(0);
				
				String nom = t1.getText().trim();
				int nitc =Integer.parseInt(t2.getText().trim());
				int codp = Integer.parseInt(t3.getText().trim());
				int cant = Integer.parseInt(t4.getText().trim());
				monto.setText("MONTO A PAGAR POR : "+nom);
				t3.setText("");
				t4.setText("");
				t1.setEditable(false);
				t2.setEditable(false);
				total.setText("");
				//add la interfe
				model.setRowCount(count);
				String nomPlato =getNombrePlato(codp);
				int precio =  getPrecioPlato(codp);
				Object[] fila= {nomPlato,cant,precio};
				model.addRow(fila);
				count++;
				//pila pedido
				Pedido pd = new Pedido();
				pd.setCodPlato(codp);
				pd.setCantidad(cant);
				Cliente clt = new Cliente();
				clt.setNombre(nom);
				clt.setNit(nitc);
				pd.setClt(clt);
				pp.adicionar(pd);
			}
		});
	}
	public void verFactura() {
		finalizar = new JButton("FINALIZAR COMPRA");
		finalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "FINALIZAR SU PEDIDO");
				if(input == JOptionPane.YES_OPTION){
					t3.setText("");
					t4.setText("");
					t1.setEditable(true);
					t2.setEditable(true);
					t1.setText("");
					t2.setText("");
					total.setText(getFactura()+" Bs");
					//model.setRowCount(0);
					pp = new PilaPedido();
					count=0;
					monto.setText("MONTO A PAGAR");
		        }
				/*else if (input == JOptionPane.NO_OPTION){
		            System.out.println("You selected: No");
		        }else{
		            System.out.println("none cancel");
		            }
		        */
			}
		});
		
	}
	public void  CreateBox() {
		Box h1 = Box.createHorizontalBox();
		h1.add(titulo);
		
		Box cajaIzq = Box.createVerticalBox();
		
		Box cajaDer = Box.createVerticalBox();
		
		Box caja1 = Box.createHorizontalBox();
		caja1.add(nombre);
		caja1.add(Box.createHorizontalStrut(10));
		caja1.add(t1);
		
		Box caja2 = Box.createHorizontalBox();
		caja2.add(nit);
		caja2.add(Box.createHorizontalStrut(10));
		caja2.add(t2);
		Box caja3 = Box.createHorizontalBox();
		caja3.add(codplato);
		caja3.add(Box.createHorizontalStrut(10));
		caja3.add(t3);
		Box caja4 = Box.createHorizontalBox();
		caja4.add(cantidad);
		caja4.add(Box.createHorizontalStrut(10));
		caja4.add(t4);
		
		Box caja5 = Box.createHorizontalBox();
		caja5.add(ordenar);
		caja5.add(Box.createHorizontalStrut(10));
		caja5.add(finalizar);
		//+++++++++++++++++++++++
		//IZQ
		cajaIzq.add(new JLabel("DATOS CLIENTE"));
		cajaIzq.add(caja1);
		cajaIzq.add(caja2);
		cajaIzq.add(new JLabel("REALIZAR PEDIDO"));
		cajaIzq.add(caja3);
		cajaIzq.add(caja4);
		cajaIzq.add(caja5);
		//DER
		Box cajader2 = Box.createHorizontalBox();
		cajader2.add(this.monto);
		cajader2.add(Box.createHorizontalStrut(10));
		cajader2.add(this.total);
		cajaDer.add(new JLabel("LISTA DE PLATOS:"));
		
		//cajaDer.add(new JScrollPane(tabladato));
		cajaDer.add(talbemenu);
		cajaDer.add(new JLabel("FACTURA:"));
		cajaDer.add(tableSP);
		cajaDer.add(cajader2);
		
		add(h1,BorderLayout.NORTH);
		
		add(cajaIzq,BorderLayout.WEST);
		add(cajaDer,BorderLayout.EAST);
	}
	
	public Object[][] llenarTablaPlatos(){
		
		PilaPlato aux = new PilaPlato();
		ccp.llenar();
		int x=ccp.nroelem();
		Object [][]v = new Object[x][3];
		int j =0;
		for (int i = 0; i < v.length; i++) {
			Plato elem = ccp.eliminar();
			v[i][j]=elem.getCod();
			v[i][j+1]=elem.getNomp();
			v[i][j+2]=elem.getPrecio();
			aux.adicionar(elem);
		}
		ccp.vaciar(aux);
		return v;
	}
	public int getFactura() {
		PilaPlato aux = new PilaPlato();
		PilaPedido auxp = new PilaPedido();
		int montotal=0;
		while(!pp.esvacia()) {
			Pedido elem = pp.eliminar();
			auxp.adicionar(elem);
			while(!ccp.esvacia()) {
				Plato plt = ccp.eliminar();
				if(elem.getCodPlato()==plt.getCod()) {
					montotal+=elem.getCantidad()*plt.getPrecio();
				}
				aux.adicionar(plt);
			}
			ccp.vaciar(aux);
		}
		pp.vaciar(auxp);
		
		//nomre cantida  precio
		return montotal;
	}
	public String getNombrePlato(int cod){
		String res="";
		PilaPlato aux = new PilaPlato();
		while(!ccp.esvacia()){
			Plato elem = ccp.eliminar();
			if(elem.getCod()==cod)
				res=elem.getNomp();
			aux.adicionar(elem);
		}
		ccp.vaciar(aux);
		return res;
	}
	public int getPrecioPlato(int cod){
		int res=0;
		PilaPlato aux = new PilaPlato();
		while(!ccp.esvacia()){
			Plato elem = ccp.eliminar();
			if(elem.getCod()==cod)
				res=elem.getPrecio();
			aux.adicionar(elem);
		}
		ccp.vaciar(aux);
		return res;
	}
	

	
}
