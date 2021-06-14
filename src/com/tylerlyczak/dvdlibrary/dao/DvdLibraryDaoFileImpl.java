package com.tylerlyczak.dvdlibrary.dao;

import com.tylerlyczak.dvdlibrary.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private final Map<String, Dvd> dvdMap = new HashMap<>();
    public static final String DVD_LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd newDvd = dvdMap.put(dvd.getTitle(), dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<>(dvdMap.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return dvdMap.get(title);
    }

    @Override
    public Dvd editDvd(String title, int option, String change) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd editDvd = dvdMap.get(title);

        if (editDvd == null)    {
            return null;
        }
        else    {
            dvdMap.remove(editDvd.getTitle());
            switch (option) {
                case 1:
                    editDvd.setTitle(change);
                    break;
                case 2:
                    editDvd.setReleaseDate(change);
                    break;
                case 3:
                    editDvd.setMpaaRating(change);
                    break;
                case 4:
                    editDvd.setDirectorsName(change);
                    break;
                case 5:
                    editDvd.setStudio(change);
                    break;
                case 6:
                    editDvd.setUserRating(change);
                    break;
                default:
                    break;
            }
            dvdMap.put(editDvd.getTitle(), editDvd);
            writeLibrary();
            return editDvd;
        }
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd dvd = dvdMap.remove(title);
        writeLibrary();
        return dvd;
    }

    private Dvd unmarshallDvd (String dvdAsText)    {
        /*
            String is in format of :
                title::releaseDate::mpaaRating::directorsName::studio::userRating
                    title = 0
                    releaseDate = 1
                    mpaaRating = 2
                    directorsName = 3
                    studio = 4
                    userRating = 5
        */

        // Split the string into tokes
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Return a new Dvd object using the string tokens
        return new Dvd(
                dvdTokens[0],
                dvdTokens[1],
                dvdTokens[2],
                dvdTokens[3],
                dvdTokens[4],
                dvdTokens[5]
        );
    }

    private void loadLibrary() throws DvdLibraryDaoException {
        // Declare a scanner before try-catch block
        Scanner scanner;

        // Try to instantiate the scanner, throw error if it occurs
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY_FILE)));
        } catch (FileNotFoundException e)   {
            throw new DvdLibraryDaoException("Could not load file", e);
        }

        String line;
        Dvd dvd;

        // Loop through the file
        while (scanner.hasNextLine())   {
            line = scanner.nextLine();
            dvd = unmarshallDvd(line);

            dvdMap.put(dvd.getTitle(), dvd);
        }

        // Close the scanner
        scanner.close();
    }

    private String marshallDvd (Dvd dvd)    {
        /*
            String is in format of :
                title::releaseDate::mpaaRating::directorsName::studio::userRating
        */
        return dvd.getTitle() + DELIMITER + dvd.getReleaseDate() + DELIMITER + dvd.getMpaaRating() + DELIMITER
                + dvd.getDirectorsName() + DELIMITER + dvd.getStudio() + DELIMITER + dvd.getUserRating();
    }

    private void writeLibrary() throws DvdLibraryDaoException {
        // Declare the PrintWriter to use after instantiation
        PrintWriter out;

        // Try to instantiate the PrintWrite
        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not load file", e);
        }

        List<Dvd> dvds = this.getAllDvds();
        // Loop over all the DVD in the current library
        for (Dvd dvd : dvds)    {
            out.println(marshallDvd(dvd));
            out.flush();
        }
        // Close PrintWriter
        out.close();
    }
}
