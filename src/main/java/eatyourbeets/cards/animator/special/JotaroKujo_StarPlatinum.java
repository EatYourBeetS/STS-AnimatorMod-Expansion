package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;

public class JotaroKujo_StarPlatinum extends AnimatorCard
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
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainIntangible(secondaryValue);
        GameActions.Bottom.GainForce(magicNumber);
    }

    @Override
    public void OnUpgrade()
    {
        SetRetain(true);
    }
}