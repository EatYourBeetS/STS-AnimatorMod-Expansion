package eatyourbeets.cards.animatorbeta.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnChannelOrbSubscriber;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class Kagari extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(Kagari.class).SetPower(2, CardRarity.UNCOMMON).SetSeriesFromClassPackage();

    public Kagari()
    {
        super(DATA);

        Initialize(0, 0, 1, 2);
        SetUpgrade(0, 0, 1, 0);
        SetAffinity_Blue(1, 1, 0);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetainOnce(true);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.StackPower(new KagariPower(p, magicNumber, secondaryValue, secondaryValue));
    }

    public static class KagariPower extends AnimatorPower implements OnChannelOrbSubscriber
    {
        private final int willpowerAmount;
        private final int shacklesAmount;
        public KagariPower(AbstractPlayer owner, int amount, int willpowerAmount, int shacklesAmount)
        {
            super(owner, Kagari.DATA);

            this.willpowerAmount = willpowerAmount;
            this.shacklesAmount = shacklesAmount;

            Initialize(amount);
            updateDescription();
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount, willpowerAmount, shacklesAmount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            ResetAmount();
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onChannelOrb.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onChannelOrb.Unsubscribe(this);
        }

        @Override
        public void OnChannelOrb(AbstractOrb orb) {
            if ((Earth.ORB_ID.equals(orb.ID) || Dark.ORB_ID.equals(orb.ID)) && amount > 0) {
                GameActions.Bottom.GainIntellect(willpowerAmount + (player.stance.ID.equals(IntellectStance.STANCE_ID) ? 1 : 0), player.stance.ID.equals(IntellectStance.STANCE_ID));
                GameActions.Bottom.StackPower(TargetHelper.Enemies(), PowerHelper.Shackles, shacklesAmount);
                this.amount -= 1;
                updateDescription();
                flash();
            }
        }
    }
}