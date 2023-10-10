package data.repositories;

import data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryImplTest {

    EntryRepositoryImpl entryRepository;

    @BeforeEach
    public void startWith(){entryRepository = new EntryRepositoryImpl();}


    @Test
    public void testSaveEntry(){
        Entry entry = new Entry();
        entry.setTitle("my word");
        entry.setBody("be care of people");

        entryRepository.save(entry);

        long numbersOfEntry = entryRepository.count();

        assertEquals(1,numbersOfEntry);
    }

    @Test
    public void testUpdateAndSaveEntry() {
        Entry entry = new Entry();
        entry.setTitle("my word");
        entry.setBody("be care of people");
        entryRepository.save(entry);

        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setTitle("word");
        entry1.setBody("in God we trust");
        entryRepository.save(entry1);

        Entry newEntryBody = entryRepository.findById(1);
        Entry newEntryForTitle = entryRepository.findById(1);


        assertEquals("word",newEntryForTitle.getTitle());
        assertEquals("in God we trust",newEntryBody.getBody());


        long numbersOfEntry = entryRepository.count();
        assertEquals(1,numbersOfEntry);
    }
//    @Test
//    public void testForWrongId() {
//        Entry entry = new Entry();
//        entry.setTitle("my word");
//        entry.setBody("be care of people");
//        entryRepository.save(entry);
//
//        assertThrows(CustomerException.class,()->entryRepository.findById(23));
//
//    }

    @Test
        public void testForDeletingEntry()  {
        Entry entry = new Entry();
        entry.setTitle("my word");
        entry.setBody("be care of people");
        entryRepository.save(entry);

        Entry entry1 = new Entry();
        entry1.setTitle("word");
        entry1.setBody("in God we trust");
        entryRepository.save(entry1);

        Entry newEntryBody = entryRepository.findById(1);
        Entry newEntryForTitle = entryRepository.findById(1);

        Entry newEntryForTitle1 = entryRepository.findById(2);
        Entry newEntryBody1 = entryRepository.findById(2);

        assertEquals("my word",newEntryForTitle.getTitle());
        assertEquals("be care of people",newEntryBody.getBody());

        assertEquals("word",newEntryForTitle1.getTitle());
        assertEquals("in God we trust",newEntryBody1.getBody());

        long numbersOfEntry = entryRepository.count();

        assertEquals(2,numbersOfEntry);

        entryRepository.delete(entry);

        long numbersOfEntry1 = entryRepository.count();
        assertEquals(1,numbersOfEntry1);


    }

    @Test
    public void testToClearEntryList()  {
        Entry entry = new Entry();
        entry.setTitle("my word");
        entry.setBody("be care of people");
        entryRepository.save(entry);

        Entry entry1 = new Entry();
        entry1.setTitle("word");
        entry1.setBody("in God we trust");
        entryRepository.save(entry1);

        Entry newEntryBody = entryRepository.findById(1);
        Entry newEntryForTitle = entryRepository.findById(1);

        Entry newEntryForTitle1 = entryRepository.findById(2);
        Entry newEntryBody1 = entryRepository.findById(2);

        assertEquals("my word",newEntryForTitle.getTitle());
        assertEquals("be care of people",newEntryBody.getBody());

        assertEquals("word",newEntryForTitle1.getTitle());
        assertEquals("in God we trust",newEntryBody1.getBody());

        long numbersOfEntry = entryRepository.count();

        assertEquals(2,numbersOfEntry);

        entryRepository.clear();

        long checkOfEntry = entryRepository.count();
        assertEquals(0,checkOfEntry);
    }
}