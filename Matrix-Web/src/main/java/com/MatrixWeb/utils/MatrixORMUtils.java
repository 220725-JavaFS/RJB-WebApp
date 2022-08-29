package com.MatrixWeb.utils;

import java.sql.SQLException;
import com.Matrix.ORM.MatrixService;

public class MatrixORMUtils {

	private static MatrixService MatrixORM;

	public static MatrixService getMatrixORM() {
//		Same way in my connection utilities
		if (MatrixORM != null) {
//			if Matrix ORM exists than get that
			return MatrixORM;
		} else {
			try {
//				JDBC ABSTRACTION
				MatrixORM = new MatrixService(ConnectionUtils.getConnection());
			} catch (SQLException e) {
//				ERROR OCCURS
				e.printStackTrace();
			}
			return MatrixORM;
		}

	}

}