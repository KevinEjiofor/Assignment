package services;

import data.models.Diary;
import data.models.Entry;
import data.repositories.*;
import  dtos.LogInRequest;
import dtos.RegisterUserRequest;

import static util.Mapper.map;


public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepositoryImpl();
    private final EntryService entryService = new EntryServiceImpl();


    public void register(RegisterUserRequest registerUserRequest) throws CustomerException {
        validateForUniqueName(registerUserRequest.getUsername());
        Diary newDiary = new Diary();
        map(registerUserRequest, newDiary);
        diaryRepository.save(newDiary);
    }





    @Override
    public Diary findByUserName(String username) throws CustomerException {
        for (Diary diaryUser: diaryRepository.findAll()) {

            if (diaryUser.getUsername().equals(username)) return diaryUser;

        }
        throw new CustomerException("User don't exist");


    }

    @Override
    public void delete(String userName) throws CustomerException {
        Diary findUser = findByUserName(userName);
        diaryRepository.delete(findUser);



    }

    @Override
    public void clear() {
        diaryRepository.clear();

    }

    @Override
    public long count() {
        return diaryRepository.count();


    }

    @Override
    public Entry addEntry(String username, String title, String body) throws CustomerException {
        validate(username);
        Entry entry = entryService.addEntry(username, title, body);
        return entry;

    }




    @Override
    public Entry findEntry(String username, String title) throws CustomerException {
        validate(username);
        Entry entry = entryService.findEntry(username, title);
        return entry;
    }

    @Override
    public void lock(String username) throws CustomerException {
        Diary findUsername = findByUserName(username);
        findUsername.setIsLock(true);
        diaryRepository.save(findUsername);

    }



    public void unlock(LogInRequest logInRequest) throws CustomerException {
            Diary diary = diaryRepository.findByUsername(logInRequest.getUsername());

            if(diary == null) throw new CustomerException("Diary Not Found");

            if(diary.getPassword().equals(logInRequest.getPassword())) diary.setIsLock(false);

            else throw new CustomerException("Incorrect Password");
            diaryRepository.save(diary);
        }

    @Override
    public void update(String username, String oldPassword, String newPassword) throws CustomerException {
        Diary diary = findByUserName(username);

        if (diary.getPassword().equals(oldPassword)) diary.setPassword(newPassword);
        else throw new CustomerException("Kindly enter the correct password");


    }



    @Override
    public Iterable<DiaryService> findAll() {

        return null;
    }
    private void validateForUniqueName(String userName) throws CustomerException {
        for (var diaryUser: diaryRepository.findAll()) {
            if (diaryUser.getUsername().equals(userName))
                throw new CustomerException("User already exist");

        }


    }
    private void validate(String username) throws CustomerException {
        Diary foundDiary = diaryRepository.findByUsername(username);
        if(foundDiary == null)
            throw new CustomerException("Diary not found");
        if(foundDiary.getIsLock())
            throw new CustomerException("Diary is locked");
    }


}
