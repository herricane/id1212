package se.kth.issuetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.kth.issuetracker.entity.Issue;
import se.kth.issuetracker.entity.IssueStatus;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    List<Issue> findAllByUserId(int userId);

    Issue findById(int id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Issue i SET i.title = :title, " +
            "i.description = :description, " +
            "i.status = :status WHERE i.id = :id")
    void updateById(@Param("id") int id,
                    @Param("title") String title,
                    @Param("description") String description,
                    @Param("status") IssueStatus status);

    @Modifying(clearAutomatically = true)
    void deleteById(int id);
}
