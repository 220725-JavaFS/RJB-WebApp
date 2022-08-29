package com.MatrixWeb.Services;

import java.util.List;
import com.Matrix.ORM.MatrixService;
import com.MatrixWeb.utils.MatrixORMUtils;

public class MatrixWebService {
	private MatrixService MatrixORM;

	// imitation of Matrix ORM
	public MatrixWebService(MatrixService MatrixORM) {
		this.MatrixORM = MatrixORM;
	}
	
	public MatrixWebService() {
		MatrixORM = MatrixORMUtils.getMatrixORM();
	}
	
	
	public <T> List<T> GetMatrices(Class<T> clazz) {
		if(MatrixORM == null) {
			System.out.println("THIS MATRIX IS IN ANOTHER REALM");
		}
		return MatrixORM.RetrieveMatrix(clazz);
	}
	

	public boolean UpdateMatrix(Object object) {
		return MatrixORM.MatrixUpdate(object);

	}

	public boolean DeleteMatrix(Object object) {
		return MatrixORM.DeleteMatrix(object);
	}
	
	public int StoreMatrix(Object object) {
		return MatrixORM.StoreMatrix(object);
	}
}