package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.SalesOrderDao;
import com.lavacorp.inven3.dao.StockDao;
import com.lavacorp.inven3.model.SalesOrder;
import com.lavacorp.inven3.model.Stock;
import com.lavacorp.inven3.model.generic.Order;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesOrderController {
    StockDao stockDao;
    SalesOrderDao salesOrderDao;

    @Autowired
    public SalesOrderController(SalesOrderDao salesOrderDao, StockDao stockDao) {
        this.salesOrderDao = salesOrderDao;
        this.stockDao = stockDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Stock.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = salesOrderDao.selectAll(true);

        List<SalesOrder> sos = salesOrderDao.selectAll(ordering, orderingDirection, page, pageSize);

        model.addAttribute("contexts", sos);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "sales/search";
    }

    @PostMapping("/create")
    public String create(@RequestBody NewSalesOrderContext context, Model model, HttpServletResponse response) {
        SalesOrder so = new SalesOrder();
        so.setSalesDate(context.salesDate);
        so.setShipmentDate(context.shipmentDate);
        so.setReference(context.reference);
        so.setStatus(Order.OrderStatus.IN_TRANSIT);

        for (int i = 0; i < context.stockId.length; i++) {
            Stock stock = stockDao.selectById(context.stockId[i]);
            so.getStocks().put(stock, context.quantity[i]);
        }

        try {
            salesOrderDao.insert(so);
        } catch (UnableToExecuteStatementException e) {
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create Sales Order.");
            response.setStatus(HttpStatus.OK.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new Sales Order.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    public record NewSalesOrderContext(int supplierId, LocalDateTime salesDate, LocalDateTime shipmentDate, String reference, int[] stockId, int[] quantity) {}

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                salesOrderDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "The Sales Order(s) is referenced by other Stocks.");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted Sales Order(s).");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @GetMapping("/options")
    public String getOptions(Model model) {
        List<SalesOrder> returns  = salesOrderDao.selectAll();
        model.addAttribute("salesOrders", returns.stream().filter((so) -> so.getStatus() != Order.OrderStatus.REFUNDED).toList());
        return "purchase/options";
    }
}
