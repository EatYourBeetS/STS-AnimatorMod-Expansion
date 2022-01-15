package eatyourbeets.cards;

import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.EYBBetaCardData;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.GR;
import eatyourbeets.resources.BetaResources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AnimatorBetaCard extends AnimatorCard
{
    protected static final Logger logger = LogManager.getLogger(AnimatorCard.class.getName());

    public static EYBCardData RegisterCardData(Class<? extends EYBCard> type, String cardID) {
        return RegisterCardData(new EYBBetaCardData(type, cardID));
    }

    protected static EYBCardData Register(Class<? extends AnimatorCard> type) {
        return RegisterCardData(type, BetaResources.Beta.CreateID(type.getSimpleName())).SetColor(GR.Animator.CardColor);
    }

    protected AnimatorBetaCard(EYBCardData cardData)
    {
        super(cardData);
    }

    protected AnimatorBetaCard(EYBCardData data, String id, String imagePath, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(data, id, imagePath, cost, type, color, rarity, target);
    }
}