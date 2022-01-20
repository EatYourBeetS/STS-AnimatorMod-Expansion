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

        if (CheckSpecialCondition(true) && CombatStats.TryActivateLimited(cardID))
        {
            final ArrayList<AbstractCard> choices = new ArrayList<>();
            choices.addAll(JUtils.Filter(player.hand.group, c -> c.block > 0));
            choices.addAll(JUtils.Filter(player.discardPile.group, c -> c.block > 0));
            choices.addAll(JUtils.Filter(player.drawPile.group, c -> c.block > 0));

            AbstractCard maxBlockCard = JUtils.FindMax(choices, c -> c.block);
            if (maxBlockCard != null) {
                GameActions.Top.PlayCard(maxBlockCard, m);
            }
        }

        for (int i = 0; i < ATTACK_TIMES; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.NONE)
                    .SetDamageEffect(c -> GameEffects.List.Add(VFX.Clash(c.hb)).SetColors(Color.TEAL, Color.LIGHT_GRAY, Color.SKY, Color.BLUE).duration * 0.1f);
        }
        GameActions.Bottom.StackPower(new SelfImmolationPower(p, magicNumber));
        GameActions.Bottom.TakeDamage(secondaryValue, AttackEffects.CLAW);
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse){
        return player.currentHealth + player.currentBlock + GameUtilities.GetTempHP() < secondaryValue;
    }
}