package com.daos;

import com.modelos.Pedido;
import com.singleton.DatabaseSingleton;
import com.utils.GeneralUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mariadb.jdbc.Connection;

public class DaoPedidos {

	private Connection connection;

	public DaoPedidos() {
		connection = DatabaseSingleton.getInstance().getConnection();
	}

	public ArrayList<Pedido> obtenerListaPedidos() {
		ArrayList<Pedido> retornoPedido = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT p.pedidoId, p.fechaPedido, p.total, c.nombre FROM pedidos as p INNER JOIN clientes as c ON p.cedulaCliente = c.cedula");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Pedido pedidoObtenido = new Pedido(
						rs.getString("p.pedidoId"),
						rs.getString("p.fechaPedido"),
						Double.parseDouble(rs.getString("p.total")),
						rs.getString("c.nombre"));

				retornoPedido.add(pedidoObtenido);
			}
			return retornoPedido;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public void insertarPedido(double total, String cedulaCliente) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO pedidos (pedidoId, fechaPedido, total, cedulaCliente) VALUES (?, ?, ?, ?)");
			ps.setString(1, GeneralUtils.generarCedula());
			ps.setString(2, GeneralUtils.convertirFechaString(new GregorianCalendar()));
			ps.setString(3, String.valueOf(total));
			ps.setString(4, cedulaCliente);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public Pedido consultarPedido(String id) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM pedidos WHERE pedidoId = ?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Pedido pedidoObtenido = new Pedido(
							rs.getString("pedidoId"),
							rs.getString("fechaPedido"),
							Double.parseDouble(rs.getString("total")),
							rs.getString("cedulaCliente"));
					return pedidoObtenido;
				}

			} else {
				return null;
			}

		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public void actualizarPedido(String id, double total, String cedulaCliente) {

		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE pedidos SET total = ?, cedulaCliente = ? WHERE pedidoId = ?");
			ps.setString(1, String.valueOf(total));
			ps.setString(2, cedulaCliente);
			ps.setString(3, id);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public void eliminarPedido(String id) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM pedidos WHERE pedidoId = ?");
			ps.setString(1, id);

			ps.execute();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public ArrayList<Pedido> filtrarCliente(String cedula) {
		ArrayList<Pedido> retornoPedido = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT p.pedidoId, p.fechaPedido, p.total, c.nombre FROM pedidos as p INNER JOIN clientes as c ON p.cedulaCliente = c.cedula WHERE p.cedulaCliente = ?");
			ps.setString(1, cedula);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Pedido pedidoObtenido = new Pedido(
						rs.getString("p.pedidoId"),
						rs.getString("p.fechaPedido"),
						Double.parseDouble(rs.getString("p.total")),
						rs.getString("c.nombre"));

				retornoPedido.add(pedidoObtenido);
			}

			return retornoPedido;
		} catch (SQLException ex) {
			Logger.getLogger(DaoPedidos.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
