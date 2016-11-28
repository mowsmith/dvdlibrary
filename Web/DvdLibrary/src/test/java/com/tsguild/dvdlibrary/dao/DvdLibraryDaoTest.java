/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.dvdlibrary.dao;

import com.tsguild.dvdlibrary.dto.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoTest {

    DvdLibraryDao testObj;

    public DvdLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testObj = ctx.getBean("jdbcDao", DvdLibraryDao.class);

        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from DVDs");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteDvd() {

        // Create new dvd
        Dvd dvd = new Dvd();
        dvd.setTitle("Cat Movie");
        dvd.setDirector("Me");
        dvd.setRating("NC17");
        dvd.setDate("2016-06-11");
        dvd.setStudio("Home");
        dvd.setUserNote("Good movie");

        testObj.addDvd(dvd);

        Dvd fromDb = testObj.getDvdById(dvd.getId());

        Assert.assertEquals(fromDb, dvd);

        testObj.removeDvd(dvd.getId());

        Assert.assertNull(testObj.getDvdById(dvd.getId()));
    }

    @Test
    public void addUpdateDvd() {

        // Create new dvd
        Dvd dvd = new Dvd();
        dvd.setTitle("Cat Movie");
        dvd.setDirector("Me");
        dvd.setRating("NC17");
        dvd.setDate("2016-06-11");
        dvd.setStudio("Home");
        dvd.setUserNote("Good movie");

        testObj.addDvd(dvd);

        dvd.setDirector("Bob");

        testObj.updateDvd(dvd);

        Dvd fromDb = testObj.getDvdById(dvd.getId());

        Assert.assertEquals(fromDb, dvd);
    }

    @Test
    public void getAllDvds() {

        // Create new dvd
        Dvd dvd = new Dvd();
        dvd.setTitle("Cat Movie");
        dvd.setDirector("Me");
        dvd.setRating("NC17");
        dvd.setDate("2016-06-11");
        dvd.setStudio("Home");
        dvd.setUserNote("Good movie");

        testObj.addDvd(dvd);

        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Dog Movie");
        dvd2.setDirector("You");
        dvd2.setRating("PG");
        dvd2.setDate("2016-03-12");
        dvd2.setStudio("Outside");
        dvd2.setUserNote("Bad movie");

        testObj.addDvd(dvd2);

        List<Dvd> dList = testObj.getAllDvds();
        Assert.assertEquals(2, dList.size());
    }

    @Test
    public void searchDvds() {
        // Create new dvd
        Dvd dvd = new Dvd();
        dvd.setTitle("Cat Movie");
        dvd.setDirector("Me");
        dvd.setRating("NC17");
        dvd.setDate("2016-06-11");
        dvd.setStudio("Home");
        dvd.setUserNote("Good movie");

        testObj.addDvd(dvd);

        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Dog Movie");
        dvd2.setDirector("You");
        dvd2.setRating("PG");
        dvd2.setDate("2016-2-03");
        dvd2.setStudio("Outside");
        dvd2.setUserNote("Bad movie");

        testObj.addDvd(dvd2);

        Dvd dvd3 = new Dvd();
        dvd3.setTitle("Cat Movie");
        dvd3.setDirector("Her");
        dvd3.setRating("NC17");
        dvd3.setDate("2016-06-11");
        dvd3.setStudio("Home");
        dvd3.setUserNote("Good movie");

        testObj.addDvd(dvd3);

        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.MPAA_RATING, "NC17");
        List<Dvd> dList = testObj.searchDvds(criteria);
        Assert.assertEquals("Search for NC17 not 2 as expected.", 2, dList.size());
        Assert.assertEquals(dvd, dList.get(0));
        Assert.assertEquals(dvd3, dList.get(1));

        criteria.remove(SearchTerm.MPAA_RATING);

        criteria.put(SearchTerm.TITLE, "Dog Movie");
        dList = testObj.searchDvds(criteria);
        Assert.assertEquals("Search for Dog Movie not 1 as expected.", 1, dList.size());

        criteria.put(SearchTerm.DIRECTOR, "Her");
        dList = testObj.searchDvds(criteria);
        Assert.assertEquals("Search for director 'Her' and title 'Dog Movie' not 0 as expected", 0, dList.size());

        criteria.put(SearchTerm.TITLE, "Cat Movie");
        dList = testObj.searchDvds(criteria);
        Assert.assertEquals("Search for title 'Cat Movie' and director 'Her' not 1 as expected.", 1, dList.size());
        Assert.assertEquals(dvd3, dList.get(0));
    }
}
