package services;

import data.models.Entry;
import data.repositories.CustomerException;

public interface EntryService {
    Entry addEntry(String username, String title, String body) throws CustomerException;

    long count();

    void deleteEntry(String username, String title) throws CustomerException;

    Entry findEntry(String username, String title) throws CustomerException;


}
