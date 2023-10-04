package com.controladores;

import com.daos.DaoPedidos;
import com.excepciones.CamposVaciosException;
import com.excepciones.DoubleInvalidoException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.modelos.Pedido;
import com.singleton.DatabaseSingleton;
import com.utils.GeneralUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;

public class ControladorPedidos {

	private Connection connection;
	private DaoPedidos daoPedidos;

	public ControladorPedidos() {
		connection = DatabaseSingleton.getInstance().getConnection();
		daoPedidos = new DaoPedidos();
	}

	public void insertarPedido(String total, String cedulaCliente) {
		if (GeneralUtils.estaVacio(total)
				|| cedulaCliente.equals("Seleccione un cliente")) {
			throw new CamposVaciosException();
		}

		if (!GeneralUtils.esDouble(total)
				|| Double.valueOf(total) < 0) {
			throw new DoubleInvalidoException();
		}

		daoPedidos.insertarPedido(Double.parseDouble(total), cedulaCliente);
	}

	public ArrayList<Pedido> listarPedidos() {
		return daoPedidos.obtenerListaPedidos();
	}

	public ResultSet filtrarCategoria(String idCategoria) {
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT p.sku, p.nombre, p.precio, p.distribuidor, c.nombre FROM productos as p INNER JOIN Categorias as c ON p.id_categoria = c.id WHERE c.id = ?");
			ps.setString(1, idCategoria);

			return ps.executeQuery();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public ArrayList<Pedido> filtrarCliente(String cedula) {
		return daoPedidos.filtrarCliente(cedula);
	}

	public Pedido consultarId(String id) {
		return daoPedidos.consultarPedido(id);
	}

	public void actualizarPedido(String id, String total, String cedulaCliente) {
		if (GeneralUtils.estaVacio(id)) {
			throw new ElementoNoSeleccionadoException();
		}

		if (GeneralUtils.estaVacio(total)
				|| cedulaCliente.equals("Seleccione un cliente")) {
			throw new CamposVaciosException();
		}

		if (!GeneralUtils.esDouble(total)
				|| Double.valueOf(total) < 0) {
			throw new DoubleInvalidoException();
		}

		daoPedidos.actualizarPedido(id, Double.valueOf(total), cedulaCliente);
	}

	public void eliminarPedido(String id) {
		if (GeneralUtils.estaVacio(id)) {
			throw new ElementoNoSeleccionadoException();
		}
		daoPedidos.eliminarPedido(id);
	}
}
