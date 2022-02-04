package eatyourbeets.relics.animatorbeta;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.relics.BetaRelic;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.JUtils;

public class Mistletoe extends BetaRelic
{
    public static final String ID = CreateFullID(Mistletoe.class);
    public static final int SCRY_AMOUNT = 2;
    public static final int DRAW_AMOUNT = 1;

    public Mistletoe()
    {
        super(ID, AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.MAGICAL);
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance)
    {
        super.onChangeStance(prevStance, newStance);

        GameActions.Bottom.Scry(SCRY_AMOUNT);
        GameActions.Bottom.Draw(DRAW_AMOUNT);
        flash();
    }

    @Override
    public String getUpdatedDescription()
    {
        return JUtils.Format(DESCRIPTIONS[0], SCRY_AMOUNT, DRAW_AMOUNT);
    }
}