package com.tylerlyczak.dvdlibrary;

import com.tylerlyczak.dvdlibrary.controller.DvdLibraryController;
import com.tylerlyczak.dvdlibrary.dao.DvdLibraryDao;
import com.tylerlyczak.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.tylerlyczak.dvdlibrary.ui.DvdLibraryView;
import com.tylerlyczak.dvdlibrary.ui.UserIO;
import com.tylerlyczak.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args)  {
        UserIO io = new UserIOConsoleImpl();
        DvdLibraryView view = new DvdLibraryView(io);
        DvdLibraryDao dao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(view, dao);
        controller.run();
    }
}
