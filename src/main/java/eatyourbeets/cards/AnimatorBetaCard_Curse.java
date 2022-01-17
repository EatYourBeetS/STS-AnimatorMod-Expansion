package eatyourbeets.cards;

import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.AnimatorCard_Curse;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.BetaResources;
import eatyourbeets.resources.GR;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AnimatorBetaCard_Curse extends AnimatorCard_Curse
{
    protected static final Logger logger = LogManager.getLogger(AnimatorCard.class.getName());

    protected static EYBCardData Register(Class<? extends AnimatorCard> type) {
        return RegisterCardData(type, BetaResources.Beta.CreateID(type.getSimpleName())).SetColor(GR.Animator.CardColor);
    }

    protected AnimatorBetaCard_Curse(EYBCardData cardData, boolean special)
    {
        super(cardData, special);
    }

}