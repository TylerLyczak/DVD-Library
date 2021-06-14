package com.tylerlyczak.dvdlibrary.ui;

import com.tylerlyczak.dvdlibrary.dto.Dvd;

import java.util.List;

public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io)    {this.io = io;}

    public int printMenuAndGetSelection()   {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add a DVD");
        io.print("3. Edit a DVD");
        io.print("4. Search for DVD by title and display info");
        io.print("5. Remove a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    // List all DVDs
    public void displayDvdList(List<Dvd> dvds)  {
        for (Dvd dvd : dvds)    {
            String dvdStr = "Title: " + dvd.getTitle() + "\n";
            dvdStr += "Release Date: " + dvd.getReleaseDate() + "\n";
            dvdStr += "MPAA Rating: " + dvd.getMpaaRating() + "\n";
            dvdStr += "Director's Name: " + dvd.getDirectorsName() + "\n";
            dvdStr += "Studio: " + dvd.getStudio() + "\n";
            dvdStr += "User Rating / Comments: " + dvd.getUserRating() + "\n";
            io.print(dvdStr);
        }
        io.readString("Please hit enter to continue");
    }

    public void bannerDisplayDvdList()  {io.print("=== Display All DVDs ===");}


    // Add a DVD
    public Dvd getNewDvdInfo()  {
        String title = io.readString("Please enter the DVD title");
        String releaseDate = io.readString("Please enter the DVDs Release Date");
        String mpaaRating = io.readString("Please enter the DVDs MPAA Rating");
        String directorsName = io.readString("Please enter the DVDs Director's Name");
        String studio = io.readString("Please enter the DVDs Studio");
        String userRating = io.readString("Please enter your rating or comments on the DVD");
        return new Dvd(title, releaseDate, mpaaRating, directorsName, studio, userRating);
    }

    public void bannerAddDvd()  {io.print("=== Add a DVD ===");}

    public void bannerAddDvdSuccessful()   {io.readString("Dvd successfully added. Please hit enter to continue");}


    // Edit a DVD
    public String getDvdTitleToEdit()   {return io.readString("Please enter the title of the DVD you want to edit");}

    public int editDvdOption()  {
        io.print("Options");
        io.print("1. Title");
        io.print("2. Release Date");
        io.print("3. MPAA Rating");
        io.print("4. Director's Name");
        io.print("5. Studio");
        io.print("6. User Rating / Comments");
        io.print("7. Exit Edit Menu");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public String editDvdGetOptionString()  {return io.readString("Please enter the new information");}

    public void bannerEditDvd() {io.print("=== Edit DVD ===");}

    public void bannerEditDvdSuccessful()  {io.readString("Dvd successfully edited. Please hit enter to continue");}

    public void bannerEditDvdUnsuccessful() {io.readString("Could not find DVD by that title, try again. Please hit enter to continue");}


    // Search for DVD by title
    public String getDvdTitleToSearch() {return io.readString("Please enter the title of the DVD to search");}

    public void displayDvdInfo(Dvd dvd) {
        String dvdStr = "Title: " + dvd.getTitle() + "\n";
        dvdStr += "Release Date: " + dvd.getReleaseDate() + "\n";
        dvdStr += "MPAA Rating: " + dvd.getMpaaRating() + "\n";
        dvdStr += "Director's Name: " + dvd.getDirectorsName() + "\n";
        dvdStr += "Studio: " + dvd.getStudio() + "\n";
        dvdStr += "User Rating / Comments: " + dvd.getUserRating() + "\n";
        io.print(dvdStr);
        io.readString("Please hit enter to continue");
    }

    public void bannerSearchDvd()   {io.print("=== Search for DVD ===");}


    // Remove a DVD
    public String getDvdTitleToRemove() {return io.readString("Please enter the title of the DVD to remove");}

    public void bannerRemoveDvd()   {io.print("=== Remove a DVD ===");}

    public void bannerRemoveDvdSuccessful() {io.readString("Dvd successfully removed. Please hit enter to continue");}

    public void bannerRemoveDvdUnsuccessful() {io.readString("Could not find DVD with that title, try again. Please hit enter to continue");}


    // Unknown
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
