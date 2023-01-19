package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Rewrite.*;
import eatyourbeets.cards.animator.ultrarare.SakuraKashima;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_Rewrite extends AnimatorLoadout
{
    public Loadout_Rewrite()
    {
        super(CardSeries.Rewrite);

        IsBeta = true;
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(ShizuruNakatsu.DATA, 4);
        AddStarterCard(Shimako.DATA, 4);
        AddStarterCard(Midou.DATA, 4);
        AddStarterCard(SougenEsaka.DATA, 6);
        AddStarterCard(Chibimoth.DATA, 8);
        AddStarterCard(ChihayaOhtori.DATA, 8);
        AddStarterCard(YoshinoHaruhiko.DATA, 9);
        AddStarterCard(ToukaNishikujou.DATA, 11);
        AddStarterCard(LuciaKonohana.DATA, 11);
        AddStarterCard(SakuyaOhtori.DATA, 11);
        AddStarterCard(KotarouTennouji.DATA, 19);
        AddStarterCard(KotoriKanbe2.DATA, 22);
    }

    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Kagari2.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return SakuraKashima.DATA;
    }
}
