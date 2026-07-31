package model;

public class RuleResult {
    private String ruleName;
    private int score;
    private boolean flagged;
    private String reason;

    public RuleResult(String ruleName, int score, boolean flagged, String reason) {
        this.ruleName = ruleName;
        this.score = score;
        this.flagged = flagged;
        this.reason = reason;
    }

    public static RuleResult notFlagged(String ruleName) {
        return new RuleResult(ruleName, 0,false,"Nada suspeito encontrado.");
    }
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName)    {
        this.ruleName = ruleName;
    }

    public int getScore()  {
        return score;

    }

    public boolean isFlagged() {
        return flagged;
    }
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
    public String getReason()   {
        return reason;
    }
    public void setReason(String reason)    {
        this.reason = reason;
    }

}
