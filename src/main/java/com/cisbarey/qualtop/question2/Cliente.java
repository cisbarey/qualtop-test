package com.cisbarey.qualtop.question2;

public class Cliente {

	private String nombre;
	private CuentaBancaria cuenta;

	public Cliente(String nombre, CuentaBancaria cuenta) {
		this.nombre = nombre;
		this.cuenta = cuenta;
	}

	public void obtenerDatosDeCuenta() {
		System.out.println(this.cuenta.getNumero());
		System.out.println(this.cuenta.getSaldo());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static void main(String[] args) {
		CuentaBancaria cuenta = new CuentaBancaria("123456-7", 10500.75F);
		Cliente cliente = new Cliente("Juan Perez", cuenta);
		cliente.obtenerDatosDeCuenta();
	}

}
