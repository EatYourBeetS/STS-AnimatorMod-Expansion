package eatyourbeets.cards.animatorbeta.series.GenshinImpact;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.AnimatorBetaCard;
import eatyourbeets.cards.animator.special.OrbCore_Frost;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards_beta.LogHorizon.Naotsugu;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.animatorbeta.SelfImmolationPower;
import eatyourbeets.powers.common.DelayedDamagePower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

import java.util.ArrayList;

public class AyakaKamisato extends AnimatorBetaCard
{
    public static final EYBCardData DATA = Register(AyakaKamisato.class).SetAttack(3, CardRarity.RARE, EYBAttackType.Normal).SetSeriesFromClassPackage()
            .SetMaxCopies(2);
    private static final int ATTACK_TIMES = 2;
    private static final int THRESHOLD = 6;
    protected boolean checkCache;

    public AyakaKamisato() {
        super(DATA);

        Initialize(28, 0, 3, 13);
        SetUpgrade(5, 0, 0, 0);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Dark(1, 0, 4);

        SetEthereal(true);
        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(ATTACK_TIMES);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {

        for (int i = 0; i < ATTACK_TIMES; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.NONE)
                    .SetDamageEffect(c -> GameEffects.List.Add(VFX.Clash(c.hb)).SetColors(Color.TEAL, Color.LIGHT_GRAY, Color.SKY, Color.BLUE).duration * 0.1f);
        }


        checkCache = CheckSpecialCondition(true);
        int damageAmount = checkCache ? player.currentHealth + player.currentBlock + GameUtilities.GetTempHP() - 1 : secondaryValue;
        GameActions.Bottom.StackPower(new SelfImmolationPower(p, magicNumber));
        GameActions.Bottom.TakeDamage(damageAmount, AttackEffects.CLAW);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        if (checkCache) {
            GameActions.Bottom.PlayFromPile(name, 1, m, p.hand)
                    .SetOptions(false, false)
                    .SetFilter(c -> c.type == CardType.ATTACK)
                    .AddCallback(cards -> {
                        for (AbstractCard c : cards) {
                            GameActions.Last.Exhaust(c);
                        }
                    });
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse){
        return player.currentHealth + player.currentBlock + GameUtilities.GetTempHP() <= secondaryValue && tryUse ? CombatStats.TryActivateLimited(cardID) : CombatStats.CanActivateLimited(cardID);
    }
}