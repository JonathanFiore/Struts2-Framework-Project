package dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSource {
	
private static BasicDataSource ds = new BasicDataSource();
    
    static {
        ds.setUrl("jdbc:mariadb://localhost:3306/scuola");
        ds.setUsername("root");
        ds.setPassword("Pass4Exprivia!");
        ds.setMinIdle(0);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }
    
    public static Connection getConnection(){
    	
        try {
			return ds.getConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    private DBCPDataSource(){ }

}