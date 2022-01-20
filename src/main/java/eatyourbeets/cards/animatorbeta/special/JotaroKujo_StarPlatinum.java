package eatyourbeets.cards.animatorbeta.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

import java.util.ArrayList;

public class JotaroKujo_StarPlatinum extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(JotaroKujo_StarPlatinum.class).SetSkill(2, CardRarity.SPECIAL, EYBCardTarget.None).SetSeries(CardSeries.Jojo);

    public JotaroKujo_StarPlatinum()
    {
        super(DATA);

        Initialize(0, 0, 4, 1);
        SetUpgrade(0, 0, 0);
        SetAffinity_Star(2, 0, 0);
        SetExhaust(true);
        SetRetainOnce(true);
    }

    @Override
    public void OnUpgrade() {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainIntangible(secondaryValue);
        GameActions.Bottom.GainForce(magicNumber);
    }
}