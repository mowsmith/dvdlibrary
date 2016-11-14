
package com.tsguild.dvdlibrary.dao;

import com.tsguild.dvdlibrary.dto.DirectorMovieCounts;
import com.tsguild.dvdlibrary.dto.Dvd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Morgan Smith
 */
public interface DvdLibraryDao {

    // Create
    public Dvd addDvd(Dvd dvd);
    
    // Read 
    public Dvd getDvdById(int dvdId);
    public List<Dvd> getAllDvds();
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);
    public List<DirectorMovieCounts> getDirectorMovieCounts();
//    public List<Dvd> searchDvds(Predicate<Dvd> filter); TODO remove?
    
    // Update
    public void updateDvd(Dvd dvd);
    
    // Delete
    public void removeDvd(int dvdId);
}
