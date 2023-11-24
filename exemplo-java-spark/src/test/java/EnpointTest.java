import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EnpointTest {
    @Test
    public void testeListagem() throws IOException {
        URL url = new URL("http://127.0.0.1:4567/produtos");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        ObjectMapper objectMapper = new ObjectMapper();
        int responseCode = conexao.getResponseCode();
        assertEquals(200,responseCode);
    }
}
