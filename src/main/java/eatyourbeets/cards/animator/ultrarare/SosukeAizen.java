package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import eatyourbeets.cards.animator.series.Bleach.ZarakiKenpachi;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnBlockBrokenSubscriber;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.common.CounterAttackPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SosukeAizen extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(SosukeAizen.class)
            .SetPower(2, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Bleach);

    public SosukeAizen()
    {
        super(DATA);

        Initialize(0, 0, 12);
        SetUpgrade(0, 0, 6);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Red(2, 0, 0);

        SetCostUpgrade(-1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.StackPower(new SosukeAizenPower(p, magicNumber));
    }

    public static class SosukeAizenPower extends AnimatorPower implements OnBlockBrokenSubscriber, OnStartOfTurnPostDrawSubscriber
    {
        public static final String POWER_ID = CreateFullID(SosukeAizenPower.class);
        boolean activated;

        public SosukeAizenPower(AbstractPlayer owner, int amount)
        {
            super(owner, SosukeAizen.DATA);

            this.amount = amount;

            CombatStats.Affinities.GetPower(Affinity.Red).Retain(-1, false);
            CombatStats.Affinities.GetPower(Affinity.Green).SetEnabled(false);
            CombatStats.Affinities.GetPower(Affinity.Blue).SetEnabled(false);

            CombatStats.onBlockBroken.Subscribe(this);
            CombatStats.onStartOfTurnPostDraw.Subscribe(this);

            updateDescription();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.Affinities.GetPower(Affinity.Red).Retain(0, false);
            CombatStats.Affinities.GetPower(Affinity.Green).SetEnabled(true);
            CombatStats.Affinities.GetPower(Affinity.Blue).SetEnabled(true);

            CombatStats.onBlockBroken.Unsubscribe(this);
            CombatStats.onStartOfTurnPostDraw.Unsubscribe(this);
        }

        @Override
        public void OnBlockBroken(AbstractCreature creature)
        {
            if (!creature.isPlayer && !activated)
            {
                activated = true;
                GameActions.Bottom.GainStrength(amount);
                GameActions.Bottom.ApplyPower(new LoseStrengthPower(player, amount));
            }
        }

        @Override
        public void OnStartOfTurnPostDraw()
        {
            activated = false;
            updateDescription();
        }
    }
}