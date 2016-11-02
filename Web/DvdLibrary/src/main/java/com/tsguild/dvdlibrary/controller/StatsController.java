
package com.tsguild.dvdlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Morgan Smith
 */

@Controller
public class StatsController {

    @RequestMapping(value={"/stats"}, method=RequestMethod.GET)
    public String displayStatsPage() {
        return "stats";
    }
}
