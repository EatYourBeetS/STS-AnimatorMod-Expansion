package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class InverseTohka extends AnimatorCard
{
    public static final EYBCardData DATA = Register(InverseTohka.class).SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.ALL).SetSeries(CardSeries.DateALive);

    public InverseTohka()
    {
        super(DATA);

        Initialize(7, 0, 3, 1);
        SetUpgrade(1, 0, 1);
        SetAffinity_Red(2, 0, 0);
        SetAffinity_Dark(1, 0, 0);

        SetAffinityRequirement(Affinity.Red, 3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HEAVY);
        GameActions.Bottom.Add(new ShakeScreenAction(0.5f, ScreenShake.ShakeDur.LONG, ScreenShake.ShakeIntensity.HIGH));
        if (CheckSpecialCondition(true))
        {
            GameActions.Bottom.SelectFromPile(name, magicNumber, player.drawPile, player.hand, player.discardPile)
                    .SetOptions(true, true)
                    .SetFilter(c -> c instanceof AnimatorCard && this.series.equals(((AnimatorCard) c).series))
                    .AddCallback(cards ->
                    {
                        for (AbstractCard c : cards)
                        {
                            GameActions.Bottom.Motivate(c, 1);
                        }
                    });
        }
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        GameActions.Delayed.Callback(() -> {
            if (player.hand.contains(this)) {
                GameActions.Top.AutoPlay(this, player.hand, (AbstractMonster)null);
            }

        });
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        return super.ModifyDamage(enemy, amount + GetPlayerAffinity(Affinity.Blue) * magicNumber);
    }
}