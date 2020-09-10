import com.demo.connector.DBConnector;
import com.demo.dto.ConnectionConfigDTO;
import com.demo.filler.ApplicationProperties;
import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DBConnectorTest {
    public static void main(String[] args) {

        ApplicationProperties applicationProperties = ApplicationProperties.getApplicationPropertiesInstance();
        ConnectionConfigDTO connectionConfig = applicationProperties.getConnectionConfig();
        assertThat(connectionConfig.getDriver(), equalTo(Driver.class.getName()));

        DBConnector dbConnector = new DBConnector(connectionConfig);

        Connection connection = null;
        connection = dbConnector.getConnection();

        if (connection != null) {
            System.out.println(" Connect successful !");
        } else {
            System.out.println(" Connection Failed!");
        }

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
