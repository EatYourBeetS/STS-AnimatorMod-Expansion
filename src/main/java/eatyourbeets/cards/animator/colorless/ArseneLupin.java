package eatyourbeets.cards.animator.colorless;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.animator.StolenGoldPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ArseneLupin extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ArseneLupin.class).SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.Random).SetColor(CardColor.COLORLESS).SetSeries(CardSeries.Lupin).SetMaxCopies(2);

    public ArseneLupin()
    {
        super(DATA);

        Initialize(4, 0, 2, 50);
        SetUpgrade(0, 0, 1, -10);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Dark(1);

        SetExhaust(true);
        SetHealing(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.GUNSHOT);
        GameActions.Bottom.Reload(name, cards ->
        {
            if (cards.size() > 0)
            {
                for (int i = 0; i < cards.size(); i++)
                {
                    final AbstractMonster enemy = GameUtilities.GetRandomEnemy(true);
                    if (enemy != null)
                    {
                        GameActions.Bottom.StackPower(new StolenGoldPower(enemy, magicNumber));
                    }
                }
            }
        });
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        return super.ModifyDamage(enemy, amount + Math.floorDiv(player.gold, secondaryValue));
    }
}