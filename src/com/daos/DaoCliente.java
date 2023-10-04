package com.daos;

import com.excepciones.ClienteEnUsoException;
import com.modelos.Cliente;
import com.singleton.DatabaseSingleton;
import com.utils.GeneralUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;

public class DaoCliente {

	private Connection connection;

	public DaoCliente() {
		connection = DatabaseSingleton.getInstance().getConnection();
	}

	public ArrayList<Cliente> obtenerListaClientes() {
		ArrayList<Cliente> retornoClientes = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM clientes");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente clienteObtenido = new Cliente(
						rs.getString("cedula"),
						rs.getString("nombre"),
						rs.getString("email"));
				retornoClientes.add(clienteObtenido);
			}
			return retornoClientes;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public void insertarCliente(String nombre, String email) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO clientes (cedula, nombre, email) VALUES (?, ?, ?)");
			ps.setString(1, GeneralUtils.generarCedula());
			ps.setString(2, nombre);
			ps.setString(3, email);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public Cliente consultarCliente(String cedula) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM clientes WHERE cedula = ?");
			ps.setString(1, cedula);

			ResultSet rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Cliente clienteObtenido = new Cliente(
							rs.getString("cedula"),
							rs.getString("nombre"),
							rs.getString("email"));
					return clienteObtenido;
				}

			} else {
				return null;
			}

		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public void actualizarCliente(String cedula, String nombre, String email) {

		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE clientes SET nombre = ?, email = ? WHERE cedula = ?");
			ps.setString(1, nombre);
			ps.setString(2, email);
			ps.setString(3, cedula);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public void eliminarCliente(String cedula) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM clientes WHERE cedula = ?");
			ps.setString(1, cedula);

			ps.execute();

		} catch (SQLIntegrityConstraintViolationException x) {
			throw new ClienteEnUsoException();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

}
