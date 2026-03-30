package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 解析路径（兼容 /order/1001 格式）
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.setStatus(400);
            out.println("Error: order ID is required (e.g., /order/1001)");
            return;
        }
        String orderIdStr = pathInfo.substring(1).trim();

        // 2. 校验ID是否为数字
        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("Error: invalid order ID format");
            return;
        }

        // 3. 获取全局订单列表
        List<Order> orderList = (List<Order>) getServletContext().getAttribute("orderList");
        if (orderList == null || orderList.isEmpty()) {
            response.setStatus(404);
            out.println("Error: no orders found");
            return;
        }

        // 4. 查找对应ID的订单
        Order foundOrder = null;
        for (Order order : orderList) {
            if (order.getOrderId() == orderId) {
                foundOrder = order;
                break;
            }
        }

        // 5. 订单不存在处理
        if (foundOrder == null) {
            response.setStatus(404);
            out.println("Error: Order " + orderId + " not found");
            return;
        }

        // 6. 严格按测试脚本要求的格式输出（顺序、换行、文字都不能改）
        out.println("Order Detail");
        out.println("Order ID: " + foundOrder.getOrderId());
        out.println("Customer: " + foundOrder.getCustomer());
        out.println("Food: " + foundOrder.getFood());
        out.println("Quantity: " + foundOrder.getQuantity());
        response.setStatus(200); // 必须显式返回200
    }
}