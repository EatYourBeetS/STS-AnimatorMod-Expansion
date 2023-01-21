package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnBlockBrokenSubscriber;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnPostDrawSubscriber;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.powers.common.CounterAttackPower;
import eatyourbeets.utilities.GameActions;

public class ZarakiKenpachi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ZarakiKenpachi.class).SetPower(2, CardRarity.RARE);
    public static final int COST = 1;

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

    public static class ZarakiKenpachiPower extends AnimatorClickablePower
    {
        public static final String POWER_ID = CreateFullID(ZarakiKenpachiPower.class);

        public ZarakiKenpachiPower(AbstractPlayer owner, int amount)
        {
            super(owner, ZarakiKenpachi.DATA, PowerTriggerConditionType.Energy, COST);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, amount);
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            super.atStartOfTurnPostDraw();
            GameActions.Bottom.StackPower(new CounterAttackPower(owner, amount));
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            GameActions.Bottom.GainTemporaryStats(amount, 0, 0);
        }
    }
}