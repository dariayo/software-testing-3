package org.example.servlets;

import org.example.model.Point;
import org.example.model.PointCollection;


import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@WebServlet("/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        HttpSession session = request.getSession();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            long timer = System.nanoTime();

            // Проверяем наличие всех параметров
            String xParam = request.getParameter("x-value");
            String yParam = request.getParameter("y-value");
            String rParam = request.getParameter("r-value");

            if (xParam == null || yParam == null || rParam == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                printWriter.write("{\"error\": \"Missing required parameters\"}");
                return;
            }

            // Парсим параметры с проверкой формата
            float x = Float.parseFloat(xParam.trim());
            float y = Float.parseFloat(yParam.trim());
            float r = Float.parseFloat(rParam.trim());

            // Проверяем допустимость значений
            if (r < 1 || r > 5) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                printWriter.write("{\"error\": \"R should be between 2 and 5\"}");
                return;
            }

            String status = hit(x, y, r);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String currentTime = formatter.format(LocalDateTime.now(ZoneId.of("Europe/Moscow")));
            long runtime = (long) ((System.nanoTime() - timer) * 0.00001);

            // Сохраняем точку в сессии
            PointCollection pointsCollection = (PointCollection) session.getAttribute("dots");
            if (pointsCollection == null) {
                pointsCollection = new PointCollection();
            }

            Point newPoint = new Point(x, y, r, currentTime, runtime, status);
            pointsCollection.add(newPoint);
            session.setAttribute("dots", pointsCollection);

            printWriter.write(newPoint.toJSON());

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.write("{\"error\": \"Invalid number format\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.write("{\"error\": \"Internal server error\"}");
        }
    }

    private String hit(double x, double y, double r) {
        if (isCircle(x, y, r) || isTriangle(x, y, r) || isSquare(x, y, r)) {
            return "True";
        }
        return "False";
    }

    private boolean isSquare(double x, double y, double r) {
        return x >= -r / 2 && x <= 0 && y <= 0 && y >= -r;
    }

    private boolean isCircle(double x, double y, double r) {
        return ((x * x + y * y) <= r * r / 4 && x <= 0 && y >= 0);
    }

    private boolean isTriangle(double x, double y, double r) {
        return (y >= (x - r / 2) && y <= 0 && x >= 0);
    }
}
