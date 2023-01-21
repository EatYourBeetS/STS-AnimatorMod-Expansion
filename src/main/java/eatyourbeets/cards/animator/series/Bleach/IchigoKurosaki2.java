package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.IchigoKurosaki_Bankai;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class IchigoKurosaki2 extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IchigoKurosaki2.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.Random)

            .PostInitialize(data -> data.AddPreview(new IchigoKurosaki_Bankai(), false));

    public IchigoKurosaki2()
    {
        super(DATA);

        Initialize(2, 0, 0, 3);
        SetUpgrade(3, 0, 0, 0);

        SetAffinity_Red(2, 0, 1);
        SetAffinity_Green(1, 1, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.SLASH_HORIZONTAL);

        GameActions.Bottom.GainAffinity(Affinity.Red,1);
        GameActions.Bottom.GainAffinity(Affinity.Green,1);

        GameActions.Bottom.Callback(() -> {
            if (CombatStats.Affinities.GetPowerAmount(Affinity.Red) >= secondaryValue)
            {
                GameActions.Bottom.Exhaust(this);
                GameActions.Bottom.MakeCardInDrawPile(new IchigoKurosaki_Bankai());
            }
        });

    }
}