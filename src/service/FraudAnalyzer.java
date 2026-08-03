package service;

import model.AnalysisResult;
import model.Message;
import model.RuleResult;
import rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class FraudAnalyzer {
    private List<Rule> rules;

    public FraudAnalyzer() {
        this.rules = RulesConfig.loadActiveRules();
    }

    public AnalysisResult analyze(Message message) {
        List<RuleResult> results = new ArrayList<>();

        for (Rule rule : rules) {
            results.add(rule.fraudRiskScoring(message));
        }

        return new AnalysisResult(results);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
