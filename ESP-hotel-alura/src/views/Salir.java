package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ReservaController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Salir extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Este constructor es para cuando quiere salir de la app
	public Salir() {
		setTitle("Salir");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/cerrar-24px.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(135, 11, 100, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿Desea salir de la aplicación?");
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(60, 122, 259, 22);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Si");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("No");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	// Este constructor es para cerrar sesion
	public Salir(MenuUsuario mu, String user) {
		setTitle("Cerrar Sesión");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		setBounds(100, 100, 394, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(135, 5, 100, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿Desea cerrar sesión?");
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(88, 134, 197, 22);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblUser = new JLabel("");
			lblUser.setText(user);
			lblUser.setHorizontalAlignment(SwingConstants.CENTER);
			lblUser.setForeground(new Color(12, 138, 199));
			lblUser.setFont(new Font("Arial", Font.BOLD, 18));
			lblUser.setBounds(88, 108, 197, 22);
			contentPanel.add(lblUser);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Si");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mu.dispose();
						MenuPrincipal principal = new MenuPrincipal();
						principal.setVisible(true);
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("No");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	// Este constructor es para cancelar una reserva estando en la ventana de RegistroHuesped 
	public Salir(RegistroHuesped rh, Integer idReserva) {
		setResizable(false);
		setTitle("Cancelar Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/papelera-de-reciclaje.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(135, 11, 100, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿Desea cancelar la reserva? ID: "+idReserva);
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(35, 122, 315, 22);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Si");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							
							// Se debe borrar la reserva en la bdd antes de volver al menu de usuario
							ReservaController rc = new ReservaController();
							
							rc.eliminar(idReserva); // lanza la excepcion SQLException
							
							// Se cierra la ventana RegistroHuesped
							rh.dispose();
							
							// Luego se vuelve al menu
							MenuUsuario principal = new MenuUsuario();
							principal.setVisible(true);
							dispose();
							
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "Error al revertir la reserva. ID: "+idReserva+"\n"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
							rh.dispose();
							
							MenuUsuario principal = new MenuUsuario();
							principal.setVisible(true);
							dispose();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("No");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}