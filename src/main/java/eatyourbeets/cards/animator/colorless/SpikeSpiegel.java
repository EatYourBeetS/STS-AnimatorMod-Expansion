package eatyourbeets.cards.animator.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.SwordfishII;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.HashSet;
import java.util.Set;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.STARTER_STRIKE;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.STRIKE;

public class SpikeSpiegel extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SpikeSpiegel.class).SetAttack(3, CardRarity.RARE, EYBAttackType.Ranged).SetColor(CardColor.COLORLESS).SetSeries(BetaCardSeries.CowboyBebop)
            .PostInitialize(data -> data.AddPreview(new SwordfishII(), false));

    public SpikeSpiegel()
    {
        super(DATA);

        Initialize(10, 0, 3, 3);
        SetUpgrade(2, 0, 1);

        SetAffinity_Red(1, 0, 0);
        SetAffinity_Green(1, 0, 1);

        SetAffinityRequirement(Affinity.General, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
        GameActions.Bottom.PlayFromPile(name, magicNumber, m, player.drawPile)
                .SetFilter(c -> c.hasTag(STRIKE) || c.hasTag(STARTER_STRIKE))
                .AddCallback(cards -> {
                    for (AbstractCard c : cards)
                    {
                        GameActions.Bottom.Callback(() -> {
                            GameUtilities.ModifyCostForCombat(c, -1, true);
                        });
                    }
                });


        if (CheckSpecialCondition(true) && CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Bottom.MakeCardInDiscardPile(new SwordfishII()).SetUpgrade(upgraded, false);
        }

    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        Set<CardType> cardTypes = new HashSet<>();
        for (AbstractCard c : player.hand.group)
        {
            cardTypes.add(c.type);
        }
        return cardTypes.size() >= secondaryValue;
    }
}