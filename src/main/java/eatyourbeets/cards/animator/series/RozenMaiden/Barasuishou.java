package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.JUtils;

public class Barasuishou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Barasuishou.class)
            .SetAttack(1, AbstractCard.CardRarity.UNCOMMON, EYBAttackType.Elemental, eatyourbeets.cards.base.EYBCardTarget.ALL).SetSeriesFromClassPackage();

    public Barasuishou()
    {
        super(DATA);

        Initialize(8, 0, 2, 1);
        SetUpgrade(3, 0, 1);
        SetAffinity_Blue(1, 0, 2);
        SetAffinity_Dark(1, 0, 2);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.DARK);
    }

    @Override
    protected float GetInitialDamage()
    {
        return baseDamage + (magicNumber *
                (JUtils.Count(player.drawPile.group, c -> c.type == AbstractCard.CardType.CURSE) +
                        JUtils.Count(player.discardPile.group, c -> c.type == AbstractCard.CardType.CURSE) +
                        JUtils.Count(player.hand.group, c -> c.type == AbstractCard.CardType.CURSE)));
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        if (player.hand.contains(this))
        {
            GameActions.Last.Exhaust(this);
        }
    }
}


