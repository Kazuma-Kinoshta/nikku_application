package com.diary.management.nikku.Repository;

import com.diary.management.nikku.Model.DiaryModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DiaryMapper {

    /*日記登録*/
    public void insertDiary(DiaryModel diaryModel);

    /*アンケート登録*/
    public void insertQuestionnare(DiaryModel diaryModel);

    /*日記取得(全件)*/
    public List<DiaryModel> selectDiariesAll();
}
