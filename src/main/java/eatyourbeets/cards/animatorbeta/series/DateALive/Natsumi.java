package eatyourbeets.cards.animatorbeta.series.DateALive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.animatorbeta.SorceryPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;
import java.util.HashMap;

public class Natsumi extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(Natsumi.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.Random)
            ;

    private static HashMap<Integer, ArrayList<AbstractCard>> cardPool;

    public Natsumi()
    {
        super(DATA);

        Initialize(2, 0, 2, 2);
        SetUpgrade(0,0, 1);
        SetAffinity_Blue(2, 0, 1);
        SetExhaust(true);

        SetAffinityRequirement(Affinity.Blue, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.FIRE)
        .SetOptions(true, false);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.SelectFromHand(name, magicNumber, false)
        .SetOptions(true, true, true)
        .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
        .AddCallback(cards ->
        {
            boolean upgrade = CheckAffinity(Affinity.Blue);
            int gainAmount = 0;
            for (AbstractCard card : cards)
            {
                if (!GameUtilities.IsHindrance(card)) {
                    gainAmount += secondaryValue;
                }
                AbstractCard replacement = AbstractDungeon.getCard(CardRarity.UNCOMMON);
                if (upgrade) {
                    replacement.upgrade();
                }
                GameActions.Bottom.ReplaceCard(card.uuid, replacement);
            }
            if (gainAmount > 0) {
                GameActions.Bottom.StackPower(new SorceryPower(player, gainAmount));
            }
        });
    }
}