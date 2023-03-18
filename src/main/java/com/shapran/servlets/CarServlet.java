package com.shapran.servlets;

import com.shapran.model.Car;
import com.shapran.model.Type;
import com.shapran.service.CarService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CarServlet", value = "/CarServlet")
public class CarServlet extends HttpServlet {

    @Override
    public void init(){
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        List<Car> cars = CarService.getInstance().getAll();
        request.setAttribute("cars", cars);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String carType = request.getParameter("type");
        Car car = CarService.getInstance().createCar(Type.valueOf(carType));
        response.setContentType("text/html");
        PrintWriter responseBody= response.getWriter();
        responseBody.println("<html>");
        responseBody.println("<h1 align=\"center\"> Car created </h1>");
        responseBody.println("<title>MyServlet</title>");
        responseBody.println("</body>");
        responseBody.println("</html>");
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}
