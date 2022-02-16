package eatyourbeets.cards.animatorbeta.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animatorbeta.special.JumpyDumpty;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.common.BurningPower;
import eatyourbeets.utilities.GameActions;

public class Klee extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(Klee.class).SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .PostInitialize(data -> data.AddPreview(new JumpyDumpty(), false));

    public Klee()
    {
        super(DATA);

        Initialize(2, 0, 15, 2);
        SetUpgrade(1, 0, 5, 0);
        SetAffinity_Red(1, 1, 0);
        SetAffinity_Blue(1, 0, 0);

        SetAffinityRequirement(Affinity.Red, 4);

        SetExhaust(true);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);
        GameActions.Bottom.Callback(() -> {
            BurningPower.AddPlayerAttackBonus(magicNumber);
        });

        int cardCount = secondaryValue;
        if (CheckAffinity(Affinity.Red)) {
            cardCount += 1;
        }
        for (int i = 0; i < cardCount; i++)
        {
            GameActions.Bottom.MakeCardInDrawPile(new JumpyDumpty()).SetUpgrade(upgraded, false);
        }
    }
}