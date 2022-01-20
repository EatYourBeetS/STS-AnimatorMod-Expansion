package eatyourbeets.relics.animatorbeta;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import eatyourbeets.cards.animatorbeta.series.DateALive.ShidoItsuka;
import eatyourbeets.relics.BetaRelic;
import eatyourbeets.utilities.GameActions;

public class PurpleTeddyBear extends BetaRelic
{
    public static final String ID = CreateFullID(PurpleTeddyBear.class);

    public PurpleTeddyBear()
    {
        super(ID, AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.SOLID);
    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();

        for (int i=0; i<3; i++)
        {
            GameActions.Bottom.MakeCardInDrawPile(new ShidoItsuka())
            .AddCallback(card ->
            {
                if (card.cost > 0 || card.costForTurn > 0)
                {
                    card.cost = 0;
                    card.costForTurn = 0;
                    card.isCostModified = true;
                }

                card.freeToPlayOnce = true;
                card.upgrade();
                card.applyPowers();
            });
        }

        flash();
    }
}