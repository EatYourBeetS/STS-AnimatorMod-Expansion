package eatyourbeets.cards.animator.curse;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.tokens.AffinityToken_Blue;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Curse_JunTormented extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Curse_JunTormented.class)
            .SetCurse(-2, eatyourbeets.cards.base.EYBCardTarget.None, true).SetSeries(CardSeries.RozenMaiden);

    public Curse_JunTormented()
    {
        super(DATA);
        Initialize(0, 0, 2, 0);
        SetAffinity_Dark(1);
        SetAffinity_Blue(1);
        SetUnplayable(true);
        playAtEndOfTurn = true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
    }

    @Override
    public void triggerWhenDrawn()
    {
        super.triggerWhenDrawn();

        for (AbstractCard c : player.hand.group)
        {
            if (GameUtilities.IsHindrance(c) && !uuid.equals(c.uuid) && !Curse_JunTormented.DATA.ID.equals(c.cardID))
            {
                GameActions.Last.MakeCardInHand(c.makeStatEquivalentCopy());
            }
        }
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        if (CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Bottom.MakeCardInHand(new AffinityToken_Blue());
        }

    }
}