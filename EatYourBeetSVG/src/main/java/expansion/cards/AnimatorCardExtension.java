package expansion.cards;

import com.megacrit.cardcrawl.localization.CardStrings;
import eatyourbeets.cards.AnimatorCard;
import expansion.resources.Resources_Expansion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patches.AbstractEnums;

public abstract class AnimatorCardExtension extends AnimatorCard
{
    protected static final Logger logger = LogManager.getLogger(AnimatorCard.class.getName());

    public static String CreateFullID(String cardID)
    {
        return "animator:expansion:" + cardID;
    }

    protected AnimatorCardExtension(String id, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        this(Resources_Expansion.GetCardStrings(id), id, Resources_Expansion.GetCardImage(id), cost, type, AbstractEnums.Cards.THE_ANIMATOR, rarity, target);
    }

    protected AnimatorCardExtension(String id, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        this(Resources_Expansion.GetCardStrings(id), id, Resources_Expansion.GetCardImage(id), cost, type, color, rarity, target);
    }

    protected AnimatorCardExtension(CardStrings strings, String id, String imagePath, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(strings, id, imagePath, cost, type, color, rarity, target);
    }
}