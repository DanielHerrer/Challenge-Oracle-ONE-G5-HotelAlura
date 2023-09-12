package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private ReservaController reservaController;
	private HuespedController huespedController;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	String reserva;
	String huespedes;

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa-1.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tbReservas);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(543, 127, 186, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BÚSQUEDA");
		lblTitulo.setBounds(300, 62, 290, 32);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(20, 169, 865, 328);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPane.add(panel);
		
		boolean[] columnEditableH = new boolean[] { false, true, true, true, true, true, false }; // Define qué columnas son editables
		String[] columnNamesH = { "Numero de Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Telefono", "Numero de Reserva" };
		modeloHuesped = new ModeloTabla(columnNamesH, 0, columnEditableH);

		tbHuespedes = new JTable(modeloHuesped);
		
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane barraDescHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), barraDescHuespedes, null);

		LlenarTablaHuespedes();

		boolean[] columnEditableR = new boolean[] { false, true, true, true, true, true }; // Define qué columnas son editables
		String[] columnNamesR = { "Numero de Reserva", "Fecha Check In", "Fecha Check Out", "Tipo de Habitacion", "Valor", "Forma de Pago" };
		modeloReserva = new ModeloTabla(columnNamesR, 0, columnEditableR);

		tbReservas = new JTable(modeloReserva);
		
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane barraDescReservas = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), barraDescReservas, null);

		LlenarTablaReservas();
		
		JLabel logo = new JLabel("");
		logo.setBounds(56, 51, 104, 107);
		logo.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		contentPane.add(logo);
		
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
		contentPane.add(header);
		
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
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
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
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(539, 159, 193, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnbuscar.setBounds(745, 120, 131, 40);
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String buscador = txtBuscar.getText().trim();
				limpiarTabla();
				
				// Si el buscador esta VACIO
				if (buscador.equals("")) {					
					LlenarTablaHuespedes();
					LlenarTablaReservas();
					
				// Si el buscador NO esta vacio
				} else {
					
					// Si el bloque try fluye con normalidad entonces es un numero
					try {
						int numeroAux = Integer.parseInt(buscador);
						LlenarTablaReservasId(buscador);
						LlenarTablaHuespedesId(buscador);
						
					// Si se acciona el bloque catch entonces es una cadena
					} catch (NumberFormatException ne) {
						LlenarTablaReservasApellido(buscador);
						LlenarTablaHuespedesApellido(buscador);			
						
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnbuscar.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnbuscar.setBackground(new Color(12, 138, 199));
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2-blanco.png")));
		lblBuscar.setBounds(0, 0, 131, 40);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();

				if (filaReservas >= 0) {
					ActualizarReservas();
					limpiarTabla();
					LlenarTablaReservas();
					LlenarTablaHuespedes();
				}
				else if (filaHuespedes >= 0) {
					ActualizarHuesped();
					limpiarTabla();
					LlenarTablaHuespedes();
					LlenarTablaReservas();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(new Color(12, 138, 199));
			}
		});
		btnEditar.setBounds(599, 505, 131, 40);
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto-blanco.png")));
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 131, 40);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setBounds(739, 505, 150, 40);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();

				if (filaReservas >= 0) {

					// reserva = tbReservas.getValueAt(filaReservas, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar los datos?","Confirmar",JOptionPane.WARNING_MESSAGE); 

					if(confirmar == JOptionPane.YES_OPTION){
						try {
							String valorIdReserva = tbReservas.getValueAt(filaReservas, 0).toString();
							
							// Primero se elimina el huesped debido a que contiene un foreign key de reserva
							huespedController.eliminarPorReserva(Integer.valueOf(valorIdReserva));
							// Luego se elimina la reserva
							reservaController.eliminar(Integer.valueOf(valorIdReserva));
							
							JOptionPane.showMessageDialog(contentPane, "Registro Eliminado","Exito",JOptionPane.INFORMATION_MESSAGE);
							limpiarTabla();
							LlenarTablaReservas();
							LlenarTablaHuespedes();
							
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "Error al eliminar el registro:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}			
				} else if (filaHuespedes >= 0) {

					// huespedes = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
					int confirmarh = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar los datos?","Confirmar",JOptionPane.WARNING_MESSAGE); 

					if(confirmarh == JOptionPane.YES_OPTION){
						try {
							String valorIdHuesped = tbHuespedes.getValueAt(filaHuespedes, 0).toString();			
							String valorIdReserva = tbHuespedes.getValueAt(filaHuespedes, 6).toString();			
							
							// Primero se elimina el huesped debido a que contiene un foreign key de reserva
							huespedController.eliminar(Integer.valueOf(valorIdHuesped));
							// Luego se elimina la reserva
							reservaController.eliminar(Integer.valueOf(valorIdReserva));
							
							JOptionPane.showMessageDialog(contentPane, "Registro Eliminado","Exito",JOptionPane.INFORMATION_MESSAGE);
							limpiarTabla();
							LlenarTablaHuespedes();
							LlenarTablaReservas();
							
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "Error al eliminar el registro:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error fila no seleccionada, por favor realice una busqueda y seleccione una fila para eliminar", "Alerta", JOptionPane.WARNING_MESSAGE);
				}							
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(new Color(12, 138, 199));
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar-blanco.png")));
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 150, 40);
		btnEliminar.add(lblEliminar);
		
		JLabel lblFechaDeIngreso = new JLabel("ID / APELLIDO");
		lblFechaDeIngreso.setForeground(SystemColor.textInactiveText);
		lblFechaDeIngreso.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblFechaDeIngreso.setBounds(580, 169, 105, 21);
		contentPane.add(lblFechaDeIngreso);
		setResizable(false);
	}
	
	private void limpiarTabla() {
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}
	
	private void LlenarTablaReservas() {

	    // Llenar tabla
		List<Reserva> reservas = reservaController.buscar();
		try {
			
			for (Reserva reserva : reservas) {
				modeloReserva.addRow(new Object[] { reserva.getId(), reserva.getfechaE(), reserva.getfechaS(), reserva.getTipoHabitacion(), reserva.getvalor(), reserva.getformaPago() });
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
	private void LlenarTablaReservasId(String id) {

	    // Llenar tabla
		List<Reserva> reservas = reservaController.buscarId(id);
		try {
			
			for (Reserva reserva : reservas) {
				modeloReserva.addRow(new Object[] { reserva.getId(), reserva.getfechaE(), reserva.getfechaS(), reserva.getTipoHabitacion(), reserva.getvalor(), reserva.getformaPago() });
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void LlenarTablaReservasApellido(String apellido) {

	    // Llenar tabla
		List<Reserva> reservas = reservaController.buscarApellido(apellido);
		try {
			
			for (Reserva reserva : reservas) {
				modeloReserva.addRow(new Object[] { reserva.getId(), reserva.getfechaE(), reserva.getfechaS(), reserva.getTipoHabitacion(), reserva.getvalor(), reserva.getformaPago() });
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void LlenarTablaHuespedes() {			       
	    //Llenar Tabla
		List<Huesped> huespedes = huespedController.listarHuespedes();
		try {
			
			for (Huesped huesped : huespedes) {
				modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getIdReserva() });
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void LlenarTablaHuespedesId(String id) {			       
	    //Llenar Tabla
		List<Huesped> huespedes = huespedController.listarHuespedesId(id);
		try {
			
			for (Huesped huesped : huespedes) {
				modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getIdReserva() });
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void LlenarTablaHuespedesApellido(String apellido) {			       
	    //Llenar Tabla
		List<Huesped> huespedes = huespedController.listarHuespedesApellido(apellido);
		try {
			
			for (Huesped huesped : huespedes) {
				modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getIdReserva() });
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void ActualizarReservas() {		
		Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
        	
        		Date fechaE = Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1).toString());		
            	Date fechaS = Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 2).toString());
            	String habitacion = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 3);
    			String valor = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4);
    			String formaPago = (String) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 5);
    			Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	    		try {
	    			this.reservaController.actualizar(fechaE, fechaS, habitacion, valor, formaPago, id); // lanza excepcion
	    			JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"), "Exito", JOptionPane.INFORMATION_MESSAGE);
	        	} catch (SQLException e) {
	        		JOptionPane.showMessageDialog(this, String.format("Error al modificar el registro:\n"+e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
	        	}
        	
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elige un registro"));
		
	}
	
	private void ActualizarHuesped() {		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
        .ifPresentOrElse(filaHuesped -> {
        	
        	String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
        	String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
        	Date fechaN = Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
			String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
			String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
			Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			try {
				this.huespedController.actualizar(nombre,apellido,fechaN, nacionalidad, telefono, idReserva, id); // lanza excepcion
				JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"), "Exito", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, String.format("Error al modificar el registro:\n"+e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
			}
				
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));
		
	}
	
	
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
    
    // CREADO ESPECIFICAMENTE para evitar que el usuario edite las ID
    public class ModeloTabla extends DefaultTableModel {
        // Lista de columnas que no se pueden editar
        private final boolean[] editableColumns;

        public ModeloTabla(Object[] columnNames, int rowCount, boolean[] editableColumns) {
            super(columnNames, rowCount);
            this.editableColumns = editableColumns;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // Devuelve true si la columna es editable, false si no lo es
            return editableColumns[column];
        }
    }
}