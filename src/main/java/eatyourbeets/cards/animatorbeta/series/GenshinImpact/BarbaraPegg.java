package eatyourbeets.cards.animatorbeta.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.HPAttribute;
import eatyourbeets.monsters.EnemyIntent;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class BarbaraPegg extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(BarbaraPegg.class).SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None);

    public BarbaraPegg()
    {
        super(DATA);

        Initialize(0, 0, 4, 2);
        SetUpgrade(0, 0, 2);
        SetAffinity_Light(2);
        SetAffinity_Blue(1);


        SetAffinityRequirement(Affinity.Blue, 3);
        //SetHarmonic(true);
        SetHealing(true);
        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return HPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.VFX(new RainbowCardEffect());
        GameActions.Bottom.TriggerOrbPassive(JUtils.Count(GameUtilities.GetIntents(), EnemyIntent::IsAttacking)).SetRandom(true);
        GameActions.Bottom.HealPlayerLimited(this, magicNumber);
        if (CheckAffinity(Affinity.Blue)) {
            GameActions.Bottom.GainBlessing(secondaryValue, upgraded);
        }

    }
}