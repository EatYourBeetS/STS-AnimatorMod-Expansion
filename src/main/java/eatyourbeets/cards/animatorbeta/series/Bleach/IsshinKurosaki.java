package eatyourbeets.cards.animatorbeta.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_ApplyToAll;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_GainOrBoost;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class IsshinKurosaki extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(IsshinKurosaki.class).SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None);
    private static final CardEffectChoice choices = new CardEffectChoice();

    public IsshinKurosaki()
    {
        super(DATA);

        Initialize(0, 6, 1, 2);
        SetUpgrade(0, 3, 0);
        SetAffinity_Red(2, 0, 1);

        SetAffinityRequirement(Affinity.Red, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        makeChoice(m);

        if ((CheckAffinity(Affinity.Red) || ForceStance.IsActive()) && CombatStats.TryActivateLimited(cardID))
        {
            makeChoice(m);
        }
    }

    private void makeChoice(AbstractMonster m) {
        if (choices.TryInitialize(this))
        {
            choices.AddEffect(new GenericEffect_ApplyToAll(TargetHelper.Enemies(), PowerHelper.Burning, magicNumber));
            choices.AddEffect(new GenericEffect_GainOrBoost(Affinity.Red, secondaryValue, false));
        }
        choices.Select(1, m);
    }
}