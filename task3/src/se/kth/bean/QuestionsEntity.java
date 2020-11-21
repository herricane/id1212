package se.kth.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "questions", schema = "id1212")
public class QuestionsEntity {
    private int id;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int answer;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "choice_1")
    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    @Basic
    @Column(name = "choice_2")
    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    @Basic
    @Column(name = "choice_3")
    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    @Basic
    @Column(name = "choice_4")
    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    @Basic
    @Column(name = "answer")
    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionsEntity that = (QuestionsEntity) o;
        return id == that.id &&
                answer == that.answer &&
                Objects.equals(question, that.question) &&
                Objects.equals(choice1, that.choice1) &&
                Objects.equals(choice2, that.choice2) &&
                Objects.equals(choice3, that.choice3) &&
                Objects.equals(choice4, that.choice4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, choice1, choice2, choice3, choice4, answer);
    }
}
