package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.SalesOrderDao;
import com.lavacorp.inven3.dao.StockDao;
import com.lavacorp.inven3.model.PurchaseOrder;
import com.lavacorp.inven3.model.SalesOrder;
import com.lavacorp.inven3.model.Stock;
import com.lavacorp.inven3.model.generic.Order;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    @ResponseBody
    public HttpStatus create(@RequestBody NewSalesOrderContext context) {
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    public record NewSalesOrderContext(int supplierId, LocalDateTime salesDate, LocalDateTime shipmentDate, String reference, int[] stockId, int[] quantity) {}

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "selected") int[] ids) {
        for (int id : ids)
            try {
                salesOrderDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        return HttpStatus.OK;
    }
}
