package com.tylerlyczak.dvdlibrary.dao;

import com.tylerlyczak.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryDao {

    Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException;

    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    Dvd getDvd(String title) throws DvdLibraryDaoException;

    Dvd editDvd(String title, int option, String change) throws DvdLibraryDaoException;

    Dvd removeDvd(String title) throws DvdLibraryDaoException;
}
