package eatyourbeets.cards.animator.curse;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Curse_SearingBurn extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Curse_SearingBurn.class).SetCurse(-2, EYBCardTarget.ALL, false);

    public Curse_SearingBurn()
    {
        super(DATA);

        Initialize(0, 0, 2, 1);
        SetUpgrade(0, 0, 2, 1);

        SetAffinity_Red(1);

        SetUnplayable(true);
        SetEthereal(true);
    }


    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        if (!this.dontTriggerOnUseCard)
        {
            this.performAction();
        }
    }

    private void performAction()
    {
        int[] damageMatrix = DamageInfo.createDamageMatrix(magicNumber, true);
        GameActions.Bottom.DealDamage(null, player, magicNumber, DamageInfo.DamageType.THORNS, AttackEffects.FIRE);
        GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AttackEffects.FIRE);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true))
        {
            GameActions.Bottom.ApplyBurning(null, enemy, secondaryValue);
        }
        GameActions.Bottom.ApplyBurning(null, player, secondaryValue);
    }

    @Override
    public void triggerOnExhaust()
    {
        this.performAction();
    }
}