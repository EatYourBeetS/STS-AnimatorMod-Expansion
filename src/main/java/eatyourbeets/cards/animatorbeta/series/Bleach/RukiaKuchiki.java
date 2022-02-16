package eatyourbeets.cards.animatorbeta.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animatorbeta.special.RukiaBankai;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class RukiaKuchiki extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(RukiaKuchiki.class).SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .PostInitialize(data -> data.AddPreview(new RukiaBankai(), false));

    public RukiaKuchiki()
    {
        super(DATA);

        Initialize(0, 2, 2, 4);
        SetUpgrade(0, 1, 0, 0);
        SetAffinity_Green(1, 1, 1);
        SetAffinity_Blue(1, 0, 0);

        SetAffinityRequirement(Affinity.Green, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);

        if (IsStarter())
        {
            GameActions.Bottom.ChannelOrbs(Frost::new, magicNumber);
        }

        if ((CombatStats.Affinities.GetPowerAmount(Affinity.Green) >= secondaryValue || CombatStats.Affinities.GetPowerAmount(Affinity.Blue) >= secondaryValue) && info.TryActivateLimited())
        {
            GameActions.Bottom.MakeCardInDrawPile(new RukiaBankai());
            GameActions.Bottom.Exhaust(this);
        }
    }
}