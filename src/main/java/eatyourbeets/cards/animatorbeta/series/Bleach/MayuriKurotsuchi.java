package eatyourbeets.cards.animatorbeta.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.affinity.AgilityPower;
import eatyourbeets.powers.affinity.CorruptionPower;
import eatyourbeets.powers.affinity.ForcePower;
import eatyourbeets.powers.affinity.IntellectPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class MayuriKurotsuchi extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(MayuriKurotsuchi.class).SetSkill(1, CardRarity.COMMON, EYBCardTarget.Normal).SetSeriesFromClassPackage();
    public static final int POISON_THRESHOLD = 12;


    public MayuriKurotsuchi() {
        super(DATA);

        Initialize(0, 0, 2, 4);
        SetUpgrade(0, 0, 2, 1);

        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Dark(1, 0, 0);
    }

    @Override
    public void Refresh(AbstractMonster enemy) {
        super.Refresh(enemy);

        GameUtilities.IncreaseMagicNumber(this, CombatStats.Affinities.GetPowerAmount(Affinity.Blue) + CombatStats.Affinities.GetPowerAmount(Affinity.Dark) , true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameActions.Bottom.ApplyPoison(TargetHelper.Normal(m), magicNumber)
                .AddCallback(m, (enemy, cards) -> {
                    if (GameUtilities.GetPowerAmount(enemy, PoisonPower.POWER_ID) >= POISON_THRESHOLD && info.TryActivateLimited()) {
                        GameActions.Bottom.GainInspiration(1);
                    }
                });
    }
}