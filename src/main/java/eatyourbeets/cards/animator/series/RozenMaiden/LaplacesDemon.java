package eatyourbeets.cards.animator.series.RozenMaiden;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.SneckoOil;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.JUtils;

public class LaplacesDemon extends AnimatorCard
{
    public static final EYBCardData DATA = Register(LaplacesDemon.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public LaplacesDemon()
    {
        super(DATA);

        Initialize(0, 0, 5, 0);
        SetUpgrade(0, 0, 1, 0);

        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Dark(2, 0, 0);

        SetDelayed(true);
        SetExhaust(true);

        SetAffinityRequirement(Affinity.General, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int hpLoss = GetPlayerAffinity(Affinity.General) * magicNumber;
        int[] damageMatrix = DamageInfo.createDamageMatrix(hpLoss, true);
        GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.HP_LOSS, AttackEffects.NONE);


        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.Add(new RandomizeHandCostAction());
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return super.CheckSpecialConditionLimited(tryUse, super::CheckSpecialCondition);
    }
}
