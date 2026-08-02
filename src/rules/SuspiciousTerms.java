package rules;

import model.Message;
import model.RuleResult;
import java.util.ArrayList;
import java.util.List;

public class SuspiciousTerms implements Rule{
    private static final String RULE_NAME = "Presença de termos suspeitos";
    private static final List<String> HIGH_RISK_TERMS = List.of(
            "bloqueada",
            "bloqueio",
            "suspensa",
            "suspenso",
            "cancelado",
            "conta encerrada",
            "última chance",
            "acesso será restrito"
    );
    private static final List<String> LOW_RISK_TERMS = List.of(
            "hoje",
            "agora",
            "urgente",
            "imediatamente",
            "rápido",
            "não perca tempo"
    );
    private static final int HIGH_POINTS = 40;
    private static final int LOW_POINTS = 15;
    private static final int LOW_MIN_COUNT = 2;

    @Override
    public RuleResult fraudRiskScoring(Message message) {
        List<String> foundTermsHigh = new ArrayList<>();
        List<String> foundTermsLow = new ArrayList<>();

        for (String term : HIGH_RISK_TERMS){
            if (message.contains(term)){
                foundTermsHigh.add(term);
            }
        }

        for (String term : LOW_RISK_TERMS){
            if (message.contains(term)){
                foundTermsLow.add(term);
            }
        }

        int highScore = foundTermsHigh.size() * HIGH_POINTS;
        int lowScore = foundTermsLow.size() >= LOW_MIN_COUNT ? foundTermsLow.size() * LOW_POINTS : 0;
        int totalScore = highScore + lowScore;

        if (totalScore == 0) {
            return RuleResult.notFlagged(RULE_NAME);
        } else {
            List<String> allFoundTerms = new ArrayList<>();
            allFoundTerms.addAll(foundTermsHigh);
            allFoundTerms.addAll(foundTermsLow);

            String reason = "Termos encontrados: " + String.join(", ", allFoundTerms);
            return new RuleResult(RULE_NAME, totalScore, true, reason);
        }
    }
}