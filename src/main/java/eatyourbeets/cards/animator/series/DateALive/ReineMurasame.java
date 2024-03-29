package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.BlockModifiers;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ReineMurasame extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ReineMurasame.class).SetSkill(-1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new ShidoItsuka(), false));

    public ReineMurasame()
    {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0);
        SetAffinity_Blue(2, 0, 0);

        SetAffinityRequirement(Affinity.Blue, 3);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int stacks = GameUtilities.UseXCostEnergy(this);

        for (int i = 0; i < stacks; i++)
        {
            GameActions.Bottom.MakeCardInDrawPile(new ShidoItsuka())
                    .SetUpgrade(upgraded, true)
                    .AddCallback(card -> {
                        if (card.costForTurn > 0)
                        {
                            final String key = cardID + uuid;

                            CostModifiers.For(card).Add(key, -1);

                            if (card.baseBlock >= 0)
                            {
                                BlockModifiers.For(card).Add(key, -magicNumber);
                            }

                            GameUtilities.TriggerWhenPlayed(card, key, (k, c) ->
                                    CostModifiers.For(c).Remove(k, false)
                            );
                        }
                    });
        }

        if (stacks > 0 && CheckSpecialCondition(true))
        {
            GameActions.Bottom.StackPower(new DrawCardNextTurnPower(p, stacks));
        }
    }
}