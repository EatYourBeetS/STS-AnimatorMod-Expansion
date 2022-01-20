package eatyourbeets.relics;

import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.relics.AnimatorRelic;
import eatyourbeets.resources.BetaResources;

public class BetaRelic extends AnimatorRelic
{
    public static String CreateFullID(Class<? extends AnimatorRelic> type) {
        return BetaResources.Beta.CreateID(type.getSimpleName());
    }

    public BetaRelic(String id, RelicTier tier, LandingSound sfx)
    {
        super(id, tier, sfx);
    }
}
