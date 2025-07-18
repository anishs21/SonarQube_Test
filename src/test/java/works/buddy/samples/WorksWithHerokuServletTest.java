package works.buddy.samples;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WorksWithHerokuServletTest {

    private WorksWithHerokuServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Modern version
        servlet = new WorksWithHerokuServlet();
    }

    @Test
    public void testDoGet() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8), true);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);
        writer.flush(); // Ensure all content is written

        String actualOutput = out.toString(StandardCharsets.UTF_8.name()).trim();
        assertEquals("Buddy Works with Heroku", actualOutput);
    }
}
