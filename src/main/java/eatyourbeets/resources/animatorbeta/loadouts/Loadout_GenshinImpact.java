package eatyourbeets.resources.animatorbeta.loadouts;

import eatyourbeets.cards.animatorbeta.curse.Curse_SearingBurn;
import eatyourbeets.cards.animatorbeta.series.GenshinImpact.*;
import eatyourbeets.cards.animatorbeta.ultrarare.RaidenShogun;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_GenshinImpact extends AnimatorLoadout
{
    public Loadout_GenshinImpact()
    {
        super(CardSeries.GenshinImpact);
        IsBeta = true;
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(Amber.DATA, 4);
        AddStarterCard(Noelle.DATA, 5);
        AddStarterCard(LisaMinci.DATA, 6);
        AddStarterCard(KaeyaAlberich.DATA, 7);
        AddStarterCard(Fischl.DATA, 10);
        AddStarterCard(Bennett.DATA, 10);
        AddStarterCard(Tartaglia.DATA, 10);
        AddStarterCard(Keqing.DATA, 13);
        AddStarterCard(Klee.DATA, 14);
        AddStarterCard(HuTao.DATA, 14);
        AddStarterCard(BarbaraPegg.DATA, 16);
        AddStarterCard(JeanGunnhildr.DATA, 20);
        AddStarterCard(Venti.DATA, 25);
        AddStarterCard(AyakaKamisato.DATA, 38);
        AddStarterCard(Curse_SearingBurn.DATA, -4);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Venti.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return RaidenShogun.DATA;
    }
}
