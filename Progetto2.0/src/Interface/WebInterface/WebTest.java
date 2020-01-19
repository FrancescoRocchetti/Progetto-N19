package Interface.WebInterface;

import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class WebTest {

    protected Server server;
    private MainWeb main;

    @BeforeEach
    public void aJettyServer() throws Exception {
        main = new MainWeb(8080);
        main.start();
    }

    @AfterEach
    public void stopServer() throws Exception {
        main.stop();
    }

    @Test
    public void test1() throws Exception {
        assertThat(resourceWithUrl("http://localhost:8080/Pagine/pageTest.html"), containsString("Francesco Rocchetti"));
    }

    public String resourceWithUrl(String uri) throws Exception {
        URL url = new URL( uri );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        byte[] response = new byte[ inputStream.available() ];
        inputStream.read(response);

        return new String(response);
    }
}
