package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.Format;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private HuespedController huespedesController;
	private ReservaController reservasController;
	private JLabel labelExit;
	private JLabel labelCancelar;
	int xMouse, yMouse;
	int id;

	/**
	 * Create the frame.
	 */
	public RegistroHuesped(int idReserva) {
		
		// Se aloja en una variable la instancia de la clase RegistroHuesped, se usa cuando se quiere Salir()
		RegistroHuesped rhAux = this;
		
		this.huespedesController = new HuespedController();
		this.reservasController = new ReservaController();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/persona.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 135, 300, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 204, 300, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);
		
		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 278, 300, 36);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);
		
		txtNacionalidad = new JComboBox();
		txtNacionalidad.setBounds(560, 350, 300, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"Afgano-Afgana", "Alemán-alemana", "Árabe-árabe", "Argentino-argentina", "Australiano-australiana", "Belga-belga", "Boliviano-boliviana", "Brasileño-brasileña", "Camboyano-camboyana", "Canadiense-canadiense", "Chileno-chilena", "Chino-china", "Colombiano-colombiana", "Coreano-coreana", "Costarricense-costarricense", "Cubano-cubana", "Danés-danesa", "Ecuatoriano-ecuatoriana", "Egipcio-egipcia", "Salvadoreño-salvadoreña", "Escocés-escocesa", "Español-española", "Estadounidense-estadounidense", "Estonio-estonia", "Etíope-etíope", "Filipino-filipina", "Finlandés-finlandesa", "Francés-francesa", "Galés-galesa", "Griego-griega", "Guatemalteco-guatemalteca", "Haitiano-haitiana", "Holandés-holandesa", "Hondureño-hondureña", "Indonés-indonesa", "Inglés-inglesa", "Iraquí-iraquí", "Iraní-iraní", "Irlandés-irlandesa", "Israelí-israelí", "Italiano-italiana", "Japonés-japonesa", "Jordano-jordana", "Laosiano-laosiana", "Letón-letona", "Letonés-letonesa", "Malayo-malaya", "Marroquí-marroquí", "Mexicano-mexicana", "Nicaragüense-nicaragüense", "Noruego-noruega", "Neozelandés-neozelandesa", "Panameño-panameña", "Paraguayo-paraguaya", "Peruano-peruana", "Polaco-polaca", "Portugués-portuguesa", "Puertorriqueño-puertorriqueño", "Dominicano-dominicana", "Rumano-rumana", "Ruso-rusa", "Sueco-sueca", "Suizo-suiza", "Tailandés-tailandesa", "Taiwanes-taiwanesa", "Turco-turca", "Ucraniano-ucraniana", "Uruguayo-uruguaya", "Venezolano-venezolana", "Vietnamita-vietnamita"}));
		contentPane.add(txtNacionalidad);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 119, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);
		
		JLabel lblNacimiento = new JLabel("FECHA DE NACIMIENTO");
		lblNacimiento.setBounds(560, 256, 255, 14);
		lblNacimiento.setForeground(SystemColor.textInactiveText);
		lblNacimiento.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacimiento);
		
		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);
		
		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 424, 300, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);
		
		JLabel lblNewLabel_4 = new JLabel("REGISTRO HUÉSPED");
		lblNewLabel_4.setBounds(585, 55, 237, 30);
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 23));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNreserva = new JLabel("NÚMERO DE RESERVA");
		lblNreserva.setBounds(560, 474, 253, 14);
		lblNreserva.setForeground(SystemColor.textInactiveText);
		lblNreserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNreserva);
		
		txtNreserva = new JTextField();
		
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 495, 300, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNreserva.setEditable(false);
		System.out.println(idReserva);
		String id = String.valueOf(idReserva);
		txtNreserva.setText(id);
		contentPane.add(txtNreserva);
		
			
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 300, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 300, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);
		
		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 300, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);
		
		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 300, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);
		
		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 300, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);
		
		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 300, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/disquete-blanco.png")));
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 145, 40);
		
		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(715, 560, 145, 40);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardarHuesped();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnguardar.setBackground(new Color(120, 160, 108));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnguardar.setBackground(new Color(12, 138, 199));
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnguardar.add(labelGuardar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 130, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
		
		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		contentPane.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se envia como parametro a Salir() para que si el usuario desea cancelar la Reserva se haga dispose()
				Salir salir = new Salir(rhAux,idReserva);
				salir.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnCancelar = new JPanel();
		btnCancelar.setBounds(560, 560, 145, 40);
		contentPane.add(btnCancelar);
		btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Se envia como parametro a Salir() para que si el usuario desea cancelar la Reserva se haga dispose()
				Salir salir = new Salir(rhAux, idReserva);
				salir.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(Color.red);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnCancelar.setBackground(new Color(12, 138, 199));
			}
		});
		btnCancelar.setLayout(null);
		btnCancelar.setBackground(new Color(12, 138, 199));
		
		labelCancelar = new JLabel("CANCELAR");
		labelCancelar.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/cancelar-blanco.png")));
		labelCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		labelCancelar.setForeground(Color.WHITE);
		labelCancelar.setFont(new Font("Dialog", Font.PLAIN, 18));
		labelCancelar.setBounds(0, 0, 145, 40);
		btnCancelar.add(labelCancelar);
	}
	

	private void guardarHuesped() {
		try {
			Huesped huesped;
			
			if (txtFechaN.getDate() != null && !txtNombre.equals("") && !txtApellido.equals("") && !txtTelefono.equals("")) {		
				
				String fechaN = ((JTextField)txtFechaN.getDateEditor().getUiComponent()).getText();	
				int nreserva = Integer.parseInt(txtNreserva.getText());
				huesped = new Huesped(txtNombre.getText(), txtApellido.getText(),  java.sql.Date.valueOf(fechaN), txtNacionalidad.getSelectedItem().toString(),txtTelefono.getText(), nreserva);
				
			} else {
				throw new IllegalArgumentException("Debes llenar todos los datos.");
			}
			
			this.huespedesController.guardar(huesped); // lanza la excepcion si hay error al guardar en la bdd
			Exito exito = new Exito();
			exito.setVisible(true);	
			dispose();
			
		} catch(IllegalArgumentException ie) {
			JOptionPane.showMessageDialog(this, "Alerta al registrar huesped:\n"+ie.getMessage(),"Alerta",JOptionPane.WARNING_MESSAGE);
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al registrar huesped:\n"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}							
	}
								
}