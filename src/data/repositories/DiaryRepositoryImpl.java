package data.repositories;

import data.models.Diary;
import dtos.LogInRequest;

import java.util.ArrayList;
import java.util.List;


public class DiaryRepositoryImpl implements DiaryRepository {
    private final List<Diary> diaries = new ArrayList<>();

    private int count;

    @Override
    public Diary save(Diary diary) {
        boolean diaryDoestNotExist = diary.getId() == 0 || findById(diary.getId()) == null;
        if (diaryDoestNotExist) saveNewDiary(diary);

        else update(diary);
        return diary;
    }

    private void update(Diary diary) {
        Diary newDiary = findById(diary.getId());
        newDiary.setUsername(diary.getUsername());
    }

    private void saveNewDiary(Diary diary) {
        diary.setId(generateId());
        diaries.add(diary);

    }

    private int generateId() {
        return ++count;
    }

    @Override
    public void delete(Diary diary) {
        Diary findId = findById(diary.getId());
        diaries.remove(findId);

    }

    @Override
    public Diary findById(int id)  {
        for (Diary diary : diaries){
            if (diary.getId() == id) return diary;
        }
        return null;
    }

    @Override
    public Diary findByUsername(String username){
        for (Diary diary : diaries)
            if (diary.getUsername().equalsIgnoreCase(username))
                return diary;
        return null;
    }

    @Override
    public Iterable<Diary> findAll() {
        return diaries;
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public void clear() {

        diaries.clear();

    }




}