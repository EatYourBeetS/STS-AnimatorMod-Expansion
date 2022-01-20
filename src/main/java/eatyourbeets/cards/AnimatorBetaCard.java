package eatyourbeets.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.EYBBetaCardData;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.resources.BetaResources;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.JUtils;
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

    public void SetAutoplay(boolean value)
    {
        SetTag(BetaResources.Enums.CardTags.AUTOPLAY, value);
    }

    public void SetHarmonic(boolean value)
    {
        SetTag(BetaResources.Enums.CardTags.HARMONIC, value);
    }

    @Override
    public void triggerWhenDrawn()
    {
        super.triggerWhenDrawn();

        if (hasTag(BetaResources.Enums.CardTags.AUTOPLAY))
        {
            GameActions.Last.PlayCard(this, player.hand, null)
                    .SpendEnergy(true, true)
                    .AddCondition(AbstractCard::hasEnoughEnergy);
        }
    }

    @Override
    public boolean HasDirectSynergy(AbstractCard other)
    {
        if (hasTag(BetaResources.Enums.CardTags.HARMONIC)) {
            AnimatorCard a = JUtils.SafeCast(other, AnimatorCard.class);
            if (a != null && a.series != null && a.series.Equals(this.series)) {
                return true;
            }
        }
        return super.HasDirectSynergy(other);
    }

}