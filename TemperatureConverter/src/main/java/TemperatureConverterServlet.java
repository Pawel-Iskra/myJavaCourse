import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tempConverter")
public class TemperatureConverterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher reqDisp = req.getRequestDispatcher("/tempConvForm.html");
        reqDisp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        float value = Float.parseFloat(req.getParameter("value"));
        String typeIn = req.getParameter("typeIn");
        String typeOut = req.getParameter("typeOut");

        float result = TempConverterOutputService.claculateCelToOutputType(
                typeOut, TempConverterInputService.calculateInputToCel(typeIn, value));
        result = Math.round(result * 100.0f) / 100.0f;

        out.println(value + " " + typeIn + " = " + result + " " + typeOut);
    }
}
