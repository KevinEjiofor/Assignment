package services;

import data.repositories.CustomerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryServiceImplTest {

    EntryService entryService;
    @BeforeEach
    public void startWith()  {entryService = new EntryServiceImpl();}


    @Test
    public void testEntryCanBeAdded() throws CustomerException {
        entryService.addEntry("don", "never forget ", "go and sleep you too like talk");
        long howLong = entryService.count();

        assertEquals(1, howLong);

    }

    @Test
    public void testWeCanDeleteEntry() throws  CustomerException {
        entryService.addEntry("don", "never forget ", "go and sleep you too like talk");
        entryService.addEntry("don G", "never give up", "if you like go and sleep on yourself");

        long howLong = entryService.count();

        assertEquals(2, howLong);

        entryService.deleteEntry("don", "never forget ");


        assertEquals(1, entryService.count());
    }

    @Test
    public void testToDeleteWithWrongUsername() throws CustomerException {
        entryService.addEntry("don", "never forget ", "go and sleep you too like talk");
        entryService.addEntry("don G", "never give up", "if you like go and sleep on yourself");

        long howLong = entryService.count();

        assertEquals(2, howLong);

        assertThrows(CustomerException.class,()-> entryService.deleteEntry("don P", "never give up"));


    }





}