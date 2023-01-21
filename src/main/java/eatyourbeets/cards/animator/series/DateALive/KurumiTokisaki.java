package eatyourbeets.cards.animator.series.DateALive;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.animator.special.Essence_Eruza;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.BetaActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KurumiTokisaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KurumiTokisaki.class).SetAttack(3, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.ALL).SetSeriesFromClassPackage();

    public KurumiTokisaki()
    {
        super(DATA);

        Initialize(12, 12, 2);
        SetUpgrade(0, 0, 1);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Dark(1, 1, 0);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.SFX("ATTACK_HEAVY");
        GameActions.Bottom.VFX(new DieDieDieEffect());
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.GUNSHOT);

        GameActions.Bottom.SelectFromHand(name, magicNumber, false)
                .SetOptions(true, true, true)
                .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
                .SetFilter(c -> !c.hasTag(DELAYED))
                .AddCallback(cards ->
                {
                    for (AbstractCard card : cards)
                    {
                        BetaActions.Bottom.ModifyTag(card, DELAYED, true);
                        GameActions.Bottom.StackPower(new EnergizedPower(p, 1));
                    }
                });
        cooldown.ProgressCooldownAndTrigger(m);
    }

    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        GameActions.Delayed.Callback(() -> {
            if (player.hand.contains(this)) {
                GameActions.Top.AutoPlay(this, player.hand, (AbstractMonster)null);
            }

        });
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }
}