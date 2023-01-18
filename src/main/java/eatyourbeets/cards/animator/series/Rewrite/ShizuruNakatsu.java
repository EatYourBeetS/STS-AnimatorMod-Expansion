package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.JUtils;

import java.util.Arrays;

public class ShizuruNakatsu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ShizuruNakatsu.class).SetAttack(1, CardRarity.COMMON, EYBAttackType.Ranged, EYBCardTarget.ALL);

    public ShizuruNakatsu()
    {
        super(DATA);

        Initialize(1, 3, 2, 5);
        SetUpgrade(0, 3, 0);
        SetAffinity_Green(2, 0, 2);
    }

    private int GetNumberOfSkills(CardGroup group)
    {
        int count = 0;

        for (AbstractCard card : group.group)
        {
            if (card.type == CardType.SKILL)
            {
                count++;
            }
        }

        return count;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.GUNSHOT);
        GameActions.Bottom.GainBlock(block);

        if (info.IsSynergizing)
        {
            GameActions.Bottom.DiscardFromHand(name, magicNumber, false)
                    .ShowEffect(true, true)
                    .SetFilter(c -> c.type == CardType.SKILL)
                    .SetOptions(false, true, false)
                    .AddCallback((cards) -> {
                                if (cards.size() >= magicNumber)
                                {
                                    GameActions.Bottom.ChangeStance(AgilityStance.STANCE_ID);
                                }
                            }
                    );
        }

    }

    @Override
    protected float GetInitialDamage()
    {
        if (CheckAttackCondition())
        {
            return super.GetInitialDamage() + secondaryValue;
        }
        return super.GetInitialDamage();
    }

    private boolean CheckAttackCondition()
    {
        Affinity highestAffinity = JUtils.FindMax(Arrays.asList(Affinity.Basic()), this::GetHandAffinity);
        return (highestAffinity.equals(Affinity.Green));
    }
}