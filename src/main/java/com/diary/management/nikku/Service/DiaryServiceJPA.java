package com.diary.management.nikku.Service;

import com.diary.management.nikku.Dao.DiarySpecification;
import com.diary.management.nikku.Form.DiaryForm;
import com.diary.management.nikku.Model.DiaryModelJPA;
import com.diary.management.nikku.Repository.DiaryRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceJPA {

    @Autowired
    DiaryRepositoryJPA diaryRepositoryJPA;

    public List<DiaryModelJPA> getSearchDiaries(DiaryForm diaryForm){
        DiarySpecification<DiaryModelJPA> spec = new DiarySpecification<>();
        return diaryRepositoryJPA.findAll(
                Specification.where(spec.gradeEquals(diaryForm.getGrade()))
        );
    }

}
