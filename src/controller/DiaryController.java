package controller;
import data.models.Entry;
import data.repositories.CustomerException;
import dtos.LogInRequest;
import dtos.RegisterUserRequest;
import services.DiaryService;
import services.DiaryServiceImpl;

public class DiaryController {

    private DiaryService diaryServices = new DiaryServiceImpl();
    public String register(RegisterUserRequest registerUserRequest) {
        try {
            diaryServices.register(registerUserRequest);
            return "Successful";
        }
        catch (CustomerException error){
            return error.getMessage();

        }
    }


    public String lock(String username) throws CustomerException {
        diaryServices.lock(username);
        return "Locked";
    }

    public String createEntry(String username, String title, String body) {
        try {
            diaryServices.addEntry(username, title, body);
            return "Entry Created Successfully";
        }
        catch (CustomerException error){
            return error.getMessage();
        }
    }

    public String unlock(LogInRequest logInRequest) {
        try {
            diaryServices.unlock(logInRequest);
            return "Diary Unlocked";
        }
        catch (Exception | CustomerException error){
            return error.getMessage();
        }
    }

    public String findEntry(String username, String title) {
        try {
            Entry entry = diaryServices.findEntry(username, title);
            return entry.toString();
        }
        catch (Exception | CustomerException error){
            return error.getMessage();
        }
    }

}