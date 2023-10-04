package com.vistas;

import com.controladores.controladorClientes;
import com.controladores.ControladorPedidos;
import com.modelos.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VistaRegistrarPedido extends javax.swing.JFrame {

	ControladorPedidos controladorPedidos;
	controladorClientes controladorClientes;
	VistaPrincipal vistaPrincipal;

	public VistaRegistrarPedido(VistaPrincipal vistaPrincipal) throws SQLException {
		controladorPedidos = new ControladorPedidos();
		controladorClientes = new controladorClientes();
		this.vistaPrincipal = vistaPrincipal;

		this.setLocationRelativeTo(null);

		initComponents();
		setCmbxCategoria();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar unicamente la ventana actual
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        cmbxClientes = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrar Pedido");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 360, 40));

        jLabel6.setText("Total:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel7.setText("Cliente:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        bg.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 240, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        bg.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 320, 30));

        bg.add(cmbxClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
		String cedulaCliente = cmbxClientes.getSelectedItem().toString().split(" - ")[0];
		try {
			controladorPedidos.insertarPedido(txtTotal.getText(),
					cedulaCliente.strip());

			this.dispose();
			vistaPrincipal.llenarTablaPedidos("");
			vistaPrincipal.actualizarVistaFiltro();
			JOptionPane.showMessageDialog(null, "Pedido registrado");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnRegistrarActionPerformed

	private void setCmbxCategoria() throws SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		cmbxClientes.setModel(model);

		ArrayList<Cliente> clientes = controladorClientes.obtenerClientes();
		model.addElement("Seleccione un cliente"); // Agrega la opción predeterminada

		for (Cliente clienteAlmacenado : clientes) {
			String cedula = clienteAlmacenado.getCedula();
			String nombre = clienteAlmacenado.getNombre();
			model.addElement(cedula + " - " + nombre); // Agrega los nombres de las categorías al ComboBoxModel
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbxClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
