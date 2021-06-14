package com.tylerlyczak.dvdlibrary.controller;

import com.tylerlyczak.dvdlibrary.dao.DvdLibraryDao;
import com.tylerlyczak.dvdlibrary.dao.DvdLibraryDaoException;
import com.tylerlyczak.dvdlibrary.dto.Dvd;
import com.tylerlyczak.dvdlibrary.ui.DvdLibraryView;

import java.util.List;

public class DvdLibraryController {

    private DvdLibraryView view;
    private DvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryView view, DvdLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run()   {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing)   {

                menuSelection = getMenuSelection();

                switch (menuSelection)   {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        searchDvd();
                        break;
                    case 5:
                        removeDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException e)  {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection()  {return view.printMenuAndGetSelection();}

    private void listDvds() throws DvdLibraryDaoException {
        view.bannerDisplayDvdList();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void addDvd() throws DvdLibraryDaoException {
        view.bannerAddDvd();
        Dvd dvd = view.getNewDvdInfo();
        dao.addDvd(dvd);
        view.bannerAddDvdSuccessful();
    }

    private void editDvd() throws DvdLibraryDaoException {
        view.bannerEditDvd();
        String title = view.getDvdTitleToEdit();
        int option = view.editDvdOption();
        String change = view.editDvdGetOptionString();
        Dvd editDvd = dao.editDvd(title, option, change);
        if (editDvd == null)    {
            view.bannerEditDvdUnsuccessful();
        }
        else    {
            view.bannerEditDvdSuccessful();
        }
    }

    private void searchDvd() throws DvdLibraryDaoException {
        view.bannerSearchDvd();
        String title = view.getDvdTitleToSearch();
        Dvd dvd = dao.getDvd(title);
        if (dvd == null)    {
            view.bannerEditDvdUnsuccessful();
        }
        else {
            view.displayDvdInfo(dvd);
        }
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.bannerRemoveDvd();
        String title = view.getDvdTitleToRemove();
        Dvd dvd = dao.removeDvd(title);
        if (dvd == null)    {
            view.bannerRemoveDvdUnsuccessful();
        }
        else    {
            view.bannerRemoveDvdSuccessful();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
