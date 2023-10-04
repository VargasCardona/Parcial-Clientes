package com.controladores;

import com.daos.DaoCliente;
import com.excepciones.CamposVaciosException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.modelos.Cliente;
import com.singleton.DatabaseSingleton;
import com.utils.GeneralUtils;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;

public class controladorClientes {

	private Connection connection;
	private DaoCliente daoCliente;

	public controladorClientes() {
		connection = DatabaseSingleton.getInstance().getConnection();
		daoCliente = new DaoCliente();
	}

	public void insertarCliente(String nombre, String email) {
		if (GeneralUtils.estaVacio(nombre) || GeneralUtils.estaVacio(email)) {
			throw new CamposVaciosException();
		}
		daoCliente.insertarCliente(nombre, email);
	}

	public ArrayList<Cliente> listarClientes() {
		return daoCliente.obtenerListaClientes();
	}

	public Cliente consultarCedula(String cedula) {
		return daoCliente.consultarCliente(cedula);
	}

	public void actualizarCliente(String cedula, String nombre, String email) {
		if (GeneralUtils.estaVacio(cedula)) {
			throw new ElementoNoSeleccionadoException();
		}

		if (GeneralUtils.estaVacio(nombre) || GeneralUtils.estaVacio(email)) {
			throw new CamposVaciosException();
		}

		daoCliente.actualizarCliente(cedula, nombre, email);
	}

	public void eliminarCliente(String cedula) {
		if (GeneralUtils.estaVacio(cedula)) {
			throw new ElementoNoSeleccionadoException();
		}

		daoCliente.eliminarCliente(cedula);
	}

	public ArrayList<Cliente> obtenerClientes() {
		return daoCliente.obtenerListaClientes();
	}
}
