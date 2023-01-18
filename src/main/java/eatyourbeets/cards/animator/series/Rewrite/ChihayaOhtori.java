package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.utilities.GameActions;

public class ChihayaOhtori extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ChihayaOhtori.class).SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal);

    private static final CardEffectChoice choices = new CardEffectChoice();

    public ChihayaOhtori()
    {
        super(DATA);

        Initialize(10, 0, 3, 2);
        SetUpgrade(3, 0, 0);
        SetAffinity_Green(2, 0, 1);
        SetAffinity_Red(1, 0, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SMASH);

        String[] text = DATA.Strings.EXTENDED_DESCRIPTION;

        if (choices.TryInitialize(this))
        {
            choices.AddEffect(text[0], this::Effect1);
            choices.AddEffect(text[1], this::Effect2);
        }

        choices.Select(1, m);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        RefreshCost();
    }

    private void Effect1(AbstractCard card, AbstractPlayer p, AbstractMonster m)
    {
        GameActions.Top.FetchFromPile(name, 1, player.discardPile)
                .SetOptions(false, false)
                .SetFilter(c -> (c instanceof AnimatorCard && ((AnimatorCard) c).affinities.GetLevel(Affinity.Red) > 0))
                .AddCallback(cards ->
                {
                    for (AbstractCard card2 : cards)
                    {
                        GameActions.Bottom.Motivate(card2, 1);
                    }
                });
    }

    private void Effect2(AbstractCard card, AbstractPlayer p, AbstractMonster m)
    {
        GameActions.Top.GainTemporaryArtifact(secondaryValue);
    }

    @Override
    public void atTurnStart()
    {
        super.atTurnStart();

        Refresh(null);
    }

    public void RefreshCost()
    {
        int orange = GetHandAffinity(Affinity.Red, false);
        if (AgilityStance.IsActive())
        {
            CostModifiers.For(this).Set(-1);
        }
        else
        {
            CostModifiers.For(this).Set(0);
        }
    }
}