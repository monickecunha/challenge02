package service;

import rules.HasLink;
import rules.Rule;
import rules.SensitiveDataRequest;
import rules.SuspiciousTerms;
import java.util.List;

public class RulesConfig {
    public static List<Rule> loadActiveRules() {
        return List.of(
                new HasLink(),
                new SensitiveDataRequest(),
                new SuspiciousTerms()
        );
    }
}
