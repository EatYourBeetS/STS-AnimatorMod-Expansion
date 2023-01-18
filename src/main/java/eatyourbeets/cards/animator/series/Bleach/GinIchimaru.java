package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_GainOrBoost;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class GinIchimaru extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GinIchimaru.class).SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Piercing, EYBCardTarget.Random);

    private static final CardEffectChoice choices = new CardEffectChoice();

    public GinIchimaru()
    {
        super(DATA);

        Initialize(3, 0, 2, 1);
        SetUpgrade(1, 0, 0);
        SetAffinity_Dark(1, 0, 0);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Green(2, 0, 2);

        SetAffinityRequirement(Affinity.Red, 3);
        SetAffinityRequirement(Affinity.Green, 3);
        SetAffinityRequirement(Affinity.Blue, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.VFX(new DieDieDieEffect());
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE);
        }

        if (TryUseAffinity(Affinity.Red) || TryUseAffinity(Affinity.Green))
        {
            GameActions.Bottom.Exhaust(this);
        }

        if (TryUseAffinity(Affinity.Blue))
        {
            GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(), magicNumber);
        }
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Callback(() -> makeChoice(m, 1));
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    protected void OnUpgrade()
    {
        SetAffinityRequirement(Affinity.Red, 4);
        SetAffinityRequirement(Affinity.Green, 4);
    }

    private void makeChoice(AbstractMonster m, int selections)
    {
        if (choices.TryInitialize(this))
        {
            if (GetHandAffinity(Affinity.Red) > 0)
            {
                choices.AddEffect(new GenericEffect_GainOrBoost(Affinity.Red, GetHandAffinity(Affinity.Red), false));
            }
            if (GetHandAffinity(Affinity.Green) > 0)
            {
                choices.AddEffect(new GenericEffect_GainOrBoost(Affinity.Green, GetHandAffinity(Affinity.Green), false));
            }
        }
        choices.Select(selections, m);
    }
}