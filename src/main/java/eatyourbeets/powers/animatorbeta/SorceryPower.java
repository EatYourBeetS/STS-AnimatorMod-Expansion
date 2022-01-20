package eatyourbeets.powers.animatorbeta;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.interfaces.subscribers.OnChannelOrbSubscriber;
import eatyourbeets.powers.AnimatorBetaPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameUtilities;

public class SorceryPower extends AnimatorBetaPower implements OnChannelOrbSubscriber
{
    public static final String POWER_ID = CreateFullID(SorceryPower.class);

    public SorceryPower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        Initialize(amount);
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
        GameUtilities.IncreaseOrbPassiveAmount(orb, amount);
        GameUtilities.IncreaseOrbEvokeAmount(orb, amount);
        RemovePower();
    }
}
