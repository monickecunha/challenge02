package rules;

import model.Message;
import model.RuleResult;

public interface Rule {
    RuleResult fraudRiskScoring(Message message);
}