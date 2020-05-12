package com.cisbarey.qualtop.question3;

import java.math.BigDecimal;

import com.cisbarey.qualtop.ExamenException;

public class RestaXXX {

	public static void main(String[] args) throws ExamenException {

		if (args.length == 3) {
			try {
				int numeroDeDecimales = Integer.parseInt(args[0]);
				double d1 = Double.parseDouble(args[1]);
				double d2 = Double.parseDouble(args[2]);

				double d3 = d1 - d2;
				System.out.println(RestaXXX.redondeaADecimales(d3, numeroDeDecimales));
			} catch (NumberFormatException e) {
				throw new ExamenException("Los parámetros deben de ser numéricos.");
			}
		} else {
			throw new ExamenException("Se esperaban 3 parámetros");
		}
	}

	public static double redondeaADecimales(double valorSinRedondear, int numeroDeDecimales) {
		BigDecimal valor = new BigDecimal(valorSinRedondear);
		valor = valor.setScale(numeroDeDecimales, BigDecimal.ROUND_HALF_UP);
		return valor.doubleValue();
	}
}
