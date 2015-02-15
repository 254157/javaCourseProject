package pl.umk.course.forms;

import pl.umk.course.validators.EncodableText;

import javax.validation.constraints.Size;

public class EncodeForm {
    @Size(min=2, max=4)
    /*@Pattern(regexp = "[a-z]*")*/
    @EncodableText
    private String sourceText;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public EncodeForm() {
    }

    public EncodeForm(String sourceText) {
        this.sourceText = sourceText;
    }
}
