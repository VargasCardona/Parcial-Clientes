package com.vistas;

import com.controladores.controladorClientes;
import com.controladores.ControladorPedidos;
import com.modelos.Cliente;
import com.modelos.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class VistaFiltrarPedidos extends javax.swing.JFrame {

	ControladorPedidos controladorPedidos;
	controladorClientes controladorClientes;
	VistaPrincipal vistaPrincipal;

	public VistaFiltrarPedidos(VistaPrincipal vistaPrincipal) throws SQLException {
		controladorPedidos = new ControladorPedidos();
		controladorClientes = new controladorClientes();
		this.vistaPrincipal = vistaPrincipal;

		this.setLocationRelativeTo(null);
		setTitle("Buscar Productos");

		initComponents();

		actualizarTablaPedidos(null);
		tblPedidos.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
		tblPedidos.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
		tblPedidos.setCellSelectionEnabled(false); // Evitar selecciones en la tabla

		setCmbxCategoria();
	}

	public void setCmbxCategoria() throws SQLException {
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

	public void actualizarTablaPedidos(ArrayList<Pedido> listaFiltrada) {
		DefaultTableModel modelo = new DefaultTableModel();
		ArrayList<Pedido> listaPedidos = new ArrayList<>();

		if (listaFiltrada == null) {
			listaPedidos = controladorPedidos.listarPedidos();
		} else {
			listaPedidos = listaFiltrada;
		}

		modelo.setColumnIdentifiers(new Object[]{"ID", "Fecha de Pedido", "Total", "Nombre Cliente"});
		tblPedidos.setModel(modelo);

		for (Pedido pedidoAlmacenado : listaPedidos) {
			modelo.addRow(new Object[]{
				pedidoAlmacenado.getId(),
				pedidoAlmacenado.getFechaPedido(),
				pedidoAlmacenado.getTotal(),
				pedidoAlmacenado.getCedulaCliente()
			});
		}

		if (modelo.getRowCount() == 0) {
			lblResultados.setText("No se han encontrado coincidencias");
		} else {
			lblResultados.setText(" ");
		}
	}

	public void limpiarCmbx() {
		cmbxClientes.setSelectedIndex(0);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        lblResultados = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbxClientes = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscar por Cliente");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 370, 50));

        cmbxClientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbxClientesItemStateChanged(evt);
            }
        });
        bg.add(cmbxClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 370, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxClientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbxClientesItemStateChanged
		if (cmbxClientes.getSelectedIndex() == 0) {
			actualizarTablaPedidos(null);
			return;
		}

		String cedula = cmbxClientes.getSelectedItem().toString().split(" - ")[0].strip();
		ArrayList<Pedido> listaFiltrada = controladorPedidos.filtrarCliente(cedula);
		actualizarTablaPedidos(listaFiltrada);
    }//GEN-LAST:event_cmbxClientesItemStateChanged

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
		String sku = String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0));
    }//GEN-LAST:event_tblPedidosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> cmbxClientes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables
}
