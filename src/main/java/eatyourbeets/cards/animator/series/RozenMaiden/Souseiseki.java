package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Souseiseki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Souseiseki.class)
            .SetAttack(1, AbstractCard.CardRarity.COMMON, EYBAttackType.Normal, eatyourbeets.cards.base.EYBCardTarget.Normal)
            .PostInitialize(data -> data.AddPreview(new Suiseiseki(), false));

    public Souseiseki()
    {
        super(DATA);

        Initialize(7, 2, 0, 0);
        SetUpgrade(2, 1, 0, 0);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HORIZONTAL);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
                .SetOptions(false, false, false)
                .AddCallback(cards -> {
                    if (cards.size() > 0 && GameUtilities.IsHindrance(cards.get(0)))
                    {
                        GameActions.Bottom.Draw(1);
                    }
                });

        if (info.IsSynergizing)
        {
            GameActions.Bottom.Draw(1)
                    .ShuffleIfEmpty(false)
                    .SetFilter(c -> Suiseiseki.DATA.ID.equals(c.cardID), false);
        }
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.Draw(1)
                .ShuffleIfEmpty(false)
                .SetFilter(c -> Suiseiseki.DATA.ID.equals(c.cardID), false);
    }
}
