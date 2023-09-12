package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import controller.ReservaController;
import modelo.Reserva;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaE;
	public static JDateChooser txtFechaS;
	public static JComboBox<String> txtFormaPago;
	public static JComboBox<String> txtTipoHabitacion;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	
	private ReservaController reservasController;
	
	/**
	 * Create the frame.
	 */
	public ReservasView() {
		//Instanciando la clase ReservasController
		this.reservasController = new ReservaController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/calendario.png")));//Adiciona el ícono a nuestro programa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false); //Evita que la ventana sea redimensionada
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);//Hace que las ventanas aparezcan siempre en medio de la pantalla
		setUndecorated(true);//retira la barra superior de la ventana
		

		
		// Código que crea los elementos de la interfáz gráfica
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 157, 289, 2);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JSeparator separator_1_5 = new JSeparator();
		separator_1_5.setForeground(SystemColor.textHighlight);
		separator_1_5.setBackground(SystemColor.textHighlight);
		separator_1_5.setBounds(68, 471, 289, 2);
		panel.add(separator_1_5);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 230, 289, 11);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 400, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(75, 47, 271, 30);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 23));
		panel.add(lblTitulo);

		JPanel fondoImagen = new JPanel();
		fondoImagen.setBounds(428, 0, 482, 560);
		fondoImagen.setBackground(new Color(153, 180, 209));
		panel.add(fondoImagen);
		fondoImagen.setLayout(null);

		JLabel Logo = new JLabel("");
		Logo.setBounds(197, 68, 104, 107);
		fondoImagen.add(Logo);
		Logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));

		JLabel ImagenFondo = new JLabel("");
		ImagenFondo.setBounds(0, 140, 500, 409);
		fondoImagen.add(ImagenFondo);
		ImagenFondo.setBackground(Color.WHITE);
		ImagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(153, 180, 209));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(153, 180, 209));
		btnexit.setBounds(429, 0, 53, 36);
		fondoImagen.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setForeground(SystemColor.textHighlight);
		separator_1_4.setBounds(68, 387, 289, 2);
		separator_1_4.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_4);
		
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/disquete-blanco.png")));
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 155, 40);
	
		
		//Campos que guardaremos en la base de datos
		 
		txtFechaE = new JDateChooser();
		txtFechaE.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularValor(txtFechaE, txtFechaS, txtTipoHabitacion);
			}
		});
		txtFechaE.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaE.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaE.setBounds(68, 123, 289, 35);
		txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaE.setBackground(Color.WHITE);
		txtFechaE.setBorder(new LineBorder(SystemColor.window));
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaE);
						
		txtFechaS = new JDateChooser();
		txtFechaS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularValor(txtFechaE, txtFechaS, txtTipoHabitacion);
			}
		});
		txtFechaS.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaS.setBounds(68, 195, 289, 35);
		txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaS.setBackground(Color.WHITE);
		txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaS);
					
		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(80, 357, 140, 23);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);		
				
		txtFormaPago = new JComboBox<String>();
		txtFormaPago.setBounds(68, 435, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);
		
		//Botón Siguiente		
		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((txtFechaE.getDate() != null && txtFechaS.getDate() != null)) {
					guardarReserva();
				}else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos","Alerta",JOptionPane.WARNING_MESSAGE);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnsiguiente.setBackground(new Color(120, 160, 108));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnsiguiente.setBackground(new Color(12, 138, 199));
			}
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(202, 498, 155, 40);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnsiguiente.add(lblSiguiente);		
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 307, 289, 2);
		panel.add(separator_1_3);
		
		txtTipoHabitacion = new JComboBox<String>();
		txtTipoHabitacion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				calcularValor(txtFechaE, txtFechaS, txtTipoHabitacion);
			}
		});
		txtTipoHabitacion.setModel(new DefaultComboBoxModel<String>(new String[] {"Individual", "Matrimonial", "Familiar", "Vista al Mar"}));
		txtTipoHabitacion.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTipoHabitacion.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtTipoHabitacion.setBackground(SystemColor.text);
		txtTipoHabitacion.setBounds(68, 271, 289, 38);
		panel.add(txtTipoHabitacion);
		
		JLabel lblFechaDeIngreso = new JLabel("FECHA DE INGRESO");
		lblFechaDeIngreso.setForeground(SystemColor.textInactiveText);
		lblFechaDeIngreso.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblFechaDeIngreso.setBounds(78, 98, 196, 14);
		panel.add(lblFechaDeIngreso);
		
		JLabel lblValor_1_1 = new JLabel("FECHA DE SALIDA");
		lblValor_1_1.setForeground(SystemColor.textInactiveText);
		lblValor_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblValor_1_1.setBounds(78, 170, 196, 14);
		panel.add(lblValor_1_1);
		
		JLabel lblValor_1_1_1 = new JLabel("TIPO DE HABITACIÓN");
		lblValor_1_1_1.setForeground(SystemColor.textInactiveText);
		lblValor_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblValor_1_1_1.setBounds(78, 246, 196, 14);
		panel.add(lblValor_1_1_1);
		
		JLabel lblFormaPago_1 = new JLabel("VALOR DE LA RESERVA");
		lblFormaPago_1.setForeground(SystemColor.textInactiveText);
		lblFormaPago_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblFormaPago_1.setBounds(68, 329, 204, 24);
		panel.add(lblFormaPago_1);
	}
	
	private void guardarReserva() {	
		
		String fechaE = ((JTextField)txtFechaE.getDateEditor().getUiComponent()).getText();
		String fechaS = ((JTextField)txtFechaS.getDateEditor().getUiComponent()).getText();	
		Reserva nuevaReserva = new Reserva(java.sql.Date.valueOf(fechaE), java.sql.Date.valueOf(fechaS), txtTipoHabitacion.getSelectedItem().toString(), txtValor.getText(), txtFormaPago.getSelectedItem().toString());
		try {
			reservasController.guardar(nuevaReserva);
			
			JOptionPane.showMessageDialog(this,"Registro Guardado con éxito | ID: " + nuevaReserva.getId(),"Exito", JOptionPane.INFORMATION_MESSAGE);
			RegistroHuesped huesped = new RegistroHuesped(nuevaReserva.getId());
			huesped.setVisible(true);
			dispose();	
		} catch (IllegalArgumentException ie) {
			JOptionPane.showMessageDialog(this, "Alerta al registrar reserva:\n"+ie.getMessage(),"Alerta", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al registrar reserva:\n"+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
			
		
	}
		
		
	private void calcularValor(JDateChooser fechaE, JDateChooser fechaS, JComboBox<String> habitacion) {		
		if(fechaE.getDate() != null && fechaS.getDate() !=null) {
			Calendar inicio = fechaE.getCalendar();
			Calendar fin = fechaS.getCalendar();
			int dias = -1; // Usamos -1 para contar a partir del dia siguiente
			int diaria = 0;
			int valor;
			
			switch(habitacion.getSelectedIndex()) {
				case 0:
					diaria = 500;
					break;
				case 1:
					diaria = 750;
					break;
				case 2:
					diaria = 1000;
					break;
				case 3:
					diaria = 1500;
					break;
				/*
				case 4:
					diaria = 2000;
					break;
				*/
			}
			
			
			
			while(inicio.before(fin)||inicio.equals(fin)) {
				dias++;
				inicio.add(Calendar.DATE,1); //dias a ser aumentados
			}
			valor = dias * diaria;
			txtValor.setText("$" + valor);
		}
	}
	
	

	//Funciones que permiten mover la ventana por la pantalla
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
