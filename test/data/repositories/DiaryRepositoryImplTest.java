package data.repositories;

import data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoryImplTest {
    private DiaryRepositoryImpl  diaryRepository;

        @BeforeEach
        public void startWithThis() {
            diaryRepository = new DiaryRepositoryImpl();
        }


        @Test
        public void testSaveDiary() {
            Diary diary = new Diary();
            diaryRepository.save(diary);

            Diary newDiary = diaryRepository.findById(1);

            assertEquals(1,diaryRepository.count());
            assertEquals(diary,newDiary);

        }
        @Test
        public void testForUpdatingDiary() {
            Diary diary = new Diary();
            diary.setUsername("john");
            diaryRepository.save(diary);

            Diary updateDiary = new Diary();
            updateDiary.setId(1);
            updateDiary.setUsername("GodPower");

            diaryRepository.save(updateDiary);

            String newUser = diaryRepository.findById(1).getUsername();
            long numberOfUser = diaryRepository.count();



            assertEquals("GodPower",newUser);
            assertEquals(1,numberOfUser);




        }


        @Test
        public void  testForDeletingDiary(){


            Diary updateDiary = new Diary();
            updateDiary.setUsername("GodPower");

            diaryRepository.save(updateDiary);

            Diary updateDiary1 = new Diary();
            updateDiary1.setUsername("Power");

            diaryRepository.save(updateDiary1);

            long numberOfUser = diaryRepository.count();

            assertEquals(2,numberOfUser);
            assertEquals("GodPower",diaryRepository.findById(1).getUsername());
            assertEquals("Power",diaryRepository.findById(2).getUsername());

            diaryRepository.delete(updateDiary1);
            assertEquals(updateDiary,diaryRepository.findById(1));

        }


        @Test
        public void TestForClearing(){
            Diary updateDiary = new Diary();
            updateDiary.setUsername("GodPower");

            diaryRepository.save(updateDiary);

            Diary updateDiary1 = new Diary();
            updateDiary1.setUsername("Power");

            diaryRepository.save(updateDiary1);

            diaryRepository.clear();

            long size = diaryRepository.count();

            assertEquals(0,size);

        }
        @Test
    public void testThatDiarySizeIsCorrect(){
            Diary updateDiary = new Diary();
            updateDiary.setUsername("GodPower");

            diaryRepository.save(updateDiary);

            Diary updateDiary1 = new Diary();
            updateDiary1.setUsername("Power");

            diaryRepository.save(updateDiary1);
            assertEquals(2,diaryRepository.count());

        }



    }


