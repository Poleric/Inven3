package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.LocationDao;
import com.lavacorp.inven3.dao.OrderDirection;
import com.lavacorp.inven3.model.Location;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {
    LocationDao locationDao;

    @Autowired
    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @PostMapping("/search")
    public String show(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "page-size", defaultValue = "20") int pageSize,
            @RequestParam(name = "ordering", defaultValue = "name") String ordering,
            @RequestParam(name = "ordering-direction", defaultValue = "ASC") OrderDirection orderingDirection,
            Model model) {
        int totalResults = locationDao.selectAllByNameLike(query, true);

        List<Location> results = locationDao.selectAllByNameLike(query, ordering, orderingDirection, page, pageSize);

        model.addAttribute("results", results);
        model.addAttribute("pageContext", new PageContext(pageSize, totalResults, page));

        return "location/search";
    }

    @PostMapping("/create")
    @ResponseBody
    public HttpStatus create(@RequestBody Location location) {
        try {
            locationDao.insert(location);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HttpStatus delete(@RequestParam(name = "id") int id) {
        try {
            locationDao.deleteById(id);
        } catch (UnableToExecuteStatementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return HttpStatus.OK;
    }
}
