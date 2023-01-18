package eatyourbeets.resources.animator.loadouts;

import eatyourbeets.cards.animator.series.RozenMaiden.*;
import eatyourbeets.cards.animator.ultrarare.Kirakishou;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_RozenMaiden extends AnimatorLoadout
{
    public Loadout_RozenMaiden()
    {
        super(CardSeries.RozenMaiden);

        IsBeta = true;
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(TomoeKashiwaba.DATA, 5);
        AddStarterCard(NoriSakurada.DATA, 5);
        AddStarterCard(Hinaichigo.DATA, 5);
        AddStarterCard(MitsuKusabue.DATA, 7);
        AddStarterCard(Souseiseki.DATA, 8);
        AddStarterCard(Suiseiseki.DATA, 8);
        AddStarterCard(Shinku.DATA, 8);
        AddStarterCard(MeguKakizaki.DATA, 8);
        AddStarterCard(Shinku.DATA, 9);
        AddStarterCard(JunSakurada.DATA, 11);
        AddStarterCard(Barasuishou.DATA, 15);
        AddStarterCard(Suigintou.DATA, 22);
    }


    @Override
    public EYBCardData GetSymbolicCard()
    {
        return Suigintou.DATA;
    }

    @Override
    public EYBCardData GetUltraRare()
    {
        return Kirakishou.DATA;
    }
}
