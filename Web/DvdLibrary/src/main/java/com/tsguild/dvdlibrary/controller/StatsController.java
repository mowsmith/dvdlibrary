package com.tsguild.dvdlibrary.controller;

import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.render.JsonRenderer;
import com.tsguild.dvdlibrary.dao.DvdLibraryDao;
import com.tsguild.dvdlibrary.dto.DirectorMovieCounts;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Morgan Smith
 */
@Controller
public class StatsController {

    private DvdLibraryDao dao;

    @Inject
    public StatsController(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/stats"}, method = RequestMethod.GET)
    public String displayStatsPage() {
        return "stats";
    }

    @RequestMapping(value = "/stats/chart", method = RequestMethod.GET)
// the @ResponseBody annotation tells Spring MVC that the value returned from
// this method is NOT the name of a View component. The value returned should
// be the body of the response to the caller
    @ResponseBody
    public String getDataTable() {
        try {
            // Get the contact counts for each company
            List<DirectorMovieCounts> counts = dao.getDirectorMovieCounts();

            // Set up the table columns and descriptions
            DataTable t = new DataTable();
            t.addColumn(new ColumnDescription("Director_Name",
                    ValueType.TEXT,
                    "Director"));
            t.addColumn(new ColumnDescription("Number_Movies",
                    ValueType.NUMBER,
                    "# Movies"));
            // Convert each DirectorMovieCounts object into a table row
            for (DirectorMovieCounts currentCount : counts) {
                TableRow tr = new TableRow();
                tr.addCell(currentCount.getDirector());
                tr.addCell(currentCount.getNumDvds());
                t.addRow(tr);
            }
            // Use the JsonRenderer to convert the DataTable to a JSON string
            // the default Jackson converter doesn't convert the DataTable object
            // to JSON properly so we have to do it here.
            return JsonRenderer.renderDataTable(t, true, false, false).toString();
        } catch (Exception e) {
            // just return an error string if we encounter an issue
            return "Invalid Data";
        }
    }
}
