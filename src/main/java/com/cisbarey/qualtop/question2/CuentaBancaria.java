package com.cisbarey.qualtop.question2;

public class CuentaBancaria {

	private String numero;
	private float saldo;

	public CuentaBancaria(String numero, float saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

}
