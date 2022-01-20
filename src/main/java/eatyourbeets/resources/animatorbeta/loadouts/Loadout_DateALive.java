package eatyourbeets.resources.animatorbeta.loadouts;

import eatyourbeets.cards.animatorbeta.series.DateALive.*;
import eatyourbeets.cards.animatorbeta.ultrarare.MioTakamiya;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;

public class Loadout_DateALive extends AnimatorLoadout
{

    public Loadout_DateALive()
    {
        super(CardSeries.DateALive);

        IsBeta = true;
    }

    @Override
    public void AddStarterCards()
    {
        AddStarterCard(YamaiSisters.DATA, 4);
        AddStarterCard(ShidoItsuka.DATA, 6);
        AddStarterCard(KotoriItsuka.DATA, 6);
        AddStarterCard(Ren.DATA, 9);
        AddStarterCard(NiaHonjou.DATA, 11);
        AddStarterCard(MikuIzayoi.DATA, 11);
        AddStarterCard(ReineMurasame.DATA, 12);
        AddStarterCard(Mayuri.DATA, 14);
        AddStarterCard(YoshinoHimekawa.DATA, 15);
        AddStarterCard(Natsumi.DATA, 15);
        AddStarterCard(MukuroHoshimiya.DATA, 20);
        AddStarterCard(KurumiTokisaki.DATA, 33);
    }

    @Override
    public EYBCardData GetSymbolicCard() {
        return TohkaYatogami.DATA;
    }

    @Override
    public EYBCardData GetUltraRare() {
        return MioTakamiya.DATA;
    }
}
