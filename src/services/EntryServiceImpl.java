package services;

import data.models.Entry;
import data.repositories.CustomerException;
import data.repositories.EntryRepository;
import data.repositories.EntryRepositoryImpl;

public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository = new EntryRepositoryImpl();



    @Override
    public Entry addEntry(String username, String title, String body) throws CustomerException {

        validateTitleDoNotExist(username,title);
        Entry newEntry = new Entry();
        newEntry.setOwnerName(username);
        newEntry.setTitle(title);
        newEntry.setBody(body);
        entryRepository.save(newEntry);

        return newEntry;
    }

    @Override
    public long count() {
        return entryRepository.count();
    }

    @Override
    public void deleteEntry(String username, String title) throws CustomerException {
        Entry entry = findEntry(username, title);
        entryRepository.delete(entry);


    }

    @Override
    public Entry findEntry(String username, String title) throws CustomerException {
        Entry foundEntry =  entryRepository.findEntry(username, title);
        boolean entryFound = foundEntry == null;
        if (entryFound) throw new CustomerException("Entry not found");
        return foundEntry;
    }

    private void validateTitleDoNotExist(String username, String title) throws CustomerException {
        Entry foundEntry = entryRepository.findEntry(username, title);
        if(foundEntry != null)
            throw new CustomerException("Entry Title Already Exists");
    }
}
