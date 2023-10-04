package com.utils;

import static java.lang.String.valueOf;
import java.util.Calendar;

public class GeneralUtils {

	/**
	 * Verifica si el String ingresado es un double.
	 *
	 * @param entrada String a procesar
	 * @return boolean indicando si el String ingresado es un double longs
	 * @throws NumberFormatException {@inheritDoc}
	 */
	public static boolean esDouble(String entrada) {
		Double aux;
		try {
			aux = Double.parseDouble(entrada);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Verifica si el String ingresado esta vacio
	 *
	 * @param entrada String a procesar
	 * @return boolean indicando si el String ingresado esta vacio
	 */
	public static boolean estaVacio(String entrada) {
		if (entrada.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * Genera un identificador basado en un String ingresado
	 *
	 * @param stringIngresado String bajo el cual se creara el identificador
	 * @return String identificador compuesto de una letra y dos numeros
	 */
	public static String generarCedula() {
		int numeroMaximo = 100000000;
		int numeroMinimo = 147483647;
		return "" + valueOf((int) (Math.random() * (numeroMaximo - numeroMinimo + 1) + numeroMinimo));
	}

	/**
	 * Convierte un objeto tipo Calendar en un string con su fecha
	 *
	 * @param fechaIngresada Calendar a convertir
	 * @return String con formato "dd/mm/yyyy"
	 */
	public static String convertirFechaString(Calendar fechaIngresada) {
		return fechaIngresada.get(Calendar.YEAR) + "-"
				+ fechaIngresada.get(Calendar.MONTH) + "-"
				+ fechaIngresada.get(Calendar.DAY_OF_MONTH);
	}
}
