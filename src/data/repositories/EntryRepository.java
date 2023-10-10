package data.repositories;


import data.models.Entry;

public interface EntryRepository {
    Entry save(Entry entry) ;

    void delete(Entry entry) ;

    Entry findById(int id) ;

    Iterable<Entry> findAll() ;

    long count();

    void clear();


    Entry findEntry(String username, String title);

}
