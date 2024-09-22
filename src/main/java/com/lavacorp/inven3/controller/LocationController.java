package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.LocationDao;
import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.dao.StockDao;
import com.lavacorp.inven3.model.Location;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stock/location")
public class LocationController {
    LocationDao locationDao;
    StockDao stockDao;

    @Autowired
    public LocationController(LocationDao locationDao, StockDao stockDao) {
        this.locationDao = locationDao;
        this.stockDao = stockDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "25") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "name") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = locationDao.selectAllByNameLike(query, true);

        List<Location> locations = locationDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);

        List<LocationContext> contexts = new ArrayList<>();
        for (Location location : locations) {
            assert location.getId() != null;
            contexts.add(new LocationContext(location, stockDao.selectAllByLocationId(location.getId(), true)));
        }

        model.addAttribute("contexts", contexts);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "stock/location/search";
    }

    public record LocationContext(Location location, int stockCount) {}

    @PostMapping("/create")
    public String create(@RequestBody Location location, Model model, HttpServletResponse response) {
        try {
            locationDao.insert(location);
        } catch (UnableToExecuteStatementException e) {
            model.addAttribute("status","bad");
            model.addAttribute("message", "Failed to create Stock location.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "fragments/status";
        }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully created new Stock location.");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(name = "selected") int[] ids, Model model, HttpServletResponse response) {
        for (int id : ids)
            try {
                locationDao.deleteById(id);
            } catch (UnableToExecuteStatementException e) {
                model.addAttribute("status","bad");
                model.addAttribute("message", "The Stock location(s) is referenced by other Stocks.");
                response.setStatus(HttpStatus.OK.value());
                return "fragments/status";
            }

        model.addAttribute("status", "ok");
        model.addAttribute("message", "Successfully deleted Stock location(s).");
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("HX-Trigger", "update");
        return "fragments/status";
    }

    @GetMapping("/options")
    public String getOptions(Model model) {
        model.addAttribute("locations", locationDao.selectAll());
        return "/stock/location/options";
    }
}
