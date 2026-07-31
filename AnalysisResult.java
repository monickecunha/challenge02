package model;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResult {

    private int totalScore;
    private RiskLevel riskLevel;
    private List<RuleResult> ruleResults;

    public AnalysisResult() {
        this.ruleResults = new ArrayList<>();
        this.totalScore = 0;
        this.riskLevel = RiskLevel.fromScore(0);
    }

    public AnalysisResult(List<RuleResult> ruleResults) {
        if (ruleResults == null) {
            this.ruleResults = new ArrayList<>();
        } else {
            this.ruleResults = ruleResults;
        }
        calculateTotalScoreAndRisk();
    }

    public void calculateTotalScoreAndRisk() {
        this.totalScore = 0;

        if (this.ruleResults != null) {
            for (RuleResult result : this.ruleResults) {
                if (result != null) {
                    this.totalScore += result.getScore();
                }
            }
        }

        this.riskLevel = RiskLevel.fromScore(this.totalScore);
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public List<RuleResult> getRuleResults() {
        return ruleResults;
    }

    public void setRuleResults(List<RuleResult> ruleResults) {
        if (ruleResults == null) {
            this.ruleResults = new ArrayList<>();
        } else {
            this.ruleResults = ruleResults;
        }
        calculateTotalScoreAndRisk();
    }
}