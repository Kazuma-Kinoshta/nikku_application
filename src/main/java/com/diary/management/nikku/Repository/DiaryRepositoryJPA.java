package com.diary.management.nikku.Repository;
import com.diary.management.nikku.Model.USERS1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tamorieeeen
 *
 */
@Repository
public interface DiaryRepositoryJPA extends
        JpaRepository<USERS1, Integer>, JpaSpecificationExecutor<USERS1> {
}