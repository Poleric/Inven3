package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.*;
import com.lavacorp.inven3.model.PurchaseOrder;
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
import java.util.Objects;

@Controller
@RequestMapping("/purchase")
public class PurchaseOrderController {
    PurchaseOrderDao purchaseOrderDao;
    SupplierDao supplierDao;
    ItemDao itemDao;
    LocationDao locationDao;

    @Autowired
    public PurchaseOrderController(PurchaseOrderDao purchaseOrderDao, SupplierDao supplierDao, ItemDao itemDao, LocationDao locationDao) {
        this.purchaseOrderDao = purchaseOrderDao;
        this.supplierDao = supplierDao;
        this.itemDao = itemDao;
        this.locationDao = locationDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "Stock.id") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = purchaseOrderDao.selectAll(true);

        List<PurchaseOrder> pos = purchaseOrderDao.selectAll(ordering, orderingDirection, page, pageSize);

        model.addAttribute("contexts", pos);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "purchase/search";
    }

    @PostMapping("/create")
    public String create(@RequestBody NewPurchaseOrderContext context, Model model, HttpServletResponse response) {
        PurchaseOrder po = new PurchaseOrder();
        po.setSupplier(supplierDao.selectById(context.supplierId));
        po.setPurchaseDate(context.purchaseDate);
        po.setTargetDate(context.targetDate);
        po.setReference(context.reference);
        po.setStatus(Order.OrderStatus.IN_TRANSIT);

        for (int i = 0; i < context.itemId.length; i++) {
            Stock stock = new Stock();

            stock.setItem(Objects.requireNonNull(itemDao.selectById(context.itemId[i])));
            stock.setSupplier(supplierDao.selectById(context.supplierId));
            if (context.locationId[i] != 0)
                stock.setLocation(locationDao.selectById(context.locationId[i]));
            stock.setQuantity(context.quantity[i]);
            stock.setStatus(Stock.StockStatus.IN_TRANSIT);

            po.getStocks().put(stock, context.quantity[i]);
        }

        try {
            purchaseOrderDao.insert(po);
        } catch (UnableToExecuteStatementException e) {
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create Purchase Order.");
            response.setStatus(HttpStatus.OK.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new Purchase Order.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    public record NewPurchaseOrderContext(int supplierId, LocalDateTime purchaseDate, LocalDateTime targetDate, String reference, Integer[] itemId, Integer[] locationId, Integer[] quantity) {}

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                purchaseOrderDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "The Purchase Order(s) is referenced by other Stocks.");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted Purchase Order(s).");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @GetMapping("/options")
    public String getOptions(Model model) {
        List<PurchaseOrder> returns  = purchaseOrderDao.selectAll();
        model.addAttribute("purchaseOrders", returns.stream().filter((po) -> po.getStatus() != Order.OrderStatus.REFUNDED).toList());
        return "purchase/options";
    }
}
