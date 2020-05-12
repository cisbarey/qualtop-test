package com.cisbarey.qualtop.question4;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

import com.cisbarey.qualtop.ExamenException;

public class DatabaseConnection {

	public static final String HOST = "45.40.139.98";
	public static final String DB = "test";
	public static final String USER = "AdminBD_des";
	public static final String PASS = "Desa#0216";
	public static final String CONN = "jdbc:sqlserver://{0}:1433;databaseName={1};user={2};password={3}";
	
	public static final String PATH = "C:\\Users\\CARLOS\\Documents\\output.txt";

	public static void main(String[] args) throws ExamenException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(MessageFormat.format(CONN, HOST, DB, USER, PASS));

			String SQL = "SELECT "
					.concat("T1.ID_ESTADO, ")
					.concat("CASE ")
					.concat("WHEN SUM(T2.NUMERO_EMPLEADOS) > 0 THEN SUM(T2.NUMERO_EMPLEADOS) ")
					.concat("ELSE 0 ")
					.concat("END NUMERO_EMPLEADOS, ")
					.concat("T1.NOMBRE ")
					.concat("FROM ESTADOS T1 ")
					.concat("LEFT JOIN PLANTAS T2 ON T2.ID_ESTADO = T1.ID_ESTADO ")
					.concat("GROUP BY T1.ID_ESTADO, T1.NOMBRE ")
					.concat("ORDER BY NUMERO_EMPLEADOS DESC");
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			Path path = Paths.get(PATH);
			try (BufferedWriter writer = Files.newBufferedWriter(path)) {
				while (rs.next()) {
					StringBuffer sb = new StringBuffer();
					sb.append(rs.getString("ID_ESTADO")).append(",");
					sb.append(rs.getString("NUMERO_EMPLEADOS")).append(",");
					sb.append(rs.getString("NOMBRE")).append("\n");
					writer.write(sb.toString());
				}
			}
			System.out.println("Archivo creado!");
		} catch (Exception e) {
			throw new ExamenException(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					throw new ExamenException(e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					throw new ExamenException(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ExamenException(e.getMessage());
				}
			}
		}
	}

}
