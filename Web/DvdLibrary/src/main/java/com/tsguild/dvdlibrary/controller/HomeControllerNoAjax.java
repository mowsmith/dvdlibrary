package com.tsguild.dvdlibrary.controller;

import com.tsguild.dvdlibrary.dao.DvdLibraryDao;
import com.tsguild.dvdlibrary.dto.Dvd;
import com.tsguild.dvdlibrary.dto.MpaaRating;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Morgan Smith
 */
@Controller
public class HomeControllerNoAjax {

    private DvdLibraryDao dao;

    @Inject
    public HomeControllerNoAjax(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayDvdListNoAjax", method = RequestMethod.GET)
    public String displayDvdListNoAjax(Model model) {
        List<Dvd> dList = dao.getAllDvds();
        model.addAttribute("dvdList", dList);
        return "displayDvdListNoAjax";
    }

    @RequestMapping(value = "/displayNewDvdFormNoAjax", method = RequestMethod.GET)
    public String displayNewContactFormNoAjax() {
        return "newDvdFormNoAjax";
    }

    @RequestMapping(value = "/addNewDvdNoAjax", method = RequestMethod.POST)
    public String addNewDvdNoAjax(HttpServletRequest req, Model model) {

        String title = req.getParameter("title");
        String director = req.getParameter("director");
        String studio = req.getParameter("studio");
        String userNote = req.getParameter("userNote");
        String rating = req.getParameter("mpaaRating");
        String date = req.getParameter("releaseDate");

        MpaaRating mpaaRating;
        Calendar releaseDate;

        switch (rating) {
            case "G":
                mpaaRating = MpaaRating.G;
                break;
            case "PG":
                mpaaRating = MpaaRating.PG;
                break;
            case "PG13":
                mpaaRating = MpaaRating.PG13;
                break;
            case "R":
                mpaaRating = MpaaRating.R;
                break;
            case "NC17":
                mpaaRating = MpaaRating.NC17;
                break;
            case "NR":
                mpaaRating = MpaaRating.NR;
                break;
            default:
                model.addAttribute("error", "Could not parse rating. Please try again.");
                return "newDvdFormNoAjax";
        }

        String[] stringDateArr = date.split("-");
        int[] dateArr = new int[3];

        try {
            dateArr[0] = Integer.parseInt(stringDateArr[0]);
            dateArr[1] = Integer.parseInt(stringDateArr[1]);
            dateArr[2] = Integer.parseInt(stringDateArr[2]);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Could not parse date. Please try again.");
            return "newDvdFormNoAjax";
        }

        releaseDate = new GregorianCalendar(dateArr[0], dateArr[1] - 1, dateArr[2]);

        Dvd dvd = new Dvd(title, mpaaRating, director, studio, userNote, releaseDate);
        dao.addDvd(dvd);

        return "redirect:displayDvdListNoAjax";
    }

    @RequestMapping(value = "/deleteDvdNoAjax", method = RequestMethod.GET)
    public String deleteDvdNoAjax(HttpServletRequest req, Model model) {

        int dvdId;

        try {
            dvdId = Integer.parseInt(req.getParameter("dvdId"));
        } catch (NumberFormatException numberFormatException) {
            model.addAttribute("error", "Could not parse id. Please try again.");
            return "redirect:displayDvdListNoAjax";
        }

        dao.removeDvd(dvdId);
        return "redirect:displayDvdListNoAjax";
    }
    
    @RequestMapping(value="/displayEditDvdFormNoAjax", method=RequestMethod.GET) 
    public String displayEditDvdFormNoAjax(HttpServletRequest req, Model model) {
        
        int dvdId;

        try {
            dvdId = Integer.parseInt(req.getParameter("dvdId"));
        } catch (NumberFormatException numberFormatException) {
            model.addAttribute("error", "Could not parse id. Please try again.");
            return "redirect:displayDvdListNoAjax";
        }
        
        Dvd dvd = dao.getDvdById(dvdId);
        model.addAttribute("dvd", dvd);
        
        return "editDvdFormNoAjax";
    }
    
    @RequestMapping(value="/editDvdNoAjax", method=RequestMethod.POST)
    public String editDvdNoAjax(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editDvdFormNoAjax";
        }
        
        dao.updateDvd(dvd);
        return "redirect:displayDvdListNoAjax";
    }
}
