package com.lavacorp.inven3.controller;

import com.lavacorp.inven3.dao.LocationDao;
import com.lavacorp.inven3.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showLocationList(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
            Model model) {
        int totalResults = locationDao.selectAllByNameLike(query, true);

        List<Location> results = locationDao.selectAllByNameLike(query, "name", "DESC", page, pageSize);

        model.addAttribute("results", results);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalResults);
        model.addAttribute("currentRow", (page - 1) * pageSize + 1);
        model.addAttribute("totalRows", totalResults);

        return "location/search";
    }
}
