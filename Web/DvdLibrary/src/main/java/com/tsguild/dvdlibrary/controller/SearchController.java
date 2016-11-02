package com.tsguild.dvdlibrary.controller;

import com.tsguild.dvdlibrary.dao.DvdLibraryDao;
import com.tsguild.dvdlibrary.dao.SearchTerm;
import com.tsguild.dvdlibrary.dto.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Morgan Smith
 */
@Controller
public class SearchController {

    private DvdLibraryDao dao;

    @Inject
    public SearchController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    @RequestMapping(value = "search/dvds", method = RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap) {
        // Create the map of search criteria to send to the DAO
        Map<SearchTerm, String> criteriaMap = new HashMap<>();

        // Determine which search terms have values, translate the String
        // keys into SearchTerm enums, and set the corresponding values
        // appropriately.
        String currentTerm = searchMap.get("title");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }
        currentTerm = searchMap.get("director");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DIRECTOR, currentTerm);
        }
        currentTerm = searchMap.get("studio");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STUDIO, currentTerm);
        }
        currentTerm = searchMap.get("rating");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.MPAA_RATING, currentTerm);
        }
        currentTerm = searchMap.get("date");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.RELEASE_DATE, currentTerm);
        }

        return dao.searchDvds(criteriaMap);
    }
}
