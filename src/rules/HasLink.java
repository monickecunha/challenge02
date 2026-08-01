package rules;

import model.Message;
import model.RuleResult;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HasLink implements Rule{
    private static final String RULE_NAME = "Presença de link";
    private static final String URL_REGEX = "(https?://|www\\.)\\S+";
    private static final Pattern LINK_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
    private static final List<String> SHORT_LINK = List.of("bit.ly", "tinyurl", "t.co", "is.gd", "cutt.ly");
    private static final int NORMAL_LINK_POINTS = 15;
    private static final int SHORT_LINK_POINTS = 35;

    @Override
    public RuleResult fraudRiskScoring(Message message) {
        Matcher matcher = LINK_PATTERN.matcher(message.getOriginalText());

        if(matcher.find()) {
            String foundLink = matcher.group();
            boolean isShort = false;

            for (String link : SHORT_LINK){
                if (foundLink.toLowerCase().contains(link)){
                    isShort = true;
                    break;
                }
            }

            int score = isShort ? SHORT_LINK_POINTS : NORMAL_LINK_POINTS;
            String reason = isShort ? "Link encurtado suspeito foi encontrado: " + foundLink : "Link encontrado: " + foundLink;

            return new RuleResult(RULE_NAME, score, true, reason);
        }else{
            return RuleResult.notFlagged(RULE_NAME);
        }
    }
}
