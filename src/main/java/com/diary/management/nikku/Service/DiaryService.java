package com.diary.management.nikku.Service;

import com.diary.management.nikku.Model.DiaryModel;

import java.util.List;

public interface DiaryService {

    /*日記登録*/
    public void insertDiary(DiaryModel diaryModel);

    /*アンケート登録*/
    public void insertQustionnare(DiaryModel diaryModel);

    /*日記取得(複数)*/
    public List<DiaryModel> selectDiariesAll();

    /*日記取得(詳細1件)*/
    public DiaryModel selectDiary(String userId, String diaryDate);

    /*日記チェック*/
    public void checkDiary(String userId, String diaryDate);
}
