package eatyourbeets.cards.animatorbeta.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class Bennett extends AnimatorBetaCard
{
    public static final EYBCardData DATA = RegisterSeriesCard(Bennett.class).SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal);

    public Bennett() {
        super(DATA);

        Initialize(10, 0, 4, 2);
        SetUpgrade(4, 0, 0);
        SetAffinity_Red(1, 0 ,0);

        SetAffinityRequirement(Affinity.Red, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);
        GameActions.Bottom.StackPower(new VigorPower(player, magicNumber + (info.IsSynergizing ? secondaryValue : 0)));
        GameActions.Bottom.DealDamageAtEndOfTurn(player, player, magicNumber, AttackEffects.SMASH);
    }
}