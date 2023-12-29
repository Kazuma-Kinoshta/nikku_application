package com.diary.management.nikku.Repository;

import com.diary.management.nikku.Model.DiaryModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    /*日記登録*/
    public void insertDiary(DiaryModel diaryModel);

    /*アンケート登録*/
    public void insertQuestionnare(DiaryModel diaryModel);

    /*日記取得(全件)*/
    public List<DiaryModel> selectDiariesAll();

    /*日記取得(詳細1件)*/
    public DiaryModel selectDiary(String userId, String diaryDate);

    /*日記確認*/
    public void checkDiary(String userId, String diaryDate);
}
