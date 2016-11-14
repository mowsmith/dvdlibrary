package com.tsguild.dvdlibrary.dao;

import com.tsguild.dvdlibrary.dto.DirectorMovieCounts;
import com.tsguild.dvdlibrary.dto.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Morgan Smith
 */
public class DvdLibraryDaoDatabaseImpl implements DvdLibraryDao {

    // So here's our private property to hold the jdbcTemplate obj
    private JdbcTemplate jdbcTemplate;

    // Here's a setter to do setter injection!!
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_DVD = "insert into DVDs (title, rating, director, studio, note, `date`) values (?, ?, ? ,? , ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_ADD_DVD, dvd.getTitle(), dvd.getRating(), dvd.getDirector(), dvd.getStudio(), dvd.getUserNote(), dvd.getDate());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        dvd.setId(id);
        return dvd;
    }

    private static final String SQL_SELECT_DVD_BY_ID = "select * from DVDs where id = ?";

    @Override
    public Dvd getDvdById(int dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD_BY_ID, // select stmt
                    new DvdMapper(), // what we're turning the RS into!
                    dvdId); // and then subsitituting in any placeholders
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final String SQL_SELECT_ALL_DVDS = "select * from DVDs";

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }

    private static final String SQL_SEARCH_DVDS = "select * from DVDs where title like ? and rating like ? and director like ? and studio like ? and `date` like ?";
    
    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String mpaaRatingCriteria = criteria.get(SearchTerm.MPAA_RATING);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        String dateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        
        return jdbcTemplate.query(SQL_SEARCH_DVDS, new DvdMapper(),
                (titleCriteria == null || titleCriteria.isEmpty())
                ? "%"
                : titleCriteria,
                (mpaaRatingCriteria == null || mpaaRatingCriteria.isEmpty())
                ? "%"
                : mpaaRatingCriteria,
                (directorCriteria == null || directorCriteria.isEmpty())
                ? "%"
                : directorCriteria,
                (studioCriteria == null || studioCriteria.isEmpty())
                ? "%"
                : studioCriteria,
                (dateCriteria == null || dateCriteria.isEmpty())
                ? "%"
                : dateCriteria
        );
    }

    private static final String SQL_UPDATE_DVD_BY_ID = "update DVDs set title = ?, rating = ?, director = ?, studio = ?, note = ?, date = ? where id = ?";
    
    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD_BY_ID, dvd.getTitle(), dvd.getRating(), dvd.getDirector(), dvd.getStudio(), dvd.getUserNote(), dvd.getDate(), dvd.getId());
    }

    private static final String SQL_REMOVE_DVD_BY_ID = "delete from DVDs where id = ?";
    
    @Override
    public void removeDvd(int dvdId) {
        jdbcTemplate.update(SQL_REMOVE_DVD_BY_ID, dvdId);
    }

    private static final String SQL_SELECT_DIRECTOR_DVD_COUNTS = "SELECT director, count(*) as num_movies FROM DVDs group by director";
    
    @Override
    public List<DirectorMovieCounts> getDirectorMovieCounts() {
        return jdbcTemplate.query(SQL_SELECT_DIRECTOR_DVD_COUNTS, new DirectorMovieCountMapper());
    }

    private static final class DirectorMovieCountMapper implements RowMapper<DirectorMovieCounts> { 

        @Override
        public DirectorMovieCounts mapRow(ResultSet rs, int rowNum) throws SQLException {
            DirectorMovieCounts count = new DirectorMovieCounts();
            count.setDirector(rs.getString("director"));
            count.setNumDvds(rs.getInt("num_movies"));
            return count;
        }
        
    }
    
    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            String title = rs.getString("title");
            String rating = rs.getString("rating");
            String director = rs.getString("director");
            String studio = rs.getString("studio");
            String note = rs.getString("note");
            String date = rs.getString("date");
            int id = rs.getInt("id");

            dvd.setTitle(title);
            dvd.setRating(rating);
            dvd.setDate(date);
            dvd.setDirector(director);
            dvd.setStudio(studio);
            dvd.setUserNote(note);
            dvd.setId(id);

            return dvd;
        }

    }
}
