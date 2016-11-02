
package com.tsguild.dvdlibrary.dao;

import com.tsguild.dvdlibrary.dto.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DvdLibraryDaoInMemImpl implements DvdLibraryDao {

    private Map<Integer, Dvd> dvdMap = new HashMap<>();
    private static int dvdIdCounter = 0;
    
    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setId(dvdIdCounter);
        dvdMap.put(dvdIdCounter, dvd);
        dvdIdCounter++;
        return dvd;
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> getAllDvds() {
        return dvdMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        // Get all the search terms from the map        
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String mpaaRatingCriteria = criteria.get(SearchTerm.MPAA_RATING);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        
        // Declare all the predicate conditions
        // Used for filtering during stream
        Predicate<Dvd> titleMatches;
        Predicate<Dvd> mpaaRatingMatches;
        Predicate<Dvd> directorMatches;
        Predicate<Dvd> studioMatches;
        
        // Placeholder predicate always returns true. 
        // Used for empty search terms.
        Predicate<Dvd> truePredicate = (c) -> {return true;};
        
        // Assign values to predicates. If empty, assign the
        // truePredicate. Using ternary operator.
        // condition ? if true : if false
        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : c -> c.getTitle().equalsIgnoreCase(titleCriteria);
        
        mpaaRatingMatches = (mpaaRatingCriteria == null || mpaaRatingCriteria.isEmpty())
                ? truePredicate
                : c -> c.getRating().equals(mpaaRatingCriteria);
        
        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : c -> c.getDirector().equalsIgnoreCase(directorCriteria);
        
        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : c -> c.getStudio().equalsIgnoreCase(studioCriteria);
        
        // Return list of Dvds that match criteria. 
        // Stream over dvdMap and filter with and.
        // *Matches is already in predicate form.
        return dvdMap.values().stream()
                .filter(titleMatches
                    .and(studioMatches)
                    .and(directorMatches)
                    .and(mpaaRatingMatches))
                .collect(Collectors.toList());
    }

    // Not in tutorial. TODO remove?
//    @Override
//    public List<Dvd> searchDvds(Predicate<Dvd> filter) {
//        throw new UnsupportedOperationException("Not supported yet."); // TODO: searchDvds
//    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getId(), dvd);
    }

    @Override
    public void removeDvd(int dvdId) {
        dvdMap.remove(dvdId);
    }

}
