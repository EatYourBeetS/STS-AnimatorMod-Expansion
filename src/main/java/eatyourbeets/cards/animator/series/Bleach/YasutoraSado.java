package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.genericEffects.GenericEffect_EnterStance;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

import java.util.Arrays;

public class YasutoraSado extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YasutoraSado.class).SetAttack(0, CardRarity.COMMON, EYBAttackType.Normal);

    private static final CardEffectChoice choices = new CardEffectChoice();

    public YasutoraSado()
    {
        super(DATA);

        Initialize(1, 0, 6);
        SetUpgrade(2, 0, 2);
        SetAffinity_Red(2, 0, 1);
        SetCooldown(2, 0, this::OnCooldownCompleted);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        if (choices.TryInitialize(this))
        {
            choices.AddEffect(new GenericEffect_EnterStance(GR.Animator.PlayerClass, AgilityStance.STANCE_ID));
            choices.AddEffect(new GenericEffect_EnterStance(GR.Animator.PlayerClass, ForceStance.STANCE_ID));
        }

        choices.Select(GameActions.Top, 1, m)
                .CancellableFromPlayer(true);

        GameActions.Last.Exhaust(this);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);

        cooldown.ProgressCooldownAndTrigger(m);
    }

    @Override
    protected float GetInitialDamage()
    {
        if (CheckAttackCondition())
        {
            return super.GetInitialDamage() + magicNumber;
        }
        return super.GetInitialDamage();
    }

    private boolean CheckAttackCondition()
    {
        Affinity highestAffinity = JUtils.FindMax(Arrays.asList(Affinity.Basic()), af -> CombatStats.Affinities.GetUsableAffinity(Affinity.Red));
        return (highestAffinity.equals(Affinity.Red));
    }
}