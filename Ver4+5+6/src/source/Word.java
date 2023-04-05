package source;

public class Word {
    private String word_target;
    private String word_explain;
    private String word_target_lowcase;

    // constructor.
    public Word(String word_target, String word_explain, String word_target_lowcase) {
        this.word_explain = word_explain;
        this.word_target = word_target;
        this.word_target_lowcase = word_target_lowcase;
    }
    // getter, setter.
    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_target_lowcase() {
        return word_target_lowcase;
    }

    public void setWord_target_lowcase(String word_target_lowcase) {
        this.word_target_lowcase = word_target_lowcase;
    }
}