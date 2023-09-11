package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Exito extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Exito() {
		setTitle("Registro exitoso");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/aH-40px.png")));
		setBounds(100, 100, 394, 232);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(138, 10, 100, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Datos guardados satisfactoriamente");
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(30, 130, 314, 22);
			contentPanel.add(lblNewLabel_1);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Reserva realizada!");
		lblNewLabel_1.setForeground(new Color(12, 138, 199));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(110, 110, 161, 22);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 156, 378, 37);
			contentPanel.add(buttonPane);
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setVgap(7);
			buttonPane.setLayout(fl_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						MenuUsuario usuario = new MenuUsuario(); 
						usuario.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
