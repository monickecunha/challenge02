package rules;

import model.Message;
import model.RuleResult;
import java.util.ArrayList;
import java.util.List;

public class SensitiveDataRequest implements Rule{
    private static final String RULE_NAME = "Pedido de dados sensíveis";
    private static final List<String> SENSITIVE_TERMS = List.of(
            "cpf",
            "senha",
            "codigo",
            "instale",
            "codigo de verificacao",
            "numero do cartao",
            "token",
            "cvv",
            "pix",
            "documento",
            "taxa",
            "premio"
    );
    private static final int POINTS_PER_TERM = 30;

    @Override
    public RuleResult fraudRiskScoring(Message message) {
        List<String> foundTerms = new ArrayList<>();

        for(String term : SENSITIVE_TERMS){
            if(message.contains(term)){
                foundTerms.add(term);
            }
        }

        if(foundTerms.isEmpty()){
            return RuleResult.notFlagged(RULE_NAME);
        }else{
            return new RuleResult(RULE_NAME, (POINTS_PER_TERM * foundTerms.size()), true, "Termos encontrados: " + String.join(", ", foundTerms));
        }
    }
}