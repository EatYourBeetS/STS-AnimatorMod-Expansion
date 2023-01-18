package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.common.BurningPower;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Tartaglia extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Tartaglia.class).SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.ALL);

    public Tartaglia()
    {
        super(DATA);

        Initialize(12, 0, 4);
        SetUpgrade(3, 0);
        SetAffinity_Red(1, 0, 0);
        SetAffinity_Green(1, 0, 2);
        SetAffinity_Dark(1, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.BLUNT_LIGHT)
                .AddCallback((targets) ->
                {
                    for (AbstractCreature t : targets)
                    {
                        if (GameUtilities.IsDeadOrEscaped(t) && CombatStats.TryActivateLimited(cardID))
                        {
                            GameActions.Bottom.ChangeStance(ForceStance.STANCE_ID);
                            break;
                        }
                    }
                });

        for (AbstractCreature c : GameUtilities.GetAllCharacters(true))
        {
            GameActions.Bottom.RemovePower(p, c, BurningPower.POWER_ID);
        }
    }

    @Override
    protected float GetInitialDamage()
    {
        int amount = 0;
        for (AbstractCreature c : GameUtilities.GetAllCharacters(true))
        {
            if (GameUtilities.GetPowerAmount(c, BurningPower.POWER_ID) > 0)
            {
                amount += magicNumber;
            }
        }
        return baseDamage + amount;
    }
}