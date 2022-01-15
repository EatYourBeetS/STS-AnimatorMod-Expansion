package eatyourbeets.cards.animatorbeta.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class ToukaNishikujou extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(ToukaNishikujou.class).SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None).SetSeriesFromClassPackage();

    public ToukaNishikujou()
    {
        super(DATA);

        Initialize(0, 5, 1, 1);
        SetUpgrade(0, 0, 1, 0);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Light(1, 1, 0);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.Cycle(name, secondaryValue)
        .SetFilter(c -> c.cardID.equals(ThrowingKnife.DATA.ID));
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.CreateThrowingKnives(magicNumber).AddCallback(card -> {
            if (card != null) {
                GameActions.Bottom.IncreaseScaling(card, Affinity.Green, GetHandAffinity(Affinity.General));
            }

            if (info.IsSynergizing)
            {
                GameActions.Bottom.Cycle(name, secondaryValue)
                        .SetFilter(c -> c.cardID.equals(ThrowingKnife.DATA.ID));
            }
        });
    }
}