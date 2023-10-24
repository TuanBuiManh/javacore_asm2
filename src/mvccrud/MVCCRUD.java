package mvccrud;

import com.example.ui.ProductConsole;

import java.sql.SQLException;

public class MVCCRUD {
    public static void main(String[] args) throws SQLException {
        ProductConsole pc = new ProductConsole();
        pc.start();
    }
}
