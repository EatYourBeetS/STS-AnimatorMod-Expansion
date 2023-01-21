package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.common.BurningPower;
import eatyourbeets.powers.common.FreezingPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KotoriItsuka extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KotoriItsuka.class).SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal);
    public static final int THRESHOLD = 12;
    public static final int BURNING_ATTACK_BONUS = 15;

    public KotoriItsuka()
    {
        super(DATA);

        Initialize(4, 0, 2, BURNING_ATTACK_BONUS);
        SetUpgrade(3, 0, 0);
        SetAffinity_Red(1, 0, 1);
        SetAffinity_Blue(1, 0, 1);

        SetAffinityRequirement(Affinity.Red, 1);
        SetAffinityRequirement(Affinity.Blue, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
        GameActions.Bottom.EvokeOrb(magicNumber);

        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.Callback(() -> BurningPower.AddPlayerAttackBonus(BURNING_ATTACK_BONUS));
        }
    }
}