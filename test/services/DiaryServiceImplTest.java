package services;

import data.models.Entry;
import data.repositories.CustomerException;
import dtos.LogInRequest;
import dtos.RegisterUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImplTest {

    private DiaryService diaryService;
    private RegisterUserRequest registerUserRequest;
    private LogInRequest logInRequest;
    @BeforeEach
    public void startWithThis(){diaryService = new DiaryServiceImpl();
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("kevin");
        registerUserRequest.setPassword("password");


        logInRequest = new LogInRequest();
        logInRequest.setUsername("kevin");
        logInRequest.setPassword("password");
    }


    @Test
    public void registrationTest() throws CustomerException {

        diaryService.register(registerUserRequest);

        assertEquals(1,diaryService.count());
    }
    @Test
    public void registrationWithSameNameTest() throws CustomerException {
        diaryService.register(registerUserRequest);
        assertEquals(1,diaryService.count());

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("kevin");
        registerUserRequest1.setPassword("password");


        assertThrows(CustomerException.class,()-> diaryService.register(registerUserRequest1));

    }
    @Test
    public void testRegistrationWithSameNameInDifferentCasing() throws CustomerException {

        diaryService.register(registerUserRequest);

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("keVin");
        registerUserRequest1.setPassword("password");



        diaryService.register(registerUserRequest1);

        assertEquals(2,diaryService.count());


    }

 @Test
    public void deleteAUser() throws CustomerException {
     diaryService.register(registerUserRequest);


     RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();

     registerUserRequest1.setUsername("ziggy");
     registerUserRequest1.setPassword("password");


     diaryService.register(registerUserRequest1);

     assertEquals(2, diaryService.count());

     diaryService.delete("ziggy");

     assertEquals(1, diaryService.count());

     assertThrows(CustomerException.class, () -> diaryService.findByUserName("ziggy"));


 }
    @Test
    public void clearTest() throws CustomerException {
        diaryService.register(registerUserRequest);

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();

        registerUserRequest1.setUsername("ziggy");
        registerUserRequest1.setPassword("password");

        diaryService.register(registerUserRequest1);

        assertEquals(2,diaryService.count());

        diaryService.clear();

        assertEquals(0,diaryService.count());

    }
    @Test
    public void testUpdatePassword() throws CustomerException {
        diaryService.register(registerUserRequest);
        diaryService.update("kevin","password","newPassword");

        diaryService.lock("kevin");
        logInRequest.setUsername("kevin");
        logInRequest.setPassword("newPassword");

        diaryService.unlock(logInRequest);

    }

    @Test
    public void testToUnlockPasswordIsIncorrect() throws CustomerException {

        diaryService.register(registerUserRequest);

        diaryService.lock("kevin");

        logInRequest.setPassword("password1");

        assertThrows(CustomerException.class, ()-> diaryService.unlock(logInRequest));
    }

    @Test
    public void testToAddEntry() throws CustomerException {
        diaryService.register(registerUserRequest);
        diaryService.addEntry("kevin","title", "body");

        Entry entry = diaryService.findEntry("kevin","title");

        assertEquals("body", entry.getBody());
    }

    @Test
    public void testForErrorMessageWithWrongTitleName() throws CustomerException {
        diaryService.register(registerUserRequest);
        diaryService.addEntry("kevin", "title", "body");

        assertThrows(CustomerException.class, () -> diaryService.findEntry("kevin", "words"));
    }

    @Test
    public void testToAddEntryWhenDiaryIsLockDiary() throws CustomerException {
        diaryService.register(registerUserRequest);
        diaryService.lock("kevin");

        assertThrows(CustomerException.class,()-> diaryService.addEntry("kevin","title", "body"));

    }




}