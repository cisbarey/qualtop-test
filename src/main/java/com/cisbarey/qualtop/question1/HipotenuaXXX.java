package com.cisbarey.qualtop.question1;

import java.math.BigDecimal;
import java.text.MessageFormat;

import com.cisbarey.qualtop.ExamenException;

public class HipotenuaXXX {

	public static final String PATTERN = "La hipotenusa de un cuadrado de {0}X{1} es {2}";

	public static void main(String[] args) throws ExamenException {

		if (args.length == 2) {
			String param1 = args[0];
			String param2 = args[1];

			try {
				BigDecimal catA = new BigDecimal(param1).pow(2);
				BigDecimal catB = new BigDecimal(param2).pow(2);
				BigDecimal sum = catA.add(catB);
				BigDecimal hip = new BigDecimal(Math.sqrt(sum.doubleValue()));

				System.out.println(MessageFormat.format(PATTERN, param1, param2, hip));
			} catch (NumberFormatException e) {
				throw new ExamenException("Los parámetros deben de ser numéricos.");
			}
		} else {
			throw new ExamenException("Se esperaban 2 parámetros");
		}
	}

}
