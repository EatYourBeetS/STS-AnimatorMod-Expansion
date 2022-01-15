package eatyourbeets.resources.animatorbeta.loadouts;

import eatyourbeets.cards.animatorbeta.series.Bleach.*;
import eatyourbeets.cards.animatorbeta.ultrarare.SosukeAizen;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Bleach extends AnimatorLoadout
{
    public Loadout_Bleach()
    {
        super(CardSeries.Bleach);

        IsBeta = true;
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(RenjiAbarai.DATA, 4);
        AddStarterCard(UryuuIshida.DATA, 4);
        AddStarterCard(OrihimeInoue.DATA, 4);
        AddStarterCard(YasutoraSado.DATA, 5);
        AddStarterCard(IchigoKurosaki.DATA, 7);
    }


    @Override
    public EYBCardData GetSymbolicCard()
    {
        return IchigoKurosaki.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return SosukeAizen.DATA;
    }
}
