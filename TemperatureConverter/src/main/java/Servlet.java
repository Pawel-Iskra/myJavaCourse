import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tc")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/form.html");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float value = Float.parseFloat(req.getParameter("value"));
        String inUnit = req.getParameter("inUnit");
        String outUnit = req.getParameter("outUnit");

        float result = OutputService.calculateFromCelsiusToOutputUnit(outUnit,
                InputService.calculateFromInputUnitToCelsius(inUnit, value));

        PrintWriter out = resp.getWriter();
        out.println(String.format("%.2f %s = %.2f %s ", value, inUnit, result, outUnit));

    }
}
