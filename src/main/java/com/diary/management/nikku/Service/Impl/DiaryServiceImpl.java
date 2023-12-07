package com.diary.management.nikku.Service.Impl;

import com.diary.management.nikku.Model.DiaryModel;
import com.diary.management.nikku.Repository.DiaryMapper;
import com.diary.management.nikku.Service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryMapper diaryMapper;

    /*日記の取得(複数)*/
    @Override
    public List<DiaryModel> selectDiariesAll(){
        List<DiaryModel> diaries = diaryMapper.selectDiariesAll();
        return diaries;
    }

    /*日記の取得(詳細1件)*/
    @Override
    public DiaryModel selectDiary(String userId, String diaryDate){
        DiaryModel diary = diaryMapper.selectDiary(userId, diaryDate);
        return diary;
    }

    @Override
    public void insertDiary(DiaryModel diaryModel){
        diaryMapper.insertDiary(diaryModel);
    }

    @Override
    public void insertQustionnare(DiaryModel diaryModel){
        diaryMapper.insertQuestionnare(diaryModel);
    }

    @Override
    public void checkDiary(String userId, String diaryDate){
        diaryMapper.checkDiary(userId, diaryDate);

    }
}
