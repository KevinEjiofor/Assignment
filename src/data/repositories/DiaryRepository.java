package data.repositories;

import data.models.Diary;
import dtos.LogInRequest;

public interface DiaryRepository {

    Diary save(Diary diary) throws CustomerException;

    void delete(Diary diary) throws CustomerException;

    Diary findById(int id) throws CustomerException;

    Diary findByUsername(String Username) throws CustomerException;

    Iterable<Diary> findAll() ;

    long count();

    void clear();



}
