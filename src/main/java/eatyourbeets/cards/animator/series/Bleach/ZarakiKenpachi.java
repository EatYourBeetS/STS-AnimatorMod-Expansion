package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnBlockBrokenSubscriber;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class ZarakiKenpachi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ZarakiKenpachi.class).SetPower(2, CardRarity.RARE);

    public ZarakiKenpachi()
    {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 2);
        SetAffinity_Red(2, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.StackPower(new ZarakiKenpachiPower(p, magicNumber));
    }

    public static class ZarakiKenpachiPower extends AnimatorPower implements OnBlockBrokenSubscriber, OnStartOfTurnPostDrawSubscriber
    {
        public static final String POWER_ID = CreateFullID(ZarakiKenpachiPower.class);
        boolean activated;

        public ZarakiKenpachiPower(AbstractPlayer owner, int amount)
        {
            super(owner, ZarakiKenpachi.DATA);

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