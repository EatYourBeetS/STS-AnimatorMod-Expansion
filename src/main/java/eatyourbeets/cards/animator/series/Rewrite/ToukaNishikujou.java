package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;

public class ToukaNishikujou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ToukaNishikujou.class).SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None);

    public ToukaNishikujou()
    {
        super(DATA);

        Initialize(0, 5, 1, 1);
        SetUpgrade(0, 0, 1, 0);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Light(1, 1, 0);

        SetAffinityRequirement(Affinity.Green, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.CreateThrowingKnives(magicNumber).AddCallback(card -> {
            if (card != null)
            {
                GameActions.Bottom.IncreaseScaling(card, Affinity.Green, magicNumber);
            }

            if (CheckSpecialCondition(true))
            {
                GameActions.Bottom.Cycle(name, secondaryValue);
            }
        });
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.Cycle(name, secondaryValue);
    }
}