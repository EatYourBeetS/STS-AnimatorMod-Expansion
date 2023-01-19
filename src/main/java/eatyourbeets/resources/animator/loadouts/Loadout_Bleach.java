package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.Bleach.*;
import eatyourbeets.cards.animator.ultrarare.SosukeAizen;
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
        AddStarterCard(YasutoraSado.DATA, 5);
        AddStarterCard(UryuuIshida.DATA, 5);
        AddStarterCard(IkkakuMadarame.DATA, 5);
        AddStarterCard(OrihimeInoue.DATA, 6);
        AddStarterCard(IchigoKurosaki2.DATA, 6);
        AddStarterCard(MayuriKurotsuchi.DATA, 7);
        AddStarterCard(IsshinKurosaki.DATA, 10);
        AddStarterCard(RukiaKuchiki.DATA, 13);
        AddStarterCard(GinIchimaru.DATA, 13);
        AddStarterCard(KanameTousen.DATA, 14);
        AddStarterCard(SajinKomamura.DATA, 14);
        AddStarterCard(ToushirouHitsugaya.DATA, 20);
        AddStarterCard(ByakuyaKuchiki.DATA, 20);
    }


    @Override
    public EYBCardData GetSymbolicCard()
    {
        return IchigoKurosaki2.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return SosukeAizen.DATA;
    }
}
