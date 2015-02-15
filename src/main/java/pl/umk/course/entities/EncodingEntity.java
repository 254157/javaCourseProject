package pl.umk.course.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "entity")
public class EncodingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String sourceText;

    @NotNull
    private String encodedText;

    @NotNull
    private Date encodingDate;

    public Long getId() {
        return id;
    }

    public String getSourceText() {
        return sourceText;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public Date getEncodingDate() {
        return encodingDate;
    }

    public EncodingEntity() {
    }

    public EncodingEntity(String sourceText, String encodedText, Date encodingDate) {
        this.sourceText = sourceText;
        this.encodedText = encodedText;
        this.encodingDate = encodingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncodingEntity entity = (EncodingEntity) o;

        if (!encodedText.equals(entity.encodedText)) return false;
        if (!sourceText.equals(entity.sourceText)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sourceText.hashCode();
        result = 31 * result + encodedText.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EncodingEntity{" +
                "id=" + id +
                ", sourceText='" + sourceText + '\'' +
                ", encodedText='" + encodedText + '\'' +
                ", encodingDate=" + encodingDate +
                '}';
    }
}
