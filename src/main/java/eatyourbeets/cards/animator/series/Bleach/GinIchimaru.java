package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_GainOrBoost;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class GinIchimaru extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GinIchimaru.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Piercing, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();

    private static final CardEffectChoice choices = new CardEffectChoice();

    public GinIchimaru()
    {
        super(DATA);

        Initialize(3, 0, 2, 1);
        SetUpgrade(1, 0, 0);
        SetAffinity_Dark(1, 0, 0);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Green(2, 0, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.VFX(new DieDieDieEffect());
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE);
        }

        GameActions.Bottom.Callback(() -> makeChoice(m, secondaryValue, magicNumber));

        if (AgilityStance.IsActive())
        {
            GameActions.Bottom.Exhaust(this);
            GameActions.Bottom.Callback(() -> makeChoice(m, secondaryValue, magicNumber));
        }
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    private void makeChoice(AbstractMonster m, int selections, int amount)
    {
        if (choices.TryInitialize(this))
        {
            choices.AddEffect(new GenericEffect_GainOrBoost(Affinity.Red, amount, false));
            choices.AddEffect(new GenericEffect_GainOrBoost(Affinity.Green, amount, false));
        }
        choices.Select(selections, m);
    }
}