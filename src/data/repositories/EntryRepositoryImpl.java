package data.repositories;

import data.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository{
    private int count;
    private final List<Entry> entryList =new ArrayList<>();
    @Override
    public Entry save(Entry entry)  {
        boolean entryDoesNotExist = entry.getId() == 0 || findById(entry.getId()) == null;
        if(entryDoesNotExist) saveNewEntry(entry);

        else update(entry);
        return entry;
    }


    private int generateId() {return ++count;}

    @Override
    public void delete(Entry entry)  {
        Entry remove = findById(entry.getId());
        entryList.remove(remove);

    }

    @Override
    public Entry findById(int id) {
        for (Entry entry : entryList){
            if (entry.getId() == id)return entry;
        }
        return null;
    }

    @Override
    public Iterable<Entry> findAll() {
        return entryList;
    }

    @Override
    public long count() {
        return entryList.size();
    }

    @Override
    public void clear() {
        entryList.clear();

    }

    @Override
    public Entry findEntry(String username, String title) {
        for (Entry entry : entryList) {
           if (entry.getOwnerName().equalsIgnoreCase(username) && entry.getTitle().equalsIgnoreCase(title)) return entry;
        }
        return null;
    }


    private void update(Entry entry){
        Entry newEntry = findById(entry.getId());
        newEntry.setTitle(entry.getTitle());
        newEntry.setBody(entry.getBody());
    }


    private void saveNewEntry(Entry entry) {
        entry.setId(generateId());
        entryList.add(entry);

    }
}
