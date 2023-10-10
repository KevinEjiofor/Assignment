package services;

import data.models.Diary;
import data.models.Entry;
import data.repositories.CustomerException;
import  dtos.LogInRequest;
import dtos.RegisterUserRequest;

public interface DiaryService {


    void register(RegisterUserRequest registerUserRequest) throws CustomerException;

    Diary findByUserName(String username) throws CustomerException;

    void  delete(String userName) throws CustomerException;

    void clear();

    long count();


    void lock(String username) throws CustomerException;

    void unlock(LogInRequest logInRequest) throws CustomerException;

    void update(String username, String oldPassword, String newPassword) throws CustomerException;

    Iterable <DiaryService> findAll() ;
    Entry addEntry(String username, String title, String body) throws CustomerException;

    Entry findEntry(String username, String title) throws CustomerException;
}
