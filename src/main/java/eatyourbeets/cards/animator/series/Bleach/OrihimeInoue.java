package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.orbs.animator.Fire;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.common.CounterAttackPower;
import eatyourbeets.utilities.GameActions;

public class OrihimeInoue extends AnimatorCard
{
    public static final EYBCardData DATA = Register(OrihimeInoue.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.None);

    public OrihimeInoue()
    {
        super(DATA);

        Initialize(0, 5, 1, 2);
        SetUpgrade(0, 3, 0);

        SetAffinity_Green(1, 0, 0);
        SetAffinity_Light(1, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new OrihimeInouePower(p, magicNumber));

        if (info.IsSynergizing)
        {
            GameActions.Bottom.StackPower(new CounterAttackPower(p, secondaryValue));
        }
    }

    public static class OrihimeInouePower extends AnimatorPower
    {
        public OrihimeInouePower(AbstractPlayer owner, int amount)
        {
            super(owner, OrihimeInoue.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void stackPower(int stackAmount)
        {
            super.stackPower(stackAmount);
            updateDescription();
        }

        @Override
        public void atStartOfTurn()
        {
            RemovePower();
        }

        public int onAttacked(DamageInfo info, int damageAmount)
        {
            if (this.amount <= 0)
            {
                return damageAmount;
            }

            this.amount--;

            GameActions.Bottom.ChannelOrb(new Fire());

            return damageAmount;
        }
    }
}