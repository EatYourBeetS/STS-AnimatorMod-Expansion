package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnSynergySubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class NiaHonjou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(NiaHonjou.class).SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None);

    public NiaHonjou()
    {
        super(DATA);

        Initialize(0, 0, 2, 1);
        SetAffinity_Light(1, 1, 0);
        SetAffinity_Blue(1, 0, 0);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.StackPower(new NiaHonjouPower(p, magicNumber));
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        if (CombatStats.TryActivateSemiLimited(cardID))
        {
            GameActions.Top.GainAffinity(Affinity.Light, secondaryValue, upgraded);
        }
    }

    public static class NiaHonjouPower extends AnimatorPower
    {
        public NiaHonjouPower(AbstractPlayer owner, int amount)
        {
            super(owner, NiaHonjou.DATA);

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
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);
            RemovePower();
        }

        @Override
        public void onAfterCardPlayed(AbstractCard usedCard)
        {
            super.onAfterCardPlayed(usedCard);

            if (GameUtilities.IsSealed(usedCard))
            {
                GameActions.Bottom.GainBlock(amount * 2);
            }
            else
            {
                GameActions.Bottom.GainBlock(amount);
            }
        }
    }
}