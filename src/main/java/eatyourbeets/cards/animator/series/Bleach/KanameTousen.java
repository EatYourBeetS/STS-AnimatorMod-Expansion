package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.common.ShacklesPower;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KanameTousen extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KanameTousen.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public KanameTousen()
    {
        super(DATA);

        Initialize(0, 5, 2, 1);
        SetUpgrade(0, 4, 0);
        SetAffinity_Dark(1, 0, 0);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Green(1, 0, 0);

        SetAffinityRequirement(Affinity.Blue, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        for (AbstractMonster mo : GameUtilities.GetEnemies(true))
        {
            if (mo.hasPower(ShacklesPower.POWER_ID))
            {
                GameActions.Bottom.GainTemporaryArtifact(secondaryValue);
            }
            else
            {
                GameActions.Bottom.ReduceStrength(mo, magicNumber, true);
            }
        }

        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.GainArtifact(1);
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
    }
}