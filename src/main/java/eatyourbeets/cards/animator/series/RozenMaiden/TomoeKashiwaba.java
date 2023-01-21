package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.BetaJUtils;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;


public class TomoeKashiwaba extends AnimatorCard
{
    public static final EYBCardData DATA = Register(TomoeKashiwaba.class).SetSkill(1, AbstractCard.CardRarity.COMMON, eatyourbeets.cards.base.EYBCardTarget.None).SetSeriesFromClassPackage();

    public TomoeKashiwaba()
    {
        super(DATA);

        Initialize(0, 3, 1, 2);
        SetUpgrade(0, 3, 0);
        SetAffinity_Green(1, 0, 1);
        SetAffinity_Red(1, 0, 0);

        SetAffinityRequirement(Affinity.Blue, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.SelectFromHand(name, 1, false)
                .SetOptions(true, true, true)
                .SetMessage(RetainCardsAction.TEXT[0])
                .SetFilter(c -> !c.isEthereal)
                .AddCallback(cards ->
                {
                    for (AbstractCard card : cards)
                    {
                        GameUtilities.Retain(card);
                        if (GameUtilities.IsSealed(card))
                        {
                            GameActions.Bottom.Motivate(magicNumber);
                        }
                    }
                });

        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.GainAffinity(Affinity.Green, secondaryValue, false);
        }
    }
}
