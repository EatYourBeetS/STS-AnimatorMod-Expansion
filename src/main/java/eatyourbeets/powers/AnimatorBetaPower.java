package eatyourbeets.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import eatyourbeets.resources.BetaResources;
import eatyourbeets.resources.GR;

public abstract class AnimatorBetaPower extends AnimatorPower
{
    public static String CreateFullID(Class<? extends AnimatorPower> type) {
        return BetaResources.Beta.CreateID(type.getSimpleName());
    }

    public AnimatorBetaPower(AbstractCreature owner, AbstractCreature source, String id) {
        super(owner, id);
        this.source = source;
    }

    public AnimatorBetaPower(AbstractCreature owner, String id) {
        super(owner, id);
    }
}