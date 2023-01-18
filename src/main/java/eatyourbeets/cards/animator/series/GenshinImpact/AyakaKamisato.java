package eatyourbeets.cards.animator.series.GenshinImpact;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.common.FreezingPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class AyakaKamisato extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AyakaKamisato.class).SetAttack(3, CardRarity.RARE, EYBAttackType.Normal)
            .SetMaxCopies(1);
    private static final int ATTACK_TIMES = 2;
    protected boolean checkCache;

    public AyakaKamisato()
    {
        super(DATA);

        Initialize(32, 0, 3, 27);
        SetUpgrade(0, 0, 0, -5);
        SetAffinity_Blue(1, 0, 0);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Dark(1, 0, 4);

        SetEthereal(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {

        for (int i = 0; i < ATTACK_TIMES; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.NONE)
                    .SetDamageEffect(c -> GameEffects.List.Add(VFX.Clash(c.hb)).SetColors(Color.TEAL, Color.LIGHT_GRAY, Color.SKY, Color.BLUE).duration * 0.1f);
        }


        checkCache = CheckSpecialCondition(false) && info.TryActivateLimited();
        int damageAmount = checkCache ? Math.min(player.currentHealth + player.currentBlock + GameUtilities.GetTempHP() - 1, secondaryValue) : secondaryValue;
        GameActions.Bottom.StackPower(new FreezingPower(p, p, magicNumber));
        GameActions.Bottom.TakeDamage(damageAmount, AttackEffects.CLAW)
                .IsCancellable(false);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        if (checkCache)
        {
            GameActions.Bottom.PlayFromPile(name, 1, m, p.hand)
                    .SetOptions(false, false)
                    .SetFilter(c -> c.type == CardType.ATTACK)
                    .AddCallback(cards -> {
                        for (AbstractCard c : cards)
                        {
                            GameActions.Last.Exhaust(c);
                        }
                    });
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return (player.currentHealth + player.currentBlock + GameUtilities.GetTempHP() <= secondaryValue);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(ATTACK_TIMES);
    }
}