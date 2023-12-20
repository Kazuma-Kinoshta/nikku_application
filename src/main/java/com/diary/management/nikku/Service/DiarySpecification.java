package com.diary.management.nikku.Service;

import org.springframework.data.jpa.domain.Specification;

public class DiarySpecification<DiaryModelJPA> {
    /*学年検索*/
    public Specification<DiaryModelJPA> gradeEquals(String grade){
        return grade.isEmpty() ? null : (root, query, builder) ->{
            return builder.greaterThanOrEqualTo(root.get("grade"), grade);
        };

    }


}
