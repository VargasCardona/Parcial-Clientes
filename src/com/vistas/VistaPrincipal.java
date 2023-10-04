package com.vistas;

import com.controladores.controladorClientes;
import com.controladores.ControladorPedidos;
import com.modelos.Cliente;
import com.modelos.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaPrincipal extends javax.swing.JFrame {

	ControladorPedidos controladorPedidos;
	controladorClientes controladorClientes;
	VistaFiltrarPedidos vistaFiltrarPedidos;

	public VistaPrincipal() throws SQLException {
		controladorPedidos = new ControladorPedidos();
		controladorClientes = new controladorClientes();

		this.setLocationRelativeTo(null);
		setTitle("Buscar Productos");

		initComponents();

		llenarTablaClientes();
		tblPedidos.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
		tblPedidos.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
		tblPedidos.setCellSelectionEnabled(false); // Evitar selecciones en la tabla

		llenarTablaPedidos("");
		tblClientes.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
		tblClientes.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
		tblClientes.setCellSelectionEnabled(false); // Evitar selecciones en la tabla

		setCmbxCliente();
	}

	public void setCmbxCliente() throws SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		cmbxCliente.setModel(model);

		ArrayList<Cliente> clientes = controladorClientes.listarClientes();
		model.addElement("Seleccione un cliente"); // Agrega la opción predeterminada

		for (Cliente clienteAlmacenado : clientes) {
			String cedula = clienteAlmacenado.getCedula();
			String nombre = clienteAlmacenado.getNombre();
			model.addElement(cedula + " - " + nombre); // Agrega los nombres de las categorías al ComboBoxModel
		}
	}

	public void llenarTablaPedidos(String id) {
		DefaultTableModel modelo = new DefaultTableModel();
		ArrayList<Pedido> listaPedidos = controladorPedidos.listarPedidos();
		modelo.setColumnIdentifiers(new Object[]{"ID", "Fecha de Pedido", "Total", "Nombre Cliente"});
		tblPedidos.setModel(modelo);

		if (id.equals("")) {
			for (Pedido pedidoAlmacenado : listaPedidos) {
				modelo.addRow(new Object[]{
					pedidoAlmacenado.getId(),
					pedidoAlmacenado.getFechaPedido(),
					pedidoAlmacenado.getTotal(),
					pedidoAlmacenado.getCedulaCliente()
				});
			}
		} else {
			for (Pedido pedidoAlmacenado : listaPedidos) {
				if (pedidoAlmacenado.getId().contains(id)) {
					modelo.addRow(new Object[]{
						pedidoAlmacenado.getId(),
						pedidoAlmacenado.getFechaPedido(),
						pedidoAlmacenado.getTotal(),
						pedidoAlmacenado.getCedulaCliente()
					});
				}
			}
		}

		if (modelo.getRowCount() == 0) {
			lblResultados.setText("No se han encontrado coincidencias");
		} else {
			lblResultados.setText(" ");
		}
	}

	public void llenarTablaClientes() {
		DefaultTableModel modelo = new DefaultTableModel();
		ArrayList<Cliente> listaClientes = controladorClientes.listarClientes();
		modelo.setColumnIdentifiers(new Object[]{"Cedula", "Nombre", "Email"});
		tblClientes.setModel(modelo);

		for (Cliente clienteAlmacenado : listaClientes) {
			modelo.addRow(new Object[]{
				clienteAlmacenado.getCedula(),
				clienteAlmacenado.getNombre(),
				clienteAlmacenado.getEmail(),});
		}
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        lblResultados = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        btnActualizarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnRegistrarCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        cmbxCliente = new javax.swing.JComboBox<>();
        btnVerPorCategoria = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clientes");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 280, 80));

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 370, 250));

        lblResultados.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblResultados.setForeground(new java.awt.Color(255, 0, 0));
        lblResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultados.setText("{Resultados}");
        bg.add(lblResultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 370, 30));

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });
        bg.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 340, 20));

        jLabel3.setText("ID:");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 30, 20));
        bg.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 200, 20));

        jLabel7.setText("Cliente:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, -1, -1));
        bg.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 200, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        bg.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 100, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        bg.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 100, -1));

        jLabel8.setText("Total:");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        btnRegistrar.setText("Registrar nuevo pedido");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        bg.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 240, -1));

        jLabel9.setText("ID:");
        bg.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 370, 220));

        jLabel4.setText("Cedula:");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 50, 20));

        jLabel10.setText("Nombre:");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, -1, -1));

        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });
        bg.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 200, -1));

        btnActualizarCliente.setText("Actualizar");
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });
        bg.add(btnActualizarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, 100, -1));

        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });
        bg.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 540, 100, -1));

        btnRegistrarCliente.setText("Registrar nuevo cliente");
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteActionPerformed(evt);
            }
        });
        bg.add(btnRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 240, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscar por ID");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 370, 50));
        bg.add(lblCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 200, 20));

        cmbxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxClienteActionPerformed(evt);
            }
        });
        bg.add(cmbxCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 200, 20));

        btnVerPorCategoria.setText("Ver pedidos por Cliente");
        btnVerPorCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPorCategoriaActionPerformed(evt);
            }
        });
        bg.add(btnVerPorCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 240, -1));

        jLabel11.setText("Email:");
        bg.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, -1, -1));

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        bg.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 200, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
		if (!txtId.getText().equals("")) {
			llenarTablaPedidos(txtId.getText());
		} else {
			llenarTablaPedidos("");
		}
    }//GEN-LAST:event_txtIdKeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
		String idCliente = cmbxCliente.getSelectedItem().toString().split(" - ")[0];
		try {
			controladorPedidos.actualizarPedido(lblId.getText(), txtTotal.getText(), idCliente);
			llenarTablaPedidos("");

			actualizarVistaFiltro();
			limpiarCamposProducto();
			txtId.setText("");

			JOptionPane.showMessageDialog(null, "El pedido se ha actualizado con exito");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
		try {
			controladorPedidos.eliminarPedido(lblId.getText());

			txtId.setText("");
			limpiarCamposProducto();
			llenarTablaPedidos("");

			limpiarCamposProducto();
			JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");

			actualizarVistaFiltro();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
		try {
			VistaRegistrarPedido vista = new VistaRegistrarPedido(this);
			vista.setVisible(true);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
		String idPedido = String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0));
		Pedido pedidoSeleccionado = controladorPedidos.consultarId(idPedido);

		lblId.setText(pedidoSeleccionado.getId());
		txtTotal.setText(String.valueOf(pedidoSeleccionado.getTotal()));

		int index = 0;
		for (int i = 0; i < cmbxCliente.getItemCount(); i++) {
			if (cmbxCliente.getItemAt(i).startsWith(pedidoSeleccionado.getCedulaCliente())) {
				index = i;
				break;
			}
		}
		cmbxCliente.setSelectedIndex(index);
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
		String cedula = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 0));
		Cliente clienteSeleccionado = controladorClientes.consultarCedula(cedula);

		lblCedula.setText(clienteSeleccionado.getCedula());
		txtNombreCliente.setText(clienteSeleccionado.getNombre());
		txtEmail.setText(clienteSeleccionado.getEmail());
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
		try {
			controladorClientes.actualizarCliente(lblCedula.getText(),
					txtNombreCliente.getText(),
					txtEmail.getText());

			llenarTablaClientes();
			llenarTablaPedidos("");
			limpiarCamposCliente();
			txtId.setText("");
			setCmbxCliente();
			actualizarVistaFiltro();

			JOptionPane.showMessageDialog(null, "El cliente se ha actualizado con exito");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
		try {
			controladorClientes.eliminarCliente(lblCedula.getText());

			llenarTablaClientes();

			limpiarCamposCliente();
			setCmbxCliente();

			actualizarVistaFiltro();

			JOptionPane.showMessageDialog(null, "El cliente se ha eliminado con exito");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
		VistaRegistrarCliente vista = new VistaRegistrarCliente(this);
		vista.setVisible(true);
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void btnVerPorCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPorCategoriaActionPerformed
		VistaFiltrarPedidos vista = null;
		try {
			vista = new VistaFiltrarPedidos(this);
			vistaFiltrarPedidos = vista;
		} catch (SQLException ex) {
			Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}
		vista.setVisible(true);
    }//GEN-LAST:event_btnVerPorCategoriaActionPerformed

    private void cmbxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxClienteActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_cmbxClienteActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

	private void limpiarCamposProducto() {
		lblId.setText("");
		txtTotal.setText("");
		cmbxCliente.setSelectedIndex(0);
	}

	private void limpiarCamposCliente() {
		lblCedula.setText("");
		txtNombreCliente.setText("");
		txtEmail.setText("");
	}

	public void actualizarVistaFiltro() throws SQLException {
		if (vistaFiltrarPedidos != null) {
			vistaFiltrarPedidos.setCmbxCategoria();
			vistaFiltrarPedidos.actualizarTablaPedidos(null);
			vistaFiltrarPedidos.limpiarCmbx();
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JButton btnVerPorCategoria;
    private javax.swing.JComboBox<String> cmbxCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
